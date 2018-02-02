package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class SerpentisEventCruiser
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/SerpentisEventCruiser.yaml";
    private static LinkedHashMap<String, SerpentisEventCruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  1567;
    }

    @Override
    public Class<?> getGroup() {
        return SerpentisEventCruiser.class;
    }

    public static synchronized LinkedHashMap<String, SerpentisEventCruiser> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(SerpentisEventCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, SerpentisEventCruiser> items;
    }
}
