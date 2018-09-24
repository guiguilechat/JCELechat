package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class Convoy
    extends Entity
{
    public final static Convoy.MetaGroup METAGROUP = new Convoy.MetaGroup();

    @Override
    public IMetaGroup<Convoy> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Convoy>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/Convoy.yaml";
        private Map<String, Convoy> cache = (null);

        @Override
        public IMetaCategory<? super Convoy> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  297;
        }

        @Override
        public String getName() {
            return "Convoy";
        }

        @Override
        public synchronized Map<String, Convoy> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(Convoy.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, Convoy> items;
        }
    }
}
