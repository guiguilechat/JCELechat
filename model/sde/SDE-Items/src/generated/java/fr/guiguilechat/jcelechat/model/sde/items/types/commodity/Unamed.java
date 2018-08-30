package fr.guiguilechat.jcelechat.model.sde.items.types.commodity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Commodity;
import org.yaml.snakeyaml.Yaml;

public class Unamed
    extends Commodity
{
    public final static Unamed.MetaGroup METAGROUP = new Unamed.MetaGroup();

    @Override
    public IMetaGroup<Unamed> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Unamed>
    {
        public final static String RESOURCE_PATH = "SDE/items/commodity/Unamed.yaml";
        private Map<String, Unamed> cache = (null);

        @Override
        public IMetaCategory<? super Unamed> category() {
            return Commodity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  2002;
        }

        @Override
        public String getName() {
            return "Unamed";
        }

        @Override
        public synchronized Map<String, Unamed> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(Unamed.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, Unamed> items;
        }
    }
}
