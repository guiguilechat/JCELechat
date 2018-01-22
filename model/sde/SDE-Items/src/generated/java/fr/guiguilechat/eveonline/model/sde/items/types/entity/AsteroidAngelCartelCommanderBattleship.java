package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidAngelCartelCommanderBattleship
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidAngelCartelCommanderBattleship.yaml";
    private static LinkedHashMap<String, AsteroidAngelCartelCommanderBattleship> cache = (null);

    @Override
    public int getGroupId() {
        return  848;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidAngelCartelCommanderBattleship.class;
    }

    public static LinkedHashMap<String, AsteroidAngelCartelCommanderBattleship> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidAngelCartelCommanderBattleship.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, AsteroidAngelCartelCommanderBattleship> items;
    }
}
