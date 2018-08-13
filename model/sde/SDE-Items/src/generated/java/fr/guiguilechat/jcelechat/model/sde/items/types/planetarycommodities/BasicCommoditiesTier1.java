package fr.guiguilechat.jcelechat.model.sde.items.types.planetarycommodities;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.types.PlanetaryCommodities;
import org.yaml.snakeyaml.Yaml;

public class BasicCommoditiesTier1
    extends PlanetaryCommodities
{
    public final static BasicCommoditiesTier1 .MetaGroup METAGROUP = new BasicCommoditiesTier1 .MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/planetarycommodities/BasicCommoditiesTier1.yaml";
    private static Map<String, BasicCommoditiesTier1> cache = (null);

    @Override
    public int getGroupId() {
        return  1042;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<BasicCommoditiesTier1> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, BasicCommoditiesTier1> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(BasicCommoditiesTier1 .class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, BasicCommoditiesTier1> items;
    }

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<BasicCommoditiesTier1>
    {

        @Override
        public MetaCategory<? super BasicCommoditiesTier1> category() {
            return PlanetaryCommodities.METACAT;
        }

        @Override
        public String getName() {
            return "BasicCommoditiesTier1";
        }

        @Override
        public Collection<BasicCommoditiesTier1> items() {
            return (load().values());
        }
    }
}
