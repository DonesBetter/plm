package cn.iocoder.yudao.module.document.controller.admin.document.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel("管理后台 - 文档分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DocumentPageReqVO extends PageParam {

    @ApiModelProperty(value = "文档名", example = "李四")
    private String name;

    @ApiModelProperty(value = "文档版本")
    private String version;

    @ApiModelProperty(value = "文档描述", example = "你说的对")
    private String description;

    @ApiModelProperty(value = "文件大小")
    private Long size;

    @ApiModelProperty(value = "路径")
    private String path;

    @ApiModelProperty(value = "链接", example = "https://www.iocoder.cn")
    private String url;

    @ApiModelProperty(value = "状态", example = "2")
    private Integer status;

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
