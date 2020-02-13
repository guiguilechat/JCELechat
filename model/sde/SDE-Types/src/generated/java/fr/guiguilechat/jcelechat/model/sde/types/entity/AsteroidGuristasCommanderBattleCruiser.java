package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidGuristasCommanderBattleCruiser
    extends Entity
{
    public static final AsteroidGuristasCommanderBattleCruiser.MetaGroup METAGROUP = new AsteroidGuristasCommanderBattleCruiser.MetaGroup();

    @Override
    public IMetaGroup<AsteroidGuristasCommanderBattleCruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidGuristasCommanderBattleCruiser>
    {
        public static final String RESOURCE_PATH = "SDE/items/entity/AsteroidGuristasCommanderBattleCruiser.yaml";
        private Map<String, AsteroidGuristasCommanderBattleCruiser> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidGuristasCommanderBattleCruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  797;
        }

        @Override
        public String getName() {
            return "AsteroidGuristasCommanderBattleCruiser";
        }

        @Override
        public synchronized Map<String, AsteroidGuristasCommanderBattleCruiser> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(AsteroidGuristasCommanderBattleCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidGuristasCommanderBattleCruiser> items;
        }
    }
}
