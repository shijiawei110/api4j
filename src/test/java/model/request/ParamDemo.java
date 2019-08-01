package model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import model.response.Movie;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author shijiawei
 * @version model.request.ParamDemo.java -> v 1.0
 * @date 2019/7/29
 */
@Data
public class ParamDemo {

    @Length(min = 1,max = 1000)
    @NotNull
    private Long id;

    @JsonProperty("test_id")
    private Long testId;

    @JsonProperty("test_age")
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
