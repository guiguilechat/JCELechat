package fr.guiguilechat.jcelechat.model.sde.items.types.planetaryresources;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.types.PlanetaryResources;
import org.yaml.snakeyaml.Yaml;

public class PlanetSolidRawResource
    extends PlanetaryResources
{
    public final static String RESOURCE_PATH = "SDE/items/planetaryresources/PlanetSolidRawResource.yaml";
    private static Map<String, PlanetSolidRawResource> cache = (null);

    @Override
    public int getGroupId() {
        return  1032;
    }

    @Override
    public Class<?> getGroup() {
        return PlanetSolidRawResource.class;
    }

    public static synchronized Map<String, PlanetSolidRawResource> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(PlanetSolidRawResource.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, PlanetSolidRawResource> items;
    }
}
