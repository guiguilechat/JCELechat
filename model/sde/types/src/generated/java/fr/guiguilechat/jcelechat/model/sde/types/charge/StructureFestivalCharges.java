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
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.DetonationRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.ExplosionDelay;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hp;
import fr.guiguilechat.jcelechat.model.sde.attributes.LauncherGroup;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxVelocity;
import fr.guiguilechat.jcelechat.model.sde.attributes.MissileNeverDoesDamage;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.SpeedMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.StructureUniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.TechLevel;
import fr.guiguilechat.jcelechat.model.sde.types.Charge;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class StructureFestivalCharges
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
     * the range in meters for an object to trigger detonation of missile. (own ship excluded)
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int detonationrange;
    /**
     * The amount of milliseconds before the object explodes.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int explosiondelay;
    /**
     * The maximum hitpoints of an object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double hp;
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
     * If present on a type which is used like a missile, signifies that it should never do damage (whether it has any to do or not).
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int missileneverdoesdamage;
    /**
     * Typically scales the firing speed of a weapon.  Reducing speed means faster, strangely..
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double speedmultiplier;
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
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {Radius.INSTANCE, AimedLaunch.INSTANCE, MaxVelocity.INSTANCE, Agility.INSTANCE, TechLevel.INSTANCE, Capacity.INSTANCE, Hp.INSTANCE, LauncherGroup.INSTANCE, DetonationRange.INSTANCE, SpeedMultiplier.INSTANCE, StructureUniformity.INSTANCE, MissileNeverDoesDamage.INSTANCE, ExplosionDelay.INSTANCE, ArmorPiercingChance.INSTANCE })));
    public static final StructureFestivalCharges.MetaGroup METAGROUP = new StructureFestivalCharges.MetaGroup();

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
            case  108 :
            {
                return detonationrange;
            }
            case  281 :
            {
                return explosiondelay;
            }
            case  9 :
            {
                return hp;
            }
            case  137 :
            {
                return launchergroup;
            }
            case  37 :
            {
                return maxvelocity;
            }
            case  1075 :
            {
                return missileneverdoesdamage;
            }
            case  204 :
            {
                return speedmultiplier;
            }
            case  525 :
            {
                return structureuniformity;
            }
            case  422 :
            {
                return techlevel;
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
    public IMetaGroup<StructureFestivalCharges> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<StructureFestivalCharges>
    {
        public static final String RESOURCE_PATH = "SDE/types/charge/StructureFestivalCharges.yaml";
        private Map<Integer, StructureFestivalCharges> cache = (null);

        @Override
        public IMetaCategory<? super StructureFestivalCharges> category() {
            return Charge.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1976;
        }

        @Override
        public String getName() {
            return "StructureFestivalCharges";
        }

        @Override
        public synchronized Map<Integer, StructureFestivalCharges> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(StructureFestivalCharges.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, StructureFestivalCharges> types;
        }
    }
}
