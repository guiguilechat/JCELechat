
package fr.guiguilechat.eveonline.model.sde.items.types.accessories;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.Accessories;
import org.yaml.snakeyaml.Yaml;

public class Voucher
    extends Accessories
{

    /**
     * Maximum velocity of ship
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0D)
    public double MaxVelocity;
    /**
     * The agility of the object.
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(0.0D)
    public double Agility;
    /**
     * One of the groups of launcher this charge can be loaded into.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int LauncherGroup;
    /**
     * the range in meters for an object to trigger detonation of missile. (own ship excluded)
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DetonationRange;
    /**
     * DO NOT MESS WITH
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0D)
    public double StructureUniformity;
    /**
     * Velocity of the damage cloud created on impact.
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0D)
    public double AoeVelocity;
    /**
     * Size of the damage cloud caused by impact.
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int AoeCloudSize;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int AoeFalloff;
    /**
     * Multiplier to the capacitors recharge rate.
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0D)
    public double CapacitorRechargeRateMultiplier;
    /**
     * EM damage done.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double EmDamage;
    /**
     * Explosive damage done.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double ExplosiveDamage;
    /**
     * Kinetic damage done.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double KineticDamage;
    /**
     * Required skill level for skill 1
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1Level;
    /**
     * Thermal damage done.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double ThermalDamage;
    /**
     * The type ID of the skill that is required.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1;
    /**
     * Required skill level for skill 2
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill2Level;
    /**
     * The type ID of the skill that is required.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill2;
    /**
     * The amount of milliseconds before the object explodes.
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int ExplosionDelay;
    public final static String RESOURCE_PATH = "SDE/items/accessories/Voucher.yaml";
    private static LinkedHashMap<String, Voucher> cache = (null);

    @Override
    public int getGroupId() {
        return  24;
    }

    @Override
    public Class<?> getGroup() {
        return Voucher.class;
    }

    public static LinkedHashMap<String, Voucher> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Voucher.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, Voucher> items;

    }

}
