import lombok.Data;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author shijiawei
 * @version Movie.java -> v 1.0
 * @date 2019/7/28
 */
@Data
public class Book {
    /**
     * 书名
     */
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
