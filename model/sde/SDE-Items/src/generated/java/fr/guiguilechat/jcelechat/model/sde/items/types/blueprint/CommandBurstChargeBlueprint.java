package fr.guiguilechat.jcelechat.model.sde.items.types.blueprint;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.types.Blueprint;
import org.yaml.snakeyaml.Yaml;

public class CommandBurstChargeBlueprint
    extends Blueprint
{
    public static final CommandBurstChargeBlueprint.MetaGroup METAGROUP = new CommandBurstChargeBlueprint.MetaGroup();

    @Override
    public IMetaGroup<CommandBurstChargeBlueprint> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<CommandBurstChargeBlueprint>
    {
        public static final String RESOURCE_PATH = "SDE/items/blueprint/CommandBurstChargeBlueprint.yaml";
        private Map<String, CommandBurstChargeBlueprint> cache = (null);

        @Override
        public IMetaCategory<? super CommandBurstChargeBlueprint> category() {
            return Blueprint.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1810;
        }

        @Override
        public String getName() {
            return "CommandBurstChargeBlueprint";
        }

        @Override
        public synchronized Map<String, CommandBurstChargeBlueprint> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(CommandBurstChargeBlueprint.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, CommandBurstChargeBlueprint> items;
        }
    }
}
