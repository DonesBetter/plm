package cn.iocoder.yudao.plm.item.controller.admin.item.vo;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

/**
* 物料 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class ItemBaseVO {

    @ApiModelProperty(value = "物料代码", required = true)
    @NotNull(message = "物料代码不能为空")
    private String itemCode;

    @ApiModelProperty(value = "version")
    private String version;

    @ApiModelProperty(value = "name")
    private String name;

    @ApiModelProperty(value = "材料")
    private String material;

    @ApiModelProperty(value = "数量")
    private String count;

    @ApiModelProperty(value = "计量单位")
    private String unit;

    @ApiModelProperty(value = "图标")
    private String icon;

}
