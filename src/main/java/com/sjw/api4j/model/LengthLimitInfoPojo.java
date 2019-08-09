package com.sjw.api4j.model;

import com.sjw.api4j.utils.StringPool;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * @author shijiawei
 * @version LengthLimitInfoPojo.java -> v 1.0
 * @date 2019/8/8
 */
@Data
public class LengthLimitInfoPojo {
    private String limitStr = StringPool.EMPTY;
    private int min = -1;
    private int max = -1;

    public LengthLimitInfoPojo() {

    }

    public LengthLimitInfoPojo(String limitStr, Integer min, Integer max) {
        if (StringUtils.isNotBlank(limitStr)) {
            this.limitStr = limitStr;
        }
        if (null != min) {
            this.min = min;
        }
        if (null != max) {
            this.max = max;
        }
    }
}
