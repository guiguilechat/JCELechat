package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceOverseerSSentry
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/DeadspaceOverseerSSentry.yaml";
    private static LinkedHashMap<String, DeadspaceOverseerSSentry> cache = (null);

    @Override
    public int getGroupId() {
        return  495;
    }

    @Override
    public Class<?> getGroup() {
        return DeadspaceOverseerSSentry.class;
    }

    public static LinkedHashMap<String, DeadspaceOverseerSSentry> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(DeadspaceOverseerSSentry.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, DeadspaceOverseerSSentry> items;
    }
}