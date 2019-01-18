package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidRogueDroneCommanderBattleCruiser
    extends Entity
{
    public static final AsteroidRogueDroneCommanderBattleCruiser.MetaGroup METAGROUP = new AsteroidRogueDroneCommanderBattleCruiser.MetaGroup();

    @Override
    public IMetaGroup<AsteroidRogueDroneCommanderBattleCruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidRogueDroneCommanderBattleCruiser>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/AsteroidRogueDroneCommanderBattleCruiser.yaml";
        private Map<String, AsteroidRogueDroneCommanderBattleCruiser> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidRogueDroneCommanderBattleCruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  843;
        }

        @Override
        public String getName() {
            return "AsteroidRogueDroneCommanderBattleCruiser";
        }

        @Override
        public synchronized Map<String, AsteroidRogueDroneCommanderBattleCruiser> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidRogueDroneCommanderBattleCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidRogueDroneCommanderBattleCruiser> items;
        }
    }
}
