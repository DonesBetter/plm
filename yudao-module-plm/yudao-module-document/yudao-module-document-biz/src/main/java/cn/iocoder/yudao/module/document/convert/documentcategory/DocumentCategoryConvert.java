package cn.iocoder.yudao.module.document.convert.documentcategory;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.document.controller.admin.document.vo.DocumentCategorySimpleRespVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.document.controller.admin.documentcategory.vo.*;
import cn.iocoder.yudao.module.document.dal.dataobject.documentcategory.DocumentCategoryDO;

/**
 * 文档分类 Convert
 *
 * @author 洋芋
 */
@Mapper
public interface DocumentCategoryConvert {

    DocumentCategoryConvert INSTANCE = Mappers.getMapper(DocumentCategoryConvert.class);

    DocumentCategoryDO convert(DocumentCategoryCreateReqVO bean);

    DocumentCategoryDO convert(DocumentCategoryUpdateReqVO bean);

    DocumentCategoryRespVO convert(DocumentCategoryDO bean);

    List<DocumentCategoryRespVO> convertList(List<DocumentCategoryDO> list);

    PageResult<DocumentCategoryRespVO> convertPage(PageResult<DocumentCategoryDO> page);

    List<DocumentCategoryExcelVO> convertList02(List<DocumentCategoryDO> list);

    List<DocumentCategorySimpleRespVO> convertList03(List<DocumentCategoryDO> list);
}
