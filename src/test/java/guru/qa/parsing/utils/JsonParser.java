package guru.qa.parsing.utils;

import com.google.gson.Gson;
import guru.qa.parsing.tests.domain.Art;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonParser {
    ClassLoader classLoader = getClass().getClassLoader();

     public void checkJson(String path, String name, int totalCount) throws IOException {
        Gson gson = new Gson();
        try(InputStream is = classLoader.getResourceAsStream(path)){
            String json = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            Art jsonObject = gson.fromJson(json, Art.class);
            assertThat(jsonObject.totalCount).isEqualTo(totalCount);
            assertThat(jsonObject.data.get(0).name).isEqualTo(name);
        }
    }
}
