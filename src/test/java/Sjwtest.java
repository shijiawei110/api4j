import com.alibaba.fastjson.JSONArray;

/**
 * Created by shijiawei on 2019/4/1.
 */
public class Sjwtest {
    private static final String API_TAG_NAME = "com.sjw.api4j.annotation.ApiTag";

    public static void main(String[] args) {
//        Faker faker = new Faker(new Locale("zh-CN"));
        model.response.Book book = new model.response.Book();
        book.setBookId(1);
        book.setBookName("aaa");
        model.response.Book book2 = new model.response.Book();
        book2.setBookId(2);
        book2.setBookName("bbb");
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(book);
        jsonArray.add(book2);
        System.out.println(jsonArray.toJSONString());

    }


}
