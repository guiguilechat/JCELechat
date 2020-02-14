package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidSanshaSNationCommanderBattleCruiser
    extends Entity
{
    public static final AsteroidSanshaSNationCommanderBattleCruiser.MetaGroup METAGROUP = new AsteroidSanshaSNationCommanderBattleCruiser.MetaGroup();

    @Override
    public IMetaGroup<AsteroidSanshaSNationCommanderBattleCruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidSanshaSNationCommanderBattleCruiser>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/AsteroidSanshaSNationCommanderBattleCruiser.yaml";
        private Map<String, AsteroidSanshaSNationCommanderBattleCruiser> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidSanshaSNationCommanderBattleCruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  807;
        }

        @Override
        public String getName() {
            return "AsteroidSanshaSNationCommanderBattleCruiser";
        }

        @Override
        public synchronized Map<String, AsteroidSanshaSNationCommanderBattleCruiser> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(AsteroidSanshaSNationCommanderBattleCruiser.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidSanshaSNationCommanderBattleCruiser> types;
        }
    }
}
