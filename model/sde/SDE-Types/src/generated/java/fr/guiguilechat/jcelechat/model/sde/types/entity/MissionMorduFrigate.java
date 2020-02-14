package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionMorduFrigate
    extends Entity
{
    public static final MissionMorduFrigate.MetaGroup METAGROUP = new MissionMorduFrigate.MetaGroup();

    @Override
    public IMetaGroup<MissionMorduFrigate> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MissionMorduFrigate>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/MissionMorduFrigate.yaml";
        private Map<String, MissionMorduFrigate> cache = (null);

        @Override
        public IMetaCategory<? super MissionMorduFrigate> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  699;
        }

        @Override
        public String getName() {
            return "MissionMorduFrigate";
        }

        @Override
        public synchronized Map<String, MissionMorduFrigate> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(MissionMorduFrigate.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, MissionMorduFrigate> types;
        }
    }
}
