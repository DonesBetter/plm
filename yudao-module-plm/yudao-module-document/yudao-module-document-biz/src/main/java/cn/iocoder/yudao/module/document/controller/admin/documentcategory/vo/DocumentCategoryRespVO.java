package cn.iocoder.yudao.module.document.controller.admin.documentcategory.vo;

import lombok.*;
import java.time.LocalDateTime;
import io.swagger.annotations.*;

@ApiModel("管理后台 - 文档分类 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DocumentCategoryRespVO extends DocumentCategoryBaseVO {

    @ApiModelProperty(value = "id", required = true, example = "15676")
    private Long id;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

}
