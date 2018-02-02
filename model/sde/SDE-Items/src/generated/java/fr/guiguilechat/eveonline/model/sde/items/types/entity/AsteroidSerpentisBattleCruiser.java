package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidSerpentisBattleCruiser
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidSerpentisBattleCruiser.yaml";
    private static LinkedHashMap<String, AsteroidSerpentisBattleCruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  584;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidSerpentisBattleCruiser.class;
    }

    public static synchronized LinkedHashMap<String, AsteroidSerpentisBattleCruiser> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidSerpentisBattleCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, AsteroidSerpentisBattleCruiser> items;
    }
}
