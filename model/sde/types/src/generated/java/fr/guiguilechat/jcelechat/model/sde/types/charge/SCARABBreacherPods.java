package fr.guiguilechat.jcelechat.model.sde.types.charge;

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
import fr.guiguilechat.jcelechat.model.sde.attributes.AimedLaunch;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorPiercingChance;
import fr.guiguilechat.jcelechat.model.sde.attributes.BaseArmorDamage;
import fr.guiguilechat.jcelechat.model.sde.attributes.BaseShieldDamage;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.ChargeSize;
import fr.guiguilechat.jcelechat.model.sde.attributes.DetonationRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.DotDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.DotMaxDamagePerTick;
import fr.guiguilechat.jcelechat.model.sde.attributes.DotMaxHPPercentagePerTick;
import fr.guiguilechat.jcelechat.model.sde.attributes.EmDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ExplosionDelay;
import fr.guiguilechat.jcelechat.model.sde.attributes.ExplosiveDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hp;
import fr.guiguilechat.jcelechat.model.sde.attributes.KineticDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.LauncherGroup;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxVelocity;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaLevelOld;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.StructureUniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.TechLevel;
import fr.guiguilechat.jcelechat.model.sde.attributes.ThermalDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.types.Charge;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class SCARABBreacherPods
    extends Charge
{
    /**
     * The agility of the object.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double agility;
    /**
     * Determines wether a missile launches aligned with the ship (0) or directly at the target (1).
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int aimedlaunch;
    /**
     * Chance of piercing the armor.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int armorpiercingchance;
    /**
     * Just for the UI to display base damage on armor.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int basearmordamage;
    /**
     * Just for the UI to display base damage on shield.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int baseshielddamage;
    /**
     * The size of the charges that can fit in the turret/whatever.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int chargesize;
    /**
     * the range in meters for an object to trigger detonation of missile. (own ship excluded)
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int detonationrange;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int dotduration;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int dotmaxdamagepertick;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double dotmaxhppercentagepertick;
    /**
     * Electro magnetic damage multiplier for shield and armor. Represented as "% Resistance" in the UI.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double emdamageresonance;
    /**
     * The amount of milliseconds before the object explodes.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int explosiondelay;
    /**
     * damage multiplier vs. explosive damagers.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double explosivedamageresonance;
    /**
     * The maximum hitpoints of an object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double hp;
    /**
     * damage multiplier vs. kinetic damagers.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double kineticdamageresonance;
    /**
     * One of the groups of launcher this charge can be loaded into.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int launchergroup;
    /**
     * Maximum velocity of ship
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double maxvelocity;
    /**
     * Authoring has been moved to FSD
     * The ranking of the module within its tech level
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int metalevelold;
    /**
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill1;
    /**
     * Required skill level for skill 1
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill1level;
    /**
     * DO NOT MESS WITH
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double structureuniformity;
    /**
     * Authoring has been moved to FSD
     * Tech level of an item
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int techlevel;
    /**
     * damage multiplier vs. thermal.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double thermaldamageresonance;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {ChargeSize.INSTANCE, AimedLaunch.INSTANCE, Agility.INSTANCE, Hp.INSTANCE, LauncherGroup.INSTANCE, StructureUniformity.INSTANCE, RequiredSkill1Level.INSTANCE, ExplosionDelay.INSTANCE, Radius.INSTANCE, BaseShieldDamage.INSTANCE, MaxVelocity.INSTANCE, BaseArmorDamage.INSTANCE, TechLevel.INSTANCE, Capacity.INSTANCE, DotDuration.INSTANCE, DotMaxDamagePerTick.INSTANCE, DotMaxHPPercentagePerTick.INSTANCE, DetonationRange.INSTANCE, KineticDamageResonance.INSTANCE, ThermalDamageResonance.INSTANCE, ExplosiveDamageResonance.INSTANCE, EmDamageResonance.INSTANCE, RequiredSkill1 .INSTANCE, MetaLevelOld.INSTANCE, ArmorPiercingChance.INSTANCE })));
    public static final SCARABBreacherPods.MetaGroup METAGROUP = new SCARABBreacherPods.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  70 :
            {
                return agility;
            }
            case  644 :
            {
                return aimedlaunch;
            }
            case  122 :
            {
                return armorpiercingchance;
            }
            case  613 :
            {
                return basearmordamage;
            }
            case  612 :
            {
                return baseshielddamage;
            }
            case  128 :
            {
                return chargesize;
            }
            case  108 :
            {
                return detonationrange;
            }
            case  5735 :
            {
                return dotduration;
            }
            case  5736 :
            {
                return dotmaxdamagepertick;
            }
            case  5737 :
            {
                return dotmaxhppercentagepertick;
            }
            case  113 :
            {
                return emdamageresonance;
            }
            case  281 :
            {
                return explosiondelay;
            }
            case  111 :
            {
                return explosivedamageresonance;
            }
            case  9 :
            {
                return hp;
            }
            case  109 :
            {
                return kineticdamageresonance;
            }
            case  137 :
            {
                return launchergroup;
            }
            case  37 :
            {
                return maxvelocity;
            }
            case  633 :
            {
                return metalevelold;
            }
            case  182 :
            {
                return requiredskill1;
            }
            case  277 :
            {
                return requiredskill1level;
            }
            case  525 :
            {
                return structureuniformity;
            }
            case  422 :
            {
                return techlevel;
            }
            case  110 :
            {
                return thermaldamageresonance;
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
    public IMetaGroup<SCARABBreacherPods> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<SCARABBreacherPods>
    {
        public static final String RESOURCE_PATH = "SDE/types/charge/SCARABBreacherPods.yaml";
        private Map<Integer, SCARABBreacherPods> cache = (null);

        @Override
        public IMetaCategory<? super SCARABBreacherPods> category() {
            return Charge.METACAT;
        }

        @Override
        public int getGroupId() {
            return  4808;
        }

        @Override
        public String getName() {
            return "SCARABBreacherPods";
        }

        @Override
        public synchronized Map<Integer, SCARABBreacherPods> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(SCARABBreacherPods.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, SCARABBreacherPods> types;
        }
    }
}
