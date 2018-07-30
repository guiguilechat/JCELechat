package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class FactionDrone
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/FactionDrone.yaml";
    private static LinkedHashMap<String, FactionDrone> cache = (null);

    @Override
    public int getGroupId() {
        return  288;
    }

    @Override
    public Class<?> getGroup() {
        return FactionDrone.class;
    }

    public static synchronized LinkedHashMap<String, FactionDrone> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(FactionDrone.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, FactionDrone> items;
    }
}
