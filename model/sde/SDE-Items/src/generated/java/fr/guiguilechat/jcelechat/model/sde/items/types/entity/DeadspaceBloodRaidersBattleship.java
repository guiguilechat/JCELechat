package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class DeadspaceBloodRaidersBattleship
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/DeadspaceBloodRaidersBattleship.yaml";
    private static LinkedHashMap<String, DeadspaceBloodRaidersBattleship> cache = (null);

    @Override
    public int getGroupId() {
        return  603;
    }

    @Override
    public Class<?> getGroup() {
        return DeadspaceBloodRaidersBattleship.class;
    }

    public static synchronized LinkedHashMap<String, DeadspaceBloodRaidersBattleship> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(DeadspaceBloodRaidersBattleship.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, DeadspaceBloodRaidersBattleship> items;
    }
}
