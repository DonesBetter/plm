package cn.iocoder.yudao.module.item.controller.admin.category;

import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.module.item.controller.admin.category.vo.*;
import cn.iocoder.yudao.module.item.convert.category.CategoryConvert;
import cn.iocoder.yudao.module.item.dal.dataobject.category.CategoryDO;
import cn.iocoder.yudao.module.item.service.category.CategoryService;
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
import java.util.Comparator;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

@Tag(name = "管理后台 - 物料分类")
@RestController
@RequestMapping("/item/category")
@Validated
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @PostMapping("/create")
    @Operation(summary ="创建物料分类")
    @PreAuthorize("@ss.hasPermission('plm:category:create')")
    public CommonResult<Long> createCategory(@Valid @RequestBody CategoryCreateReqVO createReqVO) {
        return success(categoryService.createCategory(createReqVO));
    }


    @PutMapping("/update")
    @Operation(summary ="更新物料分类")
    @PreAuthorize("@ss.hasPermission('plm:category:update')")
    public CommonResult<Boolean> updateCategory(@Valid @RequestBody CategoryUpdateReqVO updateReqVO) {
        categoryService.updateCategory(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary ="删除物料分类")
    @PreAuthorize("@ss.hasPermission('plm:category:delete')")
    public CommonResult<Boolean> deleteCategory(@RequestParam("id") Long id) {
        categoryService.deleteCategory(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary ="获得物料分类")
    @PreAuthorize("@ss.hasPermission('plm:category:query')")
    public CommonResult<CategoryRespVO> getCategory(@RequestParam("id") Long id) {
        CategoryDO category = categoryService.getCategory(id);
        return success(CategoryConvert.INSTANCE.convert(category));
    }

    @GetMapping("/list")
    @Operation(summary ="获得物料分类列表")
    @PreAuthorize("@ss.hasPermission('plm:category:query')")
    public CommonResult<List<CategoryRespVO>> getCategoryList(@RequestParam("ids") Collection<Long> ids) {
        List<CategoryDO> list = categoryService.getCategoryList(ids);
        return success(CategoryConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary ="获得物料分类分页")
    @PreAuthorize("@ss.hasPermission('plm:category:query')")
    public CommonResult<PageResult<CategoryRespVO>> getCategoryPage(@Valid CategoryPageReqVO pageVO) {
        PageResult<CategoryDO> pageResult = categoryService.getCategoryPage(pageVO);
        return success(CategoryConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/list-all-simple")
    @Operation(summary = "获取物料分类精简信息列表", description = "只包含被启用的分类，主要用于前端的下拉选项")
    public CommonResult<List<CategorySimpleRespVO>> getSimpleCategory() {
        // 获得部门列表，只要开启状态的
        CategoryListReqVO reqVO = new CategoryListReqVO();
        reqVO.setStatus(CommonStatusEnum.ENABLE.getStatus());
        List<CategoryDO> list = categoryService.getSimpleCategories(reqVO);
        // 排序后，返回给前端
        list.sort(Comparator.comparing(CategoryDO::getSort));
        return success(CategoryConvert.INSTANCE.convertList03(list));
    }


    @GetMapping("/export-excel")
    @Operation(summary ="导出物料分类 Excel")
    @PreAuthorize("@ss.hasPermission('plm:category:export')")
    @OperateLog(type = EXPORT)
    public void exportCategoryExcel(@Valid CategoryExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<CategoryDO> list = categoryService.getCategoryList(exportReqVO);
        // 导出 Excel
        List<CategoryExcelVO> datas = CategoryConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "物料分类.xls", "数据", CategoryExcelVO.class, datas);
    }

}
