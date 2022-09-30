package kr.codingtree.platformconfig.section;

import java.util.Map;

public interface MapSection {

    Map<String, Object> getMap(String key, Map<String, Object> def);
    Map<String, Object> getMap(String key);

}
