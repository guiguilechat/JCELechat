
package fr.guiguilechat.eveonline.model.sde.items.types.module;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.Module;
import org.yaml.snakeyaml.Yaml;

public class SiegeModule
    extends Module
{

    /**
     * Torpedo Velocity Bonus Percentage
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int SiegeTorpedoVelocityBonus;
    /**
     * XL Launcher ROF Bonus Percentage
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int SiegeLauncherROFBonus;
    /**
     * Missile Damage Bonus Percentage
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int SiegeMissileDamageBonus;
    /**
     * Turret Damage Bonus Percentage
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int SiegeTurretDamageBonus;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ScanGravimetricStrengthPercent;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ScanLadarStrengthPercent;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ScanMagnetometricStrengthPercent;
    /**
     * The amount of charge used from the capacitor for a module activation.
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double CapacitorNeed;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ScanRadarStrengthPercent;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MaxGroupFitted;
    /**
     * Signifies that this module if activated, will prevent ejection from the ship it is fitted to and extend the log out ship removal timer.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DisallowEarlyDeactivation;
    /**
     * Multiplies EM damage taken by Armor. 
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0D)
    public double ArmorEmDamageResonance;
    /**
     * Multiplies EXPLOSIVE damage taken by Armor. 
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0D)
    public double ArmorExplosiveDamageResonance;
    /**
     * Multiplies KINETIC damage taken by Armor. 
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0D)
    public double ArmorKineticDamageResonance;
    /**
     * Multiplies THERMAL damage taken by Armor. 
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0D)
    public double ArmorThermalDamageResonance;
    /**
     * If module is offensive should it deactivate on disconnect. Default to 1
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int DeactivateIfOffensive;
    /**
     * Multiplies EM damage taken by shield
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0D)
    public double ShieldEmDamageResonance;
    /**
     * Multiplies EXPLOSIVE damage taken by Armor. 
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0D)
    public double ShieldExplosiveDamageResonance;
    /**
     * Multiplies KINETIC damage taken by Armor. 
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0D)
    public double ShieldKineticDamageResonance;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int CanFitShipGroup01;
    /**
     * Multiplies THERMAL damage taken by Shield. 
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0D)
    public double ShieldThermalDamageResonance;
    /**
     * Factor by which topspeed increases.
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(1.0D)
    public double SpeedFactor;
    /**
     * Required skill level for skill 1
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1Level;
    /**
     * Required skill level for skill 2
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill2Level;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int IndustrialCoreBonusDroneDamageHP;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int IndustrialCoreBonusDroneVelocity;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int IndustrialCoreBonusDroneMining;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int IndustrialCoreBonusDroneIceHarvesting;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int IndustrialCoreBonusMiningBurstStrength;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int IndustrialCoreBonusCommandBurstRange;
    /**
     * current power need
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Power;
    /**
     * Autogenerated skill attribute, missileVelocityBonus 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double MissileVelocityBonus;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double ShieldBoostMultiplier;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double RemoteRepairImpedanceBonus;
    /**
     * Bonus attribute for capacitor need of EW and propulsion jamming.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int EwCapacitorNeedBonus;
    /**
     * If this ship attribute is NOT 0 then they will be prevented from activating the structure tethering.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DisallowTethering;
    /**
     * 
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int SiegeRemoteLogisticsDurationBonus;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int SiegeRemoteLogisticsAmountBonus;
    /**
     * Armor Repairer / Shield Booster Duration Bonus
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int SiegeLocalLogisticsDurationBonus;
    /**
     * Armor Repairer / Shield Booster Amount Bonus
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int SiegeLocalLogisticsAmountBonus;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int SiegeRemoteLogisticsRangeBonus;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int IndustrialCoreRemoteLogisticsRangeBonus;
    /**
     * 
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int IndustrialCoreRemoteLogisticsDurationBonus;
    /**
     * 
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int IndustrialCoreLocalLogisticsDurationBonus;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int SensorDampenerResistanceBonus;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int IndustrialCoreLocalLogisticsAmountBonus;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RemoteAssistanceImpedanceBonus;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double WeaponDisruptionResistanceBonus;
    /**
     * If this ship attribute is NOT 0 then they will be prevented from docking in stations or structures.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DisallowDocking;
    /**
     * CPU need of module
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double Cpu;
    /**
     * Improves the targeting time of ships by boosting the Scan Resolution.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double ScanResolutionMultiplier;
    /**
     * The type ID of the skill that is required.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1;
    /**
     * The type ID of the skill that is required.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill2;
    /**
     * Mass multiplier
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int SiegeMassMultiplier;
    /**
     * Maximum number of locked targets that the character or their ships electronics can handle at any given time.  Both have individual limits which apply separately.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MaxLockedTargets;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ActivationBlockedStrenght;
    /**
     * Length of activation time.
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double Duration;
    /**
     * The type of resource needed to be consumed for each activation cycle of this structure.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ConsumptionType;
    /**
     * The amount of the given resource type needed to be consumed for each activation cycle of this structure.
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ConsumptionQuantity;
    /**
     * Resistance to ECM. 0 gives Immunity.
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0D)
    public double ECMResistance;
    /**
     * 
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0D)
    public double HullEmDamageResonance;
    /**
     * 
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0D)
    public double HullExplosiveDamageResonance;
    /**
     * Amount to increase the maximum speed by.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int SpeedBonus;
    /**
     * 
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0D)
    public double HullKineticDamageResonance;
    /**
     * 
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0D)
    public double HullThermalDamageResonance;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int SiegeModeWarpStatus;
    /**
     * commandBonusEffectiveAdd
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int CommandBonusEffectiveAdd;
    /**
     * Stops the module from being activated if the ship is aligning to warp.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DisallowActivateOnWarp;
    /**
     * Autogenerated skill attribute, falloffBonus
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double FalloffBonus;
    /**
     * Autogenerated skill attribute, maxRangeBonus
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double MaxRangeBonus;
    /**
     * droneDamageBonus
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0D)
    public double DroneDamageBonus;
    /**
     * If this module is in use and this attribute is 1, then offensive modules cannot be used on the ship if they apply modifiers for the duration of their effect. If this is put on a ship or NPC with value of 1, then the ship or NPC are immune to offensive modifiers (target jamming, tracking disruption etc.)
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DisallowOffensiveModifiers;
    /**
     * Additional amount of locked targets that can be handled.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MaxLockedTargetsBonus;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int TargetPainterResistanceBonus;
    /**
     * Maximum modules of same group that can be activated at same time, 0 = no limit, 1 = 1
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MaxGroupActive;
    /**
     * Bonus attribute for armor repair amount.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ArmorDamageAmountBonus;
    public final static String RESOURCE_PATH = "SDE/items/module/SiegeModule.yaml";
    private static LinkedHashMap<String, SiegeModule> cache = (null);

    @Override
    public int getGroupId() {
        return  515;
    }

    @Override
    public Class<?> getGroup() {
        return SiegeModule.class;
    }

    public static LinkedHashMap<String, SiegeModule> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(SiegeModule.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, SiegeModule> items;

    }

}
