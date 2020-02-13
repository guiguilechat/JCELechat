package fr.guiguilechat.jcelechat.model.sde.types.celestial;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Celestial;
import org.yaml.snakeyaml.Yaml;

public class HarvestableCloud
    extends Celestial
{
    public static final HarvestableCloud.MetaGroup METAGROUP = new HarvestableCloud.MetaGroup();

    @Override
    public IMetaGroup<HarvestableCloud> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<HarvestableCloud>
    {
        public static final String RESOURCE_PATH = "SDE/items/celestial/HarvestableCloud.yaml";
        private Map<String, HarvestableCloud> cache = (null);

        @Override
        public IMetaCategory<? super HarvestableCloud> category() {
            return Celestial.METACAT;
        }

        @Override
        public int getGroupId() {
            return  711;
        }

        @Override
        public String getName() {
            return "HarvestableCloud";
        }

        @Override
        public synchronized Map<String, HarvestableCloud> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(HarvestableCloud.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, HarvestableCloud> items;
        }
    }
}
