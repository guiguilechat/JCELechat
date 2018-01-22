package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class RoamingSleepersCruiser
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/RoamingSleepersCruiser.yaml";
    private static LinkedHashMap<String, RoamingSleepersCruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  1307;
    }

    @Override
    public Class<?> getGroup() {
        return RoamingSleepersCruiser.class;
    }

    public static LinkedHashMap<String, RoamingSleepersCruiser> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(RoamingSleepersCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, RoamingSleepersCruiser> items;
    }
}
