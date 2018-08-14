package fr.guiguilechat.jcelechat.model.sde.items.types.charge;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.Charge;
import org.yaml.snakeyaml.Yaml;

public class AdvancedTorpedo
    extends Charge
{
    /**
     * The agility of the object.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double Agility;
    /**
     * Determines wether a missile launches aligned with the ship (0) or directly at the target (1).
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int AimedLaunch;
    /**
     * Size of the damage cloud caused by impact.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int AoeCloudSize;
    /**
     * Missile Damage Modifier. Smaller is better (Don't use less than 0.5)
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double AoeDamageReductionFactor;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int AoeFalloff;
    /**
     * Velocity of the damage cloud created on impact.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double AoeVelocity;
    /**
     * Just for the UI to display base damage on armor.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int BaseArmorDamage;
    /**
     * Just for the UI to display base damage on shield.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int BaseShieldDamage;
    /**
     * the range in meters for an object to trigger detonation of missile. (own ship excluded)
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DetonationRange;
    /**
     * EM damage done.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double EmDamage;
    /**
     * Electro magnetic damage multiplier for shield and armor. Represented as "% Resistance" in the UI.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double EmDamageResonance;
    /**
     * The amount of milliseconds before the object explodes.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int ExplosionDelay;
    /**
     * Explosive damage done.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double ExplosiveDamage;
    /**
     * damage multiplier vs. explosive damagers.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double ExplosiveDamageResonance;
    /**
     * The maximum hitpoints of an object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Hp;
    /**
     * Kinetic damage done.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double KineticDamage;
    /**
     * damage multiplier vs. kinetic damagers.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double KineticDamageResonance;
    /**
     * One of the groups of launcher this charge can be loaded into.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int LauncherGroup;
    /**
     * Maximum velocity of ship
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double MaxVelocity;
    /**
     * Authoring has been moved to FSD
     * The ranking of the module within its tech level
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MetaLevel;
    /**
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1;
    /**
     * Required skill level for skill 1
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1Level;
    /**
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill2;
    /**
     * Required skill level for skill 2
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill2Level;
    /**
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill3;
    /**
     * Required skill level for skill 3
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill3Level;
    /**
     * DO NOT MESS WITH
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double StructureUniformity;
    /**
     * Authoring has been moved to FSD
     * Tech level of an item
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int TechLevel;
    /**
     * Thermal damage done.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double ThermalDamage;
    /**
     * damage multiplier vs. thermal.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double ThermalDamageResonance;
    public final static AdvancedTorpedo.MetaGroup METAGROUP = new AdvancedTorpedo.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  70 :
            {
                return Agility;
            }
            case  644 :
            {
                return AimedLaunch;
            }
            case  654 :
            {
                return AoeCloudSize;
            }
            case  1353 :
            {
                return AoeDamageReductionFactor;
            }
            case  655 :
            {
                return AoeFalloff;
            }
            case  653 :
            {
                return AoeVelocity;
            }
            case  613 :
            {
                return BaseArmorDamage;
            }
            case  612 :
            {
                return BaseShieldDamage;
            }
            case  108 :
            {
                return DetonationRange;
            }
            case  114 :
            {
                return EmDamage;
            }
            case  113 :
            {
                return EmDamageResonance;
            }
            case  281 :
            {
                return ExplosionDelay;
            }
            case  116 :
            {
                return ExplosiveDamage;
            }
            case  111 :
            {
                return ExplosiveDamageResonance;
            }
            case  9 :
            {
                return Hp;
            }
            case  117 :
            {
                return KineticDamage;
            }
            case  109 :
            {
                return KineticDamageResonance;
            }
            case  137 :
            {
                return LauncherGroup;
            }
            case  37 :
            {
                return MaxVelocity;
            }
            case  633 :
            {
                return MetaLevel;
            }
            case  182 :
            {
                return RequiredSkill1;
            }
            case  277 :
            {
                return RequiredSkill1Level;
            }
            case  183 :
            {
                return RequiredSkill2;
            }
            case  278 :
            {
                return RequiredSkill2Level;
            }
            case  184 :
            {
                return RequiredSkill3;
            }
            case  279 :
            {
                return RequiredSkill3Level;
            }
            case  525 :
            {
                return StructureUniformity;
            }
            case  422 :
            {
                return TechLevel;
            }
            case  118 :
            {
                return ThermalDamage;
            }
            case  110 :
            {
                return ThermalDamageResonance;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public IMetaGroup<AdvancedTorpedo> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AdvancedTorpedo>
    {
        public final static String RESOURCE_PATH = "SDE/items/charge/AdvancedTorpedo.yaml";
        private Map<String, AdvancedTorpedo> cache = (null);

        @Override
        public IMetaCategory<? super AdvancedTorpedo> category() {
            return Charge.METACAT;
        }

        @Override
        public int getGroupId() {
            return  657;
        }

        @Override
        public String getName() {
            return "AdvancedTorpedo";
        }

        @Override
        public synchronized Map<String, AdvancedTorpedo> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AdvancedTorpedo.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AdvancedTorpedo> items;
        }
    }
}
