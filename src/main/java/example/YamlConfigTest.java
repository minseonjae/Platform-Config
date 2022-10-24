package example;

import kr.codingtree.platformconfig.YamlConfig;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class YamlConfigTest {

    public static void main(String[] args) {
        YamlConfig config = new YamlConfig();
        File file = new File("src/main/java/example", "config.yml");
        config.load(file);

        config.set("a", "a");
        config.set("b", 1);
        config.set("c", true);
        config.set("list", Arrays.asList("a", "b", "c"));

        HashMap<String, String> map = new LinkedHashMap<String, String>();
        map.put("a", "a");
        map.put("b", "b");
        map.put("c", "c");

        config.set("map", map);

        config.save(file);
    }

}
