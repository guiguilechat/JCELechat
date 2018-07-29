package fr.guiguilechat.jcelechat.model.sde.items.types.infantry;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Infantry;

public class InfantryWeapons
    extends Infantry
{
    public final static String RESOURCE_PATH = "SDE/items/infantry/InfantryWeapons.yaml";
    private static LinkedHashMap<String, InfantryWeapons> cache = (null);

    @Override
    public int getGroupId() {
        return  350858;
    }

    @Override
    public Class<?> getGroup() {
        return InfantryWeapons.class;
    }

    public static synchronized LinkedHashMap<String, InfantryWeapons> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(InfantryWeapons.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, InfantryWeapons> items;
    }
}
