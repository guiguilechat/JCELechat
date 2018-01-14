
package fr.guiguilechat.eveonline.model.sde.compiled.items.accessories;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.DefaultValue;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Accessories;
import org.yaml.snakeyaml.Yaml;

public class SkillInjectors
    extends Accessories
{

    /**
     * The maximum amount of skill points that the character can have before the item is unusable
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double MaxCharacterSkillPointLimit;
    /**
     * The amount of skill points contained in this item
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ContainedSkillPoints;
    public final static String RESOURCE_PATH = "SDE/accessories/SkillInjectors.yaml";
    private static LinkedHashMap<String, SkillInjectors> cache = (null);

    @Override
    public int getGroupId() {
        return  1739;
    }

    @Override
    public Class<?> getGroup() {
        return SkillInjectors.class;
    }

    public static LinkedHashMap<String, SkillInjectors> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, SkillInjectors> items;

    }

}
