package cn.iocoder.yudao.module.plm.item.service.item;

import cn.iocoder.yudao.module.plm.item.dal.dataobject.item.ItemDO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;


/**
 * 物料表;(plm_item)表服务接口
 * @author : http://www.chiner.pro
 * @date : 2022-12-26
 */
public interface ItemService{

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ItemDO queryById(Long id);

    /**
     * 分页查询
     *
     * @param ItemDO 筛选条件
     * @param current 当前页码
     * @param size  每页大小
     * @return
     */
    Page<ItemDO> paginQuery(ItemDO ItemDO, long current, long size);
    /**
     * 新增数据
     *
     * @param ItemDO 实例对象
     * @return 实例对象
     */
    ItemDO insert(ItemDO ItemDO);
    /**
     * 更新数据
     *
     * @param ItemDO 实例对象
     * @return 实例对象
     */
    ItemDO update(ItemDO ItemDO);
    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);
}