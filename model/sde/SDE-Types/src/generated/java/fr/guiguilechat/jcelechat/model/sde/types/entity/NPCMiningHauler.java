package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class NPCMiningHauler
    extends Entity
{
    public static final NPCMiningHauler.MetaGroup METAGROUP = new NPCMiningHauler.MetaGroup();

    @Override
    public IMetaGroup<NPCMiningHauler> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<NPCMiningHauler>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/NPCMiningHauler.yaml";
        private Map<String, NPCMiningHauler> cache = (null);

        @Override
        public IMetaCategory<? super NPCMiningHauler> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1767;
        }

        @Override
        public String getName() {
            return "NPCMiningHauler";
        }

        @Override
        public synchronized Map<String, NPCMiningHauler> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(NPCMiningHauler.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, NPCMiningHauler> types;
        }
    }
}
