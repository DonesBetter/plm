package cn.iocoder.yudao.plm.item.service.item;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.plm.item.controller.admin.item.vo.ItemCreateReqVO;
import cn.iocoder.yudao.plm.item.controller.admin.item.vo.ItemExportReqVO;
import cn.iocoder.yudao.plm.item.controller.admin.item.vo.ItemPageReqVO;
import cn.iocoder.yudao.plm.item.controller.admin.item.vo.ItemUpdateReqVO;
import cn.iocoder.yudao.plm.item.convert.item.ItemConvert;
import cn.iocoder.yudao.plm.item.dal.dataobject.item.ItemDO;
import cn.iocoder.yudao.plm.item.dal.mysql.item.ItemMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.plm.item.enums.ErrorCodeConstants.ITEM_NOT_EXISTS;


/**
 * 物料 Service 实现类
 *
 * @author 洋芋
 */
@Service
@Validated
public class ItemServiceImpl implements ItemService {

    @Resource
    private ItemMapper itemMapper;

    @Override
    public Long createItem(ItemCreateReqVO createReqVO) {
        // 插入
        ItemDO item = ItemConvert.INSTANCE.convert(createReqVO);
        itemMapper.insert(item);
        // 返回
        return item.getId();
    }

    @Override
    public void updateItem(ItemUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateItemExists(updateReqVO.getId());
        // 更新
        ItemDO updateObj = ItemConvert.INSTANCE.convert(updateReqVO);
        itemMapper.updateById(updateObj);
    }

    @Override
    public void deleteItem(Long id) {
        // 校验存在
        this.validateItemExists(id);
        // 删除
        itemMapper.deleteById(id);
    }

    private void validateItemExists(Long id) {
        if (itemMapper.selectById(id) == null) {
            throw exception(ITEM_NOT_EXISTS);
        }
    }

    @Override
    public ItemDO getItem(Long id) {
        return itemMapper.selectById(id);
    }

    @Override
    public List<ItemDO> getItemList(Collection<Long> ids) {
        return itemMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<ItemDO> getItemPage(ItemPageReqVO pageReqVO) {
        return itemMapper.selectPage(pageReqVO);
    }

    @Override
    public List<ItemDO> getItemList(ItemExportReqVO exportReqVO) {
        return itemMapper.selectList(exportReqVO);
    }

}
