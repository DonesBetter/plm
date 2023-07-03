package cn.iocoder.yudao.module.document.controller.admin.document.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description  = "管理后台 - 文档 Excel 导出 Request VO")
@Data
public class DocumentExportReqVO {

    @Schema(description ="文档名", example = "李四")
    private String name;

    @Schema(description ="文档版本")
    private String version;

    @Schema(description ="文档描述", example = "你说的对")
    private String description;

    @Schema(description ="文件大小")
    private Long size;

    @Schema(description ="路径")
    private String path;

    @Schema(description ="链接", example = "https://www.iocoder.cn")
    private String url;

    @Schema(description ="状态", example = "2")
    private Integer status;

    @Schema(description ="创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
