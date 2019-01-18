package fr.guiguilechat.jcelechat.model.sde.items.types.commodity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Commodity;
import org.yaml.snakeyaml.Yaml;

public class EmpireInsigniaDrops
    extends Commodity
{
    public static final EmpireInsigniaDrops.MetaGroup METAGROUP = new EmpireInsigniaDrops.MetaGroup();

    @Override
    public IMetaGroup<EmpireInsigniaDrops> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<EmpireInsigniaDrops>
    {
        public static final String RESOURCE_PATH = "SDE/items/commodity/EmpireInsigniaDrops.yaml";
        private Map<String, EmpireInsigniaDrops> cache = (null);

        @Override
        public IMetaCategory<? super EmpireInsigniaDrops> category() {
            return Commodity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  409;
        }

        @Override
        public String getName() {
            return "EmpireInsigniaDrops";
        }

        @Override
        public synchronized Map<String, EmpireInsigniaDrops> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(EmpireInsigniaDrops.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, EmpireInsigniaDrops> items;
        }
    }
}
