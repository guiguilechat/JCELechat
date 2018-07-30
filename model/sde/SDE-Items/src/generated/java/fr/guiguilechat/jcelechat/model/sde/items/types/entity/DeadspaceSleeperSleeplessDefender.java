package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceSleeperSleeplessDefender
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/DeadspaceSleeperSleeplessDefender.yaml";
    private static LinkedHashMap<String, DeadspaceSleeperSleeplessDefender> cache = (null);

    @Override
    public int getGroupId() {
        return  982;
    }

    @Override
    public Class<?> getGroup() {
        return DeadspaceSleeperSleeplessDefender.class;
    }

    public static synchronized LinkedHashMap<String, DeadspaceSleeperSleeplessDefender> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(DeadspaceSleeperSleeplessDefender.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, DeadspaceSleeperSleeplessDefender> items;
    }
}
