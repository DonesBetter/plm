package cn.iocoder.yudao.module.document.service.documentcategory;

import java.util.*;
import javax.validation.*;

import cn.iocoder.yudao.module.document.controller.admin.document.vo.DocumentCategoryListReqVO;
import cn.iocoder.yudao.module.document.controller.admin.documentcategory.vo.*;
import cn.iocoder.yudao.module.document.dal.dataobject.documentcategory.DocumentCategoryDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 文档分类 Service 接口
 *
 * @author 洋芋
 */
public interface DocumentCategoryService {

    /**
     * 创建文档分类
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDocumentCategory(@Valid DocumentCategoryCreateReqVO createReqVO);

    /**
     * 更新文档分类
     *
     * @param updateReqVO 更新信息
     */
    void updateDocumentCategory(@Valid DocumentCategoryUpdateReqVO updateReqVO);

    /**
     * 删除文档分类
     *
     * @param id 编号
     */
    void deleteDocumentCategory(Long id);

    /**
     * 获得文档分类
     *
     * @param id 编号
     * @return 文档分类
     */
    DocumentCategoryDO getDocumentCategory(Long id);

    /**
     * 获得文档分类列表
     *
     * @param ids 编号
     * @return 文档分类列表
     */
    List<DocumentCategoryDO> getDocumentCategoryList(Collection<Long> ids);

    /**
     * 获得文档分类分页
     *
     * @param pageReqVO 分页查询
     * @return 文档分类分页
     */
    PageResult<DocumentCategoryDO> getDocumentCategoryPage(DocumentCategoryPageReqVO pageReqVO);

    /**
     * 获得文档分类列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 文档分类列表
     */
    List<DocumentCategoryDO> getDocumentCategoryList(DocumentCategoryExportReqVO exportReqVO);

    /**
     * 获取分类列表
     * @param reqVO
     * @return
     */
    List<DocumentCategoryDO> getSimpleCategories(DocumentCategoryListReqVO reqVO);
}
