package fr.guiguilechat.jcelechat.model.sde.items.types.material;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Material;

public class HybridPolymers
    extends Material
{
    public final static String RESOURCE_PATH = "SDE/items/material/HybridPolymers.yaml";
    private static LinkedHashMap<String, HybridPolymers> cache = (null);

    @Override
    public int getGroupId() {
        return  974;
    }

    @Override
    public Class<?> getGroup() {
        return HybridPolymers.class;
    }

    public static synchronized LinkedHashMap<String, HybridPolymers> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(HybridPolymers.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, HybridPolymers> items;
    }
}
