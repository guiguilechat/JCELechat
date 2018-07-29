package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class AsteroidSerpentisCommanderBattleCruiser
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidSerpentisCommanderBattleCruiser.yaml";
    private static LinkedHashMap<String, AsteroidSerpentisCommanderBattleCruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  811;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidSerpentisCommanderBattleCruiser.class;
    }

    public static synchronized LinkedHashMap<String, AsteroidSerpentisCommanderBattleCruiser> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidSerpentisCommanderBattleCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, AsteroidSerpentisCommanderBattleCruiser> items;
    }
}
