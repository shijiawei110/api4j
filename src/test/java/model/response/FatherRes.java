package model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author shijiawei
 * @version model.response.FatherRes.java -> v 1.0
 * @date 2019/7/31
 */
@Data
public class FatherRes {
    /**
     * father baseValue
     */
    @JsonProperty("base_value")
    private String baseValue;

    /**
     * father baseId
     */
    private Integer baseId;
}
