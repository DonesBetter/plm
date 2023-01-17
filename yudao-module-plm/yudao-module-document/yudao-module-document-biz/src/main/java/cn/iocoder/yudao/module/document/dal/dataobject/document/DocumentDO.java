package cn.iocoder.yudao.module.document.dal.dataobject.document;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 文档 DO
 *
 * @author 洋芋
 */
@TableName("plm_document")
@KeySequence("plm_document_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DocumentDO extends BaseDO {

    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 文档名
     */
    private String name;
    /**
     * 文档版本
     */
    private String version;
    /**
     * 文档描述
     */
    private String description;
    /**
     * 文件大小
     */
    private Long size;
    /**
     * 路径
     */
    private String path;
    /**
     * 链接
     */
    private String url;
    /**
     * 状态
     */
    private Integer status;

}
