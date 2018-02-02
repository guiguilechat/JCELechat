package fr.guiguilechat.eveonline.model.sde.items.types.commodity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Commodity;
import org.yaml.snakeyaml.Yaml;

public class General
    extends Commodity
{
    public final static String RESOURCE_PATH = "SDE/items/commodity/General.yaml";
    private static LinkedHashMap<String, General> cache = (null);

    @Override
    public int getGroupId() {
        return  280;
    }

    @Override
    public Class<?> getGroup() {
        return General.class;
    }

    public static synchronized LinkedHashMap<String, General> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(General.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, General> items;
    }
}
