package fr.guiguilechat.jcelechat.model.sde.items.types.blueprint;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Blueprint;

public class ShieldResistanceShiftHardenerBlueprint
    extends Blueprint
{
    public final static String RESOURCE_PATH = "SDE/items/blueprint/ShieldResistanceShiftHardenerBlueprint.yaml";
    private static LinkedHashMap<String, ShieldResistanceShiftHardenerBlueprint> cache = (null);

    @Override
    public int getGroupId() {
        return  1723;
    }

    @Override
    public Class<?> getGroup() {
        return ShieldResistanceShiftHardenerBlueprint.class;
    }

    public static synchronized LinkedHashMap<String, ShieldResistanceShiftHardenerBlueprint> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(ShieldResistanceShiftHardenerBlueprint.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, ShieldResistanceShiftHardenerBlueprint> items;
    }
}
