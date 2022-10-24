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

    public Map<String, Object> getDefaultMap(String key) {
        HashMap<String, Object> map = organizedMap(defaults);
        Object value = null;

        if (key.contains(".")) {
            String[] keySplit = key.split("\\.");

            int i = 0;
            while (i < keySplit.length - 1) {
                value = map.get(keySplit[i]);

                if (value != null && value instanceof Map) {
                    map = (HashMap<String, Object>) value;
                } else {
                    break;
                }
                i++;
            }

            if (i == keySplit.length - 1) {
                value = map.get(keySplit[keySplit.length - 1]);
            }
        } else {
            value = map.get(key);
        }
        return value != null && value instanceof Map ? (Map<String, Object>) value : null;
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

    protected HashMap<String, Object> addDefaultValues(HashMap<String, Object> originalMap) {
        HashMap<String, Object> map = new LinkedHashMap<>(originalMap);

        defaults.forEach((key, value) -> {
            if (!map.containsKey(key)) {
                map.put(key, value);
            }
        });

        return map;
    }

    protected HashMap<String, Object> organizedMap(HashMap<String, Object> originalMap) {
        HashMap<String, Object> map = new LinkedHashMap<>();

        originalMap.forEach((key, value) -> {
            if (key.contains(".")) {
                String[] kSplit = key.split("\\.");
                HashMap<String, Object> pMap = null, cMap = null;

                for (int i = 0; i < kSplit.length; i++) {
                    String cName = kSplit[i];
                    Object cObject = i < 1 ? map.get(cName) : cMap.get(cName);

                    if (i < 1) {
                        pMap = cObject == null ? new LinkedHashMap<>() : (HashMap<String, Object>) cObject;
                        cMap = pMap;
                    } else if (i >= kSplit.length - 1) {
                        cMap.put(cName, value);
                        map.put(kSplit[0], pMap);
                    } else if (cObject instanceof HashMap || cObject == null) {
                        HashMap<String, Object> tChild = cObject == null ? new LinkedHashMap<>() : (HashMap<String, Object>) cObject;
                        cMap.put(cName, cMap = tChild);
                    }
                }
            } else {
                map.put(key, value);
            }
        });

        return map;
    }
}
