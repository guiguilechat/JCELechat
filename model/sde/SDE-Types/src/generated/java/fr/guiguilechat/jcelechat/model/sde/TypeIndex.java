package fr.guiguilechat.jcelechat.model.sde;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.yaml.snakeyaml.Yaml;

public class TypeIndex {
    public LinkedHashMap<Integer, String> id2name = new LinkedHashMap<>();
    public LinkedHashMap<String, String> name2group = new LinkedHashMap<>();
    public LinkedHashMap<String, String> group2class = new LinkedHashMap<>();
    private static Map<String, Map<String, ? extends EveType>> groupcache = new HashMap<>();
    public static final String RESOURCE_PATH = "SDE/items/metainf.yaml";
    private static TypeIndex cache = (null);

    @SuppressWarnings("unchecked")
    public static EveType getType(String name) {
        if (name == null) {
            return null;
        }
        String group = TypeIndex.load().name2group.get(name);
        if (group == null) {
            return null;
        }
        Map<String, ? extends EveType> map = groupcache.get(group);
        if (map == null) {
            try {
                String className = ("fr.guiguilechat.jcelechat.model.sde.types."+ group.replaceAll("/", "."));
                Class<?> loadclass = TypeIndex.class.getClassLoader().loadClass(className);
                if (loadclass!= null) {
                    IMetaGroup<? extends EveType> img = ((IMetaGroup<? extends EveType> ) loadclass.getField("METAGROUP").get(null));
                    map = img.load();
                }
            } catch (final Exception exception) {
                throw new UnsupportedOperationException(exception);
            }
            if (map == null) {
                map = Collections.emptyMap();
            }
            groupcache.put(group, map);
        }
        return map.get(name);
    }

    public static EveType getType(int id) {
        return TypeIndex.getType(TypeIndex.load().id2name.get(id));
    }

    public static synchronized TypeIndex load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(TypeIndex.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), TypeIndex.class);
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return cache;
    }
}
