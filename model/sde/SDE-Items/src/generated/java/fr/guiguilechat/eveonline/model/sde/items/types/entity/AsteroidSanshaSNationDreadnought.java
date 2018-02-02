package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidSanshaSNationDreadnought
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidSanshaSNationDreadnought.yaml";
    private static LinkedHashMap<String, AsteroidSanshaSNationDreadnought> cache = (null);

    @Override
    public int getGroupId() {
        return  1687;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidSanshaSNationDreadnought.class;
    }

    public static synchronized LinkedHashMap<String, AsteroidSanshaSNationDreadnought> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidSanshaSNationDreadnought.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, AsteroidSanshaSNationDreadnought> items;
    }
}
