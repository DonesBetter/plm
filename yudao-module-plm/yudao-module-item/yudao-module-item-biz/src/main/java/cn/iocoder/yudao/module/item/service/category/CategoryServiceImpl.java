package cn.iocoder.yudao.module.item.service.category;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.tenant.core.context.TenantContextHolder;
import cn.iocoder.yudao.module.item.controller.admin.category.vo.*;
import cn.iocoder.yudao.module.item.convert.category.CategoryConvert;
import cn.iocoder.yudao.module.item.controller.admin.category.vo.*;
import cn.iocoder.yudao.module.item.dal.dataobject.category.CategoryDO;
import cn.iocoder.yudao.module.item.dal.mysql.category.CategoryMapper;
import cn.iocoder.yudao.module.item.enums.category.CategoryIdEnum;
import com.google.common.collect.Multimap;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.item.enums.ErrorCodeConstants.*;

/**
 * 物料分类 Service 实现类
 *
 * @author 洋芋
 */
@Service
@Validated
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;


    /**
     * 父部门缓存
     * key：部门编号 {@link CategoryDO#getParentId()}
     * value: 直接子部门列表
     * <p>
     * 这里声明 volatile 修饰的原因是，每次刷新时，直接修改指向
     */
    private volatile Multimap<Long, CategoryDO> parentCategoryCache;


    @Override
    public Long createCategory(CategoryCreateReqVO createReqVO) {
        // 校验正确性
        if (createReqVO.getParentId() == null) {
            createReqVO.setParentId(CategoryIdEnum.ROOT.getId());
        }
        checkCreateOrUpdate(null, createReqVO.getParentId(), createReqVO.getName());
        // 插入
        CategoryDO category = CategoryConvert.INSTANCE.convert(createReqVO);
        categoryMapper.insert(category);
        // 返回
        //TODO  发送刷新消息
        return category.getId();
    }


    @Override
    public void updateCategory(CategoryUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateCategoryExists(updateReqVO.getId());
        // 更新
        CategoryDO updateObj = CategoryConvert.INSTANCE.convert(updateReqVO);
        categoryMapper.updateById(updateObj);
    }

    @Override
    public void deleteCategory(Long id) {
        // 校验存在
        this.validateCategoryExists(id);
        // 删除
        categoryMapper.deleteById(id);
    }

    private void validateCategoryExists(Long id) {
        if (categoryMapper.selectById(id) == null) {
            throw exception(CATEGORY_NOT_FOUND);
        }
    }

    @Override
    public CategoryDO getCategory(Long id) {
        return categoryMapper.selectById(id);
    }

    @Override
    public List<CategoryDO> getCategoryList(Collection<Long> ids) {
        return categoryMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<CategoryDO> getCategoryPage(CategoryPageReqVO pageReqVO) {
        return categoryMapper.selectPage(pageReqVO);
    }

    @Override
    public List<CategoryDO> getCategoryList(CategoryExportReqVO exportReqVO) {
        return categoryMapper.selectList(exportReqVO);
    }

    @Override
    public List<CategoryDO> getSimpleCategorys(CategoryListReqVO reqVO) {
        return categoryMapper.selectList(reqVO);
    }

    @Override
    public List<CategoryDO> getCategorysByParentIdFromCache(Long parentId, boolean recursive) {
        if (parentId == null) {
            return Collections.emptyList();
        }
        List<CategoryDO> result = new ArrayList<>(); // TODO 芋艿：待优化，新增缓存，避免每次遍历的计算
        // 递归，简单粗暴
        this.getCategorysByParentIdFromCache(result, parentId,
                recursive ? Integer.MAX_VALUE : 1, // 如果递归获取，则无限；否则，只递归 1 次
                parentCategoryCache);
        return result;
    }


    /**
     * 递归获取所有的子部门，添加到 result 结果
     *
     * @param result         结果
     * @param parentId       父编号
     * @param recursiveCount 递归次数
     * @param parentDeptMap  父部门 Map，使用缓存，避免变化
     */
    private void getCategorysByParentIdFromCache(List<CategoryDO> result, Long parentId, int recursiveCount,
                                                 Multimap<Long, CategoryDO> parentDeptMap) {
        // 递归次数为 0，结束！
        if (recursiveCount == 0) {
            return;
        }

        // 获得子部门
        Collection<CategoryDO> depts = parentDeptMap.get(parentId);
        if (CollUtil.isEmpty(depts)) {
            return;
        }
        // 针对多租户，过滤掉非当前租户的部门
        Long tenantId = TenantContextHolder.getTenantId();
        if (tenantId != null) {
//            depts = CollUtil.filterNew(depts, dept -> tenantId.equals(dept.getTenantId()));
        }
        result.addAll(depts);

        // 继续递归
        depts.forEach(dept -> getCategorysByParentIdFromCache(result, dept.getId(),
                recursiveCount - 1, parentDeptMap));
    }


    private void checkCreateOrUpdate(Long id, Long parentId, String name) {
        // 校验自己存在
        checkCategoryExists(id);
        // 校验父物料分类的有效性
        checkParentCategoryEnable(id, parentId);
        // 校验物料分类名的唯一性
        checkCategoryNameUnique(id, parentId, name);
    }

    private void checkCategoryExists(Long id) {
        if (id == null) {
            return;
        }
        CategoryDO category = categoryMapper.selectById(id);
        if (category == null) {
            throw exception(CATEGORY_NOT_FOUND);
        }
    }

    private void checkParentCategoryEnable(Long id, Long parentId) {
        if (parentId == null || CategoryIdEnum.ROOT.getId().equals(parentId)) {
            return;
        }
        // 不能设置自己为父物料分类
        if (parentId.equals(id)) {
            throw exception(CATEGORY_PARENT_ERROR);
        }
        // 父岗位不存在
        CategoryDO category = categoryMapper.selectById(parentId);
        if (category == null) {
            throw exception(CATEGORY_PARENT_NOT_EXITS);
        }
        // 父物料分类被禁用
        if (!CommonStatusEnum.ENABLE.getStatus().equals(category.getStatus())) {
            throw exception(CATEGORY_NOT_ENABLE);
        }
        // 父物料分类不能是原来的子物料分类
        List<CategoryDO> children = this.getCategorysByParentIdFromCache(id, true);
        if (children.stream().anyMatch(category1 -> category1.getId().equals(parentId))) {
            throw exception(CATEGORY_PARENT_IS_CHILD);
        }
    }

    private void checkCategoryNameUnique(Long id, Long parentId, String name) {
        CategoryDO categoryDO = categoryMapper.selectByParentIdAndName(parentId, name);
        if (categoryDO == null) {
            return;
        }
        // 看是不是自己
        if (id == null) {
            throw exception(CATEGORY_NAME_DUPLICATE);
        }
        if (!categoryDO.getId().equals(id)) {
            throw exception(CATEGORY_NAME_DUPLICATE);
        }
    }
}
