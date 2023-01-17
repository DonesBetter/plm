package cn.iocoder.yudao.module.document.controller.admin.document;

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

import cn.iocoder.yudao.module.document.controller.admin.document.vo.*;
import cn.iocoder.yudao.module.document.dal.dataobject.document.DocumentDO;
import cn.iocoder.yudao.module.document.convert.document.DocumentConvert;
import cn.iocoder.yudao.module.document.service.document.DocumentService;

@Api(tags = "管理后台 - 文档")
@RestController
@RequestMapping("/plm/document")
@Validated
public class DocumentController {

    @Resource
    private DocumentService documentService;

    @PostMapping("/create")
    @ApiOperation("创建文档")
    @PreAuthorize("@ss.hasPermission('plm:document:create')")
    public CommonResult<Long> createDocument(@Valid @RequestBody DocumentCreateReqVO createReqVO) {
        return success(documentService.createDocument(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新文档")
    @PreAuthorize("@ss.hasPermission('plm:document:update')")
    public CommonResult<Boolean> updateDocument(@Valid @RequestBody DocumentUpdateReqVO updateReqVO) {
        documentService.updateDocument(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除文档")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('plm:document:delete')")
    public CommonResult<Boolean> deleteDocument(@RequestParam("id") Long id) {
        documentService.deleteDocument(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得文档")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('plm:document:query')")
    public CommonResult<DocumentRespVO> getDocument(@RequestParam("id") Long id) {
        DocumentDO document = documentService.getDocument(id);
        return success(DocumentConvert.INSTANCE.convert(document));
    }

    @GetMapping("/list")
    @ApiOperation("获得文档列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('plm:document:query')")
    public CommonResult<List<DocumentRespVO>> getDocumentList(@RequestParam("ids") Collection<Long> ids) {
        List<DocumentDO> list = documentService.getDocumentList(ids);
        return success(DocumentConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得文档分页")
    @PreAuthorize("@ss.hasPermission('plm:document:query')")
    public CommonResult<PageResult<DocumentRespVO>> getDocumentPage(@Valid DocumentPageReqVO pageVO) {
        PageResult<DocumentDO> pageResult = documentService.getDocumentPage(pageVO);
        return success(DocumentConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出文档 Excel")
    @PreAuthorize("@ss.hasPermission('plm:document:export')")
    @OperateLog(type = EXPORT)
    public void exportDocumentExcel(@Valid DocumentExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<DocumentDO> list = documentService.getDocumentList(exportReqVO);
        // 导出 Excel
        List<DocumentExcelVO> datas = DocumentConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "文档.xls", "数据", DocumentExcelVO.class, datas);
    }

}
