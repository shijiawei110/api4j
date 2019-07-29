package com.sjw.api4j.expection;

import java.text.MessageFormat;

/**
 * @author shijw
 * @version ApiForJException.java, v 0.1 2018-10-07 20:05 shijw
 */
public class ApiForJException extends RuntimeException {
    /**
     * 参数异常
     */
    public static final ApiForJException SYSTEM_ERROR = new ApiForJException(99999, "系统错误");


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
    private ApiForJException(int code, String msgFormat, Object... args) {
        super(MessageFormat.format(msgFormat, args));
        this.code = code;
        this.msg = MessageFormat.format(msgFormat, args);
    }

    public ApiForJException(String msg) {
        this.code = 999999;
        this.msg = msg;
    }


}
