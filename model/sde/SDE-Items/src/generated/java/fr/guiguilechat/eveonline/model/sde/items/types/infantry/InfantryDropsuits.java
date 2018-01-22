package fr.guiguilechat.eveonline.model.sde.items.types.infantry;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Infantry;
import org.yaml.snakeyaml.Yaml;

public class InfantryDropsuits
    extends Infantry
{
    public final static String RESOURCE_PATH = "SDE/items/infantry/InfantryDropsuits.yaml";
    private static LinkedHashMap<String, InfantryDropsuits> cache = (null);

    @Override
    public int getGroupId() {
        return  351064;
    }

    @Override
    public Class<?> getGroup() {
        return InfantryDropsuits.class;
    }

    public static LinkedHashMap<String, InfantryDropsuits> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(InfantryDropsuits.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, InfantryDropsuits> items;
    }
}
