package fr.guiguilechat.eveonline.model.sde.items.types.lights;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Lights;
import org.yaml.snakeyaml.Yaml;

public class PointLights
    extends Lights
{
    public final static String RESOURCE_PATH = "SDE/items/lights/PointLights.yaml";
    private static LinkedHashMap<String, PointLights> cache = (null);

    @Override
    public int getGroupId() {
        return  1110;
    }

    @Override
    public Class<?> getGroup() {
        return PointLights.class;
    }

    public static LinkedHashMap<String, PointLights> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(PointLights.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, PointLights> items;
    }
}
