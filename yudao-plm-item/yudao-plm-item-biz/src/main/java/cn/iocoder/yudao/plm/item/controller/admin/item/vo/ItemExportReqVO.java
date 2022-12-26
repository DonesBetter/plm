package cn.iocoder.yudao.plm.item.controller.admin.item.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel(value = "管理后台 - 物料 Excel 导出 Request VO", description = "参数和 ItemPageReqVO 是一致的")
@Data
public class ItemExportReqVO {

    @ApiModelProperty(value = "物料代码")
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

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
