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
import fr.guiguilechat.jcelechat.model.sde.attributes.AsteroidMetaLevel;
import fr.guiguilechat.jcelechat.model.sde.attributes.AsteroidRadiusGrowthFactor;
import fr.guiguilechat.jcelechat.model.sde.attributes.AsteroidRadiusUnitSize;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.Mass;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.ReprocessingSkillType;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level;
import fr.guiguilechat.jcelechat.model.sde.types.Asteroid;
import org.yaml.snakeyaml.Yaml;

public class Bezdnacine
    extends Asteroid
{
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {Radius.INSTANCE, Mass.INSTANCE, RequiredSkill1Level.INSTANCE, Capacity.INSTANCE, RequiredSkill1 .INSTANCE, ReprocessingSkillType.INSTANCE, AsteroidMetaLevel.INSTANCE, AsteroidRadiusGrowthFactor.INSTANCE, AsteroidRadiusUnitSize.INSTANCE })));
    public static final Bezdnacine.MetaGroup METAGROUP = new Bezdnacine.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<Bezdnacine> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Bezdnacine>
    {
        public static final String RESOURCE_PATH = "SDE/types/asteroid/Bezdnacine.yaml";
        private Map<String, Bezdnacine> cache = (null);

        @Override
        public IMetaCategory<? super Bezdnacine> category() {
            return Asteroid.METACAT;
        }

        @Override
        public int getGroupId() {
            return  4031;
        }

        @Override
        public String getName() {
            return "Bezdnacine";
        }

        @Override
        public synchronized Map<String, Bezdnacine> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(Bezdnacine.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, Bezdnacine> types;
        }
    }
}
