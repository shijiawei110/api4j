package com.sjw.base.apidoc.expection;

import java.text.MessageFormat;

/**
 * @author shijw
 * @version ApiForjException.java, v 0.1 2018-10-07 20:05 shijw
 */
public class ApiForjException extends RuntimeException {
    /**
     * 参数异常
     */
    public static final ApiForjException METHOD_CUSTOM_ERROR = new ApiForjException(10001, "指定方法名异常");
    public static final ApiForjException CUSTOM_SCAN_PATH_ERROR = new ApiForjException(10002, "自定义扫描路径错误");
    public static final ApiForjException GET_FM_TEMPLATE_IO_ERROR = new ApiForjException(10003, "获取模板io异常");


    /**
     * 异常信息
     */
    protected String msg;

    /**
     * 具体异常码
     */
    protected int code;

    /**
     * 异常构造器
     *
     * @param code      代码
     * @param msgFormat 消息模板,内部会用MessageFormat拼接，模板类似：userid={0},message={1},date{2}
     * @param args      具体参数的值
     */
    private ApiForjException(int code, String msgFormat, Object... args) {
        super(MessageFormat.format(msgFormat, args));
        this.code = code;
        this.msg = MessageFormat.format(msgFormat, args);
    }

    public ApiForjException(String msg) {
        this.code = 999999;
        this.msg = msg;
    }


}
