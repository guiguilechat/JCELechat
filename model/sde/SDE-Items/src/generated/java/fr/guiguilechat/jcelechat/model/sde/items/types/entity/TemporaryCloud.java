package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class TemporaryCloud
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/TemporaryCloud.yaml";
    private static LinkedHashMap<String, TemporaryCloud> cache = (null);

    @Override
    public int getGroupId() {
        return  335;
    }

    @Override
    public Class<?> getGroup() {
        return TemporaryCloud.class;
    }

    public static synchronized LinkedHashMap<String, TemporaryCloud> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(TemporaryCloud.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, TemporaryCloud> items;
    }
}
