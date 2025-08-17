package fr.guiguilechat.jcelechat.model.sde;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class TypeIndex {
    public LinkedHashMap<String, ArrayList<Integer>> name2Ids = new LinkedHashMap<>();
    public LinkedHashMap<Integer, String> id2group = new LinkedHashMap<>();
    private static Map<String, Map<Integer, ? extends EveType>> groupcache = new HashMap<>();
    public static final String RESOURCE_PATH = "SDE/types/metainf.yaml";
    private static TypeIndex cache = null;

    public static EveType getType(int id) {
        String group = TypeIndex.load().id2group.get(id);
        if (group == null) {
            return null;
        }
        Map<Integer, ? extends EveType> map = groupcache.get(group);
        if (map == null) {
            try {
                String className = "fr.guiguilechat.jcelechat.model.sde.types."+ group.replaceAll("/", ".");
                Class<?> loadclass = TypeIndex.class.getClassLoader().loadClass(className);
                if (loadclass!= null) {
                    IMetaGroup<? extends EveType> img = (IMetaGroup<? extends EveType> ) loadclass.getField("METAGROUP").get(null);
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
        return map.get(id);
    }

    public static List<EveType> getTypes(String name) {
        return TypeIndex.load().name2Ids.getOrDefault(name, new ArrayList<>()).stream().map(TypeIndex::getType).collect(Collectors.toList());
    }

    public static synchronized TypeIndex load() {
        if (cache == null) {
            try(final InputStreamReader reader = new InputStreamReader(TypeIndex.class.getClassLoader().getResourceAsStream(RESOURCE_PATH))) {
                LoaderOptions options = new LoaderOptions();
                options.setCodePointLimit(Integer.MAX_VALUE);
                cache = new Yaml(options).loadAs(reader, TypeIndex.class);
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return cache;
    }
}
