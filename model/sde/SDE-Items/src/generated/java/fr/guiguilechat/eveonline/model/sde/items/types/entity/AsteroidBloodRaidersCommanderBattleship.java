package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidBloodRaidersCommanderBattleship
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidBloodRaidersCommanderBattleship.yaml";
    private static LinkedHashMap<String, AsteroidBloodRaidersCommanderBattleship> cache = (null);

    @Override
    public int getGroupId() {
        return  849;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidBloodRaidersCommanderBattleship.class;
    }

    public static LinkedHashMap<String, AsteroidBloodRaidersCommanderBattleship> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidBloodRaidersCommanderBattleship.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, AsteroidBloodRaidersCommanderBattleship> items;
    }
}
