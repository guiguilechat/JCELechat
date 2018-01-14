
package fr.guiguilechat.eveonline.model.sde.compiled.items.bonus;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.DefaultValue;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Bonus;
import org.yaml.snakeyaml.Yaml;

public class CareerBonus
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
     * Bonus or penalty to the percentage time it takes to train skills with Intelligence as the primary attribute.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double IntelligenceSkillTrainingTimeMultiplierBonus;
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
     * Bonus or penalty to the percentage time it takes to train skills with Willpower as the primary attribute.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double WillpowerSkillTrainingTimeMultiplierBonus;
    public final static String RESOURCE_PATH = "SDE/bonus/CareerBonus.yaml";
    private static LinkedHashMap<String, CareerBonus> cache = (null);

    @Override
    public int getGroupId() {
        return  199;
    }

    @Override
    public Class<?> getGroup() {
        return CareerBonus.class;
    }

    public static LinkedHashMap<String, CareerBonus> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, CareerBonus> items;

    }

}
