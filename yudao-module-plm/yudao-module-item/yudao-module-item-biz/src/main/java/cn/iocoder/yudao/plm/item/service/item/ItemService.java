package cn.iocoder.yudao.plm.item.service.item;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.plm.item.controller.admin.item.vo.ItemCreateReqVO;
import cn.iocoder.yudao.plm.item.controller.admin.item.vo.ItemExportReqVO;
import cn.iocoder.yudao.plm.item.controller.admin.item.vo.ItemPageReqVO;
import cn.iocoder.yudao.plm.item.controller.admin.item.vo.ItemUpdateReqVO;
import cn.iocoder.yudao.plm.item.dal.dataobject.item.ItemDO;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * 物料 Service 接口
 *
 * @author 洋芋
 */
public interface ItemService {

    /**
     * 创建物料
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createItem(@Valid ItemCreateReqVO createReqVO);

    /**
     * 更新物料
     *
     * @param updateReqVO 更新信息
     */
    void updateItem(@Valid ItemUpdateReqVO updateReqVO);

    /**
     * 删除物料
     *
     * @param id 编号
     */
    void deleteItem(Long id);

    /**
     * 获得物料
     *
     * @param id 编号
     * @return 物料
     */
    ItemDO getItem(Long id);

    /**
     * 获得物料列表
     *
     * @param ids 编号
     * @return 物料列表
     */
    List<ItemDO> getItemList(Collection<Long> ids);

    /**
     * 获得物料分页
     *
     * @param pageReqVO 分页查询
     * @return 物料分页
     */
    PageResult<ItemDO> getItemPage(ItemPageReqVO pageReqVO);

    /**
     * 获得物料列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 物料列表
     */
    List<ItemDO> getItemList(ItemExportReqVO exportReqVO);

}
