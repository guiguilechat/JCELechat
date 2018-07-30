package fr.guiguilechat.jcelechat.model.sde.items.types.infantry;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.types.Infantry;
import org.yaml.snakeyaml.Yaml;

public class InfantryVehicles
    extends Infantry
{
    public final static String RESOURCE_PATH = "SDE/items/infantry/InfantryVehicles.yaml";
    private static LinkedHashMap<String, InfantryVehicles> cache = (null);

    @Override
    public int getGroupId() {
        return  351210;
    }

    @Override
    public Class<?> getGroup() {
        return InfantryVehicles.class;
    }

    public static synchronized LinkedHashMap<String, InfantryVehicles> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(InfantryVehicles.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, InfantryVehicles> items;
    }
}
