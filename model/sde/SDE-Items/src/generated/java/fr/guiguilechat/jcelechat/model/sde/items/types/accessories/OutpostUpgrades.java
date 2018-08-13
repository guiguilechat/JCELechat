package fr.guiguilechat.jcelechat.model.sde.items.types.accessories;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.types.Accessories;
import org.yaml.snakeyaml.Yaml;

public class OutpostUpgrades
    extends Accessories
{
    public final static String RESOURCE_PATH = "SDE/items/accessories/OutpostUpgrades.yaml";
    private static Map<String, OutpostUpgrades> cache = (null);

    @Override
    public int getGroupId() {
        return  876;
    }

    @Override
    public Class<?> getGroup() {
        return OutpostUpgrades.class;
    }

    public static synchronized Map<String, OutpostUpgrades> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(OutpostUpgrades.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, OutpostUpgrades> items;
    }
}
