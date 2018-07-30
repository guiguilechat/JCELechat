package fr.guiguilechat.jcelechat.model.sde.items.types.apparel;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.types.Apparel;
import org.yaml.snakeyaml.Yaml;

public class Tattoos
    extends Apparel
{
    public final static String RESOURCE_PATH = "SDE/items/apparel/Tattoos.yaml";
    private static LinkedHashMap<String, Tattoos> cache = (null);

    @Override
    public int getGroupId() {
        return  1084;
    }

    @Override
    public Class<?> getGroup() {
        return Tattoos.class;
    }

    public static synchronized LinkedHashMap<String, Tattoos> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Tattoos.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, Tattoos> items;
    }
}
