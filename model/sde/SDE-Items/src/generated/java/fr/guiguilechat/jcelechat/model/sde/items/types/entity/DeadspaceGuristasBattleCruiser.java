package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceGuristasBattleCruiser
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/DeadspaceGuristasBattleCruiser.yaml";
    private static LinkedHashMap<String, DeadspaceGuristasBattleCruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  611;
    }

    @Override
    public Class<?> getGroup() {
        return DeadspaceGuristasBattleCruiser.class;
    }

    public static synchronized LinkedHashMap<String, DeadspaceGuristasBattleCruiser> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(DeadspaceGuristasBattleCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, DeadspaceGuristasBattleCruiser> items;
    }
}
