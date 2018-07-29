package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class DeadspaceOverseerSBelongings
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/DeadspaceOverseerSBelongings.yaml";
    private static LinkedHashMap<String, DeadspaceOverseerSBelongings> cache = (null);

    @Override
    public int getGroupId() {
        return  496;
    }

    @Override
    public Class<?> getGroup() {
        return DeadspaceOverseerSBelongings.class;
    }

    public static synchronized LinkedHashMap<String, DeadspaceOverseerSBelongings> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(DeadspaceOverseerSBelongings.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, DeadspaceOverseerSBelongings> items;
    }
}
