package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceAngelCartelCruiser
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/DeadspaceAngelCartelCruiser.yaml";
    private static LinkedHashMap<String, DeadspaceAngelCartelCruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  595;
    }

    @Override
    public Class<?> getGroup() {
        return DeadspaceAngelCartelCruiser.class;
    }

    public static synchronized LinkedHashMap<String, DeadspaceAngelCartelCruiser> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(DeadspaceAngelCartelCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, DeadspaceAngelCartelCruiser> items;
    }
}
