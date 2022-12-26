package cn.iocoder.yudao.plm.item.dal.dataobject.item;

import cn.iocoder.yudao.framework.tenant.core.db.TenantBaseDO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 物料表;
 *
 * @author : http://www.chiner.pro
 * @date : 2022-12-26
 */
@ApiModel(value = "物料表", description = "")
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
    @ApiModelProperty(name = "id", notes = "")
    @TableId
    private Long id;
    /**
     * 物料代码
     */
    @ApiModelProperty(name = "物料代码", notes = "")
    private String itemCode;
    /**
     * version
     */
    @ApiModelProperty(name = "version", notes = "")
    private String version;
    /**
     * name
     */
    @ApiModelProperty(name = "name", notes = "")
    private String name;
    /**
     * 材料
     */
    @ApiModelProperty(name = "材料", notes = "")
    private String material;
    /**
     * 数量
     */
    @ApiModelProperty(name = "数量", notes = "")
    private String count;
    /**
     * 计量单位
     */
    @ApiModelProperty(name = "计量单位", notes = "")
    private String unit;
    /**
     * 图标
     */
    @ApiModelProperty(name = "图标", notes = "")
    private String icon;
    /**
     * 创建人
     */
    @ApiModelProperty(name = "创建人", notes = "")
    private String creator;
    /**
     * 创建时间
     */
    @ApiModelProperty(name = "创建时间", notes = "")
    private Date createTime;
    /**
     * 更新人
     */
    @ApiModelProperty(name = "更新人", notes = "")
    private String updater;
    /**
     * 更新时间
     */
    @ApiModelProperty(name = "更新时间", notes = "")
    private Date updateTime;
    /**
     * 是否删除
     */
    @ApiModelProperty(name = "是否删除", notes = "")
    private Integer deleted;


}