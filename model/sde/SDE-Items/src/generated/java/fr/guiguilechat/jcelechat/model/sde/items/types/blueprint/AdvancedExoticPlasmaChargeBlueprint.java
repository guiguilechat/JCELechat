package fr.guiguilechat.jcelechat.model.sde.items.types.blueprint;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.types.Blueprint;
import org.yaml.snakeyaml.Yaml;

public class AdvancedExoticPlasmaChargeBlueprint
    extends Blueprint
{
    public final static String RESOURCE_PATH = "SDE/items/blueprint/AdvancedExoticPlasmaChargeBlueprint.yaml";
    private static LinkedHashMap<String, AdvancedExoticPlasmaChargeBlueprint> cache = (null);

    @Override
    public int getGroupId() {
        return  1994;
    }

    @Override
    public Class<?> getGroup() {
        return AdvancedExoticPlasmaChargeBlueprint.class;
    }

    public static synchronized LinkedHashMap<String, AdvancedExoticPlasmaChargeBlueprint> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AdvancedExoticPlasmaChargeBlueprint.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, AdvancedExoticPlasmaChargeBlueprint> items;
    }
}
