package cn.iocoder.yudao.plm.item.dal.dataobject.item;

import lombok.*;
import java.util.*;
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
     * 物料代码
     */
    private String itemCode;
    /**
     * version
     */
    private String version;
    /**
     * name
     */
    private String name;
    /**
     * 材料
     */
    private String material;
    /**
     * 数量
     */
    private String count;
    /**
     * 计量单位
     */
    private String unit;
    /**
     * 图标
     */
    private String icon;

}
