package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidSerpentisOfficer
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidSerpentisOfficer.yaml";
    private static LinkedHashMap<String, AsteroidSerpentisOfficer> cache = (null);

    @Override
    public int getGroupId() {
        return  574;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidSerpentisOfficer.class;
    }

    public static synchronized LinkedHashMap<String, AsteroidSerpentisOfficer> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidSerpentisOfficer.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, AsteroidSerpentisOfficer> items;
    }
}
