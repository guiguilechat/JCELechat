package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class ConcordDrone
    extends Entity
{
    public static final ConcordDrone.MetaGroup METAGROUP = new ConcordDrone.MetaGroup();

    @Override
    public IMetaGroup<ConcordDrone> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<ConcordDrone>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/ConcordDrone.yaml";
        private Map<String, ConcordDrone> cache = (null);

        @Override
        public IMetaCategory<? super ConcordDrone> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  301;
        }

        @Override
        public String getName() {
            return "ConcordDrone";
        }

        @Override
        public synchronized Map<String, ConcordDrone> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(ConcordDrone.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, ConcordDrone> types;
        }
    }
}
