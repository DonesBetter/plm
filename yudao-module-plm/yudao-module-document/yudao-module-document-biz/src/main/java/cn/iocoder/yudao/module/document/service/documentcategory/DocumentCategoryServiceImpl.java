package cn.iocoder.yudao.module.document.service.documentcategory;

import cn.iocoder.yudao.module.document.controller.admin.document.vo.DocumentCategoryListReqVO;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.document.controller.admin.documentcategory.vo.*;
import cn.iocoder.yudao.module.document.dal.dataobject.documentcategory.DocumentCategoryDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.document.convert.documentcategory.DocumentCategoryConvert;
import cn.iocoder.yudao.module.document.dal.mysql.documentcategory.DocumentCategoryMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.document.enums.ErrorCodeConstants.*;

/**
 * 文档分类 Service 实现类
 *
 * @author 洋芋
 */
@Service
@Validated
public class DocumentCategoryServiceImpl implements DocumentCategoryService {

    @Resource
    private DocumentCategoryMapper documentCategoryMapper;

    @Override
    public Long createDocumentCategory(DocumentCategoryCreateReqVO createReqVO) {
        // 插入
        DocumentCategoryDO documentCategory = DocumentCategoryConvert.INSTANCE.convert(createReqVO);
        documentCategoryMapper.insert(documentCategory);
        // 返回
        return documentCategory.getId();
    }

    @Override
    public void updateDocumentCategory(DocumentCategoryUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateDocumentCategoryExists(updateReqVO.getId());
        // 更新
        DocumentCategoryDO updateObj = DocumentCategoryConvert.INSTANCE.convert(updateReqVO);
        documentCategoryMapper.updateById(updateObj);
    }

    @Override
    public void deleteDocumentCategory(Long id) {
        // 校验存在
        this.validateDocumentCategoryExists(id);
        // 删除
        documentCategoryMapper.deleteById(id);
    }

    private void validateDocumentCategoryExists(Long id) {
        if (documentCategoryMapper.selectById(id) == null) {
            throw exception(DOCUMENT_CATEGORY_NOT_EXISTS);
        }
    }

    @Override
    public DocumentCategoryDO getDocumentCategory(Long id) {
        return documentCategoryMapper.selectById(id);
    }

    @Override
    public List<DocumentCategoryDO> getDocumentCategoryList(Collection<Long> ids) {
        return documentCategoryMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<DocumentCategoryDO> getDocumentCategoryPage(DocumentCategoryPageReqVO pageReqVO) {
        return documentCategoryMapper.selectPage(pageReqVO);
    }

    @Override
    public List<DocumentCategoryDO> getDocumentCategoryList(DocumentCategoryExportReqVO exportReqVO) {
        return documentCategoryMapper.selectList(exportReqVO);
    }

    @Override
    public List<DocumentCategoryDO> getSimpleCategories(DocumentCategoryListReqVO reqVO) {

        return documentCategoryMapper.queryCategories(reqVO);

    }

}
