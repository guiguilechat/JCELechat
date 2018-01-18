
package fr.guiguilechat.eveonline.model.sde.compiled.items.skill;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Skill;
import org.yaml.snakeyaml.Yaml;

public class FakeSkills
    extends Skill
{

    public final static String RESOURCE_PATH = "SDE/items/skill/FakeSkills.yaml";
    private static LinkedHashMap<String, FakeSkills> cache = (null);

    @Override
    public int getGroupId() {
        return  505;
    }

    @Override
    public Class<?> getGroup() {
        return FakeSkills.class;
    }

    public static LinkedHashMap<String, FakeSkills> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(FakeSkills.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, FakeSkills> items;

    }

}
