package example;

import kr.codingtree.platformconfig.YamlConfig;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class YamlConfigDefaultTest {

    public static void main(String[] args) {
        YamlConfig config = new YamlConfig();
        File file = new File("src/main/java/example", "default-config.yml");

        config.addDefault("a", "a");
        config.addDefault("b", 1);
        config.addDefault("c", true);
        config.addDefault("list", Arrays.asList("a", "b", "c"));

        HashMap<String, String> map = new LinkedHashMap<String, String>();
        map.put("a", "a");
        map.put("b", "b");
        map.put("c", "c");

        config.addDefault("map", map);

        config.load(file);

        config.set("a", "b");
        config.set("b", 2);
        config.set("c", false);

        System.out.println("=========================");

        System.out.println("a : " + config.getString("a"));
        System.out.println("b : " + config.getInt("b"));
        System.out.println("c : " + config.getBoolean("c"));

        System.out.println("list : " + config.getStringList("list"));
        System.out.println("map : " + config.getMap("map"));

        System.out.println("=========================");

    }

}
