package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class RetaliatingCaldariEntities
    extends Entity
{
    public static final RetaliatingCaldariEntities.MetaGroup METAGROUP = new RetaliatingCaldariEntities.MetaGroup();

    @Override
    public IMetaGroup<RetaliatingCaldariEntities> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<RetaliatingCaldariEntities>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/RetaliatingCaldariEntities.yaml";
        private Map<String, RetaliatingCaldariEntities> cache = (null);

        @Override
        public IMetaCategory<? super RetaliatingCaldariEntities> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  4035;
        }

        @Override
        public String getName() {
            return "RetaliatingCaldariEntities";
        }

        @Override
        public synchronized Map<String, RetaliatingCaldariEntities> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(RetaliatingCaldariEntities.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, RetaliatingCaldariEntities> types;
        }
    }
}
