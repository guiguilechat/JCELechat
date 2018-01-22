package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceSerpentisBattleship
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/DeadspaceSerpentisBattleship.yaml";
    private static LinkedHashMap<String, DeadspaceSerpentisBattleship> cache = (null);

    @Override
    public int getGroupId() {
        return  630;
    }

    @Override
    public Class<?> getGroup() {
        return DeadspaceSerpentisBattleship.class;
    }

    public static LinkedHashMap<String, DeadspaceSerpentisBattleship> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(DeadspaceSerpentisBattleship.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, DeadspaceSerpentisBattleship> items;
    }
}
