package cn.iocoder.yudao.plm.item.controller.admin.item.vo;

import lombok.*;

import java.math.BigDecimal;
import java.util.*;
import io.swagger.annotations.*;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel(value = "管理后台 - 物料 Excel 导出 Request VO", description = "参数和 ItemPageReqVO 是一致的")
@Data
public class ItemExportReqVO {

    @ApiModelProperty(value = "物料编码")
    private String itemCode;

    @ApiModelProperty(value = "物料版本")
    private String version;

    @ApiModelProperty(value = "物料名称")
    private String name;

    @ApiModelProperty(value = "物料分类ID")
    private Long categoryId;

    @ApiModelProperty(value = "物料分类代码")
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

    @ApiModelProperty(value = "状态;0-启用，1-停用")
    private Boolean status;

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
