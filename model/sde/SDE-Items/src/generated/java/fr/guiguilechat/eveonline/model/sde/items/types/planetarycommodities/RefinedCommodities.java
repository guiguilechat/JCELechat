package fr.guiguilechat.eveonline.model.sde.items.types.planetarycommodities;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.PlanetaryCommodities;
import org.yaml.snakeyaml.Yaml;

public class RefinedCommodities
    extends PlanetaryCommodities
{
    public final static String RESOURCE_PATH = "SDE/items/planetarycommodities/RefinedCommodities.yaml";
    private static LinkedHashMap<String, RefinedCommodities> cache = (null);

    @Override
    public int getGroupId() {
        return  1034;
    }

    @Override
    public Class<?> getGroup() {
        return RefinedCommodities.class;
    }

    public static LinkedHashMap<String, RefinedCommodities> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(RefinedCommodities.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, RefinedCommodities> items;
    }
}
