package fr.guiguilechat.jcelechat.model.sde.items.types.planetarycommodities;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.PlanetaryCommodities;

public class RefinedCommoditiesTier2
    extends PlanetaryCommodities
{
    public final static String RESOURCE_PATH = "SDE/items/planetarycommodities/RefinedCommoditiesTier2.yaml";
    private static LinkedHashMap<String, RefinedCommoditiesTier2> cache = (null);

    @Override
    public int getGroupId() {
        return  1034;
    }

    @Override
    public Class<?> getGroup() {
        return RefinedCommoditiesTier2 .class;
    }

    public static synchronized LinkedHashMap<String, RefinedCommoditiesTier2> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(RefinedCommoditiesTier2 .class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, RefinedCommoditiesTier2> items;
    }
}
