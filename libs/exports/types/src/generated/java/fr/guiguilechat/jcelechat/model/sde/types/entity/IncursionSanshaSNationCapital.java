package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultRealValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.*;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;

public class IncursionSanshaSNationCapital
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
     * Can not be attacked by doomsday devices
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int aiimmunetosuperweapon;
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
     * Capacitor capacity
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double capacitorcapacity;
    /**
     * If this module is in use and this attribute is 1, then assistance modules cannot be used on the ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int disallowassistance;
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
     * The base time between ECM bursts
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(300000)
    public int entityremoteecmbaseduration;
    /**
     * Chance that the NPC remote ECM fires
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int entityremoteecmchanceofactivation;
    /**
     * The current duration for the remote ECM ( this is recalculated each time it is activated )
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(300000)
    public int entityremoteecmduration;
    /**
     * The scaling factor used for the NPC remote ECM
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.9)
    public double entityremoteecmdurationscale;
    /**
     * The number of players in each step of scaling of remote ECM
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(8)
    public int entityremoteecmextraplayerscale;
    /**
     * The base number of players, where the scaling of the remote ECM should start
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(40)
    public int entityremoteecmintendednumplayers;
    /**
     * Lower cap, so even if more players are added to the bubble remote ECM duration will not go below this value
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(10000)
    public int entityremoteecmminduration;
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
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int iscapitalsize;
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
     * Maximum velocity of ship
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double maxvelocity;
    /**
     * Maximum distance to a friendly NPC so that remote repairs may be performed on it.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(5000)
    public int npcassistancerange;
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
    @DefaultRealValue(0.75)
    public double npcremoteshieldboostthreshold;
    /**
     * The graphicID of the propulsion system.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int propulsiongraphicid;
    /**
     * Amount of time taken to fully recharge the capacitor.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double rechargerate;
    /**
     * +/- modifier to the gravimetric strength of an electronic system.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double scangravimetricstrengthbonus;
    /**
     * +/- modifier to the ladar strength of an electronic system.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double scanladarstrengthbonus;
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
     * Amount of maximum shield HP on the item.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shieldcapacity;
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
     * DO NOT MESS WITH
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double structureuniformity;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {EntityGroupRespawnChance.INSTANCE, EntityRemoteECMChanceOfActivation.INSTANCE, ShieldCapacity.INSTANCE, Hp.INSTANCE, ArmorHP.INSTANCE, ArmorEmDamageResonance.INSTANCE, ArmorExplosiveDamageResonance.INSTANCE, ArmorUniformity.INSTANCE, ArmorKineticDamageResonance.INSTANCE, StructureUniformity.INSTANCE, ArmorThermalDamageResonance.INSTANCE, ShieldEmDamageResonance.INSTANCE, ShieldExplosiveDamageResonance.INSTANCE, ShieldKineticDamageResonance.INSTANCE, ShieldThermalDamageResonance.INSTANCE, EntityChaseMaxDistance.INSTANCE, EntityBracketColour.INSTANCE, EntityFlyRange.INSTANCE, EnergyNeutralizerEntityChance.INSTANCE, MaxVelocity.INSTANCE, SignatureRadius.INSTANCE, EnergyNeutralizerDuration.INSTANCE, EntityFactionLoss.INSTANCE, NpcRemoteShieldBoostDuration.INSTANCE, EntitySecurityMaxGain.INSTANCE, NpcRemoteShieldBoostChance.INSTANCE, ScanResolution.INSTANCE, NpcRemoteShieldBoostAmount.INSTANCE, NpcRemoteShieldBoostThreshold.INSTANCE, RechargeRate.INSTANCE, NpcAssistanceRange.INSTANCE, MaxLockedTargets.INSTANCE, MaxAttackTargets.INSTANCE, EntityChaseMaxDelay.INSTANCE, EntityChaseMaxDelayChance.INSTANCE, Agility.INSTANCE, EntityChaseMaxDuration.INSTANCE, EntityChaseMaxDurationChance.INSTANCE, EntityEquipmentMin.INSTANCE, EntityEquipmentMax.INSTANCE, ScanRadarStrength.INSTANCE, EntityEquipmentGroupMax.INSTANCE, EntityReactionFactor.INSTANCE, DisallowAssistance.INSTANCE, PropulsionGraphicID.INSTANCE, EntityAttackDelayMin.INSTANCE, EntityAttackDelayMax.INSTANCE, NpcRemoteShieldBoostMaxTargets.INSTANCE, ShieldRechargeRate.INSTANCE, EnergyNeutralizerAmount.INSTANCE, EnergyNeutralizerRangeOptimal.INSTANCE, CapacitorCapacity.INSTANCE, ShieldUniformity.INSTANCE, EntityOverviewShipGroupId.INSTANCE, ScanGravimetricStrengthBonus.INSTANCE, ScanLadarStrengthBonus.INSTANCE, ScanMagnetometricStrengthBonus.INSTANCE, EntityShieldBoostDelayChanceLarge.INSTANCE, AIShouldUseTargetSwitching.INSTANCE, ScanRadarStrengthBonus.INSTANCE, AIShouldUseSecondaryTarget.INSTANCE, AIShouldUseSignatureRadius.INSTANCE, AIChanceToNotTargetSwitch.INSTANCE, AIShouldUseEffectMultiplier.INSTANCE, GfxBoosterID.INSTANCE, AIImmuneToSuperWeapon.INSTANCE, EntityAttackRange.INSTANCE, IsCapitalSize.INSTANCE, EntityLootCountMin.INSTANCE, EntityRemoteECMDuration.INSTANCE, EntityRemoteECMMinDuration.INSTANCE, EntitySecurityStatusKillBonus.INSTANCE, EntityCruiseSpeed.INSTANCE, EntityShieldBoostDuration.INSTANCE, EntityRemoteECMDurationScale.INSTANCE, EntityShieldBoostAmount.INSTANCE, EntityRemoteECMBaseDuration.INSTANCE, EntityRemoteECMExtraPlayerScale.INSTANCE, EntityRemoteECMIntendedNumPlayers.INSTANCE })));
    public static final IncursionSanshaSNationCapital.MetaGroup METAGROUP = new IncursionSanshaSNationCapital.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  1651 :
            {
                return aichancetonottargetswitch;
            }
            case  1654 :
            {
                return aiimmunetosuperweapon;
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
            case  854 :
            {
                return disallowassistance;
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
            case  1661 :
            {
                return entityremoteecmbaseduration;
            }
            case  1664 :
            {
                return entityremoteecmchanceofactivation;
            }
            case  1658 :
            {
                return entityremoteecmduration;
            }
            case  1660 :
            {
                return entityremoteecmdurationscale;
            }
            case  1662 :
            {
                return entityremoteecmextraplayerscale;
            }
            case  1663 :
            {
                return entityremoteecmintendednumplayers;
            }
            case  1659 :
            {
                return entityremoteecmminduration;
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
            case  1008 :
            {
                return entityshieldboostdelaychancelarge;
            }
            case  636 :
            {
                return entityshieldboostduration;
            }
            case  246 :
            {
                return gfxboosterid;
            }
            case  9 :
            {
                return hp;
            }
            case  1785 :
            {
                return iscapitalsize;
            }
            case  193 :
            {
                return maxattacktargets;
            }
            case  192 :
            {
                return maxlockedtargets;
            }
            case  37 :
            {
                return maxvelocity;
            }
            case  1464 :
            {
                return npcassistancerange;
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
            case  217 :
            {
                return propulsiongraphicid;
            }
            case  55 :
            {
                return rechargerate;
            }
            case  238 :
            {
                return scangravimetricstrengthbonus;
            }
            case  239 :
            {
                return scanladarstrengthbonus;
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
            case  263 :
            {
                return shieldcapacity;
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
            case  525 :
            {
                return structureuniformity;
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
    public IMetaGroup<IncursionSanshaSNationCapital> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<IncursionSanshaSNationCapital>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/IncursionSanshaSNationCapital.yaml";
        private Map<Integer, IncursionSanshaSNationCapital> cache = (null);

        @Override
        public IMetaCategory<? super IncursionSanshaSNationCapital> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1052;
        }

        @Override
        public String getName() {
            return "IncursionSanshaSNationCapital";
        }

        @Override
        public synchronized Map<Integer, IncursionSanshaSNationCapital> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(IncursionSanshaSNationCapital.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, IncursionSanshaSNationCapital> types;
        }
    }
}
