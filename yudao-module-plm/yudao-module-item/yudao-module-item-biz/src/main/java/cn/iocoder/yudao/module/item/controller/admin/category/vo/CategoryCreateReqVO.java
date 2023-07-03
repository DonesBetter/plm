package cn.iocoder.yudao.module.item.controller.admin.category.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;


@Schema(description ="plm - 物料分类创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CategoryCreateReqVO extends CategoryBaseVO {

}
