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
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
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
import fr.guiguilechat.jcelechat.model.sde.attributes.CapacitorNeedMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.Charge;
import fr.guiguilechat.jcelechat.model.sde.attributes.ConsumptionQuantityBonusPercentage;
import fr.guiguilechat.jcelechat.model.sde.attributes.CovertOpsAndReconOpsCloakModuleDelay;
import fr.guiguilechat.jcelechat.model.sde.attributes.CpuLoad;
import fr.guiguilechat.jcelechat.model.sde.attributes.CpuOutput;
import fr.guiguilechat.jcelechat.model.sde.attributes.Damage;
import fr.guiguilechat.jcelechat.model.sde.attributes.DroneBandwidth;
import fr.guiguilechat.jcelechat.model.sde.attributes.DroneCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.EliteBonusInterceptorRole;
import fr.guiguilechat.jcelechat.model.sde.attributes.EmDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ExplosiveDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.FalloffBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterHeavySlots;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterLightSlots;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterSupportSlots;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterTubes;
import fr.guiguilechat.jcelechat.model.sde.attributes.FrigateEscapeBayCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.FwLpKill;
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
import fr.guiguilechat.jcelechat.model.sde.attributes.HullEmDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.HullExplosiveDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.HullKineticDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.HullThermalDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.IndustrialBonusDroneDamage;
import fr.guiguilechat.jcelechat.model.sde.attributes.JumpHarmonics;
import fr.guiguilechat.jcelechat.model.sde.attributes.KineticDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.LauncherSlotsLeft;
import fr.guiguilechat.jcelechat.model.sde.attributes.LowSlots;
import fr.guiguilechat.jcelechat.model.sde.attributes.MainColor;
import fr.guiguilechat.jcelechat.model.sde.attributes.Mass;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxDirectionalVelocity;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxLockedTargets;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxPassengers;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxRangeBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxTargetRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxVelocity;
import fr.guiguilechat.jcelechat.model.sde.attributes.MedSlots;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaGroupID;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaLevel;
import fr.guiguilechat.jcelechat.model.sde.attributes.MinTargetVelDmgMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.MiniProfessionRangeBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.MiningAmountMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.NosOverride;
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
import fr.guiguilechat.jcelechat.model.sde.attributes.RigSize;
import fr.guiguilechat.jcelechat.model.sde.attributes.RigSlots;
import fr.guiguilechat.jcelechat.model.sde.attributes.RoleBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.RookieArmorResistanceBonus;
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
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonus2AF;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonus3CF;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonus3MF;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusAF;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusCF;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusCF2;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusGF;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusGF2;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusMF;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusMF2;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusOREfrig1;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusOREfrig2;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusPF1;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusPF2;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusRole1;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusRole2;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusRole7;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusRole8;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusUF1;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusUF2;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipRoleBonusWarpSpeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipScanResistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.SignatureRadius;
import fr.guiguilechat.jcelechat.model.sde.attributes.SpecialAmmoHoldCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.SpecialCommandCenterHoldCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.SpecialFuelBayCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.SpecialGasHoldCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.SpecialIndustrialShipHoldCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.SpecialLargeShipHoldCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.SpecialMediumShipHoldCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.SpecialMineralHoldCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.SpecialOreHoldCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.SpecialPlanetaryCommoditiesHoldCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.SpecialSalvageHoldCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.SpecialShipHoldCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.SpecialSmallShipHoldCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.StructureUniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.TechLevel;
import fr.guiguilechat.jcelechat.model.sde.attributes.ThermalDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.TurretSlotsLeft;
import fr.guiguilechat.jcelechat.model.sde.attributes.TypeColorScheme;
import fr.guiguilechat.jcelechat.model.sde.attributes.Uniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.UpgradeCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.UpgradeSlotsLeft;
import fr.guiguilechat.jcelechat.model.sde.attributes.VirusStrengthBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarpCapacitorNeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarpFactor;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarpScrambleStatus;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarpSpeedMultiplier;
import fr.guiguilechat.jcelechat.model.sde.types.Ship;
import org.yaml.snakeyaml.Yaml;

