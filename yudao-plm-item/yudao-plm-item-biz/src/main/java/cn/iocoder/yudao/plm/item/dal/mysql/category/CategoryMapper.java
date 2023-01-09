package cn.iocoder.yudao.plm.item.dal.mysql.category;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.plm.item.dal.dataobject.category.CategoryDO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.plm.item.controller.admin.category.vo.*;

/**
 * 物料分类 Mapper
 *
 * @author 洋芋
 */
@Mapper
public interface CategoryMapper extends BaseMapperX<CategoryDO> {

    default PageResult<CategoryDO> selectPage(CategoryPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CategoryDO>()
                .eqIfPresent(CategoryDO::getParentId, reqVO.getParentId())
                .eqIfPresent(CategoryDO::getParentCode, reqVO.getParentCode())
                .eqIfPresent(CategoryDO::getCategoryCode, reqVO.getCategoryCode())
                .likeIfPresent(CategoryDO::getName, reqVO.getName())
                .eqIfPresent(CategoryDO::getDescription, reqVO.getDescription())
                .eqIfPresent(CategoryDO::getPicUrl, reqVO.getPicUrl())
                .eqIfPresent(CategoryDO::getSort, reqVO.getSort())
                .eqIfPresent(CategoryDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(CategoryDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CategoryDO::getId));
    }

    default List<CategoryDO> selectList(CategoryExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<CategoryDO>()
                .eqIfPresent(CategoryDO::getParentId, reqVO.getParentId())
                .eqIfPresent(CategoryDO::getParentCode, reqVO.getParentCode())
                .eqIfPresent(CategoryDO::getCategoryCode, reqVO.getCategoryCode())
                .likeIfPresent(CategoryDO::getName, reqVO.getName())
                .eqIfPresent(CategoryDO::getDescription, reqVO.getDescription())
                .eqIfPresent(CategoryDO::getPicUrl, reqVO.getPicUrl())
                .eqIfPresent(CategoryDO::getSort, reqVO.getSort())
                .eqIfPresent(CategoryDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(CategoryDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CategoryDO::getId));
    }

    default List<CategoryDO> selectList(CategoryListReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<CategoryDO>()
                .likeIfPresent(CategoryDO::getName, reqVO.getName())
                .eqIfPresent(CategoryDO::getStatus, reqVO.getStatus()));
    }

    default CategoryDO selectByParentIdAndName(Long parentId, String name) {
        return selectOne(new LambdaQueryWrapper<CategoryDO>()
                .eq(CategoryDO::getParentId, parentId)
                .eq(CategoryDO::getName, name));
    }

}
