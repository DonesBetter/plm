package cn.iocoder.yudao.module.document.controller.admin.document.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description ="管理后台 - 物料分类列表 Request VO")
@Data
public class DocumentCategoryListReqVO {
    @Schema(description ="物料分类名称", example = "芋道")
    private String name;

    @Schema(description ="展示状态", example = "1")
    private Integer status;
}
