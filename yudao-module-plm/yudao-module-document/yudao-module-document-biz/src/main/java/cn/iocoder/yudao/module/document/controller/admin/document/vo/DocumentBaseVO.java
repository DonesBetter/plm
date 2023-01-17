package cn.iocoder.yudao.module.document.controller.admin.document.vo;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

/**
* 文档 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class DocumentBaseVO {

    @ApiModelProperty(value = "文档名", required = true, example = "李四")
    @NotNull(message = "文档名不能为空")
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

}
