package fr.guiguilechat.jcelechat.model.sde.types.apparel;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Apparel;
import org.yaml.snakeyaml.Yaml;

public class Headwear
    extends Apparel
{
    public static final Headwear.MetaGroup METAGROUP = new Headwear.MetaGroup();

    @Override
    public IMetaGroup<Headwear> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Headwear>
    {
        public static final String RESOURCE_PATH = "SDE/types/apparel/Headwear.yaml";
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
                try(final InputStreamReader reader = new InputStreamReader(Headwear.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, Headwear> types;
        }
    }
}
