package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class DeadspaceGuristasCruiser
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/DeadspaceGuristasCruiser.yaml";
    private static LinkedHashMap<String, DeadspaceGuristasCruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  613;
    }

    @Override
    public Class<?> getGroup() {
        return DeadspaceGuristasCruiser.class;
    }

    public static synchronized LinkedHashMap<String, DeadspaceGuristasCruiser> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(DeadspaceGuristasCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, DeadspaceGuristasCruiser> items;
    }
}
