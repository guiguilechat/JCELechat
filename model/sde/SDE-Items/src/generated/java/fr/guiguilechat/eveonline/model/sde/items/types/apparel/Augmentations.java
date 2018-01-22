package fr.guiguilechat.eveonline.model.sde.items.types.apparel;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Apparel;
import org.yaml.snakeyaml.Yaml;

public class Augmentations
    extends Apparel
{
    public final static String RESOURCE_PATH = "SDE/items/apparel/Augmentations.yaml";
    private static LinkedHashMap<String, Augmentations> cache = (null);

    @Override
    public int getGroupId() {
        return  1670;
    }

    @Override
    public Class<?> getGroup() {
        return Augmentations.class;
    }

    public static LinkedHashMap<String, Augmentations> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Augmentations.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, Augmentations> items;
    }
}
