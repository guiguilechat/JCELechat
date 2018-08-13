package fr.guiguilechat.jcelechat.model.sde.items.types.planetarycommodities;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.types.PlanetaryCommodities;
import org.yaml.snakeyaml.Yaml;

public class SpecializedCommoditiesTier3
    extends PlanetaryCommodities
{
    public final static SpecializedCommoditiesTier3 .MetaGroup METAGROUP = new SpecializedCommoditiesTier3 .MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/planetarycommodities/SpecializedCommoditiesTier3.yaml";
    private static Map<String, SpecializedCommoditiesTier3> cache = (null);

    @Override
    public int getGroupId() {
        return  1040;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<SpecializedCommoditiesTier3> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, SpecializedCommoditiesTier3> load() {
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

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<SpecializedCommoditiesTier3>
    {

        @Override
        public MetaCategory<? super SpecializedCommoditiesTier3> category() {
            return PlanetaryCommodities.METACAT;
        }

        @Override
        public String getName() {
            return "SpecializedCommoditiesTier3";
        }

        @Override
        public Collection<SpecializedCommoditiesTier3> items() {
            return (load().values());
        }
    }
}
