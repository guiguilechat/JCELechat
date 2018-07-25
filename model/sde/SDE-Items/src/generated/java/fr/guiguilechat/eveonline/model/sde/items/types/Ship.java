package fr.guiguilechat.eveonline.model.sde.items.types;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import fr.guiguilechat.eveonline.model.sde.items.Attribute;
import fr.guiguilechat.eveonline.model.sde.items.Item;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.ship.AssaultFrigate;
import fr.guiguilechat.eveonline.model.sde.items.types.ship.AttackBattlecruiser;
import fr.guiguilechat.eveonline.model.sde.items.types.ship.Battleship;
import fr.guiguilechat.eveonline.model.sde.items.types.ship.BlackOps;
import fr.guiguilechat.eveonline.model.sde.items.types.ship.BlockadeRunner;
import fr.guiguilechat.eveonline.model.sde.items.types.ship.CapitalIndustrialShip;
import fr.guiguilechat.eveonline.model.sde.items.types.ship.Capsule;
import fr.guiguilechat.eveonline.model.sde.items.types.ship.Carrier;
import fr.guiguilechat.eveonline.model.sde.items.types.ship.CombatBattlecruiser;
import fr.guiguilechat.eveonline.model.sde.items.types.ship.CombatReconShip;
import fr.guiguilechat.eveonline.model.sde.items.types.ship.CommandDestroyer;
import fr.guiguilechat.eveonline.model.sde.items.types.ship.CommandShip;
import fr.guiguilechat.eveonline.model.sde.items.types.ship.Corvette;
import fr.guiguilechat.eveonline.model.sde.items.types.ship.CovertOps;
import fr.guiguilechat.eveonline.model.sde.items.types.ship.Cruiser;
import fr.guiguilechat.eveonline.model.sde.items.types.ship.DeepSpaceTransport;
import fr.guiguilechat.eveonline.model.sde.items.types.ship.Destroyer;
import fr.guiguilechat.eveonline.model.sde.items.types.ship.Dreadnought;
import fr.guiguilechat.eveonline.model.sde.items.types.ship.ElectronicAttackShip;
import fr.guiguilechat.eveonline.model.sde.items.types.ship.Exhumer;
import fr.guiguilechat.eveonline.model.sde.items.types.ship.ExpeditionFrigate;
import fr.guiguilechat.eveonline.model.sde.items.types.ship.FlagCruiser;
import fr.guiguilechat.eveonline.model.sde.items.types.ship.ForceAuxiliary;
import fr.guiguilechat.eveonline.model.sde.items.types.ship.ForceReconShip;
import fr.guiguilechat.eveonline.model.sde.items.types.ship.Freighter;
import fr.guiguilechat.eveonline.model.sde.items.types.ship.Frigate;
import fr.guiguilechat.eveonline.model.sde.items.types.ship.HeavyAssaultCruiser;
import fr.guiguilechat.eveonline.model.sde.items.types.ship.HeavyInterdictionCruiser;
import fr.guiguilechat.eveonline.model.sde.items.types.ship.Industrial;
import fr.guiguilechat.eveonline.model.sde.items.types.ship.IndustrialCommandShip;
import fr.guiguilechat.eveonline.model.sde.items.types.ship.Interceptor;
import fr.guiguilechat.eveonline.model.sde.items.types.ship.Interdictor;
import fr.guiguilechat.eveonline.model.sde.items.types.ship.JumpFreighter;
import fr.guiguilechat.eveonline.model.sde.items.types.ship.Logistics;
import fr.guiguilechat.eveonline.model.sde.items.types.ship.LogisticsFrigate;
import fr.guiguilechat.eveonline.model.sde.items.types.ship.Marauder;
import fr.guiguilechat.eveonline.model.sde.items.types.ship.MiningBarge;
import fr.guiguilechat.eveonline.model.sde.items.types.ship.PrototypeExplorationShip;
import fr.guiguilechat.eveonline.model.sde.items.types.ship.Shuttle;
import fr.guiguilechat.eveonline.model.sde.items.types.ship.StealthBomber;
import fr.guiguilechat.eveonline.model.sde.items.types.ship.StrategicCruiser;
import fr.guiguilechat.eveonline.model.sde.items.types.ship.Supercarrier;
import fr.guiguilechat.eveonline.model.sde.items.types.ship.TacticalDestroyer;
import fr.guiguilechat.eveonline.model.sde.items.types.ship.Titan;

