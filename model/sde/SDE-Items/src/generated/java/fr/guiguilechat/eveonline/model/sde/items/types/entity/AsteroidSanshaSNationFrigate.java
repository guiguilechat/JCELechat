package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidSanshaSNationFrigate
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidSanshaSNationFrigate.yaml";
    private static LinkedHashMap<String, AsteroidSanshaSNationFrigate> cache = (null);

    @Override
    public int getGroupId() {
        return  567;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidSanshaSNationFrigate.class;
    }

    public static synchronized LinkedHashMap<String, AsteroidSanshaSNationFrigate> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidSanshaSNationFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, AsteroidSanshaSNationFrigate> items;
    }
}
