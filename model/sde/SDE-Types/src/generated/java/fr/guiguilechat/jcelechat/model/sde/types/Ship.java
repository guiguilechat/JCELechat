package fr.guiguilechat.jcelechat.model.sde.types;

import java.util.Arrays;
import java.util.Collection;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.types.ship.AssaultFrigate;
import fr.guiguilechat.jcelechat.model.sde.types.ship.AttackBattlecruiser;
import fr.guiguilechat.jcelechat.model.sde.types.ship.Battleship;
import fr.guiguilechat.jcelechat.model.sde.types.ship.BlackOps;
import fr.guiguilechat.jcelechat.model.sde.types.ship.BlockadeRunner;
import fr.guiguilechat.jcelechat.model.sde.types.ship.CapitalIndustrialShip;
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
import fr.guiguilechat.jcelechat.model.sde.types.ship.HeavyAssaultCruiser;
import fr.guiguilechat.jcelechat.model.sde.types.ship.HeavyInterdictionCruiser;
import fr.guiguilechat.jcelechat.model.sde.types.ship.Industrial;
import fr.guiguilechat.jcelechat.model.sde.types.ship.IndustrialCommandShip;
import fr.guiguilechat.jcelechat.model.sde.types.ship.Interceptor;
import fr.guiguilechat.jcelechat.model.sde.types.ship.Interdictor;
import fr.guiguilechat.jcelechat.model.sde.types.ship.JumpFreighter;
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
     * tbd
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int HiSlots;
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
     * The number of low power slots on the ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int LowSlots;
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
     * tbd
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MedSlots;
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
     * The value of this attribute is a graphicsID which controls the color scheme of this type. It is used to apply said color scheme to items of other types whose gfx representation is tied in with the attribute holder. Example: Turrets on ships.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int TypeColorScheme;
    /**
     * This number is deducted from the %chance of the seeping to armor, to slow seep of damage through shield.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Uniformity;
    /**
     * Attribute on ships used for ship upgrades
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int UpgradeCapacity;
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
    public static final Ship.MetaCat METACAT = new Ship.MetaCat();

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
            case  14 :
            {
                return HiSlots;
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
            case  12 :
            {
                return LowSlots;
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
            case  13 :
            {
                return MedSlots;
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
            case  182 :
            {
                return RequiredSkill1;
            }
            case  277 :
            {
                return RequiredSkill1Level;
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
            case  1768 :
            {
                return TypeColorScheme;
            }
            case  136 :
            {
                return Uniformity;
            }
            case  1132 :
            {
                return UpgradeCapacity;
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
            return Arrays.asList(Frigate.METAGROUP, Cruiser.METAGROUP, Battleship.METAGROUP, Industrial.METAGROUP, Titan.METAGROUP, Shuttle.METAGROUP, Corvette.METAGROUP, AssaultFrigate.METAGROUP, HeavyAssaultCruiser.METAGROUP, DeepSpaceTransport.METAGROUP, CombatBattlecruiser.METAGROUP, Destroyer.METAGROUP, MiningBarge.METAGROUP, Dreadnought.METAGROUP, Freighter.METAGROUP, CommandShip.METAGROUP, Interdictor.METAGROUP, Exhumer.METAGROUP, Carrier.METAGROUP, Supercarrier.METAGROUP, CovertOps.METAGROUP, Interceptor.METAGROUP, Logistics.METAGROUP, ForceReconShip.METAGROUP, StealthBomber.METAGROUP, CapitalIndustrialShip.METAGROUP, ElectronicAttackShip.METAGROUP, HeavyInterdictionCruiser.METAGROUP, BlackOps.METAGROUP, Marauder.METAGROUP, JumpFreighter.METAGROUP, CombatReconShip.METAGROUP, IndustrialCommandShip.METAGROUP, StrategicCruiser.METAGROUP, PrototypeExplorationShip.METAGROUP, AttackBattlecruiser.METAGROUP, BlockadeRunner.METAGROUP, ExpeditionFrigate.METAGROUP, TacticalDestroyer.METAGROUP, LogisticsFrigate.METAGROUP, CommandDestroyer.METAGROUP, ForceAuxiliary.METAGROUP, FlagCruiser.METAGROUP);
        }
    }
}
