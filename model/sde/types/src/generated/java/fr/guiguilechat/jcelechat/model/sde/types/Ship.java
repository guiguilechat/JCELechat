package fr.guiguilechat.jcelechat.model.sde.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultRealValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.Agility;
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
import fr.guiguilechat.jcelechat.model.sde.attributes.GfxBoosterID;
import fr.guiguilechat.jcelechat.model.sde.attributes.HeatCapacityHi;
import fr.guiguilechat.jcelechat.model.sde.attributes.HeatCapacityLow;
import fr.guiguilechat.jcelechat.model.sde.attributes.HeatCapacityMed;
import fr.guiguilechat.jcelechat.model.sde.attributes.HeatDissipationRateHi;
import fr.guiguilechat.jcelechat.model.sde.attributes.HeatDissipationRateLow;
import fr.guiguilechat.jcelechat.model.sde.attributes.HeatDissipationRateMed;
import fr.guiguilechat.jcelechat.model.sde.attributes.HeatGenerationMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hp;
import fr.guiguilechat.jcelechat.model.sde.attributes.KineticDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.LauncherSlotsLeft;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxDirectionalScanRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxLockedTargets;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxTargetRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxVelocity;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaLevelOld;
import fr.guiguilechat.jcelechat.model.sde.attributes.PowerLoad;
import fr.guiguilechat.jcelechat.model.sde.attributes.PowerOutput;
import fr.guiguilechat.jcelechat.model.sde.attributes.PowerToSpeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.PropulsionGraphicID;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.RechargeRate;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanGravimetricStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanLadarStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanMagnetometricStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanRadarStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldCharge;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldEmDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldExplosiveDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldKineticDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldRechargeRate;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldThermalDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldUniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.SignatureRadius;
import fr.guiguilechat.jcelechat.model.sde.attributes.StasisWebifierResistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.StructureUniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.TechLevel;
import fr.guiguilechat.jcelechat.model.sde.attributes.ThermalDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.TurretSlotsLeft;
import fr.guiguilechat.jcelechat.model.sde.attributes.Uniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarpCapacitorNeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarpFactor;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarpSpeedMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.WeaponDisruptionResistance;
import fr.guiguilechat.jcelechat.model.sde.types.ship.AssaultFrigate;
import fr.guiguilechat.jcelechat.model.sde.types.ship.AttackBattlecruiser;
import fr.guiguilechat.jcelechat.model.sde.types.ship.Battleship;
import fr.guiguilechat.jcelechat.model.sde.types.ship.BlackOps;
import fr.guiguilechat.jcelechat.model.sde.types.ship.BlockadeRunner;
import fr.guiguilechat.jcelechat.model.sde.types.ship.CapitalIndustrialShip;
import fr.guiguilechat.jcelechat.model.sde.types.ship.Capsule;
import fr.guiguilechat.jcelechat.model.sde.types.ship.Carrier;
import fr.guiguilechat.jcelechat.model.sde.types.ship.CombatBattlecruiser;
import fr.guiguilechat.jcelechat.model.sde.types.ship.CombatReconShip;
import fr.guiguilechat.jcelechat.model.sde.types.ship.CommandDestroyer;
import fr.guiguilechat.jcelechat.model.sde.types.ship.CommandShip;
import fr.guiguilechat.jcelechat.model.sde.types.ship.Corvette;
import fr.guiguilechat.jcelechat.model.sde.types.ship.CovertOps;
import fr.guiguilechat.jcelechat.model.sde.types.ship.Cruiser;
import fr.guiguilechat.jcelechat.model.sde.types.ship.DeepSpaceTransport;
import fr.guiguilechat.jcelechat.model.sde.types.ship.Destroyer;
import fr.guiguilechat.jcelechat.model.sde.types.ship.Dreadnought;
import fr.guiguilechat.jcelechat.model.sde.types.ship.ElectronicAttackShip;
import fr.guiguilechat.jcelechat.model.sde.types.ship.Exhumer;
import fr.guiguilechat.jcelechat.model.sde.types.ship.ExpeditionFrigate;
import fr.guiguilechat.jcelechat.model.sde.types.ship.FlagCruiser;
import fr.guiguilechat.jcelechat.model.sde.types.ship.ForceAuxiliary;
import fr.guiguilechat.jcelechat.model.sde.types.ship.ForceReconShip;
import fr.guiguilechat.jcelechat.model.sde.types.ship.Freighter;
import fr.guiguilechat.jcelechat.model.sde.types.ship.Frigate;
import fr.guiguilechat.jcelechat.model.sde.types.ship.Hauler;
import fr.guiguilechat.jcelechat.model.sde.types.ship.HeavyAssaultCruiser;
import fr.guiguilechat.jcelechat.model.sde.types.ship.HeavyInterdictionCruiser;
import fr.guiguilechat.jcelechat.model.sde.types.ship.IndustrialCommandShip;
import fr.guiguilechat.jcelechat.model.sde.types.ship.Interceptor;
import fr.guiguilechat.jcelechat.model.sde.types.ship.Interdictor;
import fr.guiguilechat.jcelechat.model.sde.types.ship.JumpFreighter;
import fr.guiguilechat.jcelechat.model.sde.types.ship.LancerDreadnought;
import fr.guiguilechat.jcelechat.model.sde.types.ship.Logistics;
import fr.guiguilechat.jcelechat.model.sde.types.ship.LogisticsFrigate;
import fr.guiguilechat.jcelechat.model.sde.types.ship.Marauder;
import fr.guiguilechat.jcelechat.model.sde.types.ship.MiningBarge;
import fr.guiguilechat.jcelechat.model.sde.types.ship.PrototypeExplorationShip;
import fr.guiguilechat.jcelechat.model.sde.types.ship.Shuttle;
import fr.guiguilechat.jcelechat.model.sde.types.ship.StealthBomber;
import fr.guiguilechat.jcelechat.model.sde.types.ship.StrategicCruiser;
import fr.guiguilechat.jcelechat.model.sde.types.ship.Supercarrier;
import fr.guiguilechat.jcelechat.model.sde.types.ship.TacticalDestroyer;
import fr.guiguilechat.jcelechat.model.sde.types.ship.Titan;

