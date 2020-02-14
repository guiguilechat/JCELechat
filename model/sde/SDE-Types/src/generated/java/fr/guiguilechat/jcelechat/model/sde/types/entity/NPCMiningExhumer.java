package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class NPCMiningExhumer
    extends Entity
{
    public static final NPCMiningExhumer.MetaGroup METAGROUP = new NPCMiningExhumer.MetaGroup();

    @Override
    public IMetaGroup<NPCMiningExhumer> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<NPCMiningExhumer>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/NPCMiningExhumer.yaml";
        private Map<String, NPCMiningExhumer> cache = (null);

        @Override
        public IMetaCategory<? super NPCMiningExhumer> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1766;
        }

        @Override
        public String getName() {
            return "NPCMiningExhumer";
        }

        @Override
        public synchronized Map<String, NPCMiningExhumer> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(NPCMiningExhumer.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, NPCMiningExhumer> types;
        }
    }
}
