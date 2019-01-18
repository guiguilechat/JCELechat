package fr.guiguilechat.jcelechat.model.sde.items.types.planetarycommodities;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.PlanetaryCommodities;
import org.yaml.snakeyaml.Yaml;

public class SpecializedCommoditiesTier3
    extends PlanetaryCommodities
{
    public static final SpecializedCommoditiesTier3 .MetaGroup METAGROUP = new SpecializedCommoditiesTier3 .MetaGroup();

    @Override
    public IMetaGroup<SpecializedCommoditiesTier3> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<SpecializedCommoditiesTier3>
    {
        public static final String RESOURCE_PATH = "SDE/items/planetarycommodities/SpecializedCommoditiesTier3.yaml";
        private Map<String, SpecializedCommoditiesTier3> cache = (null);

        @Override
        public IMetaCategory<? super SpecializedCommoditiesTier3> category() {
            return PlanetaryCommodities.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1040;
        }

        @Override
        public String getName() {
            return "SpecializedCommoditiesTier3";
        }

        @Override
        public synchronized Map<String, SpecializedCommoditiesTier3> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(SpecializedCommoditiesTier3 .class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, SpecializedCommoditiesTier3> items;
        }
    }
}
