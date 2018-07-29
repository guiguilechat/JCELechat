package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class MissionCaldariStateCarrier
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/MissionCaldariStateCarrier.yaml";
    private static LinkedHashMap<String, MissionCaldariStateCarrier> cache = (null);

    @Override
    public int getGroupId() {
        return  866;
    }

    @Override
    public Class<?> getGroup() {
        return MissionCaldariStateCarrier.class;
    }

    public static synchronized LinkedHashMap<String, MissionCaldariStateCarrier> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(MissionCaldariStateCarrier.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, MissionCaldariStateCarrier> items;
    }
}
