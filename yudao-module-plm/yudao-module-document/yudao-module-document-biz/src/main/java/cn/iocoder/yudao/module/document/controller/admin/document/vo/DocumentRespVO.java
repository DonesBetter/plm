package cn.iocoder.yudao.module.document.controller.admin.document.vo;

import lombok.*;
import java.time.LocalDateTime;
import io.swagger.annotations.*;

@ApiModel("管理后台 - 文档 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DocumentRespVO extends DocumentBaseVO {

    @ApiModelProperty(value = "id", required = true, example = "21201")
    private Long id;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

}
