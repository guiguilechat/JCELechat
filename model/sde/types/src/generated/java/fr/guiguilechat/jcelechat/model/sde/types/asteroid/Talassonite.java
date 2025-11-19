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
import fr.guiguilechat.jcelechat.model.sde.attributes.ReprocessingSkillType;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level;
import fr.guiguilechat.jcelechat.model.sde.types.Asteroid;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class Talassonite
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
     * The skill required to reprocess this ore type.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int reprocessingskilltype;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {RequiredSkill1Level.INSTANCE, ReprocessingSkillType.INSTANCE, RequiredSkill1 .INSTANCE, AsteroidMetaLevel.INSTANCE, AsteroidRadiusGrowthFactor.INSTANCE, AsteroidRadiusUnitSize.INSTANCE })));
    public static final Talassonite.MetaGroup METAGROUP = new Talassonite.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  1981 :
            {
                return asteroidradiusunitsize;
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
    public IMetaGroup<Talassonite> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Talassonite>
    {
        public static final String RESOURCE_PATH = "SDE/types/asteroid/Talassonite.yaml";
        private Map<Integer, Talassonite> cache = (null);

        @Override
        public IMetaCategory<? super Talassonite> category() {
            return Asteroid.METACAT;
        }

        @Override
        public int getGroupId() {
            return  4029;
        }

        @Override
        public String getName() {
            return "Talassonite";
        }

        @Override
        public synchronized Map<Integer, Talassonite> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(Talassonite.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, Talassonite> types;
        }
    }
}
