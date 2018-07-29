package fr.guiguilechat.jcelechat.model.sde.items.types.celestial;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Celestial;

public class IndustrialSupportFacility
    extends Celestial
{
    public final static String RESOURCE_PATH = "SDE/items/celestial/IndustrialSupportFacility.yaml";
    private static LinkedHashMap<String, IndustrialSupportFacility> cache = (null);

    @Override
    public int getGroupId() {
        return  1978;
    }

    @Override
    public Class<?> getGroup() {
        return IndustrialSupportFacility.class;
    }

    public static synchronized LinkedHashMap<String, IndustrialSupportFacility> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(IndustrialSupportFacility.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, IndustrialSupportFacility> items;
    }
}
