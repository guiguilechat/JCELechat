package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidGuristasCommanderBattleship
    extends Entity
{
    public static final AsteroidGuristasCommanderBattleship.MetaGroup METAGROUP = new AsteroidGuristasCommanderBattleship.MetaGroup();

    @Override
    public IMetaGroup<AsteroidGuristasCommanderBattleship> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidGuristasCommanderBattleship>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/AsteroidGuristasCommanderBattleship.yaml";
        private Map<String, AsteroidGuristasCommanderBattleship> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidGuristasCommanderBattleship> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  850;
        }

        @Override
        public String getName() {
            return "AsteroidGuristasCommanderBattleship";
        }

        @Override
        public synchronized Map<String, AsteroidGuristasCommanderBattleship> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(AsteroidGuristasCommanderBattleship.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidGuristasCommanderBattleship> types;
        }
    }
}
