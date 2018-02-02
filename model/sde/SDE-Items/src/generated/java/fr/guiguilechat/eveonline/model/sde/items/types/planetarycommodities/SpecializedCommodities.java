package fr.guiguilechat.eveonline.model.sde.items.types.planetarycommodities;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.PlanetaryCommodities;
import org.yaml.snakeyaml.Yaml;

public class SpecializedCommodities
    extends PlanetaryCommodities
{
    public final static String RESOURCE_PATH = "SDE/items/planetarycommodities/SpecializedCommodities.yaml";
    private static LinkedHashMap<String, SpecializedCommodities> cache = (null);

    @Override
    public int getGroupId() {
        return  1040;
    }

    @Override
    public Class<?> getGroup() {
        return SpecializedCommodities.class;
    }

    public static synchronized LinkedHashMap<String, SpecializedCommodities> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(SpecializedCommodities.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, SpecializedCommodities> items;
    }
}
