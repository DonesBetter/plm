package cn.iocoder.yudao.plm.item.dal.dataobject.item;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 物料 DO
 *
 * @author 洋芋
 */
@TableName("plm_item")
@KeySequence("plm_item_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 物料编码
     */
    private String itemCode;
    /**
     * 物料版本
     */
    private String version;
    /**
     * 物料名称
     */
    private String name;
    /**
     * 物料分类ID
     */
    private Long categoryId;
    /**
     * 物料分类代码
     */
    private String categoryCode;
    /**
     * 材料
     */
    private String material;
    /**
     * 成本
     */
    private BigDecimal cost;
    /**
     * 重量
     */
    private BigDecimal weight;
    /**
     * 计量单位
     */
    private String unit;
    /**
     * 库存
     */
    private BigDecimal stock;
    /**
     * 图标
     */
    private String picUrl;
    /**
     * 状态;0-启用，1-停用
     */
    private Integer status;

}
