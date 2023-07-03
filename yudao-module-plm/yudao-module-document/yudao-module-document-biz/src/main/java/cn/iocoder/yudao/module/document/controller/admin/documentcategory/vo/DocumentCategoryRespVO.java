package cn.iocoder.yudao.module.document.controller.admin.documentcategory.vo;

import lombok.*;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description ="管理后台 - 文档分类 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DocumentCategoryRespVO extends DocumentCategoryBaseVO {

    @Schema(description ="id", required = true, example = "15676")
    private Long id;

    @Schema(description ="创建时间")
    private LocalDateTime createTime;

}
