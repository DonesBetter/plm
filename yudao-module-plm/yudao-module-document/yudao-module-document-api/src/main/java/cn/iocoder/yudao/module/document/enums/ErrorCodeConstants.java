package cn.iocoder.yudao.module.document.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * PLM 错误码枚举类
 * <p>
 * 文档系统，使用 2-002-000-000 段
 */
public interface ErrorCodeConstants {
    // ========== 文档 2002001000 ==========
    ErrorCode DOCUMENT_NOT_EXISTS = new ErrorCode(2002001001, "文档不存在");

    // ========== 文档分类 2002002000 ==========
    ErrorCode DOCUMENT_CATEGORY_NOT_EXISTS = new ErrorCode(2002002001, "文档分类不存在");
}
