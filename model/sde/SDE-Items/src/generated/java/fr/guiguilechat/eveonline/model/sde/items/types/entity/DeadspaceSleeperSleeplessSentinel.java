package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceSleeperSleeplessSentinel
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/DeadspaceSleeperSleeplessSentinel.yaml";
    private static LinkedHashMap<String, DeadspaceSleeperSleeplessSentinel> cache = (null);

    @Override
    public int getGroupId() {
        return  959;
    }

    @Override
    public Class<?> getGroup() {
        return DeadspaceSleeperSleeplessSentinel.class;
    }

    public static synchronized LinkedHashMap<String, DeadspaceSleeperSleeplessSentinel> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(DeadspaceSleeperSleeplessSentinel.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, DeadspaceSleeperSleeplessSentinel> items;
    }
}
