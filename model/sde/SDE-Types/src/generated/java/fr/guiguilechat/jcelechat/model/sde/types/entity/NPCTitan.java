package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class NPCTitan
    extends Entity
{
    public static final NPCTitan.MetaGroup METAGROUP = new NPCTitan.MetaGroup();

    @Override
    public IMetaGroup<NPCTitan> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<NPCTitan>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/NPCTitan.yaml";
        private Map<String, NPCTitan> cache = (null);

        @Override
        public IMetaCategory<? super NPCTitan> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1878;
        }

        @Override
        public String getName() {
            return "NPCTitan";
        }

        @Override
        public synchronized Map<String, NPCTitan> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(NPCTitan.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, NPCTitan> types;
        }
    }
}
