package cn.iocoder.yudao.module.document.dal.mysql.documentcategory;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.document.dal.dataobject.documentcategory.DocumentCategoryDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.document.controller.admin.documentcategory.vo.*;

/**
 * 文档分类 Mapper
 *
 * @author 洋芋
 */
@Mapper
public interface DocumentCategoryMapper extends BaseMapperX<DocumentCategoryDO> {

    default PageResult<DocumentCategoryDO> selectPage(DocumentCategoryPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DocumentCategoryDO>()
                .eqIfPresent(DocumentCategoryDO::getParentId, reqVO.getParentId())
                .eqIfPresent(DocumentCategoryDO::getParentCode, reqVO.getParentCode())
                .eqIfPresent(DocumentCategoryDO::getCategoryCode, reqVO.getCategoryCode())
                .likeIfPresent(DocumentCategoryDO::getName, reqVO.getName())
                .eqIfPresent(DocumentCategoryDO::getDescription, reqVO.getDescription())
                .eqIfPresent(DocumentCategoryDO::getPicUrl, reqVO.getPicUrl())
                .eqIfPresent(DocumentCategoryDO::getSort, reqVO.getSort())
                .eqIfPresent(DocumentCategoryDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(DocumentCategoryDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DocumentCategoryDO::getId));
    }

    default List<DocumentCategoryDO> selectList(DocumentCategoryExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<DocumentCategoryDO>()
                .eqIfPresent(DocumentCategoryDO::getParentId, reqVO.getParentId())
                .eqIfPresent(DocumentCategoryDO::getParentCode, reqVO.getParentCode())
                .eqIfPresent(DocumentCategoryDO::getCategoryCode, reqVO.getCategoryCode())
                .likeIfPresent(DocumentCategoryDO::getName, reqVO.getName())
                .eqIfPresent(DocumentCategoryDO::getDescription, reqVO.getDescription())
                .eqIfPresent(DocumentCategoryDO::getPicUrl, reqVO.getPicUrl())
                .eqIfPresent(DocumentCategoryDO::getSort, reqVO.getSort())
                .eqIfPresent(DocumentCategoryDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(DocumentCategoryDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(DocumentCategoryDO::getId));
    }

}
