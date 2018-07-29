package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class PirateDrone
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/PirateDrone.yaml";
    private static LinkedHashMap<String, PirateDrone> cache = (null);

    @Override
    public int getGroupId() {
        return  185;
    }

    @Override
    public Class<?> getGroup() {
        return PirateDrone.class;
    }

    public static synchronized LinkedHashMap<String, PirateDrone> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(PirateDrone.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, PirateDrone> items;
    }
}
