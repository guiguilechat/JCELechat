package fr.guiguilechat.jcelechat.model.sde.items.types.celestial;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Celestial;

public class OrbitalTarget
    extends Celestial
{
    public final static String RESOURCE_PATH = "SDE/items/celestial/OrbitalTarget.yaml";
    private static LinkedHashMap<String, OrbitalTarget> cache = (null);

    @Override
    public int getGroupId() {
        return  1198;
    }

    @Override
    public Class<?> getGroup() {
        return OrbitalTarget.class;
    }

    public static synchronized LinkedHashMap<String, OrbitalTarget> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(OrbitalTarget.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, OrbitalTarget> items;
    }
}
