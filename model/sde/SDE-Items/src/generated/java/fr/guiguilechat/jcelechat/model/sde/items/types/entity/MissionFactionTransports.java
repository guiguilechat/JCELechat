package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionFactionTransports
    extends Entity
{
    public final static MissionFactionTransports.MetaGroup METAGROUP = new MissionFactionTransports.MetaGroup();

    @Override
    public IMetaGroup<MissionFactionTransports> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MissionFactionTransports>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/MissionFactionTransports.yaml";
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
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(MissionFactionTransports.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, MissionFactionTransports> items;
        }
    }
}