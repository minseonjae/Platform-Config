package kr.codingtree.platformconfig;

import kr.codingtree.platformconfig.section.DefaultSection;
import lombok.Getter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class DefaultConfig implements DefaultSection {

    @Getter
    protected HashMap<String, Object> defaults = new LinkedHashMap<>();

    public void addDefault(String key, Object value) {
        if (key == null || key.length() < 1) {
            throw new NullPointerException("key is marked non-null but is null");
        }

        Iterator<String> iterator = defaults.keySet().iterator();

        while (iterator.hasNext()) {
            String ikey = iterator.next();

            if (ikey.startsWith(key + ".")) {
                iterator.remove();
            }
        }

        if (value instanceof Map) {
            valuesToDot(key, defaults, (Map<String, Object>) value);
        } else {
            defaults.put(key, value);
        }
    }
    public void addDefaults(Map<String, Object> map) {
        map.forEach((key, value) -> addDefault(key, value));
    }

    public Object getDefault(String key) {
        return defaults.get(key);
    }

    public boolean isDefault(String key) {
        if (key == null || key.length() < 1) {
            throw new NullPointerException("key is marked non-null but is null");
        }
        return defaults.containsKey(key);
    }

    public void clearDefaults() {
        defaults.clear();
    }

    protected void valuesToDot(String parentKey, Map<String, Object> values, Map<String, Object> loadValues) {
        loadValues.forEach((key, value) -> {
            String pkey;

            if (parentKey != null) {
                pkey = parentKey + "." + key;
            } else {
                pkey = key;
            }

            if (value instanceof Map) {
                valuesToDot(pkey, values, (Map<String, Object>) value);
            } else {
                values.put(pkey, value);
            }
        });
    }
}
