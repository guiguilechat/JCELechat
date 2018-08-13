package fr.guiguilechat.jcelechat.model.sde.items.types.commodity;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.types.Commodity;
import org.yaml.snakeyaml.Yaml;

public class ObsoleteBooks
    extends Commodity
{
    public final static ObsoleteBooks.MetaGroup METAGROUP = new ObsoleteBooks.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/commodity/ObsoleteBooks.yaml";
    private static Map<String, ObsoleteBooks> cache = (null);

    @Override
    public int getGroupId() {
        return  267;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<ObsoleteBooks> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, ObsoleteBooks> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(ObsoleteBooks.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, ObsoleteBooks> items;
    }

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<ObsoleteBooks>
    {

        @Override
        public MetaCategory<? super ObsoleteBooks> category() {
            return Commodity.METACAT;
        }

        @Override
        public String getName() {
            return "ObsoleteBooks";
        }

        @Override
        public Collection<ObsoleteBooks> items() {
            return (load().values());
        }
    }
}
