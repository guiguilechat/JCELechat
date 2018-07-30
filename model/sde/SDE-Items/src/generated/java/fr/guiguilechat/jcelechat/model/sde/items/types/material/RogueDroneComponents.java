package fr.guiguilechat.jcelechat.model.sde.items.types.material;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.types.Material;
import org.yaml.snakeyaml.Yaml;

public class RogueDroneComponents
    extends Material
{
    public final static String RESOURCE_PATH = "SDE/items/material/RogueDroneComponents.yaml";
    private static LinkedHashMap<String, RogueDroneComponents> cache = (null);

    @Override
    public int getGroupId() {
        return  886;
    }

    @Override
    public Class<?> getGroup() {
        return RogueDroneComponents.class;
    }

    public static synchronized LinkedHashMap<String, RogueDroneComponents> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(RogueDroneComponents.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, RogueDroneComponents> items;
    }
}
