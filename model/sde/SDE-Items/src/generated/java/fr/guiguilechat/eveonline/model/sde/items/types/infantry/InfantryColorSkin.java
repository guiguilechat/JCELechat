package fr.guiguilechat.eveonline.model.sde.items.types.infantry;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Infantry;
import org.yaml.snakeyaml.Yaml;

public class InfantryColorSkin
    extends Infantry
{
    public final static String RESOURCE_PATH = "SDE/items/infantry/InfantryColorSkin.yaml";
    private static LinkedHashMap<String, InfantryColorSkin> cache = (null);

    @Override
    public int getGroupId() {
        return  368726;
    }

    @Override
    public Class<?> getGroup() {
        return InfantryColorSkin.class;
    }

    public static synchronized LinkedHashMap<String, InfantryColorSkin> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(InfantryColorSkin.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, InfantryColorSkin> items;
    }
}
