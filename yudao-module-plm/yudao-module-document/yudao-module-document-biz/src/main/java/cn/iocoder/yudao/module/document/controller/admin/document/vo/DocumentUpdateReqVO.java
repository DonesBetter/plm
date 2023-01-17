package cn.iocoder.yudao.module.document.controller.admin.document.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

@ApiModel("管理后台 - 文档更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DocumentUpdateReqVO extends DocumentBaseVO {

    @ApiModelProperty(value = "id", required = true, example = "21201")
    @NotNull(message = "id不能为空")
    private Long id;

}
