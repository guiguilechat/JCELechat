package fr.guiguilechat.jcelechat.model.sde.types.worldspace;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class WorldSpace
    extends fr.guiguilechat.jcelechat.model.sde.types.WorldSpace
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final WorldSpace.MetaGroup METAGROUP = new WorldSpace.MetaGroup();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<WorldSpace> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<WorldSpace>
    {
        public static final String RESOURCE_PATH = "SDE/types/worldspace/WorldSpace.yaml";
        private Map<Integer, WorldSpace> cache = (null);

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
        public synchronized Map<Integer, WorldSpace> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(WorldSpace.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    LoaderOptions options = new LoaderOptions();
                    options.setCodePointLimit(Integer.MAX_VALUE);
                    cache = new Yaml(options).loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<Integer, WorldSpace> types;
        }
    }
}
