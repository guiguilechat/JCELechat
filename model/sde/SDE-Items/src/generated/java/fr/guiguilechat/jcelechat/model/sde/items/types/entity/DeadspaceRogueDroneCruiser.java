package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class DeadspaceRogueDroneCruiser
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/DeadspaceRogueDroneCruiser.yaml";
    private static LinkedHashMap<String, DeadspaceRogueDroneCruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  803;
    }

    @Override
    public Class<?> getGroup() {
        return DeadspaceRogueDroneCruiser.class;
    }

    public static synchronized LinkedHashMap<String, DeadspaceRogueDroneCruiser> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(DeadspaceRogueDroneCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, DeadspaceRogueDroneCruiser> items;
    }
}
