
package fr.guiguilechat.eveonline.model.sde.items.types.bonus;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.Bonus;
import org.yaml.snakeyaml.Yaml;

public class PhysicalBenefit
    extends Bonus
{

    /**
     * Additional percentage to the characters missile damage multiplier.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double MissileDamageMultiplierBonus;
    /**
     * Multiplier to the agility of an object.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double AgilityMultiplier;
    /**
     * Factor to adjust module cpu need by.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0D)
    public double CpuMultiplier;
    /**
     * Bonus or penalty to the percentage time it takes to research a blueprint.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double BlueprintResearchTimeMultiplierBonus;
    /**
     * Scales the accuracy of some targeted weapon.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0D)
    public double AccuracyMultiplier;
    /**
     * Bonus or penalty to the percentage time it takes to manufacture from a blueprint.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double BlueprintManufactureTimeMultiplierBonus;
    /**
     * The factor by which the amount mined by a mining laser is scaled.
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(1.0D)
    public double MiningAmountMultiplier;
    public final static String RESOURCE_PATH = "SDE/items/bonus/PhysicalBenefit.yaml";
    private static LinkedHashMap<String, PhysicalBenefit> cache = (null);

    @Override
    public int getGroupId() {
        return  191;
    }

    @Override
    public Class<?> getGroup() {
        return PhysicalBenefit.class;
    }

    public static LinkedHashMap<String, PhysicalBenefit> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(PhysicalBenefit.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, PhysicalBenefit> items;

    }

}
