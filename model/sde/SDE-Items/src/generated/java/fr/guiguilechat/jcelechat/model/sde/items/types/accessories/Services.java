package fr.guiguilechat.jcelechat.model.sde.items.types.accessories;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.types.Accessories;
import org.yaml.snakeyaml.Yaml;

public class Services
    extends Accessories
{
    public final static String RESOURCE_PATH = "SDE/items/accessories/Services.yaml";
    private static Map<String, Services> cache = (null);

    @Override
    public int getGroupId() {
        return  1301;
    }

    @Override
    public Class<?> getGroup() {
        return Services.class;
    }

    public static synchronized Map<String, Services> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Services.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, Services> items;
    }
}
