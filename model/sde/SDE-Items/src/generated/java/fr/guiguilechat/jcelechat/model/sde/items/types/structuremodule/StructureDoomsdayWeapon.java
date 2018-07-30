package fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.StructureModule;
import org.yaml.snakeyaml.Yaml;

public class StructureDoomsdayWeapon
    extends StructureModule
{
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int CanFitShipType1;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int CanFitShipType2;
    /**
     * The amount of charge used from the capacitor for a module activation.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double CapacitorNeed;
    /**
     * CPU need of module
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Cpu;
    /**
     * The delay in ms until the damage is done to the target. (Allows some FX to be played)
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(10000)
    public int DamageDelayDuration;
    /**
     * Modules with this attribute set to 1 can not be used in deadspace. Modules with this attribute set to 2 can not be used in deadspace even where "disableModuleBlocking" is selected
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DeadspaceUnsafe;
    /**
     * Signifies that this module if activated, will prevent ejection from the ship it is fitted to and extend the log out ship removal timer.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DisallowEarlyDeactivation;
    /**
     * Security status restriction, preventing ships from entering high sec and modules from being activated.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DisallowInHighSec;
    /**
     * If set, this module cannot be activated and made to autorepeat.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DisallowRepeatingActivation;
    /**
     * Length of activation time.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Duration;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int EffectDeactivationDelay;
    /**
     * EM damage done.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double EmDamage;
    /**
     * Explosive damage done.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double ExplosiveDamage;
    /**
     * The maximum hitpoints of an object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Hp;
    /**
     * Kinetic damage done.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double KineticDamage;
    /**
     * Damage lost per target hit
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double LightningWeaponDamageLossTarget;
    /**
     * Number of targets affected by the structure doomsday beam.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int LightningWeaponTargetAmount;
    /**
     * Maximum distance between two possible targets for the structure doomsday.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int LightningWeaponTargetRange;
    /**
     * Maximum modules of same group that can be activated at same time, 0 = no limit, 1 = 1
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MaxGroupActive;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MaxGroupFitted;
    /**
     * Determines the maximum security class that a module can be onlined within. Used for structure modules.
     * 
     *  0=Nullsec
     *  1=Lowsec
     *  2=Highsec
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(2)
    public int OnlineMaxSecurityClass;
    /**
     * current power need
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Power;
    /**
     * Dogma attribute that specifies if the item should have the structure icon or not.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int StructureItemVisualFlag;
    /**
     * Thermal damage done.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double ThermalDamage;
    public final static String RESOURCE_PATH = "SDE/items/structuremodule/StructureDoomsdayWeapon.yaml";
    private static LinkedHashMap<String, StructureDoomsdayWeapon> cache = (null);

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  1302 :
            {
                return CanFitShipType1;
            }
            case  1303 :
            {
                return CanFitShipType2;
            }
            case  6 :
            {
                return CapacitorNeed;
            }
            case  50 :
            {
                return Cpu;
            }
            case  1839 :
            {
                return DamageDelayDuration;
            }
            case  801 :
            {
                return DeadspaceUnsafe;
            }
            case  906 :
            {
                return DisallowEarlyDeactivation;
            }
            case  1970 :
            {
                return DisallowInHighSec;
            }
            case  1014 :
            {
                return DisallowRepeatingActivation;
            }
            case  73 :
            {
                return Duration;
            }
            case  1579 :
            {
                return EffectDeactivationDelay;
            }
            case  114 :
            {
                return EmDamage;
            }
            case  116 :
            {
                return ExplosiveDamage;
            }
            case  9 :
            {
                return Hp;
            }
            case  117 :
            {
                return KineticDamage;
            }
            case  2106 :
            {
                return LightningWeaponDamageLossTarget;
            }
            case  2104 :
            {
                return LightningWeaponTargetAmount;
            }
            case  2105 :
            {
                return LightningWeaponTargetRange;
            }
            case  763 :
            {
                return MaxGroupActive;
            }
            case  1544 :
            {
                return MaxGroupFitted;
            }
            case  2581 :
            {
                return OnlineMaxSecurityClass;
            }
            case  30 :
            {
                return Power;
            }
            case  2334 :
            {
                return StructureItemVisualFlag;
            }
            case  118 :
            {
                return ThermalDamage;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public int getGroupId() {
        return  1333;
    }

    @Override
    public Class<?> getGroup() {
        return StructureDoomsdayWeapon.class;
    }

    public static synchronized LinkedHashMap<String, StructureDoomsdayWeapon> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(StructureDoomsdayWeapon.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, StructureDoomsdayWeapon> items;
    }
}
