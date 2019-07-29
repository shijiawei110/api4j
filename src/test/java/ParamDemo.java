import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author shijiawei
 * @version ParamDemo.java -> v 1.0
 * @date 2019/7/29
 */
@Data
public class ParamDemo {

    @Length(min = 1,max = 1000)
    private Long id;

    @JsonProperty("test_id")
    private Long testId;

    @JSONField(name = "test_age")
    private Integer testAge;

    @JsonProperty("test_name")
    private String testName;

    @NotNull
    private Boolean isBoo;

    @NotBlank
    private String ok;

    @NotEmpty
    private List<String> reqStrs;

    private Movie movie;

    private List<Movie> movies;
}
