package cn.iocoder.yudao.module.item.dal.mysql.item;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.item.controller.admin.item.vo.ItemExportReqVO;
import cn.iocoder.yudao.module.item.controller.admin.item.vo.ItemPageReqVO;
import cn.iocoder.yudao.module.item.dal.dataobject.item.ItemDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.item.controller.admin.item.vo.*;

/**
 * 物料 Mapper
 *
 * @author 洋芋
 */
@Mapper
public interface ItemMapper extends BaseMapperX<ItemDO> {

    default PageResult<ItemDO> selectPage(ItemPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ItemDO>()
                .eqIfPresent(ItemDO::getItemCode, reqVO.getItemCode())
                .eqIfPresent(ItemDO::getVersion, reqVO.getVersion())
                .likeIfPresent(ItemDO::getName, reqVO.getName())
                .eqIfPresent(ItemDO::getCategoryId, reqVO.getCategoryId())
                .eqIfPresent(ItemDO::getCategoryCode, reqVO.getCategoryCode())
                .eqIfPresent(ItemDO::getMaterial, reqVO.getMaterial())
                .eqIfPresent(ItemDO::getCost, reqVO.getCost())
                .eqIfPresent(ItemDO::getWeight, reqVO.getWeight())
                .eqIfPresent(ItemDO::getUnit, reqVO.getUnit())
                .eqIfPresent(ItemDO::getStock, reqVO.getStock())
                .eqIfPresent(ItemDO::getPicUrl, reqVO.getPicUrl())
                .eqIfPresent(ItemDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(ItemDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ItemDO::getId));
    }

    default List<ItemDO> selectList(ItemExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ItemDO>()
                .eqIfPresent(ItemDO::getItemCode, reqVO.getItemCode())
                .eqIfPresent(ItemDO::getVersion, reqVO.getVersion())
                .likeIfPresent(ItemDO::getName, reqVO.getName())
                .eqIfPresent(ItemDO::getCategoryId, reqVO.getCategoryId())
                .eqIfPresent(ItemDO::getCategoryCode, reqVO.getCategoryCode())
                .eqIfPresent(ItemDO::getMaterial, reqVO.getMaterial())
                .eqIfPresent(ItemDO::getCost, reqVO.getCost())
                .eqIfPresent(ItemDO::getWeight, reqVO.getWeight())
                .eqIfPresent(ItemDO::getUnit, reqVO.getUnit())
                .eqIfPresent(ItemDO::getStock, reqVO.getStock())
                .eqIfPresent(ItemDO::getPicUrl, reqVO.getPicUrl())
                .eqIfPresent(ItemDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(ItemDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ItemDO::getId));
    }

}
