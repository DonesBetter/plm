package cn.iocoder.yudao.plm.item.controller.admin.item.vo;

import lombok.*;
import java.util.*;
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

    @ExcelProperty("物料代码")
    private String itemCode;

    @ExcelProperty("version")
    private String version;

    @ExcelProperty("name")
    private String name;

    @ExcelProperty("材料")
    private String material;

    @ExcelProperty("数量")
    private String count;

    @ExcelProperty("计量单位")
    private String unit;

    @ExcelProperty("图标")
    private String icon;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
