package fr.guiguilechat.jcelechat.model.sde.items.types.apparel;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.types.Apparel;
import org.yaml.snakeyaml.Yaml;

public class Headwear
    extends Apparel
{
    public final static Headwear.MetaGroup METAGROUP = new Headwear.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/apparel/Headwear.yaml";
    private static Map<String, Headwear> cache = (null);

    @Override
    public int getGroupId() {
        return  1092;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<Headwear> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, Headwear> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Headwear.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, Headwear> items;
    }

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<Headwear>
    {

        @Override
        public MetaCategory<? super Headwear> category() {
            return Apparel.METACAT;
        }

        @Override
        public String getName() {
            return "Headwear";
        }

        @Override
        public Collection<Headwear> items() {
            return (load().values());
        }
    }
}
