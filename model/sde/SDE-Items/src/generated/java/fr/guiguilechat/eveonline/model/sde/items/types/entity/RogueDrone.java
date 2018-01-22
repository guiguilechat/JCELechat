package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class RogueDrone
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/RogueDrone.yaml";
    private static LinkedHashMap<String, RogueDrone> cache = (null);

    @Override
    public int getGroupId() {
        return  287;
    }

    @Override
    public Class<?> getGroup() {
        return RogueDrone.class;
    }

    public static LinkedHashMap<String, RogueDrone> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(RogueDrone.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, RogueDrone> items;
    }
}
