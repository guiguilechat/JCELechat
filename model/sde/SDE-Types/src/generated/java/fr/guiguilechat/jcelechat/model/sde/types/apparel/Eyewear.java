package fr.guiguilechat.jcelechat.model.sde.types.apparel;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Apparel;
import org.yaml.snakeyaml.Yaml;

public class Eyewear
    extends Apparel
{
    public static final Eyewear.MetaGroup METAGROUP = new Eyewear.MetaGroup();

    @Override
    public IMetaGroup<Eyewear> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Eyewear>
    {
        public static final String RESOURCE_PATH = "SDE/items/apparel/Eyewear.yaml";
        private Map<String, Eyewear> cache = (null);

        @Override
        public IMetaCategory<? super Eyewear> category() {
            return Apparel.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1083;
        }

        @Override
        public String getName() {
            return "Eyewear";
        }

        @Override
        public synchronized Map<String, Eyewear> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(Eyewear.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, Eyewear> items;
        }
    }
}
