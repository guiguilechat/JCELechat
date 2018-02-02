package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class NPCCruiser
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/NPCCruiser.yaml";
    private static LinkedHashMap<String, NPCCruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  1813;
    }

    @Override
    public Class<?> getGroup() {
        return NPCCruiser.class;
    }

    public static synchronized LinkedHashMap<String, NPCCruiser> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(NPCCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, NPCCruiser> items;
    }
}
