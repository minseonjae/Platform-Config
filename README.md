# Platform-Config
![snakeyaml](https://img.shields.io/badge/snakeyaml-1.30-GREEN?style=for-the-badge)
![gson](https://img.shields.io/badge/gson-2.9.0-GREEN?style=for-the-badge)
![lombok](https://img.shields.io/badge/lombok-1.18.24-GREEN?style=for-the-badge)
####BukkitApi, BungeeCord에 들어있는 Configuration의 기능을 모방하여 제작하였습니다.
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
### ConfigTest.java
``` Java
import java.io.File;

public class ConfigTest {

    public static void main(String[] args) {
        YamlConfig config = new YamlConfig();
        File configFile = new File("config.yml");
        config.set("a", 1);
        config.set("b", 2);
        config.set("c", 3);
        config.set("d", 4);
        config.save(configFile);
    }
    
}
```
### 결과 config.yml
``` yaml
a: 1
b: 2
c: 3
d: 4
```
###
## 예제 _(yaml config, default)_
### ConfigTest.java
``` Java
import java.io.File;

public class ConfigTest {

    public static void main(String[] args) {
        YamlConfig config = new YamlConfig();
        File configFile = new File("config.yml");
        config.addDefault("a", 1);
        config.addDefault("b", 2);
        config.addDefault("c", 3);
        config.addDefault("d", 4);
        config.load(configFile);
        
        config.set("b", 5);
        config.set("d", 6);
        config.save(configFile);
    }
    
}
```
### 결과 config.yml
``` yaml
a: 1
b: 5
c: 3
d: 6
```
###
## 예제 _(json config)_
### ConfigTest.java
``` Java
import java.io.File;

public class ConfigTest {

    public static void main(String[] args) {
        JsonConfig config = new JsonConfig();
        File configFile = new File("config.json");
        config.set("a", 1);
        config.set("b", 2);
        config.set("c", 3);
        config.set("d", 4);
        config.save(configFile);
    }
    
}
```
### 결과 config.json
``` json
{
    "a":1,
    "b":2,
    "c":3,
    "d":4
}
```
###
## 예제 _(json config, default)_
### ConfigTest.java
``` Java
import java.io.File;

public class ConfigTest {

    public static void main(String[] args) {
        JsonConfig config = new JsonConfig();
        File configFile = new File("config.json");
        config.addDefault("a", 1);
        config.addDefault("b", 2);
        config.addDefault("c", 3);
        config.addDefault("d", 4);
        config.load(configFile);
        
        config.set("b", 5);
        config.set("d", 6);
        config.save(configFile);
    }
    
}
```
### 결과 config.json
``` json
{
    "a":1,
    "b":5,
    "c":3,
    "d":6
}
```