package fr.guiguilechat.jcelechat.model.sde.items.types.commodity;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.types.Commodity;
import org.yaml.snakeyaml.Yaml;

public class Drugs
    extends Commodity
{
    public final static Drugs.MetaGroup METAGROUP = new Drugs.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/commodity/Drugs.yaml";
    private static Map<String, Drugs> cache = (null);

    @Override
    public int getGroupId() {
        return  313;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<Drugs> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, Drugs> load() {
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

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<Drugs>
    {

        @Override
        public MetaCategory<? super Drugs> category() {
            return Commodity.METACAT;
        }

        @Override
        public String getName() {
            return "Drugs";
        }

        @Override
        public Collection<Drugs> items() {
            return (load().values());
        }
    }
}
