import com.github.javafaker.Faker;
import com.sjw.api4j.enums.HttpTypeEnum;
import com.sjw.api4j.helper.FileHelper;
import com.thoughtworks.qdox.model.JavaAnnotation;
import com.thoughtworks.qdox.model.JavaClass;

import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by shijiawei on 2019/4/1.
 */
public class Sjwtest {
    private static final String API_TAG_NAME = "com.sjw.api4j.annotation.ApiTag";

    public static void main(String[] args) {
//        Faker faker = new Faker(new Locale("zh-CN"));
        Faker faker = new Faker();
        System.out.println(faker.name().name());
        System.out.println(faker.number().numberBetween(0,0));

    }


}