public abstract class Ship
    extends Item
{
    /**
     * The agility of the object.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double Agility;
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
     * The number of hit points on the entities armor.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ArmorHP;
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
     * DO NOT MESS WITH
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double ArmorUniformity;
    /**
     * Just for the UI to display the ship warp speed.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int BaseWarpSpeed;
    /**
     * Capacitor capacity
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double CapacitorCapacity;
    /**
     * CPU load of ship
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int CpuLoad;
    /**
     * CPU output of ship
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int CpuOutput;
    /**
     * current structure damage dealt to module
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Damage;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DroneBandwidth;
    /**
     * This defines the total capacity of drones allowed in the drone bay of the ship
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DroneCapacity;
    /**
     * Electro magnetic damage multiplier for shield and armor. Represented as "% Resistance" in the UI.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double EmDamageResonance;
    /**
     * damage multiplier vs. explosive damagers.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double ExplosiveDamageResonance;
    /**
     * Graphic ID of the boosters for drone type ships.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int GfxBoosterID;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int HeatCapacityHi;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int HeatCapacityLow;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int HeatCapacityMed;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double HeatDissipationRateHi;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double HeatDissipationRateLow;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double HeatDissipationRateMed;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double HeatGenerationMultiplier;
    /**
     * The maximum hitpoints of an object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Hp;
    /**
     * damage multiplier vs. kinetic damagers.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double KineticDamageResonance;
    /**
     * The number of remaining unused launcher slots.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int LauncherSlotsLeft;
    /**
     * Maximum number of locked targets that the character or their ships electronics can handle at any given time.  Both have individual limits which apply separately.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MaxLockedTargets;
    /**
     * Maximum range at which the scanner can lock a target.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int MaxTargetRange;
    /**
     * Maximum velocity of ship
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double MaxVelocity;
    /**
     * Authoring has been moved to FSD
     * The ranking of the module within its tech level
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MetaLevel;
    /**
     * Current load of power core
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int PowerLoad;
    /**
     * power output of power core
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int PowerOutput;
    /**
     * tbd
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int PowerToSpeed;
    /**
     * The graphicID of the propulsion system.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int PropulsionGraphicID;
    /**
     * Amount of time taken to fully recharge the capacitor.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double RechargeRate;
    /**
     * Gravimetric strength.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double ScanGravimetricStrength;
    /**
     * Ladar strength.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double ScanLadarStrength;
    /**
     * Magnetometric strength.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double ScanMagnetometricStrength;
    /**
     * Radar strength.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double ScanRadarStrength;
    /**
     * Amount of maximum shield HP on the item.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShieldCapacity;
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
     * Amount of time taken to fully recharge the shield.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double ShieldRechargeRate;
    /**
     * Multiplies THERMAL damage taken by Shield. 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double ShieldThermalDamageResonance;
    /**
     * DO NOT MESS WITH This number is deducted from the %chance of the seeping to armor, to slow seep of damage through shield.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double ShieldUniformity;
    /**
     * Signature Radius is used for turret tracking and scanning.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(100)
    public int SignatureRadius;
    /**
     * DO NOT MESS WITH
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double StructureUniformity;
    /**
     * Authoring has been moved to FSD
     * Tech level of an item
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int TechLevel;
    /**
     * damage multiplier vs. thermal.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double ThermalDamageResonance;
    /**
     * Remaining number of unused turret slots on the ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int TurretSlotsLeft;
    /**
     * This number is deducted from the %chance of the seeping to armor, to slow seep of damage through shield.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Uniformity;
    /**
     * The power cost to warp per one kg per AU (floats do not have the resolution for meters).
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double WarpCapacitorNeed;
    /**
     * tbd instance param
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int WarpFactor;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(3.0)
    public double WarpSpeedMultiplier;

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  70 :
            {
                return Agility;
            }
            case  267 :
            {
                return ArmorEmDamageResonance;
            }
            case  268 :
            {
                return ArmorExplosiveDamageResonance;
            }
            case  265 :
            {
                return ArmorHP;
            }
            case  269 :
            {
                return ArmorKineticDamageResonance;
            }
            case  270 :
            {
                return ArmorThermalDamageResonance;
            }
            case  524 :
            {
                return ArmorUniformity;
            }
            case  1281 :
            {
                return BaseWarpSpeed;
            }
            case  482 :
            {
                return CapacitorCapacity;
            }
            case  49 :
            {
                return CpuLoad;
            }
            case  48 :
            {
                return CpuOutput;
            }
            case  3 :
            {
                return Damage;
            }
            case  1271 :
            {
                return DroneBandwidth;
            }
            case  283 :
            {
                return DroneCapacity;
            }
            case  113 :
            {
                return EmDamageResonance;
            }
            case  111 :
            {
                return ExplosiveDamageResonance;
            }
            case  246 :
            {
                return GfxBoosterID;
            }
            case  1178 :
            {
                return HeatCapacityHi;
            }
            case  1200 :
            {
                return HeatCapacityLow;
            }
            case  1199 :
            {
                return HeatCapacityMed;
            }
            case  1179 :
            {
                return HeatDissipationRateHi;
            }
            case  1198 :
            {
                return HeatDissipationRateLow;
            }
            case  1196 :
            {
                return HeatDissipationRateMed;
            }
            case  1224 :
            {
                return HeatGenerationMultiplier;
            }
            case  9 :
            {
                return Hp;
            }
            case  109 :
            {
                return KineticDamageResonance;
            }
            case  101 :
            {
                return LauncherSlotsLeft;
            }
            case  192 :
            {
                return MaxLockedTargets;
            }
            case  76 :
            {
                return MaxTargetRange;
            }
            case  37 :
            {
                return MaxVelocity;
            }
            case  633 :
            {
                return MetaLevel;
            }
            case  15 :
            {
                return PowerLoad;
            }
            case  11 :
            {
                return PowerOutput;
            }
            case  19 :
            {
                return PowerToSpeed;
            }
            case  217 :
            {
                return PropulsionGraphicID;
            }
            case  55 :
            {
                return RechargeRate;
            }
            case  211 :
            {
                return ScanGravimetricStrength;
            }
            case  209 :
            {
                return ScanLadarStrength;
            }
            case  210 :
            {
                return ScanMagnetometricStrength;
            }
            case  208 :
            {
                return ScanRadarStrength;
            }
            case  263 :
            {
                return ShieldCapacity;
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
            case  479 :
            {
                return ShieldRechargeRate;
            }
            case  274 :
            {
                return ShieldThermalDamageResonance;
            }
            case  484 :
            {
                return ShieldUniformity;
            }
            case  552 :
            {
                return SignatureRadius;
            }
            case  525 :
            {
                return StructureUniformity;
            }
            case  422 :
            {
                return TechLevel;
            }
            case  110 :
            {
                return ThermalDamageResonance;
            }
            case  102 :
            {
                return TurretSlotsLeft;
            }
            case  136 :
            {
                return Uniformity;
            }
            case  153 :
            {
                return WarpCapacitorNeed;
            }
            case  21 :
            {
                return WarpFactor;
            }
            case  600 :
            {
                return WarpSpeedMultiplier;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public int getCategoryId() {
        return  6;
    }

    @Override
    public Class<?> getCategory() {
        return Ship.class;
    }

    public static Map<String, ? extends Ship> loadCategory() {
        return Stream.of(AssaultFrigate.load(), AttackBattlecruiser.load(), Battleship.load(), BlackOps.load(), BlockadeRunner.load(), CapitalIndustrialShip.load(), Capsule.load(), Carrier.load(), CombatBattlecruiser.load(), CombatReconShip.load(), CommandDestroyer.load(), CommandShip.load(), Corvette.load(), CovertOps.load(), Cruiser.load(), DeepSpaceTransport.load(), Destroyer.load(), Dreadnought.load(), ElectronicAttackShip.load(), Exhumer.load(), ExpeditionFrigate.load(), FlagCruiser.load(), ForceAuxiliary.load(), ForceReconShip.load(), Freighter.load(), Frigate.load(), HeavyAssaultCruiser.load(), HeavyInterdictionCruiser.load(), Industrial.load(), IndustrialCommandShip.load(), Interceptor.load(), Interdictor.load(), JumpFreighter.load(), Logistics.load(), LogisticsFrigate.load(), Marauder.load(), MiningBarge.load(), PrototypeExplorationShip.load(), Shuttle.load(), StealthBomber.load(), StrategicCruiser.load(), Supercarrier.load(), TacticalDestroyer.load(), Titan.load()).flatMap((m -> m.entrySet().stream())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
