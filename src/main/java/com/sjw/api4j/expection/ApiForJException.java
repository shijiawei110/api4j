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
    public static final ApiForJException SYSTEM_ERROR = new ApiForJException(9999, "系统错误");
    public static final ApiForJException BT_TYPE_NOT_EXIST = new ApiForJException(9001, "币种不存在");
    public static final ApiForJException GET_RESPONSE_ERROR = new ApiForJException(1001, "获取api结果异常");
    public static final ApiForJException ORDER_ID_IS_NULL = new ApiForJException(1002, "订单号为空");
    public static final ApiForJException OUT_MAX_ORDER_RETRY = new ApiForJException(1003, "达到交易最大重试次数,停止下单");
    public static final ApiForJException NOT_FIND_CACHE_LIST = new ApiForJException(1004, "寻找到的缓存为空");


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
        this.code = 10000;
        this.msg = msg;
    }


}
