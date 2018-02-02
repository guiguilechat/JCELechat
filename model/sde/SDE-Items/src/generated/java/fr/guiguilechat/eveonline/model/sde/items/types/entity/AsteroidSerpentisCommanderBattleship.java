package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidSerpentisCommanderBattleship
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidSerpentisCommanderBattleship.yaml";
    private static LinkedHashMap<String, AsteroidSerpentisCommanderBattleship> cache = (null);

    @Override
    public int getGroupId() {
        return  852;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidSerpentisCommanderBattleship.class;
    }

    public static synchronized LinkedHashMap<String, AsteroidSerpentisCommanderBattleship> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidSerpentisCommanderBattleship.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, AsteroidSerpentisCommanderBattleship> items;
    }
}
