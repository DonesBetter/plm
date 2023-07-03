package cn.iocoder.yudao.module.item.controller.admin.category.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

@Schema(description ="plm - 物料分类更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CategoryUpdateReqVO extends CategoryBaseVO {

    @Schema(description ="id", required = true)
    @NotNull(message = "id不能为空")
    private Long id;

}
