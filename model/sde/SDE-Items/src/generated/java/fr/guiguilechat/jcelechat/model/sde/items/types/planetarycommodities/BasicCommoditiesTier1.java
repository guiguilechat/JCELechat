package fr.guiguilechat.jcelechat.model.sde.items.types.planetarycommodities;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.types.PlanetaryCommodities;
import org.yaml.snakeyaml.Yaml;

public class BasicCommoditiesTier1
    extends PlanetaryCommodities
{
    public final static String RESOURCE_PATH = "SDE/items/planetarycommodities/BasicCommoditiesTier1.yaml";
    private static Map<String, BasicCommoditiesTier1> cache = (null);

    @Override
    public int getGroupId() {
        return  1042;
    }

    @Override
    public Class<?> getGroup() {
        return BasicCommoditiesTier1 .class;
    }

    public static synchronized Map<String, BasicCommoditiesTier1> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(BasicCommoditiesTier1 .class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, BasicCommoditiesTier1> items;
    }
}
