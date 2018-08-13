package fr.guiguilechat.jcelechat.model.sde.items.types.celestial;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.types.Celestial;
import org.yaml.snakeyaml.Yaml;

public class OrbitalTarget
    extends Celestial
{
    public final static String RESOURCE_PATH = "SDE/items/celestial/OrbitalTarget.yaml";
    private static Map<String, OrbitalTarget> cache = (null);

    @Override
    public int getGroupId() {
        return  1198;
    }

    @Override
    public Class<?> getGroup() {
        return OrbitalTarget.class;
    }

    public static synchronized Map<String, OrbitalTarget> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(OrbitalTarget.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, OrbitalTarget> items;
    }
}
