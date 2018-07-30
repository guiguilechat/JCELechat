package fr.guiguilechat.jcelechat.model.sde.items.types.celestial;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.types.Celestial;
import org.yaml.snakeyaml.Yaml;

public class Wormhole
    extends Celestial
{
    public final static String RESOURCE_PATH = "SDE/items/celestial/Wormhole.yaml";
    private static LinkedHashMap<String, Wormhole> cache = (null);

    @Override
    public int getGroupId() {
        return  988;
    }

    @Override
    public Class<?> getGroup() {
        return Wormhole.class;
    }

    public static synchronized LinkedHashMap<String, Wormhole> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Wormhole.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, Wormhole> items;
    }
}
