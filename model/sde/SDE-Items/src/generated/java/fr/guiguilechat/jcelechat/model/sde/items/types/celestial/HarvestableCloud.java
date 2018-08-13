package fr.guiguilechat.jcelechat.model.sde.items.types.celestial;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.types.Celestial;
import org.yaml.snakeyaml.Yaml;

public class HarvestableCloud
    extends Celestial
{
    public final static HarvestableCloud.MetaGroup METAGROUP = new HarvestableCloud.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/celestial/HarvestableCloud.yaml";
    private static Map<String, HarvestableCloud> cache = (null);

    @Override
    public int getGroupId() {
        return  711;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<HarvestableCloud> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, HarvestableCloud> load() {
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

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<HarvestableCloud>
    {

        @Override
        public MetaCategory<? super HarvestableCloud> category() {
            return Celestial.METACAT;
        }

        @Override
        public String getName() {
            return "HarvestableCloud";
        }

        @Override
        public Collection<HarvestableCloud> items() {
            return (load().values());
        }
    }
}
