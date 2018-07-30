package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceBloodRaidersBattleCruiser
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/DeadspaceBloodRaidersBattleCruiser.yaml";
    private static LinkedHashMap<String, DeadspaceBloodRaidersBattleCruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  602;
    }

    @Override
    public Class<?> getGroup() {
        return DeadspaceBloodRaidersBattleCruiser.class;
    }

    public static synchronized LinkedHashMap<String, DeadspaceBloodRaidersBattleCruiser> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(DeadspaceBloodRaidersBattleCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, DeadspaceBloodRaidersBattleCruiser> items;
    }
}
