package fr.guiguilechat.jcelechat.model.sde.types.planetaryindustry;

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
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.CpuLoad;
import fr.guiguilechat.jcelechat.model.sde.attributes.PlanetRestriction;
import fr.guiguilechat.jcelechat.model.sde.attributes.PowerLoad;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.types.PlanetaryIndustry;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class Processors
    extends PlanetaryIndustry
{
    /**
     * CPU load of ship
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int cpuload;
    /**
     * This type can only be found/used/created on a planet matching this type ID.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int planetrestriction;
    /**
     * Current load of power core
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int powerload;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {PlanetRestriction.INSTANCE, CpuLoad.INSTANCE, Radius.INSTANCE, Capacity.INSTANCE, PowerLoad.INSTANCE })));
    public static final Processors.MetaGroup METAGROUP = new Processors.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  49 :
            {
                return cpuload;
            }
            case  1632 :
            {
                return planetrestriction;
            }
            case  15 :
            {
                return powerload;
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
    public IMetaGroup<Processors> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Processors>
    {
        public static final String RESOURCE_PATH = "SDE/types/planetaryindustry/Processors.yaml";
        private Map<Integer, Processors> cache = (null);

        @Override
        public IMetaCategory<? super Processors> category() {
            return PlanetaryIndustry.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1028;
        }

        @Override
        public String getName() {
            return "Processors";
        }

        @Override
        public synchronized Map<Integer, Processors> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(Processors.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, Processors> types;
        }
    }
}
