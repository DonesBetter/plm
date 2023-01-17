package cn.iocoder.yudao.module.document.service.document;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.document.controller.admin.document.vo.*;
import cn.iocoder.yudao.module.document.dal.dataobject.document.DocumentDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.document.convert.document.DocumentConvert;
import cn.iocoder.yudao.module.document.dal.mysql.document.DocumentMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.document.enums.ErrorCodeConstants.*;

/**
 * 文档 Service 实现类
 *
 * @author 洋芋
 */
@Service
@Validated
public class DocumentServiceImpl implements DocumentService {

    @Resource
    private DocumentMapper documentMapper;

    @Override
    public Long createDocument(DocumentCreateReqVO createReqVO) {
        // 插入
        DocumentDO document = DocumentConvert.INSTANCE.convert(createReqVO);
        documentMapper.insert(document);
        // 返回
        return document.getId();
    }

    @Override
    public void updateDocument(DocumentUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateDocumentExists(updateReqVO.getId());
        // 更新
        DocumentDO updateObj = DocumentConvert.INSTANCE.convert(updateReqVO);
        documentMapper.updateById(updateObj);
    }

    @Override
    public void deleteDocument(Long id) {
        // 校验存在
        this.validateDocumentExists(id);
        // 删除
        documentMapper.deleteById(id);
    }

    private void validateDocumentExists(Long id) {
        if (documentMapper.selectById(id) == null) {
            throw exception(DOCUMENT_NOT_EXISTS);
        }
    }

    @Override
    public DocumentDO getDocument(Long id) {
        return documentMapper.selectById(id);
    }

    @Override
    public List<DocumentDO> getDocumentList(Collection<Long> ids) {
        return documentMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<DocumentDO> getDocumentPage(DocumentPageReqVO pageReqVO) {
        return documentMapper.selectPage(pageReqVO);
    }

    @Override
    public List<DocumentDO> getDocumentList(DocumentExportReqVO exportReqVO) {
        return documentMapper.selectList(exportReqVO);
    }

}
