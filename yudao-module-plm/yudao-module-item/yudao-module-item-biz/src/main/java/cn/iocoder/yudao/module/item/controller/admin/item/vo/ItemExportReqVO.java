package cn.iocoder.yudao.module.item.controller.admin.item.vo;

import lombok.*;

import java.math.BigDecimal;
import java.util.*;

import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 物料 Excel 导出 Request VO")
@Data
public class ItemExportReqVO {

    @Schema(description = "物料编码")
    private String itemCode;

    @Schema(description = "物料版本")
    private String version;

    @Schema(description = "物料名称")
    private String name;

    @Schema(description = "物料分类ID")
    private Long categoryId;

    @Schema(description = "物料分类代码")
    private String categoryCode;

    @Schema(description = "材料")
    private String material;

    @Schema(description = "成本")
    private BigDecimal cost;

    @Schema(description = "重量")
    private BigDecimal weight;

    @Schema(description = "计量单位")
    private String unit;

    @Schema(description = "库存")
    private BigDecimal stock;

    @Schema(description = "图标")
    private String picUrl;

    @Schema(description = "状态;0-启用，1-停用")
    private Boolean status;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
