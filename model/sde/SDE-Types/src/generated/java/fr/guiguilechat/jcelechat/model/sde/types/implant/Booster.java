package fr.guiguilechat.jcelechat.model.sde.types.implant;

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
import fr.guiguilechat.jcelechat.model.sde.attributes.AgilityBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.AoeCloudSizeBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.AoeVelocityBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorDamageAmountBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorDamageResistanceBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorHpBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorHpBonus2;
import fr.guiguilechat.jcelechat.model.sde.attributes.BoosterAOEVelocityPenalty;
import fr.guiguilechat.jcelechat.model.sde.attributes.BoosterArmorHPPenalty;
import fr.guiguilechat.jcelechat.model.sde.attributes.BoosterArmorRepairAmountPenalty;
import fr.guiguilechat.jcelechat.model.sde.attributes.BoosterCapacitorCapacityPenalty;
import fr.guiguilechat.jcelechat.model.sde.attributes.BoosterDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.BoosterEffectChance1;
import fr.guiguilechat.jcelechat.model.sde.attributes.BoosterEffectChance2;
import fr.guiguilechat.jcelechat.model.sde.attributes.BoosterEffectChance3;
import fr.guiguilechat.jcelechat.model.sde.attributes.BoosterEffectChance4;
import fr.guiguilechat.jcelechat.model.sde.attributes.BoosterEffectChance5;
import fr.guiguilechat.jcelechat.model.sde.attributes.BoosterLastInjectionDatetime;
import fr.guiguilechat.jcelechat.model.sde.attributes.BoosterMaxCharAgeHours;
import fr.guiguilechat.jcelechat.model.sde.attributes.BoosterMaxVelocityPenalty;
import fr.guiguilechat.jcelechat.model.sde.attributes.BoosterMissileAOECloudPenalty;
import fr.guiguilechat.jcelechat.model.sde.attributes.BoosterMissileVelocityPenalty;
import fr.guiguilechat.jcelechat.model.sde.attributes.BoosterShieldBoostAmountPenalty;
import fr.guiguilechat.jcelechat.model.sde.attributes.BoosterShieldCapacityPenalty;
import fr.guiguilechat.jcelechat.model.sde.attributes.BoosterTurretFalloffPenalty;
import fr.guiguilechat.jcelechat.model.sde.attributes.BoosterTurretOptimalRangePenalty;
import fr.guiguilechat.jcelechat.model.sde.attributes.BoosterTurretTrackingPenalty;
import fr.guiguilechat.jcelechat.model.sde.attributes.Boosterness;
import fr.guiguilechat.jcelechat.model.sde.attributes.CapRechargeBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.CapacitorCapacityBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.CargoScanResistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.CharismaBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.CopySpeedBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.CpuOutputBonus2;
import fr.guiguilechat.jcelechat.model.sde.attributes.DamageMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.DamageMultiplierBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.DroneMaxVelocityBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.DroneRangeBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.DroneTrackingBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.DurationBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.DurationSkillBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.EnergyWarfareResistanceBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.FalloffBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.FollowsJumpClones;
import fr.guiguilechat.jcelechat.model.sde.attributes.HullDamageResistanceBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.HullHpBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.IceHarvestCycleBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.IntelligenceBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.MWDSignatureRadiusBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.ManufacturingTimeBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxFlightTimeBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxRangeBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxScanDeviationModifier;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxTargetRangeBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.MemoryBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaLevelOld;
import fr.guiguilechat.jcelechat.model.sde.attributes.MineralNeedResearchBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.MiningAmountBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.MissileDamageMultiplierBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.NonDiminishingSkillInjectorUses;
import fr.guiguilechat.jcelechat.model.sde.attributes.Nondestructible;
import fr.guiguilechat.jcelechat.model.sde.attributes.PassiveEmDamageResistanceBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.PassiveExplosiveDamageResistanceBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.PassiveKineticDamageResistanceBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.PassiveThermicDamageResistanceBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.PerceptionBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.PowerEngineeringOutputBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.RangeSkillBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.RefiningYieldMutator;
import fr.guiguilechat.jcelechat.model.sde.attributes.ReloadTimeBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.RemoteRepDurationBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.RofBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanGravimetricStrengthPercent;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanLadarStrengthPercent;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanMagnetometricStrengthPercent;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanRadarStrengthPercent;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanResolutionBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanSkillTargetPaintStrengthBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanStrengthBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScramblerRangeAdd;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldBoostMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldCapacityBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.SignatureRadiusBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.SignatureRadiusBonusPercent;
import fr.guiguilechat.jcelechat.model.sde.attributes.SocialBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.SocialMutator;
import fr.guiguilechat.jcelechat.model.sde.attributes.SpeedFBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.SpeedFactor;
import fr.guiguilechat.jcelechat.model.sde.attributes.StabilizeCloakDurationBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.StasisWebRangeAdd;
import fr.guiguilechat.jcelechat.model.sde.attributes.StasisWebRangeBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.StasisWebifierResistanceBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.ThermodynamicsHeatDamage;
import fr.guiguilechat.jcelechat.model.sde.attributes.TrackingSpeedBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.TurretSpeeBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.VelocityBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.VirusCoherenceBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.VirusStrengthBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarpSBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarpScrambleRangeBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarpScrambleStrengthBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.WeaponDisruptionResistanceBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.WillpowerBonus;
import fr.guiguilechat.jcelechat.model.sde.types.Implant;
import org.yaml.snakeyaml.Yaml;

