package cn.iocoder.yudao.module.item.controller.admin.item.vo;

import lombok.*;

import java.time.LocalDateTime;

import io.swagger.annotations.*;

@ApiModel("管理后台 - 物料 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ItemRespVO extends ItemBaseVO {

    @ApiModelProperty(value = "id", required = true)
    private Long id;

    @ApiModelProperty(value = "创建时间", required = true)
    private LocalDateTime createTime;

}
