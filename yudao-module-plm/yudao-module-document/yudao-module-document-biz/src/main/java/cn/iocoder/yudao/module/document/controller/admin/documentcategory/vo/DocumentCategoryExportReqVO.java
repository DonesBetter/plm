package cn.iocoder.yudao.module.document.controller.admin.documentcategory.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 文档分类 Excel 导出 Request VO")
@Data
public class DocumentCategoryExportReqVO {

    @Schema(description = "父分类ID", example = "21983")
    private Long parentId;

    @Schema(description = "父分类代码")
    private String parentCode;

    @Schema(description = "分类代码")
    private String categoryCode;

    @Schema(description = "分类名称", example = "王五")
    private String name;

    @Schema(description = "描述", example = "你猜")
    private String description;

    @Schema(description = "图标", example = "https://www.iocoder.cn")
    private String picUrl;

    @Schema(description = "排序")
    private String sort;

    @Schema(description = "状态;0-启用，1-停用", example = "2")
    private Byte status;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
