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
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.AoeCloudSizeBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.AoeVelocityBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorDamageAmountBonus;
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
import fr.guiguilechat.jcelechat.model.sde.attributes.CapacitorCapacityBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.CharismaBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.DamageMultiplierBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.FalloffBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.FollowsJumpClones;
import fr.guiguilechat.jcelechat.model.sde.attributes.IntelligenceBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.Mass;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxFlightTimeBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.MemoryBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.NonDiminishingSkillInjectorUses;
import fr.guiguilechat.jcelechat.model.sde.attributes.Nondestructible;
import fr.guiguilechat.jcelechat.model.sde.attributes.PerceptionBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.RangeSkillBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.RofBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanResolutionBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldBoostMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.SignatureRadiusBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.SocialBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.SocialMutator;
import fr.guiguilechat.jcelechat.model.sde.attributes.TrackingSpeedBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.VelocityBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.WillpowerBonus;
import fr.guiguilechat.jcelechat.model.sde.types.Implant;
import org.yaml.snakeyaml.Yaml;

public class Booster
    extends Implant
{
    /**
     * Attribute defining usage count for penaltyless skill injections
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int nondiminishingskillinjectoruses;
    /**
     * Booster attribute to explosion radius of missiles vs. signature radius.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double aoecloudsizebonus;
    /**
     * Increases velocity of missile explosion
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double aoevelocitybonus;
    /**
     * Bonus attribute for armor repair amount.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int armordamageamountbonus;
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
    @DefaultDoubleValue(0.0)
    public double boosterduration;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double boostereffectchance1;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double boostereffectchance2;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double boostereffectchance3;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double boostereffectchance4;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double boostereffectchance5;
    /**
     * The last allowed injection date.  After this date the booster can no longer be consumed. Formatted YYYY.MM.DD HH:MM:SS
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
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
     * Autogenerated skill attribute, CapacitorCapacityBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double capacitorcapacitybonus;
    /**
     * +/- bonus to the charisma of a character.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int charismabonus;
    /**
     * Autogenerated skill attribute, damageMultiplierBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int damagemultiplierbonus;
    /**
     * Autogenerated skill attribute, falloffBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double falloffbonus;
    /**
     * Stays with characters across clone changes. ONLY FOR IMPLANTS AND BOOSTERS.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int followsjumpclones;
    /**
     * +/- bonus to the intelligence of a character.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int intelligencebonus;
    /**
     * Autogenerated skill attribute, max flightTimeBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxflighttimebonus;
    /**
     * +/- bonus to the memory of a character.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int memorybonus;
    /**
     * This will make the item non-destructible upon podding.  ONLY FOR IMPLANTS AND BOOSTERS
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int nondestructible;
    /**
     * +/- bonus to the perception of a character.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int perceptionbonus;
    /**
     * Autogenerated skill attribute, rangeSkillBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rangeskillbonus;
    /**
     * Autogenerated skill attribute, rofBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rofbonus;
    /**
     * Bonus for scan resolution
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double scanresolutionbonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double shieldboostmultiplier;
    /**
     * Autogenerated skill attribute, signatureRadiusBonus
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double signatureradiusbonus;
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
     * Tracking Speed Bonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double trackingspeedbonus;
    /**
     * Autogenerated skill attribute, velocityBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double velocitybonus;
    /**
     * +/- bonus to the willpower of a character.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int willpowerbonus;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {Mass.INSTANCE, RequiredSkill1Level.INSTANCE, Radius.INSTANCE, ShieldBoostMultiplier.INSTANCE, DamageMultiplierBonus.INSTANCE, RofBonus.INSTANCE, RangeSkillBonus.INSTANCE, Capacity.INSTANCE, SignatureRadiusBonus.INSTANCE, MaxFlightTimeBonus.INSTANCE, CharismaBonus.INSTANCE, IntelligenceBonus.INSTANCE, MemoryBonus.INSTANCE, PerceptionBonus.INSTANCE, WillpowerBonus.INSTANCE, SocialMutator.INSTANCE, ScanResolutionBonus.INSTANCE, RequiredSkill1 .INSTANCE, CapacitorCapacityBonus.INSTANCE, VelocityBonus.INSTANCE, Boosterness.INSTANCE, BoosterEffectChance1 .INSTANCE, BoosterEffectChance2 .INSTANCE, BoosterEffectChance3 .INSTANCE, BoosterEffectChance4 .INSTANCE, BoosterEffectChance5 .INSTANCE, BoosterDuration.INSTANCE, AoeVelocityBonus.INSTANCE, AoeCloudSizeBonus.INSTANCE, FalloffBonus.INSTANCE, Nondestructible.INSTANCE, BoosterShieldBoostAmountPenalty.INSTANCE, SocialBonus.INSTANCE, BoosterMaxCharAgeHours.INSTANCE, BoosterArmorHPPenalty.INSTANCE, BoosterLastInjectionDatetime.INSTANCE, BoosterArmorRepairAmountPenalty.INSTANCE, NonDiminishingSkillInjectorUses.INSTANCE, BoosterShieldCapacityPenalty.INSTANCE, BoosterTurretOptimalRangePenalty.INSTANCE, BoosterTurretTrackingPenalty.INSTANCE, BoosterTurretFalloffPenalty.INSTANCE, BoosterAOEVelocityPenalty.INSTANCE, FollowsJumpClones.INSTANCE, BoosterMissileVelocityPenalty.INSTANCE, BoosterMissileAOECloudPenalty.INSTANCE, BoosterCapacitorCapacityPenalty.INSTANCE, TrackingSpeedBonus.INSTANCE, ArmorDamageAmountBonus.INSTANCE, BoosterMaxVelocityPenalty.INSTANCE })));
    public static final Booster.MetaGroup METAGROUP = new Booster.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  2806 :
            {
                return nondiminishingskillinjectoruses;
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
            case  1079 :
            {
                return capacitorcapacitybonus;
            }
            case  175 :
            {
                return charismabonus;
            }
            case  292 :
            {
                return damagemultiplierbonus;
            }
            case  349 :
            {
                return falloffbonus;
            }
            case  1916 :
            {
                return followsjumpclones;
            }
            case  176 :
            {
                return intelligencebonus;
            }
            case  557 :
            {
                return maxflighttimebonus;
            }
            case  177 :
            {
                return memorybonus;
            }
            case  1890 :
            {
                return nondestructible;
            }
            case  178 :
            {
                return perceptionbonus;
            }
            case  294 :
            {
                return rangeskillbonus;
            }
            case  293 :
            {
                return rofbonus;
            }
            case  566 :
            {
                return scanresolutionbonus;
            }
            case  548 :
            {
                return shieldboostmultiplier;
            }
            case  554 :
            {
                return signatureradiusbonus;
            }
            case  362 :
            {
                return socialbonus;
            }
            case  438 :
            {
                return socialmutator;
            }
            case  767 :
            {
                return trackingspeedbonus;
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
