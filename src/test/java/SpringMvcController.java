import com.sjw.api4j.annotation.ApiTag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author shijiawei
 * @version SpringMvcController.java -> v 1.0
 * @date 2019/7/28
 */
@RestController
@RequestMapping(value = "/mvc-controller")
public class SpringMvcController {

    @GetMapping("/getTest1")
    @ApiTag(value = "这是mvc get test1")
    public String getTest1(String name) {
        return null;
    }

    @GetMapping("getTest2")
    public List<String> getTest2(@RequestParam(value = "ID") Integer id) {
        return null;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getTest3")
    public List<String> getTest3(@RequestParam(value = "ID") Integer id) {
        return null;
    }

    @PostMapping("/postTest1")
    @ApiTag(value = "这是mvc post test1")
    public String postTest1(ParamDemo paramDemo) {
        return null;
    }

    @PostMapping("postTest2")
    @ApiTag(value = "这是mvc post test2")
    public List<Movie> postTest2(ParamDemo paramDemo) {
        return null;
    }


}
