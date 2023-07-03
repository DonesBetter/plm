package cn.iocoder.yudao.module.item.controller.admin.category.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "plm - 物料分类 Excel 导出 Request VO")
@Data
public class CategoryExportReqVO {

    @Schema(description ="父分类ID")
    private Long parentId;

    @Schema(description ="父分类代码")
    private String parentCode;

    @Schema(description ="分类代码")
    private String categoryCode;

    @Schema(description ="分类名称")
    private String name;

    @Schema(description ="描述")
    private String description;

    @Schema(description ="图标")
    private String picUrl;

    @Schema(description ="排序")
    private String sort;

    @Schema(description ="状态;0-启用，1-停用")
    private Integer status;

    @Schema(description ="创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