public abstract class Ship
    extends EveType
{
    /**
     * The agility of the object.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double agility;
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
     * The number of hit points on the entities armor.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double armorhp;
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
     * DO NOT MESS WITH
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double armoruniformity;
    /**
     * Just for the UI to display the ship warp speed.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int basewarpspeed;
    /**
     * Capacitor capacity
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double capacitorcapacity;
    /**
     * The cargo space allowed
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double capacity;
    /**
     * Chance of being able to resist a cargo scan.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double cargoscanresistance;
    /**
     * charge of module
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int charge;
    /**
     * CPU load of ship
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int cpuload;
    /**
     * CPU output of ship
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int cpuoutput;
    /**
     * current structure damage dealt to module
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int damage;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int dronebandwidth;
    /**
     * This defines the total capacity of drones allowed in the drone bay of the ship
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int dronecapacity;
    /**
     * Electro magnetic damage multiplier for shield and armor. Represented as "% Resistance" in the UI.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double emdamageresonance;
    /**
     * Resistance against Energy Neutralizing and Nosferatu
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double energywarfareresistance;
    /**
     * damage multiplier vs. explosive damagers.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double explosivedamageresonance;
    /**
     * Graphic ID of the boosters for drone type ships.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int gfxboosterid;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int heatcapacityhi;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int heatcapacitylow;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int heatcapacitymed;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double heatdissipationratehi;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double heatdissipationratelow;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double heatdissipationratemed;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double heatgenerationmultiplier;
    /**
     * The maximum hitpoints of an object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double hp;
    /**
     * damage multiplier vs. kinetic damagers.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double kineticdamageresonance;
    /**
     * The number of remaining unused launcher slots.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int launcherslotsleft;
    /**
     * Maximum range (in metres) that a ship's Directional Scanner can reach
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(2.147483648E12)
    public double maxdirectionalscanrange;
    /**
     * Maximum number of locked targets that the character or their ships electronics can handle at any given time.  Both have individual limits which apply separately.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxlockedtargets;
    /**
     * Maximum range at which the scanner can lock a target.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double maxtargetrange;
    /**
     * Maximum velocity of ship
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double maxvelocity;
    /**
     * Authoring has been moved to FSD
     * The ranking of the module within its tech level
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int metalevelold;
    /**
     * Current load of power core
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int powerload;
    /**
     * power output of power core
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int poweroutput;
    /**
     * tbd
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int powertospeed;
    /**
     * The graphicID of the propulsion system.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int propulsiongraphicid;
    /**
     * Radius of an object in meters
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double radius;
    /**
     * Amount of time taken to fully recharge the capacitor.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double rechargerate;
    /**
     * Gravimetric strength.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double scangravimetricstrength;
    /**
     * Ladar strength.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double scanladarstrength;
    /**
     * Magnetometric strength.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double scanmagnetometricstrength;
    /**
     * Radar strength.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double scanradarstrength;
    /**
     * Amount of maximum shield HP on the item.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shieldcapacity;
    /**
     * DO NOT MESS WITH. Helper attribute for entities, stands in for the shield charge.
     * The amount of starting shield capacity of the NPC.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shieldcharge;
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
     * Amount of time taken to fully recharge the shield.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shieldrechargerate;
    /**
     * Multiplies THERMAL damage taken by Shield. 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double shieldthermaldamageresonance;
    /**
     * DO NOT MESS WITH This number is deducted from the %chance of the seeping to armor, to slow seep of damage through shield.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shielduniformity;
    /**
     * Signature Radius is used for turret tracking and scanning.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(100.0)
    public double signatureradius;
    /**
     * Resistance against Stasis Webifiers
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double stasiswebifierresistance;
    /**
     * DO NOT MESS WITH
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double structureuniformity;
    /**
     * Authoring has been moved to FSD
     * Tech level of an item
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int techlevel;
    /**
     * damage multiplier vs. thermal.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double thermaldamageresonance;
    /**
     * Remaining number of unused turret slots on the ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int turretslotsleft;
    /**
     * This number is deducted from the %chance of the seeping to armor, to slow seep of damage through shield.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double uniformity;
    /**
     * The power cost to warp per one kg per AU (floats do not have the resolution for meters).
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double warpcapacitorneed;
    /**
     * tbd instance param
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int warpfactor;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(3.0)
    public double warpspeedmultiplier;
    /**
     * Resistance against Remote Weapon Disruptors.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double weapondisruptionresistance;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {BaseWarpSpeed.INSTANCE, Damage.INSTANCE, ShieldCapacity.INSTANCE, ShieldCharge.INSTANCE, Hp.INSTANCE, ArmorHP.INSTANCE, PowerOutput.INSTANCE, ArmorEmDamageResonance.INSTANCE, ArmorUniformity.INSTANCE, ArmorExplosiveDamageResonance.INSTANCE, StructureUniformity.INSTANCE, ArmorKineticDamageResonance.INSTANCE, ArmorThermalDamageResonance.INSTANCE, PowerLoad.INSTANCE, ShieldEmDamageResonance.INSTANCE, ShieldExplosiveDamageResonance.INSTANCE, ShieldKineticDamageResonance.INSTANCE, Charge.INSTANCE, ShieldThermalDamageResonance.INSTANCE, PowerToSpeed.INSTANCE, WarpFactor.INSTANCE, DroneCapacity.INSTANCE, MaxVelocity.INSTANCE, Capacity.INSTANCE, SignatureRadius.INSTANCE, CpuOutput.INSTANCE, CpuLoad.INSTANCE, RechargeRate.INSTANCE, WeaponDisruptionResistance.INSTANCE, StasisWebifierResistance.INSTANCE, Agility.INSTANCE, MaxTargetRange.INSTANCE, WarpSpeedMultiplier.INSTANCE, LauncherSlotsLeft.INSTANCE, TurretSlotsLeft.INSTANCE, KineticDamageResonance.INSTANCE, ThermalDamageResonance.INSTANCE, ExplosiveDamageResonance.INSTANCE, EmDamageResonance.INSTANCE, MetaLevelOld.INSTANCE, Uniformity.INSTANCE, WarpCapacitorNeed.INSTANCE, HeatCapacityHi.INSTANCE, HeatDissipationRateHi.INSTANCE, Radius.INSTANCE, MaxDirectionalScanRange.INSTANCE, TechLevel.INSTANCE, HeatDissipationRateMed.INSTANCE, HeatDissipationRateLow.INSTANCE, HeatCapacityMed.INSTANCE, HeatCapacityLow.INSTANCE, CargoScanResistance.INSTANCE, MaxLockedTargets.INSTANCE, HeatGenerationMultiplier.INSTANCE, ScanRadarStrength.INSTANCE, ScanLadarStrength.INSTANCE, ScanMagnetometricStrength.INSTANCE, ScanGravimetricStrength.INSTANCE, PropulsionGraphicID.INSTANCE, ShieldRechargeRate.INSTANCE, CapacitorCapacity.INSTANCE, ShieldUniformity.INSTANCE, GfxBoosterID.INSTANCE, DroneBandwidth.INSTANCE, EnergyWarfareResistance.INSTANCE })));
    public static final Ship.MetaCat METACAT = new Ship.MetaCat();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  70 :
            {
                return agility;
            }
            case  267 :
            {
                return armoremdamageresonance;
            }
            case  268 :
            {
                return armorexplosivedamageresonance;
            }
            case  265 :
            {
                return armorhp;
            }
            case  269 :
            {
                return armorkineticdamageresonance;
            }
            case  270 :
            {
                return armorthermaldamageresonance;
            }
            case  524 :
            {
                return armoruniformity;
            }
            case  1281 :
            {
                return basewarpspeed;
            }
            case  482 :
            {
                return capacitorcapacity;
            }
            case  38 :
            {
                return capacity;
            }
            case  188 :
            {
                return cargoscanresistance;
            }
            case  18 :
            {
                return charge;
            }
            case  49 :
            {
                return cpuload;
            }
            case  48 :
            {
                return cpuoutput;
            }
            case  3 :
            {
                return damage;
            }
            case  1271 :
            {
                return dronebandwidth;
            }
            case  283 :
            {
                return dronecapacity;
            }
            case  113 :
            {
                return emdamageresonance;
            }
            case  2045 :
            {
                return energywarfareresistance;
            }
            case  111 :
            {
                return explosivedamageresonance;
            }
            case  246 :
            {
                return gfxboosterid;
            }
            case  1178 :
            {
                return heatcapacityhi;
            }
            case  1200 :
            {
                return heatcapacitylow;
            }
            case  1199 :
            {
                return heatcapacitymed;
            }
            case  1179 :
            {
                return heatdissipationratehi;
            }
            case  1198 :
            {
                return heatdissipationratelow;
            }
            case  1196 :
            {
                return heatdissipationratemed;
            }
            case  1224 :
            {
                return heatgenerationmultiplier;
            }
            case  9 :
            {
                return hp;
            }
            case  109 :
            {
                return kineticdamageresonance;
            }
            case  101 :
            {
                return launcherslotsleft;
            }
            case  5796 :
            {
                return maxdirectionalscanrange;
            }
            case  192 :
            {
                return maxlockedtargets;
            }
            case  76 :
            {
                return maxtargetrange;
            }
            case  37 :
            {
                return maxvelocity;
            }
            case  633 :
            {
                return metalevelold;
            }
            case  15 :
            {
                return powerload;
            }
            case  11 :
            {
                return poweroutput;
            }
            case  19 :
            {
                return powertospeed;
            }
            case  217 :
            {
                return propulsiongraphicid;
            }
            case  162 :
            {
                return radius;
            }
            case  55 :
            {
                return rechargerate;
            }
            case  211 :
            {
                return scangravimetricstrength;
            }
            case  209 :
            {
                return scanladarstrength;
            }
            case  210 :
            {
                return scanmagnetometricstrength;
            }
            case  208 :
            {
                return scanradarstrength;
            }
            case  263 :
            {
                return shieldcapacity;
            }
            case  264 :
            {
                return shieldcharge;
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
            case  479 :
            {
                return shieldrechargerate;
            }
            case  274 :
            {
                return shieldthermaldamageresonance;
            }
            case  484 :
            {
                return shielduniformity;
            }
            case  552 :
            {
                return signatureradius;
            }
            case  2115 :
            {
                return stasiswebifierresistance;
            }
            case  525 :
            {
                return structureuniformity;
            }
            case  422 :
            {
                return techlevel;
            }
            case  110 :
            {
                return thermaldamageresonance;
            }
            case  102 :
            {
                return turretslotsleft;
            }
            case  136 :
            {
                return uniformity;
            }
            case  153 :
            {
                return warpcapacitorneed;
            }
            case  21 :
            {
                return warpfactor;
            }
            case  600 :
            {
                return warpspeedmultiplier;
            }
            case  2113 :
            {
                return weapondisruptionresistance;
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
    public IMetaCategory<Ship> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<Ship>
    {

        @Override
        public int getCategoryId() {
            return  6;
        }

        @Override
        public String getName() {
            return "Ship";
        }

        @Override
        public Collection<IMetaGroup<? extends Ship>> groups() {
            return Arrays.asList(Frigate.METAGROUP, Cruiser.METAGROUP, Battleship.METAGROUP, Hauler.METAGROUP, Capsule.METAGROUP, Titan.METAGROUP, Shuttle.METAGROUP, Corvette.METAGROUP, AssaultFrigate.METAGROUP, HeavyAssaultCruiser.METAGROUP, DeepSpaceTransport.METAGROUP, CombatBattlecruiser.METAGROUP, Destroyer.METAGROUP, MiningBarge.METAGROUP, Dreadnought.METAGROUP, LancerDreadnought.METAGROUP, Freighter.METAGROUP, CommandShip.METAGROUP, Interdictor.METAGROUP, Exhumer.METAGROUP, Carrier.METAGROUP, Supercarrier.METAGROUP, CovertOps.METAGROUP, Interceptor.METAGROUP, Logistics.METAGROUP, ForceReconShip.METAGROUP, StealthBomber.METAGROUP, CapitalIndustrialShip.METAGROUP, ElectronicAttackShip.METAGROUP, HeavyInterdictionCruiser.METAGROUP, BlackOps.METAGROUP, Marauder.METAGROUP, JumpFreighter.METAGROUP, CombatReconShip.METAGROUP, IndustrialCommandShip.METAGROUP, StrategicCruiser.METAGROUP, PrototypeExplorationShip.METAGROUP, AttackBattlecruiser.METAGROUP, BlockadeRunner.METAGROUP, ExpeditionFrigate.METAGROUP, TacticalDestroyer.METAGROUP, LogisticsFrigate.METAGROUP, CommandDestroyer.METAGROUP, ForceAuxiliary.METAGROUP, FlagCruiser.METAGROUP);
        }
    }
}
