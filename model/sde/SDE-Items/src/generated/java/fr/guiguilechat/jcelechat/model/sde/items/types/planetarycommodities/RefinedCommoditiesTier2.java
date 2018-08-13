package fr.guiguilechat.jcelechat.model.sde.items.types.planetarycommodities;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.types.PlanetaryCommodities;
import org.yaml.snakeyaml.Yaml;

public class RefinedCommoditiesTier2
    extends PlanetaryCommodities
{
    public final static RefinedCommoditiesTier2 .MetaGroup METAGROUP = new RefinedCommoditiesTier2 .MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/planetarycommodities/RefinedCommoditiesTier2.yaml";
    private static Map<String, RefinedCommoditiesTier2> cache = (null);

    @Override
    public int getGroupId() {
        return  1034;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<RefinedCommoditiesTier2> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, RefinedCommoditiesTier2> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(RefinedCommoditiesTier2 .class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, RefinedCommoditiesTier2> items;
    }

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<RefinedCommoditiesTier2>
    {

        @Override
        public MetaCategory<? super RefinedCommoditiesTier2> category() {
            return PlanetaryCommodities.METACAT;
        }

        @Override
        public String getName() {
            return "RefinedCommoditiesTier2";
        }

        @Override
        public Collection<RefinedCommoditiesTier2> items() {
            return (load().values());
        }
    }
}
