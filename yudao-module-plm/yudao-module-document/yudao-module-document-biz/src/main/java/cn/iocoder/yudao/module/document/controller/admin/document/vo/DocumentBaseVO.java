package cn.iocoder.yudao.module.document.controller.admin.document.vo;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.*;

/**
* 文档 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class DocumentBaseVO {

    @Schema(description ="文档名", required = true, example = "李四")
    @NotNull(message = "文档名不能为空")
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

}
