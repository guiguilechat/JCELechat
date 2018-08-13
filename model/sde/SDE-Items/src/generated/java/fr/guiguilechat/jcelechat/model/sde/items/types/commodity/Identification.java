package fr.guiguilechat.jcelechat.model.sde.items.types.commodity;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.types.Commodity;
import org.yaml.snakeyaml.Yaml;

public class Identification
    extends Commodity
{
    public final static Identification.MetaGroup METAGROUP = new Identification.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/commodity/Identification.yaml";
    private static Map<String, Identification> cache = (null);

    @Override
    public int getGroupId() {
        return  521;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<Identification> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, Identification> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Identification.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, Identification> items;
    }

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<Identification>
    {

        @Override
        public MetaCategory<? super Identification> category() {
            return Commodity.METACAT;
        }

        @Override
        public String getName() {
            return "Identification";
        }

        @Override
        public Collection<Identification> items() {
            return (load().values());
        }
    }
}
