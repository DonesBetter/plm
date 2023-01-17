package cn.iocoder.yudao.module.document.controller.admin.document.vo;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 文档 Excel VO
 *
 * @author 洋芋
 */
@Data
public class DocumentExcelVO {

    @ExcelProperty("id")
    private Long id;

    @ExcelProperty("文档名")
    private String name;

    @ExcelProperty("文档版本")
    private String version;

    @ExcelProperty("文档描述")
    private String description;

    @ExcelProperty("文件大小")
    private Long size;

    @ExcelProperty("路径")
    private String path;

    @ExcelProperty("链接")
    private String url;

    @ExcelProperty("状态")
    private Integer status;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
