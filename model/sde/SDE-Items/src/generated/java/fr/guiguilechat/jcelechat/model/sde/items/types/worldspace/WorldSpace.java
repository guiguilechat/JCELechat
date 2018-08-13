package fr.guiguilechat.jcelechat.model.sde.items.types.worldspace;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import org.yaml.snakeyaml.Yaml;

public class WorldSpace
    extends fr.guiguilechat.jcelechat.model.sde.items.types.WorldSpace
{
    public final static WorldSpace.MetaGroup METAGROUP = new WorldSpace.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/worldspace/WorldSpace.yaml";
    private static Map<String, WorldSpace> cache = (null);

    @Override
    public int getGroupId() {
        return  935;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<WorldSpace> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, WorldSpace> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(WorldSpace.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, WorldSpace> items;
    }

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<WorldSpace>
    {

        @Override
        public MetaCategory<? super WorldSpace> category() {
            return fr.guiguilechat.jcelechat.model.sde.items.types.WorldSpace.METACAT;
        }

        @Override
        public String getName() {
            return "WorldSpace";
        }

        @Override
        public Collection<WorldSpace> items() {
            return (load().values());
        }
    }
}
