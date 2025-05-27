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
import fr.guiguilechat.jcelechat.model.sde.attributes.CargoScanResistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.Charge;
import fr.guiguilechat.jcelechat.model.sde.attributes.CpuLoad;
import fr.guiguilechat.jcelechat.model.sde.attributes.CpuOutput;
import fr.guiguilechat.jcelechat.model.sde.attributes.Damage;
import fr.guiguilechat.jcelechat.model.sde.attributes.DroneBandwidth;
import fr.guiguilechat.jcelechat.model.sde.attributes.DroneCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.EmDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.EnergyWarfareResistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ExplosiveDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.FwLpKill;
import fr.guiguilechat.jcelechat.model.sde.attributes.GallenteIndustrialBonusIceHoldCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.GallenteIndustrialBonusMiningHoldCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.GeneralMiningHoldCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.GfxBoosterID;
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
import fr.guiguilechat.jcelechat.model.sde.attributes.IsCarrierJumpConduitPassenger;
import fr.guiguilechat.jcelechat.model.sde.attributes.JumpFatigueMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.KineticDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.LauncherSlotsLeft;
import fr.guiguilechat.jcelechat.model.sde.attributes.LowSlots;
import fr.guiguilechat.jcelechat.model.sde.attributes.MainColor;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxDirectionalScanRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxLockedTargets;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxPassengers;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxTargetRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxVelocity;
import fr.guiguilechat.jcelechat.model.sde.attributes.MedSlots;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaLevelOld;
import fr.guiguilechat.jcelechat.model.sde.attributes.MinmatarIndustrialBonusGasHoldCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.PowerLoad;
import fr.guiguilechat.jcelechat.model.sde.attributes.PowerOutput;
import fr.guiguilechat.jcelechat.model.sde.attributes.PowerToSpeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.PropulsionGraphicID;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.RechargeRate;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill2;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill2Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill3;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill3Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.RigSize;
import fr.guiguilechat.jcelechat.model.sde.attributes.RigSlots;
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
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusAI;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusAI2;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusCI;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusCI2;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusGI;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusGI2;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusMI;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusMI2;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusOreIndustrial1;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusOreIndustrial2;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusUH1;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusUH2;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipRoleBonusWarpSpeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.SignatureRadius;
import fr.guiguilechat.jcelechat.model.sde.attributes.SpecialAmmoHoldCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.SpecialColonyResourcesHoldCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.SpecialCommandCenterHoldCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.SpecialGasHoldCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.SpecialIceHoldCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.SpecialMineralHoldCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.SpecialPlanetaryCommoditiesHoldCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.SpecialQuafeHoldCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.StasisWebifierResistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.StructureUniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.TechLevel;
import fr.guiguilechat.jcelechat.model.sde.attributes.ThermalDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.TurretSlotsLeft;
import fr.guiguilechat.jcelechat.model.sde.attributes.TypeColorScheme;
import fr.guiguilechat.jcelechat.model.sde.attributes.Uniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.UpgradeCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.UpgradeSlotsLeft;
import fr.guiguilechat.jcelechat.model.sde.attributes.UpwellHaulersMediumMissileFittingBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarpCapacitorNeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarpFactor;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarpSpeedMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.WeaponDisruptionResistance;
import fr.guiguilechat.jcelechat.model.sde.types.Ship;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class Hauler
    extends Ship
{
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double upwellhaulersmediummissilefittingbonus;
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
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fwlpkill;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int gallenteindustrialbonusiceholdcapacity;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int gallenteindustrialbonusminingholdcapacity;
    /**
     * Capacity of general mining hold
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int generalminingholdcapacity;
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
     * tbd
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int hislots;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int industrialbonusdronedamage;
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(1)
    public int iscarrierjumpconduitpassenger;
    /**
     * Multiplier for jump fatigue distance
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double jumpfatiguemultiplier;
    /**
     * The number of low power slots on the ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int lowslots;
    /**
     * The main color of a ship type.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maincolor;
    /**
     * Specifies the maximum numbers of passengers that the ship can have
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxpassengers;
    /**
     * tbd
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int medslots;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int minmatarindustrialbonusgasholdcapacity;
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
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill3;
    /**
     * Required skill level for skill 3
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill3level;
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
    @DefaultIntValue(5)
    public int shipbonusai;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shipbonusai2;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(5)
    public int shipbonusci;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shipbonusci2;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(5)
    public int shipbonusgi;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shipbonusgi2;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(5)
    public int shipbonusmi;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shipbonusmi2;
    /**
     * Bonus 1 for ORE Industrials
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonusoreindustrial1;
    /**
     * Bonus 2 for ORE Industrials
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonusoreindustrial2;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonusuh1;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonusuh2;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shiprolebonuswarpspeed;
    /**
     * special ammo hold capacity
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int specialammoholdcapacity;
    /**
     * Infrastructure Hold Capacity
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int specialcolonyresourcesholdcapacity;
    /**
     * Capacity of CC-only hold
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int specialcommandcenterholdcapacity;
    /**
     * Capacity of gas-only hold
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int specialgasholdcapacity;
    /**
     * Capacity of ice-only hold
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int specialiceholdcapacity;
    /**
     * Capacity of mineral-only hold
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int specialmineralholdcapacity;
    /**
     * Capacity of Planetary Commodities hold
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int specialplanetarycommoditiesholdcapacity;
    /**
     * Capacity of Quafe hold
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int specialquafeholdcapacity;
    /**
     * The value of this attribute is a graphicsID which controls the color scheme of this type. It is used to apply said color scheme to items of other types whose gfx representation is tied in with the attribute holder. Example: Turrets on ships.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int typecolorscheme;
    /**
     * Attribute on ships used for ship upgrades
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int upgradecapacity;
    /**
     * How many rigs can by fitted to this ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int upgradeslotsleft;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {BaseWarpSpeed.INSTANCE, Damage.INSTANCE, ShieldCapacity.INSTANCE, ShieldCharge.INSTANCE, Hp.INSTANCE, ArmorHP.INSTANCE, RigSize.INSTANCE, PowerOutput.INSTANCE, ArmorEmDamageResonance.INSTANCE, LowSlots.INSTANCE, SpecialQuafeHoldCapacity.INSTANCE, ArmorUniformity.INSTANCE, ArmorExplosiveDamageResonance.INSTANCE, MedSlots.INSTANCE, StructureUniformity.INSTANCE, ArmorKineticDamageResonance.INSTANCE, HiSlots.INSTANCE, SpecialColonyResourcesHoldCapacity.INSTANCE, ArmorThermalDamageResonance.INSTANCE, ShipBonusUH1 .INSTANCE, PowerLoad.INSTANCE, ShieldEmDamageResonance.INSTANCE, ShipBonusUH2 .INSTANCE, ShieldExplosiveDamageResonance.INSTANCE, ShieldKineticDamageResonance.INSTANCE, Charge.INSTANCE, ShieldThermalDamageResonance.INSTANCE, FwLpKill.INSTANCE, PowerToSpeed.INSTANCE, IndustrialBonusDroneDamage.INSTANCE, GeneralMiningHoldCapacity.INSTANCE, RequiredSkill1Level.INSTANCE, SpecialGasHoldCapacity.INSTANCE, WarpFactor.INSTANCE, SpecialMineralHoldCapacity.INSTANCE, RequiredSkill2Level.INSTANCE, RequiredSkill3Level.INSTANCE, DroneCapacity.INSTANCE, SpecialAmmoHoldCapacity.INSTANCE, MaxVelocity.INSTANCE, Capacity.INSTANCE, SignatureRadius.INSTANCE, ShipBonusAI2 .INSTANCE, ShipBonusCI2 .INSTANCE, ShipBonusGI2 .INSTANCE, ShipBonusMI2 .INSTANCE, CpuOutput.INSTANCE, CpuLoad.INSTANCE, IsCarrierJumpConduitPassenger.INSTANCE, ScanResolution.INSTANCE, RechargeRate.INSTANCE, SpecialIceHoldCapacity.INSTANCE, WeaponDisruptionResistance.INSTANCE, StasisWebifierResistance.INSTANCE, Agility.INSTANCE, UpwellHaulersMediumMissileFittingBonus.INSTANCE, MaxTargetRange.INSTANCE, ScanSpeed.INSTANCE, GallenteIndustrialBonusIceHoldCapacity.INSTANCE, WarpSpeedMultiplier.INSTANCE, AllowedInCapIndustrialMaintenanceBay.INSTANCE, LauncherSlotsLeft.INSTANCE, TurretSlotsLeft.INSTANCE, UpgradeCapacity.INSTANCE, KineticDamageResonance.INSTANCE, SpecialCommandCenterHoldCapacity.INSTANCE, ThermalDamageResonance.INSTANCE, ExplosiveDamageResonance.INSTANCE, RigSlots.INSTANCE, EmDamageResonance.INSTANCE, SpecialPlanetaryCommoditiesHoldCapacity.INSTANCE, MetaLevelOld.INSTANCE, MainColor.INSTANCE, MaxPassengers.INSTANCE, UpgradeSlotsLeft.INSTANCE, ShipBonusOreIndustrial1 .INSTANCE, ShipBonusOreIndustrial2 .INSTANCE, Uniformity.INSTANCE, MinmatarIndustrialBonusGasHoldCapacity.INSTANCE, WarpCapacitorNeed.INSTANCE, HeatCapacityHi.INSTANCE, HeatDissipationRateHi.INSTANCE, AffectedByIndustrialInvulnModule.INSTANCE, Radius.INSTANCE, MaxDirectionalScanRange.INSTANCE, TechLevel.INSTANCE, GallenteIndustrialBonusMiningHoldCapacity.INSTANCE, HeatDissipationRateMed.INSTANCE, HeatDissipationRateLow.INSTANCE, HeatCapacityMed.INSTANCE, HeatCapacityLow.INSTANCE, JumpFatigueMultiplier.INSTANCE, RequiredSkill1 .INSTANCE, RequiredSkill2 .INSTANCE, RequiredSkill3 .INSTANCE, CargoScanResistance.INSTANCE, MaxLockedTargets.INSTANCE, HeatGenerationMultiplier.INSTANCE, ScanRadarStrength.INSTANCE, ScanLadarStrength.INSTANCE, ScanMagnetometricStrength.INSTANCE, ScanGravimetricStrength.INSTANCE, PropulsionGraphicID.INSTANCE, ShieldRechargeRate.INSTANCE, CapacitorCapacity.INSTANCE, ShieldUniformity.INSTANCE, ShipRoleBonusWarpSpeed.INSTANCE, TypeColorScheme.INSTANCE, HeatAttenuationHi.INSTANCE, HeatAttenuationMed.INSTANCE, ShipBonusMI.INSTANCE, HeatAttenuationLow.INSTANCE, ShipBonusAI.INSTANCE, ShipBonusCI.INSTANCE, ShipBonusGI.INSTANCE, GfxBoosterID.INSTANCE, DroneBandwidth.INSTANCE, EnergyWarfareResistance.INSTANCE })));
    public static final Hauler.MetaGroup METAGROUP = new Hauler.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  5705 :
            {
                return upwellhaulersmediummissilefittingbonus;
            }
            case  2464 :
            {
                return affectedbyindustrialinvulnmodule;
            }
            case  1891 :
            {
                return allowedincapindustrialmaintenancebay;
            }
            case  1555 :
            {
                return fwlpkill;
            }
            case  3157 :
            {
                return gallenteindustrialbonusiceholdcapacity;
            }
            case  3241 :
            {
                return gallenteindustrialbonusminingholdcapacity;
            }
            case  1556 :
            {
                return generalminingholdcapacity;
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
            case  14 :
            {
                return hislots;
            }
            case  2580 :
            {
                return industrialbonusdronedamage;
            }
            case  5682 :
            {
                return iscarrierjumpconduitpassenger;
            }
            case  1971 :
            {
                return jumpfatiguemultiplier;
            }
            case  12 :
            {
                return lowslots;
            }
            case  124 :
            {
                return maincolor;
            }
            case  129 :
            {
                return maxpassengers;
            }
            case  13 :
            {
                return medslots;
            }
            case  3210 :
            {
                return minmatarindustrialbonusgasholdcapacity;
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
            case  184 :
            {
                return requiredskill3;
            }
            case  279 :
            {
                return requiredskill3level;
            }
            case  1547 :
            {
                return rigsize;
            }
            case  1137 :
            {
                return rigslots;
            }
            case  564 :
            {
                return scanresolution;
            }
            case  79 :
            {
                return scanspeed;
            }
            case  494 :
            {
                return shipbonusai;
            }
            case  809 :
            {
                return shipbonusai2;
            }
            case  495 :
            {
                return shipbonusci;
            }
            case  811 :
            {
                return shipbonusci2;
            }
            case  496 :
            {
                return shipbonusgi;
            }
            case  813 :
            {
                return shipbonusgi2;
            }
            case  493 :
            {
                return shipbonusmi;
            }
            case  814 :
            {
                return shipbonusmi2;
            }
            case  1669 :
            {
                return shipbonusoreindustrial1;
            }
            case  1670 :
            {
                return shipbonusoreindustrial2;
            }
            case  5647 :
            {
                return shipbonusuh1;
            }
            case  5648 :
            {
                return shipbonusuh2;
            }
            case  2789 :
            {
                return shiprolebonuswarpspeed;
            }
            case  1573 :
            {
                return specialammoholdcapacity;
            }
            case  5646 :
            {
                return specialcolonyresourcesholdcapacity;
            }
            case  1646 :
            {
                return specialcommandcenterholdcapacity;
            }
            case  1557 :
            {
                return specialgasholdcapacity;
            }
            case  3136 :
            {
                return specialiceholdcapacity;
            }
            case  1558 :
            {
                return specialmineralholdcapacity;
            }
            case  1653 :
            {
                return specialplanetarycommoditiesholdcapacity;
            }
            case  1804 :
            {
                return specialquafeholdcapacity;
            }
            case  1768 :
            {
                return typecolorscheme;
            }
            case  1132 :
            {
                return upgradecapacity;
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
    public IMetaGroup<Hauler> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Hauler>
    {
        public static final String RESOURCE_PATH = "SDE/types/ship/Hauler.yaml";
        private Map<Integer, Hauler> cache = (null);

        @Override
        public IMetaCategory<? super Hauler> category() {
            return Ship.METACAT;
        }

        @Override
        public int getGroupId() {
            return  28;
        }

        @Override
        public String getName() {
            return "Hauler";
        }

        @Override
        public synchronized Map<Integer, Hauler> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(Hauler.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, Hauler> types;
        }
    }
}
