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
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.AsteroidMetaLevel;
import fr.guiguilechat.jcelechat.model.sde.attributes.AsteroidRadiusGrowthFactor;
import fr.guiguilechat.jcelechat.model.sde.attributes.AsteroidRadiusUnitSize;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.IgnoreMiningWaste;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.ReprocessingSkillType;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level;
import fr.guiguilechat.jcelechat.model.sde.types.Asteroid;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class Rakovene
    extends Asteroid
{
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
     * The skill required to reprocess this ore type.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int reprocessingskilltype;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {Radius.INSTANCE, IgnoreMiningWaste.INSTANCE, RequiredSkill1Level.INSTANCE, ReprocessingSkillType.INSTANCE, RequiredSkill1 .INSTANCE, Capacity.INSTANCE, AsteroidMetaLevel.INSTANCE, AsteroidRadiusGrowthFactor.INSTANCE, AsteroidRadiusUnitSize.INSTANCE })));
    public static final Rakovene.MetaGroup METAGROUP = new Rakovene.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  1981 :
            {
                return asteroidradiusunitsize;
            }
            case  3236 :
            {
                return ignoreminingwaste;
            }
            case  790 :
            {
                return reprocessingskilltype;
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
    public IMetaGroup<Rakovene> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Rakovene>
    {
        public static final String RESOURCE_PATH = "SDE/types/asteroid/Rakovene.yaml";
        private Map<Integer, Rakovene> cache = (null);

        @Override
        public IMetaCategory<? super Rakovene> category() {
            return Asteroid.METACAT;
        }

        @Override
        public int getGroupId() {
            return  4030;
        }

        @Override
        public String getName() {
            return "Rakovene";
        }

        @Override
        public synchronized Map<Integer, Rakovene> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(Rakovene.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, Rakovene> types;
        }
    }
}
