package cn.iocoder.yudao.module.document.service.document;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.document.controller.admin.document.vo.*;
import cn.iocoder.yudao.module.document.dal.dataobject.document.DocumentDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 文档 Service 接口
 *
 * @author 洋芋
 */
public interface DocumentService {

    /**
     * 创建文档
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createDocument(@Valid DocumentCreateReqVO createReqVO);

    /**
     * 更新文档
     *
     * @param updateReqVO 更新信息
     */
    void updateDocument(@Valid DocumentUpdateReqVO updateReqVO);

    /**
     * 删除文档
     *
     * @param id 编号
     */
    void deleteDocument(Long id);

    /**
     * 获得文档
     *
     * @param id 编号
     * @return 文档
     */
    DocumentDO getDocument(Long id);

    /**
     * 获得文档列表
     *
     * @param ids 编号
     * @return 文档列表
     */
    List<DocumentDO> getDocumentList(Collection<Long> ids);

    /**
     * 获得文档分页
     *
     * @param pageReqVO 分页查询
     * @return 文档分页
     */
    PageResult<DocumentDO> getDocumentPage(DocumentPageReqVO pageReqVO);

    /**
     * 获得文档列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 文档列表
     */
    List<DocumentDO> getDocumentList(DocumentExportReqVO exportReqVO);

}
