package cn.iocoder.yudao.plm.item.service.item;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.plm.item.dal.dataobject.item.ItemDO;
import cn.iocoder.yudao.plm.item.dal.mysql.item.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;

/**
 * 物料表;(plm_item)表服务实现类
 * @author : http://www.chiner.pro
 * @date : 2022-12-26
 */
@Service
public class ItemServiceImpl implements ItemService{
    @Autowired
    private ItemMapper ItemDOMapper;

    /**
     * 通过ID查询单条数据 
     *
     * @param id 主键
     * @return 实例对象
     */
    public ItemDO queryById(Long id){
        return ItemDOMapper.selectById(id);
    }

    /**
     * 分页查询
     *
     * @param ItemDO 筛选条件
     * @param current 当前页码
     * @param size  每页大小
     * @return
     */
    public Page<ItemDO> paginQuery(ItemDO ItemDO, long current, long size){
        //1. 构建动态查询条件
        LambdaQueryWrapper<ItemDO> queryWrapper = new LambdaQueryWrapper<>();
        if(StrUtil.isNotBlank(ItemDO.getItemCode())){
            queryWrapper.eq(ItemDO::getItemCode, ItemDO.getItemCode());
        }
        if(StrUtil.isNotBlank(ItemDO.getVersion())){
            queryWrapper.eq(ItemDO::getVersion, ItemDO.getVersion());
        }
        if(StrUtil.isNotBlank(ItemDO.getName())){
            queryWrapper.eq(ItemDO::getName, ItemDO.getName());
        }
        if(StrUtil.isNotBlank(ItemDO.getMaterial())){
            queryWrapper.eq(ItemDO::getMaterial, ItemDO.getMaterial());
        }
        if(StrUtil.isNotBlank(ItemDO.getCount())){
            queryWrapper.eq(ItemDO::getCount, ItemDO.getCount());
        }
        if(StrUtil.isNotBlank(ItemDO.getUnit())){
            queryWrapper.eq(ItemDO::getUnit, ItemDO.getUnit());
        }
        if(StrUtil.isNotBlank(ItemDO.getIcon())){
            queryWrapper.eq(ItemDO::getIcon, ItemDO.getIcon());
        }
        if(StrUtil.isNotBlank(ItemDO.getCreator())){
            queryWrapper.eq(ItemDO::getCreator, ItemDO.getCreator());
        }
        if(StrUtil.isNotBlank(ItemDO.getUpdater())){
            queryWrapper.eq(ItemDO::getUpdater, ItemDO.getUpdater());
        }
        //2. 执行分页查询
        Page<ItemDO> pagin = new Page<>(current , size , true);
        IPage<ItemDO> selectResult = ItemDOMapper.selectByPage(pagin , queryWrapper);
        pagin.setPages(selectResult.getPages());
        pagin.setTotal(selectResult.getTotal());
        pagin.setRecords(selectResult.getRecords());
        //3. 返回结果
        return pagin;
    }

    /**
     * 新增数据
     *
     * @param ItemDO 实例对象
     * @return 实例对象
     */
    public ItemDO insert(ItemDO ItemDO){
        ItemDOMapper.insert(ItemDO);
        return ItemDO;
    }

    /**
     * 更新数据
     *
     * @param ItemDO 实例对象
     * @return 实例对象
     */
    public ItemDO update(ItemDO ItemDO){
        //1. 根据条件动态更新
        LambdaUpdateChainWrapper<ItemDO> chainWrapper = new LambdaUpdateChainWrapper<ItemDO>(ItemDOMapper);
        if(StrUtil.isNotBlank(ItemDO.getItemCode())){
            chainWrapper.eq(ItemDO::getItemCode, ItemDO.getItemCode());
        }
        if(StrUtil.isNotBlank(ItemDO.getVersion())){
            chainWrapper.eq(ItemDO::getVersion, ItemDO.getVersion());
        }
        if(StrUtil.isNotBlank(ItemDO.getName())){
            chainWrapper.eq(ItemDO::getName, ItemDO.getName());
        }
        if(StrUtil.isNotBlank(ItemDO.getMaterial())){
            chainWrapper.eq(ItemDO::getMaterial, ItemDO.getMaterial());
        }
        if(StrUtil.isNotBlank(ItemDO.getCount())){
            chainWrapper.eq(ItemDO::getCount, ItemDO.getCount());
        }
        if(StrUtil.isNotBlank(ItemDO.getUnit())){
            chainWrapper.eq(ItemDO::getUnit, ItemDO.getUnit());
        }
        if(StrUtil.isNotBlank(ItemDO.getIcon())){
            chainWrapper.eq(ItemDO::getIcon, ItemDO.getIcon());
        }
        if(StrUtil.isNotBlank(ItemDO.getCreator())){
            chainWrapper.eq(ItemDO::getCreator, ItemDO.getCreator());
        }
        if(StrUtil.isNotBlank(ItemDO.getUpdater())){
            chainWrapper.eq(ItemDO::getUpdater, ItemDO.getUpdater());
        }
        //2. 设置主键，并更新
        chainWrapper.set(ItemDO::getId, ItemDO.getId());
        boolean ret = chainWrapper.update();
        //3. 更新成功了，查询最最对象返回
        if(ret){
            return queryById(ItemDO.getId());
        }else{
            return ItemDO;
        }
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public boolean deleteById(Long id){
        int total = ItemDOMapper.deleteById(id);
        return total > 0;
    }
}