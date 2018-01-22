package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class NPCBattleship
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/NPCBattleship.yaml";
    private static LinkedHashMap<String, NPCBattleship> cache = (null);

    @Override
    public int getGroupId() {
        return  1814;
    }

    @Override
    public Class<?> getGroup() {
        return NPCBattleship.class;
    }

    public static LinkedHashMap<String, NPCBattleship> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(NPCBattleship.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, NPCBattleship> items;
    }
}
