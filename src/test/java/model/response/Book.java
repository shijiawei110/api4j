package model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @author shijiawei
 * @version model.response.Movie.java -> v 1.0
 * @date 2019/7/28
 */
@Data
public class Book implements Serializable{

    private static final long serialVersionUID = -1L;

    /**
     * 书名
     */
    @JsonProperty("book_name")
    private String bookName;
    /**
     * 书编号
     */
    private Integer bookId;


    @Override
    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this,
                ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
