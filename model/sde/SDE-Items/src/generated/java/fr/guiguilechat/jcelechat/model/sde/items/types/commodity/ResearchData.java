package fr.guiguilechat.jcelechat.model.sde.items.types.commodity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.types.Commodity;
import org.yaml.snakeyaml.Yaml;

public class ResearchData
    extends Commodity
{
    public final static String RESOURCE_PATH = "SDE/items/commodity/ResearchData.yaml";
    private static Map<String, ResearchData> cache = (null);

    @Override
    public int getGroupId() {
        return  1141;
    }

    @Override
    public Class<?> getGroup() {
        return ResearchData.class;
    }

    public static synchronized Map<String, ResearchData> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(ResearchData.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, ResearchData> items;
    }
}
