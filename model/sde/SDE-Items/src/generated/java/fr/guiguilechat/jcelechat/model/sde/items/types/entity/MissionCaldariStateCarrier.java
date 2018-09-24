package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionCaldariStateCarrier
    extends Entity
{
    public final static MissionCaldariStateCarrier.MetaGroup METAGROUP = new MissionCaldariStateCarrier.MetaGroup();

    @Override
    public IMetaGroup<MissionCaldariStateCarrier> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MissionCaldariStateCarrier>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/MissionCaldariStateCarrier.yaml";
        private Map<String, MissionCaldariStateCarrier> cache = (null);

        @Override
        public IMetaCategory<? super MissionCaldariStateCarrier> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  866;
        }

        @Override
        public String getName() {
            return "MissionCaldariStateCarrier";
        }

        @Override
        public synchronized Map<String, MissionCaldariStateCarrier> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(MissionCaldariStateCarrier.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, MissionCaldariStateCarrier> items;
        }
    }
}
