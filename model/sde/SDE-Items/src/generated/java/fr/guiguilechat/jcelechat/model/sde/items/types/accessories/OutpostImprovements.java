package fr.guiguilechat.jcelechat.model.sde.items.types.accessories;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.types.Accessories;
import org.yaml.snakeyaml.Yaml;

public class OutpostImprovements
    extends Accessories
{
    public final static OutpostImprovements.MetaGroup METAGROUP = new OutpostImprovements.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/accessories/OutpostImprovements.yaml";
    private static Map<String, OutpostImprovements> cache = (null);

    @Override
    public int getGroupId() {
        return  872;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<OutpostImprovements> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, OutpostImprovements> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(OutpostImprovements.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, OutpostImprovements> items;
    }

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<OutpostImprovements>
    {

        @Override
        public MetaCategory<? super OutpostImprovements> category() {
            return Accessories.METACAT;
        }

        @Override
        public String getName() {
            return "OutpostImprovements";
        }

        @Override
        public Collection<OutpostImprovements> items() {
            return (load().values());
        }
    }
}
