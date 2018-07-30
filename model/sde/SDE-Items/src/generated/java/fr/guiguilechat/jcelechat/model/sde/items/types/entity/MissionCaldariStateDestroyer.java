package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionCaldariStateDestroyer
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/MissionCaldariStateDestroyer.yaml";
    private static LinkedHashMap<String, MissionCaldariStateDestroyer> cache = (null);

    @Override
    public int getGroupId() {
        return  676;
    }

    @Override
    public Class<?> getGroup() {
        return MissionCaldariStateDestroyer.class;
    }

    public static synchronized LinkedHashMap<String, MissionCaldariStateDestroyer> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(MissionCaldariStateDestroyer.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, MissionCaldariStateDestroyer> items;
    }
}
