package fr.guiguilechat.eveonline.model.sde.items.types.placeables;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Placeables;
import org.yaml.snakeyaml.Yaml;

public class Furniture
    extends Placeables
{
    public final static String RESOURCE_PATH = "SDE/items/placeables/Furniture.yaml";
    private static LinkedHashMap<String, Furniture> cache = (null);

    @Override
    public int getGroupId() {
        return  940;
    }

    @Override
    public Class<?> getGroup() {
        return Furniture.class;
    }

    public static synchronized LinkedHashMap<String, Furniture> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Furniture.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, Furniture> items;
    }
}
