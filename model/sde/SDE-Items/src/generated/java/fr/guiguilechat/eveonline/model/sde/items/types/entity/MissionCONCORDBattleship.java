package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionCONCORDBattleship
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/MissionCONCORDBattleship.yaml";
    private static LinkedHashMap<String, MissionCONCORDBattleship> cache = (null);

    @Override
    public int getGroupId() {
        return  697;
    }

    @Override
    public Class<?> getGroup() {
        return MissionCONCORDBattleship.class;
    }

    public static LinkedHashMap<String, MissionCONCORDBattleship> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(MissionCONCORDBattleship.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, MissionCONCORDBattleship> items;
    }
}
