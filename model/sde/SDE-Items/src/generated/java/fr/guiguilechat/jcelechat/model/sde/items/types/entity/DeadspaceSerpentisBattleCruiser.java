package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceSerpentisBattleCruiser
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/DeadspaceSerpentisBattleCruiser.yaml";
    private static LinkedHashMap<String, DeadspaceSerpentisBattleCruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  629;
    }

    @Override
    public Class<?> getGroup() {
        return DeadspaceSerpentisBattleCruiser.class;
    }

    public static synchronized LinkedHashMap<String, DeadspaceSerpentisBattleCruiser> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(DeadspaceSerpentisBattleCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, DeadspaceSerpentisBattleCruiser> items;
    }
}
