package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionContainer
    extends Entity
{
    public final static MissionContainer.MetaGroup METAGROUP = new MissionContainer.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/entity/MissionContainer.yaml";
    private static Map<String, MissionContainer> cache = (null);

    @Override
    public int getGroupId() {
        return  952;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<MissionContainer> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, MissionContainer> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(MissionContainer.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, MissionContainer> items;
    }

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<MissionContainer>
    {

        @Override
        public MetaCategory<? super MissionContainer> category() {
            return Entity.METACAT;
        }

        @Override
        public String getName() {
            return "MissionContainer";
        }

        @Override
        public Collection<MissionContainer> items() {
            return (load().values());
        }
    }
}
