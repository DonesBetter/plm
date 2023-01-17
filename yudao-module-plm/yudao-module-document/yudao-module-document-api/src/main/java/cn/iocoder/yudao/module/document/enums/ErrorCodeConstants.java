package cn.iocoder.yudao.module.document.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * PLM 错误码枚举类
 * <p>
 * 文档系统，使用 2-002-000-000 段
 */
public interface ErrorCodeConstants {
    ErrorCode DOCUMENT_NOT_EXISTS = new ErrorCode(2002000001, "文档不存在");
}
