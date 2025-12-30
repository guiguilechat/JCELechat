package fr.guiguilechat.jcelechat.model.sde.types.station;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Station;

public class StationServices
    extends Station
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final StationServices.MetaGroup METAGROUP = new StationServices.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<StationServices> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<StationServices>
    {
        public static final String RESOURCE_PATH = "SDE/types/station/StationServices.yaml";
        private Map<Integer, StationServices> cache = (null);

        @Override
        public IMetaCategory<? super StationServices> category() {
            return Station.METACAT;
        }

        @Override
        public int getGroupId() {
            return  16;
        }

        @Override
        public String getName() {
            return "StationServices";
        }

        @Override
        public synchronized Map<Integer, StationServices> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(StationServices.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, StationServices> types;
        }
    }
}
