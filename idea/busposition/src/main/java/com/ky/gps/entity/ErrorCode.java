package com.ky.gps.entity;

/**
 * @author Daye
 * 操作返回码
 */
public enum ErrorCode {

    /** 登录账号/密码错误 */
    Login_ERROR("账号/密码错误"),
    /** 查询失败 */
    SELECT_ERROR("查询失败"),
    /** 添加失败 */
    SAVE_ERROR("添加失败"),
    /** 更新失败 */
    UPDATE_ERROR("更新失败"),
    /** 异常错误 */
    SYSTEM_ERROR("操作异常"),
    /** 空值判断 */
    EMPTY_ERROR("存在空值");

    private String errorMessage;

    ErrorCode(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return errorMessage;
    }}
