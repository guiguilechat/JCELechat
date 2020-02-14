package fr.guiguilechat.jcelechat.model.sde.types.station;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import org.yaml.snakeyaml.Yaml;

public class Station
    extends fr.guiguilechat.jcelechat.model.sde.types.Station
{
    public static final Station.MetaGroup METAGROUP = new Station.MetaGroup();

    @Override
    public IMetaGroup<Station> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Station>
    {
        public static final String RESOURCE_PATH = "SDE/types/station/Station.yaml";
        private Map<String, Station> cache = (null);

        @Override
        public IMetaCategory<? super Station> category() {
            return fr.guiguilechat.jcelechat.model.sde.types.Station.METACAT;
        }

        @Override
        public int getGroupId() {
            return  15;
        }

        @Override
        public String getName() {
            return "Station";
        }

        @Override
        public synchronized Map<String, Station> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(Station.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, Station> types;
        }
    }
}
