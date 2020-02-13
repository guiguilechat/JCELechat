package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidMordusLegionCommanderBattleship
    extends Entity
{
    public static final AsteroidMordusLegionCommanderBattleship.MetaGroup METAGROUP = new AsteroidMordusLegionCommanderBattleship.MetaGroup();

    @Override
    public IMetaGroup<AsteroidMordusLegionCommanderBattleship> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidMordusLegionCommanderBattleship>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/AsteroidMordusLegionCommanderBattleship.yaml";
        private Map<String, AsteroidMordusLegionCommanderBattleship> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidMordusLegionCommanderBattleship> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1287;
        }

        @Override
        public String getName() {
            return "AsteroidMordusLegionCommanderBattleship";
        }

        @Override
        public synchronized Map<String, AsteroidMordusLegionCommanderBattleship> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidMordusLegionCommanderBattleship.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidMordusLegionCommanderBattleship> items;
        }
    }
}
