package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionMorduFrigate
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/MissionMorduFrigate.yaml";
    private static LinkedHashMap<String, MissionMorduFrigate> cache = (null);

    @Override
    public int getGroupId() {
        return  699;
    }

    @Override
    public Class<?> getGroup() {
        return MissionMorduFrigate.class;
    }

    public static synchronized LinkedHashMap<String, MissionMorduFrigate> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(MissionMorduFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, MissionMorduFrigate> items;
    }
}
