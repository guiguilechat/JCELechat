package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class NPCCruiser
    extends Entity
{
    public static final NPCCruiser.MetaGroup METAGROUP = new NPCCruiser.MetaGroup();

    @Override
    public IMetaGroup<NPCCruiser> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<NPCCruiser>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/NPCCruiser.yaml";
        private Map<String, NPCCruiser> cache = (null);

        @Override
        public IMetaCategory<? super NPCCruiser> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1813;
        }

        @Override
        public String getName() {
            return "NPCCruiser";
        }

        @Override
        public synchronized Map<String, NPCCruiser> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(NPCCruiser.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, NPCCruiser> types;
        }
    }
}
