package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class DeadspaceSerpentisFrigate
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/DeadspaceSerpentisFrigate.yaml";
    private static LinkedHashMap<String, DeadspaceSerpentisFrigate> cache = (null);

    @Override
    public int getGroupId() {
        return  633;
    }

    @Override
    public Class<?> getGroup() {
        return DeadspaceSerpentisFrigate.class;
    }

    public static synchronized LinkedHashMap<String, DeadspaceSerpentisFrigate> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(DeadspaceSerpentisFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, DeadspaceSerpentisFrigate> items;
    }
}
