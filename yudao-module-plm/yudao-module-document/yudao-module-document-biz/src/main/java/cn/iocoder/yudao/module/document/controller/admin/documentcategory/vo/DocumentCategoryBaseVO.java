package cn.iocoder.yudao.module.document.controller.admin.documentcategory.vo;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

/**
* 文档分类 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class DocumentCategoryBaseVO {

    @ApiModelProperty(value = "父分类ID", required = true, example = "21983")
    @NotNull(message = "父分类ID不能为空")
    private Long parentId;

    @ApiModelProperty(value = "父分类代码", required = true)
    @NotNull(message = "父分类代码不能为空")
    private String parentCode;

    @ApiModelProperty(value = "分类代码", required = true)
    @NotNull(message = "分类代码不能为空")
    private String categoryCode;

    @ApiModelProperty(value = "分类名称", example = "王五")
    private String name;

    @ApiModelProperty(value = "描述", example = "你猜")
    private String description;

    @ApiModelProperty(value = "图标", example = "https://www.iocoder.cn")
    private String picUrl;

    @ApiModelProperty(value = "排序")
    private String sort;

    @ApiModelProperty(value = "状态;0-启用，1-停用", required = true, example = "2")
    @NotNull(message = "状态;0-启用，1-停用不能为空")
    private Byte status;

}
