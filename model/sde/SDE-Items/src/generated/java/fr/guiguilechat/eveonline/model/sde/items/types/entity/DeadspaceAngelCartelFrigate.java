package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceAngelCartelFrigate
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/DeadspaceAngelCartelFrigate.yaml";
    private static LinkedHashMap<String, DeadspaceAngelCartelFrigate> cache = (null);

    @Override
    public int getGroupId() {
        return  597;
    }

    @Override
    public Class<?> getGroup() {
        return DeadspaceAngelCartelFrigate.class;
    }

    public static LinkedHashMap<String, DeadspaceAngelCartelFrigate> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(DeadspaceAngelCartelFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, DeadspaceAngelCartelFrigate> items;
    }
}
