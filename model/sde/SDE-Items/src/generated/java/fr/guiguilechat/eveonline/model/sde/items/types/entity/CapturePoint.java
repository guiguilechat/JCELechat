package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class CapturePoint
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/CapturePoint.yaml";
    private static LinkedHashMap<String, CapturePoint> cache = (null);

    @Override
    public int getGroupId() {
        return  922;
    }

    @Override
    public Class<?> getGroup() {
        return CapturePoint.class;
    }

    public static LinkedHashMap<String, CapturePoint> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(CapturePoint.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, CapturePoint> items;
    }
}
