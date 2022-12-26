package cn.iocoder.yudao.plm.item.service.item;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;
import cn.iocoder.yudao.plm.item.controller.admin.item.vo.ItemCreateReqVO;
import cn.iocoder.yudao.plm.item.controller.admin.item.vo.ItemExportReqVO;
import cn.iocoder.yudao.plm.item.controller.admin.item.vo.ItemPageReqVO;
import cn.iocoder.yudao.plm.item.controller.admin.item.vo.ItemUpdateReqVO;
import cn.iocoder.yudao.plm.item.dal.dataobject.item.ItemDO;
import cn.iocoder.yudao.plm.item.dal.mysql.item.ItemMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

import static cn.iocoder.yudao.framework.common.util.object.ObjectUtils.cloneIgnoreId;
import static cn.iocoder.yudao.framework.test.core.util.AssertUtils.assertPojoEquals;
import static cn.iocoder.yudao.framework.test.core.util.AssertUtils.assertServiceException;
import static cn.iocoder.yudao.framework.test.core.util.RandomUtils.randomLongId;
import static cn.iocoder.yudao.framework.test.core.util.RandomUtils.randomPojo;
import static cn.iocoder.yudao.plm.item.enums.ErrorCodeConstants.ITEM_NOT_EXISTS;
import static org.junit.jupiter.api.Assertions.*;

/**
* {@link ItemServiceImpl} 的单元测试类
*
* @author 洋芋
*/
@Import(ItemServiceImpl.class)
public class ItemServiceImplTest extends BaseDbUnitTest {

    @Resource
    private ItemServiceImpl itemService;

    @Resource
    private ItemMapper itemMapper;

    @Test
    public void testCreateItem_success() {
        // 准备参数
        ItemCreateReqVO reqVO = randomPojo(ItemCreateReqVO.class);

        // 调用
        Long itemId = itemService.createItem(reqVO);
        // 断言
        assertNotNull(itemId);
        // 校验记录的属性是否正确
        ItemDO item = itemMapper.selectById(itemId);
        assertPojoEquals(reqVO, item);
    }

    @Test
    public void testUpdateItem_success() {
        // mock 数据
        ItemDO dbItem = randomPojo(ItemDO.class);
        itemMapper.insert(dbItem);// @Sql: 先插入出一条存在的数据
        // 准备参数
        ItemUpdateReqVO reqVO = randomPojo(ItemUpdateReqVO.class, o -> {
            o.setId(dbItem.getId()); // 设置更新的 ID
        });

        // 调用
        itemService.updateItem(reqVO);
        // 校验是否更新正确
        ItemDO item = itemMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, item);
    }

    @Test
    public void testUpdateItem_notExists() {
        // 准备参数
        ItemUpdateReqVO reqVO = randomPojo(ItemUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> itemService.updateItem(reqVO), ITEM_NOT_EXISTS);
    }

    @Test
    public void testDeleteItem_success() {
        // mock 数据
        ItemDO dbItem = randomPojo(ItemDO.class);
        itemMapper.insert(dbItem);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbItem.getId();

        // 调用
        itemService.deleteItem(id);
       // 校验数据不存在了
       assertNull(itemMapper.selectById(id));
    }

    @Test
    public void testDeleteItem_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> itemService.deleteItem(id), ITEM_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetItemPage() {
       // mock 数据
       ItemDO dbItem = randomPojo(ItemDO.class, o -> { // 等会查询到
           o.setItemCode(null);
           o.setVersion(null);
           o.setName(null);
           o.setMaterial(null);
           o.setCount(null);
           o.setUnit(null);
           o.setIcon(null);
           o.setCreateTime(null);
       });
       itemMapper.insert(dbItem);
       // 测试 itemCode 不匹配
       itemMapper.insert(cloneIgnoreId(dbItem, o -> o.setItemCode(null)));
       // 测试 version 不匹配
       itemMapper.insert(cloneIgnoreId(dbItem, o -> o.setVersion(null)));
       // 测试 name 不匹配
       itemMapper.insert(cloneIgnoreId(dbItem, o -> o.setName(null)));
       // 测试 material 不匹配
       itemMapper.insert(cloneIgnoreId(dbItem, o -> o.setMaterial(null)));
       // 测试 count 不匹配
       itemMapper.insert(cloneIgnoreId(dbItem, o -> o.setCount(null)));
       // 测试 unit 不匹配
       itemMapper.insert(cloneIgnoreId(dbItem, o -> o.setUnit(null)));
       // 测试 icon 不匹配
       itemMapper.insert(cloneIgnoreId(dbItem, o -> o.setIcon(null)));
       // 测试 createTime 不匹配
       itemMapper.insert(cloneIgnoreId(dbItem, o -> o.setCreateTime(null)));
       // 准备参数
       ItemPageReqVO reqVO = new ItemPageReqVO();
       reqVO.setItemCode(null);
       reqVO.setVersion(null);
       reqVO.setName(null);
       reqVO.setMaterial(null);
       reqVO.setCount(null);
       reqVO.setUnit(null);
       reqVO.setIcon(null);
       reqVO.setCreateTime((new LocalDateTime[]{}));

       // 调用
       PageResult<ItemDO> pageResult = itemService.getItemPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbItem, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetItemList() {
       // mock 数据
       ItemDO dbItem = randomPojo(ItemDO.class, o -> { // 等会查询到
           o.setItemCode(null);
           o.setVersion(null);
           o.setName(null);
           o.setMaterial(null);
           o.setCount(null);
           o.setUnit(null);
           o.setIcon(null);
           o.setCreateTime(null);
       });
       itemMapper.insert(dbItem);
       // 测试 itemCode 不匹配
       itemMapper.insert(cloneIgnoreId(dbItem, o -> o.setItemCode(null)));
       // 测试 version 不匹配
       itemMapper.insert(cloneIgnoreId(dbItem, o -> o.setVersion(null)));
       // 测试 name 不匹配
       itemMapper.insert(cloneIgnoreId(dbItem, o -> o.setName(null)));
       // 测试 material 不匹配
       itemMapper.insert(cloneIgnoreId(dbItem, o -> o.setMaterial(null)));
       // 测试 count 不匹配
       itemMapper.insert(cloneIgnoreId(dbItem, o -> o.setCount(null)));
       // 测试 unit 不匹配
       itemMapper.insert(cloneIgnoreId(dbItem, o -> o.setUnit(null)));
       // 测试 icon 不匹配
       itemMapper.insert(cloneIgnoreId(dbItem, o -> o.setIcon(null)));
       // 测试 createTime 不匹配
       itemMapper.insert(cloneIgnoreId(dbItem, o -> o.setCreateTime(null)));
       // 准备参数
       ItemExportReqVO reqVO = new ItemExportReqVO();
       reqVO.setItemCode(null);
       reqVO.setVersion(null);
       reqVO.setName(null);
       reqVO.setMaterial(null);
       reqVO.setCount(null);
       reqVO.setUnit(null);
       reqVO.setIcon(null);
       reqVO.setCreateTime((new LocalDateTime[]{}));

       // 调用
       List<ItemDO> list = itemService.getItemList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbItem, list.get(0));
    }

}
