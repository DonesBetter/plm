package cn.iocoder.yudao.module.item.dal.dataobject.category;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 物料分类 DO
 *
 * @author 洋芋
 */
@TableName("plm_category")
@KeySequence("plm_category_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 父分类ID
     */
    private Long parentId;
    /**
     * 父分类代码
     */
    private String parentCode;
    /**
     * 分类代码
     */
    private String categoryCode;
    /**
     * 分类名称
     */
    private String name;
    /**
     * 描述
     */
    private String description;
    /**
     * 图标
     */
    private String picUrl;
    /**
     * 排序
     */
    private String sort;
    /**
     * 状态;0-启用，1-停用
     */
    private Integer status;

}
