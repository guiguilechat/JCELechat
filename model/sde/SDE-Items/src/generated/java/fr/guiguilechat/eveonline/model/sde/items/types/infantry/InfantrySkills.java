package fr.guiguilechat.eveonline.model.sde.items.types.infantry;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Infantry;
import org.yaml.snakeyaml.Yaml;

public class InfantrySkills
    extends Infantry
{
    public final static String RESOURCE_PATH = "SDE/items/infantry/InfantrySkills.yaml";
    private static LinkedHashMap<String, InfantrySkills> cache = (null);

    @Override
    public int getGroupId() {
        return  351648;
    }

    @Override
    public Class<?> getGroup() {
        return InfantrySkills.class;
    }

    public static LinkedHashMap<String, InfantrySkills> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(InfantrySkills.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, InfantrySkills> items;
    }
}
