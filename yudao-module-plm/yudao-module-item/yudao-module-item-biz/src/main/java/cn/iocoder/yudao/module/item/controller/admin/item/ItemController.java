package cn.iocoder.yudao.module.item.controller.admin.item;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.module.item.controller.admin.item.vo.*;
import cn.iocoder.yudao.module.item.convert.item.ItemConvert;
import cn.iocoder.yudao.module.item.dal.dataobject.item.ItemDO;
import cn.iocoder.yudao.module.item.service.item.ItemService;
import cn.iocoder.yudao.module.item.controller.admin.item.vo.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

@Tag(name = "管理后台 - 物料")
@RestController
@RequestMapping("/plm/item")
@Validated
public class ItemController {

    @Resource
    private ItemService itemService;

    @PostMapping("/create")
    @Operation(summary = "创建物料")
    @PreAuthorize("@ss.hasPermission('plm:item:create')")
    public CommonResult<Long> createItem(@Valid @RequestBody ItemCreateReqVO createReqVO) {
        return success(itemService.createItem(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新物料")
    @PreAuthorize("@ss.hasPermission('plm:item:update')")
    public CommonResult<Boolean> updateItem(@Valid @RequestBody ItemUpdateReqVO updateReqVO) {
        itemService.updateItem(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除物料")
    @PreAuthorize("@ss.hasPermission('plm:item:delete')")
    public CommonResult<Boolean> deleteItem(@RequestParam("id") Long id) {
        itemService.deleteItem(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得物料")
    @PreAuthorize("@ss.hasPermission('plm:item:query')")
    public CommonResult<ItemRespVO> getItem(@RequestParam("id") Long id) {
        ItemDO item = itemService.getItem(id);
        return success(ItemConvert.INSTANCE.convert(item));
    }

    @GetMapping("/list")
    @Operation(summary = "获得物料列表")
    @PreAuthorize("@ss.hasPermission('plm:item:query')")
    public CommonResult<List<ItemRespVO>> getItemList(@RequestParam("ids") Collection<Long> ids) {
        List<ItemDO> list = itemService.getItemList(ids);
        return success(ItemConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得物料分页")
    @PreAuthorize("@ss.hasPermission('plm:item:query')")
    public CommonResult<PageResult<ItemRespVO>> getItemPage(@Valid ItemPageReqVO pageVO) {
        PageResult<ItemDO> pageResult = itemService.getItemPage(pageVO);
        return success(ItemConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出物料 Excel")
    @PreAuthorize("@ss.hasPermission('plm:item:export')")
    @OperateLog(type = EXPORT)
    public void exportItemExcel(@Valid ItemExportReqVO exportReqVO, HttpServletResponse response) throws IOException {
        List<ItemDO> list = itemService.getItemList(exportReqVO);
        // 导出 Excel
        List<ItemExcelVO> datas = ItemConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "物料.xls", "数据", ItemExcelVO.class, datas);
    }

}
