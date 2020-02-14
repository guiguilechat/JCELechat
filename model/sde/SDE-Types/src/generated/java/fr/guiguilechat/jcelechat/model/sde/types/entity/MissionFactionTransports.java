package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionFactionTransports
    extends Entity
{
    public static final MissionFactionTransports.MetaGroup METAGROUP = new MissionFactionTransports.MetaGroup();

    @Override
    public IMetaGroup<MissionFactionTransports> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MissionFactionTransports>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/MissionFactionTransports.yaml";
        private Map<String, MissionFactionTransports> cache = (null);

        @Override
        public IMetaCategory<? super MissionFactionTransports> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  875;
        }

        @Override
        public String getName() {
            return "MissionFactionTransports";
        }

        @Override
        public synchronized Map<String, MissionFactionTransports> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(MissionFactionTransports.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, MissionFactionTransports> types;
        }
    }
}
