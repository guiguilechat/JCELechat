package fr.guiguilechat.jcelechat.model.sde.types.module;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.types.Module;
import org.yaml.snakeyaml.Yaml;

public class SiegeModule
    extends Module
{
    /**
     * Resistance to ECM. 0 gives Immunity.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double ECMResistance;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ActivationBlockedStrenght;
    /**
     * Bonus attribute for armor repair amount.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ArmorDamageAmountBonus;
    /**
     * Multiplies EM damage taken by Armor. 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double ArmorEmDamageResonance;
    /**
     * Multiplies EXPLOSIVE damage taken by Armor. 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double ArmorExplosiveDamageResonance;
    /**
     * Multiplies KINETIC damage taken by Armor. 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double ArmorKineticDamageResonance;
    /**
     * Multiplies THERMAL damage taken by Armor. 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double ArmorThermalDamageResonance;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int CanFitShipGroup01;
    /**
     * The amount of charge used from the capacitor for a module activation.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double CapacitorNeed;
    /**
     * commandBonusEffectiveAdd
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int CommandBonusEffectiveAdd;
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
     * If module is offensive should it deactivate on disconnect. Default to 1
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int DeactivateIfOffensive;
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
     * If this module is in use and this attribute is 1, then offensive modules cannot be used on the ship if they apply modifiers for the duration of their effect. If this is put on a ship or NPC with value of 1, then the ship or NPC are immune to offensive modifiers (target jamming, tracking disruption etc.)
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DisallowOffensiveModifiers;
    /**
     * If this ship attribute is NOT 0 then they will be prevented from activating the structure tethering.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DisallowTethering;
    /**
     * droneDamageBonus
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double DroneDamageBonus;
    /**
     * Length of activation time.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Duration;
    /**
     * Bonus attribute for capacitor need of EW and propulsion jamming.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int EwCapacitorNeedBonus;
    /**
     * Autogenerated skill attribute, falloffBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double FalloffBonus;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double HullEmDamageResonance;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double HullExplosiveDamageResonance;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double HullKineticDamageResonance;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double HullThermalDamageResonance;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int IndustrialCoreBonusCommandBurstRange;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int IndustrialCoreBonusDroneDamageHP;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int IndustrialCoreBonusDroneIceHarvesting;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int IndustrialCoreBonusDroneMining;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int IndustrialCoreBonusDroneVelocity;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int IndustrialCoreBonusMiningBurstStrength;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int IndustrialCoreLocalLogisticsAmountBonus;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int IndustrialCoreLocalLogisticsDurationBonus;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int IndustrialCoreRemoteLogisticsDurationBonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int IndustrialCoreRemoteLogisticsRangeBonus;
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
     * Maximum number of locked targets that the character or their ships electronics can handle at any given time.  Both have individual limits which apply separately.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MaxLockedTargets;
    /**
     * Additional amount of locked targets that can be handled.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MaxLockedTargetsBonus;
    /**
     * Autogenerated skill attribute, maxRangeBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double MaxRangeBonus;
    /**
     * Authoring has been moved to FSD
     * The ranking of the module within its tech level
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MetaLevel;
    /**
     * Autogenerated skill attribute, missileVelocityBonus 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double MissileVelocityBonus;
    /**
     * current power need
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Power;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RemoteAssistanceImpedanceBonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double RemoteRepairImpedanceBonus;
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
    public int ScanGravimetricStrengthPercent;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ScanLadarStrengthPercent;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ScanMagnetometricStrengthPercent;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ScanRadarStrengthPercent;
    /**
     * Improves the targeting time of ships by boosting the Scan Resolution.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double ScanResolutionMultiplier;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int SensorDampenerResistanceBonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double ShieldBoostMultiplier;
    /**
     * Multiplies EM damage taken by shield
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double ShieldEmDamageResonance;
    /**
     * Multiplies EXPLOSIVE damage taken by Armor. 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double ShieldExplosiveDamageResonance;
    /**
     * Multiplies KINETIC damage taken by Armor. 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double ShieldKineticDamageResonance;
    /**
     * Multiplies THERMAL damage taken by Shield. 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double ShieldThermalDamageResonance;
    /**
     * Bonus to HAW Missile Launcher Rate of Fire
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int SiegeHAWMissileROFBonus;
    /**
     * XL Launcher ROF Bonus Percentage
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int SiegeLauncherROFBonus;
    /**
     * Armor Repairer / Shield Booster Amount Bonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int SiegeLocalLogisticsAmountBonus;
    /**
     * Armor Repairer / Shield Booster Duration Bonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int SiegeLocalLogisticsDurationBonus;
    /**
     * Mass multiplier
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int SiegeMassMultiplier;
    /**
     * Missile Damage Bonus Percentage
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int SiegeMissileDamageBonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int SiegeModeWarpStatus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int SiegeRemoteLogisticsAmountBonus;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int SiegeRemoteLogisticsDurationBonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int SiegeRemoteLogisticsRangeBonus;
    /**
     * Torpedo Velocity Bonus Percentage
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int SiegeTorpedoVelocityBonus;
    /**
     * Turret Damage Bonus Percentage
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int SiegeTurretDamageBonus;
    /**
     * Amount to increase the maximum speed by.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int SpeedBonus;
    /**
     * Factor by which topspeed increases.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double SpeedFactor;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int TargetPainterResistanceBonus;
    /**
     * Authoring has been moved to FSD
     * Tech level of an item
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int TechLevel;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double WeaponDisruptionResistanceBonus;
    public static final SiegeModule.MetaGroup METAGROUP = new SiegeModule.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  2253 :
            {
                return ECMResistance;
            }
            case  1350 :
            {
                return ActivationBlockedStrenght;
            }
            case  895 :
            {
                return ArmorDamageAmountBonus;
            }
            case  267 :
            {
                return ArmorEmDamageResonance;
            }
            case  268 :
            {
                return ArmorExplosiveDamageResonance;
            }
            case  269 :
            {
                return ArmorKineticDamageResonance;
            }
            case  270 :
            {
                return ArmorThermalDamageResonance;
            }
            case  1298 :
            {
                return CanFitShipGroup01;
            }
            case  6 :
            {
                return CapacitorNeed;
            }
            case  1238 :
            {
                return CommandBonusEffectiveAdd;
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
            case  1934 :
            {
                return DeactivateIfOffensive;
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
            case  872 :
            {
                return DisallowOffensiveModifiers;
            }
            case  2343 :
            {
                return DisallowTethering;
            }
            case  1255 :
            {
                return DroneDamageBonus;
            }
            case  73 :
            {
                return Duration;
            }
            case  1190 :
            {
                return EwCapacitorNeedBonus;
            }
            case  349 :
            {
                return FalloffBonus;
            }
            case  974 :
            {
                return HullEmDamageResonance;
            }
            case  975 :
            {
                return HullExplosiveDamageResonance;
            }
            case  976 :
            {
                return HullKineticDamageResonance;
            }
            case  977 :
            {
                return HullThermalDamageResonance;
            }
            case  2588 :
            {
                return IndustrialCoreBonusCommandBurstRange;
            }
            case  2583 :
            {
                return IndustrialCoreBonusDroneDamageHP;
            }
            case  2586 :
            {
                return IndustrialCoreBonusDroneIceHarvesting;
            }
            case  2585 :
            {
                return IndustrialCoreBonusDroneMining;
            }
            case  2584 :
            {
                return IndustrialCoreBonusDroneVelocity;
            }
            case  2587 :
            {
                return IndustrialCoreBonusMiningBurstStrength;
            }
            case  2607 :
            {
                return IndustrialCoreLocalLogisticsAmountBonus;
            }
            case  2606 :
            {
                return IndustrialCoreLocalLogisticsDurationBonus;
            }
            case  2605 :
            {
                return IndustrialCoreRemoteLogisticsDurationBonus;
            }
            case  2604 :
            {
                return IndustrialCoreRemoteLogisticsRangeBonus;
            }
            case  763 :
            {
                return MaxGroupActive;
            }
            case  1544 :
            {
                return MaxGroupFitted;
            }
            case  192 :
            {
                return MaxLockedTargets;
            }
            case  235 :
            {
                return MaxLockedTargetsBonus;
            }
            case  351 :
            {
                return MaxRangeBonus;
            }
            case  633 :
            {
                return MetaLevel;
            }
            case  547 :
            {
                return MissileVelocityBonus;
            }
            case  30 :
            {
                return Power;
            }
            case  2352 :
            {
                return RemoteAssistanceImpedanceBonus;
            }
            case  2342 :
            {
                return RemoteRepairImpedanceBonus;
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
            case  1027 :
            {
                return ScanGravimetricStrengthPercent;
            }
            case  1028 :
            {
                return ScanLadarStrengthPercent;
            }
            case  1029 :
            {
                return ScanMagnetometricStrengthPercent;
            }
            case  1030 :
            {
                return ScanRadarStrengthPercent;
            }
            case  565 :
            {
                return ScanResolutionMultiplier;
            }
            case  2351 :
            {
                return SensorDampenerResistanceBonus;
            }
            case  548 :
            {
                return ShieldBoostMultiplier;
            }
            case  271 :
            {
                return ShieldEmDamageResonance;
            }
            case  272 :
            {
                return ShieldExplosiveDamageResonance;
            }
            case  273 :
            {
                return ShieldKineticDamageResonance;
            }
            case  274 :
            {
                return ShieldThermalDamageResonance;
            }
            case  2821 :
            {
                return SiegeHAWMissileROFBonus;
            }
            case  2305 :
            {
                return SiegeLauncherROFBonus;
            }
            case  2347 :
            {
                return SiegeLocalLogisticsAmountBonus;
            }
            case  2346 :
            {
                return SiegeLocalLogisticsDurationBonus;
            }
            case  1471 :
            {
                return SiegeMassMultiplier;
            }
            case  2306 :
            {
                return SiegeMissileDamageBonus;
            }
            case  852 :
            {
                return SiegeModeWarpStatus;
            }
            case  2345 :
            {
                return SiegeRemoteLogisticsAmountBonus;
            }
            case  2344 :
            {
                return SiegeRemoteLogisticsDurationBonus;
            }
            case  2348 :
            {
                return SiegeRemoteLogisticsRangeBonus;
            }
            case  2304 :
            {
                return SiegeTorpedoVelocityBonus;
            }
            case  2307 :
            {
                return SiegeTurretDamageBonus;
            }
            case  80 :
            {
                return SpeedBonus;
            }
            case  20 :
            {
                return SpeedFactor;
            }
            case  2424 :
            {
                return TargetPainterResistanceBonus;
            }
            case  422 :
            {
                return TechLevel;
            }
            case  2353 :
            {
                return WeaponDisruptionResistanceBonus;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public IMetaGroup<SiegeModule> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<SiegeModule>
    {
        public static final String RESOURCE_PATH = "SDE/types/module/SiegeModule.yaml";
        private Map<String, SiegeModule> cache = (null);

        @Override
        public IMetaCategory<? super SiegeModule> category() {
            return Module.METACAT;
        }

        @Override
        public int getGroupId() {
            return  515;
        }

        @Override
        public String getName() {
            return "SiegeModule";
        }

        @Override
        public synchronized Map<String, SiegeModule> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(SiegeModule.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, SiegeModule> types;
        }
    }
}
