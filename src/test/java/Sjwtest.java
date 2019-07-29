import com.sjw.api4j.helper.FileHelper;
import com.thoughtworks.qdox.model.JavaAnnotation;
import com.thoughtworks.qdox.model.JavaClass;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by shijiawei on 2019/4/1.
 */
public class Sjwtest {
    private static final String API_TAG_NAME = "com.sjw.api4j.annotation.ApiTag";

    public static void main(String[] args) {

//        String rootPath = FileHelper.getRootPath();
//        Collection<JavaClass> classes = FileHelper.getAllClassses(rootPath);
//        for (JavaClass javaClass : classes) {
//            List<JavaAnnotation> annos = javaClass.getAnnotations();
//            annos.stream().forEach(p -> {
//                if (API_TAG_NAME.equalsIgnoreCase(p.getType().toString())) {
//                    Map<String, Object> map = p.getNamedParameter();
//                    map.forEach((k, v) -> {
//                        System.out.println(k + " = " + v);
//                    });
//                }
//            });
//
//        }
    }


}
