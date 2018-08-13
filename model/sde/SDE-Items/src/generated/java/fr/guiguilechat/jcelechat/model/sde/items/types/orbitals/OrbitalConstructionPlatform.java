package fr.guiguilechat.jcelechat.model.sde.items.types.orbitals;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.types.Orbitals;
import org.yaml.snakeyaml.Yaml;

public class OrbitalConstructionPlatform
    extends Orbitals
{
    public final static String RESOURCE_PATH = "SDE/items/orbitals/OrbitalConstructionPlatform.yaml";
    private static Map<String, OrbitalConstructionPlatform> cache = (null);

    @Override
    public int getGroupId() {
        return  1106;
    }

    @Override
    public Class<?> getGroup() {
        return OrbitalConstructionPlatform.class;
    }

    public static synchronized Map<String, OrbitalConstructionPlatform> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(OrbitalConstructionPlatform.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, OrbitalConstructionPlatform> items;
    }
}
