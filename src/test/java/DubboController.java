import com.sjw.api4j.annotation.ApiTag;
import org.springframework.web.bind.annotation.RequestParam;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.List;

/**
 * @author shijiawei
 * @version DubboController.java -> v 1.0
 * @date 2019/7/28
 */
@Path("dubbo-controller")
@ApiTag(value = "kkkk")
public interface DubboController {

    /**
     * this is get Test note.
     * @param id
     * @param name
     * @return
     */
    @GET
    @Path("/getTest")
    List<Movie> getTest(Integer id, String name);

    @GET
    @Path("getTest2")
    List<String> getTest2(@RequestParam(value = "ID") Integer id);

    @POST
    @Path("postTest1")
    List<Movie> postTest1(ParamDemo paramDemo);
}
