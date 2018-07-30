package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidBloodRaidersBattleship
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidBloodRaidersBattleship.yaml";
    private static LinkedHashMap<String, AsteroidBloodRaidersBattleship> cache = (null);

    @Override
    public int getGroupId() {
        return  556;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidBloodRaidersBattleship.class;
    }

    public static synchronized LinkedHashMap<String, AsteroidBloodRaidersBattleship> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidBloodRaidersBattleship.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, AsteroidBloodRaidersBattleship> items;
    }
}
