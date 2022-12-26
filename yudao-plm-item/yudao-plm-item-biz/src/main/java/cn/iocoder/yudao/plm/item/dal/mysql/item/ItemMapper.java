package cn.iocoder.yudao.plm.item.dal.mysql.item;

import cn.iocoder.yudao.plm.item.dal.dataobject.item.ItemDO;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 物料表;(plm_item)表数据库访问层
 *
 * @author : http://www.chiner.pro
 * @date : 2022-12-26
 */
@Mapper
public interface ItemMapper extends BaseMapper<ItemDO> {
    /**
     * 分页查询指定行数据
     *
     * @param page    分页参数
     * @param wrapper 动态查询条件
     * @return 分页对象列表
     */
    IPage<ItemDO> selectByPage(IPage<ItemDO> page, @Param(Constants.WRAPPER) Wrapper<ItemDO> wrapper);
}