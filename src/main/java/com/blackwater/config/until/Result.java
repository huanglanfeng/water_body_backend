package com.blackwater.config.until;

import lombok.Data;

/**
 * @param <T>
 */
@Data
public class Result<T> {
    //    状态码
    private int code;
    //    信息
    private String msg;
    //    数据
    private T data;
    //    名字
    private String name;
    //    权限
    private String role;

    public Result() {
    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(T data) {
        this.data = data;
    }

    public Result(int code, String msg, T data, String name, String role) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.name = name;
        this.role = role;
    }
}
