package kr.codingtree.platformconfig.section;

import java.util.Map;

public interface DefaultSection {

    void addDefault(String key, Object value);
    void addDefaults(Map<String, Object> map);

    Object getDefault(String key);
    Map<String, Object> getDefaults();

    boolean isDefault(String key);

    void clearDefaults();

}
