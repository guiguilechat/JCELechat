
package fr.guiguilechat.eveonline.model.sde.compiled.items.bonus;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.DefaultValue;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Bonus;
import org.yaml.snakeyaml.Yaml;

public class MinmatarEducation
    extends Bonus
{

    /**
     * Bonus or penalty to the percentage time it takes to train skills with Charisma as the primary attribute.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double CharismaSkillTrainingTimeMultiplierBonus;
    /**
     * Bonus or penalty to the percentage time it takes to train skills with Memory as the primary attribute.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double MemorySkillTrainingTimeMultiplierBonus;
    /**
     * Bonus or penalty to the percentage time it takes to train skills with Perception as the primary attribute.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double PerceptionSkillTrainingTimeMultiplierBonus;
    /**
     * Scales the capacitor need for fitted modules.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double CapacitorNeedMultiplier;
    /**
     * Factor to adjust module cpu need by.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(1.0D)
    public double CpuMultiplier;
    public final static String RESOURCE_PATH = "SDE/bonus/MinmatarEducation.yaml";
    private static LinkedHashMap<String, MinmatarEducation> cache = (null);

    @Override
    public int getGroupId() {
        return  198;
    }

    @Override
    public Class<?> getGroup() {
        return MinmatarEducation.class;
    }

    public static LinkedHashMap<String, MinmatarEducation> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, MinmatarEducation> items;

    }

}
