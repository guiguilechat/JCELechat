
package fr.guiguilechat.eveonline.model.sde.compiled.items.accessories;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.DefaultValue;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Accessories;
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
    @DefaultValue(10000.0D)
    public double SkillPointsSaved;
    public final static String RESOURCE_PATH = "SDE/accessories/Clone.yaml";
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
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, Clone> items;

    }

}
