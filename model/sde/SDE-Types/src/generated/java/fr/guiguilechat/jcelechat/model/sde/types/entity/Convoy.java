package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class Convoy
    extends Entity
{
    public static final Convoy.MetaGroup METAGROUP = new Convoy.MetaGroup();

    @Override
    public IMetaGroup<Convoy> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Convoy>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/Convoy.yaml";
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
                try(final InputStreamReader reader = new InputStreamReader(Convoy.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, Convoy> types;
        }
    }
}
