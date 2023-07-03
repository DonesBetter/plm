package cn.iocoder.yudao.module.item.controller.admin.item.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description ="管理后台 - 物料创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ItemCreateReqVO extends ItemBaseVO {

}
