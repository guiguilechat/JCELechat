package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidSanshaSNationBattleship
    extends Entity
{
    public static final AsteroidSanshaSNationBattleship.MetaGroup METAGROUP = new AsteroidSanshaSNationBattleship.MetaGroup();

    @Override
    public IMetaGroup<AsteroidSanshaSNationBattleship> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidSanshaSNationBattleship>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/AsteroidSanshaSNationBattleship.yaml";
        private Map<String, AsteroidSanshaSNationBattleship> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidSanshaSNationBattleship> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  565;
        }

        @Override
        public String getName() {
            return "AsteroidSanshaSNationBattleship";
        }

        @Override
        public synchronized Map<String, AsteroidSanshaSNationBattleship> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(AsteroidSanshaSNationBattleship.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidSanshaSNationBattleship> types;
        }
    }
}
