package fr.guiguilechat.jcelechat.model.sde.items.types.commodity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Commodity;

public class Biohazard
    extends Commodity
{
    public final static String RESOURCE_PATH = "SDE/items/commodity/Biohazard.yaml";
    private static LinkedHashMap<String, Biohazard> cache = (null);

    @Override
    public int getGroupId() {
        return  284;
    }

    @Override
    public Class<?> getGroup() {
        return Biohazard.class;
    }

    public static synchronized LinkedHashMap<String, Biohazard> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Biohazard.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, Biohazard> items;
    }
}
