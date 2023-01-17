package cn.iocoder.yudao.module.item.convert.category;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.item.controller.admin.category.vo.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.item.controller.admin.category.vo.*;
import cn.iocoder.yudao.module.item.dal.dataobject.category.CategoryDO;

/**
 * 物料分类 Convert
 *
 * @author 洋芋
 */
@Mapper
public interface CategoryConvert {

    CategoryConvert INSTANCE = Mappers.getMapper(CategoryConvert.class);

    CategoryDO convert(CategoryCreateReqVO bean);

    CategoryDO convert(CategoryUpdateReqVO bean);

    CategoryRespVO convert(CategoryDO bean);

    List<CategoryRespVO> convertList(List<CategoryDO> list);

    PageResult<CategoryRespVO> convertPage(PageResult<CategoryDO> page);

    List<CategoryExcelVO> convertList02(List<CategoryDO> list);

    List<CategorySimpleRespVO> convertList03(List<CategoryDO> list);

}
