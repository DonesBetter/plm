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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
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

@Api(tags = "管理后台 - 物料")
@RestController
@RequestMapping("/plm/item")
@Validated
public class ItemController {

    @Resource
    private ItemService itemService;

    @PostMapping("/create")
    @ApiOperation("创建物料")
    @PreAuthorize("@ss.hasPermission('plm:item:create')")
    public CommonResult<Long> createItem(@Valid @RequestBody ItemCreateReqVO createReqVO) {
        return success(itemService.createItem(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新物料")
    @PreAuthorize("@ss.hasPermission('plm:item:update')")
    public CommonResult<Boolean> updateItem(@Valid @RequestBody ItemUpdateReqVO updateReqVO) {
        itemService.updateItem(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除物料")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('plm:item:delete')")
    public CommonResult<Boolean> deleteItem(@RequestParam("id") Long id) {
        itemService.deleteItem(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得物料")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('plm:item:query')")
    public CommonResult<ItemRespVO> getItem(@RequestParam("id") Long id) {
        ItemDO item = itemService.getItem(id);
        return success(ItemConvert.INSTANCE.convert(item));
    }

    @GetMapping("/list")
    @ApiOperation("获得物料列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('plm:item:query')")
    public CommonResult<List<ItemRespVO>> getItemList(@RequestParam("ids") Collection<Long> ids) {
        List<ItemDO> list = itemService.getItemList(ids);
        return success(ItemConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得物料分页")
    @PreAuthorize("@ss.hasPermission('plm:item:query')")
    public CommonResult<PageResult<ItemRespVO>> getItemPage(@Valid ItemPageReqVO pageVO) {
        PageResult<ItemDO> pageResult = itemService.getItemPage(pageVO);
        return success(ItemConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出物料 Excel")
    @PreAuthorize("@ss.hasPermission('plm:item:export')")
    @OperateLog(type = EXPORT)
    public void exportItemExcel(@Valid ItemExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ItemDO> list = itemService.getItemList(exportReqVO);
        // 导出 Excel
        List<ItemExcelVO> datas = ItemConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "物料.xls", "数据", ItemExcelVO.class, datas);
    }

}
