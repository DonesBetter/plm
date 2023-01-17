package cn.iocoder.yudao.module.item.controller.admin.category.vo;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 物料分类 Excel VO
 *
 * @author 洋芋
 */
@Data
public class CategoryExcelVO {

    @ExcelProperty("id")
    private Long id;

    @ExcelProperty("父分类ID")
    private Long parentId;

    @ExcelProperty("父分类代码")
    private String parentCode;

    @ExcelProperty("分类代码")
    private String categoryCode;

    @ExcelProperty("分类名称")
    private String name;

    @ExcelProperty("描述")
    private String description;

    @ExcelProperty("图标")
    private String picUrl;

    @ExcelProperty("排序")
    private String sort;

    @ExcelProperty("状态;0-启用，1-停用")
    private Integer status;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
