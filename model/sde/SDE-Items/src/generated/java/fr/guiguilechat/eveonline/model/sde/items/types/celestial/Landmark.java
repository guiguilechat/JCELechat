package fr.guiguilechat.eveonline.model.sde.items.types.celestial;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Celestial;
import org.yaml.snakeyaml.Yaml;

public class Landmark
    extends Celestial
{
    public final static String RESOURCE_PATH = "SDE/items/celestial/Landmark.yaml";
    private static LinkedHashMap<String, Landmark> cache = (null);

    @Override
    public int getGroupId() {
        return  318;
    }

    @Override
    public Class<?> getGroup() {
        return Landmark.class;
    }

    public static synchronized LinkedHashMap<String, Landmark> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Landmark.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, Landmark> items;
    }
}
