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
     * @param item 筛选条件
     * @param current 当前页码
     * @param size  每页大小
     * @return
     */
    public Page<ItemDO> paginQuery(ItemDO item, long current, long size){
        //1. 构建动态查询条件
        LambdaQueryWrapper<ItemDO> queryWrapper = new LambdaQueryWrapper<>();
        if(StrUtil.isNotBlank(item.getItemCode())){
            queryWrapper.eq(ItemDO::getItemCode, item.getItemCode());
        }
        if(StrUtil.isNotBlank(item.getVersion())){
            queryWrapper.eq(ItemDO::getVersion, item.getVersion());
        }
        if(StrUtil.isNotBlank(item.getName())){
            queryWrapper.eq(ItemDO::getName, item.getName());
        }
        if(StrUtil.isNotBlank(item.getMaterial())){
            queryWrapper.eq(ItemDO::getMaterial, item.getMaterial());
        }
        if(StrUtil.isNotBlank(item.getCount())){
            queryWrapper.eq(ItemDO::getCount, item.getCount());
        }
        if(StrUtil.isNotBlank(item.getUnit())){
            queryWrapper.eq(ItemDO::getUnit, item.getUnit());
        }
        if(StrUtil.isNotBlank(item.getIcon())){
            queryWrapper.eq(ItemDO::getIcon, item.getIcon());
        }
        if(StrUtil.isNotBlank(item.getCreator())){
            queryWrapper.eq(ItemDO::getCreator, item.getCreator());
        }
        if(StrUtil.isNotBlank(item.getUpdater())){
            queryWrapper.eq(ItemDO::getUpdater, item.getUpdater());
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
     * @param item 实例对象
     * @return 实例对象
     */
    public ItemDO update(ItemDO item){
        //1. 根据条件动态更新
        LambdaUpdateChainWrapper<ItemDO> chainWrapper = new LambdaUpdateChainWrapper<ItemDO>(ItemDOMapper);
        if(StrUtil.isNotBlank(item.getItemCode())){
            chainWrapper.eq(ItemDO::getItemCode, item.getItemCode());
        }
        if(StrUtil.isNotBlank(item.getVersion())){
            chainWrapper.eq(ItemDO::getVersion, item.getVersion());
        }
        if(StrUtil.isNotBlank(item.getName())){
            chainWrapper.eq(ItemDO::getName, item.getName());
        }
        if(StrUtil.isNotBlank(item.getMaterial())){
            chainWrapper.eq(ItemDO::getMaterial, item.getMaterial());
        }
        if(StrUtil.isNotBlank(item.getCount())){
            chainWrapper.eq(ItemDO::getCount, item.getCount());
        }
        if(StrUtil.isNotBlank(item.getUnit())){
            chainWrapper.eq(ItemDO::getUnit, item.getUnit());
        }
        if(StrUtil.isNotBlank(item.getIcon())){
            chainWrapper.eq(ItemDO::getIcon, item.getIcon());
        }
        if(StrUtil.isNotBlank(item.getCreator())){
            chainWrapper.eq(ItemDO::getCreator, item.getCreator());
        }
        if(StrUtil.isNotBlank(item.getUpdater())){
            chainWrapper.eq(ItemDO::getUpdater, item.getUpdater());
        }
        //2. 设置主键，并更新
        chainWrapper.set(ItemDO::getId, item.getId());
        boolean ret = chainWrapper.update();
        //3. 更新成功了，查询最最对象返回
        if(ret){
            return queryById(item.getId());
        }else{
            return item;
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