package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class HiddenZenithGallenteCapital
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/HiddenZenithGallenteCapital.yaml";
    private static LinkedHashMap<String, HiddenZenithGallenteCapital> cache = (null);

    @Override
    public int getGroupId() {
        return  1806;
    }

    @Override
    public Class<?> getGroup() {
        return HiddenZenithGallenteCapital.class;
    }

    public static synchronized LinkedHashMap<String, HiddenZenithGallenteCapital> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(HiddenZenithGallenteCapital.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, HiddenZenithGallenteCapital> items;
    }
}
