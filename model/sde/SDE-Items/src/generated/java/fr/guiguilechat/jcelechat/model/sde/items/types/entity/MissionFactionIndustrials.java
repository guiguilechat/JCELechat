package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class MissionFactionIndustrials
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/MissionFactionIndustrials.yaml";
    private static LinkedHashMap<String, MissionFactionIndustrials> cache = (null);

    @Override
    public int getGroupId() {
        return  927;
    }

    @Override
    public Class<?> getGroup() {
        return MissionFactionIndustrials.class;
    }

    public static synchronized LinkedHashMap<String, MissionFactionIndustrials> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(MissionFactionIndustrials.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, MissionFactionIndustrials> items;
    }
}
