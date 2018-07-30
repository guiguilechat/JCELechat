package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceSleeperAwakenedPatroller
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/DeadspaceSleeperAwakenedPatroller.yaml";
    private static LinkedHashMap<String, DeadspaceSleeperAwakenedPatroller> cache = (null);

    @Override
    public int getGroupId() {
        return  985;
    }

    @Override
    public Class<?> getGroup() {
        return DeadspaceSleeperAwakenedPatroller.class;
    }

    public static synchronized LinkedHashMap<String, DeadspaceSleeperAwakenedPatroller> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(DeadspaceSleeperAwakenedPatroller.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, DeadspaceSleeperAwakenedPatroller> items;
    }
}
