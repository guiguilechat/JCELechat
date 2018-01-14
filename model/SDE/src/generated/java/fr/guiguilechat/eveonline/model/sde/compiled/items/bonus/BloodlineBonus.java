
package fr.guiguilechat.eveonline.model.sde.compiled.items.bonus;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.DefaultValue;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Bonus;
import org.yaml.snakeyaml.Yaml;

public class BloodlineBonus
    extends Bonus
{

    /**
     * Factor to scale mining laser durations by.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double MiningDurationMultiplier;
    /**
     * Multiplier to adjust the cost of repairs.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(1.0D)
    public double RepairCostMultiplier;
    /**
     * Typically scales the firing speed of a weapon.  Reducing speed means faster, strangely..
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultValue(1.0D)
    public double SpeedMultiplier;
    /**
     * Scales the accuracy of some targeted weapon.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(1.0D)
    public double AccuracyMultiplier;
    public final static String RESOURCE_PATH = "SDE/bonus/BloodlineBonus.yaml";
    private static LinkedHashMap<String, BloodlineBonus> cache = (null);

    @Override
    public int getGroupId() {
        return  190;
    }

    @Override
    public Class<?> getGroup() {
        return BloodlineBonus.class;
    }

    public static LinkedHashMap<String, BloodlineBonus> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, BloodlineBonus> items;

    }

}
