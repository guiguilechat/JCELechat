package fr.guiguilechat.jcelechat.model.sde.items.types.commodity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Commodity;
import org.yaml.snakeyaml.Yaml;

public class Drugs
    extends Commodity
{
    public final static Drugs.MetaGroup METAGROUP = new Drugs.MetaGroup();

    @Override
    public IMetaGroup<Drugs> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Drugs>
    {
        public final static String RESOURCE_PATH = "SDE/items/commodity/Drugs.yaml";
        private Map<String, Drugs> cache = (null);

        @Override
        public IMetaCategory<? super Drugs> category() {
            return Commodity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  313;
        }

        @Override
        public String getName() {
            return "Drugs";
        }

        @Override
        public synchronized Map<String, Drugs> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(Drugs.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, Drugs> items;
        }
    }
}
