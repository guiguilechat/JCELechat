package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidGuristasCommanderCruiser
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidGuristasCommanderCruiser.yaml";
    private static LinkedHashMap<String, AsteroidGuristasCommanderCruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  798;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidGuristasCommanderCruiser.class;
    }

    public static LinkedHashMap<String, AsteroidGuristasCommanderCruiser> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidGuristasCommanderCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, AsteroidGuristasCommanderCruiser> items;
    }
}
