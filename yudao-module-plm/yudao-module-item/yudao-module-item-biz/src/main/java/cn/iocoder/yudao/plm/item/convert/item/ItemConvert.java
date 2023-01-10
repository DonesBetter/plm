package cn.iocoder.yudao.plm.item.convert.item;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.plm.item.controller.admin.item.vo.*;
import cn.iocoder.yudao.plm.item.dal.dataobject.item.ItemDO;

/**
 * 物料 Convert
 *
 * @author 洋芋
 */
@Mapper
public interface ItemConvert {

    ItemConvert INSTANCE = Mappers.getMapper(ItemConvert.class);

    ItemDO convert(ItemCreateReqVO bean);

    ItemDO convert(ItemUpdateReqVO bean);

    ItemRespVO convert(ItemDO bean);

    List<ItemRespVO> convertList(List<ItemDO> list);

    PageResult<ItemRespVO> convertPage(PageResult<ItemDO> page);

    List<ItemExcelVO> convertList02(List<ItemDO> list);

}
