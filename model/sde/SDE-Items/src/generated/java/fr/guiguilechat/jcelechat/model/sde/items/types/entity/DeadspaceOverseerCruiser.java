package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceOverseerCruiser
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/DeadspaceOverseerCruiser.yaml";
    private static LinkedHashMap<String, DeadspaceOverseerCruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  820;
    }

    @Override
    public Class<?> getGroup() {
        return DeadspaceOverseerCruiser.class;
    }

    public static synchronized LinkedHashMap<String, DeadspaceOverseerCruiser> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(DeadspaceOverseerCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, DeadspaceOverseerCruiser> items;
    }
}
