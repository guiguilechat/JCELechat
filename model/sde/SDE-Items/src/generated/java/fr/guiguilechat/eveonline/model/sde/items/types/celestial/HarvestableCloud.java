package fr.guiguilechat.eveonline.model.sde.items.types.celestial;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Celestial;
import org.yaml.snakeyaml.Yaml;

public class HarvestableCloud
    extends Celestial
{
    public final static String RESOURCE_PATH = "SDE/items/celestial/HarvestableCloud.yaml";
    private static LinkedHashMap<String, HarvestableCloud> cache = (null);

    @Override
    public int getGroupId() {
        return  711;
    }

    @Override
    public Class<?> getGroup() {
        return HarvestableCloud.class;
    }

    public static LinkedHashMap<String, HarvestableCloud> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(HarvestableCloud.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, HarvestableCloud> items;
    }
}
