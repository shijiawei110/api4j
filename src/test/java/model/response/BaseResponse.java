package model.response;

import lombok.Data;

/**
 * @author shijiawei
 * @version model.response.BaseResponse.java -> v 1.0
 * @date 2019/7/30
 */
@Data
public class BaseResponse<T> {
    /**
     * 真正返回数据
     */
    private T data;
    /**
     * 是否调用成功表示
     */
    private boolean success;
    /**
     * 调用失败错误信息
     */
    private String errorMsg;
}
