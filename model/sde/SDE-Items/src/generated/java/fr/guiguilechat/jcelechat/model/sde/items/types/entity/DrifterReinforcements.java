package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DrifterReinforcements
    extends Entity
{
    public final static DrifterReinforcements.MetaGroup METAGROUP = new DrifterReinforcements.MetaGroup();

    @Override
    public IMetaGroup<DrifterReinforcements> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<DrifterReinforcements>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/DrifterReinforcements.yaml";
        private Map<String, DrifterReinforcements> cache = (null);

        @Override
        public IMetaCategory<? super DrifterReinforcements> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1956;
        }

        @Override
        public String getName() {
            return "DrifterReinforcements";
        }

        @Override
        public synchronized Map<String, DrifterReinforcements> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(DrifterReinforcements.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, DrifterReinforcements> items;
        }
    }
}
