package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidSanshaSNationSupercarrier
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidSanshaSNationSupercarrier.yaml";
    private static LinkedHashMap<String, AsteroidSanshaSNationSupercarrier> cache = (null);

    @Override
    public int getGroupId() {
        return  1688;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidSanshaSNationSupercarrier.class;
    }

    public static synchronized LinkedHashMap<String, AsteroidSanshaSNationSupercarrier> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidSanshaSNationSupercarrier.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, AsteroidSanshaSNationSupercarrier> items;
    }
}
