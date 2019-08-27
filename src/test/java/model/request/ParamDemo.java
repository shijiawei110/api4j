package model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import model.response.Book;
import model.response.Movie;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.util.List;

/**
 * @author shijiawei
 * @version model.request.ParamDemo.java -> v 1.0
 * @date 2019/7/29
 */
@Data
public class ParamDemo {
    /**
     * 在这里写字段含义的注释可以在文档中输出
     */
    @NotNull
    private Long id;

    @JsonProperty("test_id")
    @Min(1)
    @Max(5)
    private Long testId;

    /**
     * age
     */
    @JsonProperty("test_age")
    private Integer testAge;

    /**
     * 名称
     */
    @JsonProperty("test_name")
    @Length(min = 1, max = 100)
    private String testName;

    @NotBlank
    private String ok;

    @NotEmpty
    @Size(min = 1, max = 10)
    private List<String> reqStrs;

    /**
     * 书籍实体类
     */
    private Book book;
}
