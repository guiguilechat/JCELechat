package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidMordusLegionCommanderCruiser
    extends Entity
{
    public static final AsteroidMordusLegionCommanderCruiser.MetaGroup METAGROUP = new AsteroidMordusLegionCommanderCruiser.MetaGroup();

    @Override
    public IMetaGroup<AsteroidMordusLegionCommanderCruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidMordusLegionCommanderCruiser>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/AsteroidMordusLegionCommanderCruiser.yaml";
        private Map<String, AsteroidMordusLegionCommanderCruiser> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidMordusLegionCommanderCruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1286;
        }

        @Override
        public String getName() {
            return "AsteroidMordusLegionCommanderCruiser";
        }

        @Override
        public synchronized Map<String, AsteroidMordusLegionCommanderCruiser> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(AsteroidMordusLegionCommanderCruiser.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AsteroidMordusLegionCommanderCruiser> types;
        }
    }
}
