package cn.iocoder.yudao.plm.item.controller.admin;

import cn.iocoder.yudao.plm.item.dal.dataobject.item.ItemDO;
import cn.iocoder.yudao.plm.item.service.item.ItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * 物料表;(plm_item)表控制层
 *
 * @author : http://www.chiner.pro
 * @date : 2022-12-26
 */
@Api(tags = "plm - 物料库")
@RestController
@RequestMapping("/plm/item")
@Validated
public class ItemController {
    @Resource
    private ItemService ItemDOService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @ApiOperation("通过ID查询单条数据")
    @GetMapping("{id}")
    @PreAuthorize("@ss.hasPermission('system:dept:create')")
    public ResponseEntity<ItemDO> queryById(Long id) {
        return ResponseEntity.ok(ItemDOService.queryById(id));
    }

    /**
     * 分页查询
     *
     * @param ItemDO      筛选条件
     * @param pageRequest 分页对象
     * @return 查询结果
     */
    @ApiOperation("分页查询")
    @GetMapping("/list")
    @PreAuthorize("@ss.hasPermission('system:dept:create')")
    public ResponseEntity<PageImpl<ItemDO>> paginQuery(ItemDO ItemDO, PageRequest pageRequest) {
        //1.分页参数
        long current = pageRequest.getPageNumber();
        long size = pageRequest.getPageSize();
        //2.分页查询
        /*把Mybatis的分页对象做封装转换，MP的分页对象上有一些SQL敏感信息，还是通过spring的分页模型来封装数据吧*/
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<ItemDO> pageResult = ItemDOService.paginQuery(ItemDO, current, size);
        //3. 分页结果组装
        List<ItemDO> dataList = pageResult.getRecords();
        long total = pageResult.getTotal();
        PageImpl<ItemDO> retPage = new PageImpl<ItemDO>(dataList, pageRequest, total);
        return ResponseEntity.ok(retPage);
    }

    /**
     * 新增数据
     *
     * @param ItemDO 实例对象
     * @return 实例对象
     */
    @ApiOperation("新增数据")
    @PostMapping("create")
    @PreAuthorize("@ss.hasPermission('system:dept:create')")
    public ResponseEntity<ItemDO> create(ItemDO ItemDO) {
        return ResponseEntity.ok(ItemDOService.insert(ItemDO));
    }

    /**
     * 更新数据
     *
     * @param ItemDO 实例对象
     * @return 实例对象
     */
    @ApiOperation("更新数据")
    @PutMapping("update")
    @PreAuthorize("@ss.hasPermission('system:dept:create')")
    public ResponseEntity<ItemDO> update(ItemDO ItemDO) {
        return ResponseEntity.ok(ItemDOService.update(ItemDO));
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @ApiOperation("通过主键删除数据")
    @DeleteMapping("delete")
    @PreAuthorize("@ss.hasPermission('system:dept:create')")
    public ResponseEntity<Boolean> deleteById(@RequestParam("id") Long id) {
        return ResponseEntity.ok(ItemDOService.deleteById(id));
    }
}