package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionContainer
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/MissionContainer.yaml";
    private static Map<String, MissionContainer> cache = (null);

    @Override
    public int getGroupId() {
        return  952;
    }

    @Override
    public Class<?> getGroup() {
        return MissionContainer.class;
    }

    public static synchronized Map<String, MissionContainer> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(MissionContainer.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, MissionContainer> items;
    }
}
