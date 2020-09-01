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
import fr.guiguilechat.jcelechat.model.sde.attributes.Agility;
import fr.guiguilechat.jcelechat.model.sde.attributes.AoeCloudSizeBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.AoeVelocityBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorDamageAmount;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorEmDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorExplosiveDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorHP;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorKineticDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorThermalDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorUniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorRemoteArmorRepairDischarge;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorRemoteArmorRepairDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorRemoteArmorRepairFalloff;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorRemoteArmorRepairRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorRemoteShieldBoostDischarge;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorRemoteShieldBoostDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorRemoteShieldBoostFalloff;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorRemoteShieldBoostRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorSensorDampenerDischarge;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorSensorDampenerDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorSensorDampenerFalloff;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorSensorDampenerRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorTargetPainterDischarge;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorTargetPainterDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorTargetPainterFalloff;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorTargetPainterRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorWebifierDischarge;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorWebifierDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorWebifierFalloff;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorWebifierRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.CapacitorCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.Charge;
import fr.guiguilechat.jcelechat.model.sde.attributes.DamageMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.EmDamage;
import fr.guiguilechat.jcelechat.model.sde.attributes.EmDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityChaseMaxDelay;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityChaseMaxDelayChance;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityChaseMaxDistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityChaseMaxDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityChaseMaxDurationChance;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityCruiseSpeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityFactionLoss;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityKillBounty;
import fr.guiguilechat.jcelechat.model.sde.attributes.ExplosionDelayBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.ExplosiveDamage;
import fr.guiguilechat.jcelechat.model.sde.attributes.ExplosiveDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.Falloff;
import fr.guiguilechat.jcelechat.model.sde.attributes.FalloffBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityAntiFighterMissileResistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.GfxBoosterID;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hp;
import fr.guiguilechat.jcelechat.model.sde.attributes.KineticDamage;
import fr.guiguilechat.jcelechat.model.sde.attributes.KineticDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.Mass;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxAttackTargets;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxLockedTargets;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxRangeBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxTargetRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxTargetRangeBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxVelocity;
import fr.guiguilechat.jcelechat.model.sde.attributes.MissileVelocityBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.NpcBehaviorMaximumCombatOrbitRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.NpcGuidanceDisruptorDischarge;
import fr.guiguilechat.jcelechat.model.sde.attributes.NpcGuidanceDisruptorDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.NpcGuidanceDisruptorFalloff;
import fr.guiguilechat.jcelechat.model.sde.attributes.NpcGuidanceDisruptorRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.NpcTrackingDisruptorDischarge;
import fr.guiguilechat.jcelechat.model.sde.attributes.NpcTrackingDisruptorDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.NpcTrackingDisruptorFalloff;
import fr.guiguilechat.jcelechat.model.sde.attributes.NpcTrackingDisruptorRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.OptimalSigRadius;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.RechargeRate;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanGravimetricStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanLadarStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanMagnetometricStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanRadarStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanResolution;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanResolutionBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanSpeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldCharge;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldEmDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldExplosiveDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldKineticDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldRechargeRate;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldThermalDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldUniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.SignatureRadius;
import fr.guiguilechat.jcelechat.model.sde.attributes.SignatureRadiusBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.Speed;
import fr.guiguilechat.jcelechat.model.sde.attributes.SpeedFactor;
import fr.guiguilechat.jcelechat.model.sde.attributes.StructureUniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.ThermalDamage;
import fr.guiguilechat.jcelechat.model.sde.attributes.ThermalDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.TrackingSpeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.TrackingSpeedBonus;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AbyssalDroneEntities
    extends Entity
{
    /**
     * The agility of the object.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double agility;
    /**
     * Booster attribute to explosion radius of missiles vs. signature radius.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double aoecloudsizebonus;
    /**
     * Increases velocity of missile explosion
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double aoevelocitybonus;
    /**
     * An amount to modify the armor damage by.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double armordamageamount;
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
    public double behaviorremotearmorrepairdischarge;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double behaviorremotearmorrepairduration;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int behaviorremotearmorrepairfalloff;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int behaviorremotearmorrepairrange;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double behaviorremoteshieldboostdischarge;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int behaviorremoteshieldboostduration;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int behaviorremoteshieldboostfalloff;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int behaviorremoteshieldboostrange;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double behaviorsensordampenerdischarge;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int behaviorsensordampenerduration;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double behaviorsensordampenerfalloff;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double behaviorsensordampenerrange;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double behaviortargetpainterdischarge;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int behaviortargetpainterduration;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int behaviortargetpainterfalloff;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int behaviortargetpainterrange;
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
    @DefaultIntValue(0)
    public int behaviorwebifierduration;
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
    @DefaultIntValue(0)
    public int behaviorwebifierrange;
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
     * Damage multiplier.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double damagemultiplier;
    /**
     * EM damage done.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double emdamage;
    /**
     * Electro magnetic damage multiplier for shield and armor. Represented as "% Resistance" in the UI.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double emdamageresonance;
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
    @DefaultRealValue(0.0)
    public double entityfactionloss;
    /**
     * Reward for destroying this entity.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int entitykillbounty;
    /**
     * Autogenerated skill attribute, explosionDelayBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double explosiondelaybonus;
    /**
     * Explosive damage done.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double explosivedamage;
    /**
     * damage multiplier vs. explosive damagers.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double explosivedamageresonance;
    /**
     * distance from maximum range at which accuracy has fallen by half
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double falloff;
    /**
     * Autogenerated skill attribute, falloffBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double falloffbonus;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fighterabilityantifightermissileresistance;
    /**
     * Graphic ID of the boosters for drone type ships.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int gfxboosterid;
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
     * damage multiplier vs. kinetic damagers.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double kineticdamageresonance;
    /**
     * Integer that describes the types mass
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double mass;
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
     * Autogenerated skill attribute, maxRangeBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double maxrangebonus;
    /**
     * Maximum range at which the scanner can lock a target.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double maxtargetrange;
    /**
     * Bonus to Max Targeting Range
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double maxtargetrangebonus;
    /**
     * Maximum velocity of ship
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double maxvelocity;
    /**
     * Autogenerated skill attribute, missileVelocityBonus 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double missilevelocitybonus;
    /**
     * Used by Behavior NPCs to work out minimum orbit range. If the npc has an effect with a shorter range, it will use the effects range instead.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int npcbehaviormaximumcombatorbitrange;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double npcguidancedisruptordischarge;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int npcguidancedisruptorduration;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int npcguidancedisruptorfalloff;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int npcguidancedisruptorrange;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double npctrackingdisruptordischarge;
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
    public int npctrackingdisruptorfalloff;
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
     * Bonus for scan resolution
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double scanresolutionbonus;
    /**
     * scanning speed in milliseconds
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int scanspeed;
    /**
     * Bonus to shield.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double shieldbonus;
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
     * Autogenerated skill attribute, signatureRadiusBonus
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double signatureradiusbonus;
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
     * damage multiplier vs. thermal.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double thermaldamageresonance;
    /**
     * Weapon accuracy
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double trackingspeed;
    /**
     * Tracking Speed Bonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double trackingspeedbonus;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {Mass.INSTANCE, ShieldCapacity.INSTANCE, ShieldCharge.INSTANCE, Hp.INSTANCE, ArmorHP.INSTANCE, ArmorEmDamageResonance.INSTANCE, ArmorExplosiveDamageResonance.INSTANCE, ArmorUniformity.INSTANCE, ArmorKineticDamageResonance.INSTANCE, StructureUniformity.INSTANCE, ArmorThermalDamageResonance.INSTANCE, ShieldEmDamageResonance.INSTANCE, ShieldExplosiveDamageResonance.INSTANCE, ShieldKineticDamageResonance.INSTANCE, ShieldThermalDamageResonance.INSTANCE, Charge.INSTANCE, SpeedFactor.INSTANCE, MissileVelocityBonus.INSTANCE, MaxVelocity.INSTANCE, Capacity.INSTANCE, SignatureRadius.INSTANCE, SignatureRadiusBonus.INSTANCE, EntityFactionLoss.INSTANCE, Speed.INSTANCE, ScanResolution.INSTANCE, MaxTargetRangeBonus.INSTANCE, MaxRange.INSTANCE, ScanResolutionBonus.INSTANCE, RechargeRate.INSTANCE, DamageMultiplier.INSTANCE, EntityChaseMaxDelay.INSTANCE, ShieldBonus.INSTANCE, EntityChaseMaxDelayChance.INSTANCE, Agility.INSTANCE, EntityChaseMaxDuration.INSTANCE, EntityChaseMaxDurationChance.INSTANCE, MaxTargetRange.INSTANCE, AoeVelocityBonus.INSTANCE, ScanSpeed.INSTANCE, AoeCloudSizeBonus.INSTANCE, ExplosionDelayBonus.INSTANCE, ArmorDamageAmount.INSTANCE, FalloffBonus.INSTANCE, MaxRangeBonus.INSTANCE, OptimalSigRadius.INSTANCE, KineticDamageResonance.INSTANCE, ThermalDamageResonance.INSTANCE, ExplosiveDamageResonance.INSTANCE, EmDamageResonance.INSTANCE, EmDamage.INSTANCE, ExplosiveDamage.INSTANCE, KineticDamage.INSTANCE, ThermalDamage.INSTANCE, FighterAbilityAntiFighterMissileResistance.INSTANCE, EntityChaseMaxDistance.INSTANCE, Falloff.INSTANCE, TrackingSpeed.INSTANCE, Radius.INSTANCE, BehaviorRemoteArmorRepairDuration.INSTANCE, BehaviorRemoteArmorRepairRange.INSTANCE, BehaviorRemoteArmorRepairFalloff.INSTANCE, BehaviorRemoteArmorRepairDischarge.INSTANCE, BehaviorRemoteShieldBoostDuration.INSTANCE, MaxLockedTargets.INSTANCE, BehaviorRemoteShieldBoostRange.INSTANCE, BehaviorRemoteShieldBoostFalloff.INSTANCE, MaxAttackTargets.INSTANCE, BehaviorRemoteShieldBoostDischarge.INSTANCE, BehaviorWebifierDuration.INSTANCE, BehaviorWebifierRange.INSTANCE, BehaviorWebifierFalloff.INSTANCE, BehaviorWebifierDischarge.INSTANCE, NpcGuidanceDisruptorDuration.INSTANCE, ScanRadarStrength.INSTANCE, NpcGuidanceDisruptorRange.INSTANCE, ScanLadarStrength.INSTANCE, NpcGuidanceDisruptorFalloff.INSTANCE, ScanMagnetometricStrength.INSTANCE, NpcGuidanceDisruptorDischarge.INSTANCE, ScanGravimetricStrength.INSTANCE, NpcTrackingDisruptorDuration.INSTANCE, NpcTrackingDisruptorRange.INSTANCE, NpcTrackingDisruptorFalloff.INSTANCE, NpcTrackingDisruptorDischarge.INSTANCE, BehaviorTargetPainterDuration.INSTANCE, BehaviorTargetPainterRange.INSTANCE, BehaviorTargetPainterFalloff.INSTANCE, BehaviorTargetPainterDischarge.INSTANCE, ShieldRechargeRate.INSTANCE, BehaviorSensorDampenerDuration.INSTANCE, BehaviorSensorDampenerRange.INSTANCE, EntityKillBounty.INSTANCE, BehaviorSensorDampenerFalloff.INSTANCE, NpcBehaviorMaximumCombatOrbitRange.INSTANCE, CapacitorCapacity.INSTANCE, BehaviorSensorDampenerDischarge.INSTANCE, ShieldUniformity.INSTANCE, GfxBoosterID.INSTANCE, EntityCruiseSpeed.INSTANCE, TrackingSpeedBonus.INSTANCE })));
    public static final AbyssalDroneEntities.MetaGroup METAGROUP = new AbyssalDroneEntities.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  70 :
            {
                return agility;
            }
            case  848 :
            {
                return aoecloudsizebonus;
            }
            case  847 :
            {
                return aoevelocitybonus;
            }
            case  84 :
            {
                return armordamageamount;
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
            case  2494 :
            {
                return behaviorremotearmorrepairdischarge;
            }
            case  2491 :
            {
                return behaviorremotearmorrepairduration;
            }
            case  2493 :
            {
                return behaviorremotearmorrepairfalloff;
            }
            case  2492 :
            {
                return behaviorremotearmorrepairrange;
            }
            case  2498 :
            {
                return behaviorremoteshieldboostdischarge;
            }
            case  2495 :
            {
                return behaviorremoteshieldboostduration;
            }
            case  2497 :
            {
                return behaviorremoteshieldboostfalloff;
            }
            case  2496 :
            {
                return behaviorremoteshieldboostrange;
            }
            case  2530 :
            {
                return behaviorsensordampenerdischarge;
            }
            case  2527 :
            {
                return behaviorsensordampenerduration;
            }
            case  2529 :
            {
                return behaviorsensordampenerfalloff;
            }
            case  2528 :
            {
                return behaviorsensordampenerrange;
            }
            case  2526 :
            {
                return behaviortargetpainterdischarge;
            }
            case  2523 :
            {
                return behaviortargetpainterduration;
            }
            case  2525 :
            {
                return behaviortargetpainterfalloff;
            }
            case  2524 :
            {
                return behaviortargetpainterrange;
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
            case  64 :
            {
                return damagemultiplier;
            }
            case  114 :
            {
                return emdamage;
            }
            case  113 :
            {
                return emdamageresonance;
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
            case  562 :
            {
                return entityfactionloss;
            }
            case  481 :
            {
                return entitykillbounty;
            }
            case  596 :
            {
                return explosiondelaybonus;
            }
            case  116 :
            {
                return explosivedamage;
            }
            case  111 :
            {
                return explosivedamageresonance;
            }
            case  158 :
            {
                return falloff;
            }
            case  349 :
            {
                return falloffbonus;
            }
            case  2189 :
            {
                return fighterabilityantifightermissileresistance;
            }
            case  246 :
            {
                return gfxboosterid;
            }
            case  9 :
            {
                return hp;
            }
            case  117 :
            {
                return kineticdamage;
            }
            case  109 :
            {
                return kineticdamageresonance;
            }
            case  4 :
            {
                return mass;
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
            case  351 :
            {
                return maxrangebonus;
            }
            case  76 :
            {
                return maxtargetrange;
            }
            case  309 :
            {
                return maxtargetrangebonus;
            }
            case  37 :
            {
                return maxvelocity;
            }
            case  547 :
            {
                return missilevelocitybonus;
            }
            case  2786 :
            {
                return npcbehaviormaximumcombatorbitrange;
            }
            case  2514 :
            {
                return npcguidancedisruptordischarge;
            }
            case  2511 :
            {
                return npcguidancedisruptorduration;
            }
            case  2513 :
            {
                return npcguidancedisruptorfalloff;
            }
            case  2512 :
            {
                return npcguidancedisruptorrange;
            }
            case  2518 :
            {
                return npctrackingdisruptordischarge;
            }
            case  2515 :
            {
                return npctrackingdisruptorduration;
            }
            case  2517 :
            {
                return npctrackingdisruptorfalloff;
            }
            case  2516 :
            {
                return npctrackingdisruptorrange;
            }
            case  620 :
            {
                return optimalsigradius;
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
            case  566 :
            {
                return scanresolutionbonus;
            }
            case  79 :
            {
                return scanspeed;
            }
            case  68 :
            {
                return shieldbonus;
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
            case  554 :
            {
                return signatureradiusbonus;
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
            case  110 :
            {
                return thermaldamageresonance;
            }
            case  160 :
            {
                return trackingspeed;
            }
            case  767 :
            {
                return trackingspeedbonus;
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
    public IMetaGroup<AbyssalDroneEntities> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AbyssalDroneEntities>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/AbyssalDroneEntities.yaml";
        private Map<String, AbyssalDroneEntities> cache = (null);

        @Override
        public IMetaCategory<? super AbyssalDroneEntities> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1997;
        }

        @Override
        public String getName() {
            return "AbyssalDroneEntities";
        }

        @Override
        public synchronized Map<String, AbyssalDroneEntities> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(AbyssalDroneEntities.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AbyssalDroneEntities> types;
        }
    }
}
