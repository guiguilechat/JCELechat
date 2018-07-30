package fr.guiguilechat.jcelechat.model.sde.items.types.placeables;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.types.Placeables;
import org.yaml.snakeyaml.Yaml;

public class Generic
    extends Placeables
{
    public final static String RESOURCE_PATH = "SDE/items/placeables/Generic.yaml";
    private static LinkedHashMap<String, Generic> cache = (null);

    @Override
    public int getGroupId() {
        return  1079;
    }

    @Override
    public Class<?> getGroup() {
        return Generic.class;
    }

    public static synchronized LinkedHashMap<String, Generic> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Generic.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, Generic> items;
    }
}
