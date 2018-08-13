package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class SpawnContainer
    extends Entity
{
    public final static SpawnContainer.MetaGroup METAGROUP = new SpawnContainer.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/entity/SpawnContainer.yaml";
    private static Map<String, SpawnContainer> cache = (null);

    @Override
    public int getGroupId() {
        return  306;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<SpawnContainer> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, SpawnContainer> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(SpawnContainer.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, SpawnContainer> items;
    }

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<SpawnContainer>
    {

        @Override
        public MetaCategory<? super SpawnContainer> category() {
            return Entity.METACAT;
        }

        @Override
        public String getName() {
            return "SpawnContainer";
        }

        @Override
        public Collection<SpawnContainer> items() {
            return (load().values());
        }
    }
}
