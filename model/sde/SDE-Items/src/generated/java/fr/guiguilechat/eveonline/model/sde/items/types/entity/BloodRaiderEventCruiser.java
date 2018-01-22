package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class BloodRaiderEventCruiser
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/BloodRaiderEventCruiser.yaml";
    private static LinkedHashMap<String, BloodRaiderEventCruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  1454;
    }

    @Override
    public Class<?> getGroup() {
        return BloodRaiderEventCruiser.class;
    }

    public static LinkedHashMap<String, BloodRaiderEventCruiser> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(BloodRaiderEventCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, BloodRaiderEventCruiser> items;
    }
}
