package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidBloodRaidersCruiser
    extends Entity
{
    public static final AsteroidBloodRaidersCruiser.MetaGroup METAGROUP = new AsteroidBloodRaidersCruiser.MetaGroup();

    @Override
    public IMetaGroup<AsteroidBloodRaidersCruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidBloodRaidersCruiser>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/AsteroidBloodRaidersCruiser.yaml";
        private Map<String, AsteroidBloodRaidersCruiser> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidBloodRaidersCruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  555;
        }

        @Override
        public String getName() {
            return "AsteroidBloodRaidersCruiser";
        }

        @Override
        public synchronized Map<String, AsteroidBloodRaidersCruiser> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(AsteroidBloodRaidersCruiser.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidBloodRaidersCruiser> types;
        }
    }
}
