package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class LargeCollidableStructure
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/LargeCollidableStructure.yaml";
    private static LinkedHashMap<String, LargeCollidableStructure> cache = (null);

    @Override
    public int getGroupId() {
        return  319;
    }

    @Override
    public Class<?> getGroup() {
        return LargeCollidableStructure.class;
    }

    public static synchronized LinkedHashMap<String, LargeCollidableStructure> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(LargeCollidableStructure.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, LargeCollidableStructure> items;
    }
}
