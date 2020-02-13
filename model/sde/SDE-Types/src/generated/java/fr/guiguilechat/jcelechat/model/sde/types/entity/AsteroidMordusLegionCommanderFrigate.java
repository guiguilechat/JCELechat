package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidMordusLegionCommanderFrigate
    extends Entity
{
    public static final AsteroidMordusLegionCommanderFrigate.MetaGroup METAGROUP = new AsteroidMordusLegionCommanderFrigate.MetaGroup();

    @Override
    public IMetaGroup<AsteroidMordusLegionCommanderFrigate> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidMordusLegionCommanderFrigate>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/AsteroidMordusLegionCommanderFrigate.yaml";
        private Map<String, AsteroidMordusLegionCommanderFrigate> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidMordusLegionCommanderFrigate> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1285;
        }

        @Override
        public String getName() {
            return "AsteroidMordusLegionCommanderFrigate";
        }

        @Override
        public synchronized Map<String, AsteroidMordusLegionCommanderFrigate> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidMordusLegionCommanderFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidMordusLegionCommanderFrigate> items;
        }
    }
}
