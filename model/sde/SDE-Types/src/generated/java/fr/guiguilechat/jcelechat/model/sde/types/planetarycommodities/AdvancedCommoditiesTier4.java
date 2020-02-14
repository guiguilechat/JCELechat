package fr.guiguilechat.jcelechat.model.sde.types.planetarycommodities;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.PlanetaryCommodities;
import org.yaml.snakeyaml.Yaml;

public class AdvancedCommoditiesTier4
    extends PlanetaryCommodities
{
    public static final AdvancedCommoditiesTier4 .MetaGroup METAGROUP = new AdvancedCommoditiesTier4 .MetaGroup();

    @Override
    public IMetaGroup<AdvancedCommoditiesTier4> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AdvancedCommoditiesTier4>
    {
        public static final String RESOURCE_PATH = "SDE/types/planetarycommodities/AdvancedCommoditiesTier4.yaml";
        private Map<String, AdvancedCommoditiesTier4> cache = (null);

        @Override
        public IMetaCategory<? super AdvancedCommoditiesTier4> category() {
            return PlanetaryCommodities.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1041;
        }

        @Override
        public String getName() {
            return "AdvancedCommoditiesTier4";
        }

        @Override
        public synchronized Map<String, AdvancedCommoditiesTier4> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(AdvancedCommoditiesTier4 .MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AdvancedCommoditiesTier4> types;
        }
    }
}
