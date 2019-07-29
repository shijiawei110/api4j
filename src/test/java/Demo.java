import com.sjw.api4j.annotation.ApiTag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.ws.rs.POST;

/**
 * @author shijiawei
 * @version Demo.java -> v 1.0
 * @date 2019/7/27
 */
public class Demo {

    /**
     * demo-1注释
     *
     * @return
     */
    @ApiTag(value = "这是demo api")
    @GetMapping
    public String apiDemo(@RequestParam(value = "str") String strParam) {
        System.out.println("this is api demo");
        return "sjw";
    }

    /**
     * demo-2注释
     *
     * @return
     */
    @ApiTag(value = "这是demo api-2")
    @RequestMapping()
    public Movie apiDemoTwo(String strParam, Integer intParam) {
        System.out.println("this is api demo2");
        return new Movie();
    }

    /**
     * demo-3注释
     *
     * @return
     */
    @ApiTag(value = "这是demo api-2")
    @POST
    public Movie apiDemoThree(String strParam, Integer intParam) {
        System.out.println("this is api demo2");
        return new Movie();
    }
}
