package com.zgy.springboot_biye.config;

import lombok.Data;

/**
 * 封装后台接口信息
 */
@Data
public class Result {
    private String code; // 标识
    private String msg; // 错误信息
    private Object data; // 封装数据

    private static final String SUCCSSS_CODE = "200";
    private static final String SUCCSSS_MSG = "请求成功";
    private static final String ERROR_CODE = "-1";

    public static Result success(){
        Result result = new Result();
        result.setCode(SUCCSSS_CODE);
        result.setMsg(SUCCSSS_MSG);
        return result;
    }
    public static Result success(Object data){
        Result result = success();
        result.setData(data);
        return result;
    }
    public static Result error(String msg){
        Result result = success();
        result.setCode(ERROR_CODE);
        result.setMsg(msg);
        return result;
    }

}
