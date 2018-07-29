package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class LCODrone
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/LCODrone.yaml";
    private static LinkedHashMap<String, LCODrone> cache = (null);

    @Override
    public int getGroupId() {
        return  279;
    }

    @Override
    public Class<?> getGroup() {
        return LCODrone.class;
    }

    public static synchronized LinkedHashMap<String, LCODrone> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(LCODrone.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, LCODrone> items;
    }
}
