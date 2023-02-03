package cn.iocoder.yudao.module.document.controller.admin.documentcategory;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.module.document.controller.admin.documentcategory.vo.DocumentCategoryCreateReqVO;
import cn.iocoder.yudao.module.document.controller.admin.documentcategory.vo.DocumentCategoryRespVO;
import cn.iocoder.yudao.module.document.controller.admin.documentcategory.vo.DocumentCategoryUpdateReqVO;
import cn.iocoder.yudao.module.document.service.documentcategory.DocumentCategoryService;
import cn.iocoder.yudao.module.document.controller.admin.documentcategory.vo.*;
import cn.iocoder.yudao.module.document.convert.documentcategory.DocumentCategoryConvert;
import cn.iocoder.yudao.module.document.dal.dataobject.documentcategory.DocumentCategoryDO;
import cn.iocoder.yudao.module.document.service.documentcategory.DocumentCategoryService;
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

@Api(tags = "管理后台 - 文档分类")
@RestController
@RequestMapping("/plm/document-category")
@Validated
public class DocumentCategoryController {

    @Resource
    private DocumentCategoryService documentCategoryService;

    @PostMapping("/create")
    @ApiOperation("创建文档分类")
    @PreAuthorize("@ss.hasPermission('plm:document-category:create')")
    public CommonResult<Long> createDocumentCategory(@Valid @RequestBody DocumentCategoryCreateReqVO createReqVO) {
        return success(documentCategoryService.createDocumentCategory(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新文档分类")
    @PreAuthorize("@ss.hasPermission('plm:document-category:update')")
    public CommonResult<Boolean> updateDocumentCategory(@Valid @RequestBody DocumentCategoryUpdateReqVO updateReqVO) {
        documentCategoryService.updateDocumentCategory(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除文档分类")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('plm:document-category:delete')")
    public CommonResult<Boolean> deleteDocumentCategory(@RequestParam("id") Long id) {
        documentCategoryService.deleteDocumentCategory(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得文档分类")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('plm:document-category:query')")
    public CommonResult<DocumentCategoryRespVO> getDocumentCategory(@RequestParam("id") Long id) {
        DocumentCategoryDO documentCategory = documentCategoryService.getDocumentCategory(id);
        return success(DocumentCategoryConvert.INSTANCE.convert(documentCategory));
    }

    @GetMapping("/list")
    @ApiOperation("获得文档分类列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('plm:document-category:query')")
    public CommonResult<List<DocumentCategoryRespVO>> getDocumentCategoryList(@RequestParam("ids") Collection<Long> ids) {
        List<DocumentCategoryDO> list = documentCategoryService.getDocumentCategoryList(ids);
        return success(DocumentCategoryConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得文档分类分页")
    @PreAuthorize("@ss.hasPermission('plm:document-category:query')")
    public CommonResult<PageResult<DocumentCategoryRespVO>> getDocumentCategoryPage(@Valid DocumentCategoryPageReqVO pageVO) {
        PageResult<DocumentCategoryDO> pageResult = documentCategoryService.getDocumentCategoryPage(pageVO);
        return success(DocumentCategoryConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出文档分类 Excel")
    @PreAuthorize("@ss.hasPermission('plm:document-category:export')")
    @OperateLog(type = EXPORT)
    public void exportDocumentCategoryExcel(@Valid DocumentCategoryExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<DocumentCategoryDO> list = documentCategoryService.getDocumentCategoryList(exportReqVO);
        // 导出 Excel
        List<DocumentCategoryExcelVO> datas = DocumentCategoryConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "文档分类.xls", "数据", DocumentCategoryExcelVO.class, datas);
    }

}
