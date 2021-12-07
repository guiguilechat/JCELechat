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
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorEmDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorExplosiveDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorHP;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorKineticDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorThermalDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorECMDischarge;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorECMDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorECMFalloff;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorECMRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorEnergyNeutralizerDischarge;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorEnergyNeutralizerDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorEnergyNeutralizerFalloff;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorEnergyNeutralizerRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorMicroWarpDriveDischarge;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorMicroWarpDriveDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorMicroWarpDriveMassAddition;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorMicroWarpDriveSignatureRadiusBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorMicroWarpDriveSpeedBoostFactor;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorMicroWarpDriveSpeedFactor;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorSensorDampenerDischarge;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorSensorDampenerDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorSensorDampenerFalloff;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorSensorDampenerRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorTargetPainterDischarge;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorTargetPainterDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorTargetPainterFalloff;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorTargetPainterRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorWarpDisruptDischarge;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorWarpDisruptDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorWarpDisruptRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorWarpDisruptStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorWebifierDischarge;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorWebifierDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorWebifierFalloff;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorWebifierRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.CapacitorCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.Charge;
import fr.guiguilechat.jcelechat.model.sde.attributes.DamageMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.DisallowAssistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.EmDamage;
import fr.guiguilechat.jcelechat.model.sde.attributes.EmDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.EnergyNeutralizerAmount;
import fr.guiguilechat.jcelechat.model.sde.attributes.EnergyWarfareResistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityBracketColour;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityFactionLoss;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityKillBounty;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityMissileTypeID;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntitySecurityMaxGain;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntitySecurityStatusKillBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.ExplosiveDamage;
import fr.guiguilechat.jcelechat.model.sde.attributes.ExplosiveDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.Falloff;
import fr.guiguilechat.jcelechat.model.sde.attributes.GfxBoosterID;
import fr.guiguilechat.jcelechat.model.sde.attributes.GfxTurretID;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hp;
import fr.guiguilechat.jcelechat.model.sde.attributes.KineticDamage;
import fr.guiguilechat.jcelechat.model.sde.attributes.KineticDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.Mass;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxLockedTargets;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxTargetRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxTargetRangeBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxVelocity;
import fr.guiguilechat.jcelechat.model.sde.attributes.MissileDamageMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.MissileEntityAoeCloudSizeMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.MissileEntityAoeVelocityMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.MissileEntityFlightTimeMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.MissileEntityVelocityMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.MissileLaunchDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.OptimalSigRadius;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.RechargeRate;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanGravimetricStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanGravimetricStrengthBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanLadarStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanLadarStrengthBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanMagnetometricStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanMagnetometricStrengthBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanRadarStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanRadarStrengthBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanResolution;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanResolutionBonus;
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
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class NPCBattleship
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
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double behaviorecmdischarge;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int behaviorecmduration;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double behaviorecmfalloff;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int behaviorecmrange;
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
    @DefaultIntValue(0)
    public int behaviormicrowarpdrivedischarge;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int behaviormicrowarpdriveduration;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int behaviormicrowarpdrivemassaddition;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int behaviormicrowarpdrivesignatureradiusbonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int behaviormicrowarpdrivespeedboostfactor;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double behaviormicrowarpdrivespeedfactor;
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
    @DefaultRealValue(0.0)
    public double behaviorsensordampenerduration;
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
    @DefaultRealValue(0.0)
    public double behaviortargetpainterduration;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double behaviortargetpainterfalloff;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double behaviortargetpainterrange;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double behaviorwarpdisruptdischarge;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int behaviorwarpdisruptduration;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int behaviorwarpdisruptrange;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int behaviorwarpdisruptstrength;
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
     * Damage multiplier.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(1.0)
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
     * An amount to modify the power of the target by.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double energyneutralizeramount;
    /**
     * Resistance against Energy Neutralizing and Nosferatu
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double energywarfareresistance;
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
     * The type of missiles the entity launches.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int entitymissiletypeid;
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
     * The characters missile use efficiency, scales the damage missiles do.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double missiledamagemultiplier;
    /**
     * Affects the signature radius of the target in missile impact calculations.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double missileentityaoecloudsizemultiplier;
    /**
     * Affects the velocity of the target in missile impact calculations.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double missileentityaoevelocitymultiplier;
    /**
     * Multiplier for the missile's flight time.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double missileentityflighttimemultiplier;
    /**
     * Multiplier for the missile's speed.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double missileentityvelocitymultiplier;
    /**
     * Cycle time for a missile launch, in milliseconds.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(20000.0)
    public double missilelaunchduration;
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
     * +/- modifier to the gravimetric strength of an electronic system.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double scangravimetricstrengthbonus;
    /**
     * Ladar strength.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double scanladarstrength;
    /**
     * +/- modifier to the ladar strength of an electronic system.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double scanladarstrengthbonus;
    /**
     * Magnetometric strength.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double scanmagnetometricstrength;
    /**
     * +/- modifier to the magnetometric strength of an electronic system.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double scanmagnetometricstrengthbonus;
    /**
     * Radar strength.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double scanradarstrength;
    /**
     * +/- modifier to the radar strength of an electronic system.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double scanradarstrengthbonus;
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
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {Mass.INSTANCE, ShieldCapacity.INSTANCE, ShieldCharge.INSTANCE, Hp.INSTANCE, ArmorHP.INSTANCE, ArmorEmDamageResonance.INSTANCE, ArmorExplosiveDamageResonance.INSTANCE, ArmorKineticDamageResonance.INSTANCE, StructureUniformity.INSTANCE, ArmorThermalDamageResonance.INSTANCE, ShieldEmDamageResonance.INSTANCE, ShieldExplosiveDamageResonance.INSTANCE, ShieldKineticDamageResonance.INSTANCE, ShieldThermalDamageResonance.INSTANCE, Charge.INSTANCE, SpeedFactor.INSTANCE, EntityBracketColour.INSTANCE, MaxVelocity.INSTANCE, Capacity.INSTANCE, SignatureRadius.INSTANCE, SignatureRadiusBonus.INSTANCE, EntityFactionLoss.INSTANCE, Speed.INSTANCE, EntitySecurityMaxGain.INSTANCE, ScanResolution.INSTANCE, MaxTargetRangeBonus.INSTANCE, MaxRange.INSTANCE, ScanResolutionBonus.INSTANCE, BehaviorMicroWarpDriveDischarge.INSTANCE, RechargeRate.INSTANCE, BehaviorMicroWarpDriveDuration.INSTANCE, BehaviorMicroWarpDriveMassAddition.INSTANCE, BehaviorMicroWarpDriveSignatureRadiusBonus.INSTANCE, BehaviorMicroWarpDriveSpeedFactor.INSTANCE, BehaviorMicroWarpDriveSpeedBoostFactor.INSTANCE, DamageMultiplier.INSTANCE, Agility.INSTANCE, MaxTargetRange.INSTANCE, DisallowAssistance.INSTANCE, MissileEntityAoeCloudSizeMultiplier.INSTANCE, MissileEntityAoeVelocityMultiplier.INSTANCE, EnergyNeutralizerAmount.INSTANCE, OptimalSigRadius.INSTANCE, KineticDamageResonance.INSTANCE, ThermalDamageResonance.INSTANCE, ExplosiveDamageResonance.INSTANCE, EmDamageResonance.INSTANCE, EmDamage.INSTANCE, ExplosiveDamage.INSTANCE, KineticDamage.INSTANCE, ThermalDamage.INSTANCE, MissileEntityVelocityMultiplier.INSTANCE, MissileEntityFlightTimeMultiplier.INSTANCE, Falloff.INSTANCE, TrackingSpeed.INSTANCE, Radius.INSTANCE, MaxLockedTargets.INSTANCE, BehaviorWebifierDuration.INSTANCE, BehaviorWebifierRange.INSTANCE, BehaviorWebifierFalloff.INSTANCE, BehaviorWebifierDischarge.INSTANCE, BehaviorWarpDisruptDuration.INSTANCE, BehaviorWarpDisruptRange.INSTANCE, BehaviorWarpDisruptDischarge.INSTANCE, BehaviorWarpDisruptStrength.INSTANCE, ScanRadarStrength.INSTANCE, ScanLadarStrength.INSTANCE, ScanMagnetometricStrength.INSTANCE, ScanGravimetricStrength.INSTANCE, MissileDamageMultiplier.INSTANCE, BehaviorEnergyNeutralizerDuration.INSTANCE, BehaviorEnergyNeutralizerRange.INSTANCE, BehaviorEnergyNeutralizerFalloff.INSTANCE, BehaviorEnergyNeutralizerDischarge.INSTANCE, BehaviorTargetPainterDuration.INSTANCE, BehaviorTargetPainterRange.INSTANCE, BehaviorTargetPainterFalloff.INSTANCE, BehaviorTargetPainterDischarge.INSTANCE, BehaviorSensorDampenerDuration.INSTANCE, ShieldRechargeRate.INSTANCE, BehaviorSensorDampenerRange.INSTANCE, BehaviorSensorDampenerFalloff.INSTANCE, EntityKillBounty.INSTANCE, BehaviorSensorDampenerDischarge.INSTANCE, CapacitorCapacity.INSTANCE, BehaviorECMDuration.INSTANCE, ShieldUniformity.INSTANCE, BehaviorECMRange.INSTANCE, BehaviorECMFalloff.INSTANCE, BehaviorECMDischarge.INSTANCE, ScanGravimetricStrengthBonus.INSTANCE, ScanLadarStrengthBonus.INSTANCE, ScanMagnetometricStrengthBonus.INSTANCE, ScanRadarStrengthBonus.INSTANCE, GfxTurretID.INSTANCE, GfxBoosterID.INSTANCE, MissileLaunchDuration.INSTANCE, EntityMissileTypeID.INSTANCE, EntitySecurityStatusKillBonus.INSTANCE, EnergyWarfareResistance.INSTANCE })));
    public static final NPCBattleship.MetaGroup METAGROUP = new NPCBattleship.MetaGroup();

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
            case  2534 :
            {
                return behaviorecmdischarge;
            }
            case  2531 :
            {
                return behaviorecmduration;
            }
            case  2533 :
            {
                return behaviorecmfalloff;
            }
            case  2532 :
            {
                return behaviorecmrange;
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
            case  2614 :
            {
                return behaviormicrowarpdrivedischarge;
            }
            case  2615 :
            {
                return behaviormicrowarpdriveduration;
            }
            case  2616 :
            {
                return behaviormicrowarpdrivemassaddition;
            }
            case  2617 :
            {
                return behaviormicrowarpdrivesignatureradiusbonus;
            }
            case  2619 :
            {
                return behaviormicrowarpdrivespeedboostfactor;
            }
            case  2618 :
            {
                return behaviormicrowarpdrivespeedfactor;
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
            case  2505 :
            {
                return behaviorwarpdisruptdischarge;
            }
            case  2503 :
            {
                return behaviorwarpdisruptduration;
            }
            case  2504 :
            {
                return behaviorwarpdisruptrange;
            }
            case  2510 :
            {
                return behaviorwarpdisruptstrength;
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
            case  854 :
            {
                return disallowassistance;
            }
            case  114 :
            {
                return emdamage;
            }
            case  113 :
            {
                return emdamageresonance;
            }
            case  97 :
            {
                return energyneutralizeramount;
            }
            case  2045 :
            {
                return energywarfareresistance;
            }
            case  798 :
            {
                return entitybracketcolour;
            }
            case  562 :
            {
                return entityfactionloss;
            }
            case  481 :
            {
                return entitykillbounty;
            }
            case  507 :
            {
                return entitymissiletypeid;
            }
            case  563 :
            {
                return entitysecuritymaxgain;
            }
            case  252 :
            {
                return entitysecuritystatuskillbonus;
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
            case  109 :
            {
                return kineticdamageresonance;
            }
            case  4 :
            {
                return mass;
            }
            case  192 :
            {
                return maxlockedtargets;
            }
            case  54 :
            {
                return maxrange;
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
            case  238 :
            {
                return scangravimetricstrengthbonus;
            }
            case  209 :
            {
                return scanladarstrength;
            }
            case  239 :
            {
                return scanladarstrengthbonus;
            }
            case  210 :
            {
                return scanmagnetometricstrength;
            }
            case  240 :
            {
                return scanmagnetometricstrengthbonus;
            }
            case  208 :
            {
                return scanradarstrength;
            }
            case  241 :
            {
                return scanradarstrengthbonus;
            }
            case  564 :
            {
                return scanresolution;
            }
            case  566 :
            {
                return scanresolutionbonus;
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
    public IMetaGroup<NPCBattleship> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<NPCBattleship>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/NPCBattleship.yaml";
        private Map<String, NPCBattleship> cache = (null);

        @Override
        public IMetaCategory<? super NPCBattleship> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1814;
        }

        @Override
        public String getName() {
            return "NPCBattleship";
        }

        @Override
        public synchronized Map<String, NPCBattleship> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(NPCBattleship.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, NPCBattleship> types;
        }
    }
}
