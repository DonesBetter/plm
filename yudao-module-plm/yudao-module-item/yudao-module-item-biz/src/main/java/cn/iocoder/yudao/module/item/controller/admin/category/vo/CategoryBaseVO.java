package cn.iocoder.yudao.module.item.controller.admin.category.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
* 物料分类 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class CategoryBaseVO {

    @Schema(description ="父分类ID", required = true)
    private Long parentId;

    @Schema(description ="父分类代码")
    private String parentCode;

    @Schema(description ="分类代码", required = true)
    @NotNull(message = "分类代码不能为空")
    private String categoryCode;

    @Schema(description ="分类名称")
    private String name;

    @Schema(description ="描述")
    private String description;

    @Schema(description ="图标")
    private String picUrl;

    @Schema(description ="排序")
    private String sort;

    @Schema(description ="状态;0-启用，1-停用", required = true)
    @NotNull(message = "状态;0-启用，1-停用不能为空")
    private Integer status;

}