public class Booster
    extends Implant
{
    /**
     * reduction in MicroWarp Drive signature
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int mwdsignatureradiusbonus;
    /**
     * Attribute defining usage count for penaltyless skill injections
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int nondiminishingskillinjectoruses;
    /**
     * Autogenerated skill attribute, WarpSBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int warpsbonus;
    /**
     * Bonus to the agility for a ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double agilitybonus;
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
     * Bonus attribute for armor repair amount.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int armordamageamountbonus;
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int armordamageresistancebonus;
    /**
     * Autogenerated skill attribute, armorHpBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int armorhpbonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double armorhpbonus2;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int boosteraoevelocitypenalty;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int boosterarmorhppenalty;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int boosterarmorrepairamountpenalty;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int boostercapacitorcapacitypenalty;
    /**
     * Duration of booster, after this duration the booster is destroyed
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double boosterduration;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double boostereffectchance1;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double boostereffectchance2;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double boostereffectchance3;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double boostereffectchance4;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double boostereffectchance5;
    /**
     * The last allowed injection date.  After this date the booster can no longer be consumed. Formatted YYYY.MM.DD HH:MM:SS
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double boosterlastinjectiondatetime;
    /**
     * This attribute deactivates the booster after the character's age reaches a certain amount
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int boostermaxcharagehours;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int boostermaxvelocitypenalty;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int boostermissileaoecloudpenalty;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int boostermissilevelocitypenalty;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int boostershieldboostamountpenalty;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int boostershieldcapacitypenalty;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int boosterturretfalloffpenalty;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int boosterturretoptimalrangepenalty;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int boosterturrettrackingpenalty;
    /**
     * Whether an item is a booster or not.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int boosterness;
    /**
     * Autogenerated skill attribute, CapRechargeBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double caprechargebonus;
    /**
     * Autogenerated skill attribute, CapacitorCapacityBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double capacitorcapacitybonus;
    /**
     * Chance of being able to resist a cargo scan.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double cargoscanresistance;
    /**
     * +/- bonus to the charisma of a character.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int charismabonus;
    /**
     * Autogenerated skill attribute, CopySpeedBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int copyspeedbonus;
    /**
     * Autogenerated skill attribute, cpu OutputBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double cpuoutputbonus2;
    /**
     * Damage multiplier.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double damagemultiplier;
    /**
     * Autogenerated skill attribute, damageMultiplierBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int damagemultiplierbonus;
    /**
     * Increases max velocity of all drone types.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int dronemaxvelocitybonus;
    /**
     * Autogenerated skill attribute, droneRangeBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int dronerangebonus;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int dronetrackingbonus;
    /**
     * Bonus to duration.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int durationbonus;
    /**
     * Autogenerated skill attribute, DurationBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int durationskillbonus;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int energywarfareresistancebonus;
    /**
     * Autogenerated skill attribute, falloffBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double falloffbonus;
    /**
     * Stays with characters across clone changes. ONLY FOR IMPLANTS AND BOOSTERS.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int followsjumpclones;
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int hulldamageresistancebonus;
    /**
     * Autogenerated skill attribute, hullHpBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double hullhpbonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double iceharvestcyclebonus;
    /**
     * +/- bonus to the intelligence of a character.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int intelligencebonus;
    /**
     * Autogenerated skill attribute, manufacturingTimeBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int manufacturingtimebonus;
    /**
     * Autogenerated skill attribute, max flightTimeBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxflighttimebonus;
    /**
     * Autogenerated skill attribute, maxRangeBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double maxrangebonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxscandeviationmodifier;
    /**
     * Bonus to Max Targeting Range
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double maxtargetrangebonus;
    /**
     * +/- bonus to the memory of a character.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int memorybonus;
    /**
     * Authoring has been moved to FSD
     * The ranking of the module within its tech level
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int metalevelold;
    /**
     * Autogenerated skill attribute, mineralNeedResearchBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int mineralneedresearchbonus;
    /**
     * Autogenerated skill attribute, miningAmountBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int miningamountbonus;
    /**
     * Additional percentage to the characters missile damage multiplier.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double missiledamagemultiplierbonus;
    /**
     * This will make the item non-destructible upon podding.  ONLY FOR IMPLANTS AND BOOSTERS
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int nondestructible;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int passiveemdamageresistancebonus;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int passiveexplosivedamageresistancebonus;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int passivekineticdamageresistancebonus;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int passivethermicdamageresistancebonus;
    /**
     * +/- bonus to the perception of a character.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int perceptionbonus;
    /**
     * Autogenerated skill attribute, PowerOutputBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double powerengineeringoutputbonus;
    /**
     * Autogenerated skill attribute, rangeSkillBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rangeskillbonus;
    /**
     * Autogenerated skill attribute, refiningYieldMutator
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int refiningyieldmutator;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int reloadtimebonus;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double remoterepdurationbonus;
    /**
     * Autogenerated skill attribute, rofBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rofbonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int scangravimetricstrengthpercent;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int scanladarstrengthpercent;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int scanmagnetometricstrengthpercent;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int scanradarstrengthpercent;
    /**
     * Bonus for scan resolution
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double scanresolutionbonus;
    /**
     * Skill attribute for increasing effectiveness on Target Painters
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int scanskilltargetpaintstrengthbonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int scanstrengthbonus;
    /**
     * Bonus added to warp scrambler range
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int scramblerrangeadd;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shieldboostmultiplier;
    /**
     * Autogenerated skill attribute, shieldCapacityBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shieldcapacitybonus;
    /**
     * Autogenerated skill attribute, signatureRadiusBonus
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double signatureradiusbonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int signatureradiusbonuspercent;
    /**
     * Bonus To standing gain towards non CONCORD npcs  
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(100)
    public int socialbonus;
    /**
     * Autogenerated skill attribute, socialMutator
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int socialmutator;
    /**
     * Autogenerated skill attribute, speedFBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int speedfbonus;
    /**
     * Factor by which topspeed increases.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double speedfactor;
    /**
     * bonus to stabilize cloak duration
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int stabilizecloakdurationbonus;
    /**
     * Bonus added to stasis webifier range
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int stasiswebrangeadd;
    /**
     * Percent bonus for Stasis Webifiers maximum range
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int stasiswebrangebonus;
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int stasiswebifierresistancebonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int thermodynamicsheatdamage;
    /**
     * Tracking Speed Bonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double trackingspeedbonus;
    /**
     * Autogenerated skill attribute, TurretSpeeBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int turretspeebonus;
    /**
     * Autogenerated skill attribute, velocityBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double velocitybonus;
    /**
     * Adds to the virus coherence of profession modules
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int viruscoherencebonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int virusstrengthbonus;
    /**
     * Warp Scramble Range Bonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int warpscramblerangebonus;
    /**
     * Warp Scramble Strength Bonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int warpscramblestrengthbonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int weapondisruptionresistancebonus;
    /**
     * +/- bonus to the willpower of a character.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int willpowerbonus;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {ScanGravimetricStrengthPercent.INSTANCE, ScanLadarStrengthPercent.INSTANCE, ScanMagnetometricStrengthPercent.INSTANCE, ScanRadarStrengthPercent.INSTANCE, MWDSignatureRadiusBonus.INSTANCE, IceHarvestCycleBonus.INSTANCE, ReloadTimeBonus.INSTANCE, SpeedFactor.INSTANCE, RequiredSkill1Level.INSTANCE, DroneTrackingBonus.INSTANCE, ShieldBoostMultiplier.INSTANCE, DamageMultiplierBonus.INSTANCE, RofBonus.INSTANCE, RangeSkillBonus.INSTANCE, Capacity.INSTANCE, SignatureRadiusBonus.INSTANCE, MaxFlightTimeBonus.INSTANCE, WarpScrambleRangeBonus.INSTANCE, WeaponDisruptionResistanceBonus.INSTANCE, MaxTargetRangeBonus.INSTANCE, ScanResolutionBonus.INSTANCE, CapacitorCapacityBonus.INSTANCE, DurationSkillBonus.INSTANCE, PowerEngineeringOutputBonus.INSTANCE, CapRechargeBonus.INSTANCE, VelocityBonus.INSTANCE, ArmorHpBonus2 .INSTANCE, StabilizeCloakDurationBonus.INSTANCE, SpeedFBonus.INSTANCE, Boosterness.INSTANCE, DamageMultiplier.INSTANCE, ScanSkillTargetPaintStrengthBonus.INSTANCE, BoosterEffectChance1 .INSTANCE, DurationBonus.INSTANCE, BoosterEffectChance2 .INSTANCE, BoosterEffectChance3 .INSTANCE, BoosterEffectChance4 .INSTANCE, BoosterEffectChance5 .INSTANCE, HullHpBonus.INSTANCE, BoosterDuration.INSTANCE, ScanStrengthBonus.INSTANCE, DroneMaxVelocityBonus.INSTANCE, ArmorHpBonus.INSTANCE, AoeVelocityBonus.INSTANCE, AoeCloudSizeBonus.INSTANCE, ShieldCapacityBonus.INSTANCE, FalloffBonus.INSTANCE, StasisWebifierResistanceBonus.INSTANCE, MaxRangeBonus.INSTANCE, Nondestructible.INSTANCE, ArmorDamageResistanceBonus.INSTANCE, BoosterShieldBoostAmountPenalty.INSTANCE, SocialBonus.INSTANCE, BoosterMaxCharAgeHours.INSTANCE, WarpSBonus.INSTANCE, BoosterArmorHPPenalty.INSTANCE, BoosterLastInjectionDatetime.INSTANCE, BoosterArmorRepairAmountPenalty.INSTANCE, BoosterShieldCapacityPenalty.INSTANCE, BoosterTurretOptimalRangePenalty.INSTANCE, MetaLevelOld.INSTANCE, BoosterTurretTrackingPenalty.INSTANCE, BoosterTurretFalloffPenalty.INSTANCE, VirusCoherenceBonus.INSTANCE, RefiningYieldMutator.INSTANCE, BoosterAOEVelocityPenalty.INSTANCE, FollowsJumpClones.INSTANCE, BoosterMissileVelocityPenalty.INSTANCE, BoosterMissileAOECloudPenalty.INSTANCE, BoosterCapacitorCapacityPenalty.INSTANCE, VirusStrengthBonus.INSTANCE, ArmorDamageAmountBonus.INSTANCE, BoosterMaxVelocityPenalty.INSTANCE, MaxScanDeviationModifier.INSTANCE, StasisWebRangeAdd.INSTANCE, AgilityBonus.INSTANCE, Radius.INSTANCE, CpuOutputBonus2 .INSTANCE, CharismaBonus.INSTANCE, IntelligenceBonus.INSTANCE, MemoryBonus.INSTANCE, PerceptionBonus.INSTANCE, MiningAmountBonus.INSTANCE, WillpowerBonus.INSTANCE, SocialMutator.INSTANCE, RequiredSkill1 .INSTANCE, ManufacturingTimeBonus.INSTANCE, TurretSpeeBonus.INSTANCE, ScramblerRangeAdd.INSTANCE, HullDamageResistanceBonus.INSTANCE, StasisWebRangeBonus.INSTANCE, CargoScanResistance.INSTANCE, CopySpeedBonus.INSTANCE, DroneRangeBonus.INSTANCE, SignatureRadiusBonusPercent.INSTANCE, ThermodynamicsHeatDamage.INSTANCE, RemoteRepDurationBonus.INSTANCE, MineralNeedResearchBonus.INSTANCE, MissileDamageMultiplierBonus.INSTANCE, EnergyWarfareResistanceBonus.INSTANCE, WarpScrambleStrengthBonus.INSTANCE, PassiveEmDamageResistanceBonus.INSTANCE, PassiveExplosiveDamageResistanceBonus.INSTANCE, PassiveKineticDamageResistanceBonus.INSTANCE, PassiveThermicDamageResistanceBonus.INSTANCE, NonDiminishingSkillInjectorUses.INSTANCE, TrackingSpeedBonus.INSTANCE })));
    public static final Booster.MetaGroup METAGROUP = new Booster.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  1803 :
            {
                return mwdsignatureradiusbonus;
            }
            case  2806 :
            {
                return nondiminishingskillinjectoruses;
            }
            case  624 :
            {
                return warpsbonus;
            }
            case  151 :
            {
                return agilitybonus;
            }
            case  848 :
            {
                return aoecloudsizebonus;
            }
            case  847 :
            {
                return aoevelocitybonus;
            }
            case  895 :
            {
                return armordamageamountbonus;
            }
            case  3429 :
            {
                return armordamageresistancebonus;
            }
            case  335 :
            {
                return armorhpbonus;
            }
            case  1083 :
            {
                return armorhpbonus2;
            }
            case  1147 :
            {
                return boosteraoevelocitypenalty;
            }
            case  1141 :
            {
                return boosterarmorhppenalty;
            }
            case  1142 :
            {
                return boosterarmorrepairamountpenalty;
            }
            case  1150 :
            {
                return boostercapacitorcapacitypenalty;
            }
            case  330 :
            {
                return boosterduration;
            }
            case  1089 :
            {
                return boostereffectchance1;
            }
            case  1090 :
            {
                return boostereffectchance2;
            }
            case  1091 :
            {
                return boostereffectchance3;
            }
            case  1092 :
            {
                return boostereffectchance4;
            }
            case  1093 :
            {
                return boostereffectchance5;
            }
            case  2422 :
            {
                return boosterlastinjectiondatetime;
            }
            case  1647 :
            {
                return boostermaxcharagehours;
            }
            case  1151 :
            {
                return boostermaxvelocitypenalty;
            }
            case  1149 :
            {
                return boostermissileaoecloudpenalty;
            }
            case  1148 :
            {
                return boostermissilevelocitypenalty;
            }
            case  616 :
            {
                return boostershieldboostamountpenalty;
            }
            case  1143 :
            {
                return boostershieldcapacitypenalty;
            }
            case  1146 :
            {
                return boosterturretfalloffpenalty;
            }
            case  1144 :
            {
                return boosterturretoptimalrangepenalty;
            }
            case  1145 :
            {
                return boosterturrettrackingpenalty;
            }
            case  1087 :
            {
                return boosterness;
            }
            case  314 :
            {
                return caprechargebonus;
            }
            case  1079 :
            {
                return capacitorcapacitybonus;
            }
            case  188 :
            {
                return cargoscanresistance;
            }
            case  175 :
            {
                return charismabonus;
            }
            case  452 :
            {
                return copyspeedbonus;
            }
            case  424 :
            {
                return cpuoutputbonus2;
            }
            case  64 :
            {
                return damagemultiplier;
            }
            case  292 :
            {
                return damagemultiplierbonus;
            }
            case  591 :
            {
                return dronemaxvelocitybonus;
            }
            case  459 :
            {
                return dronerangebonus;
            }
            case  3353 :
            {
                return dronetrackingbonus;
            }
            case  66 :
            {
                return durationbonus;
            }
            case  312 :
            {
                return durationskillbonus;
            }
            case  2267 :
            {
                return energywarfareresistancebonus;
            }
            case  349 :
            {
                return falloffbonus;
            }
            case  1916 :
            {
                return followsjumpclones;
            }
            case  3258 :
            {
                return hulldamageresistancebonus;
            }
            case  327 :
            {
                return hullhpbonus;
            }
            case  780 :
            {
                return iceharvestcyclebonus;
            }
            case  176 :
            {
                return intelligencebonus;
            }
            case  440 :
            {
                return manufacturingtimebonus;
            }
            case  557 :
            {
                return maxflighttimebonus;
            }
            case  351 :
            {
                return maxrangebonus;
            }
            case  1156 :
            {
                return maxscandeviationmodifier;
            }
            case  309 :
            {
                return maxtargetrangebonus;
            }
            case  177 :
            {
                return memorybonus;
            }
            case  633 :
            {
                return metalevelold;
            }
            case  468 :
            {
                return mineralneedresearchbonus;
            }
            case  434 :
            {
                return miningamountbonus;
            }
            case  213 :
            {
                return missiledamagemultiplierbonus;
            }
            case  1890 :
            {
                return nondestructible;
            }
            case  994 :
            {
                return passiveemdamageresistancebonus;
            }
            case  995 :
            {
                return passiveexplosivedamageresistancebonus;
            }
            case  996 :
            {
                return passivekineticdamageresistancebonus;
            }
            case  997 :
            {
                return passivethermicdamageresistancebonus;
            }
            case  178 :
            {
                return perceptionbonus;
            }
            case  313 :
            {
                return powerengineeringoutputbonus;
            }
            case  294 :
            {
                return rangeskillbonus;
            }
            case  379 :
            {
                return refiningyieldmutator;
            }
            case  2573 :
            {
                return reloadtimebonus;
            }
            case  3024 :
            {
                return remoterepdurationbonus;
            }
            case  293 :
            {
                return rofbonus;
            }
            case  1027 :
            {
                return scangravimetricstrengthpercent;
            }
            case  1028 :
            {
                return scanladarstrengthpercent;
            }
            case  1029 :
            {
                return scanmagnetometricstrengthpercent;
            }
            case  1030 :
            {
                return scanradarstrengthpercent;
            }
            case  566 :
            {
                return scanresolutionbonus;
            }
            case  832 :
            {
                return scanskilltargetpaintstrengthbonus;
            }
            case  846 :
            {
                return scanstrengthbonus;
            }
            case  3257 :
            {
                return scramblerrangeadd;
            }
            case  548 :
            {
                return shieldboostmultiplier;
            }
            case  337 :
            {
                return shieldcapacitybonus;
            }
            case  554 :
            {
                return signatureradiusbonus;
            }
            case  973 :
            {
                return signatureradiusbonuspercent;
            }
            case  362 :
            {
                return socialbonus;
            }
            case  438 :
            {
                return socialmutator;
            }
            case  318 :
            {
                return speedfbonus;
            }
            case  20 :
            {
                return speedfactor;
            }
            case  3134 :
            {
                return stabilizecloakdurationbonus;
            }
            case  3206 :
            {
                return stasiswebrangeadd;
            }
            case  2747 :
            {
                return stasiswebrangebonus;
            }
            case  3422 :
            {
                return stasiswebifierresistancebonus;
            }
            case  1229 :
            {
                return thermodynamicsheatdamage;
            }
            case  767 :
            {
                return trackingspeedbonus;
            }
            case  441 :
            {
                return turretspeebonus;
            }
            case  315 :
            {
                return velocitybonus;
            }
            case  1915 :
            {
                return viruscoherencebonus;
            }
            case  1918 :
            {
                return virusstrengthbonus;
            }
            case  1327 :
            {
                return warpscramblerangebonus;
            }
            case  3035 :
            {
                return warpscramblestrengthbonus;
            }
            case  2353 :
            {
                return weapondisruptionresistancebonus;
            }
            case  179 :
            {
                return willpowerbonus;
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
    public IMetaGroup<Booster> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Booster>
    {
        public static final String RESOURCE_PATH = "SDE/types/implant/Booster.yaml";
        private Map<String, Booster> cache = (null);

        @Override
        public IMetaCategory<? super Booster> category() {
            return Implant.METACAT;
        }

        @Override
        public int getGroupId() {
            return  303;
        }

        @Override
        public String getName() {
            return "Booster";
        }

        @Override
        public synchronized Map<String, Booster> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(Booster.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, Booster> types;
        }
    }
}
