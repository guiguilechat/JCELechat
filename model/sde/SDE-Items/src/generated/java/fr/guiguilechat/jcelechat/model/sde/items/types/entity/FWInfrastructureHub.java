package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class FWInfrastructureHub
    extends Entity
{
    public final static FWInfrastructureHub.MetaGroup METAGROUP = new FWInfrastructureHub.MetaGroup();

    @Override
    public IMetaGroup<FWInfrastructureHub> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<FWInfrastructureHub>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/FWInfrastructureHub.yaml";
        private Map<String, FWInfrastructureHub> cache = (null);

        @Override
        public IMetaCategory<? super FWInfrastructureHub> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  925;
        }

        @Override
        public String getName() {
            return "FWInfrastructureHub";
        }

        @Override
        public synchronized Map<String, FWInfrastructureHub> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(FWInfrastructureHub.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, FWInfrastructureHub> items;
        }
    }
}
