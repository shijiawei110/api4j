import lombok.Data;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * @author shijiawei
 * @version Movie.java -> v 1.0
 * @date 2019/7/28
 */
@Data
public class Movie extends FatherRes {
    /**
     * 出参str注释
     */
    private String strV;
    /**
     * 出参int注释
     */
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
