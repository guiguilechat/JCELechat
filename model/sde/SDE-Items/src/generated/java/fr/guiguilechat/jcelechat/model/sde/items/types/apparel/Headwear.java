package fr.guiguilechat.jcelechat.model.sde.items.types.apparel;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Apparel;
import org.yaml.snakeyaml.Yaml;

public class Headwear
    extends Apparel
{
    public final static Headwear.MetaGroup METAGROUP = new Headwear.MetaGroup();

    @Override
    public IMetaGroup<Headwear> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Headwear>
    {
        public final static String RESOURCE_PATH = "SDE/items/apparel/Headwear.yaml";
        private Map<String, Headwear> cache = (null);

        @Override
        public IMetaCategory<? super Headwear> category() {
            return Apparel.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1092;
        }

        @Override
        public String getName() {
            return "Headwear";
        }

        @Override
        public synchronized Map<String, Headwear> load() {
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
    }
}
