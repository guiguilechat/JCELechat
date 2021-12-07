package fr.guiguilechat.jcelechat.model.sde.types.ship;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultRealValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.AffectedByIndustrialInvulnModule;
import fr.guiguilechat.jcelechat.model.sde.attributes.Agility;
import fr.guiguilechat.jcelechat.model.sde.attributes.AllowedInCapIndustrialMaintenanceBay;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorEmDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorExplosiveDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorHP;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorKineticDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorThermalDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorUniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.BaseWarpSpeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.CapacitorCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.Charge;
import fr.guiguilechat.jcelechat.model.sde.attributes.CpuLoad;
import fr.guiguilechat.jcelechat.model.sde.attributes.CpuOutput;
import fr.guiguilechat.jcelechat.model.sde.attributes.Damage;
import fr.guiguilechat.jcelechat.model.sde.attributes.DroneBandwidth;
import fr.guiguilechat.jcelechat.model.sde.attributes.DroneCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.EmDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.EnergyWarfareResistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ExplosiveDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.FleetHangarCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.FwLpKill;
import fr.guiguilechat.jcelechat.model.sde.attributes.GeneralMiningHoldCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.GfxBoosterID;
import fr.guiguilechat.jcelechat.model.sde.attributes.HasFleetHangars;
import fr.guiguilechat.jcelechat.model.sde.attributes.HasShipMaintenanceBay;
import fr.guiguilechat.jcelechat.model.sde.attributes.HeatAttenuationHi;
import fr.guiguilechat.jcelechat.model.sde.attributes.HeatAttenuationLow;
import fr.guiguilechat.jcelechat.model.sde.attributes.HeatAttenuationMed;
import fr.guiguilechat.jcelechat.model.sde.attributes.HeatCapacityHi;
import fr.guiguilechat.jcelechat.model.sde.attributes.HeatCapacityLow;
import fr.guiguilechat.jcelechat.model.sde.attributes.HeatCapacityMed;
import fr.guiguilechat.jcelechat.model.sde.attributes.HeatDissipationRateHi;
import fr.guiguilechat.jcelechat.model.sde.attributes.HeatDissipationRateLow;
import fr.guiguilechat.jcelechat.model.sde.attributes.HeatDissipationRateMed;
import fr.guiguilechat.jcelechat.model.sde.attributes.HeatGenerationMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.HiSlots;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hp;
import fr.guiguilechat.jcelechat.model.sde.attributes.IndustrialBonusDroneDamage;
import fr.guiguilechat.jcelechat.model.sde.attributes.IndustrialCommandBonusDroneDamage;
import fr.guiguilechat.jcelechat.model.sde.attributes.IndustrialCommandBonusDroneHitPoints;
import fr.guiguilechat.jcelechat.model.sde.attributes.IndustrialCommandBonusDroneIceHarvestingCycleTime;
import fr.guiguilechat.jcelechat.model.sde.attributes.IndustrialCommandBonusDroneOreMiningYield;
import fr.guiguilechat.jcelechat.model.sde.attributes.IndustrialCommandBonusFuelConsuptionCompactIndustrialCore;
import fr.guiguilechat.jcelechat.model.sde.attributes.IndustrialCommandBonusGeneralMiningHoldCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.IndustrialCommandBonusMiningForemanBurstRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.IndustrialCommandBonusShipCargoCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.JumpFatigueMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.KineticDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.LauncherSlotsLeft;
import fr.guiguilechat.jcelechat.model.sde.attributes.LowSlots;
import fr.guiguilechat.jcelechat.model.sde.attributes.MainColor;
import fr.guiguilechat.jcelechat.model.sde.attributes.Mass;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxGangModules;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxLockedTargets;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxOperationalDistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxOperationalUsers;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxPassengers;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxRangeBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxTargetRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxVelocity;
import fr.guiguilechat.jcelechat.model.sde.attributes.MedSlots;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaLevelOld;
import fr.guiguilechat.jcelechat.model.sde.attributes.PowerLoad;
import fr.guiguilechat.jcelechat.model.sde.attributes.PowerOutput;
import fr.guiguilechat.jcelechat.model.sde.attributes.PowerToSpeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.PropulsionGraphicID;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.RechargeRate;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.RigSize;
import fr.guiguilechat.jcelechat.model.sde.attributes.RigSlots;
import fr.guiguilechat.jcelechat.model.sde.attributes.RoleBonusCD;
import fr.guiguilechat.jcelechat.model.sde.attributes.RoleBonusCommandBurstAoERange;
import fr.guiguilechat.jcelechat.model.sde.attributes.RoleBonusDroneMiningYield;
import fr.guiguilechat.jcelechat.model.sde.attributes.RoleBonusSurveyScannerRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.RoleBonusTractorBeamRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.RoleBonusTractorBeamVelocity;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanGravimetricStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanLadarStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanMagnetometricStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanRadarStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanResolution;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanSpeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldCharge;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldEmDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldExplosiveDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldKineticDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldRechargeRate;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldThermalDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldUniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusICS1;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusICS2;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusICS3;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusICS5;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipMaintenanceBayCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.SignatureRadius;
import fr.guiguilechat.jcelechat.model.sde.attributes.SpecialFuelBayCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.StructureUniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.TechLevel;
import fr.guiguilechat.jcelechat.model.sde.attributes.ThermalDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.TurretSlotsLeft;
import fr.guiguilechat.jcelechat.model.sde.attributes.TypeColorScheme;
import fr.guiguilechat.jcelechat.model.sde.attributes.Uniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.UpgradeCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.UpgradeSlotsLeft;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarpCapacitorNeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarpFactor;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarpSpeedMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.WeaponDisruptionResistance;
import fr.guiguilechat.jcelechat.model.sde.types.Ship;
import org.yaml.snakeyaml.Yaml;

