package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class AsteroidRogueDroneOfficer
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidRogueDroneOfficer.yaml";
    private static LinkedHashMap<String, AsteroidRogueDroneOfficer> cache = (null);

    @Override
    public int getGroupId() {
        return  1174;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidRogueDroneOfficer.class;
    }

    public static synchronized LinkedHashMap<String, AsteroidRogueDroneOfficer> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidRogueDroneOfficer.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, AsteroidRogueDroneOfficer> items;
    }
}
