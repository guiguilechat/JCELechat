package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class HiddenZenithCaldariCapital
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/HiddenZenithCaldariCapital.yaml";
    private static LinkedHashMap<String, HiddenZenithCaldariCapital> cache = (null);

    @Override
    public int getGroupId() {
        return  1805;
    }

    @Override
    public Class<?> getGroup() {
        return HiddenZenithCaldariCapital.class;
    }

    public static LinkedHashMap<String, HiddenZenithCaldariCapital> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(HiddenZenithCaldariCapital.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, HiddenZenithCaldariCapital> items;
    }
}
