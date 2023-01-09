package cn.iocoder.yudao.plm.item.controller.admin.category;

import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.*;

import javax.validation.constraints.*;
import javax.validation.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.plm.item.controller.admin.category.vo.*;
import cn.iocoder.yudao.plm.item.dal.dataobject.category.CategoryDO;
import cn.iocoder.yudao.plm.item.convert.category.CategoryConvert;
import cn.iocoder.yudao.plm.item.service.category.CategoryService;

@Api(tags = "管理后台 - 物料分类")
@RestController
@RequestMapping("/item/category")
@Validated
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @PostMapping("/create")
    @ApiOperation("创建物料分类")
    @PreAuthorize("@ss.hasPermission('item:category:create')")
    public CommonResult<Long> createCategory(@Valid @RequestBody CategoryCreateReqVO createReqVO) {
        return success(categoryService.createCategory(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新物料分类")
    @PreAuthorize("@ss.hasPermission('item:category:update')")
    public CommonResult<Boolean> updateCategory(@Valid @RequestBody CategoryUpdateReqVO updateReqVO) {
        categoryService.updateCategory(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除物料分类")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('item:category:delete')")
    public CommonResult<Boolean> deleteCategory(@RequestParam("id") Long id) {
        categoryService.deleteCategory(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得物料分类")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('item:category:query')")
    public CommonResult<CategoryRespVO> getCategory(@RequestParam("id") Long id) {
        CategoryDO category = categoryService.getCategory(id);
        return success(CategoryConvert.INSTANCE.convert(category));
    }

    @GetMapping("/list")
    @ApiOperation("获得物料分类列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('item:category:query')")
    public CommonResult<List<CategoryRespVO>> getCategoryList(@RequestParam("ids") Collection<Long> ids) {
        List<CategoryDO> list = categoryService.getCategoryList(ids);
        return success(CategoryConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得物料分类分页")
    @PreAuthorize("@ss.hasPermission('item:category:query')")
    public CommonResult<PageResult<CategoryRespVO>> getCategoryPage(@Valid CategoryPageReqVO pageVO) {
        PageResult<CategoryDO> pageResult = categoryService.getCategoryPage(pageVO);
        return success(CategoryConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/list-all-simple")
    @ApiOperation(value = "获取物料分类精简信息列表", notes = "只包含被启用的分类，主要用于前端的下拉选项")
    public CommonResult<List<CategorySimpleRespVO>> getSimpleCategory() {
        // 获得部门列表，只要开启状态的
        CategoryListReqVO reqVO = new CategoryListReqVO();
        reqVO.setStatus(CommonStatusEnum.ENABLE.getStatus());
        List<CategoryDO> list = categoryService.getSimpleCategorys(reqVO);
        // 排序后，返回给前端
        list.sort(Comparator.comparing(CategoryDO::getSort));
        return success(CategoryConvert.INSTANCE.convertList03(list));
    }


    @GetMapping("/export-excel")
    @ApiOperation("导出物料分类 Excel")
    @PreAuthorize("@ss.hasPermission('item:category:export')")
    @OperateLog(type = EXPORT)
    public void exportCategoryExcel(@Valid CategoryExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<CategoryDO> list = categoryService.getCategoryList(exportReqVO);
        // 导出 Excel
        List<CategoryExcelVO> datas = CategoryConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "物料分类.xls", "数据", CategoryExcelVO.class, datas);
    }

}