public class Frigate
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
     * Scales the capacitor need for fitted modules.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double capacitorneedmultiplier;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int consumptionquantitybonuspercentage;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(30000)
    public int covertopsandreconopscloakmoduledelay;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int elitebonusinterceptorrole;
    /**
     * Autogenerated skill attribute, falloffBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double falloffbonus;
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
     * Number of Support Fighters the ship can launch.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fightersupportslots;
    /**
     * This defines the total number of fighter launch tubes on the ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fightertubes;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int frigateescapebaycapacity;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fwlpkill;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double heatattenuationhi;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double heatattenuationlow;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double heatattenuationmed;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double hullemdamageresonance;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double hullexplosivedamageresonance;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double hullkineticdamageresonance;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double hullthermaldamageresonance;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int industrialbonusdronedamage;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int jumpharmonics;
    /**
     * The main color of a ship type.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maincolor;
    /**
     * Deprecated.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int maxdirectionalvelocity;
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
    @DefaultDoubleValue(0.0)
    public double maxrangebonus;
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
     * Deprecated.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double mintargetveldmgmultiplier;
    /**
     * Bonus to optimal range of Codebreakers and Analyzers
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int miniprofessionrangebonus;
    /**
     * The factor by which the amount mined by a mining laser is scaled.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double miningamountmultiplier;
    /**
     * NOS override allows a nosferatu module to drain the target capacitor below the current ships capacitor level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int nosoverride;
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
    public int rolebonus;
    /**
     * Bonus to armor resistances
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rookiearmorresistancebonus;
    /**
     * The resolution that the vessel can target other objects at.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
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
    @DefaultDoubleValue(5.0)
    public double shipbonus2af;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonus3cf;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonus3mf;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double shipbonusaf;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonuscf;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double shipbonuscf2;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double shipbonusgf;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double shipbonusgf2;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double shipbonusmf;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double shipbonusmf2;
    /**
     * ORE Mining frigate bonus 1
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonusorefrig1;
    /**
     * ORE Mining frigate bonus 2
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonusorefrig2;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double shipbonuspf1;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double shipbonuspf2;
    /**
     * Ship Role Bonus. Not multiplied by skills.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double shipbonusrole1;
    /**
     * Ship Role Bonus. Not multiplied by skills.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonusrole2;
    /**
     * Fixed Role Bonus on a ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double shipbonusrole7;
    /**
     * Second Stock Bonus on Pirate Faction Ships.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double shipbonusrole8;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int shipbonusuf1;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int shipbonusuf2;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shiprolebonuswarpspeed;
    /**
     * Chance of being able to resist a ship scan.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipscanresistance;
    /**
     * special ammo hold capacity
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int specialammoholdcapacity;
    /**
     * Capacity of CC-only hold
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int specialcommandcenterholdcapacity;
    /**
     * special fuel bay capacity
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int specialfuelbaycapacity;
    /**
     * Capacity of gas-only hold
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int specialgasholdcapacity;
    /**
     * Capacity of industrial ship hold
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int specialindustrialshipholdcapacity;
    /**
     * Capacity of battleship hold
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int speciallargeshipholdcapacity;
    /**
     * Capacity of cruiser/battlecruiser ship hold
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int specialmediumshipholdcapacity;
    /**
     * Capacity of mineral-only hold
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int specialmineralholdcapacity;
    /**
     * Capacity of ore-only hold
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int specialoreholdcapacity;
    /**
     * Capacity of Planetary Commodities hold
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int specialplanetarycommoditiesholdcapacity;
    /**
     * Capacity of special salvage-only hold
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int specialsalvageholdcapacity;
    /**
     * Capacity of ship-only hold
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int specialshipholdcapacity;
    /**
     * Capacity of frigate/destroyer hold
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int specialsmallshipholdcapacity;
    /**
     * How many rigs can by fitted to this ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int upgradeslotsleft;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int virusstrengthbonus;
    /**
     * Warp ability of a ship.  If greater than zero than the ship cannot warp.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int warpscramblestatus;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {EliteBonusInterceptorRole.INSTANCE, BaseWarpSpeed.INSTANCE, Damage.INSTANCE, Mass.INSTANCE, FighterCapacity.INSTANCE, ShieldCapacity.INSTANCE, ShieldCharge.INSTANCE, Hp.INSTANCE, ArmorHP.INSTANCE, CovertOpsAndReconOpsCloakModuleDelay.INSTANCE, RigSize.INSTANCE, PowerOutput.INSTANCE, ArmorEmDamageResonance.INSTANCE, LowSlots.INSTANCE, ArmorUniformity.INSTANCE, ArmorExplosiveDamageResonance.INSTANCE, SpecialFuelBayCapacity.INSTANCE, MedSlots.INSTANCE, StructureUniformity.INSTANCE, ArmorKineticDamageResonance.INSTANCE, HiSlots.INSTANCE, ArmorThermalDamageResonance.INSTANCE, PowerLoad.INSTANCE, ShieldEmDamageResonance.INSTANCE, ConsumptionQuantityBonusPercentage.INSTANCE, ShieldExplosiveDamageResonance.INSTANCE, ShieldKineticDamageResonance.INSTANCE, Charge.INSTANCE, ShieldThermalDamageResonance.INSTANCE, FwLpKill.INSTANCE, PowerToSpeed.INSTANCE, SpecialOreHoldCapacity.INSTANCE, IndustrialBonusDroneDamage.INSTANCE, SpecialGasHoldCapacity.INSTANCE, WarpFactor.INSTANCE, RequiredSkill1Level.INSTANCE, RequiredSkill2Level.INSTANCE, SpecialMineralHoldCapacity.INSTANCE, SpecialSalvageHoldCapacity.INSTANCE, SpecialShipHoldCapacity.INSTANCE, ShipBonusRole7 .INSTANCE, SpecialSmallShipHoldCapacity.INSTANCE, SpecialMediumShipHoldCapacity.INSTANCE, SpecialLargeShipHoldCapacity.INSTANCE, DroneCapacity.INSTANCE, SpecialIndustrialShipHoldCapacity.INSTANCE, RookieArmorResistanceBonus.INSTANCE, SpecialAmmoHoldCapacity.INSTANCE, MaxVelocity.INSTANCE, Capacity.INSTANCE, SignatureRadius.INSTANCE, RoleBonus.INSTANCE, MiniProfessionRangeBonus.INSTANCE, CpuOutput.INSTANCE, CpuLoad.INSTANCE, ShipBonusOREfrig1 .INSTANCE, ShipBonusOREfrig2 .INSTANCE, ScanResolution.INSTANCE, RechargeRate.INSTANCE, Agility.INSTANCE, ShipBonusGF2 .INSTANCE, ShipBonusMF2 .INSTANCE, ShipBonusCF2 .INSTANCE, MaxTargetRange.INSTANCE, ScanSpeed.INSTANCE, ShipBonus3CF.INSTANCE, WarpSpeedMultiplier.INSTANCE, ShipBonus3MF.INSTANCE, FalloffBonus.INSTANCE, MaxRangeBonus.INSTANCE, AllowedInCapIndustrialMaintenanceBay.INSTANCE, LauncherSlotsLeft.INSTANCE, TurretSlotsLeft.INSTANCE, WarpScrambleStatus.INSTANCE, UpgradeCapacity.INSTANCE, KineticDamageResonance.INSTANCE, SpecialCommandCenterHoldCapacity.INSTANCE, ThermalDamageResonance.INSTANCE, ExplosiveDamageResonance.INSTANCE, RigSlots.INSTANCE, EmDamageResonance.INSTANCE, SpecialPlanetaryCommoditiesHoldCapacity.INSTANCE, MetaLevel.INSTANCE, MainColor.INSTANCE, VirusStrengthBonus.INSTANCE, MaxPassengers.INSTANCE, UpgradeSlotsLeft.INSTANCE, Uniformity.INSTANCE, MaxDirectionalVelocity.INSTANCE, MinTargetVelDmgMultiplier.INSTANCE, ShipBonusRole8 .INSTANCE, NosOverride.INSTANCE, WarpCapacitorNeed.INSTANCE, HeatCapacityHi.INSTANCE, HeatDissipationRateHi.INSTANCE, MetaGroupID.INSTANCE, AffectedByIndustrialInvulnModule.INSTANCE, Radius.INSTANCE, TechLevel.INSTANCE, FighterTubes.INSTANCE, FighterLightSlots.INSTANCE, FighterSupportSlots.INSTANCE, FighterHeavySlots.INSTANCE, HeatDissipationRateMed.INSTANCE, HeatDissipationRateLow.INSTANCE, HeatCapacityMed.INSTANCE, HeatCapacityLow.INSTANCE, RequiredSkill1 .INSTANCE, RequiredSkill2 .INSTANCE, MaxLockedTargets.INSTANCE, HeatGenerationMultiplier.INSTANCE, ShipBonusPF1 .INSTANCE, ShipBonusPF2 .INSTANCE, ShipBonusMF.INSTANCE, FrigateEscapeBayCapacity.INSTANCE, HullEmDamageResonance.INSTANCE, ShipBonusGF.INSTANCE, HullExplosiveDamageResonance.INSTANCE, ShipBonusCF.INSTANCE, MiningAmountMultiplier.INSTANCE, HullKineticDamageResonance.INSTANCE, ShipBonusAF.INSTANCE, ScanRadarStrength.INSTANCE, HullThermalDamageResonance.INSTANCE, ScanLadarStrength.INSTANCE, ScanMagnetometricStrength.INSTANCE, ScanGravimetricStrength.INSTANCE, CapacitorNeedMultiplier.INSTANCE, PropulsionGraphicID.INSTANCE, ShieldRechargeRate.INSTANCE, ShipBonusUF1 .INSTANCE, ShipBonusUF2 .INSTANCE, CapacitorCapacity.INSTANCE, ShieldUniformity.INSTANCE, ShipBonus2AF.INSTANCE, ShipRoleBonusWarpSpeed.INSTANCE, JumpHarmonics.INSTANCE, TypeColorScheme.INSTANCE, HeatAttenuationHi.INSTANCE, HeatAttenuationMed.INSTANCE, HeatAttenuationLow.INSTANCE, GfxBoosterID.INSTANCE, DroneBandwidth.INSTANCE, ShipBonusRole1 .INSTANCE, ShipBonusRole2 .INSTANCE, ShipScanResistance.INSTANCE })));
    public static final Frigate.MetaGroup METAGROUP = new Frigate.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  2464 :
            {
                return affectedbyindustrialinvulnmodule;
            }
            case  1891 :
            {
                return allowedincapindustrialmaintenancebay;
            }
            case  216 :
            {
                return capacitorneedmultiplier;
            }
            case  1296 :
            {
                return consumptionquantitybonuspercentage;
            }
            case  1034 :
            {
                return covertopsandreconopscloakmoduledelay;
            }
            case  1280 :
            {
                return elitebonusinterceptorrole;
            }
            case  349 :
            {
                return falloffbonus;
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
            case  2218 :
            {
                return fightersupportslots;
            }
            case  2216 :
            {
                return fightertubes;
            }
            case  3020 :
            {
                return frigateescapebaycapacity;
            }
            case  1555 :
            {
                return fwlpkill;
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
            case  2580 :
            {
                return industrialbonusdronedamage;
            }
            case  1253 :
            {
                return jumpharmonics;
            }
            case  124 :
            {
                return maincolor;
            }
            case  661 :
            {
                return maxdirectionalvelocity;
            }
            case  129 :
            {
                return maxpassengers;
            }
            case  351 :
            {
                return maxrangebonus;
            }
            case  1692 :
            {
                return metagroupid;
            }
            case  662 :
            {
                return mintargetveldmgmultiplier;
            }
            case  1838 :
            {
                return miniprofessionrangebonus;
            }
            case  207 :
            {
                return miningamountmultiplier;
            }
            case  1945 :
            {
                return nosoverride;
            }
            case  183 :
            {
                return requiredskill2;
            }
            case  278 :
            {
                return requiredskill2level;
            }
            case  1547 :
            {
                return rigsize;
            }
            case  1137 :
            {
                return rigslots;
            }
            case  2091 :
            {
                return rolebonus;
            }
            case  1825 :
            {
                return rookiearmorresistancebonus;
            }
            case  564 :
            {
                return scanresolution;
            }
            case  79 :
            {
                return scanspeed;
            }
            case  485 :
            {
                return shipbonus2af;
            }
            case  1624 :
            {
                return shipbonus3cf;
            }
            case  1626 :
            {
                return shipbonus3mf;
            }
            case  464 :
            {
                return shipbonusaf;
            }
            case  463 :
            {
                return shipbonuscf;
            }
            case  588 :
            {
                return shipbonuscf2;
            }
            case  462 :
            {
                return shipbonusgf;
            }
            case  586 :
            {
                return shipbonusgf2;
            }
            case  460 :
            {
                return shipbonusmf;
            }
            case  587 :
            {
                return shipbonusmf2;
            }
            case  1842 :
            {
                return shipbonusorefrig1;
            }
            case  1843 :
            {
                return shipbonusorefrig2;
            }
            case  2762 :
            {
                return shipbonuspf1;
            }
            case  2763 :
            {
                return shipbonuspf2;
            }
            case  2298 :
            {
                return shipbonusrole1;
            }
            case  2299 :
            {
                return shipbonusrole2;
            }
            case  793 :
            {
                return shipbonusrole7;
            }
            case  1688 :
            {
                return shipbonusrole8;
            }
            case  3041 :
            {
                return shipbonusuf1;
            }
            case  3042 :
            {
                return shipbonusuf2;
            }
            case  2789 :
            {
                return shiprolebonuswarpspeed;
            }
            case  511 :
            {
                return shipscanresistance;
            }
            case  1573 :
            {
                return specialammoholdcapacity;
            }
            case  1646 :
            {
                return specialcommandcenterholdcapacity;
            }
            case  1549 :
            {
                return specialfuelbaycapacity;
            }
            case  1557 :
            {
                return specialgasholdcapacity;
            }
            case  1564 :
            {
                return specialindustrialshipholdcapacity;
            }
            case  1563 :
            {
                return speciallargeshipholdcapacity;
            }
            case  1562 :
            {
                return specialmediumshipholdcapacity;
            }
            case  1558 :
            {
                return specialmineralholdcapacity;
            }
            case  1556 :
            {
                return specialoreholdcapacity;
            }
            case  1653 :
            {
                return specialplanetarycommoditiesholdcapacity;
            }
            case  1559 :
            {
                return specialsalvageholdcapacity;
            }
            case  1560 :
            {
                return specialshipholdcapacity;
            }
            case  1561 :
            {
                return specialsmallshipholdcapacity;
            }
            case  1154 :
            {
                return upgradeslotsleft;
            }
            case  1918 :
            {
                return virusstrengthbonus;
            }
            case  104 :
            {
                return warpscramblestatus;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<Frigate> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Frigate>
    {
        public static final String RESOURCE_PATH = "SDE/types/ship/Frigate.yaml";
        private Map<String, Frigate> cache = (null);

        @Override
        public IMetaCategory<? super Frigate> category() {
            return Ship.METACAT;
        }

        @Override
        public int getGroupId() {
            return  25;
        }

        @Override
        public String getName() {
            return "Frigate";
        }

        @Override
        public synchronized Map<String, Frigate> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(Frigate.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, Frigate> types;
        }
    }
}
