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
import fr.guiguilechat.jcelechat.model.sde.attributes.AdvancedAgility;
import fr.guiguilechat.jcelechat.model.sde.attributes.AdvancedCapitalAgility;
import fr.guiguilechat.jcelechat.model.sde.attributes.Agility;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorEmDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorExplosiveDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorHP;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorKineticDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorThermalDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorUniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.BaseWarpSpeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanJump;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanReceiveCloneJumps;
import fr.guiguilechat.jcelechat.model.sde.attributes.CapacitorCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.CapitalIndustrialCommandBonusDroneDamage;
import fr.guiguilechat.jcelechat.model.sde.attributes.CapitalIndustrialShipBonusDroneHitPoints;
import fr.guiguilechat.jcelechat.model.sde.attributes.CapitalIndustrialShipBonusDroneIceCycleTime;
import fr.guiguilechat.jcelechat.model.sde.attributes.CapitalIndustrialShipBonusDroneOreMiningYield;
import fr.guiguilechat.jcelechat.model.sde.attributes.CargoScanResistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.CargoScannerRangeBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.Charge;
import fr.guiguilechat.jcelechat.model.sde.attributes.CommandBonusEffective;
import fr.guiguilechat.jcelechat.model.sde.attributes.ConduitJumpDriveConsumptionAmount;
import fr.guiguilechat.jcelechat.model.sde.attributes.ConduitJumpPassengerCount;
import fr.guiguilechat.jcelechat.model.sde.attributes.CpuLoad;
import fr.guiguilechat.jcelechat.model.sde.attributes.CpuOutput;
import fr.guiguilechat.jcelechat.model.sde.attributes.Damage;
import fr.guiguilechat.jcelechat.model.sde.attributes.DisallowInHighSec;
import fr.guiguilechat.jcelechat.model.sde.attributes.DroneBandwidth;
import fr.guiguilechat.jcelechat.model.sde.attributes.DroneCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.EmDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.EnergyWarfareResistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntosisAssistanceImpedanceMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntosisDurationMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.ExplosiveDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityAntiCapitalMissileResistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityKamikazeResistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.FleetHangarCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.FwLpKill;
import fr.guiguilechat.jcelechat.model.sde.attributes.GateScrambleStatus;
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
import fr.guiguilechat.jcelechat.model.sde.attributes.IndustrialCommandBonusMiningForemanBurstRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.IsCapitalSize;
import fr.guiguilechat.jcelechat.model.sde.attributes.IsCarrierJumpConduitPassenger;
import fr.guiguilechat.jcelechat.model.sde.attributes.IsTitanJumpPortalPassenger;
import fr.guiguilechat.jcelechat.model.sde.attributes.JumpClonesLeft;
import fr.guiguilechat.jcelechat.model.sde.attributes.JumpDriveCapacitorNeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.JumpDriveConsumptionAmount;
import fr.guiguilechat.jcelechat.model.sde.attributes.JumpDriveConsumptionType;
import fr.guiguilechat.jcelechat.model.sde.attributes.JumpDriveDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.JumpDriveRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.JumpDriveTargetBeaconTypelistID;
import fr.guiguilechat.jcelechat.model.sde.attributes.JumpFatigueMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.KineticDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.LauncherSlotsLeft;
import fr.guiguilechat.jcelechat.model.sde.attributes.LowSlots;
import fr.guiguilechat.jcelechat.model.sde.attributes.MainColor;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxDirectionalScanRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxGangModules;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxJumpClones;
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
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill2;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill2Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill3;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill3Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.RigSize;
import fr.guiguilechat.jcelechat.model.sde.attributes.RigSlots;
import fr.guiguilechat.jcelechat.model.sde.attributes.RoleBonusCommandBurstAoERange;
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
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusORECapital1;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusORECapital2;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusORECapital3;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusORECapital4;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipMaintenanceBayCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.SignatureRadius;
import fr.guiguilechat.jcelechat.model.sde.attributes.SpecialFuelBayCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.StasisWebifierResistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.StructureUniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.SurveyScannerRangeBonus;
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
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class CapitalIndustrialShip
    extends Ship
{
    /**
     * Attribute on ship to make advanced command affect only ships that we want.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int advancedagility;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int advancedcapitalagility;
    /**
     *  1 = ship can use jump drive
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canjump;
    /**
     * Defines whether a ship has the functionality to allow it to receive clone jumps and host jump clones.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canreceiveclonejumps;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int capitalindustrialcommandbonusdronedamage;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int capitalindustrialshipbonusdronehitpoints;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int capitalindustrialshipbonusdroneicecycletime;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int capitalindustrialshipbonusdroneoreminingyield;
    /**
     * Cargo Scanner Range Bonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int cargoscannerrangebonus;
    /**
     * commandBonusEffective
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int commandbonuseffective;
    /**
     * Number of units needed to conduit jump
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int conduitjumpdriveconsumptionamount;
    /**
     * How many passengers can be carried in a Conduit Jump
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int conduitjumppassengercount;
    /**
     * Security status restriction, preventing ships from entering high sec and modules from being activated.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int disallowinhighsec;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double entosisassistanceimpedancemultiplier;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int entosisdurationmultiplier;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fighterabilityanticapitalmissileresistance;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fighterabilitykamikazeresistance;
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
     * If greater than zero than the ship cannot activate gates. Set this to 0 on a type if you want it to be gate scramble-able.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(-1000)
    public int gatescramblestatus;
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
     * tbd
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int hislots;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int industrialcommandbonusminingforemanburstrange;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int iscapitalsize;
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(1)
    public int iscarrierjumpconduitpassenger;
    /**
     * Set this attribute on a ship to declare that the ship is an eligible passenger to travel through a Titan Jump Portal
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(1)
    public int istitanjumpportalpassenger;
    /**
     * The remaining number of unused clone vats on the ship that are available for installation of jump clones.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int jumpclonesleft;
    /**
     * Minimum capacitor need for jump drive operation from full capacitor in modifier%.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double jumpdrivecapacitorneed;
    /**
     * Number of units it consumes per light year.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(2000)
    public int jumpdriveconsumptionamount;
    /**
     * Type that is used for consumption from cargo hold when activating jump drive operation.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int jumpdriveconsumptiontype;
    /**
     * The amount of time before the ship actually jumps.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(300000)
    public int jumpdriveduration;
    /**
     * Range in light years the ship can maximum jump to.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double jumpdriverange;
    /**
     * Pointer to type-list that describes which beacons a ship's jump drive can connect to
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int jumpdrivetargetbeacontypelistid;
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
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(3)
    public int maxgangmodules;
    /**
     * The maximum amount of jump clones that the character may have in existence or ship may have stored.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxjumpclones;
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
     * tbd
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int medslots;
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
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rolebonuscommandburstaoerange;
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
     * shipBonusORECapital1
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonusorecapital1;
    /**
     * shipBonusORECapital2
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonusorecapital2;
    /**
     * shipBonusORECapital3
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonusorecapital3;
    /**
     * shipBonusORECapital4
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonusorecapital4;
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
     * Survey Scanner Range Bonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int surveyscannerrangebonus;
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
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {BaseWarpSpeed.INSTANCE, Damage.INSTANCE, ShieldCapacity.INSTANCE, ShieldCharge.INSTANCE, Hp.INSTANCE, ArmorHP.INSTANCE, RigSize.INSTANCE, PowerOutput.INSTANCE, ArmorEmDamageResonance.INSTANCE, LowSlots.INSTANCE, ArmorUniformity.INSTANCE, ArmorExplosiveDamageResonance.INSTANCE, MedSlots.INSTANCE, SpecialFuelBayCapacity.INSTANCE, StructureUniformity.INSTANCE, ArmorKineticDamageResonance.INSTANCE, HiSlots.INSTANCE, RoleBonusCommandBurstAoERange.INSTANCE, ArmorThermalDamageResonance.INSTANCE, PowerLoad.INSTANCE, ShieldEmDamageResonance.INSTANCE, ShieldExplosiveDamageResonance.INSTANCE, ShieldKineticDamageResonance.INSTANCE, Charge.INSTANCE, ShieldThermalDamageResonance.INSTANCE, FwLpKill.INSTANCE, PowerToSpeed.INSTANCE, GeneralMiningHoldCapacity.INSTANCE, RequiredSkill1Level.INSTANCE, WarpFactor.INSTANCE, RequiredSkill2Level.INSTANCE, RequiredSkill3Level.INSTANCE, DroneCapacity.INSTANCE, MaxVelocity.INSTANCE, Capacity.INSTANCE, SignatureRadius.INSTANCE, CpuOutput.INSTANCE, CpuLoad.INSTANCE, IsCarrierJumpConduitPassenger.INSTANCE, ScanResolution.INSTANCE, RechargeRate.INSTANCE, JumpClonesLeft.INSTANCE, ConduitJumpDriveConsumptionAmount.INSTANCE, ConduitJumpPassengerCount.INSTANCE, WeaponDisruptionResistance.INSTANCE, StasisWebifierResistance.INSTANCE, Agility.INSTANCE, MaxTargetRange.INSTANCE, ScanSpeed.INSTANCE, AdvancedAgility.INSTANCE, WarpSpeedMultiplier.INSTANCE, CanJump.INSTANCE, MaxRangeBonus.INSTANCE, JumpDriveConsumptionType.INSTANCE, JumpDriveRange.INSTANCE, JumpDriveConsumptionAmount.INSTANCE, JumpDriveDuration.INSTANCE, LauncherSlotsLeft.INSTANCE, TurretSlotsLeft.INSTANCE, AdvancedCapitalAgility.INSTANCE, UpgradeCapacity.INSTANCE, KineticDamageResonance.INSTANCE, ThermalDamageResonance.INSTANCE, ExplosiveDamageResonance.INSTANCE, RigSlots.INSTANCE, EmDamageResonance.INSTANCE, MetaLevelOld.INSTANCE, MainColor.INSTANCE, MaxPassengers.INSTANCE, FighterAbilityKamikazeResistance.INSTANCE, UpgradeSlotsLeft.INSTANCE, JumpDriveCapacitorNeed.INSTANCE, IndustrialCommandBonusMiningForemanBurstRange.INSTANCE, Uniformity.INSTANCE, HasShipMaintenanceBay.INSTANCE, ShipMaintenanceBayCapacity.INSTANCE, HasFleetHangars.INSTANCE, FleetHangarCapacity.INSTANCE, CapitalIndustrialShipBonusDroneOreMiningYield.INSTANCE, CapitalIndustrialShipBonusDroneIceCycleTime.INSTANCE, WarpCapacitorNeed.INSTANCE, HeatCapacityHi.INSTANCE, HeatDissipationRateHi.INSTANCE, CapitalIndustrialShipBonusDroneHitPoints.INSTANCE, Radius.INSTANCE, MaxDirectionalScanRange.INSTANCE, TechLevel.INSTANCE, HeatDissipationRateMed.INSTANCE, HeatDissipationRateLow.INSTANCE, HeatCapacityMed.INSTANCE, HeatCapacityLow.INSTANCE, DisallowInHighSec.INSTANCE, JumpFatigueMultiplier.INSTANCE, MaxGangModules.INSTANCE, GateScrambleStatus.INSTANCE, RequiredSkill1 .INSTANCE, RequiredSkill2 .INSTANCE, RequiredSkill3 .INSTANCE, CargoScanResistance.INSTANCE, MaxLockedTargets.INSTANCE, EntosisAssistanceImpedanceMultiplier.INSTANCE, FighterAbilityAntiCapitalMissileResistance.INSTANCE, HeatGenerationMultiplier.INSTANCE, MaxOperationalDistance.INSTANCE, MaxOperationalUsers.INSTANCE, ScanRadarStrength.INSTANCE, ScanLadarStrength.INSTANCE, SurveyScannerRangeBonus.INSTANCE, ScanMagnetometricStrength.INSTANCE, CargoScannerRangeBonus.INSTANCE, MaxJumpClones.INSTANCE, ScanGravimetricStrength.INSTANCE, CommandBonusEffective.INSTANCE, CanReceiveCloneJumps.INSTANCE, ShipBonusORECapital1 .INSTANCE, ShipBonusORECapital2 .INSTANCE, PropulsionGraphicID.INSTANCE, ShipBonusORECapital3 .INSTANCE, ShipBonusORECapital4 .INSTANCE, ShieldRechargeRate.INSTANCE, CapacitorCapacity.INSTANCE, ShieldUniformity.INSTANCE, EntosisDurationMultiplier.INSTANCE, TypeColorScheme.INSTANCE, HeatAttenuationHi.INSTANCE, HeatAttenuationMed.INSTANCE, HeatAttenuationLow.INSTANCE, JumpDriveTargetBeaconTypelistID.INSTANCE, GfxBoosterID.INSTANCE, IsTitanJumpPortalPassenger.INSTANCE, DroneBandwidth.INSTANCE, IsCapitalSize.INSTANCE, EnergyWarfareResistance.INSTANCE, CapitalIndustrialCommandBonusDroneDamage.INSTANCE })));
    public static final CapitalIndustrialShip.MetaGroup METAGROUP = new CapitalIndustrialShip.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  853 :
            {
                return advancedagility;
            }
            case  874 :
            {
                return advancedcapitalagility;
            }
            case  861 :
            {
                return canjump;
            }
            case  982 :
            {
                return canreceiveclonejumps;
            }
            case  3326 :
            {
                return capitalindustrialcommandbonusdronedamage;
            }
            case  3233 :
            {
                return capitalindustrialshipbonusdronehitpoints;
            }
            case  3224 :
            {
                return capitalindustrialshipbonusdroneicecycletime;
            }
            case  3223 :
            {
                return capitalindustrialshipbonusdroneoreminingyield;
            }
            case  1235 :
            {
                return cargoscannerrangebonus;
            }
            case  1236 :
            {
                return commandbonuseffective;
            }
            case  3131 :
            {
                return conduitjumpdriveconsumptionamount;
            }
            case  3133 :
            {
                return conduitjumppassengercount;
            }
            case  1970 :
            {
                return disallowinhighsec;
            }
            case  2754 :
            {
                return entosisassistanceimpedancemultiplier;
            }
            case  2021 :
            {
                return entosisdurationmultiplier;
            }
            case  2244 :
            {
                return fighterabilityanticapitalmissileresistance;
            }
            case  2433 :
            {
                return fighterabilitykamikazeresistance;
            }
            case  912 :
            {
                return fleethangarcapacity;
            }
            case  1555 :
            {
                return fwlpkill;
            }
            case  1973 :
            {
                return gatescramblestatus;
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
            case  14 :
            {
                return hislots;
            }
            case  3205 :
            {
                return industrialcommandbonusminingforemanburstrange;
            }
            case  1785 :
            {
                return iscapitalsize;
            }
            case  5682 :
            {
                return iscarrierjumpconduitpassenger;
            }
            case  3319 :
            {
                return istitanjumpportalpassenger;
            }
            case  1336 :
            {
                return jumpclonesleft;
            }
            case  898 :
            {
                return jumpdrivecapacitorneed;
            }
            case  868 :
            {
                return jumpdriveconsumptionamount;
            }
            case  866 :
            {
                return jumpdriveconsumptiontype;
            }
            case  869 :
            {
                return jumpdriveduration;
            }
            case  867 :
            {
                return jumpdriverange;
            }
            case  3317 :
            {
                return jumpdrivetargetbeacontypelistid;
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
            case  435 :
            {
                return maxgangmodules;
            }
            case  979 :
            {
                return maxjumpclones;
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
            case  13 :
            {
                return medslots;
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
            case  2574 :
            {
                return rolebonuscommandburstaoerange;
            }
            case  564 :
            {
                return scanresolution;
            }
            case  79 :
            {
                return scanspeed;
            }
            case  1239 :
            {
                return shipbonusorecapital1;
            }
            case  1240 :
            {
                return shipbonusorecapital2;
            }
            case  1243 :
            {
                return shipbonusorecapital3;
            }
            case  1244 :
            {
                return shipbonusorecapital4;
            }
            case  908 :
            {
                return shipmaintenancebaycapacity;
            }
            case  1549 :
            {
                return specialfuelbaycapacity;
            }
            case  1234 :
            {
                return surveyscannerrangebonus;
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
    public IMetaGroup<CapitalIndustrialShip> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<CapitalIndustrialShip>
    {
        public static final String RESOURCE_PATH = "SDE/types/ship/CapitalIndustrialShip.yaml";
        private Map<Integer, CapitalIndustrialShip> cache = (null);

        @Override
        public IMetaCategory<? super CapitalIndustrialShip> category() {
            return Ship.METACAT;
        }

        @Override
        public int getGroupId() {
            return  883;
        }

        @Override
        public String getName() {
            return "CapitalIndustrialShip";
        }

        @Override
        public synchronized Map<Integer, CapitalIndustrialShip> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(CapitalIndustrialShip.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, CapitalIndustrialShip> types;
        }
    }
}
