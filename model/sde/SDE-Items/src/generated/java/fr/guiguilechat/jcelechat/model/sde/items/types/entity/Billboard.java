package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class Billboard
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/Billboard.yaml";
    private static LinkedHashMap<String, Billboard> cache = (null);

    @Override
    public int getGroupId() {
        return  323;
    }

    @Override
    public Class<?> getGroup() {
        return Billboard.class;
    }

    public static synchronized LinkedHashMap<String, Billboard> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Billboard.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, Billboard> items;
    }
}
