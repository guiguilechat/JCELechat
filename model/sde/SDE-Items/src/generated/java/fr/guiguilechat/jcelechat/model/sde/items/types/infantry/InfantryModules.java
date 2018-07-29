package fr.guiguilechat.jcelechat.model.sde.items.types.infantry;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Infantry;

public class InfantryModules
    extends Infantry
{
    public final static String RESOURCE_PATH = "SDE/items/infantry/InfantryModules.yaml";
    private static LinkedHashMap<String, InfantryModules> cache = (null);

    @Override
    public int getGroupId() {
        return  351121;
    }

    @Override
    public Class<?> getGroup() {
        return InfantryModules.class;
    }

    public static synchronized LinkedHashMap<String, InfantryModules> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(InfantryModules.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, InfantryModules> items;
    }
}
