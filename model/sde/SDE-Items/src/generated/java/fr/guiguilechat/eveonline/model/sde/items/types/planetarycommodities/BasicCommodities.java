package fr.guiguilechat.eveonline.model.sde.items.types.planetarycommodities;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.PlanetaryCommodities;
import org.yaml.snakeyaml.Yaml;

public class BasicCommodities
    extends PlanetaryCommodities
{
    public final static String RESOURCE_PATH = "SDE/items/planetarycommodities/BasicCommodities.yaml";
    private static LinkedHashMap<String, BasicCommodities> cache = (null);

    @Override
    public int getGroupId() {
        return  1042;
    }

    @Override
    public Class<?> getGroup() {
        return BasicCommodities.class;
    }

    public static LinkedHashMap<String, BasicCommodities> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(BasicCommodities.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, BasicCommodities> items;
    }
}
