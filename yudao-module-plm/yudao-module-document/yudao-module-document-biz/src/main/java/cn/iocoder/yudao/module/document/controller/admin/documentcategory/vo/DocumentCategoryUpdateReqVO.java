package cn.iocoder.yudao.module.document.controller.admin.documentcategory.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description ="管理后台 - 文档分类更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DocumentCategoryUpdateReqVO extends DocumentCategoryBaseVO {

    @Schema(description ="id", required = true, example = "15676")
    @NotNull(message = "id不能为空")
    private Long id;

}
