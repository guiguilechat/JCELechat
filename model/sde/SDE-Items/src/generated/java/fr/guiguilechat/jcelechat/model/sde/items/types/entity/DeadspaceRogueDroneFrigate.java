package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class DeadspaceRogueDroneFrigate
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/DeadspaceRogueDroneFrigate.yaml";
    private static LinkedHashMap<String, DeadspaceRogueDroneFrigate> cache = (null);

    @Override
    public int getGroupId() {
        return  805;
    }

    @Override
    public Class<?> getGroup() {
        return DeadspaceRogueDroneFrigate.class;
    }

    public static synchronized LinkedHashMap<String, DeadspaceRogueDroneFrigate> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(DeadspaceRogueDroneFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, DeadspaceRogueDroneFrigate> items;
    }
}
