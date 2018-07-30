package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidSanshaSNationCommanderBattleship
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidSanshaSNationCommanderBattleship.yaml";
    private static LinkedHashMap<String, AsteroidSanshaSNationCommanderBattleship> cache = (null);

    @Override
    public int getGroupId() {
        return  851;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidSanshaSNationCommanderBattleship.class;
    }

    public static synchronized LinkedHashMap<String, AsteroidSanshaSNationCommanderBattleship> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidSanshaSNationCommanderBattleship.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, AsteroidSanshaSNationCommanderBattleship> items;
    }
}
