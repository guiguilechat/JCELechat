package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidGuristasCommanderDestroyer
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidGuristasCommanderDestroyer.yaml";
    private static LinkedHashMap<String, AsteroidGuristasCommanderDestroyer> cache = (null);

    @Override
    public int getGroupId() {
        return  799;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidGuristasCommanderDestroyer.class;
    }

    public static synchronized LinkedHashMap<String, AsteroidGuristasCommanderDestroyer> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidGuristasCommanderDestroyer.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, AsteroidGuristasCommanderDestroyer> items;
    }
}
