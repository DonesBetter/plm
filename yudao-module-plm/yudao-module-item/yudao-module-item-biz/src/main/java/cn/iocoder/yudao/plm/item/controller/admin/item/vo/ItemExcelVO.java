package cn.iocoder.yudao.plm.item.controller.admin.item.vo;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 物料 Excel VO
 *
 * @author 洋芋
 */
@Data
public class ItemExcelVO {

    @ExcelProperty("id")
    private Long id;

    @ExcelProperty("物料编码")
    private String itemCode;

    @ExcelProperty("物料版本")
    private String version;

    @ExcelProperty("物料名称")
    private String name;

    @ExcelProperty("物料分类ID")
    private Long categoryId;

    @ExcelProperty("物料分类代码")
    private String categoryCode;

    @ExcelProperty("材料")
    private String material;

    @ExcelProperty("成本")
    private BigDecimal cost;

    @ExcelProperty("重量")
    private BigDecimal weight;

    @ExcelProperty("计量单位")
    private String unit;

    @ExcelProperty("库存")
    private BigDecimal stock;

    @ExcelProperty("图标")
    private String picUrl;

    @ExcelProperty("状态;0-启用，1-停用")
    private Integer status;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
