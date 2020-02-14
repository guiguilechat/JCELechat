package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidAngelCartelOfficer
    extends Entity
{
    public static final AsteroidAngelCartelOfficer.MetaGroup METAGROUP = new AsteroidAngelCartelOfficer.MetaGroup();

    @Override
    public IMetaGroup<AsteroidAngelCartelOfficer> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidAngelCartelOfficer>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/AsteroidAngelCartelOfficer.yaml";
        private Map<String, AsteroidAngelCartelOfficer> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidAngelCartelOfficer> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  553;
        }

        @Override
        public String getName() {
            return "AsteroidAngelCartelOfficer";
        }

        @Override
        public synchronized Map<String, AsteroidAngelCartelOfficer> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(AsteroidAngelCartelOfficer.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidAngelCartelOfficer> types;
        }
    }
}
