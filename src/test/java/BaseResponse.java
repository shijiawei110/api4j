import lombok.Data;

/**
 * @author shijiawei
 * @version BaseResponse.java -> v 1.0
 * @date 2019/7/30
 */
@Data
public class BaseResponse<T> {
    private T data;
    private boolean success;
    private String errorMsg;
}
