package fr.guiguilechat.eveonline.model.sde.items.types.infantry;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Infantry;
import org.yaml.snakeyaml.Yaml;

public class InfantrySkillEnhancers
    extends Infantry
{
    public final static String RESOURCE_PATH = "SDE/items/infantry/InfantrySkillEnhancers.yaml";
    private static LinkedHashMap<String, InfantrySkillEnhancers> cache = (null);

    @Override
    public int getGroupId() {
        return  354641;
    }

    @Override
    public Class<?> getGroup() {
        return InfantrySkillEnhancers.class;
    }

    public static synchronized LinkedHashMap<String, InfantrySkillEnhancers> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(InfantrySkillEnhancers.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, InfantrySkillEnhancers> items;
    }
}
