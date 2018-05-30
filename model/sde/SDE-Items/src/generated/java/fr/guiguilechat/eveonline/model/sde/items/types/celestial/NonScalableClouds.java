package fr.guiguilechat.eveonline.model.sde.items.types.celestial;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Celestial;
import org.yaml.snakeyaml.Yaml;

public class NonScalableClouds
    extends Celestial
{
    public final static String RESOURCE_PATH = "SDE/items/celestial/NonScalableClouds.yaml";
    private static LinkedHashMap<String, NonScalableClouds> cache = (null);

    @Override
    public int getGroupId() {
        return  1980;
    }

    @Override
    public Class<?> getGroup() {
        return NonScalableClouds.class;
    }

    public static synchronized LinkedHashMap<String, NonScalableClouds> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(NonScalableClouds.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, NonScalableClouds> items;
    }
}
