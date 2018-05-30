package fr.guiguilechat.eveonline.model.sde.items.types.planetarycommodities;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.PlanetaryCommodities;
import org.yaml.snakeyaml.Yaml;

public class AdvancedCommoditiesTier4
    extends PlanetaryCommodities
{
    public final static String RESOURCE_PATH = "SDE/items/planetarycommodities/AdvancedCommoditiesTier4.yaml";
    private static LinkedHashMap<String, AdvancedCommoditiesTier4> cache = (null);

    @Override
    public int getGroupId() {
        return  1041;
    }

    @Override
    public Class<?> getGroup() {
        return AdvancedCommoditiesTier4 .class;
    }

    public static synchronized LinkedHashMap<String, AdvancedCommoditiesTier4> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AdvancedCommoditiesTier4 .class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, AdvancedCommoditiesTier4> items;
    }
}
