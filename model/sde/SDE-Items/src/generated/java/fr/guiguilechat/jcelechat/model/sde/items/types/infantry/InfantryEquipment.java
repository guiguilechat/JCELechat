package fr.guiguilechat.jcelechat.model.sde.items.types.infantry;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Infantry;

public class InfantryEquipment
    extends Infantry
{
    public final static String RESOURCE_PATH = "SDE/items/infantry/InfantryEquipment.yaml";
    private static LinkedHashMap<String, InfantryEquipment> cache = (null);

    @Override
    public int getGroupId() {
        return  351844;
    }

    @Override
    public Class<?> getGroup() {
        return InfantryEquipment.class;
    }

    public static synchronized LinkedHashMap<String, InfantryEquipment> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(InfantryEquipment.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, InfantryEquipment> items;
    }
}
