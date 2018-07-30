package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidAngelCartelCommanderCruiser
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidAngelCartelCommanderCruiser.yaml";
    private static LinkedHashMap<String, AsteroidAngelCartelCommanderCruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  790;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidAngelCartelCommanderCruiser.class;
    }

    public static synchronized LinkedHashMap<String, AsteroidAngelCartelCommanderCruiser> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidAngelCartelCommanderCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, AsteroidAngelCartelCommanderCruiser> items;
    }
}
