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

    /**
     * 这是mvc get test1 note.
     */
    @GetMapping("/getTest1")
//    @ApiTag(value = "这是mvc get test1", author = "sjw")
    public String getTest1(String name, Integer id) {
        return null;
    }

    /**
     * 这是mvc get test2 note.
     */
    @GetMapping("getTest2")
    @ApiTag(value = "这是mvc get test2", author = "sjw")
    public List<String> getTest2(@RequestParam(value = "test_id",required = false) Integer id, String name) {
        return null;
    }

    /**
     * 这是mvc get test3 note.
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getTest3")
//    @ApiTag(value = "这是mvc get test3")
    public List<String> getTest3(@RequestParam("ID") Integer id) {
        return null;
    }

    /**
     * 这是mvc get 泛型测试.
     */
    @GetMapping("/getTestF")
//    @ApiTag(value = "这是mvc get getTestF", author = "sjw")
    public BaseResponse<Movie> getTestF(String name) {
        return null;
    }

    /**
     * 这是mvc post test1 note.
     *
     * @param paramDemo
     * @return
     */
    @PostMapping("/postTest1")
//    @ApiTag(value = "这是mvc post test1", author = "sjw")
    public String postTest1(ParamDemo paramDemo) {
        return null;
    }

    /**
     * 这是mvc post test2 note.
     */
    @PostMapping("postTest2")
//    @ApiTag(value = "这是mvc post test2")
    public List<Movie> postTest2(ParamDemo paramDemo) {
        return null;
    }


}
