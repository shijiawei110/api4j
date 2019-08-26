package controller;

import com.sjw.base.apidoc.annotation.ApiTag;
import com.sjw.base.apidoc.enums.ProtocolEnum;
import model.request.ParamDemo;
import model.response.BaseResponse;
import model.response.Movie;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * @author shijiawei
 * @version controller.DubboController.java -> v 1.0
 * @date 2019/7/28
 * 这是dubbo controller class的注解
 */
@Path("dubbo-controller")
//@ApiTag(value = "kkkk", protocol = ProtocolEnum.DUBBO,author = "sjw")
public interface DubboController {

    /**
     * this is get Test note.
     *
     * @return
     */
//    @GET
//    @Path("/getTest")
//    Movie getTest(@QueryParam("test_id") Integer testId, String testName);


//    /**
//     * this is get test2 note.
//     * @param id
//     * @return
//     */
//    @GET
//    @Path("getTest2")
//    List<String> getTest2(@QueryParam("test_id") Integer id);


    @POST
    @Path("postTest1")
    BaseResponse<Movie> postTest1(ParamDemo paramDemo);
}
