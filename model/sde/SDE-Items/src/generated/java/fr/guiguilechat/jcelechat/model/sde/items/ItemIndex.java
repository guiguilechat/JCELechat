package fr.guiguilechat.jcelechat.model.sde.items;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.yaml.snakeyaml.Yaml;

public class ItemIndex {
    public LinkedHashMap<Integer, String> id2name = new LinkedHashMap<>();
    public LinkedHashMap<String, String> name2group = new LinkedHashMap<>();
    public LinkedHashMap<String, String> group2class = new LinkedHashMap<>();
    private static Map<String, Map<String, ? extends Item>> groupcache = new HashMap<>();
    public static final String RESOURCE_PATH = "SDE/items/metainf.yaml";
    private static ItemIndex cache = (null);

    @SuppressWarnings("unchecked")
    public static Item getItem(String name) {
        if (name == null) {
            return null;
        }
        String group = ItemIndex.load().name2group.get(name);
        if (group == null) {
            return null;
        }
        Map<String, ? extends Item> map = groupcache.get(group);
        if (map == null) {
            try {
                String className = ("fr.guiguilechat.jcelechat.model.sde.items.types."+ group.replaceAll("/", "."));
                Class<?> loadclass = ItemIndex.class.getClassLoader().loadClass(className);
                if (loadclass!= null) {
                    IMetaGroup<? extends Item> img = ((IMetaGroup<? extends Item> ) loadclass.getField("METAGROUP").get(null));
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

    public static Item getItem(int id) {
        return ItemIndex.getItem(ItemIndex.load().id2name.get(id));
    }

    public static synchronized ItemIndex load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(ItemIndex.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), ItemIndex.class);
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return cache;
    }
}
