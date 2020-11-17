package fr.guiguilechat.jcelechat.model.sde.types.asteroid;

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
import fr.guiguilechat.jcelechat.model.sde.attributes.AsteroidMaxRadius;
import fr.guiguilechat.jcelechat.model.sde.attributes.AsteroidMetaLevel;
import fr.guiguilechat.jcelechat.model.sde.attributes.AsteroidRadiusGrowthFactor;
import fr.guiguilechat.jcelechat.model.sde.attributes.AsteroidRadiusUnitSize;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.CompressionQuantityNeeded;
import fr.guiguilechat.jcelechat.model.sde.attributes.CompressionTypeID;
import fr.guiguilechat.jcelechat.model.sde.attributes.Mass;
import fr.guiguilechat.jcelechat.model.sde.attributes.OreBasicType;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.ReprocessingSkillType;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.StasisWebifierResistance;
import fr.guiguilechat.jcelechat.model.sde.types.Asteroid;
import org.yaml.snakeyaml.Yaml;

public class Jaspet
    extends Asteroid
{
    /**
     * max visual size for asteroids to fit moon chunk
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(16255)
    public int asteroidmaxradius;
    /**
     * Number of items needed to be able to compress it
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int compressionquantityneeded;
    /**
     * What type this type can be compressed into
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int compressiontypeid;
    /**
     * Reference for grouping ores in visual displays. All variants of one ore should have the same BasicType ID
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int orebasictype;
    /**
     * Resistance against Stasis Webifiers
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double stasiswebifierresistance;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {Radius.INSTANCE, StasisWebifierResistance.INSTANCE, Mass.INSTANCE, Capacity.INSTANCE, AsteroidMaxRadius.INSTANCE, AsteroidMetaLevel.INSTANCE, CompressionTypeID.INSTANCE, CompressionQuantityNeeded.INSTANCE, RequiredSkill1Level.INSTANCE, RequiredSkill1 .INSTANCE, ReprocessingSkillType.INSTANCE, OreBasicType.INSTANCE, AsteroidRadiusGrowthFactor.INSTANCE, AsteroidRadiusUnitSize.INSTANCE })));
    public static final Jaspet.MetaGroup METAGROUP = new Jaspet.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  2727 :
            {
                return asteroidmaxradius;
            }
            case  1941 :
            {
                return compressionquantityneeded;
            }
            case  1940 :
            {
                return compressiontypeid;
            }
            case  2711 :
            {
                return orebasictype;
            }
            case  2115 :
            {
                return stasiswebifierresistance;
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
    public IMetaGroup<Jaspet> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Jaspet>
    {
        public static final String RESOURCE_PATH = "SDE/types/asteroid/Jaspet.yaml";
        private Map<String, Jaspet> cache = (null);

        @Override
        public IMetaCategory<? super Jaspet> category() {
            return Asteroid.METACAT;
        }

        @Override
        public int getGroupId() {
            return  456;
        }

        @Override
        public String getName() {
            return "Jaspet";
        }

        @Override
        public synchronized Map<String, Jaspet> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(Jaspet.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, Jaspet> types;
        }
    }
}
