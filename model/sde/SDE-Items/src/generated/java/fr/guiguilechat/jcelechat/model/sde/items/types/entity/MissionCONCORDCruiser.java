package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class MissionCONCORDCruiser
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/MissionCONCORDCruiser.yaml";
    private static LinkedHashMap<String, MissionCONCORDCruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  695;
    }

    @Override
    public Class<?> getGroup() {
        return MissionCONCORDCruiser.class;
    }

    public static synchronized LinkedHashMap<String, MissionCONCORDCruiser> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(MissionCONCORDCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, MissionCONCORDCruiser> items;
    }
}
