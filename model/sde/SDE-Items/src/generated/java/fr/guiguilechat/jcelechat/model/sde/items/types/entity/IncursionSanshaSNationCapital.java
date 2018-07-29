package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class IncursionSanshaSNationCapital
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/IncursionSanshaSNationCapital.yaml";
    private static LinkedHashMap<String, IncursionSanshaSNationCapital> cache = (null);

    @Override
    public int getGroupId() {
        return  1052;
    }

    @Override
    public Class<?> getGroup() {
        return IncursionSanshaSNationCapital.class;
    }

    public static synchronized LinkedHashMap<String, IncursionSanshaSNationCapital> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(IncursionSanshaSNationCapital.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, IncursionSanshaSNationCapital> items;
    }
}
