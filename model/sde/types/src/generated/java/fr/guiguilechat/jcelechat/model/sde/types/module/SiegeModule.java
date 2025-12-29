package fr.guiguilechat.jcelechat.model.sde.types.module;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultRealValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.*;
import fr.guiguilechat.jcelechat.model.sde.types.Module;

public class SiegeModule
    extends Module
{
    /**
     * Resistance to ECM. 0 gives Immunity.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double ecmresistance;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int activationblockedstrenght;
    /**
     * Bonus attribute for armor repair amount.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int armordamageamountbonus;
    /**
     * Multiplies EM damage taken by Armor. 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double armoremdamageresonance;
    /**
     * Multiplies EXPLOSIVE damage taken by Armor. 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double armorexplosivedamageresonance;
    /**
     * Multiplies KINETIC damage taken by Armor. 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double armorkineticdamageresonance;
    /**
     * Multiplies THERMAL damage taken by Armor. 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double armorthermaldamageresonance;
    /**
     * Bastion effect bonus to missile rate of fire
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int bastionmissilerofbonus;
    /**
     * Reduces the cycle time and capacitor cost of Shield Booster and Armor Repairer modules for the bastion module. 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int bastionmodearmorrepairandshieldboostercapdurationbonus;
    /**
     * Bastion effect bonus to turret rate of fire
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int bastionturretrofbonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshipgroup01;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshipgroup02;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshiptype1;
    /**
     * The amount of charge used from the capacitor for a module activation.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double capacitorneed;
    /**
     * commandBonusEffectiveAdd
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int commandbonuseffectiveadd;
    /**
     * The amount of the given resource type needed to be consumed for each activation cycle of this structure.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int consumptionquantity;
    /**
     * The type of resource needed to be consumed for each activation cycle of this structure.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int consumptiontype;
    /**
     * CPU need of module
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double cpu;
    /**
     * If module is offensive should it deactivate on disconnect. Default to 1
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int deactivateifoffensive;
    /**
     * Stops the module from being activated if the ship is aligning to warp.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int disallowactivateonwarp;
    /**
     * If this ship attribute is NOT 0 then they will be prevented from docking in stations or structures.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int disallowdocking;
    /**
     * Signifies that this module if activated, will prevent ejection from the ship it is fitted to and extend the log out ship removal timer.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int disallowearlydeactivation;
    /**
     * If this ship attribute is NOT 0 then they will be prevented from activating the structure tethering.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int disallowtethering;
    /**
     * droneDamageBonus
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double dronedamagebonus;
    /**
     * Length of activation time.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double duration;
    /**
     * Bonus attribute for capacitor need of EW and propulsion jamming.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ewcapacitorneedbonus;
    /**
     * Autogenerated skill attribute, falloffBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double falloffbonus;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double hullemdamageresonance;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double hullexplosivedamageresonance;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double hullkineticdamageresonance;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double hullthermaldamageresonance;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int industrialcorebonuscommandburstrange;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int industrialcorebonusdronedamagehp;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int industrialcorebonusdroneiceharvesting;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int industrialcorebonusdronemining;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double industrialcorebonusdronevelocity;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double industrialcorebonusminingburststrength;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int industrialcorelocallogisticsamountbonus;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int industrialcorelocallogisticsdurationbonus;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int industrialcoreremotelogisticsdurationbonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int industrialcoreremotelogisticsrangebonus;
    /**
     * Maximum modules of same group that can be activated at same time, 0 = no limit, 1 = 1
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxgroupactive;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxgroupfitted;
    /**
     * Additional amount of locked targets that can be handled.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxlockedtargetsbonus;
    /**
     * Autogenerated skill attribute, maxRangeBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double maxrangebonus;
    /**
     * Autogenerated skill attribute, missileVelocityBonus 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double missilevelocitybonus;
    /**
     * current power need
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int power;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int remoteassistanceimpedancebonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double remoterepairimpedancebonus;
    /**
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill1;
    /**
     * Required skill level for skill 1
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill1level;
    /**
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill2;
    /**
     * Required skill level for skill 2
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill2level;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int scangravimetricstrengthpercent;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int scanladarstrengthpercent;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int scanmagnetometricstrengthpercent;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int scanradarstrengthpercent;
    /**
     * Improves the targeting time of ships by boosting the Scan Resolution.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double scanresolutionmultiplier;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int sensordampenerresistancebonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shieldboostmultiplier;
    /**
     * Multiplies EM damage taken by shield
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double shieldemdamageresonance;
    /**
     * Multiplies EXPLOSIVE damage taken by Armor. 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double shieldexplosivedamageresonance;
    /**
     * Multiplies KINETIC damage taken by Armor. 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double shieldkineticdamageresonance;
    /**
     * Multiplies THERMAL damage taken by Shield. 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double shieldthermaldamageresonance;
    /**
     * Bonus to HAW Missile Launcher Rate of Fire
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int siegehawmissilerofbonus;
    /**
     * XL Launcher ROF Bonus Percentage
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int siegelauncherrofbonus;
    /**
     * Armor Repairer / Shield Booster Amount Bonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int siegelocallogisticsamountbonus;
    /**
     * Armor Repairer / Shield Booster Duration Bonus
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int siegelocallogisticsdurationbonus;
    /**
     * Mass multiplier
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int siegemassmultiplier;
    /**
     * Siege Missile Damage Bonus Percentage
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int siegemissiledamagebonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int siegemodewarpstatus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int siegeremotelogisticsamountbonus;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int siegeremotelogisticsdurationbonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int siegeremotelogisticsrangebonus;
    /**
     * Torpedo Velocity Bonus Percentage
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int siegetorpedovelocitybonus;
    /**
     * Turret Damage Bonus Percentage
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int siegeturretdamagebonus;
    /**
     * Amount to increase the maximum speed by.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int speedbonus;
    /**
     * Factor by which topspeed increases.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double speedfactor;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(-99)
    public int speedfactorfloor;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int weapondisruptionresistancebonus;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {SiegeTorpedoVelocityBonus.INSTANCE, SiegeLauncherROFBonus.INSTANCE, SiegeMissileDamageBonus.INSTANCE, SiegeTurretDamageBonus.INSTANCE, ScanGravimetricStrengthPercent.INSTANCE, ScanLadarStrengthPercent.INSTANCE, SiegeHAWMissileROFBonus.INSTANCE, ScanMagnetometricStrengthPercent.INSTANCE, CapacitorNeed.INSTANCE, ScanRadarStrengthPercent.INSTANCE, MaxGroupFitted.INSTANCE, Hp.INSTANCE, DisallowEarlyDeactivation.INSTANCE, ArmorEmDamageResonance.INSTANCE, ArmorExplosiveDamageResonance.INSTANCE, ArmorKineticDamageResonance.INSTANCE, ArmorThermalDamageResonance.INSTANCE, DeactivateIfOffensive.INSTANCE, ShieldEmDamageResonance.INSTANCE, ShieldExplosiveDamageResonance.INSTANCE, ShieldKineticDamageResonance.INSTANCE, CanFitShipGroup01 .INSTANCE, ShieldThermalDamageResonance.INSTANCE, CanFitShipGroup02 .INSTANCE, SpeedFactor.INSTANCE, RequiredSkill1Level.INSTANCE, CanFitShipType1 .INSTANCE, RequiredSkill2Level.INSTANCE, IndustrialCoreBonusDroneDamageHP.INSTANCE, IndustrialCoreBonusDroneVelocity.INSTANCE, IndustrialCoreBonusDroneMining.INSTANCE, IndustrialCoreBonusDroneIceHarvesting.INSTANCE, IndustrialCoreBonusMiningBurstStrength.INSTANCE, IndustrialCoreBonusCommandBurstRange.INSTANCE, Power.INSTANCE, MissileVelocityBonus.INSTANCE, ShieldBoostMultiplier.INSTANCE, BastionMissileROFBonus.INSTANCE, BastionTurretROFBonus.INSTANCE, RemoteRepairImpedanceBonus.INSTANCE, EwCapacitorNeedBonus.INSTANCE, TechLevel.INSTANCE, DisallowTethering.INSTANCE, SiegeRemoteLogisticsDurationBonus.INSTANCE, SiegeRemoteLogisticsAmountBonus.INSTANCE, SiegeLocalLogisticsDurationBonus.INSTANCE, SiegeLocalLogisticsAmountBonus.INSTANCE, SiegeRemoteLogisticsRangeBonus.INSTANCE, IndustrialCoreRemoteLogisticsRangeBonus.INSTANCE, IndustrialCoreRemoteLogisticsDurationBonus.INSTANCE, IndustrialCoreLocalLogisticsDurationBonus.INSTANCE, SensorDampenerResistanceBonus.INSTANCE, IndustrialCoreLocalLogisticsAmountBonus.INSTANCE, RemoteAssistanceImpedanceBonus.INSTANCE, WeaponDisruptionResistanceBonus.INSTANCE, Cpu.INSTANCE, DisallowDocking.INSTANCE, ScanResolutionMultiplier.INSTANCE, RequiredSkill1 .INSTANCE, RequiredSkill2 .INSTANCE, SiegeMassMultiplier.INSTANCE, ActivationBlockedStrenght.INSTANCE, Duration.INSTANCE, ConsumptionType.INSTANCE, ConsumptionQuantity.INSTANCE, BastionModeArmorRepairAndShieldBoosterCapDurationBonus.INSTANCE, ECMResistance.INSTANCE, HullEmDamageResonance.INSTANCE, HullExplosiveDamageResonance.INSTANCE, SpeedBonus.INSTANCE, HullKineticDamageResonance.INSTANCE, HullThermalDamageResonance.INSTANCE, SiegeModeWarpStatus.INSTANCE, CommandBonusEffectiveAdd.INSTANCE, SpeedFactorFloor.INSTANCE, DisallowActivateOnWarp.INSTANCE, FalloffBonus.INSTANCE, MaxRangeBonus.INSTANCE, DroneDamageBonus.INSTANCE, MaxLockedTargetsBonus.INSTANCE, MetaLevelOld.INSTANCE, MaxGroupActive.INSTANCE, ArmorDamageAmountBonus.INSTANCE })));
    public static final SiegeModule.MetaGroup METAGROUP = new SiegeModule.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  2253 :
            {
                return ecmresistance;
            }
            case  1350 :
            {
                return activationblockedstrenght;
            }
            case  895 :
            {
                return armordamageamountbonus;
            }
            case  267 :
            {
                return armoremdamageresonance;
            }
            case  268 :
            {
                return armorexplosivedamageresonance;
            }
            case  269 :
            {
                return armorkineticdamageresonance;
            }
            case  270 :
            {
                return armorthermaldamageresonance;
            }
            case  3108 :
            {
                return bastionmissilerofbonus;
            }
            case  5964 :
            {
                return bastionmodearmorrepairandshieldboostercapdurationbonus;
            }
            case  3109 :
            {
                return bastionturretrofbonus;
            }
            case  1298 :
            {
                return canfitshipgroup01;
            }
            case  1299 :
            {
                return canfitshipgroup02;
            }
            case  1302 :
            {
                return canfitshiptype1;
            }
            case  6 :
            {
                return capacitorneed;
            }
            case  1238 :
            {
                return commandbonuseffectiveadd;
            }
            case  714 :
            {
                return consumptionquantity;
            }
            case  713 :
            {
                return consumptiontype;
            }
            case  50 :
            {
                return cpu;
            }
            case  1934 :
            {
                return deactivateifoffensive;
            }
            case  1245 :
            {
                return disallowactivateonwarp;
            }
            case  2354 :
            {
                return disallowdocking;
            }
            case  906 :
            {
                return disallowearlydeactivation;
            }
            case  2343 :
            {
                return disallowtethering;
            }
            case  1255 :
            {
                return dronedamagebonus;
            }
            case  73 :
            {
                return duration;
            }
            case  1190 :
            {
                return ewcapacitorneedbonus;
            }
            case  349 :
            {
                return falloffbonus;
            }
            case  974 :
            {
                return hullemdamageresonance;
            }
            case  975 :
            {
                return hullexplosivedamageresonance;
            }
            case  976 :
            {
                return hullkineticdamageresonance;
            }
            case  977 :
            {
                return hullthermaldamageresonance;
            }
            case  2588 :
            {
                return industrialcorebonuscommandburstrange;
            }
            case  2583 :
            {
                return industrialcorebonusdronedamagehp;
            }
            case  2586 :
            {
                return industrialcorebonusdroneiceharvesting;
            }
            case  2585 :
            {
                return industrialcorebonusdronemining;
            }
            case  2584 :
            {
                return industrialcorebonusdronevelocity;
            }
            case  2587 :
            {
                return industrialcorebonusminingburststrength;
            }
            case  2607 :
            {
                return industrialcorelocallogisticsamountbonus;
            }
            case  2606 :
            {
                return industrialcorelocallogisticsdurationbonus;
            }
            case  2605 :
            {
                return industrialcoreremotelogisticsdurationbonus;
            }
            case  2604 :
            {
                return industrialcoreremotelogisticsrangebonus;
            }
            case  763 :
            {
                return maxgroupactive;
            }
            case  1544 :
            {
                return maxgroupfitted;
            }
            case  235 :
            {
                return maxlockedtargetsbonus;
            }
            case  351 :
            {
                return maxrangebonus;
            }
            case  547 :
            {
                return missilevelocitybonus;
            }
            case  30 :
            {
                return power;
            }
            case  2352 :
            {
                return remoteassistanceimpedancebonus;
            }
            case  2342 :
            {
                return remoterepairimpedancebonus;
            }
            case  182 :
            {
                return requiredskill1;
            }
            case  277 :
            {
                return requiredskill1level;
            }
            case  183 :
            {
                return requiredskill2;
            }
            case  278 :
            {
                return requiredskill2level;
            }
            case  1027 :
            {
                return scangravimetricstrengthpercent;
            }
            case  1028 :
            {
                return scanladarstrengthpercent;
            }
            case  1029 :
            {
                return scanmagnetometricstrengthpercent;
            }
            case  1030 :
            {
                return scanradarstrengthpercent;
            }
            case  565 :
            {
                return scanresolutionmultiplier;
            }
            case  2351 :
            {
                return sensordampenerresistancebonus;
            }
            case  548 :
            {
                return shieldboostmultiplier;
            }
            case  271 :
            {
                return shieldemdamageresonance;
            }
            case  272 :
            {
                return shieldexplosivedamageresonance;
            }
            case  273 :
            {
                return shieldkineticdamageresonance;
            }
            case  274 :
            {
                return shieldthermaldamageresonance;
            }
            case  2821 :
            {
                return siegehawmissilerofbonus;
            }
            case  2305 :
            {
                return siegelauncherrofbonus;
            }
            case  2347 :
            {
                return siegelocallogisticsamountbonus;
            }
            case  2346 :
            {
                return siegelocallogisticsdurationbonus;
            }
            case  1471 :
            {
                return siegemassmultiplier;
            }
            case  2306 :
            {
                return siegemissiledamagebonus;
            }
            case  852 :
            {
                return siegemodewarpstatus;
            }
            case  2345 :
            {
                return siegeremotelogisticsamountbonus;
            }
            case  2344 :
            {
                return siegeremotelogisticsdurationbonus;
            }
            case  2348 :
            {
                return siegeremotelogisticsrangebonus;
            }
            case  2304 :
            {
                return siegetorpedovelocitybonus;
            }
            case  2307 :
            {
                return siegeturretdamagebonus;
            }
            case  80 :
            {
                return speedbonus;
            }
            case  20 :
            {
                return speedfactor;
            }
            case  2266 :
            {
                return speedfactorfloor;
            }
            case  2353 :
            {
                return weapondisruptionresistancebonus;
            }
            default:
            {
                return super.valueSet((attribute));
            }
        }
    }

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<SiegeModule> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<SiegeModule>
    {
        public static final String RESOURCE_PATH = "SDE/types/module/SiegeModule.yaml";
        private Map<Integer, SiegeModule> cache = (null);

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
        public synchronized Map<Integer, SiegeModule> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(SiegeModule.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    LoaderOptions options = new LoaderOptions();
                    options.setCodePointLimit(Integer.MAX_VALUE);
                    cache = new Yaml(options).loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<Integer, SiegeModule> types;
        }
    }
}
