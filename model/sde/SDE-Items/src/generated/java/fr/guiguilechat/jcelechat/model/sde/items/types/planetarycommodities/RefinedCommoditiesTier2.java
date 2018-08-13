package fr.guiguilechat.jcelechat.model.sde.items.types.planetarycommodities;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.types.PlanetaryCommodities;
import org.yaml.snakeyaml.Yaml;

public class RefinedCommoditiesTier2
    extends PlanetaryCommodities
{
    public final static String RESOURCE_PATH = "SDE/items/planetarycommodities/RefinedCommoditiesTier2.yaml";
    private static Map<String, RefinedCommoditiesTier2> cache = (null);

    @Override
    public int getGroupId() {
        return  1034;
    }

    @Override
    public Class<?> getGroup() {
        return RefinedCommoditiesTier2 .class;
    }

    public static synchronized Map<String, RefinedCommoditiesTier2> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(RefinedCommoditiesTier2 .class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, RefinedCommoditiesTier2> items;
    }
}
