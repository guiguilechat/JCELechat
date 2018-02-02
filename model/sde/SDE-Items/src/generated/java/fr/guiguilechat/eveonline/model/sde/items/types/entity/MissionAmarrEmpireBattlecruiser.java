package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionAmarrEmpireBattlecruiser
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/MissionAmarrEmpireBattlecruiser.yaml";
    private static LinkedHashMap<String, MissionAmarrEmpireBattlecruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  666;
    }

    @Override
    public Class<?> getGroup() {
        return MissionAmarrEmpireBattlecruiser.class;
    }

    public static synchronized LinkedHashMap<String, MissionAmarrEmpireBattlecruiser> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(MissionAmarrEmpireBattlecruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, MissionAmarrEmpireBattlecruiser> items;
    }
}
