package fr.guiguilechat.jcelechat.model.sde.types.entity;

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
import fr.guiguilechat.jcelechat.model.sde.attributes.AIChanceToNotTargetSwitch;
import fr.guiguilechat.jcelechat.model.sde.attributes.AIIgnoreDronesBelowSignatureRadius;
import fr.guiguilechat.jcelechat.model.sde.attributes.AIShouldUseEffectMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.AIShouldUseSecondaryTarget;
import fr.guiguilechat.jcelechat.model.sde.attributes.AIShouldUseSignatureRadius;
import fr.guiguilechat.jcelechat.model.sde.attributes.AIShouldUseTargetSwitching;
import fr.guiguilechat.jcelechat.model.sde.attributes.AITankingModifierDrone;
import fr.guiguilechat.jcelechat.model.sde.attributes.AITargetSwitchTimer;
import fr.guiguilechat.jcelechat.model.sde.attributes.ActivationBlockedStrenght;
import fr.guiguilechat.jcelechat.model.sde.attributes.Agility;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorEmDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorExplosiveDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorHP;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorKineticDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorThermalDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorUniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.CapacitorCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.Charge;
import fr.guiguilechat.jcelechat.model.sde.attributes.DamageMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.DisallowAssistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.EmDamage;
import fr.guiguilechat.jcelechat.model.sde.attributes.EnergyNeutralizerAmount;
import fr.guiguilechat.jcelechat.model.sde.attributes.EnergyNeutralizerDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.EnergyNeutralizerEntityChance;
import fr.guiguilechat.jcelechat.model.sde.attributes.EnergyNeutralizerRangeOptimal;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityArmorRepairAmount;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityArmorRepairAmountPerSecond;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityArmorRepairDelayChance;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityArmorRepairDelayChanceSmall;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityArmorRepairDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityAttackDelayMax;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityAttackDelayMin;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityAttackRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityBracketColour;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityChaseMaxDelay;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityChaseMaxDelayChance;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityChaseMaxDistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityChaseMaxDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityChaseMaxDurationChance;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityCruiseSpeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityDefenderChance;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityEquipmentGroupMax;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityEquipmentMax;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityEquipmentMin;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityFactionLoss;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityFlyRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityGroupRespawnChance;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityKillBounty;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityLootCountMax;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityLootCountMin;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityMissileTypeID;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityOverviewShipGroupId;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityReactionFactor;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntitySecurityMaxGain;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntitySecurityStatusKillBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityShieldBoostAmount;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityShieldBoostAmountPerSecond;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityShieldBoostDelayChance;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityShieldBoostDelayChanceSmall;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityShieldBoostDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityWarpScrambleChance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ExplosiveDamage;
import fr.guiguilechat.jcelechat.model.sde.attributes.Falloff;
import fr.guiguilechat.jcelechat.model.sde.attributes.FalloffBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.GfxBoosterID;
import fr.guiguilechat.jcelechat.model.sde.attributes.GfxTurretID;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hp;
import fr.guiguilechat.jcelechat.model.sde.attributes.HullEmDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.HullExplosiveDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.HullKineticDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.HullThermalDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.KineticDamage;
import fr.guiguilechat.jcelechat.model.sde.attributes.Mass;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxAttackTargets;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxDirectionalVelocity;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxLockedTargets;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxRangeBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxVelocity;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxVelocityMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.MinTargetVelDmgMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.MissileDamageMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.MissileEntityAoeCloudSizeMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.MissileEntityAoeVelocityMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.MissileEntityFlightTimeMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.MissileEntityVelocityMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.MissileLaunchDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.ModifyTargetSpeedChance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ModifyTargetSpeedDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.ModifyTargetSpeedRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.NpcAssistanceRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.NpcRemoteArmorRepairAmount;
import fr.guiguilechat.jcelechat.model.sde.attributes.NpcRemoteArmorRepairChance;
import fr.guiguilechat.jcelechat.model.sde.attributes.NpcRemoteArmorRepairDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.NpcRemoteArmorRepairMaxTargets;
import fr.guiguilechat.jcelechat.model.sde.attributes.NpcRemoteArmorRepairThreshold;
import fr.guiguilechat.jcelechat.model.sde.attributes.NpcRemoteShieldBoostAmount;
import fr.guiguilechat.jcelechat.model.sde.attributes.NpcRemoteShieldBoostChance;
import fr.guiguilechat.jcelechat.model.sde.attributes.NpcRemoteShieldBoostDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.NpcRemoteShieldBoostMaxTargets;
import fr.guiguilechat.jcelechat.model.sde.attributes.NpcRemoteShieldBoostThreshold;
import fr.guiguilechat.jcelechat.model.sde.attributes.NpcTrackingDisruptorActivationChance;
import fr.guiguilechat.jcelechat.model.sde.attributes.NpcTrackingDisruptorDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.NpcTrackingDisruptorRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.OptimalSigRadius;
import fr.guiguilechat.jcelechat.model.sde.attributes.PropulsionGraphicID;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.RechargeRate;
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
import fr.guiguilechat.jcelechat.model.sde.attributes.SignatureRadius;
import fr.guiguilechat.jcelechat.model.sde.attributes.Speed;
import fr.guiguilechat.jcelechat.model.sde.attributes.SpeedFactor;
import fr.guiguilechat.jcelechat.model.sde.attributes.StructureUniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.ThermalDamage;
import fr.guiguilechat.jcelechat.model.sde.attributes.TrackingSpeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.TrackingSpeedBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.Uniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarpScrambleDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarpScrambleRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarpScrambleStrength;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionGenericFrigates
    extends Entity
{
    /**
     * A percentage chance to not change targets 0.0 - 1.0. 1.0 they will never change targets 0.0 they will always change targets
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double aichancetonottargetswitch;
    /**
     * NPC'S with this attribute wont shoot drones with signature radius less than this value.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int aiignoredronesbelowsignatureradius;
    /**
     * Should the entity watch for effects when choosing targets
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int aishoulduseeffectmultiplier;
    /**
     * Should use secondary effect on other targets?
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int aishouldusesecondarytarget;
    /**
     * Should this type use signature radius
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int aishouldusesignatureradius;
    /**
     * This controls how L1 AI target switches
     * When disabled AI_ChanceToNotTargetSwitch, AI_ShouldUseEffectMultiplier, and AI_ShouldUseSignatureRadius are disabled also.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int aishouldusetargetswitching;
    /**
     * Tanking modifier applied to drones if their owner is tanking. 1.0 is no modifier
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.699999988079071)
    public double aitankingmodifierdrone;
    /**
     * This controls the time that must pass between one target switch and another!
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(60000)
    public int aitargetswitchtimer;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int activationblockedstrenght;
    /**
     * The agility of the object.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double agility;
    /**
     * Multiplies EM damage taken by Armor. 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double armoremdamageresonance;
    /**
     * Multiplies EXPLOSIVE damage taken by Armor. 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double armorexplosivedamageresonance;
    /**
     * The number of hit points on the entities armor.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double armorhp;
    /**
     * Multiplies KINETIC damage taken by Armor. 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double armorkineticdamageresonance;
    /**
     * Multiplies THERMAL damage taken by Armor. 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double armorthermaldamageresonance;
    /**
     * DO NOT MESS WITH
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double armoruniformity;
    /**
     * Capacitor capacity
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double capacitorcapacity;
    /**
     * The cargo space allowed
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double capacity;
    /**
     * charge of module
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int charge;
    /**
     * Damage multiplier.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double damagemultiplier;
    /**
     * If this module is in use and this attribute is 1, then assistance modules cannot be used on the ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int disallowassistance;
    /**
     * EM damage done.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double emdamage;
    /**
     * An amount to modify the power of the target by.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double energyneutralizeramount;
    /**
     * Duration of NPC Energy Neutralizer effect
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(30000)
    public int energyneutralizerduration;
    /**
     * Chance of NPC effect to be activated each duration
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double energyneutralizerentitychance;
    /**
     * Optimal Range of Energy Neutralizer
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int energyneutralizerrangeoptimal;
    /**
     * Amount of armor repaired per cycle for entities.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double entityarmorrepairamount;
    /**
     * the average armor amount repaired per second
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double entityarmorrepairamountpersecond;
    /**
     * Chance that an entity will delay employing armor repair.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double entityarmorrepairdelaychance;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double entityarmorrepairdelaychancesmall;
    /**
     * Duration between armor repair actions for entities.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int entityarmorrepairduration;
    /**
     * Maximum attack delay time for entity.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int entityattackdelaymax;
    /**
     * Minimum attack delay time for entity.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int entityattackdelaymin;
    /**
     * The distance from a target an entity starts using its weapons.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(15000)
    public int entityattackrange;
    /**
     *  0: white (default)
     *  1: red (hostile NPC)
     *  2: blue (Neutral NPC)
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int entitybracketcolour;
    /**
     * The maximum amount of time stalled before entity chase speed kicks in.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(5000)
    public int entitychasemaxdelay;
    /**
     * Chance that the max delay is waited before chase is engaged.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double entitychasemaxdelaychance;
    /**
     * The distance outside of which the entity activates their MWD equivalent.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(2500)
    public int entitychasemaxdistance;
    /**
     * The maximum amount of time chase is ever engaged for.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(5000)
    public int entitychasemaxduration;
    /**
     * The chance of engaging chase for the maximum duration.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double entitychasemaxdurationchance;
    /**
     * The speed that entities fly at when not chasing a target.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double entitycruisespeed;
    /**
     * % chance of entity to shoot defender at incoming missile
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double entitydefenderchance;
    /**
     * The maximum drops of same group (example: entity can only drop 1 of group: energy laser)
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int entityequipmentgroupmax;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int entityequipmentmax;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int entityequipmentmin;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double entityfactionloss;
    /**
     * The distance at which the entity orbits, follows.. and more.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(500.0)
    public double entityflyrange;
    /**
     * The chance an entity will respawn into his group if destroyed.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int entitygrouprespawnchance;
    /**
     * Reward for destroying this entity.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int entitykillbounty;
    /**
     * The maximum number of pieces of loot dropped by this entity.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int entitylootcountmax;
    /**
     * Deprecated. The minimum number of pieces of loot dropped by this entity.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int entitylootcountmin;
    /**
     * The type of missiles the entity launches.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int entitymissiletypeid;
    /**
     * This attribute is used on entities to link them to a player ship group. This is then used to determine which overview icon they should get, among other things
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int entityoverviewshipgroupid;
    /**
     * The chance of an entity attacking the same person as its group members.  Scales delay in joining in on fights too.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double entityreactionfactor;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double entitysecuritymaxgain;
    /**
     * How much security status is modified by for killing this entity.  Depending on the entity, this may be a positive or negative amount.
     * Value is a % movement of the character's current security towards the upper/lower limit.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double entitysecuritystatuskillbonus;
    /**
     * How much the shield is boosted each duration.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double entityshieldboostamount;
    /**
     * the average shield amount regenerated per second
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double entityshieldboostamountpersecond;
    /**
     * The chance an entity will delay repeating use of its shield boosting effect if it has one.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double entityshieldboostdelaychance;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double entityshieldboostdelaychancesmall;
    /**
     * How long between repeats.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(10000)
    public int entityshieldboostduration;
    /**
     * A relative strength that indicates how powerful this NPC entity is in combat.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int entitystrength;
    /**
     * Chance of entity warp scrambling it's target.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double entitywarpscramblechance;
    /**
     * Explosive damage done.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double explosivedamage;
    /**
     * distance from maximum range at which accuracy has fallen by half
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double falloff;
    /**
     * Autogenerated skill attribute, falloffBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double falloffbonus;
    /**
     * Graphic ID of the boosters for drone type ships.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int gfxboosterid;
    /**
     * Graphic ID of the turrets for drone type ships.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int gfxturretid;
    /**
     * The maximum hitpoints of an object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double hp;
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
     * Kinetic damage done.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double kineticdamage;
    /**
     * Integer that describes the types mass
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double mass;
    /**
     * The maximum number of their targets that the character can attack at a given time.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxattacktargets;
    /**
     * Deprecated.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int maxdirectionalvelocity;
    /**
     * Maximum number of locked targets that the character or their ships electronics can handle at any given time.  Both have individual limits which apply separately.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxlockedtargets;
    /**
     * Distance below which range does not affect the to-hit equation.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double maxrange;
    /**
     * Autogenerated skill attribute, maxRangeBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double maxrangebonus;
    /**
     * Maximum velocity of ship
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double maxvelocity;
    /**
     * Maximum velocity multiplier
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double maxvelocitymultiplier;
    /**
     * Deprecated.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double mintargetveldmgmultiplier;
    /**
     * The characters missile use efficiency, scales the damage missiles do.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double missiledamagemultiplier;
    /**
     * Affects the signature radius of the target in missile impact calculations.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double missileentityaoecloudsizemultiplier;
    /**
     * Affects the velocity of the target in missile impact calculations.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double missileentityaoevelocitymultiplier;
    /**
     * Multiplier for the missile's flight time.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double missileentityflighttimemultiplier;
    /**
     * Multiplier for the missile's speed.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double missileentityvelocitymultiplier;
    /**
     * Cycle time for a missile launch, in milliseconds.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(20000.0)
    public double missilelaunchduration;
    /**
     * Chance that  an entity will use a Stasis Web on a target.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double modifytargetspeedchance;
    /**
     * Duration of entities Stasis Web 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(5000.0)
    public double modifytargetspeedduration;
    /**
     * Range of entities Stasis Web
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(20000)
    public int modifytargetspeedrange;
    /**
     * Maximum distance to a friendly NPC so that remote repairs may be performed on it.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(5000)
    public int npcassistancerange;
    /**
     * the amount of armor that is repaired per cycle to each target
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int npcremotearmorrepairamount;
    /**
     * the chance of the NPC remote reapiring it's comrads.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double npcremotearmorrepairchance;
    /**
     * How long NPC take to remote repair ther comerad in MS.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(10000)
    public int npcremotearmorrepairduration;
    /**
     * The maximum number of targets that can be repaired at once.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int npcremotearmorrepairmaxtargets;
    /**
     * How damaged does a teammate's armor need to be before it will be repaired.
     *  0.1 means: Must be below 90% armor to get repairs
     *  0.9 means: Must be below 10% armor to get repairs
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.25)
    public double npcremotearmorrepairthreshold;
    /**
     * How many shields points does the activation of the effect bestow upon the target
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(50)
    public int npcremoteshieldboostamount;
    /**
     * Chance of the remote shield boosting effect being used
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int npcremoteshieldboostchance;
    /**
     * Duration of shield boost effect
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(20000)
    public int npcremoteshieldboostduration;
    /**
     * The maximum number of targets that can be shield boosted at once
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int npcremoteshieldboostmaxtargets;
    /**
     * How damaged does a teammates shield need to be before it'll be repaired.
     *  0.1 means: Must be below 90% shields to get repairs
     *  0.9 means: Must be below 10% shields to get repairs
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.75)
    public double npcremoteshieldboostthreshold;
    /**
     * Chance of NPC effect to be activated each duration
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double npctrackingdisruptoractivationchance;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int npctrackingdisruptorduration;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int npctrackingdisruptorrange;
    /**
     * Prefered target signature. The base signature radius at which the turret's tracking speed is rated. 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1000)
    public int optimalsigradius;
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
    @DefaultDoubleValue(0.0)
    public double radius;
    /**
     * Amount of time taken to fully recharge the capacitor.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double rechargerate;
    /**
     * Gravimetric strength.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double scangravimetricstrength;
    /**
     * Ladar strength.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double scanladarstrength;
    /**
     * Magnetometric strength.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double scanmagnetometricstrength;
    /**
     * Radar strength.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double scanradarstrength;
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
     * Amount of maximum shield HP on the item.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double shieldcapacity;
    /**
     * DO NOT MESS WITH. Helper attribute for entities, stands in for the shield charge.
     * The amount of starting shield capacity of the NPC.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double shieldcharge;
    /**
     * Multiplies EM damage taken by shield
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double shieldemdamageresonance;
    /**
     * Multiplies EXPLOSIVE damage taken by Armor. 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double shieldexplosivedamageresonance;
    /**
     * Multiplies KINETIC damage taken by Armor. 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double shieldkineticdamageresonance;
    /**
     * Amount of time taken to fully recharge the shield.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double shieldrechargerate;
    /**
     * Multiplies THERMAL damage taken by Shield. 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double shieldthermaldamageresonance;
    /**
     * DO NOT MESS WITH This number is deducted from the %chance of the seeping to armor, to slow seep of damage through shield.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double shielduniformity;
    /**
     * Signature Radius is used for turret tracking and scanning.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(100.0)
    public double signatureradius;
    /**
     * Time in milliseconds between possible activations
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double speed;
    /**
     * Factor by which topspeed increases.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double speedfactor;
    /**
     * DO NOT MESS WITH
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double structureuniformity;
    /**
     * Thermal damage done.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double thermaldamage;
    /**
     * Weapon accuracy
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double trackingspeed;
    /**
     * Tracking Speed Bonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double trackingspeedbonus;
    /**
     * This number is deducted from the %chance of the seeping to armor, to slow seep of damage through shield.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double uniformity;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(8000)
    public int warpscrambleduration;
    /**
     * Maximum range objects can be warp scrambled from.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int warpscramblerange;
    /**
     * Amount to modify ships warp scramble status by.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int warpscramblestrength;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {ModifyTargetSpeedChance.INSTANCE, ModifyTargetSpeedDuration.INSTANCE, ModifyTargetSpeedRange.INSTANCE, Mass.INSTANCE, ShieldCapacity.INSTANCE, ShieldCharge.INSTANCE, Hp.INSTANCE, ArmorHP.INSTANCE, ArmorEmDamageResonance.INSTANCE, ArmorExplosiveDamageResonance.INSTANCE, ArmorUniformity.INSTANCE, ArmorKineticDamageResonance.INSTANCE, StructureUniformity.INSTANCE, ArmorThermalDamageResonance.INSTANCE, ShieldEmDamageResonance.INSTANCE, ShieldExplosiveDamageResonance.INSTANCE, ShieldKineticDamageResonance.INSTANCE, ShieldThermalDamageResonance.INSTANCE, Charge.INSTANCE, SpeedFactor.INSTANCE, EntityBracketColour.INSTANCE, EntityStrength.INSTANCE, MaxVelocity.INSTANCE, Capacity.INSTANCE, SignatureRadius.INSTANCE, EntityFactionLoss.INSTANCE, Speed.INSTANCE, EntitySecurityMaxGain.INSTANCE, ScanResolution.INSTANCE, MaxRange.INSTANCE, RechargeRate.INSTANCE, AIIgnoreDronesBelowSignatureRadius.INSTANCE, DamageMultiplier.INSTANCE, EntityChaseMaxDelay.INSTANCE, EntityChaseMaxDelayChance.INSTANCE, EntityChaseMaxDuration.INSTANCE, ActivationBlockedStrenght.INSTANCE, Agility.INSTANCE, EntityChaseMaxDurationChance.INSTANCE, ScanSpeed.INSTANCE, DisallowAssistance.INSTANCE, MissileEntityAoeCloudSizeMultiplier.INSTANCE, MissileEntityAoeVelocityMultiplier.INSTANCE, FalloffBonus.INSTANCE, MaxRangeBonus.INSTANCE, EnergyNeutralizerAmount.INSTANCE, EnergyNeutralizerRangeOptimal.INSTANCE, EntityArmorRepairAmountPerSecond.INSTANCE, EntityShieldBoostAmountPerSecond.INSTANCE, WarpScrambleRange.INSTANCE, WarpScrambleStrength.INSTANCE, OptimalSigRadius.INSTANCE, AIShouldUseTargetSwitching.INSTANCE, AIShouldUseSecondaryTarget.INSTANCE, AIShouldUseSignatureRadius.INSTANCE, EmDamage.INSTANCE, AIChanceToNotTargetSwitch.INSTANCE, AIShouldUseEffectMultiplier.INSTANCE, ExplosiveDamage.INSTANCE, KineticDamage.INSTANCE, ThermalDamage.INSTANCE, EntityArmorRepairDuration.INSTANCE, EntityArmorRepairAmount.INSTANCE, AITankingModifierDrone.INSTANCE, EntityShieldBoostDuration.INSTANCE, EntityShieldBoostAmount.INSTANCE, EntityArmorRepairDelayChance.INSTANCE, EntityShieldBoostDelayChance.INSTANCE, EntityGroupRespawnChance.INSTANCE, MissileEntityVelocityMultiplier.INSTANCE, MissileEntityFlightTimeMultiplier.INSTANCE, AITargetSwitchTimer.INSTANCE, Uniformity.INSTANCE, MaxDirectionalVelocity.INSTANCE, MinTargetVelDmgMultiplier.INSTANCE, EntityChaseMaxDistance.INSTANCE, Falloff.INSTANCE, TrackingSpeed.INSTANCE, EntityFlyRange.INSTANCE, Radius.INSTANCE, EnergyNeutralizerEntityChance.INSTANCE, NpcTrackingDisruptorActivationChance.INSTANCE, NpcRemoteArmorRepairChance.INSTANCE, EnergyNeutralizerDuration.INSTANCE, NpcRemoteArmorRepairDuration.INSTANCE, NpcRemoteArmorRepairAmount.INSTANCE, NpcRemoteArmorRepairThreshold.INSTANCE, NpcRemoteShieldBoostDuration.INSTANCE, NpcRemoteShieldBoostChance.INSTANCE, NpcRemoteShieldBoostAmount.INSTANCE, NpcRemoteShieldBoostThreshold.INSTANCE, NpcAssistanceRange.INSTANCE, MaxVelocityMultiplier.INSTANCE, MaxLockedTargets.INSTANCE, MaxAttackTargets.INSTANCE, EntityEquipmentMin.INSTANCE, EntityEquipmentMax.INSTANCE, HullEmDamageResonance.INSTANCE, HullExplosiveDamageResonance.INSTANCE, ScanRadarStrength.INSTANCE, HullKineticDamageResonance.INSTANCE, ScanLadarStrength.INSTANCE, EntityEquipmentGroupMax.INSTANCE, HullThermalDamageResonance.INSTANCE, ScanMagnetometricStrength.INSTANCE, EntityReactionFactor.INSTANCE, ScanGravimetricStrength.INSTANCE, NpcTrackingDisruptorDuration.INSTANCE, MissileDamageMultiplier.INSTANCE, NpcTrackingDisruptorRange.INSTANCE, PropulsionGraphicID.INSTANCE, EntityAttackDelayMin.INSTANCE, EntityAttackDelayMax.INSTANCE, NpcRemoteArmorRepairMaxTargets.INSTANCE, NpcRemoteShieldBoostMaxTargets.INSTANCE, ShieldRechargeRate.INSTANCE, EntityKillBounty.INSTANCE, CapacitorCapacity.INSTANCE, ShieldUniformity.INSTANCE, EntityOverviewShipGroupId.INSTANCE, EntityShieldBoostDelayChanceSmall.INSTANCE, EntityDefenderChance.INSTANCE, EntityArmorRepairDelayChanceSmall.INSTANCE, GfxTurretID.INSTANCE, GfxBoosterID.INSTANCE, EntityAttackRange.INSTANCE, EntityWarpScrambleChance.INSTANCE, WarpScrambleDuration.INSTANCE, MissileLaunchDuration.INSTANCE, EntityLootCountMin.INSTANCE, EntityMissileTypeID.INSTANCE, EntityLootCountMax.INSTANCE, EntityCruiseSpeed.INSTANCE, EntitySecurityStatusKillBonus.INSTANCE, TrackingSpeedBonus.INSTANCE })));
    public static final MissionGenericFrigates.MetaGroup METAGROUP = new MissionGenericFrigates.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  1651 :
            {
                return aichancetonottargetswitch;
            }
            case  1855 :
            {
                return aiignoredronesbelowsignatureradius;
            }
            case  1652 :
            {
                return aishoulduseeffectmultiplier;
            }
            case  1649 :
            {
                return aishouldusesecondarytarget;
            }
            case  1650 :
            {
                return aishouldusesignatureradius;
            }
            case  1648 :
            {
                return aishouldusetargetswitching;
            }
            case  1656 :
            {
                return aitankingmodifierdrone;
            }
            case  1416 :
            {
                return aitargetswitchtimer;
            }
            case  1350 :
            {
                return activationblockedstrenght;
            }
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
            case  482 :
            {
                return capacitorcapacity;
            }
            case  38 :
            {
                return capacity;
            }
            case  18 :
            {
                return charge;
            }
            case  64 :
            {
                return damagemultiplier;
            }
            case  854 :
            {
                return disallowassistance;
            }
            case  114 :
            {
                return emdamage;
            }
            case  97 :
            {
                return energyneutralizeramount;
            }
            case  942 :
            {
                return energyneutralizerduration;
            }
            case  931 :
            {
                return energyneutralizerentitychance;
            }
            case  98 :
            {
                return energyneutralizerrangeoptimal;
            }
            case  631 :
            {
                return entityarmorrepairamount;
            }
            case  1892 :
            {
                return entityarmorrepairamountpersecond;
            }
            case  638 :
            {
                return entityarmorrepairdelaychance;
            }
            case  1009 :
            {
                return entityarmorrepairdelaychancesmall;
            }
            case  630 :
            {
                return entityarmorrepairduration;
            }
            case  476 :
            {
                return entityattackdelaymax;
            }
            case  475 :
            {
                return entityattackdelaymin;
            }
            case  247 :
            {
                return entityattackrange;
            }
            case  798 :
            {
                return entitybracketcolour;
            }
            case  580 :
            {
                return entitychasemaxdelay;
            }
            case  581 :
            {
                return entitychasemaxdelaychance;
            }
            case  665 :
            {
                return entitychasemaxdistance;
            }
            case  582 :
            {
                return entitychasemaxduration;
            }
            case  583 :
            {
                return entitychasemaxdurationchance;
            }
            case  508 :
            {
                return entitycruisespeed;
            }
            case  497 :
            {
                return entitydefenderchance;
            }
            case  465 :
            {
                return entityequipmentgroupmax;
            }
            case  457 :
            {
                return entityequipmentmax;
            }
            case  456 :
            {
                return entityequipmentmin;
            }
            case  562 :
            {
                return entityfactionloss;
            }
            case  416 :
            {
                return entityflyrange;
            }
            case  640 :
            {
                return entitygrouprespawnchance;
            }
            case  481 :
            {
                return entitykillbounty;
            }
            case  251 :
            {
                return entitylootcountmax;
            }
            case  250 :
            {
                return entitylootcountmin;
            }
            case  507 :
            {
                return entitymissiletypeid;
            }
            case  1766 :
            {
                return entityoverviewshipgroupid;
            }
            case  466 :
            {
                return entityreactionfactor;
            }
            case  563 :
            {
                return entitysecuritymaxgain;
            }
            case  252 :
            {
                return entitysecuritystatuskillbonus;
            }
            case  637 :
            {
                return entityshieldboostamount;
            }
            case  1893 :
            {
                return entityshieldboostamountpersecond;
            }
            case  639 :
            {
                return entityshieldboostdelaychance;
            }
            case  1006 :
            {
                return entityshieldboostdelaychancesmall;
            }
            case  636 :
            {
                return entityshieldboostduration;
            }
            case  542 :
            {
                return entitystrength;
            }
            case  504 :
            {
                return entitywarpscramblechance;
            }
            case  116 :
            {
                return explosivedamage;
            }
            case  158 :
            {
                return falloff;
            }
            case  349 :
            {
                return falloffbonus;
            }
            case  246 :
            {
                return gfxboosterid;
            }
            case  245 :
            {
                return gfxturretid;
            }
            case  9 :
            {
                return hp;
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
            case  117 :
            {
                return kineticdamage;
            }
            case  4 :
            {
                return mass;
            }
            case  193 :
            {
                return maxattacktargets;
            }
            case  661 :
            {
                return maxdirectionalvelocity;
            }
            case  192 :
            {
                return maxlockedtargets;
            }
            case  54 :
            {
                return maxrange;
            }
            case  351 :
            {
                return maxrangebonus;
            }
            case  37 :
            {
                return maxvelocity;
            }
            case  1470 :
            {
                return maxvelocitymultiplier;
            }
            case  662 :
            {
                return mintargetveldmgmultiplier;
            }
            case  212 :
            {
                return missiledamagemultiplier;
            }
            case  858 :
            {
                return missileentityaoecloudsizemultiplier;
            }
            case  859 :
            {
                return missileentityaoevelocitymultiplier;
            }
            case  646 :
            {
                return missileentityflighttimemultiplier;
            }
            case  645 :
            {
                return missileentityvelocitymultiplier;
            }
            case  506 :
            {
                return missilelaunchduration;
            }
            case  512 :
            {
                return modifytargetspeedchance;
            }
            case  513 :
            {
                return modifytargetspeedduration;
            }
            case  514 :
            {
                return modifytargetspeedrange;
            }
            case  1464 :
            {
                return npcassistancerange;
            }
            case  1455 :
            {
                return npcremotearmorrepairamount;
            }
            case  1453 :
            {
                return npcremotearmorrepairchance;
            }
            case  1454 :
            {
                return npcremotearmorrepairduration;
            }
            case  1501 :
            {
                return npcremotearmorrepairmaxtargets;
            }
            case  1456 :
            {
                return npcremotearmorrepairthreshold;
            }
            case  1460 :
            {
                return npcremoteshieldboostamount;
            }
            case  1459 :
            {
                return npcremoteshieldboostchance;
            }
            case  1458 :
            {
                return npcremoteshieldboostduration;
            }
            case  1502 :
            {
                return npcremoteshieldboostmaxtargets;
            }
            case  1462 :
            {
                return npcremoteshieldboostthreshold;
            }
            case  933 :
            {
                return npctrackingdisruptoractivationchance;
            }
            case  2515 :
            {
                return npctrackingdisruptorduration;
            }
            case  2516 :
            {
                return npctrackingdisruptorrange;
            }
            case  620 :
            {
                return optimalsigradius;
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
            case  564 :
            {
                return scanresolution;
            }
            case  79 :
            {
                return scanspeed;
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
            case  51 :
            {
                return speed;
            }
            case  20 :
            {
                return speedfactor;
            }
            case  525 :
            {
                return structureuniformity;
            }
            case  118 :
            {
                return thermaldamage;
            }
            case  160 :
            {
                return trackingspeed;
            }
            case  767 :
            {
                return trackingspeedbonus;
            }
            case  136 :
            {
                return uniformity;
            }
            case  505 :
            {
                return warpscrambleduration;
            }
            case  103 :
            {
                return warpscramblerange;
            }
            case  105 :
            {
                return warpscramblestrength;
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
    public IMetaGroup<MissionGenericFrigates> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MissionGenericFrigates>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/MissionGenericFrigates.yaml";
        private Map<String, MissionGenericFrigates> cache = (null);

        @Override
        public IMetaCategory<? super MissionGenericFrigates> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  818;
        }

        @Override
        public String getName() {
            return "MissionGenericFrigates";
        }

        @Override
        public synchronized Map<String, MissionGenericFrigates> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(MissionGenericFrigates.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, MissionGenericFrigates> types;
        }
    }
}
