package cn.iocoder.yudao.module.document.dal.mysql.document;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.document.dal.dataobject.document.DocumentDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.document.controller.admin.document.vo.*;

/**
 * 文档 Mapper
 *
 * @author 洋芋
 */
@Mapper
public interface DocumentMapper extends BaseMapperX<DocumentDO> {

    default PageResult<DocumentDO> selectPage(DocumentPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DocumentDO>()
                .likeIfPresent(DocumentDO::getName, reqVO.getName())
                .eqIfPresent(DocumentDO::getVersion, reqVO.getVersion())
                .eqIfPresent(DocumentDO::getDescription, reqVO.getDescription())
                .eqIfPresent(DocumentDO::getSize, reqVO.getSize())
                .eqIfPresent(DocumentDO::getPath, reqVO.getPath())
                .eqIfPresent(DocumentDO::getUrl, reqVO.getUrl())
                .eqIfPresent(DocumentDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(DocumentDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DocumentDO::getId));
    }

    default List<DocumentDO> selectList(DocumentExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<DocumentDO>()
                .likeIfPresent(DocumentDO::getName, reqVO.getName())
                .eqIfPresent(DocumentDO::getVersion, reqVO.getVersion())
                .eqIfPresent(DocumentDO::getDescription, reqVO.getDescription())
                .eqIfPresent(DocumentDO::getSize, reqVO.getSize())
                .eqIfPresent(DocumentDO::getPath, reqVO.getPath())
                .eqIfPresent(DocumentDO::getUrl, reqVO.getUrl())
                .eqIfPresent(DocumentDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(DocumentDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DocumentDO::getId));
    }

}
