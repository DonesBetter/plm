package cn.iocoder.yudao.module.item.controller.admin.item.vo;

import lombok.*;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description ="管理后台 - 物料 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ItemRespVO extends ItemBaseVO {

    @Schema(description ="id", required = true)
    private Long id;

    @Schema(description ="创建时间", required = true)
    private LocalDateTime createTime;

}
