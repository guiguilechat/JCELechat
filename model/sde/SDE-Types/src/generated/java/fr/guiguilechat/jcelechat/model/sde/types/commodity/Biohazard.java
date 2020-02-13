package fr.guiguilechat.jcelechat.model.sde.types.commodity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Commodity;
import org.yaml.snakeyaml.Yaml;

public class Biohazard
    extends Commodity
{
    public static final Biohazard.MetaGroup METAGROUP = new Biohazard.MetaGroup();

    @Override
    public IMetaGroup<Biohazard> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Biohazard>
    {
        public static final String RESOURCE_PATH = "SDE/items/commodity/Biohazard.yaml";
        private Map<String, Biohazard> cache = (null);

        @Override
        public IMetaCategory<? super Biohazard> category() {
            return Commodity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  284;
        }

        @Override
        public String getName() {
            return "Biohazard";
        }

        @Override
        public synchronized Map<String, Biohazard> load() {
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
    }
}