public class IndustrialCommandShip
    extends Ship
{
    /**
     * Tells if this type (ship) can be affected by the Rorqual Invulnerability Module
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int affectedbyindustrialinvulnmodule;
    /**
     * Tells if this type (ship) can be placed in the maintenance bay of a capital industrial ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int allowedincapindustrialmaintenancebay;
    /**
     * The capacity of the fleet hangar.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fleethangarcapacity;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fwlpkill;
    /**
     * Capacity of general mining hold
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int generalminingholdcapacity;
    /**
     * Whether this ship has fleet hangars.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int hasfleethangars;
    /**
     * Indicates whether a ship type has a ship maintenance bay.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int hasshipmaintenancebay;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double heatattenuationhi;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double heatattenuationlow;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double heatattenuationmed;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int industrialbonusdronedamage;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int industrialcommandbonusdronedamage;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int industrialcommandbonusdronehitpoints;
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int industrialcommandbonusdroneiceharvestingcycletime;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int industrialcommandbonusdroneoreminingyield;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int industrialcommandbonusfuelconsuptioncompactindustrialcore;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int industrialcommandbonusgeneralminingholdcapacity;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int industrialcommandbonusminingforemanburstrange;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int industrialcommandbonusshipcargocapacity;
    /**
     * Multiplier for jump fatigue distance
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double jumpfatiguemultiplier;
    /**
     * The main color of a ship type.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maincolor;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(3)
    public int maxgangmodules;
    /**
     * The maximum distance at which the object can be used.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxoperationaldistance;
    /**
     * The maximum number of users that can be present within the operational range of the structure for it to be capable of operation.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxoperationalusers;
    /**
     * Specifies the maximum numbers of passengers that the ship can have
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxpassengers;
    /**
     * Autogenerated skill attribute, maxRangeBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double maxrangebonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rigsize;
    /**
     * The number of rig slots on the ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rigslots;
    /**
     * role bonus for command destroyers
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rolebonuscd;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rolebonuscommandburstaoerange;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rolebonusdroneminingyield;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rolebonussurveyscannerrange;
    /**
     * bonus to range of tractor beams
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rolebonustractorbeamrange;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rolebonustractorbeamvelocity;
    /**
     * The resolution that the vessel can target other objects at.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double scanresolution;
    /**
     * scanning speed in milliseconds
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int scanspeed;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonusics1;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonusics2;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonusics3;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonusics5;
    /**
     * The capacity of the hangar in a ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipmaintenancebaycapacity;
    /**
     * special fuel bay capacity
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int specialfuelbaycapacity;
    /**
     * How many rigs can by fitted to this ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int upgradeslotsleft;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {BaseWarpSpeed.INSTANCE, Damage.INSTANCE, Mass.INSTANCE, ShieldCapacity.INSTANCE, ShieldCharge.INSTANCE, Hp.INSTANCE, ArmorHP.INSTANCE, RigSize.INSTANCE, PowerOutput.INSTANCE, ArmorEmDamageResonance.INSTANCE, LowSlots.INSTANCE, ArmorUniformity.INSTANCE, ArmorExplosiveDamageResonance.INSTANCE, SpecialFuelBayCapacity.INSTANCE, MedSlots.INSTANCE, StructureUniformity.INSTANCE, ArmorKineticDamageResonance.INSTANCE, RoleBonusCommandBurstAoERange.INSTANCE, HiSlots.INSTANCE, ArmorThermalDamageResonance.INSTANCE, PowerLoad.INSTANCE, ShieldEmDamageResonance.INSTANCE, RoleBonusCD.INSTANCE, ShieldExplosiveDamageResonance.INSTANCE, ShipBonusICS5 .INSTANCE, ShieldKineticDamageResonance.INSTANCE, RoleBonusDroneMiningYield.INSTANCE, Charge.INSTANCE, ShieldThermalDamageResonance.INSTANCE, FwLpKill.INSTANCE, PowerToSpeed.INSTANCE, GeneralMiningHoldCapacity.INSTANCE, IndustrialBonusDroneDamage.INSTANCE, WarpFactor.INSTANCE, RequiredSkill1Level.INSTANCE, DroneCapacity.INSTANCE, MaxVelocity.INSTANCE, Capacity.INSTANCE, SignatureRadius.INSTANCE, CpuOutput.INSTANCE, CpuLoad.INSTANCE, ScanResolution.INSTANCE, RechargeRate.INSTANCE, WeaponDisruptionResistance.INSTANCE, Agility.INSTANCE, RoleBonusTractorBeamRange.INSTANCE, ShipBonusICS1 .INSTANCE, MaxTargetRange.INSTANCE, RoleBonusTractorBeamVelocity.INSTANCE, ShipBonusICS2 .INSTANCE, RoleBonusSurveyScannerRange.INSTANCE, ScanSpeed.INSTANCE, WarpSpeedMultiplier.INSTANCE, MaxRangeBonus.INSTANCE, AllowedInCapIndustrialMaintenanceBay.INSTANCE, LauncherSlotsLeft.INSTANCE, TurretSlotsLeft.INSTANCE, UpgradeCapacity.INSTANCE, KineticDamageResonance.INSTANCE, ThermalDamageResonance.INSTANCE, ExplosiveDamageResonance.INSTANCE, RigSlots.INSTANCE, EmDamageResonance.INSTANCE, MetaLevelOld.INSTANCE, MainColor.INSTANCE, MaxPassengers.INSTANCE, UpgradeSlotsLeft.INSTANCE, IndustrialCommandBonusDroneDamage.INSTANCE, IndustrialCommandBonusFuelConsuptionCompactIndustrialCore.INSTANCE, IndustrialCommandBonusMiningForemanBurstRange.INSTANCE, Uniformity.INSTANCE, IndustrialCommandBonusShipCargoCapacity.INSTANCE, HasShipMaintenanceBay.INSTANCE, IndustrialCommandBonusGeneralMiningHoldCapacity.INSTANCE, ShipMaintenanceBayCapacity.INSTANCE, HasFleetHangars.INSTANCE, FleetHangarCapacity.INSTANCE, IndustrialCommandBonusDroneOreMiningYield.INSTANCE, IndustrialCommandBonusDroneIceHarvestingCycleTime.INSTANCE, WarpCapacitorNeed.INSTANCE, HeatCapacityHi.INSTANCE, HeatDissipationRateHi.INSTANCE, AffectedByIndustrialInvulnModule.INSTANCE, Radius.INSTANCE, IndustrialCommandBonusDroneHitPoints.INSTANCE, TechLevel.INSTANCE, ShipBonusICS3 .INSTANCE, HeatDissipationRateMed.INSTANCE, HeatDissipationRateLow.INSTANCE, HeatCapacityMed.INSTANCE, HeatCapacityLow.INSTANCE, JumpFatigueMultiplier.INSTANCE, MaxGangModules.INSTANCE, RequiredSkill1 .INSTANCE, MaxLockedTargets.INSTANCE, HeatGenerationMultiplier.INSTANCE, MaxOperationalDistance.INSTANCE, MaxOperationalUsers.INSTANCE, ScanRadarStrength.INSTANCE, ScanLadarStrength.INSTANCE, ScanMagnetometricStrength.INSTANCE, ScanGravimetricStrength.INSTANCE, PropulsionGraphicID.INSTANCE, ShieldRechargeRate.INSTANCE, CapacitorCapacity.INSTANCE, ShieldUniformity.INSTANCE, TypeColorScheme.INSTANCE, HeatAttenuationHi.INSTANCE, HeatAttenuationMed.INSTANCE, HeatAttenuationLow.INSTANCE, GfxBoosterID.INSTANCE, DroneBandwidth.INSTANCE, EnergyWarfareResistance.INSTANCE })));
    public static final IndustrialCommandShip.MetaGroup METAGROUP = new IndustrialCommandShip.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  2464 :
            {
                return affectedbyindustrialinvulnmodule;
            }
            case  1891 :
            {
                return allowedincapindustrialmaintenancebay;
            }
            case  912 :
            {
                return fleethangarcapacity;
            }
            case  1555 :
            {
                return fwlpkill;
            }
            case  1556 :
            {
                return generalminingholdcapacity;
            }
            case  911 :
            {
                return hasfleethangars;
            }
            case  907 :
            {
                return hasshipmaintenancebay;
            }
            case  1259 :
            {
                return heatattenuationhi;
            }
            case  1262 :
            {
                return heatattenuationlow;
            }
            case  1261 :
            {
                return heatattenuationmed;
            }
            case  2580 :
            {
                return industrialbonusdronedamage;
            }
            case  3203 :
            {
                return industrialcommandbonusdronedamage;
            }
            case  3235 :
            {
                return industrialcommandbonusdronehitpoints;
            }
            case  3222 :
            {
                return industrialcommandbonusdroneiceharvestingcycletime;
            }
            case  3221 :
            {
                return industrialcommandbonusdroneoreminingyield;
            }
            case  3204 :
            {
                return industrialcommandbonusfuelconsuptioncompactindustrialcore;
            }
            case  3212 :
            {
                return industrialcommandbonusgeneralminingholdcapacity;
            }
            case  3205 :
            {
                return industrialcommandbonusminingforemanburstrange;
            }
            case  3211 :
            {
                return industrialcommandbonusshipcargocapacity;
            }
            case  1971 :
            {
                return jumpfatiguemultiplier;
            }
            case  124 :
            {
                return maincolor;
            }
            case  435 :
            {
                return maxgangmodules;
            }
            case  715 :
            {
                return maxoperationaldistance;
            }
            case  716 :
            {
                return maxoperationalusers;
            }
            case  129 :
            {
                return maxpassengers;
            }
            case  351 :
            {
                return maxrangebonus;
            }
            case  1547 :
            {
                return rigsize;
            }
            case  1137 :
            {
                return rigslots;
            }
            case  2064 :
            {
                return rolebonuscd;
            }
            case  2574 :
            {
                return rolebonuscommandburstaoerange;
            }
            case  2578 :
            {
                return rolebonusdroneminingyield;
            }
            case  1359 :
            {
                return rolebonussurveyscannerrange;
            }
            case  1355 :
            {
                return rolebonustractorbeamrange;
            }
            case  1357 :
            {
                return rolebonustractorbeamvelocity;
            }
            case  564 :
            {
                return scanresolution;
            }
            case  79 :
            {
                return scanspeed;
            }
            case  1356 :
            {
                return shipbonusics1;
            }
            case  1358 :
            {
                return shipbonusics2;
            }
            case  2474 :
            {
                return shipbonusics3;
            }
            case  2577 :
            {
                return shipbonusics5;
            }
            case  908 :
            {
                return shipmaintenancebaycapacity;
            }
            case  1549 :
            {
                return specialfuelbaycapacity;
            }
            case  1154 :
            {
                return upgradeslotsleft;
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
    public IMetaGroup<IndustrialCommandShip> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<IndustrialCommandShip>
    {
        public static final String RESOURCE_PATH = "SDE/types/ship/IndustrialCommandShip.yaml";
        private Map<String, IndustrialCommandShip> cache = (null);

        @Override
        public IMetaCategory<? super IndustrialCommandShip> category() {
            return Ship.METACAT;
        }

        @Override
        public int getGroupId() {
            return  941;
        }

        @Override
        public String getName() {
            return "IndustrialCommandShip";
        }

        @Override
        public synchronized Map<String, IndustrialCommandShip> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(IndustrialCommandShip.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, IndustrialCommandShip> types;
        }
    }
}
