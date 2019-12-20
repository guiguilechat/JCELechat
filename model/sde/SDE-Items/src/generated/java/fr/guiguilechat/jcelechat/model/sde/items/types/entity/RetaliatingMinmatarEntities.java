package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class RetaliatingMinmatarEntities
    extends Entity
{
    public static final RetaliatingMinmatarEntities.MetaGroup METAGROUP = new RetaliatingMinmatarEntities.MetaGroup();

    @Override
    public IMetaGroup<RetaliatingMinmatarEntities> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<RetaliatingMinmatarEntities>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/RetaliatingMinmatarEntities.yaml";
        private Map<String, RetaliatingMinmatarEntities> cache = (null);

        @Override
        public IMetaCategory<? super RetaliatingMinmatarEntities> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  4037;
        }

        @Override
        public String getName() {
            return "RetaliatingMinmatarEntities";
        }

        @Override
        public synchronized Map<String, RetaliatingMinmatarEntities> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(RetaliatingMinmatarEntities.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, RetaliatingMinmatarEntities> items;
        }
    }
}