package fr.guiguilechat.jcelechat.model.sde.items.types.ancientrelics;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.types.AncientRelics;
import org.yaml.snakeyaml.Yaml;

public class SleeperOffensiveRelics
    extends AncientRelics
{
    public final static SleeperOffensiveRelics.MetaGroup METAGROUP = new SleeperOffensiveRelics.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/ancientrelics/SleeperOffensiveRelics.yaml";
    private static Map<String, SleeperOffensiveRelics> cache = (null);

    @Override
    public int getGroupId() {
        return  991;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<SleeperOffensiveRelics> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, SleeperOffensiveRelics> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(SleeperOffensiveRelics.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, SleeperOffensiveRelics> items;
    }

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<SleeperOffensiveRelics>
    {

        @Override
        public MetaCategory<? super SleeperOffensiveRelics> category() {
            return AncientRelics.METACAT;
        }

        @Override
        public String getName() {
            return "SleeperOffensiveRelics";
        }

        @Override
        public Collection<SleeperOffensiveRelics> items() {
            return (load().values());
        }
    }
}
