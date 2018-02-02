package fr.guiguilechat.eveonline.model.sde.items.types.planetarycommodities;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.PlanetaryCommodities;
import org.yaml.snakeyaml.Yaml;

public class AdvancedCommodities
    extends PlanetaryCommodities
{
    public final static String RESOURCE_PATH = "SDE/items/planetarycommodities/AdvancedCommodities.yaml";
    private static LinkedHashMap<String, AdvancedCommodities> cache = (null);

    @Override
    public int getGroupId() {
        return  1041;
    }

    @Override
    public Class<?> getGroup() {
        return AdvancedCommodities.class;
    }

    public static synchronized LinkedHashMap<String, AdvancedCommodities> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AdvancedCommodities.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, AdvancedCommodities> items;
    }
}
