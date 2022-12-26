package cn.iocoder.yudao.plm.item.controller.admin.item;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ItemBaseVO {
    @ApiModelProperty(value = "物料代码", required = true, example = "haha")
    @NotEmpty(message = "物料代码不能为空")
    private String itemCode;

    @ApiModelProperty(value = "物料版本", required = true, example = "haha")
    @NotEmpty(message = "物料版本不能为空")
    private String version;

    @ApiModelProperty(value = "物料名称", required = true, example = "haha")
    @NotEmpty(message = "物料名称不能为空")
    private String name;

    @ApiModelProperty(value = "物料材质", required = true, example = "haha")
    @NotEmpty(message = "物料材质不能为空")
    private String material;

    @ApiModelProperty(value = "物料数量？", required = true, example = "haha")
    private String count;

    @ApiModelProperty(value = "计量单位", required = true, example = "haha")
    private String unit;

    @ApiModelProperty(value = "物料图标", required = true, example = "haha")
    private String icon;

}
