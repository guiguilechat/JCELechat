package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionKhanidBattleship
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/MissionKhanidBattleship.yaml";
    private static LinkedHashMap<String, MissionKhanidBattleship> cache = (null);

    @Override
    public int getGroupId() {
        return  691;
    }

    @Override
    public Class<?> getGroup() {
        return MissionKhanidBattleship.class;
    }

    public static LinkedHashMap<String, MissionKhanidBattleship> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(MissionKhanidBattleship.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, MissionKhanidBattleship> items;
    }
}
