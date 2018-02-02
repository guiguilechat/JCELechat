package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionKhanidCruiser
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/MissionKhanidCruiser.yaml";
    private static LinkedHashMap<String, MissionKhanidCruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  689;
    }

    @Override
    public Class<?> getGroup() {
        return MissionKhanidCruiser.class;
    }

    public static synchronized LinkedHashMap<String, MissionKhanidCruiser> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(MissionKhanidCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, MissionKhanidCruiser> items;
    }
}
