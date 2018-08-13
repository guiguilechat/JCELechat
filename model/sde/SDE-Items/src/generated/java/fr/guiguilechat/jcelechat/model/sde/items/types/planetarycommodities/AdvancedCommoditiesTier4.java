package fr.guiguilechat.jcelechat.model.sde.items.types.planetarycommodities;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.types.PlanetaryCommodities;
import org.yaml.snakeyaml.Yaml;

public class AdvancedCommoditiesTier4
    extends PlanetaryCommodities
{
    public final static AdvancedCommoditiesTier4 .MetaGroup METAGROUP = new AdvancedCommoditiesTier4 .MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/planetarycommodities/AdvancedCommoditiesTier4.yaml";
    private static Map<String, AdvancedCommoditiesTier4> cache = (null);

    @Override
    public int getGroupId() {
        return  1041;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<AdvancedCommoditiesTier4> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, AdvancedCommoditiesTier4> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AdvancedCommoditiesTier4 .class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, AdvancedCommoditiesTier4> items;
    }

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<AdvancedCommoditiesTier4>
    {

        @Override
        public MetaCategory<? super AdvancedCommoditiesTier4> category() {
            return PlanetaryCommodities.METACAT;
        }

        @Override
        public String getName() {
            return "AdvancedCommoditiesTier4";
        }

        @Override
        public Collection<AdvancedCommoditiesTier4> items() {
            return (load().values());
        }
    }
}
