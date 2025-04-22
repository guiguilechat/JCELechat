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
import fr.guiguilechat.jcelechat.model.sde.attributes.OreBasicType;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.ReprocessingSkillType;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.StasisWebifierResistance;
import fr.guiguilechat.jcelechat.model.sde.types.Asteroid;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class Hemorphite
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
     * Sets the radius of the asteroid ball when it has a quantity of 1 unit
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(90)
    public int asteroidradiusunitsize;
    /**
     * Reference for grouping ores in visual displays. All variants of one ore should have the same BasicType ID
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int orebasictype;
    /**
     * The skill required to reprocess this ore type.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int reprocessingskilltype;
    /**
     * Resistance against Stasis Webifiers
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double stasiswebifierresistance;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {Radius.INSTANCE, StasisWebifierResistance.INSTANCE, RequiredSkill1Level.INSTANCE, ReprocessingSkillType.INSTANCE, RequiredSkill1 .INSTANCE, Capacity.INSTANCE, OreBasicType.INSTANCE, AsteroidMaxRadius.INSTANCE, AsteroidMetaLevel.INSTANCE, AsteroidRadiusGrowthFactor.INSTANCE, AsteroidRadiusUnitSize.INSTANCE })));
    public static final Hemorphite.MetaGroup METAGROUP = new Hemorphite.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  2727 :
            {
                return asteroidmaxradius;
            }
            case  1981 :
            {
                return asteroidradiusunitsize;
            }
            case  2711 :
            {
                return orebasictype;
            }
            case  790 :
            {
                return reprocessingskilltype;
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
    public IMetaGroup<Hemorphite> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Hemorphite>
    {
        public static final String RESOURCE_PATH = "SDE/types/asteroid/Hemorphite.yaml";
        private Map<Integer, Hemorphite> cache = (null);

        @Override
        public IMetaCategory<? super Hemorphite> category() {
            return Asteroid.METACAT;
        }

        @Override
        public int getGroupId() {
            return  455;
        }

        @Override
        public String getName() {
            return "Hemorphite";
        }

        @Override
        public synchronized Map<Integer, Hemorphite> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(Hemorphite.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, Hemorphite> types;
        }
    }
}
