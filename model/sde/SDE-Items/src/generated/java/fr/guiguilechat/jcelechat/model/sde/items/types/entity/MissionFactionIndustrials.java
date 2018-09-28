package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionFactionIndustrials
    extends Entity
{
    public final static MissionFactionIndustrials.MetaGroup METAGROUP = new MissionFactionIndustrials.MetaGroup();

    @Override
    public IMetaGroup<MissionFactionIndustrials> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MissionFactionIndustrials>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/MissionFactionIndustrials.yaml";
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
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(MissionFactionIndustrials.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, MissionFactionIndustrials> items;
        }
    }
}