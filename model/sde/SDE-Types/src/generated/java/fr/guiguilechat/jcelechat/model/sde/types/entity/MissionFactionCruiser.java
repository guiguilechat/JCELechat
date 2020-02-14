package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionFactionCruiser
    extends Entity
{
    public static final MissionFactionCruiser.MetaGroup METAGROUP = new MissionFactionCruiser.MetaGroup();

    @Override
    public IMetaGroup<MissionFactionCruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MissionFactionCruiser>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/MissionFactionCruiser.yaml";
        private Map<String, MissionFactionCruiser> cache = (null);

        @Override
        public IMetaCategory<? super MissionFactionCruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1006;
        }

        @Override
        public String getName() {
            return "MissionFactionCruiser";
        }

        @Override
        public synchronized Map<String, MissionFactionCruiser> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(MissionFactionCruiser.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, MissionFactionCruiser> types;
        }
    }
}
