package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DestructibleAgentsInSpace
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/DestructibleAgentsInSpace.yaml";
    private static LinkedHashMap<String, DestructibleAgentsInSpace> cache = (null);

    @Override
    public int getGroupId() {
        return  715;
    }

    @Override
    public Class<?> getGroup() {
        return DestructibleAgentsInSpace.class;
    }

    public static synchronized LinkedHashMap<String, DestructibleAgentsInSpace> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(DestructibleAgentsInSpace.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, DestructibleAgentsInSpace> items;
    }
}
