package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceSerpentisDestroyer
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/DeadspaceSerpentisDestroyer.yaml";
    private static LinkedHashMap<String, DeadspaceSerpentisDestroyer> cache = (null);

    @Override
    public int getGroupId() {
        return  632;
    }

    @Override
    public Class<?> getGroup() {
        return DeadspaceSerpentisDestroyer.class;
    }

    public static synchronized LinkedHashMap<String, DeadspaceSerpentisDestroyer> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(DeadspaceSerpentisDestroyer.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, DeadspaceSerpentisDestroyer> items;
    }
}
