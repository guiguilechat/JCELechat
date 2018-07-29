package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class AsteroidGuristasCommanderBattleship
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidGuristasCommanderBattleship.yaml";
    private static LinkedHashMap<String, AsteroidGuristasCommanderBattleship> cache = (null);

    @Override
    public int getGroupId() {
        return  850;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidGuristasCommanderBattleship.class;
    }

    public static synchronized LinkedHashMap<String, AsteroidGuristasCommanderBattleship> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidGuristasCommanderBattleship.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, AsteroidGuristasCommanderBattleship> items;
    }
}
