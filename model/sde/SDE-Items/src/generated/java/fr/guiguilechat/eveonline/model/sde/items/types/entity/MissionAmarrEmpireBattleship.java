package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionAmarrEmpireBattleship
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/MissionAmarrEmpireBattleship.yaml";
    private static LinkedHashMap<String, MissionAmarrEmpireBattleship> cache = (null);

    @Override
    public int getGroupId() {
        return  667;
    }

    @Override
    public Class<?> getGroup() {
        return MissionAmarrEmpireBattleship.class;
    }

    public static LinkedHashMap<String, MissionAmarrEmpireBattleship> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(MissionAmarrEmpireBattleship.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, MissionAmarrEmpireBattleship> items;
    }
}
