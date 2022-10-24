# Platform-Config
![snakeyaml](https://img.shields.io/badge/snakeyaml-1.30-GREEN?style=for-the-badge)
![gson](https://img.shields.io/badge/gson-2.9.0-GREEN?style=for-the-badge)
![lombok](https://img.shields.io/badge/lombok-1.18.24-GREEN?style=for-the-badge)

BukkitApi, BungeeCord에 들어있는 Configuration의 기능을 모방하여 제작하였습니다.
#
#### 설정 값에 넣을 때
``` Java
set(String key, Object value);
```
#### 기본 설정 값을 넣을 때 [기본 설정 값은 다른 값에 영향을 주지 않습니다.]
``` Java
addDefault(String key, Object value);
```
#### 설정 값을 가져올 떄
``` Java
get(String key);
```
#### 기본 설정 값을 가져올 떄
``` Java
getDefault(String key);
```
#### 설정 값이 존재하는지 확인할 때
``` Java
contains(String key);
```
#### 기본 설정 값이 존재하는지 확인할 때
``` Java
isDefault(String key);
```
###
## 예제 _(yaml config)_
### YamlConfigTest.java
``` Java
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

```
### 결과 config.yml
``` yaml
a: a
b: 1
c: true
list:
- a
- b
- c
map:
  a: a
  b: b
  c: c
```
###
## 예제 _(yaml config, default)_
### YamlConfigDefaultTest.java
``` Java
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
```

### 결과 콘솔
```text
=========================
a : b
b : 2
c : false
list : [a, b, c]
map : {a=a, b=b, c=c}
=========================
```

### 결과 default-config.yml
``` yaml
a: a
b: 1
c: true
list:
- a
- b
- c
map:
  a: a
  b: b
  c: c

```
###
## 예제 _(json config)_
### JsonConfigTest.java
``` Java
package example;

import kr.codingtree.platformconfig.JsonConfig;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class JsonConfigTest {

    public static void main(String[] args) {
        JsonConfig config = new JsonConfig();
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

```
### 결과 config.json
``` json
{
  "a": "a",
  "b": 1.0,
  "c": true,
  "list": [
    "a",
    "b",
    "c"
  ],
  "map": {
    "a": "aa",
    "b": "bb",
    "c": "cc"
  }
}
```
###
## 예제 _(json config, default)_
### JsonConfigDefaultTest.java
``` Java
package example;

import kr.codingtree.platformconfig.JsonConfig;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class JsonConfigDefaultTest {

    public static void main(String[] args) {
        JsonConfig config = new JsonConfig();
        File file = new File("src/main/java/example", "default-config.json");

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
```

### 결과 콘솔
```text
=========================
a : b
b : 2
c : false
list : [a, b, c]
map : {a=a, b=b, c=c}
=========================
```

### 결과 config.json
``` json
{
  "a": "a",
  "b": 1.0,
  "c": true,
  "list": [
    "a",
    "b",
    "c"
  ],
  "map": {
    "a": "a",
    "b": "b",
    "c": "c"
  }
}
```
