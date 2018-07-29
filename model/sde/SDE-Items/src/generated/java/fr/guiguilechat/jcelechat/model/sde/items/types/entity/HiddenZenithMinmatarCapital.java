package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class HiddenZenithMinmatarCapital
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/HiddenZenithMinmatarCapital.yaml";
    private static LinkedHashMap<String, HiddenZenithMinmatarCapital> cache = (null);

    @Override
    public int getGroupId() {
        return  1807;
    }

    @Override
    public Class<?> getGroup() {
        return HiddenZenithMinmatarCapital.class;
    }

    public static synchronized LinkedHashMap<String, HiddenZenithMinmatarCapital> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(HiddenZenithMinmatarCapital.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, HiddenZenithMinmatarCapital> items;
    }
}
