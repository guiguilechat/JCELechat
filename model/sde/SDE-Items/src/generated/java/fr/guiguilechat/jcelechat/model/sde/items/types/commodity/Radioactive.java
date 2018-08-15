package fr.guiguilechat.jcelechat.model.sde.items.types.commodity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Commodity;
import org.yaml.snakeyaml.Yaml;

public class Radioactive
    extends Commodity
{
    public final static Radioactive.MetaGroup METAGROUP = new Radioactive.MetaGroup();

    @Override
    public IMetaGroup<Radioactive> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Radioactive>
    {
        public final static String RESOURCE_PATH = "SDE/items/commodity/Radioactive.yaml";
        private Map<String, Radioactive> cache = (null);

        @Override
        public IMetaCategory<? super Radioactive> category() {
            return Commodity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  282;
        }

        @Override
        public String getName() {
            return "Radioactive";
        }

        @Override
        public synchronized Map<String, Radioactive> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(Radioactive.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, Radioactive> items;
        }
    }
}