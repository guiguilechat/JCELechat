
package fr.guiguilechat.eveonline.model.sde.compiled.items.bonus;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.DefaultValue;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Bonus;
import org.yaml.snakeyaml.Yaml;

public class AmarrEducation
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
    public final static String RESOURCE_PATH = "SDE/items/bonus/AmarrEducation.yaml";
    private static LinkedHashMap<String, AmarrEducation> cache = (null);

    @Override
    public int getGroupId() {
        return  195;
    }

    @Override
    public Class<?> getGroup() {
        return AmarrEducation.class;
    }

    public static LinkedHashMap<String, AmarrEducation> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AmarrEducation.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, AmarrEducation> items;

    }

}
