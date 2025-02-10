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
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorSiegeDisallowTetheringModifier;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorSiegeDischarge;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorSiegeDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorSiegeECMResistanceModifier;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorSiegeLocalLogisticsAmountModifier;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorSiegeLocalLogisticsDurationModifier;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorSiegeMassModifier;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorSiegeMaxVelocityModifier;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorSiegeMissileDamageModifier;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorSiegeRemoteAssistanceImpedanceModifier;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorSiegeRemoteRepairImpedanceModifier;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorSiegeSensorDampenerResistanceModifier;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorSiegeTurretDamageModifier;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorSiegeWarpScrambleStatusModifier;
import fr.guiguilechat.jcelechat.model.sde.attributes.BehaviorSiegeWeaponDisruptionResistanceModifier;
import fr.guiguilechat.jcelechat.model.sde.attributes.CapacitorCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.CapacitorNeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.Charge;
import fr.guiguilechat.jcelechat.model.sde.attributes.DoomsdayEnergyNeutAmount;
import fr.guiguilechat.jcelechat.model.sde.attributes.DoomsdayEnergyNeutRadius;
import fr.guiguilechat.jcelechat.model.sde.attributes.DoomsdayEnergyNeutResistanceID;
import fr.guiguilechat.jcelechat.model.sde.attributes.DoomsdayEnergyNeutSignatureRadius;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityEquipmentMax;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityEquipmentMin;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityFactionLoss;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityKillBounty;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityMissileTypeID;
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
import fr.guiguilechat.jcelechat.model.sde.attributes.GfxBoosterID;
import fr.guiguilechat.jcelechat.model.sde.attributes.GfxTurretID;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hp;
import fr.guiguilechat.jcelechat.model.sde.attributes.IsCapitalSize;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxLockedTargets;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxTargetRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxVelocity;
import fr.guiguilechat.jcelechat.model.sde.attributes.MissileDamageMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.MissileLaunchDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.NpcBehaviorMaximumCombatOrbitRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.OptimalSigRadius;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.RechargeRate;
import fr.guiguilechat.jcelechat.model.sde.attributes.RemoteRepairImpedance;
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
import fr.guiguilechat.jcelechat.model.sde.attributes.SignatureRadius;
import fr.guiguilechat.jcelechat.model.sde.attributes.TrackingSpeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarpSpeedMultiplier;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class IrregularDreadnought
    extends Entity
{
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int behaviorsiegedisallowtetheringmodifier;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int behaviorsiegedischarge;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int behaviorsiegeduration;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double behaviorsiegeecmresistancemodifier;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int behaviorsiegelocallogisticsamountmodifier;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int behaviorsiegelocallogisticsdurationmodifier;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int behaviorsiegemassmodifier;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int behaviorsiegemaxvelocitymodifier;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int behaviorsiegemissiledamagemodifier;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int behaviorsiegeremoteassistanceimpedancemodifier;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double behaviorsiegeremoterepairimpedancemodifier;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int behaviorsiegesensordampenerresistancemodifier;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int behaviorsiegeturretdamagemodifier;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int behaviorsiegewarpscramblestatusmodifier;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int behaviorsiegeweapondisruptionresistancemodifier;
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
     * Capacitor capacity
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double capacitorcapacity;
    /**
     * The amount of charge used from the capacitor for a module activation.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double capacitorneed;
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
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int doomsdayenergyneutamount;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int doomsdayenergyneutradius;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int doomsdayenergyneutresistanceid;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int doomsdayenergyneutsignatureradius;
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
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int iscapitalsize;
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
     * The characters missile use efficiency, scales the damage missiles do.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double missiledamagemultiplier;
    /**
     * Cycle time for a missile launch, in milliseconds.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(20000.0)
    public double missilelaunchduration;
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
     * Impedance against Remote Repair (shield, armor, hull and energy).
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double remoterepairimpedance;
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
     * Signature Radius is used for turret tracking and scanning.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(100.0)
    public double signatureradius;
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
    @Stackable(false)
    @DefaultRealValue(3.0)
    public double warpspeedmultiplier;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {EntitySuperWeaponTrackingSpeed.INSTANCE, EntitySuperWeaponOptimalSignatureRadius.INSTANCE, CapacitorNeed.INSTANCE, ShieldCapacity.INSTANCE, ShieldCharge.INSTANCE, ArmorHP.INSTANCE, Hp.INSTANCE, ArmorEmDamageResonance.INSTANCE, ArmorExplosiveDamageResonance.INSTANCE, ArmorKineticDamageResonance.INSTANCE, ArmorThermalDamageResonance.INSTANCE, ShieldEmDamageResonance.INSTANCE, ShieldExplosiveDamageResonance.INSTANCE, ShieldKineticDamageResonance.INSTANCE, Charge.INSTANCE, ShieldThermalDamageResonance.INSTANCE, TrackingSpeed.INSTANCE, Radius.INSTANCE, MaxVelocity.INSTANCE, Capacity.INSTANCE, SignatureRadius.INSTANCE, BehaviorSiegeMissileDamageModifier.INSTANCE, DoomsdayEnergyNeutResistanceID.INSTANCE, EntityFactionLoss.INSTANCE, EntitySecurityMaxGain.INSTANCE, ScanResolution.INSTANCE, MaxRange.INSTANCE, RechargeRate.INSTANCE, MaxLockedTargets.INSTANCE, RemoteRepairImpedance.INSTANCE, Agility.INSTANCE, EntityEquipmentMin.INSTANCE, EntityEquipmentMax.INSTANCE, BehaviorSiegeDuration.INSTANCE, MaxTargetRange.INSTANCE, BehaviorSiegeDischarge.INSTANCE, BehaviorSiegeRemoteRepairImpedanceModifier.INSTANCE, BehaviorSiegeRemoteAssistanceImpedanceModifier.INSTANCE, BehaviorSiegeSensorDampenerResistanceModifier.INSTANCE, ScanRadarStrength.INSTANCE, ScanLadarStrength.INSTANCE, BehaviorSiegeWeaponDisruptionResistanceModifier.INSTANCE, ScanMagnetometricStrength.INSTANCE, BehaviorSiegeECMResistanceModifier.INSTANCE, BehaviorSiegeMaxVelocityModifier.INSTANCE, ScanGravimetricStrength.INSTANCE, DoomsdayEnergyNeutRadius.INSTANCE, DoomsdayEnergyNeutAmount.INSTANCE, BehaviorSiegeWarpScrambleStatusModifier.INSTANCE, MissileDamageMultiplier.INSTANCE, BehaviorSiegeDisallowTetheringModifier.INSTANCE, DoomsdayEnergyNeutSignatureRadius.INSTANCE, BehaviorSiegeMassModifier.INSTANCE, BehaviorSiegeLocalLogisticsAmountModifier.INSTANCE, BehaviorSiegeLocalLogisticsDurationModifier.INSTANCE, WarpSpeedMultiplier.INSTANCE, BehaviorSiegeTurretDamageModifier.INSTANCE, EntitySuperWeaponDuration.INSTANCE, EntitySuperWeaponEmDamage.INSTANCE, EntitySuperWeaponKineticDamage.INSTANCE, EntitySuperWeaponThermalDamage.INSTANCE, EntitySuperWeaponExplosiveDamage.INSTANCE, ShieldRechargeRate.INSTANCE, EntityKillBounty.INSTANCE, NpcBehaviorMaximumCombatOrbitRange.INSTANCE, CapacitorCapacity.INSTANCE, OptimalSigRadius.INSTANCE, GfxTurretID.INSTANCE, GfxBoosterID.INSTANCE, IsCapitalSize.INSTANCE, MissileLaunchDuration.INSTANCE, EntityMissileTypeID.INSTANCE, EntitySecurityStatusKillBonus.INSTANCE, EntitySuperWeaponMaxRange.INSTANCE, EntitySuperWeaponFallOff.INSTANCE })));
    public static final IrregularDreadnought.MetaGroup METAGROUP = new IrregularDreadnought.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  2645 :
            {
                return behaviorsiegedisallowtetheringmodifier;
            }
            case  2637 :
            {
                return behaviorsiegedischarge;
            }
            case  2636 :
            {
                return behaviorsiegeduration;
            }
            case  2642 :
            {
                return behaviorsiegeecmresistancemodifier;
            }
            case  2647 :
            {
                return behaviorsiegelocallogisticsamountmodifier;
            }
            case  2648 :
            {
                return behaviorsiegelocallogisticsdurationmodifier;
            }
            case  2646 :
            {
                return behaviorsiegemassmodifier;
            }
            case  2643 :
            {
                return behaviorsiegemaxvelocitymodifier;
            }
            case  2730 :
            {
                return behaviorsiegemissiledamagemodifier;
            }
            case  2639 :
            {
                return behaviorsiegeremoteassistanceimpedancemodifier;
            }
            case  2638 :
            {
                return behaviorsiegeremoterepairimpedancemodifier;
            }
            case  2640 :
            {
                return behaviorsiegesensordampenerresistancemodifier;
            }
            case  2649 :
            {
                return behaviorsiegeturretdamagemodifier;
            }
            case  2644 :
            {
                return behaviorsiegewarpscramblestatusmodifier;
            }
            case  2641 :
            {
                return behaviorsiegeweapondisruptionresistancemodifier;
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
            case  482 :
            {
                return capacitorcapacity;
            }
            case  6 :
            {
                return capacitorneed;
            }
            case  38 :
            {
                return capacity;
            }
            case  18 :
            {
                return charge;
            }
            case  2260 :
            {
                return doomsdayenergyneutamount;
            }
            case  2259 :
            {
                return doomsdayenergyneutradius;
            }
            case  2609 :
            {
                return doomsdayenergyneutresistanceid;
            }
            case  2261 :
            {
                return doomsdayenergyneutsignatureradius;
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
            case  1785 :
            {
                return iscapitalsize;
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
            case  212 :
            {
                return missiledamagemultiplier;
            }
            case  506 :
            {
                return missilelaunchduration;
            }
            case  2786 :
            {
                return npcbehaviormaximumcombatorbitrange;
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
            case  2116 :
            {
                return remoterepairimpedance;
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
            case  552 :
            {
                return signatureradius;
            }
            case  160 :
            {
                return trackingspeed;
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
    public IMetaGroup<IrregularDreadnought> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<IrregularDreadnought>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/IrregularDreadnought.yaml";
        private Map<Integer, IrregularDreadnought> cache = (null);

        @Override
        public IMetaCategory<? super IrregularDreadnought> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1724;
        }

        @Override
        public String getName() {
            return "IrregularDreadnought";
        }

        @Override
        public synchronized Map<Integer, IrregularDreadnought> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(IrregularDreadnought.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, IrregularDreadnought> types;
        }
    }
}
