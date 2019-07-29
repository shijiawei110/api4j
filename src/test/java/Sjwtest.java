import com.sjw.api4j.helper.AnnotationHelper;
import com.sjw.api4j.helper.FileHelper;
import com.thoughtworks.qdox.model.JavaAnnotation;
import com.thoughtworks.qdox.model.JavaClass;
import com.thoughtworks.qdox.model.JavaMethod;

import java.util.Collection;
import java.util.List;

/**
 * Created by shijiawei on 2019/4/1.
 */
public class Sjwtest {

    public static void main(String[] args) {
        String rootPath = FileHelper.getRootPath();
        Collection<JavaClass> classes = FileHelper.getAllClassses(rootPath);
        for (JavaClass javaClass : classes) {
            List<JavaMethod> methods = javaClass.getMethods();
            for (JavaMethod javaMethod : methods) {
                List<JavaAnnotation> anns = javaMethod.getAnnotations();
                if (AnnotationHelper.isApiTag(anns)) {
                    System.out.println(javaMethod + " comment : " + javaMethod.getComment());

                }
            }
        }
    }

}
