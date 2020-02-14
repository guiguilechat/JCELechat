package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidRogueDroneCommanderBattleship
    extends Entity
{
    public static final AsteroidRogueDroneCommanderBattleship.MetaGroup METAGROUP = new AsteroidRogueDroneCommanderBattleship.MetaGroup();

    @Override
    public IMetaGroup<AsteroidRogueDroneCommanderBattleship> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidRogueDroneCommanderBattleship>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/AsteroidRogueDroneCommanderBattleship.yaml";
        private Map<String, AsteroidRogueDroneCommanderBattleship> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidRogueDroneCommanderBattleship> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  844;
        }

        @Override
        public String getName() {
            return "AsteroidRogueDroneCommanderBattleship";
        }

        @Override
        public synchronized Map<String, AsteroidRogueDroneCommanderBattleship> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(AsteroidRogueDroneCommanderBattleship.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidRogueDroneCommanderBattleship> types;
        }
    }
}
