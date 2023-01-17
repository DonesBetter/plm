package cn.iocoder.yudao.module.document.convert.document;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.document.controller.admin.document.vo.*;
import cn.iocoder.yudao.module.document.dal.dataobject.document.DocumentDO;

/**
 * 文档 Convert
 *
 * @author 洋芋
 */
@Mapper
public interface DocumentConvert {

    DocumentConvert INSTANCE = Mappers.getMapper(DocumentConvert.class);

    DocumentDO convert(DocumentCreateReqVO bean);

    DocumentDO convert(DocumentUpdateReqVO bean);

    DocumentRespVO convert(DocumentDO bean);

    List<DocumentRespVO> convertList(List<DocumentDO> list);

    PageResult<DocumentRespVO> convertPage(PageResult<DocumentDO> page);

    List<DocumentExcelVO> convertList02(List<DocumentDO> list);

}
