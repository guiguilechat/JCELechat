package fr.guiguilechat.jcelechat.model.sde.items.types.commodity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Commodity;
import org.yaml.snakeyaml.Yaml;

public class Refinables
    extends Commodity
{
    public final static Refinables.MetaGroup METAGROUP = new Refinables.MetaGroup();

    @Override
    public IMetaGroup<Refinables> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Refinables>
    {
        public final static String RESOURCE_PATH = "SDE/items/commodity/Refinables.yaml";
        private Map<String, Refinables> cache = (null);

        @Override
        public IMetaCategory<? super Refinables> category() {
            return Commodity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  355;
        }

        @Override
        public String getName() {
            return "Refinables";
        }

        @Override
        public synchronized Map<String, Refinables> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(Refinables.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, Refinables> items;
        }
    }
}
