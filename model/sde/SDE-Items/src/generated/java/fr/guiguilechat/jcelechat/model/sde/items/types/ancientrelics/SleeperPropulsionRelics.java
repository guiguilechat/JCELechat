package fr.guiguilechat.jcelechat.model.sde.items.types.ancientrelics;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.types.AncientRelics;
import org.yaml.snakeyaml.Yaml;

public class SleeperPropulsionRelics
    extends AncientRelics
{
    public final static SleeperPropulsionRelics.MetaGroup METAGROUP = new SleeperPropulsionRelics.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/ancientrelics/SleeperPropulsionRelics.yaml";
    private static Map<String, SleeperPropulsionRelics> cache = (null);

    @Override
    public int getGroupId() {
        return  971;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<SleeperPropulsionRelics> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, SleeperPropulsionRelics> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(SleeperPropulsionRelics.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, SleeperPropulsionRelics> items;
    }

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<SleeperPropulsionRelics>
    {

        @Override
        public MetaCategory<? super SleeperPropulsionRelics> category() {
            return AncientRelics.METACAT;
        }

        @Override
        public String getName() {
            return "SleeperPropulsionRelics";
        }

        @Override
        public Collection<SleeperPropulsionRelics> items() {
            return (load().values());
        }
    }
}
