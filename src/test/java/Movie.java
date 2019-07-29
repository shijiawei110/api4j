import lombok.Data;

import java.math.BigDecimal;

/**
 * @author shijiawei
 * @version Movie.java -> v 1.0
 * @date 2019/7/28
 */
@Data
public class Movie {
    private String strV;
    private Integer intV;
    private Long longV;
    private Boolean booleanV;
    private BigDecimal bigV;
}
