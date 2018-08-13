package fr.guiguilechat.jcelechat.model.sde.items.types.commodity;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.types.Commodity;
import org.yaml.snakeyaml.Yaml;

public class General
    extends Commodity
{
    public final static General.MetaGroup METAGROUP = new General.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/commodity/General.yaml";
    private static Map<String, General> cache = (null);

    @Override
    public int getGroupId() {
        return  280;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<General> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, General> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(General.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, General> items;
    }

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<General>
    {

        @Override
        public MetaCategory<? super General> category() {
            return Commodity.METACAT;
        }

        @Override
        public String getName() {
            return "General";
        }

        @Override
        public Collection<General> items() {
            return (load().values());
        }
    }
}
