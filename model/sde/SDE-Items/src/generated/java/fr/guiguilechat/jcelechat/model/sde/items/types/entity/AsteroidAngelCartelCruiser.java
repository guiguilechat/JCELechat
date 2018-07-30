package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidAngelCartelCruiser
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidAngelCartelCruiser.yaml";
    private static LinkedHashMap<String, AsteroidAngelCartelCruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  551;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidAngelCartelCruiser.class;
    }

    public static synchronized LinkedHashMap<String, AsteroidAngelCartelCruiser> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidAngelCartelCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, AsteroidAngelCartelCruiser> items;
    }
}
