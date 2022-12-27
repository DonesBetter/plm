package cn.iocoder.yudao.plm.item.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

public interface ErrorCodeConstants {
    ErrorCode ITEM_NOT_EXISTS = new ErrorCode(1, "物料不存在");
    ErrorCode CATEGORY_NOT_EXISTS = new ErrorCode( 2, "物料分类不存在");
}
