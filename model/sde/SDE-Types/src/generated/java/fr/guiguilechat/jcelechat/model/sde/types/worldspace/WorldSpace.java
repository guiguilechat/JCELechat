package fr.guiguilechat.jcelechat.model.sde.types.worldspace;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import org.yaml.snakeyaml.Yaml;

public class WorldSpace
    extends fr.guiguilechat.jcelechat.model.sde.types.WorldSpace
{
    public static final WorldSpace.MetaGroup METAGROUP = new WorldSpace.MetaGroup();

    @Override
    public IMetaGroup<WorldSpace> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<WorldSpace>
    {
        public static final String RESOURCE_PATH = "SDE/items/worldspace/WorldSpace.yaml";
        private Map<String, WorldSpace> cache = (null);

        @Override
        public IMetaCategory<? super WorldSpace> category() {
            return fr.guiguilechat.jcelechat.model.sde.types.WorldSpace.METACAT;
        }

        @Override
        public int getGroupId() {
            return  935;
        }

        @Override
        public String getName() {
            return "WorldSpace";
        }

        @Override
        public synchronized Map<String, WorldSpace> load() {
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
    }
}
