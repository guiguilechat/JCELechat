package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidSerpentisCommanderCruiser
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidSerpentisCommanderCruiser.yaml";
    private static LinkedHashMap<String, AsteroidSerpentisCommanderCruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  812;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidSerpentisCommanderCruiser.class;
    }

    public static LinkedHashMap<String, AsteroidSerpentisCommanderCruiser> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidSerpentisCommanderCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, AsteroidSerpentisCommanderCruiser> items;
    }
}
