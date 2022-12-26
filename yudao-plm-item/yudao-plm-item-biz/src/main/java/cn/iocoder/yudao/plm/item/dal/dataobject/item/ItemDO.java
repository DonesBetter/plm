package cn.iocoder.yudao.plm.item.dal.dataobject.item;

import cn.iocoder.yudao.framework.tenant.core.db.TenantBaseDO;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;

;


@TableName("plm_item")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemDO extends TenantBaseDO implements Serializable, Cloneable {
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