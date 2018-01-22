package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionFactionBattleship
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/MissionFactionBattleship.yaml";
    private static LinkedHashMap<String, MissionFactionBattleship> cache = (null);

    @Override
    public int getGroupId() {
        return  924;
    }

    @Override
    public Class<?> getGroup() {
        return MissionFactionBattleship.class;
    }

    public static LinkedHashMap<String, MissionFactionBattleship> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(MissionFactionBattleship.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, MissionFactionBattleship> items;
    }
}
