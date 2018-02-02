package fr.guiguilechat.eveonline.model.sde.items.types.celestial;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Celestial;
import org.yaml.snakeyaml.Yaml;

public class SolarSystem
    extends Celestial
{
    public final static String RESOURCE_PATH = "SDE/items/celestial/SolarSystem.yaml";
    private static LinkedHashMap<String, SolarSystem> cache = (null);

    @Override
    public int getGroupId() {
        return  5;
    }

    @Override
    public Class<?> getGroup() {
        return SolarSystem.class;
    }

    public static synchronized LinkedHashMap<String, SolarSystem> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(SolarSystem.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, SolarSystem> items;
    }
}
