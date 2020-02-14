package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionFactionIndustrials
    extends Entity
{
    public static final MissionFactionIndustrials.MetaGroup METAGROUP = new MissionFactionIndustrials.MetaGroup();

    @Override
    public IMetaGroup<MissionFactionIndustrials> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MissionFactionIndustrials>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/MissionFactionIndustrials.yaml";
        private Map<String, MissionFactionIndustrials> cache = (null);

        @Override
        public IMetaCategory<? super MissionFactionIndustrials> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  927;
        }

        @Override
        public String getName() {
            return "MissionFactionIndustrials";
        }

        @Override
        public synchronized Map<String, MissionFactionIndustrials> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(MissionFactionIndustrials.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, MissionFactionIndustrials> types;
        }
    }
}
