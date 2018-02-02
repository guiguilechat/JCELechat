package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionContainer
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/MissionContainer.yaml";
    private static LinkedHashMap<String, MissionContainer> cache = (null);

    @Override
    public int getGroupId() {
        return  952;
    }

    @Override
    public Class<?> getGroup() {
        return MissionContainer.class;
    }

    public static synchronized LinkedHashMap<String, MissionContainer> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(MissionContainer.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, MissionContainer> items;
    }
}
