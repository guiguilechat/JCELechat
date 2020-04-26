package fr.guiguilechat.jcelechat.model.sde.types.entity;

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
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class InvadingPrecursorEntities
    extends Entity
{
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int BehaviorSiegeDisallowTetheringModifier;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double BehaviorSiegeECMResistanceModifier;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int BehaviorSiegeLocalLogisticsAmountModifier;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int BehaviorSiegeLocalLogisticsDurationModifier;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int BehaviorSiegeMassModifier;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int BehaviorSiegeMaxVelocityModifier;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int BehaviorSiegeMissileDamageModifier;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int BehaviorSiegeRemoteAssistanceImpedanceModifier;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double BehaviorSiegeRemoteRepairImpedanceModifier;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int BehaviorSiegeSensorDampenerResistanceModifier;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int BehaviorSiegeTurretDamageModifier;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int BehaviorSiegeWarpScrambleStatusModifier;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int BehaviorSiegeWeaponDisruptionResistanceModifier;
    /**
     * The agility of the object.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double Agility;
    /**
     * Booster attribute to explosion radius of missiles vs. signature radius.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double AoeCloudSizeBonus;
    /**
     * Increases velocity of missile explosion
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double AoeVelocityBonus;
    /**
     * An amount to modify the armor damage by.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double ArmorDamageAmount;
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
    @DefaultDoubleValue(0.0)
    public double ArmorHP;
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
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int BehaviorArmorRepairerAmount;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int BehaviorArmorRepairerDischarge;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int BehaviorArmorRepairerDuration;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double BehaviorEnergyNeutralizerDischarge;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int BehaviorEnergyNeutralizerDuration;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int BehaviorEnergyNeutralizerFalloff;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int BehaviorEnergyNeutralizerRange;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double BehaviorEnergyNosferatuDischarge;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int BehaviorEnergyNosferatuDuration;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int BehaviorEnergyNosferatuFalloff;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int BehaviorEnergyNosferatuRange;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double BehaviorRemoteArmorRepairDischarge;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double BehaviorRemoteArmorRepairDuration;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int BehaviorRemoteArmorRepairFalloff;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int BehaviorRemoteArmorRepairRange;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double BehaviorSensorDampenerDischarge;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int BehaviorSensorDampenerDuration;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double BehaviorSensorDampenerFalloff;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double BehaviorSensorDampenerRange;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double BehaviorTargetPainterDischarge;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int BehaviorTargetPainterDuration;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int BehaviorTargetPainterFalloff;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int BehaviorTargetPainterRange;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double BehaviorWarpScrambleDischarge;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int BehaviorWarpScrambleDuration;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double BehaviorWarpScrambleRange;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int BehaviorWarpScrambleStrength;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double BehaviorWebifierDischarge;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int BehaviorWebifierDuration;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int BehaviorWebifierFalloff;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int BehaviorWebifierRange;
    /**
     * Capacitor capacity
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double CapacitorCapacity;
    /**
     * The cargo space allowed
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Capacity;
    /**
     * charge of module
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Charge;
    /**
     * Damage multiplier.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double DamageMultiplier;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.5)
    public double DamageMultiplierBonusMax;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double DamageMultiplierBonusPerCycle;
    /**
     * EM damage done.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double EmDamage;
    /**
     * Electro magnetic damage multiplier for shield and armor. Represented as "% Resistance" in the UI.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double EmDamageResonance;
    /**
     * An amount to modify the power of the target by.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double EnergyNeutralizerAmount;
    /**
     * The maximum amount of time stalled before entity chase speed kicks in.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(5000)
    public int EntityChaseMaxDelay;
    /**
     * Chance that the max delay is waited before chase is engaged.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double EntityChaseMaxDelayChance;
    /**
     * The distance outside of which the entity activates their MWD equivalent.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(2500)
    public int EntityChaseMaxDistance;
    /**
     * The maximum amount of time chase is ever engaged for.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(5000)
    public int EntityChaseMaxDuration;
    /**
     * The chance of engaging chase for the maximum duration.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double EntityChaseMaxDurationChance;
    /**
     * The speed that entities fly at when not chasing a target.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double EntityCruiseSpeed;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int EntityEquipmentMax;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int EntityEquipmentMin;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double EntityFactionLoss;
    /**
     * Reward for destroying this entity.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int EntityKillBounty;
    /**
     * Used to increase signature radius of entity when it activates Max Velocity. Used to fake MWD sig radius increase.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(6)
    public int EntityMaxVelocitySignatureRadiusMultiplier;
    /**
     * The type of missiles the entity launches.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int EntityMissileTypeID;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double EntitySecurityMaxGain;
    /**
     * How much security status is modified by for killing this entity.  Depending on the entity, this may be a positive or negative amount.
     * Value is a % movement of the character's current security towards the upper/lower limit.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double EntitySecurityStatusKillBonus;
    /**
     * Autogenerated skill attribute, explosionDelayBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double ExplosionDelayBonus;
    /**
     * Explosive damage done.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double ExplosiveDamage;
    /**
     * damage multiplier vs. explosive damagers.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double ExplosiveDamageResonance;
    /**
     * distance from maximum range at which accuracy has fallen by half
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double Falloff;
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
    @DefaultIntValue(0)
    public int FighterAbilityAntiCapitalMissileResistance;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterAbilityKamikazeResistance;
    /**
     * Graphic ID of the boosters for drone type ships.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int GfxBoosterID;
    /**
     * Graphic ID of the turrets for drone type ships.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int GfxTurretID;
    /**
     * The maximum hitpoints of an object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Hp;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int IsCapitalSize;
    /**
     * Kinetic damage done.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double KineticDamage;
    /**
     * damage multiplier vs. kinetic damagers.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double KineticDamageResonance;
    /**
     * Integer that describes the types mass
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double Mass;
    /**
     * Maximum number of locked targets that the character or their ships electronics can handle at any given time.  Both have individual limits which apply separately.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MaxLockedTargets;
    /**
     * Distance below which range does not affect the to-hit equation.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double MaxRange;
    /**
     * Autogenerated skill attribute, maxRangeBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double MaxRangeBonus;
    /**
     * Maximum range at which the scanner can lock a target.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double MaxTargetRange;
    /**
     * Bonus to Max Targeting Range
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double MaxTargetRangeBonus;
    /**
     * Maximum velocity of ship
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double MaxVelocity;
    /**
     * The maximum possible target range.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(300000)
    public int MaximumRangeCap;
    /**
     * The characters missile use efficiency, scales the damage missiles do.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double MissileDamageMultiplier;
    /**
     * Affects the signature radius of the target in missile impact calculations.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double MissileEntityAoeCloudSizeMultiplier;
    /**
     * Affects the velocity of the target in missile impact calculations.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double MissileEntityAoeVelocityMultiplier;
    /**
     * Multiplier for the missile's flight time.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double MissileEntityFlightTimeMultiplier;
    /**
     * Multiplier for the missile's speed.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double MissileEntityVelocityMultiplier;
    /**
     * Cycle time for a missile launch, in milliseconds.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(20000.0)
    public double MissileLaunchDuration;
    /**
     * Autogenerated skill attribute, missileVelocityBonus 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double MissileVelocityBonus;
    /**
     * NOS override allows a nosferatu module to drain the target capacitor below the current ships capacitor level.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int NosOverride;
    /**
     * Used by Behavior NPCs to work out minimum orbit range. If the npc has an effect with a shorter range, it will use the effects range instead.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int NpcBehaviorMaximumCombatOrbitRange;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double NpcGuidanceDisruptorDischarge;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int NpcGuidanceDisruptorDuration;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int NpcGuidanceDisruptorFalloff;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int NpcGuidanceDisruptorRange;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double NpcTrackingDisruptorDischarge;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int NpcTrackingDisruptorDuration;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int NpcTrackingDisruptorFalloff;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int NpcTrackingDisruptorRange;
    /**
     * Prefered target signature. The base signature radius at which the turret's tracking speed is rated. 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1000)
    public int OptimalSigRadius;
    /**
     * Amount of power to transfer.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int PowerTransferAmount;
    /**
     * Radius of an object in meters
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Radius;
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
     * The resolution that the vessel can target other objects at.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double ScanResolution;
    /**
     * Bonus for scan resolution
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double ScanResolutionBonus;
    /**
     * Amount of maximum shield HP on the item.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double ShieldCapacity;
    /**
     * DO NOT MESS WITH. Helper attribute for entities, stands in for the shield charge.
     * The amount of starting shield capacity of the NPC.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double ShieldCharge;
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
    @DefaultDoubleValue(100.0)
    public double SignatureRadius;
    /**
     * Autogenerated skill attribute, signatureRadiusBonus
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double SignatureRadiusBonus;
    /**
     * Time in milliseconds between possible activations
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double Speed;
    /**
     * Factor by which topspeed increases.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double SpeedFactor;
    /**
     * DO NOT MESS WITH
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double StructureUniformity;
    /**
     * Thermal damage done.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double ThermalDamage;
    /**
     * damage multiplier vs. thermal.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double ThermalDamageResonance;
    /**
     * Weapon accuracy
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double TrackingSpeed;
    /**
     * Tracking Speed Bonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double TrackingSpeedBonus;
    /**
     * This number is deducted from the %chance of the seeping to armor, to slow seep of damage through shield.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Uniformity;
    public static final InvadingPrecursorEntities.MetaGroup METAGROUP = new InvadingPrecursorEntities.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  2645 :
            {
                return BehaviorSiegeDisallowTetheringModifier;
            }
            case  2642 :
            {
                return BehaviorSiegeECMResistanceModifier;
            }
            case  2647 :
            {
                return BehaviorSiegeLocalLogisticsAmountModifier;
            }
            case  2648 :
            {
                return BehaviorSiegeLocalLogisticsDurationModifier;
            }
            case  2646 :
            {
                return BehaviorSiegeMassModifier;
            }
            case  2643 :
            {
                return BehaviorSiegeMaxVelocityModifier;
            }
            case  2730 :
            {
                return BehaviorSiegeMissileDamageModifier;
            }
            case  2639 :
            {
                return BehaviorSiegeRemoteAssistanceImpedanceModifier;
            }
            case  2638 :
            {
                return BehaviorSiegeRemoteRepairImpedanceModifier;
            }
            case  2640 :
            {
                return BehaviorSiegeSensorDampenerResistanceModifier;
            }
            case  2649 :
            {
                return BehaviorSiegeTurretDamageModifier;
            }
            case  2644 :
            {
                return BehaviorSiegeWarpScrambleStatusModifier;
            }
            case  2641 :
            {
                return BehaviorSiegeWeaponDisruptionResistanceModifier;
            }
            case  70 :
            {
                return Agility;
            }
            case  848 :
            {
                return AoeCloudSizeBonus;
            }
            case  847 :
            {
                return AoeVelocityBonus;
            }
            case  84 :
            {
                return ArmorDamageAmount;
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
            case  2635 :
            {
                return BehaviorArmorRepairerAmount;
            }
            case  2634 :
            {
                return BehaviorArmorRepairerDischarge;
            }
            case  2633 :
            {
                return BehaviorArmorRepairerDuration;
            }
            case  2522 :
            {
                return BehaviorEnergyNeutralizerDischarge;
            }
            case  2519 :
            {
                return BehaviorEnergyNeutralizerDuration;
            }
            case  2521 :
            {
                return BehaviorEnergyNeutralizerFalloff;
            }
            case  2520 :
            {
                return BehaviorEnergyNeutralizerRange;
            }
            case  2629 :
            {
                return BehaviorEnergyNosferatuDischarge;
            }
            case  2630 :
            {
                return BehaviorEnergyNosferatuDuration;
            }
            case  2631 :
            {
                return BehaviorEnergyNosferatuFalloff;
            }
            case  2632 :
            {
                return BehaviorEnergyNosferatuRange;
            }
            case  2494 :
            {
                return BehaviorRemoteArmorRepairDischarge;
            }
            case  2491 :
            {
                return BehaviorRemoteArmorRepairDuration;
            }
            case  2493 :
            {
                return BehaviorRemoteArmorRepairFalloff;
            }
            case  2492 :
            {
                return BehaviorRemoteArmorRepairRange;
            }
            case  2530 :
            {
                return BehaviorSensorDampenerDischarge;
            }
            case  2527 :
            {
                return BehaviorSensorDampenerDuration;
            }
            case  2529 :
            {
                return BehaviorSensorDampenerFalloff;
            }
            case  2528 :
            {
                return BehaviorSensorDampenerRange;
            }
            case  2526 :
            {
                return BehaviorTargetPainterDischarge;
            }
            case  2523 :
            {
                return BehaviorTargetPainterDuration;
            }
            case  2525 :
            {
                return BehaviorTargetPainterFalloff;
            }
            case  2524 :
            {
                return BehaviorTargetPainterRange;
            }
            case  2508 :
            {
                return BehaviorWarpScrambleDischarge;
            }
            case  2506 :
            {
                return BehaviorWarpScrambleDuration;
            }
            case  2507 :
            {
                return BehaviorWarpScrambleRange;
            }
            case  2509 :
            {
                return BehaviorWarpScrambleStrength;
            }
            case  2502 :
            {
                return BehaviorWebifierDischarge;
            }
            case  2499 :
            {
                return BehaviorWebifierDuration;
            }
            case  2501 :
            {
                return BehaviorWebifierFalloff;
            }
            case  2500 :
            {
                return BehaviorWebifierRange;
            }
            case  482 :
            {
                return CapacitorCapacity;
            }
            case  38 :
            {
                return Capacity;
            }
            case  18 :
            {
                return Charge;
            }
            case  64 :
            {
                return DamageMultiplier;
            }
            case  2734 :
            {
                return DamageMultiplierBonusMax;
            }
            case  2733 :
            {
                return DamageMultiplierBonusPerCycle;
            }
            case  114 :
            {
                return EmDamage;
            }
            case  113 :
            {
                return EmDamageResonance;
            }
            case  97 :
            {
                return EnergyNeutralizerAmount;
            }
            case  580 :
            {
                return EntityChaseMaxDelay;
            }
            case  581 :
            {
                return EntityChaseMaxDelayChance;
            }
            case  665 :
            {
                return EntityChaseMaxDistance;
            }
            case  582 :
            {
                return EntityChaseMaxDuration;
            }
            case  583 :
            {
                return EntityChaseMaxDurationChance;
            }
            case  508 :
            {
                return EntityCruiseSpeed;
            }
            case  457 :
            {
                return EntityEquipmentMax;
            }
            case  456 :
            {
                return EntityEquipmentMin;
            }
            case  562 :
            {
                return EntityFactionLoss;
            }
            case  481 :
            {
                return EntityKillBounty;
            }
            case  1133 :
            {
                return EntityMaxVelocitySignatureRadiusMultiplier;
            }
            case  507 :
            {
                return EntityMissileTypeID;
            }
            case  563 :
            {
                return EntitySecurityMaxGain;
            }
            case  252 :
            {
                return EntitySecurityStatusKillBonus;
            }
            case  596 :
            {
                return ExplosionDelayBonus;
            }
            case  116 :
            {
                return ExplosiveDamage;
            }
            case  111 :
            {
                return ExplosiveDamageResonance;
            }
            case  158 :
            {
                return Falloff;
            }
            case  349 :
            {
                return FalloffBonus;
            }
            case  2244 :
            {
                return FighterAbilityAntiCapitalMissileResistance;
            }
            case  2433 :
            {
                return FighterAbilityKamikazeResistance;
            }
            case  246 :
            {
                return GfxBoosterID;
            }
            case  245 :
            {
                return GfxTurretID;
            }
            case  9 :
            {
                return Hp;
            }
            case  1785 :
            {
                return IsCapitalSize;
            }
            case  117 :
            {
                return KineticDamage;
            }
            case  109 :
            {
                return KineticDamageResonance;
            }
            case  4 :
            {
                return Mass;
            }
            case  192 :
            {
                return MaxLockedTargets;
            }
            case  54 :
            {
                return MaxRange;
            }
            case  351 :
            {
                return MaxRangeBonus;
            }
            case  76 :
            {
                return MaxTargetRange;
            }
            case  309 :
            {
                return MaxTargetRangeBonus;
            }
            case  37 :
            {
                return MaxVelocity;
            }
            case  797 :
            {
                return MaximumRangeCap;
            }
            case  212 :
            {
                return MissileDamageMultiplier;
            }
            case  858 :
            {
                return MissileEntityAoeCloudSizeMultiplier;
            }
            case  859 :
            {
                return MissileEntityAoeVelocityMultiplier;
            }
            case  646 :
            {
                return MissileEntityFlightTimeMultiplier;
            }
            case  645 :
            {
                return MissileEntityVelocityMultiplier;
            }
            case  506 :
            {
                return MissileLaunchDuration;
            }
            case  547 :
            {
                return MissileVelocityBonus;
            }
            case  1945 :
            {
                return NosOverride;
            }
            case  2786 :
            {
                return NpcBehaviorMaximumCombatOrbitRange;
            }
            case  2514 :
            {
                return NpcGuidanceDisruptorDischarge;
            }
            case  2511 :
            {
                return NpcGuidanceDisruptorDuration;
            }
            case  2513 :
            {
                return NpcGuidanceDisruptorFalloff;
            }
            case  2512 :
            {
                return NpcGuidanceDisruptorRange;
            }
            case  2518 :
            {
                return NpcTrackingDisruptorDischarge;
            }
            case  2515 :
            {
                return NpcTrackingDisruptorDuration;
            }
            case  2517 :
            {
                return NpcTrackingDisruptorFalloff;
            }
            case  2516 :
            {
                return NpcTrackingDisruptorRange;
            }
            case  620 :
            {
                return OptimalSigRadius;
            }
            case  90 :
            {
                return PowerTransferAmount;
            }
            case  162 :
            {
                return Radius;
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
            case  564 :
            {
                return ScanResolution;
            }
            case  566 :
            {
                return ScanResolutionBonus;
            }
            case  263 :
            {
                return ShieldCapacity;
            }
            case  264 :
            {
                return ShieldCharge;
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
            case  554 :
            {
                return SignatureRadiusBonus;
            }
            case  51 :
            {
                return Speed;
            }
            case  20 :
            {
                return SpeedFactor;
            }
            case  525 :
            {
                return StructureUniformity;
            }
            case  118 :
            {
                return ThermalDamage;
            }
            case  110 :
            {
                return ThermalDamageResonance;
            }
            case  160 :
            {
                return TrackingSpeed;
            }
            case  767 :
            {
                return TrackingSpeedBonus;
            }
            case  136 :
            {
                return Uniformity;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public IMetaGroup<InvadingPrecursorEntities> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<InvadingPrecursorEntities>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/InvadingPrecursorEntities.yaml";
        private Map<String, InvadingPrecursorEntities> cache = (null);

        @Override
        public IMetaCategory<? super InvadingPrecursorEntities> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  4028;
        }

        @Override
        public String getName() {
            return "InvadingPrecursorEntities";
        }

        @Override
        public synchronized Map<String, InvadingPrecursorEntities> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(InvadingPrecursorEntities.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, InvadingPrecursorEntities> types;
        }
    }
}
