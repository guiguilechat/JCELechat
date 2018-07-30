package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidRogueDroneCommanderBattleship
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidRogueDroneCommanderBattleship.yaml";
    private static LinkedHashMap<String, AsteroidRogueDroneCommanderBattleship> cache = (null);

    @Override
    public int getGroupId() {
        return  844;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidRogueDroneCommanderBattleship.class;
    }

    public static synchronized LinkedHashMap<String, AsteroidRogueDroneCommanderBattleship> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidRogueDroneCommanderBattleship.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, AsteroidRogueDroneCommanderBattleship> items;
    }
}
