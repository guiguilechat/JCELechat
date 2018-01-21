
package fr.guiguilechat.eveonline.model.sde.items.types.bonus;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.Bonus;
import org.yaml.snakeyaml.Yaml;

public class CaldariEducation
    extends Bonus
{

    /**
     * Bonus or penalty to the percentage time it takes to train skills with Charisma as the primary attribute.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double CharismaSkillTrainingTimeMultiplierBonus;
    /**
     * Bonus or penalty to the percentage time it takes to train skills with Memory as the primary attribute.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double MemorySkillTrainingTimeMultiplierBonus;
    /**
     * Bonus or penalty to the percentage time it takes to train skills with Perception as the primary attribute.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double PerceptionSkillTrainingTimeMultiplierBonus;
    /**
     * Scales the capacitor need for fitted modules.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double CapacitorNeedMultiplier;
    /**
     * Factor to adjust module cpu need by.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0D)
    public double CpuMultiplier;
    public final static String RESOURCE_PATH = "SDE/items/bonus/CaldariEducation.yaml";
    private static LinkedHashMap<String, CaldariEducation> cache = (null);

    @Override
    public int getGroupId() {
        return  196;
    }

    @Override
    public Class<?> getGroup() {
        return CaldariEducation.class;
    }

    public static LinkedHashMap<String, CaldariEducation> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(CaldariEducation.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, CaldariEducation> items;

    }

}
