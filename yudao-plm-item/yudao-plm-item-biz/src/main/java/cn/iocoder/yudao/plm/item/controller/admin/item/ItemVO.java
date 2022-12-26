package cn.iocoder.yudao.plm.item.controller.admin.item;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@ApiModel("plm - 物料 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class ItemVO extends ItemBaseVO {
    @ApiModelProperty(value = "部门编号", required = true, example = "1024")
    private Long id;

    @ApiModelProperty(value = "状态", required = true, example = "1", notes = "参见 CommonStatusEnum 枚举类")
    private Integer status;
}
