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
import fr.guiguilechat.jcelechat.model.sde.attributes.AccuracyBonus;
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
import fr.guiguilechat.jcelechat.model.sde.attributes.CargoScanResistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.Charge;
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
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterHeavySlots;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterLightSlots;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterTubes;
import fr.guiguilechat.jcelechat.model.sde.attributes.FleetHangarCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.FwLpKill;
import fr.guiguilechat.jcelechat.model.sde.attributes.GateScrambleStatus;
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
import fr.guiguilechat.jcelechat.model.sde.attributes.IsCapitalSize;
import fr.guiguilechat.jcelechat.model.sde.attributes.IsTitanJumpPortalPassenger;
import fr.guiguilechat.jcelechat.model.sde.attributes.JumpClonesLeft;
import fr.guiguilechat.jcelechat.model.sde.attributes.JumpDriveCapacitorNeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.JumpDriveConsumptionAmount;
import fr.guiguilechat.jcelechat.model.sde.attributes.JumpDriveConsumptionType;
import fr.guiguilechat.jcelechat.model.sde.attributes.JumpDriveDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.JumpDriveRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.JumpDriveTargetBeaconTypelistID;
import fr.guiguilechat.jcelechat.model.sde.attributes.JumpHarmonics;
import fr.guiguilechat.jcelechat.model.sde.attributes.JumpPortalCapacitorNeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.JumpPortalConsumptionMassFactor;
import fr.guiguilechat.jcelechat.model.sde.attributes.JumpPortalDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.KineticDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.LauncherSlotsLeft;
import fr.guiguilechat.jcelechat.model.sde.attributes.LowSlots;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxJumpClones;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxLockedTargets;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxOperationalDistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxOperationalUsers;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxPassengers;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxTargetRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxVelocity;
import fr.guiguilechat.jcelechat.model.sde.attributes.MedSlots;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaGroupID;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaLevelOld;
import fr.guiguilechat.jcelechat.model.sde.attributes.NosOverride;
import fr.guiguilechat.jcelechat.model.sde.attributes.PowerLoad;
import fr.guiguilechat.jcelechat.model.sde.attributes.PowerOutput;
import fr.guiguilechat.jcelechat.model.sde.attributes.PowerToSpeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.PropulsionGraphicID;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.RechargeRate;
import fr.guiguilechat.jcelechat.model.sde.attributes.RemoteAssistanceImpedance;
import fr.guiguilechat.jcelechat.model.sde.attributes.RemoteRepairImpedance;
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
import fr.guiguilechat.jcelechat.model.sde.attributes.SensorDampenerResistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldCharge;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldEmDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldExplosiveDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldKineticDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldRechargeRate;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldThermalDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldUniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusRole1;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusRole2;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusRole3;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusRole4;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusTitanA1;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusTitanA2;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusTitanA3;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusTitanC1;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusTitanC2;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusTitanC3;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusTitanC5;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusTitanG1;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusTitanG2;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusTitanG3;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusTitanM1;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusTitanM2;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusTitanM3;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipMaintenanceBayCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.SignatureRadius;
import fr.guiguilechat.jcelechat.model.sde.attributes.SpecialCorpseHoldCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.SpecialFuelBayCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.StasisWebifierResistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.StructureUniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.TargetPainterResistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.TechLevel;
import fr.guiguilechat.jcelechat.model.sde.attributes.ThermalDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.TurretSlotsLeft;
import fr.guiguilechat.jcelechat.model.sde.attributes.TypeColorScheme;
import fr.guiguilechat.jcelechat.model.sde.attributes.Uniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.UpgradeCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.UpgradeSlotsLeft;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarpCapacitorNeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarpFactor;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarpScrambleStatus;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarpSpeedMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.WeaponDisruptionResistance;
import fr.guiguilechat.jcelechat.model.sde.types.Ship;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class Titan
    extends Ship
{
    /**
     * Something to do with accuracy.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int accuracybonus;
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
     * This defines the total capacity of fighters allowed in the fighter bay of the ship
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fightercapacity;
    /**
     * Number of Heavy Fighters the ship can launch.Heavy 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fighterheavyslots;
    /**
     * Number of Light Fighters the ship can launch.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fighterlightslots;
    /**
     * This defines the total number of fighter launch tubes on the ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fightertubes;
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
    public int iscapitalsize;
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
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int jumpharmonics;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int jumpportalcapacitorneed;
    /**
     * Multiplier used to calculate amount of quantity used for jumping via portals based on mass of ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double jumpportalconsumptionmassfactor;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(300000)
    public int jumpportalduration;
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
     * Authoring has been moved to FSD.
     * meta group of type
     * 
     *  3: Story-line (Cosmos)
     *  4: Faction
     *  5: Officer (rare asteroid NPCs)
     *  6: Deadspace
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int metagroupid;
    /**
     * NOS override allows a nosferatu module to drain the target capacitor below the current ships capacitor level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int nosoverride;
    /**
     * Impedance against Remote assistance (sensor boosters, tracking computers and ECCM).
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double remoteassistanceimpedance;
    /**
     * Impedance against Remote Repair (shield, armor, hull and energy).
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double remoterepairimpedance;
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
     * Resistance against Remote Sensor Dampeners.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double sensordampenerresistance;
    /**
     * Ship Role Bonus. Not multiplied by skills.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shipbonusrole1;
    /**
     * Ship Role Bonus. Not multiplied by skills.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonusrole2;
    /**
     * Ship Role Bonus. Not multiplied by skills.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonusrole3;
    /**
     * Ship Role Bonus. Not multiplied by skills.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonusrole4;
    /**
     * Multiplied by Amarr Titan skill level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonustitana1;
    /**
     * Multiplied by Amarr Titan skill level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonustitana2;
    /**
     * Multiplied by Amarr Titan skill level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonustitana3;
    /**
     * Multiplied by Caldari Titan skill level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonustitanc1;
    /**
     * Multiplied by Caldari Titan skill level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonustitanc2;
    /**
     * Multiplied by Caldari Titan skill level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonustitanc3;
    /**
     * Multiplied by Caldari Titan skill level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonustitanc5;
    /**
     * Multiplied by Gallente Titan skill level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonustitang1;
    /**
     * Multiplied by Gallente Titan skill level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonustitang2;
    /**
     * Multiplied by Gallente Titan skill level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonustitang3;
    /**
     * Multiplied by Minmatar Titan skill level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonustitanm1;
    /**
     * Multiplied by Minmatar Titan skill level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonustitanm2;
    /**
     * Multiplied by Minmatar Titan skill level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonustitanm3;
    /**
     * The capacity of the hangar in a ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipmaintenancebaycapacity;
    /**
     * special corpse hold capacity
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int specialcorpseholdcapacity;
    /**
     * special fuel bay capacity
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int specialfuelbaycapacity;
    /**
     * Resistance against Target Painters
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double targetpainterresistance;
    /**
     * How many rigs can by fitted to this ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int upgradeslotsleft;
    /**
     * Warp ability of a ship.  If greater than zero than the ship cannot warp.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int warpscramblestatus;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {BaseWarpSpeed.INSTANCE, Damage.INSTANCE, FighterCapacity.INSTANCE, ShieldCapacity.INSTANCE, ShieldCharge.INSTANCE, Hp.INSTANCE, ArmorHP.INSTANCE, RigSize.INSTANCE, PowerOutput.INSTANCE, ArmorEmDamageResonance.INSTANCE, LowSlots.INSTANCE, ArmorUniformity.INSTANCE, ArmorExplosiveDamageResonance.INSTANCE, SpecialFuelBayCapacity.INSTANCE, MedSlots.INSTANCE, StructureUniformity.INSTANCE, ArmorKineticDamageResonance.INSTANCE, RoleBonusCommandBurstAoERange.INSTANCE, HiSlots.INSTANCE, ArmorThermalDamageResonance.INSTANCE, PowerLoad.INSTANCE, ShieldEmDamageResonance.INSTANCE, ShieldExplosiveDamageResonance.INSTANCE, ShieldKineticDamageResonance.INSTANCE, Charge.INSTANCE, ShieldThermalDamageResonance.INSTANCE, FwLpKill.INSTANCE, PowerToSpeed.INSTANCE, WarpFactor.INSTANCE, RequiredSkill1Level.INSTANCE, RequiredSkill2Level.INSTANCE, RequiredSkill3Level.INSTANCE, DroneCapacity.INSTANCE, MaxVelocity.INSTANCE, Capacity.INSTANCE, SignatureRadius.INSTANCE, CpuOutput.INSTANCE, CpuLoad.INSTANCE, ScanResolution.INSTANCE, RechargeRate.INSTANCE, JumpClonesLeft.INSTANCE, AccuracyBonus.INSTANCE, SensorDampenerResistance.INSTANCE, WeaponDisruptionResistance.INSTANCE, TargetPainterResistance.INSTANCE, StasisWebifierResistance.INSTANCE, RemoteRepairImpedance.INSTANCE, Agility.INSTANCE, MaxTargetRange.INSTANCE, AdvancedAgility.INSTANCE, RemoteAssistanceImpedance.INSTANCE, WarpSpeedMultiplier.INSTANCE, CanJump.INSTANCE, JumpDriveConsumptionType.INSTANCE, JumpDriveRange.INSTANCE, JumpDriveConsumptionAmount.INSTANCE, JumpDriveDuration.INSTANCE, LauncherSlotsLeft.INSTANCE, ShipBonusTitanA1 .INSTANCE, TurretSlotsLeft.INSTANCE, ShipBonusTitanA2 .INSTANCE, WarpScrambleStatus.INSTANCE, ShipBonusTitanA3 .INSTANCE, AdvancedCapitalAgility.INSTANCE, ShipBonusTitanC1 .INSTANCE, ShipBonusTitanC2 .INSTANCE, ShipBonusTitanC3 .INSTANCE, UpgradeCapacity.INSTANCE, KineticDamageResonance.INSTANCE, ShipBonusTitanG1 .INSTANCE, ThermalDamageResonance.INSTANCE, ShipBonusTitanG2 .INSTANCE, ExplosiveDamageResonance.INSTANCE, ShipBonusTitanG3 .INSTANCE, RigSlots.INSTANCE, EmDamageResonance.INSTANCE, ShipBonusTitanM1 .INSTANCE, ShipBonusTitanM2 .INSTANCE, ShipBonusTitanM3 .INSTANCE, ShipBonusTitanC5 .INSTANCE, MetaLevelOld.INSTANCE, MaxPassengers.INSTANCE, FighterAbilityKamikazeResistance.INSTANCE, UpgradeSlotsLeft.INSTANCE, JumpDriveCapacitorNeed.INSTANCE, Uniformity.INSTANCE, HasShipMaintenanceBay.INSTANCE, ShipMaintenanceBayCapacity.INSTANCE, HasFleetHangars.INSTANCE, FleetHangarCapacity.INSTANCE, NosOverride.INSTANCE, WarpCapacitorNeed.INSTANCE, HeatCapacityHi.INSTANCE, HeatDissipationRateHi.INSTANCE, MetaGroupID.INSTANCE, Radius.INSTANCE, SpecialCorpseHoldCapacity.INSTANCE, TechLevel.INSTANCE, FighterTubes.INSTANCE, FighterLightSlots.INSTANCE, FighterHeavySlots.INSTANCE, HeatDissipationRateMed.INSTANCE, HeatDissipationRateLow.INSTANCE, HeatCapacityMed.INSTANCE, HeatCapacityLow.INSTANCE, DisallowInHighSec.INSTANCE, GateScrambleStatus.INSTANCE, RequiredSkill1 .INSTANCE, RequiredSkill2 .INSTANCE, RequiredSkill3 .INSTANCE, CargoScanResistance.INSTANCE, MaxLockedTargets.INSTANCE, EntosisAssistanceImpedanceMultiplier.INSTANCE, FighterAbilityAntiCapitalMissileResistance.INSTANCE, HeatGenerationMultiplier.INSTANCE, MaxOperationalDistance.INSTANCE, MaxOperationalUsers.INSTANCE, ScanRadarStrength.INSTANCE, ScanLadarStrength.INSTANCE, ScanMagnetometricStrength.INSTANCE, MaxJumpClones.INSTANCE, ScanGravimetricStrength.INSTANCE, CanReceiveCloneJumps.INSTANCE, PropulsionGraphicID.INSTANCE, ShieldRechargeRate.INSTANCE, CapacitorCapacity.INSTANCE, ShieldUniformity.INSTANCE, JumpHarmonics.INSTANCE, EntosisDurationMultiplier.INSTANCE, TypeColorScheme.INSTANCE, JumpPortalConsumptionMassFactor.INSTANCE, JumpPortalDuration.INSTANCE, HeatAttenuationHi.INSTANCE, HeatAttenuationMed.INSTANCE, JumpPortalCapacitorNeed.INSTANCE, HeatAttenuationLow.INSTANCE, JumpDriveTargetBeaconTypelistID.INSTANCE, GfxBoosterID.INSTANCE, IsTitanJumpPortalPassenger.INSTANCE, DroneBandwidth.INSTANCE, IsCapitalSize.INSTANCE, ShipBonusRole1 .INSTANCE, ShipBonusRole2 .INSTANCE, ShipBonusRole3 .INSTANCE, ShipBonusRole4 .INSTANCE, EnergyWarfareResistance.INSTANCE })));
    public static final Titan.MetaGroup METAGROUP = new Titan.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  63 :
            {
                return accuracybonus;
            }
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
            case  2055 :
            {
                return fightercapacity;
            }
            case  2219 :
            {
                return fighterheavyslots;
            }
            case  2217 :
            {
                return fighterlightslots;
            }
            case  2216 :
            {
                return fightertubes;
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
            case  1785 :
            {
                return iscapitalsize;
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
            case  1253 :
            {
                return jumpharmonics;
            }
            case  1005 :
            {
                return jumpportalcapacitorneed;
            }
            case  1001 :
            {
                return jumpportalconsumptionmassfactor;
            }
            case  1002 :
            {
                return jumpportalduration;
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
            case  1692 :
            {
                return metagroupid;
            }
            case  1945 :
            {
                return nosoverride;
            }
            case  2135 :
            {
                return remoteassistanceimpedance;
            }
            case  2116 :
            {
                return remoterepairimpedance;
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
            case  2112 :
            {
                return sensordampenerresistance;
            }
            case  2298 :
            {
                return shipbonusrole1;
            }
            case  2299 :
            {
                return shipbonusrole2;
            }
            case  2300 :
            {
                return shipbonusrole3;
            }
            case  2301 :
            {
                return shipbonusrole4;
            }
            case  2406 :
            {
                return shipbonustitana1;
            }
            case  2407 :
            {
                return shipbonustitana2;
            }
            case  2408 :
            {
                return shipbonustitana3;
            }
            case  2410 :
            {
                return shipbonustitanc1;
            }
            case  2411 :
            {
                return shipbonustitanc2;
            }
            case  2412 :
            {
                return shipbonustitanc3;
            }
            case  2423 :
            {
                return shipbonustitanc5;
            }
            case  2414 :
            {
                return shipbonustitang1;
            }
            case  2415 :
            {
                return shipbonustitang2;
            }
            case  2416 :
            {
                return shipbonustitang3;
            }
            case  2418 :
            {
                return shipbonustitanm1;
            }
            case  2419 :
            {
                return shipbonustitanm2;
            }
            case  2420 :
            {
                return shipbonustitanm3;
            }
            case  908 :
            {
                return shipmaintenancebaycapacity;
            }
            case  2467 :
            {
                return specialcorpseholdcapacity;
            }
            case  1549 :
            {
                return specialfuelbaycapacity;
            }
            case  2114 :
            {
                return targetpainterresistance;
            }
            case  1154 :
            {
                return upgradeslotsleft;
            }
            case  104 :
            {
                return warpscramblestatus;
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
    public IMetaGroup<Titan> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Titan>
    {
        public static final String RESOURCE_PATH = "SDE/types/ship/Titan.yaml";
        private Map<Integer, Titan> cache = (null);

        @Override
        public IMetaCategory<? super Titan> category() {
            return Ship.METACAT;
        }

        @Override
        public int getGroupId() {
            return  30;
        }

        @Override
        public String getName() {
            return "Titan";
        }

        @Override
        public synchronized Map<Integer, Titan> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(Titan.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, Titan> types;
        }
    }
}
