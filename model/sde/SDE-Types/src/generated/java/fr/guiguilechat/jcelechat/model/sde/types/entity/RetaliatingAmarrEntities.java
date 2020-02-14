package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class RetaliatingAmarrEntities
    extends Entity
{
    public static final RetaliatingAmarrEntities.MetaGroup METAGROUP = new RetaliatingAmarrEntities.MetaGroup();

    @Override
    public IMetaGroup<RetaliatingAmarrEntities> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<RetaliatingAmarrEntities>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/RetaliatingAmarrEntities.yaml";
        private Map<String, RetaliatingAmarrEntities> cache = (null);

        @Override
        public IMetaCategory<? super RetaliatingAmarrEntities> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  4034;
        }

        @Override
        public String getName() {
            return "RetaliatingAmarrEntities";
        }

        @Override
        public synchronized Map<String, RetaliatingAmarrEntities> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(RetaliatingAmarrEntities.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, RetaliatingAmarrEntities> types;
        }
    }
}
