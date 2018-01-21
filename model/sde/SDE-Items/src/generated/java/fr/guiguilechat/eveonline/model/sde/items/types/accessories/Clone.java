
package fr.guiguilechat.eveonline.model.sde.items.types.accessories;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.Accessories;
import org.yaml.snakeyaml.Yaml;

public class Clone
    extends Accessories
{

    /**
     * Amount of skill points saved by clone
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(10000)
    public int SkillPointsSaved;
    public final static String RESOURCE_PATH = "SDE/items/accessories/Clone.yaml";
    private static LinkedHashMap<String, Clone> cache = (null);

    @Override
    public int getGroupId() {
        return  23;
    }

    @Override
    public Class<?> getGroup() {
        return Clone.class;
    }

    public static LinkedHashMap<String, Clone> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Clone.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, Clone> items;

    }

}
