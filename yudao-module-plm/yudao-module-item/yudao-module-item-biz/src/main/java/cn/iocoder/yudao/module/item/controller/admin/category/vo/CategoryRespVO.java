package cn.iocoder.yudao.module.item.controller.admin.category.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

@Schema(description ="管理后台 - 物料分类 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CategoryRespVO extends CategoryBaseVO {

    @Schema(description ="id", required = true)
    private Long id;

    @Schema(description ="创建时间")
    private LocalDateTime createTime;

}
