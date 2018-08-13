package fr.guiguilechat.jcelechat.model.sde.items.types.ancientrelics;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.types.AncientRelics;
import org.yaml.snakeyaml.Yaml;

public class SleeperDefensiveRelics
    extends AncientRelics
{
    public final static SleeperDefensiveRelics.MetaGroup METAGROUP = new SleeperDefensiveRelics.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/ancientrelics/SleeperDefensiveRelics.yaml";
    private static Map<String, SleeperDefensiveRelics> cache = (null);

    @Override
    public int getGroupId() {
        return  993;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<SleeperDefensiveRelics> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, SleeperDefensiveRelics> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(SleeperDefensiveRelics.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, SleeperDefensiveRelics> items;
    }

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<SleeperDefensiveRelics>
    {

        @Override
        public MetaCategory<? super SleeperDefensiveRelics> category() {
            return AncientRelics.METACAT;
        }

        @Override
        public String getName() {
            return "SleeperDefensiveRelics";
        }

        @Override
        public Collection<SleeperDefensiveRelics> items() {
            return (load().values());
        }
    }
}
