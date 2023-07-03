package cn.iocoder.yudao.module.document.controller.admin.document.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Schema(description ="管理后台 - 物料分类精简信息 Response VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentCategorySimpleRespVO {
    @Schema(description ="物料分类编号", required = true, example = "1024")
    private Long id;

    @Schema(description ="物料分类名称", required = true, example = "芋道")
    private String name;
    
    @Schema(description ="物料分类编码", required = true, example = "yd")
    private String categoryCode;

    @Schema(description ="父物料分类 ID", required = true, example = "1024")
    private Long parentId;

    @Schema(description ="父物料分类 编码", required = true, example = "yd")
    private String parentCode;


}
