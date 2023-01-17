package cn.iocoder.yudao.module.item.controller.admin.item.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
* 物料 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class ItemBaseVO {

    @ApiModelProperty(value = "物料编码", required = true)
    @NotNull(message = "物料编码不能为空")
    private String itemCode;

    @ApiModelProperty(value = "物料版本", required = true)
    @NotNull(message = "物料版本不能为空")
    private String version;

    @ApiModelProperty(value = "物料名称", required = true)
    @NotNull(message = "物料名称不能为空")
    private String name;

    @ApiModelProperty(value = "物料分类ID", required = true)
    @NotNull(message = "物料分类ID不能为空")
    private Long categoryId;

    @ApiModelProperty(value = "物料分类代码", required = true)
    @NotNull(message = "物料分类代码不能为空")
    private String categoryCode;

    @ApiModelProperty(value = "材料")
    private String material;

    @ApiModelProperty(value = "成本")
    private BigDecimal cost;

    @ApiModelProperty(value = "重量")
    private BigDecimal weight;

    @ApiModelProperty(value = "计量单位")
    private String unit;

    @ApiModelProperty(value = "库存")
    private BigDecimal stock;

    @ApiModelProperty(value = "图标")
    private String picUrl;

    @ApiModelProperty(value = "状态;0-启用，1-停用", required = true)
    @NotNull(message = "状态;0-启用，1-停用不能为空")
    private Integer status;

}
