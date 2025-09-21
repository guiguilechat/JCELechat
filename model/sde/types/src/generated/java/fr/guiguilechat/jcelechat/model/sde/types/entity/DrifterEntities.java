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
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultRealValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.AIChanceToNotTargetSwitch;
import fr.guiguilechat.jcelechat.model.sde.attributes.AIShouldUseEffectMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.AIShouldUseSecondaryTarget;
import fr.guiguilechat.jcelechat.model.sde.attributes.AIShouldUseSignatureRadius;
import fr.guiguilechat.jcelechat.model.sde.attributes.AIShouldUseTargetSwitching;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorEmDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorExplosiveDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorHP;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorKineticDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorThermalDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorUniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorEnergyNeutralizerDischarge;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorEnergyNeutralizerDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorEnergyNeutralizerFalloff;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorEnergyNeutralizerRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorWarpScrambleDischarge;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorWarpScrambleDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorWarpScrambleRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorWarpScrambleStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorWebifierDischarge;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorWebifierDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorWebifierFalloff;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorWebifierRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.CapacitorCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.Charge;
import fr.guiguilechat.jcelechat.model.sde.attributes.DamageDelayDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.DamageMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.EffectDeactivationDelay;
import fr.guiguilechat.jcelechat.model.sde.attributes.EmDamage;
import fr.guiguilechat.jcelechat.model.sde.attributes.EnergyNeutralizerAmount;
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
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityEquipmentMax;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityEquipmentMin;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityFactionLoss;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityFlyRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityGroupRespawnChance;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityLootCountMin;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityOverviewShipGroupId;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityReactionFactor;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntitySecurityMaxGain;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntitySecurityStatusKillBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntitySuperWeaponDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntitySuperWeaponEmDamage;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntitySuperWeaponExplosiveDamage;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntitySuperWeaponFallOff;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntitySuperWeaponKineticDamage;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntitySuperWeaponMaxRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntitySuperWeaponOptimalSignatureRadius;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntitySuperWeaponThermalDamage;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntitySuperWeaponTrackingSpeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.ExplosiveDamage;
import fr.guiguilechat.jcelechat.model.sde.attributes.Falloff;
import fr.guiguilechat.jcelechat.model.sde.attributes.GfxBoosterID;
import fr.guiguilechat.jcelechat.model.sde.attributes.GfxTurretID;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hp;
import fr.guiguilechat.jcelechat.model.sde.attributes.KineticDamage;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxAttackTargets;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxLockedTargets;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxVelocity;
import fr.guiguilechat.jcelechat.model.sde.attributes.OptimalSigRadius;
import fr.guiguilechat.jcelechat.model.sde.attributes.PropulsionGraphicID;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.RechargeRate;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanGravimetricStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanLadarStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanMagnetometricStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanRadarStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanResolution;
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
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class DrifterEntities
    extends Entity
{
    /**
     * A percentage chance to not change targets 0.0 - 1.0. 1.0 they will never change targets 0.0 they will always change targets
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double aichancetonottargetswitch;
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
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double behaviorenergyneutralizerdischarge;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int behaviorenergyneutralizerduration;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double behaviorenergyneutralizerfalloff;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double behaviorenergyneutralizerrange;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double behaviorwarpscrambledischarge;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int behaviorwarpscrambleduration;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double behaviorwarpscramblerange;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int behaviorwarpscramblestrength;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double behaviorwebifierdischarge;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double behaviorwebifierduration;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int behaviorwebifierfalloff;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double behaviorwebifierrange;
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
     * charge of module
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int charge;
    /**
     * The delay in ms until the damage is done to the target. (Allows some FX to be played)
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(10000)
    public int damagedelayduration;
    /**
     * Damage multiplier.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double damagemultiplier;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int effectdeactivationdelay;
    /**
     * EM damage done.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double emdamage;
    /**
     * An amount to modify the power of the target by.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double energyneutralizeramount;
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
    @DefaultRealValue(5000.0)
    public double entitychasemaxdelay;
    /**
     * Chance that the max delay is waited before chase is engaged.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
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
    @DefaultRealValue(1.0)
    public double entitychasemaxdurationchance;
    /**
     * The speed that entities fly at when not chasing a target.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double entitycruisespeed;
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
    @DefaultRealValue(0.0)
    public double entityfactionloss;
    /**
     * The distance at which the entity orbits, follows.. and more.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(500.0)
    public double entityflyrange;
    /**
     * The chance an entity will respawn into his group if destroyed.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int entitygrouprespawnchance;
    /**
     * Deprecated. The minimum number of pieces of loot dropped by this entity.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int entitylootcountmin;
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
    @DefaultRealValue(1.0)
    public double entityreactionfactor;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double entitysecuritymaxgain;
    /**
     * How much security status is modified by for killing this entity.  Depending on the entity, this may be a positive or negative amount.
     * Value is a % movement of the character's current security towards the upper/lower limit.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double entitysecuritystatuskillbonus;
    /**
     * Used for NPCs to replicate cooldown functionality for the super weapon.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int entitysuperweaponduration;
    /**
     * Used for NPCs to replicate damage for the super weapon.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int entitysuperweaponemdamage;
    /**
     * Used for NPCs to replicate damage for the super weapon.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int entitysuperweaponexplosivedamage;
    /**
     * Used for chance based accuracy hit calculation for entity super weapon.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(250000)
    public int entitysuperweaponfalloff;
    /**
     * Used for NPCs to replicate damage for the super weapon.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int entitysuperweaponkineticdamage;
    /**
     * Used for chance based accuracy hit calculation for entity super weapon.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(250000)
    public int entitysuperweaponmaxrange;
    /**
     * Used for chance based accuracy hit calculation for entity super weapon.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(20)
    public int entitysuperweaponoptimalsignatureradius;
    /**
     * Used for NPCs to replicate damage for the super weapon.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int entitysuperweaponthermaldamage;
    /**
     * Used for chance based accuracy hit calculation for entity super weapon.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double entitysuperweapontrackingspeed;
    /**
     * Explosive damage done.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double explosivedamage;
    /**
     * distance from maximum range at which accuracy has fallen by half
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double falloff;
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
    @DefaultRealValue(0.0)
    public double hp;
    /**
     * Kinetic damage done.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double kineticdamage;
    /**
     * The maximum number of their targets that the character can attack at a given time.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxattacktargets;
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
    @DefaultRealValue(0.0)
    public double maxrange;
    /**
     * Maximum velocity of ship
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double maxvelocity;
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
     * The resolution that the vessel can target other objects at.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double scanresolution;
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
     * Time in milliseconds between possible activations
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double speed;
    /**
     * Factor by which topspeed increases.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double speedfactor;
    /**
     * DO NOT MESS WITH
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double structureuniformity;
    /**
     * Thermal damage done.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double thermaldamage;
    /**
     * Weapon accuracy
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double trackingspeed;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {EntitySuperWeaponTrackingSpeed.INSTANCE, EntityGroupRespawnChance.INSTANCE, EntitySuperWeaponOptimalSignatureRadius.INSTANCE, ShieldCapacity.INSTANCE, ShieldCharge.INSTANCE, Hp.INSTANCE, ArmorHP.INSTANCE, ArmorEmDamageResonance.INSTANCE, ArmorUniformity.INSTANCE, ArmorExplosiveDamageResonance.INSTANCE, StructureUniformity.INSTANCE, ArmorKineticDamageResonance.INSTANCE, ArmorThermalDamageResonance.INSTANCE, ShieldEmDamageResonance.INSTANCE, ShieldExplosiveDamageResonance.INSTANCE, ShieldKineticDamageResonance.INSTANCE, Charge.INSTANCE, ShieldThermalDamageResonance.INSTANCE, SpeedFactor.INSTANCE, EntityChaseMaxDistance.INSTANCE, Falloff.INSTANCE, EntityBracketColour.INSTANCE, TrackingSpeed.INSTANCE, EntityFlyRange.INSTANCE, Radius.INSTANCE, MaxVelocity.INSTANCE, Capacity.INSTANCE, SignatureRadius.INSTANCE, EffectDeactivationDelay.INSTANCE, DamageDelayDuration.INSTANCE, EntityFactionLoss.INSTANCE, Speed.INSTANCE, EntitySecurityMaxGain.INSTANCE, ScanResolution.INSTANCE, MaxRange.INSTANCE, RechargeRate.INSTANCE, DamageMultiplier.INSTANCE, MaxLockedTargets.INSTANCE, MaxAttackTargets.INSTANCE, BehaviorWebifierDuration.INSTANCE, EntityChaseMaxDelay.INSTANCE, BehaviorWebifierRange.INSTANCE, EntityChaseMaxDelayChance.INSTANCE, BehaviorWebifierFalloff.INSTANCE, EntityChaseMaxDuration.INSTANCE, BehaviorWebifierDischarge.INSTANCE, EntityChaseMaxDurationChance.INSTANCE, EntityEquipmentMin.INSTANCE, EntityEquipmentMax.INSTANCE, BehaviorWarpScrambleDuration.INSTANCE, BehaviorWarpScrambleRange.INSTANCE, BehaviorWarpScrambleDischarge.INSTANCE, BehaviorWarpScrambleStrength.INSTANCE, ScanRadarStrength.INSTANCE, ScanLadarStrength.INSTANCE, ScanMagnetometricStrength.INSTANCE, EntityReactionFactor.INSTANCE, ScanGravimetricStrength.INSTANCE, BehaviorEnergyNeutralizerDuration.INSTANCE, BehaviorEnergyNeutralizerRange.INSTANCE, PropulsionGraphicID.INSTANCE, BehaviorEnergyNeutralizerFalloff.INSTANCE, EntitySuperWeaponDuration.INSTANCE, BehaviorEnergyNeutralizerDischarge.INSTANCE, EntitySuperWeaponEmDamage.INSTANCE, EntityAttackDelayMin.INSTANCE, EntitySuperWeaponKineticDamage.INSTANCE, EntityAttackDelayMax.INSTANCE, EntitySuperWeaponThermalDamage.INSTANCE, EntitySuperWeaponExplosiveDamage.INSTANCE, ShieldRechargeRate.INSTANCE, EnergyNeutralizerAmount.INSTANCE, CapacitorCapacity.INSTANCE, ShieldUniformity.INSTANCE, EntityOverviewShipGroupId.INSTANCE, OptimalSigRadius.INSTANCE, AIShouldUseTargetSwitching.INSTANCE, AIShouldUseSecondaryTarget.INSTANCE, AIShouldUseSignatureRadius.INSTANCE, EmDamage.INSTANCE, AIChanceToNotTargetSwitch.INSTANCE, AIShouldUseEffectMultiplier.INSTANCE, ExplosiveDamage.INSTANCE, KineticDamage.INSTANCE, GfxTurretID.INSTANCE, ThermalDamage.INSTANCE, GfxBoosterID.INSTANCE, EntityAttackRange.INSTANCE, EntityLootCountMin.INSTANCE, EntitySecurityStatusKillBonus.INSTANCE, EntityCruiseSpeed.INSTANCE, EntitySuperWeaponMaxRange.INSTANCE, EntitySuperWeaponFallOff.INSTANCE })));
    public static final DrifterEntities.MetaGroup METAGROUP = new DrifterEntities.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  1651 :
            {
                return aichancetonottargetswitch;
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
            case  2522 :
            {
                return behaviorenergyneutralizerdischarge;
            }
            case  2519 :
            {
                return behaviorenergyneutralizerduration;
            }
            case  2521 :
            {
                return behaviorenergyneutralizerfalloff;
            }
            case  2520 :
            {
                return behaviorenergyneutralizerrange;
            }
            case  2508 :
            {
                return behaviorwarpscrambledischarge;
            }
            case  2506 :
            {
                return behaviorwarpscrambleduration;
            }
            case  2507 :
            {
                return behaviorwarpscramblerange;
            }
            case  2509 :
            {
                return behaviorwarpscramblestrength;
            }
            case  2502 :
            {
                return behaviorwebifierdischarge;
            }
            case  2499 :
            {
                return behaviorwebifierduration;
            }
            case  2501 :
            {
                return behaviorwebifierfalloff;
            }
            case  2500 :
            {
                return behaviorwebifierrange;
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
            case  1839 :
            {
                return damagedelayduration;
            }
            case  64 :
            {
                return damagemultiplier;
            }
            case  1579 :
            {
                return effectdeactivationdelay;
            }
            case  114 :
            {
                return emdamage;
            }
            case  97 :
            {
                return energyneutralizeramount;
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
            case  250 :
            {
                return entitylootcountmin;
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
            case  2009 :
            {
                return entitysuperweaponduration;
            }
            case  2010 :
            {
                return entitysuperweaponemdamage;
            }
            case  2013 :
            {
                return entitysuperweaponexplosivedamage;
            }
            case  2047 :
            {
                return entitysuperweaponfalloff;
            }
            case  2011 :
            {
                return entitysuperweaponkineticdamage;
            }
            case  2046 :
            {
                return entitysuperweaponmaxrange;
            }
            case  2049 :
            {
                return entitysuperweaponoptimalsignatureradius;
            }
            case  2012 :
            {
                return entitysuperweaponthermaldamage;
            }
            case  2048 :
            {
                return entitysuperweapontrackingspeed;
            }
            case  116 :
            {
                return explosivedamage;
            }
            case  158 :
            {
                return falloff;
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
            case  117 :
            {
                return kineticdamage;
            }
            case  193 :
            {
                return maxattacktargets;
            }
            case  192 :
            {
                return maxlockedtargets;
            }
            case  54 :
            {
                return maxrange;
            }
            case  37 :
            {
                return maxvelocity;
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
    public IMetaGroup<DrifterEntities> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<DrifterEntities>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/DrifterEntities.yaml";
        private Map<Integer, DrifterEntities> cache = (null);

        @Override
        public IMetaCategory<? super DrifterEntities> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  4035;
        }

        @Override
        public String getName() {
            return "DrifterEntities";
        }

        @Override
        public synchronized Map<Integer, DrifterEntities> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(DrifterEntities.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, DrifterEntities> types;
        }
    }
}
