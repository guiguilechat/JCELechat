package fr.guiguilechat.jcelechat.model.sde.types.asteroid;

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
import fr.guiguilechat.jcelechat.model.sde.types.Asteroid;

public class DarkOchre
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
     * If set to true, this results in no mining waste.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int ignoreminingwaste;
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
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {StasisWebifierResistance.INSTANCE, IgnoreMiningWaste.INSTANCE, RequiredSkill1Level.INSTANCE, ReprocessingSkillType.INSTANCE, RequiredSkill1 .INSTANCE, OreBasicType.INSTANCE, AsteroidMaxRadius.INSTANCE, AsteroidMetaLevel.INSTANCE, AsteroidRadiusGrowthFactor.INSTANCE, AsteroidRadiusUnitSize.INSTANCE })));
    public static final DarkOchre.MetaGroup METAGROUP = new DarkOchre.MetaGroup();

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
            case  3236 :
            {
                return ignoreminingwaste;
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
    public IMetaGroup<DarkOchre> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<DarkOchre>
    {
        public static final String RESOURCE_PATH = "SDE/types/asteroid/DarkOchre.yaml";
        private Map<Integer, DarkOchre> cache = (null);

        @Override
        public IMetaCategory<? super DarkOchre> category() {
            return Asteroid.METACAT;
        }

        @Override
        public int getGroupId() {
            return  453;
        }

        @Override
        public String getName() {
            return "DarkOchre";
        }

        @Override
        public synchronized Map<Integer, DarkOchre> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(DarkOchre.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, DarkOchre> types;
        }
    }
}
