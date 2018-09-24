package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class NPCIndustrialCommand
    extends Entity
{
    public final static NPCIndustrialCommand.MetaGroup METAGROUP = new NPCIndustrialCommand.MetaGroup();

    @Override
    public IMetaGroup<NPCIndustrialCommand> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<NPCIndustrialCommand>
    {
        public final static String RESOURCE_PATH = "SDE/items/entity/NPCIndustrialCommand.yaml";
        private Map<String, NPCIndustrialCommand> cache = (null);

        @Override
        public IMetaCategory<? super NPCIndustrialCommand> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1896;
        }

        @Override
        public String getName() {
            return "NPCIndustrialCommand";
        }

        @Override
        public synchronized Map<String, NPCIndustrialCommand> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(NPCIndustrialCommand.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, NPCIndustrialCommand> items;
        }
    }
}
