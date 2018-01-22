package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class NPCTitan
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/NPCTitan.yaml";
    private static LinkedHashMap<String, NPCTitan> cache = (null);

    @Override
    public int getGroupId() {
        return  1878;
    }

    @Override
    public Class<?> getGroup() {
        return NPCTitan.class;
    }

    public static LinkedHashMap<String, NPCTitan> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(NPCTitan.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, NPCTitan> items;
    }
}
