package fr.guiguilechat.jcelechat.model.sde.items.types.material;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Material;

public class GasIsotopes
    extends Material
{
    public final static String RESOURCE_PATH = "SDE/items/material/GasIsotopes.yaml";
    private static LinkedHashMap<String, GasIsotopes> cache = (null);

    @Override
    public int getGroupId() {
        return  422;
    }

    @Override
    public Class<?> getGroup() {
        return GasIsotopes.class;
    }

    public static synchronized LinkedHashMap<String, GasIsotopes> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(GasIsotopes.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, GasIsotopes> items;
    }
}
