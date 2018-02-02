package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class ConvoyDrone
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/ConvoyDrone.yaml";
    private static LinkedHashMap<String, ConvoyDrone> cache = (null);

    @Override
    public int getGroupId() {
        return  298;
    }

    @Override
    public Class<?> getGroup() {
        return ConvoyDrone.class;
    }

    public static synchronized LinkedHashMap<String, ConvoyDrone> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(ConvoyDrone.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, ConvoyDrone> items;
    }
}
