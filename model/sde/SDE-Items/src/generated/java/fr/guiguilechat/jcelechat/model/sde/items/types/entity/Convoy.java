package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class Convoy
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/Convoy.yaml";
    private static LinkedHashMap<String, Convoy> cache = (null);

    @Override
    public int getGroupId() {
        return  297;
    }

    @Override
    public Class<?> getGroup() {
        return Convoy.class;
    }

    public static synchronized LinkedHashMap<String, Convoy> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Convoy.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, Convoy> items;
    }
}
