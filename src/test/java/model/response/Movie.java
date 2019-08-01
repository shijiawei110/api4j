package model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.List;

/**
 * @author shijiawei
 * @version model.response.Movie.java -> v 1.0
 * @date 2019/7/28
 */
@Data
public class Movie extends FatherRes implements Serializable{

    private static final long serialVersionUID = -1L;

    /**
     * 出参str注释
     */
    @JsonProperty("str_v")
    private String strV;
    /**
     * 出参int注释
     */
    @JsonProperty("int_v")
    private Integer intV;
    /**
     * 出参long注释
     */
    private Long longV;
    /**
     * 出参boolean注释
     */
    private Boolean booleanV;
    /**
     * 出参book注释
     */
    private Book book;
    /**
     * 出参list注释
     */
    private List<String> strList;
    /**
     * 出参book-list注释
     */
    private List<Book> bookList;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this,
                ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
