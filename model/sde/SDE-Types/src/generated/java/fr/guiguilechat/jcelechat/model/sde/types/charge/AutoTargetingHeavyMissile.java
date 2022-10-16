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
import fr.guiguilechat.jcelechat.model.sde.attributes.AoeCloudSize;
import fr.guiguilechat.jcelechat.model.sde.attributes.AoeDamageReductionFactor;
import fr.guiguilechat.jcelechat.model.sde.attributes.AoeFalloff;
import fr.guiguilechat.jcelechat.model.sde.attributes.AoeVelocity;
import fr.guiguilechat.jcelechat.model.sde.attributes.BaseArmorDamage;
import fr.guiguilechat.jcelechat.model.sde.attributes.BaseShieldDamage;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.DetonationRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.EmDamage;
import fr.guiguilechat.jcelechat.model.sde.attributes.ExplosionDelay;
import fr.guiguilechat.jcelechat.model.sde.attributes.ExplosiveDamage;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hp;
import fr.guiguilechat.jcelechat.model.sde.attributes.KineticDamage;
import fr.guiguilechat.jcelechat.model.sde.attributes.LauncherGroup;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxFOFTargetRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxVelocity;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaGroupID;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill2;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill2Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill3;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill3Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.StructureUniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.TechLevel;
import fr.guiguilechat.jcelechat.model.sde.attributes.ThermalDamage;
import fr.guiguilechat.jcelechat.model.sde.types.Charge;
import org.yaml.snakeyaml.Yaml;

public class AutoTargetingHeavyMissile
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
     * Size of the damage cloud caused by impact.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int aoecloudsize;
    /**
     * Missile Damage Modifier. Smaller is better (Don't use less than 0.5)
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double aoedamagereductionfactor;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int aoefalloff;
    /**
     * Velocity of the damage cloud created on impact.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int aoevelocity;
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
     * the range in meters for an object to trigger detonation of missile. (own ship excluded)
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int detonationrange;
    /**
     * EM damage done.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double emdamage;
    /**
     * The amount of milliseconds before the object explodes.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int explosiondelay;
    /**
     * Explosive damage done.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double explosivedamage;
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
     * One of the groups of launcher this charge can be loaded into.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int launchergroup;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(200000)
    public int maxfoftargetrange;
    /**
     * Maximum velocity of ship
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double maxvelocity;
    /**
     * Authoring has been moved to FSD.
     * meta group of type
     * 
     *  3: Story-line (Cosmos)
     *  4: Faction
     *  5: Officer (rare asteroid NPCs)
     *  6: Deadspace
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int metagroupid;
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
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill3;
    /**
     * Required skill level for skill 3
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill3level;
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
     * Thermal damage done.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double thermaldamage;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {AimedLaunch.INSTANCE, Agility.INSTANCE, Hp.INSTANCE, LauncherGroup.INSTANCE, AoeDamageReductionFactor.INSTANCE, MaxFOFTargetRange.INSTANCE, AoeVelocity.INSTANCE, StructureUniformity.INSTANCE, AoeCloudSize.INSTANCE, AoeFalloff.INSTANCE, RequiredSkill1Level.INSTANCE, RequiredSkill2Level.INSTANCE, RequiredSkill3Level.INSTANCE, ExplosionDelay.INSTANCE, MetaGroupID.INSTANCE, Radius.INSTANCE, BaseShieldDamage.INSTANCE, MaxVelocity.INSTANCE, BaseArmorDamage.INSTANCE, TechLevel.INSTANCE, Capacity.INSTANCE, DetonationRange.INSTANCE, EmDamage.INSTANCE, ExplosiveDamage.INSTANCE, KineticDamage.INSTANCE, RequiredSkill1 .INSTANCE, ThermalDamage.INSTANCE, RequiredSkill2 .INSTANCE, RequiredSkill3 .INSTANCE })));
    public static final AutoTargetingHeavyMissile.MetaGroup METAGROUP = new AutoTargetingHeavyMissile.MetaGroup();

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
            case  654 :
            {
                return aoecloudsize;
            }
            case  1353 :
            {
                return aoedamagereductionfactor;
            }
            case  655 :
            {
                return aoefalloff;
            }
            case  653 :
            {
                return aoevelocity;
            }
            case  613 :
            {
                return basearmordamage;
            }
            case  612 :
            {
                return baseshielddamage;
            }
            case  108 :
            {
                return detonationrange;
            }
            case  114 :
            {
                return emdamage;
            }
            case  281 :
            {
                return explosiondelay;
            }
            case  116 :
            {
                return explosivedamage;
            }
            case  9 :
            {
                return hp;
            }
            case  117 :
            {
                return kineticdamage;
            }
            case  137 :
            {
                return launchergroup;
            }
            case  2700 :
            {
                return maxfoftargetrange;
            }
            case  37 :
            {
                return maxvelocity;
            }
            case  1692 :
            {
                return metagroupid;
            }
            case  182 :
            {
                return requiredskill1;
            }
            case  277 :
            {
                return requiredskill1level;
            }
            case  183 :
            {
                return requiredskill2;
            }
            case  278 :
            {
                return requiredskill2level;
            }
            case  184 :
            {
                return requiredskill3;
            }
            case  279 :
            {
                return requiredskill3level;
            }
            case  525 :
            {
                return structureuniformity;
            }
            case  422 :
            {
                return techlevel;
            }
            case  118 :
            {
                return thermaldamage;
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
    public IMetaGroup<AutoTargetingHeavyMissile> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AutoTargetingHeavyMissile>
    {
        public static final String RESOURCE_PATH = "SDE/types/charge/AutoTargetingHeavyMissile.yaml";
        private Map<String, AutoTargetingHeavyMissile> cache = (null);

        @Override
        public IMetaCategory<? super AutoTargetingHeavyMissile> category() {
            return Charge.METACAT;
        }

        @Override
        public int getGroupId() {
            return  395;
        }

        @Override
        public String getName() {
            return "AutoTargetingHeavyMissile";
        }

        @Override
        public synchronized Map<String, AutoTargetingHeavyMissile> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(AutoTargetingHeavyMissile.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AutoTargetingHeavyMissile> types;
        }
    }
}
