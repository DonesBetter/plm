package cn.iocoder.yudao.module.document.controller.admin.documentcategory.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

@ApiModel("管理后台 - 文档分类更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DocumentCategoryUpdateReqVO extends DocumentCategoryBaseVO {

    @ApiModelProperty(value = "id", required = true, example = "15676")
    @NotNull(message = "id不能为空")
    private Long id;

}
