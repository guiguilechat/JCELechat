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
import fr.guiguilechat.jcelechat.model.sde.attributes.AIIgnoreDronesBelowSignatureRadius;
import fr.guiguilechat.jcelechat.model.sde.attributes.AIShouldUseEffectMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.AIShouldUseSecondaryTarget;
import fr.guiguilechat.jcelechat.model.sde.attributes.AIShouldUseSignatureRadius;
import fr.guiguilechat.jcelechat.model.sde.attributes.AIShouldUseTargetSwitching;
import fr.guiguilechat.jcelechat.model.sde.attributes.AITankingModifierDrone;
import fr.guiguilechat.jcelechat.model.sde.attributes.Agility;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorDamage;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorDamageAmount;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorEmDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorExplosiveDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorHP;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorKineticDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorThermalDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorUniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorArmorRepairerAmount;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorArmorRepairerDischarge;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorArmorRepairerDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorEnergyNeutralizerDischarge;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorEnergyNeutralizerDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorEnergyNeutralizerFalloff;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorEnergyNeutralizerRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorRemoteArmorRepairDischarge;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorRemoteArmorRepairDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorRemoteArmorRepairFalloff;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorRemoteArmorRepairRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorShieldBoosterAmount;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorShieldBoosterDischarge;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorShieldBoosterDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorWarpDisruptDischarge;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorWarpDisruptDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorWarpDisruptRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorWarpDisruptStrength;
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
import fr.guiguilechat.jcelechat.model.sde.attributes.Damage;
import fr.guiguilechat.jcelechat.model.sde.attributes.DamageMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.DamageMultiplierBonusMax;
import fr.guiguilechat.jcelechat.model.sde.attributes.DamageMultiplierBonusPerCycle;
import fr.guiguilechat.jcelechat.model.sde.attributes.DisallowAssistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.EmDamage;
import fr.guiguilechat.jcelechat.model.sde.attributes.EmDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.EnergyNeutralizerAmount;
import fr.guiguilechat.jcelechat.model.sde.attributes.EnergyNeutralizerDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.EnergyNeutralizerEntityChance;
import fr.guiguilechat.jcelechat.model.sde.attributes.EnergyNeutralizerRangeOptimal;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityArmorRepairAmount;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityArmorRepairDelayChance;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityArmorRepairDelayChanceLarge;
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
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityMaxVelocitySignatureRadiusMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityMissileTypeID;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityOverviewShipGroupId;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityReactionFactor;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntitySecurityMaxGain;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntitySecurityStatusKillBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityShieldBoostAmount;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityShieldBoostDelayChance;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityShieldBoostDelayChanceLarge;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityShieldBoostDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityWarpScrambleChance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ExplosiveDamage;
import fr.guiguilechat.jcelechat.model.sde.attributes.ExplosiveDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.Falloff;
import fr.guiguilechat.jcelechat.model.sde.attributes.GfxBoosterID;
import fr.guiguilechat.jcelechat.model.sde.attributes.GfxTurretID;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hp;
import fr.guiguilechat.jcelechat.model.sde.attributes.KineticDamage;
import fr.guiguilechat.jcelechat.model.sde.attributes.KineticDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.Mass;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxAttackTargets;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxDirectionalVelocity;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxLockedTargets;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxTargetRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxVelocity;
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
import fr.guiguilechat.jcelechat.model.sde.attributes.NpcBehaviorMaximumCombatOrbitRange;
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
import fr.guiguilechat.jcelechat.model.sde.attributes.ThermalDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.TrackingSpeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarpScrambleDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarpScrambleRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarpScrambleStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarpSpeedMultiplier;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class IrregularBattleship
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
    @DefaultRealValue(0.699999988079071)
    public double aitankingmodifierdrone;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int behaviorshieldboosteramount;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int behaviorshieldboosterdischarge;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int behaviorshieldboosterduration;
    /**
     * The agility of the object.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double agility;
    /**
     * DO NOT MESS WITH. The amount of damage done to the entities armor hit points. Starting armor damage.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int armordamage;
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
    @DefaultIntValue(0)
    public int behaviorarmorrepaireramount;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int behaviorarmorrepairerdischarge;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int behaviorarmorrepairerduration;
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
     * current structure damage dealt to module
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int damage;
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
    @DefaultRealValue(0.5)
    public double damagemultiplierbonusmax;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double damagemultiplierbonuspercycle;
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
    @DefaultRealValue(0.0)
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
    @DefaultRealValue(0.0)
    public double entityarmorrepairamount;
    /**
     * Chance that an entity will delay employing armor repair.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double entityarmorrepairdelaychance;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double entityarmorrepairdelaychancelarge;
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
     * % chance of entity to shoot defender at incoming missile
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
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
     * Used to increase signature radius of entity when it activates Max Velocity. Used to fake MWD sig radius increase.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(6)
    public int entitymaxvelocitysignatureradiusmultiplier;
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
     * How much the shield is boosted each duration.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double entityshieldboostamount;
    /**
     * The chance an entity will delay repeating use of its shield boosting effect if it has one.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double entityshieldboostdelaychance;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double entityshieldboostdelaychancelarge;
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
    @DefaultRealValue(0.0)
    public double entitywarpscramblechance;
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
     * Maximum velocity of ship
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double maxvelocity;
    /**
     * Deprecated.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double mintargetveldmgmultiplier;
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
     * Chance that  an entity will use a Stasis Web on a target.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double modifytargetspeedchance;
    /**
     * Duration of entities Stasis Web 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(5000.0)
    public double modifytargetspeedduration;
    /**
     * Range of entities Stasis Web
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(20000)
    public int modifytargetspeedrange;
    /**
     * Used by Behavior NPCs to work out minimum orbit range. If the npc has an effect with a shorter range, it will use the effects range instead.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double npcbehaviormaximumcombatorbitrange;
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
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(3.0)
    public double warpspeedmultiplier;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {ModifyTargetSpeedChance.INSTANCE, ModifyTargetSpeedDuration.INSTANCE, ModifyTargetSpeedRange.INSTANCE, Damage.INSTANCE, Mass.INSTANCE, ShieldCapacity.INSTANCE, ShieldCharge.INSTANCE, Hp.INSTANCE, ArmorHP.INSTANCE, ArmorDamage.INSTANCE, ArmorEmDamageResonance.INSTANCE, ArmorExplosiveDamageResonance.INSTANCE, ArmorUniformity.INSTANCE, ArmorKineticDamageResonance.INSTANCE, StructureUniformity.INSTANCE, ArmorThermalDamageResonance.INSTANCE, ShieldEmDamageResonance.INSTANCE, ShieldExplosiveDamageResonance.INSTANCE, ShieldKineticDamageResonance.INSTANCE, ShieldThermalDamageResonance.INSTANCE, Charge.INSTANCE, SpeedFactor.INSTANCE, EntityBracketColour.INSTANCE, EntityStrength.INSTANCE, MaxVelocity.INSTANCE, Capacity.INSTANCE, SignatureRadius.INSTANCE, EntityFactionLoss.INSTANCE, Speed.INSTANCE, EntitySecurityMaxGain.INSTANCE, ScanResolution.INSTANCE, MaxRange.INSTANCE, RechargeRate.INSTANCE, AIIgnoreDronesBelowSignatureRadius.INSTANCE, DamageMultiplier.INSTANCE, EntityChaseMaxDelay.INSTANCE, EntityChaseMaxDelayChance.INSTANCE, Agility.INSTANCE, EntityChaseMaxDuration.INSTANCE, EntityChaseMaxDurationChance.INSTANCE, BehaviorArmorRepairerDuration.INSTANCE, BehaviorArmorRepairerDischarge.INSTANCE, BehaviorArmorRepairerAmount.INSTANCE, MaxTargetRange.INSTANCE, ScanSpeed.INSTANCE, ArmorDamageAmount.INSTANCE, DisallowAssistance.INSTANCE, WarpSpeedMultiplier.INSTANCE, MissileEntityAoeCloudSizeMultiplier.INSTANCE, MissileEntityAoeVelocityMultiplier.INSTANCE, EnergyNeutralizerAmount.INSTANCE, EnergyNeutralizerRangeOptimal.INSTANCE, WarpScrambleRange.INSTANCE, WarpScrambleStrength.INSTANCE, OptimalSigRadius.INSTANCE, EntityMaxVelocitySignatureRadiusMultiplier.INSTANCE, KineticDamageResonance.INSTANCE, ThermalDamageResonance.INSTANCE, ExplosiveDamageResonance.INSTANCE, AIShouldUseTargetSwitching.INSTANCE, AIShouldUseSecondaryTarget.INSTANCE, EmDamageResonance.INSTANCE, EmDamage.INSTANCE, AIShouldUseSignatureRadius.INSTANCE, AIChanceToNotTargetSwitch.INSTANCE, ExplosiveDamage.INSTANCE, AIShouldUseEffectMultiplier.INSTANCE, KineticDamage.INSTANCE, ThermalDamage.INSTANCE, EntityArmorRepairDuration.INSTANCE, EntityArmorRepairAmount.INSTANCE, AITankingModifierDrone.INSTANCE, EntityShieldBoostDuration.INSTANCE, EntityShieldBoostAmount.INSTANCE, EntityArmorRepairDelayChance.INSTANCE, EntityShieldBoostDelayChance.INSTANCE, EntityGroupRespawnChance.INSTANCE, MissileEntityVelocityMultiplier.INSTANCE, MissileEntityFlightTimeMultiplier.INSTANCE, MaxDirectionalVelocity.INSTANCE, MinTargetVelDmgMultiplier.INSTANCE, EntityChaseMaxDistance.INSTANCE, Falloff.INSTANCE, TrackingSpeed.INSTANCE, EntityFlyRange.INSTANCE, Radius.INSTANCE, EnergyNeutralizerEntityChance.INSTANCE, BehaviorShieldBoosterAmount.INSTANCE, BehaviorShieldBoosterDischarge.INSTANCE, BehaviorShieldBoosterDuration.INSTANCE, DamageMultiplierBonusPerCycle.INSTANCE, EnergyNeutralizerDuration.INSTANCE, DamageMultiplierBonusMax.INSTANCE, BehaviorRemoteArmorRepairDuration.INSTANCE, BehaviorRemoteArmorRepairRange.INSTANCE, BehaviorRemoteArmorRepairFalloff.INSTANCE, BehaviorRemoteArmorRepairDischarge.INSTANCE, MaxLockedTargets.INSTANCE, MaxAttackTargets.INSTANCE, BehaviorWebifierDuration.INSTANCE, BehaviorWebifierRange.INSTANCE, BehaviorWebifierFalloff.INSTANCE, BehaviorWebifierDischarge.INSTANCE, BehaviorWarpDisruptDuration.INSTANCE, EntityEquipmentMin.INSTANCE, BehaviorWarpDisruptRange.INSTANCE, EntityEquipmentMax.INSTANCE, BehaviorWarpDisruptDischarge.INSTANCE, BehaviorWarpScrambleDuration.INSTANCE, BehaviorWarpScrambleRange.INSTANCE, BehaviorWarpScrambleDischarge.INSTANCE, BehaviorWarpScrambleStrength.INSTANCE, BehaviorWarpDisruptStrength.INSTANCE, ScanRadarStrength.INSTANCE, ScanLadarStrength.INSTANCE, EntityEquipmentGroupMax.INSTANCE, ScanMagnetometricStrength.INSTANCE, EntityReactionFactor.INSTANCE, ScanGravimetricStrength.INSTANCE, MissileDamageMultiplier.INSTANCE, BehaviorEnergyNeutralizerDuration.INSTANCE, BehaviorEnergyNeutralizerRange.INSTANCE, PropulsionGraphicID.INSTANCE, BehaviorEnergyNeutralizerFalloff.INSTANCE, BehaviorEnergyNeutralizerDischarge.INSTANCE, EntityAttackDelayMin.INSTANCE, EntityAttackDelayMax.INSTANCE, ShieldRechargeRate.INSTANCE, EntityKillBounty.INSTANCE, CapacitorCapacity.INSTANCE, NpcBehaviorMaximumCombatOrbitRange.INSTANCE, ShieldUniformity.INSTANCE, EntityOverviewShipGroupId.INSTANCE, EntityShieldBoostDelayChanceLarge.INSTANCE, EntityDefenderChance.INSTANCE, EntityArmorRepairDelayChanceLarge.INSTANCE, GfxTurretID.INSTANCE, GfxBoosterID.INSTANCE, EntityAttackRange.INSTANCE, EntityWarpScrambleChance.INSTANCE, WarpScrambleDuration.INSTANCE, MissileLaunchDuration.INSTANCE, EntityLootCountMin.INSTANCE, EntityMissileTypeID.INSTANCE, EntityLootCountMax.INSTANCE, EntitySecurityStatusKillBonus.INSTANCE, EntityCruiseSpeed.INSTANCE })));
    public static final IrregularBattleship.MetaGroup METAGROUP = new IrregularBattleship.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
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
            case  2723 :
            {
                return behaviorshieldboosteramount;
            }
            case  2724 :
            {
                return behaviorshieldboosterdischarge;
            }
            case  2725 :
            {
                return behaviorshieldboosterduration;
            }
            case  70 :
            {
                return agility;
            }
            case  266 :
            {
                return armordamage;
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
            case  2635 :
            {
                return behaviorarmorrepaireramount;
            }
            case  2634 :
            {
                return behaviorarmorrepairerdischarge;
            }
            case  2633 :
            {
                return behaviorarmorrepairerduration;
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
            case  3 :
            {
                return damage;
            }
            case  64 :
            {
                return damagemultiplier;
            }
            case  2734 :
            {
                return damagemultiplierbonusmax;
            }
            case  2733 :
            {
                return damagemultiplierbonuspercycle;
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
            case  638 :
            {
                return entityarmorrepairdelaychance;
            }
            case  1011 :
            {
                return entityarmorrepairdelaychancelarge;
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
            case  1133 :
            {
                return entitymaxvelocitysignatureradiusmultiplier;
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
            case  639 :
            {
                return entityshieldboostdelaychance;
            }
            case  1008 :
            {
                return entityshieldboostdelaychancelarge;
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
            case  76 :
            {
                return maxtargetrange;
            }
            case  37 :
            {
                return maxvelocity;
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
            case  2786 :
            {
                return npcbehaviormaximumcombatorbitrange;
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
            case  110 :
            {
                return thermaldamageresonance;
            }
            case  160 :
            {
                return trackingspeed;
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
            case  600 :
            {
                return warpspeedmultiplier;
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
    public IMetaGroup<IrregularBattleship> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<IrregularBattleship>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/IrregularBattleship.yaml";
        private Map<String, IrregularBattleship> cache = (null);

        @Override
        public IMetaCategory<? super IrregularBattleship> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1667;
        }

        @Override
        public String getName() {
            return "IrregularBattleship";
        }

        @Override
        public synchronized Map<String, IrregularBattleship> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(IrregularBattleship.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, IrregularBattleship> types;
        }
    }
}
