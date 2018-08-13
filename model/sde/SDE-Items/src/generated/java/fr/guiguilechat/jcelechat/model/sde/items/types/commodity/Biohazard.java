package fr.guiguilechat.jcelechat.model.sde.items.types.commodity;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.types.Commodity;
import org.yaml.snakeyaml.Yaml;

public class Biohazard
    extends Commodity
{
    public final static Biohazard.MetaGroup METAGROUP = new Biohazard.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/commodity/Biohazard.yaml";
    private static Map<String, Biohazard> cache = (null);

    @Override
    public int getGroupId() {
        return  284;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<Biohazard> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, Biohazard> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Biohazard.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, Biohazard> items;
    }

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<Biohazard>
    {

        @Override
        public MetaCategory<? super Biohazard> category() {
            return Commodity.METACAT;
        }

        @Override
        public String getName() {
            return "Biohazard";
        }

        @Override
        public Collection<Biohazard> items() {
            return (load().values());
        }
    }
}
