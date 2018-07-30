package fr.guiguilechat.jcelechat.model.sde.items.types.accessories;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.types.Accessories;
import org.yaml.snakeyaml.Yaml;

public class PLEX
    extends Accessories
{
    public final static String RESOURCE_PATH = "SDE/items/accessories/PLEX.yaml";
    private static LinkedHashMap<String, PLEX> cache = (null);

    @Override
    public int getGroupId() {
        return  1875;
    }

    @Override
    public Class<?> getGroup() {
        return PLEX.class;
    }

    public static synchronized LinkedHashMap<String, PLEX> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(PLEX.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, PLEX> items;
    }
}
