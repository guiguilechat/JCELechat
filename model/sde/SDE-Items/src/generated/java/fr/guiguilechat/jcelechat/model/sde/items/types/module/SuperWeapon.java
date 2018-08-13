package fr.guiguilechat.jcelechat.model.sde.items.types.module;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.Module;
import org.yaml.snakeyaml.Yaml;

public class SuperWeapon
    extends Module
{
    /**
     * Applied modifier duration
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int BuffDuration;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int CanCloak;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int CanFitShipGroup01;
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
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int CanFitShipType3;
    /**
     * The amount of charge used from the capacitor for a module activation.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double CapacitorNeed;
    /**
     * The amount of the given resource type needed to be consumed for each activation cycle of this structure.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ConsumptionQuantity;
    /**
     * The type of resource needed to be consumed for each activation cycle of this structure.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ConsumptionType;
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
     * Stops the module from being activated if the ship is aligning to warp.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DisallowActivateOnWarp;
    /**
     * If this ship attribute is NOT 0 then they will be prevented from docking in stations or structures.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DisallowDocking;
    /**
     * Signifies that this module if activated, will prevent ejection from the ship it is fitted to and extend the log out ship removal timer.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DisallowEarlyDeactivation;
    /**
     * If set on a charge or module type, will prevent it from being activated in empire space.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DisallowInEmpireSpace;
    /**
     * If set, this module cannot be activated and made to autorepeat.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DisallowRepeatingActivation;
    /**
     * Radius of the AOE Effect
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DoomsdayAOERange;
    /**
     *  1: Fixed Cylinder (Beam)
     *  2: Cylinder moving in an arc (Slash)
     *  3: Fixed Cone
     *  4: Projected Sphere
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DoomsdayAOEShape;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DoomsdayDamageCycleTime;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DoomsdayDamageDuration;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DoomsdayDamageRadius;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DoomsdayEnergyNeutAmount;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DoomsdayEnergyNeutRadius;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DoomsdayEnergyNeutResistanceID;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DoomsdayEnergyNeutSignatureRadius;
    /**
     * Length of Immobility time.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DoomsdayImmobilityDuration;
    /**
     * Length of No Jump Or Cloak time.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DoomsdayNoJumpOrCloakDuration;
    /**
     * Determines whether the maxRange attribute is a fixed length or a maximum length of the effect
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DoomsdayRangeIsFixed;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DoomsdayWarningDuration;
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
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int IsPointTargeted;
    /**
     * Kinetic damage done.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double KineticDamage;
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
     * Distance below which range does not affect the to-hit equation.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int MaxRange;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MaxTypeFitted;
    /**
     * Amount of time that has to be waited after the deactivation of this module until it can be reactivated.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ModuleReactivationDelay;
    /**
     * current power need
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Power;
    /**
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1;
    /**
     * Required skill level for skill 1
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1Level;
    /**
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill2;
    /**
     * Required skill level for skill 2
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill2Level;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int SiegeModeWarpStatus;
    /**
     * Signature Radius is used for turret tracking and scanning.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(100)
    public int SignatureRadius;
    /**
     * Factor by which topspeed increases.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double SpeedFactor;
    /**
     * Thermal damage done.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double ThermalDamage;
    public final static String RESOURCE_PATH = "SDE/items/module/SuperWeapon.yaml";
    private static Map<String, SuperWeapon> cache = (null);

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  2535 :
            {
                return BuffDuration;
            }
            case  1163 :
            {
                return CanCloak;
            }
            case  1298 :
            {
                return CanFitShipGroup01;
            }
            case  1302 :
            {
                return CanFitShipType1;
            }
            case  1303 :
            {
                return CanFitShipType2;
            }
            case  1304 :
            {
                return CanFitShipType3;
            }
            case  6 :
            {
                return CapacitorNeed;
            }
            case  714 :
            {
                return ConsumptionQuantity;
            }
            case  713 :
            {
                return ConsumptionType;
            }
            case  50 :
            {
                return Cpu;
            }
            case  1839 :
            {
                return DamageDelayDuration;
            }
            case  1245 :
            {
                return DisallowActivateOnWarp;
            }
            case  2354 :
            {
                return DisallowDocking;
            }
            case  906 :
            {
                return DisallowEarlyDeactivation;
            }
            case  1074 :
            {
                return DisallowInEmpireSpace;
            }
            case  1014 :
            {
                return DisallowRepeatingActivation;
            }
            case  2279 :
            {
                return DoomsdayAOERange;
            }
            case  2429 :
            {
                return DoomsdayAOEShape;
            }
            case  2265 :
            {
                return DoomsdayDamageCycleTime;
            }
            case  2264 :
            {
                return DoomsdayDamageDuration;
            }
            case  2263 :
            {
                return DoomsdayDamageRadius;
            }
            case  2260 :
            {
                return DoomsdayEnergyNeutAmount;
            }
            case  2259 :
            {
                return DoomsdayEnergyNeutRadius;
            }
            case  2609 :
            {
                return DoomsdayEnergyNeutResistanceID;
            }
            case  2261 :
            {
                return DoomsdayEnergyNeutSignatureRadius;
            }
            case  2428 :
            {
                return DoomsdayImmobilityDuration;
            }
            case  2427 :
            {
                return DoomsdayNoJumpOrCloakDuration;
            }
            case  2430 :
            {
                return DoomsdayRangeIsFixed;
            }
            case  2262 :
            {
                return DoomsdayWarningDuration;
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
            case  2269 :
            {
                return IsPointTargeted;
            }
            case  117 :
            {
                return KineticDamage;
            }
            case  763 :
            {
                return MaxGroupActive;
            }
            case  1544 :
            {
                return MaxGroupFitted;
            }
            case  54 :
            {
                return MaxRange;
            }
            case  2431 :
            {
                return MaxTypeFitted;
            }
            case  669 :
            {
                return ModuleReactivationDelay;
            }
            case  30 :
            {
                return Power;
            }
            case  182 :
            {
                return RequiredSkill1;
            }
            case  277 :
            {
                return RequiredSkill1Level;
            }
            case  183 :
            {
                return RequiredSkill2;
            }
            case  278 :
            {
                return RequiredSkill2Level;
            }
            case  852 :
            {
                return SiegeModeWarpStatus;
            }
            case  552 :
            {
                return SignatureRadius;
            }
            case  20 :
            {
                return SpeedFactor;
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
        return  588;
    }

    @Override
    public Class<?> getGroup() {
        return SuperWeapon.class;
    }

    public static synchronized Map<String, SuperWeapon> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(SuperWeapon.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, SuperWeapon> items;
    }
}
