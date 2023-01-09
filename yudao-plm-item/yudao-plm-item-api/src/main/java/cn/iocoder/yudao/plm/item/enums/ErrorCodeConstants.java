package cn.iocoder.yudao.plm.item.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;
/**
 * PLM 错误码枚举类
 *
 * 物料系统，使用 2-001-000-000 段
 */
public interface ErrorCodeConstants {
    // ========== 物料 2001001000 ==========
    ErrorCode ITEM_NOT_EXISTS = new ErrorCode(2001001001, "物料不存在");
    ErrorCode ITEM_NAME_EXISTS = new ErrorCode( 2001001002, "物料名称已存在");

    // ========== 物料分类 2001002000 ==========
    ErrorCode CATEGORY_NAME_DUPLICATE = new ErrorCode(2001002000, "已经存在该名字的物料分类");
    ErrorCode CATEGORY_PARENT_NOT_EXITS = new ErrorCode(2001002001,"父级物料分类不存在");
    ErrorCode CATEGORY_NOT_FOUND = new ErrorCode(2001002002, "当前物料分类不存在");
    ErrorCode CATEGORY_EXITS_CHILDREN = new ErrorCode(2001002003, "存在子物料分类，无法删除");
    ErrorCode CATEGORY_PARENT_ERROR = new ErrorCode(2001002004, "不能设置自己为父物料分类");
    ErrorCode CATEGORY_EXISTS_USER = new ErrorCode(2001002005, "物料分类中存在员工，无法删除");
    ErrorCode CATEGORY_NOT_ENABLE = new ErrorCode(2001002006, "物料分类不处于开启状态，不允许选择");
    ErrorCode CATEGORY_PARENT_IS_CHILD = new ErrorCode(2001002007, "不能设置自己的子物料分类为父物料分类");


}
