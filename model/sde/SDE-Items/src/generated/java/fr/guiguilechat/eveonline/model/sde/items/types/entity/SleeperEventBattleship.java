package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class SleeperEventBattleship
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/SleeperEventBattleship.yaml";
    private static LinkedHashMap<String, SleeperEventBattleship> cache = (null);

    @Override
    public int getGroupId() {
        return  1929;
    }

    @Override
    public Class<?> getGroup() {
        return SleeperEventBattleship.class;
    }

    public static LinkedHashMap<String, SleeperEventBattleship> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(SleeperEventBattleship.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, SleeperEventBattleship> items;
    }
}
