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
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorHpBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorRepairBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.BoosterAttributeModifier;
import fr.guiguilechat.jcelechat.model.sde.attributes.CapRechargeBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.CharismaBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.CpuOutputBonus2;
import fr.guiguilechat.jcelechat.model.sde.attributes.DamageMultiplierBonusMaxModifier;
import fr.guiguilechat.jcelechat.model.sde.attributes.DamageMultiplierBonusPerCycleModifier;
import fr.guiguilechat.jcelechat.model.sde.attributes.DisallowOffensiveModifierBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.DoesNotEmergencyWarp;
import fr.guiguilechat.jcelechat.model.sde.attributes.DurationBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.FollowsJumpClones;
import fr.guiguilechat.jcelechat.model.sde.attributes.HydraDroneRangeBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.HydraDroneTrackingBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.HydraMissileExplosionVelocityBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.HydraMissileFlightTimeBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.ImplantSetAmulet;
import fr.guiguilechat.jcelechat.model.sde.attributes.ImplantSetBloodraider;
import fr.guiguilechat.jcelechat.model.sde.attributes.ImplantSetCaldariNavy;
import fr.guiguilechat.jcelechat.model.sde.attributes.ImplantSetFederationNavy;
import fr.guiguilechat.jcelechat.model.sde.attributes.ImplantSetGuristas;
import fr.guiguilechat.jcelechat.model.sde.attributes.ImplantSetHalo;
import fr.guiguilechat.jcelechat.model.sde.attributes.ImplantSetHydra;
import fr.guiguilechat.jcelechat.model.sde.attributes.ImplantSetImperialNavy;
import fr.guiguilechat.jcelechat.model.sde.attributes.ImplantSetLGCaldariNavy;
import fr.guiguilechat.jcelechat.model.sde.attributes.ImplantSetLGFederationNavy;
import fr.guiguilechat.jcelechat.model.sde.attributes.ImplantSetLGImperialNavy;
import fr.guiguilechat.jcelechat.model.sde.attributes.ImplantSetLGRepublicFleet;
import fr.guiguilechat.jcelechat.model.sde.attributes.ImplantSetMordus;
import fr.guiguilechat.jcelechat.model.sde.attributes.ImplantSetNirvana;
import fr.guiguilechat.jcelechat.model.sde.attributes.ImplantSetORE;
import fr.guiguilechat.jcelechat.model.sde.attributes.ImplantSetRapture;
import fr.guiguilechat.jcelechat.model.sde.attributes.ImplantSetRepublicFleet;
import fr.guiguilechat.jcelechat.model.sde.attributes.ImplantSetSavior;
import fr.guiguilechat.jcelechat.model.sde.attributes.ImplantSetSerpentis;
import fr.guiguilechat.jcelechat.model.sde.attributes.ImplantSetSerpentis2;
import fr.guiguilechat.jcelechat.model.sde.attributes.ImplantSetSisters;
import fr.guiguilechat.jcelechat.model.sde.attributes.ImplantSetSyndicate;
import fr.guiguilechat.jcelechat.model.sde.attributes.ImplantSetThukker;
import fr.guiguilechat.jcelechat.model.sde.attributes.ImplantSetWarpSpeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.Implantness;
import fr.guiguilechat.jcelechat.model.sde.attributes.IntelligenceBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.JumpDriveRangeBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxRangeBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxTargetRangeBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.MemoryBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaLevelOld;
import fr.guiguilechat.jcelechat.model.sde.attributes.Nondestructible;
import fr.guiguilechat.jcelechat.model.sde.attributes.PassiveEmDamageResistanceBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.PassiveExplosiveDamageResistanceBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.PassiveKineticDamageResistanceBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.PassiveThermicDamageResistanceBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.PerceptionBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.PowerEngineeringOutputBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.RangeSkillBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.RemoteRepDurationBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.RepairBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill2;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill2Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanGravimetricStrengthModifier;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanGravimetricStrengthPercent;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanLadarStrengthModifier;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanLadarStrengthPercent;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanMagnetometricStrengthModifier;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanMagnetometricStrengthPercent;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanRadarStrengthModifier;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanRadarStrengthPercent;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanStrengthBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.SetBonusMimesis;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldBoostMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldCapacityBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldHpBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.SignatureRadiusBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.SmugglingChanceBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.TechLevel;
import fr.guiguilechat.jcelechat.model.sde.attributes.TrackingSpeedMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.VelocityBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarpSBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.WillpowerBonus;
import fr.guiguilechat.jcelechat.model.sde.types.Implant;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class Cyberimplant
    extends Implant
{
    /**
     * Implant Set Nirvana
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double implantsetnirvana;
    /**
     * Rapture Implant Set Bonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double implantsetrapture;
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
    @DefaultIntValue(0)
    public int armorrepairbonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int boosterattributemodifier;
    /**
     * Autogenerated skill attribute, CapRechargeBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double caprechargebonus;
    /**
     * +/- bonus to the charisma of a character.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int charismabonus;
    /**
     * Autogenerated skill attribute, cpu OutputBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double cpuoutputbonus2;
    /**
     * Modifier for damageMultiplierBonusMax used by Triglavians
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double damagemultiplierbonusmaxmodifier;
    /**
     * Modifier for Triglavian ramp-up value
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double damagemultiplierbonuspercyclemodifier;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int disallowoffensivemodifierbonus;
    /**
     * This is a devhax attribute that prevents you from e-warping on logon or logoff
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int doesnotemergencywarp;
    /**
     * Bonus to duration.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int durationbonus;
    /**
     * Stays with characters across clone changes. ONLY FOR IMPLANTS AND BOOSTERS.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int followsjumpclones;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double hydradronerangebonus;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double hydradronetrackingbonus;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double hydramissileexplosionvelocitybonus;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double hydramissileflighttimebonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double implantsetamulet;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(2.0)
    public double implantsetbloodraider;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double implantsetcaldarinavy;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double implantsetfederationnavy;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double implantsetguristas;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double implantsethalo;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double implantsethydra;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double implantsetimperialnavy;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double implantsetlgcaldarinavy;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double implantsetlgfederationnavy;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double implantsetlgimperialnavy;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double implantsetlgrepublicfleet;
    /**
     * Centurion set bonus attribute
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double implantsetmordus;
    /**
     * Harvest set bonus attribute
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double implantsetore;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double implantsetrepublicfleet;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double implantsetsavior;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double implantsetserpentis;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double implantsetserpentis2;
    /**
     * Virtue set bonus attribute
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double implantsetsisters;
    /**
     * Edge set bonus attribute
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double implantsetsyndicate;
    /**
     * Nomad set bonus attribute
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double implantsetthukker;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double implantsetwarpspeed;
    /**
     * Whether an item is an implant or not
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int implantness;
    /**
     * +/- bonus to the intelligence of a character.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int intelligencebonus;
    /**
     * Jump range bonus for jump drive operation.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int jumpdriverangebonus;
    /**
     * Autogenerated skill attribute, maxRangeBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double maxrangebonus;
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
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double remoterepdurationbonus;
    /**
     * Autogenerated skill attribute, repairBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int repairbonus;
    /**
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill2;
    /**
     * Required skill level for skill 2
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill2level;
    /**
     * + / - modifier to a ship gravimetric strength
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int scangravimetricstrengthmodifier;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int scangravimetricstrengthpercent;
    /**
     * + / - modifier to a ship ladar strength
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int scanladarstrengthmodifier;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int scanladarstrengthpercent;
    /**
     * + / - modifier to a ship magnetometric strength
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int scanmagnetometricstrengthmodifier;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int scanmagnetometricstrengthpercent;
    /**
     * + / - modifier to a ship radar strength
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int scanradarstrengthmodifier;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int scanradarstrengthpercent;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int scanstrengthbonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double setbonusmimesis;
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
     * Shield HP Bonus used for nirvana implants
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int shieldhpbonus;
    /**
     * Autogenerated skill attribute, signatureRadiusBonus
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double signatureradiusbonus;
    /**
     * Autogenerated skill attribute, smugglingChanceBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double smugglingchancebonus;
    /**
     * Authoring has been moved to FSD
     * Tech level of an item
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int techlevel;
    /**
     * Scale the tracking speed of a weapon.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double trackingspeedmultiplier;
    /**
     * Autogenerated skill attribute, velocityBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double velocitybonus;
    /**
     * +/- bonus to the willpower of a character.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int willpowerbonus;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {ImplantSetThukker.INSTANCE, ScanGravimetricStrengthPercent.INSTANCE, ImplantSetSisters.INSTANCE, ScanLadarStrengthPercent.INSTANCE, ScanMagnetometricStrengthPercent.INSTANCE, ScanRadarStrengthPercent.INSTANCE, DamageMultiplierBonusMaxModifier.INSTANCE, DamageMultiplierBonusPerCycleModifier.INSTANCE, SetBonusMimesis.INSTANCE, ImplantSetSyndicate.INSTANCE, ImplantSetORE.INSTANCE, ImplantSetWarpSpeed.INSTANCE, ImplantSetMordus.INSTANCE, ImplantSetImperialNavy.INSTANCE, ImplantSetCaldariNavy.INSTANCE, ImplantSetFederationNavy.INSTANCE, ImplantSetRepublicFleet.INSTANCE, RequiredSkill1Level.INSTANCE, RequiredSkill2Level.INSTANCE, AgilityBonus.INSTANCE, DisallowOffensiveModifierBonus.INSTANCE, ArmorRepairBonus.INSTANCE, ScanRadarStrengthModifier.INSTANCE, ScanLadarStrengthModifier.INSTANCE, ImplantSetBloodraider.INSTANCE, ScanGravimetricStrengthModifier.INSTANCE, ScanMagnetometricStrengthModifier.INSTANCE, ImplantSetLGImperialNavy.INSTANCE, ImplantSetSerpentis.INSTANCE, ImplantSetLGFederationNavy.INSTANCE, Radius.INSTANCE, ImplantSetSerpentis2 .INSTANCE, ImplantSetRapture.INSTANCE, ImplantSetLGCaldariNavy.INSTANCE, ShieldBoostMultiplier.INSTANCE, ImplantSetLGRepublicFleet.INSTANCE, TechLevel.INSTANCE, RangeSkillBonus.INSTANCE, RepairBonus.INSTANCE, Capacity.INSTANCE, CpuOutputBonus2 .INSTANCE, SignatureRadiusBonus.INSTANCE, CharismaBonus.INSTANCE, IntelligenceBonus.INSTANCE, MemoryBonus.INSTANCE, PerceptionBonus.INSTANCE, WillpowerBonus.INSTANCE, MaxTargetRangeBonus.INSTANCE, RequiredSkill1 .INSTANCE, RequiredSkill2 .INSTANCE, PowerEngineeringOutputBonus.INSTANCE, CapRechargeBonus.INSTANCE, VelocityBonus.INSTANCE, DoesNotEmergencyWarp.INSTANCE, SmugglingChanceBonus.INSTANCE, DurationBonus.INSTANCE, ImplantSetGuristas.INSTANCE, ShieldHpBonus.INSTANCE, ImplantSetNirvana.INSTANCE, Implantness.INSTANCE, ScanStrengthBonus.INSTANCE, ArmorHpBonus.INSTANCE, ImplantSetSavior.INSTANCE, RemoteRepDurationBonus.INSTANCE, ShieldCapacityBonus.INSTANCE, ImplantSetHydra.INSTANCE, HydraDroneTrackingBonus.INSTANCE, HydraDroneRangeBonus.INSTANCE, HydraMissileFlightTimeBonus.INSTANCE, HydraMissileExplosionVelocityBonus.INSTANCE, ImplantSetHalo.INSTANCE, MaxRangeBonus.INSTANCE, ImplantSetAmulet.INSTANCE, PassiveEmDamageResistanceBonus.INSTANCE, Nondestructible.INSTANCE, PassiveExplosiveDamageResistanceBonus.INSTANCE, PassiveKineticDamageResistanceBonus.INSTANCE, PassiveThermicDamageResistanceBonus.INSTANCE, BoosterAttributeModifier.INSTANCE, JumpDriveRangeBonus.INSTANCE, WarpSBonus.INSTANCE, TrackingSpeedMultiplier.INSTANCE, MetaLevelOld.INSTANCE, FollowsJumpClones.INSTANCE })));
    public static final Cyberimplant.MetaGroup METAGROUP = new Cyberimplant.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  3017 :
            {
                return implantsetnirvana;
            }
            case  3107 :
            {
                return implantsetrapture;
            }
            case  624 :
            {
                return warpsbonus;
            }
            case  151 :
            {
                return agilitybonus;
            }
            case  335 :
            {
                return armorhpbonus;
            }
            case  2457 :
            {
                return armorrepairbonus;
            }
            case  1126 :
            {
                return boosterattributemodifier;
            }
            case  314 :
            {
                return caprechargebonus;
            }
            case  175 :
            {
                return charismabonus;
            }
            case  424 :
            {
                return cpuoutputbonus2;
            }
            case  2823 :
            {
                return damagemultiplierbonusmaxmodifier;
            }
            case  2824 :
            {
                return damagemultiplierbonuspercyclemodifier;
            }
            case  1048 :
            {
                return disallowoffensivemodifierbonus;
            }
            case  1854 :
            {
                return doesnotemergencywarp;
            }
            case  66 :
            {
                return durationbonus;
            }
            case  1916 :
            {
                return followsjumpclones;
            }
            case  3029 :
            {
                return hydradronerangebonus;
            }
            case  3028 :
            {
                return hydradronetrackingbonus;
            }
            case  3031 :
            {
                return hydramissileexplosionvelocitybonus;
            }
            case  3030 :
            {
                return hydramissileflighttimebonus;
            }
            case  864 :
            {
                return implantsetamulet;
            }
            case  799 :
            {
                return implantsetbloodraider;
            }
            case  1552 :
            {
                return implantsetcaldarinavy;
            }
            case  1553 :
            {
                return implantsetfederationnavy;
            }
            case  838 :
            {
                return implantsetguristas;
            }
            case  863 :
            {
                return implantsethalo;
            }
            case  3027 :
            {
                return implantsethydra;
            }
            case  1550 :
            {
                return implantsetimperialnavy;
            }
            case  1571 :
            {
                return implantsetlgcaldarinavy;
            }
            case  1570 :
            {
                return implantsetlgfederationnavy;
            }
            case  1569 :
            {
                return implantsetlgimperialnavy;
            }
            case  1572 :
            {
                return implantsetlgrepublicfleet;
            }
            case  1293 :
            {
                return implantsetmordus;
            }
            case  1292 :
            {
                return implantsetore;
            }
            case  1554 :
            {
                return implantsetrepublicfleet;
            }
            case  3023 :
            {
                return implantsetsavior;
            }
            case  802 :
            {
                return implantsetserpentis;
            }
            case  803 :
            {
                return implantsetserpentis2;
            }
            case  1284 :
            {
                return implantsetsisters;
            }
            case  1291 :
            {
                return implantsetsyndicate;
            }
            case  1282 :
            {
                return implantsetthukker;
            }
            case  1932 :
            {
                return implantsetwarpspeed;
            }
            case  331 :
            {
                return implantness;
            }
            case  176 :
            {
                return intelligencebonus;
            }
            case  870 :
            {
                return jumpdriverangebonus;
            }
            case  351 :
            {
                return maxrangebonus;
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
            case  3024 :
            {
                return remoterepdurationbonus;
            }
            case  806 :
            {
                return repairbonus;
            }
            case  183 :
            {
                return requiredskill2;
            }
            case  278 :
            {
                return requiredskill2level;
            }
            case  1567 :
            {
                return scangravimetricstrengthmodifier;
            }
            case  1027 :
            {
                return scangravimetricstrengthpercent;
            }
            case  1566 :
            {
                return scanladarstrengthmodifier;
            }
            case  1028 :
            {
                return scanladarstrengthpercent;
            }
            case  1568 :
            {
                return scanmagnetometricstrengthmodifier;
            }
            case  1029 :
            {
                return scanmagnetometricstrengthpercent;
            }
            case  1565 :
            {
                return scanradarstrengthmodifier;
            }
            case  1030 :
            {
                return scanradarstrengthpercent;
            }
            case  846 :
            {
                return scanstrengthbonus;
            }
            case  2825 :
            {
                return setbonusmimesis;
            }
            case  548 :
            {
                return shieldboostmultiplier;
            }
            case  337 :
            {
                return shieldcapacitybonus;
            }
            case  3015 :
            {
                return shieldhpbonus;
            }
            case  554 :
            {
                return signatureradiusbonus;
            }
            case  447 :
            {
                return smugglingchancebonus;
            }
            case  422 :
            {
                return techlevel;
            }
            case  244 :
            {
                return trackingspeedmultiplier;
            }
            case  315 :
            {
                return velocitybonus;
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
    public IMetaGroup<Cyberimplant> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Cyberimplant>
    {
        public static final String RESOURCE_PATH = "SDE/types/implant/Cyberimplant.yaml";
        private Map<Integer, Cyberimplant> cache = (null);

        @Override
        public IMetaCategory<? super Cyberimplant> category() {
            return Implant.METACAT;
        }

        @Override
        public int getGroupId() {
            return  300;
        }

        @Override
        public String getName() {
            return "Cyberimplant";
        }

        @Override
        public synchronized Map<Integer, Cyberimplant> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(Cyberimplant.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, Cyberimplant> types;
        }
    }
}
