package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionCaldariStateBattlecruiser
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/MissionCaldariStateBattlecruiser.yaml";
    private static LinkedHashMap<String, MissionCaldariStateBattlecruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  672;
    }

    @Override
    public Class<?> getGroup() {
        return MissionCaldariStateBattlecruiser.class;
    }

    public static synchronized LinkedHashMap<String, MissionCaldariStateBattlecruiser> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(MissionCaldariStateBattlecruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, MissionCaldariStateBattlecruiser> items;
    }
}
