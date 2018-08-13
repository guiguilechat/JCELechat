package fr.guiguilechat.jcelechat.model.sde.items.types.planetarycommodities;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.types.PlanetaryCommodities;
import org.yaml.snakeyaml.Yaml;

public class SpecializedCommoditiesTier3
    extends PlanetaryCommodities
{
    public final static String RESOURCE_PATH = "SDE/items/planetarycommodities/SpecializedCommoditiesTier3.yaml";
    private static Map<String, SpecializedCommoditiesTier3> cache = (null);

    @Override
    public int getGroupId() {
        return  1040;
    }

    @Override
    public Class<?> getGroup() {
        return SpecializedCommoditiesTier3 .class;
    }

    public static synchronized Map<String, SpecializedCommoditiesTier3> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(SpecializedCommoditiesTier3 .class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, SpecializedCommoditiesTier3> items;
    }
}
