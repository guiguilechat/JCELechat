package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidBloodRaidersBattleCruiser
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidBloodRaidersBattleCruiser.yaml";
    private static LinkedHashMap<String, AsteroidBloodRaidersBattleCruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  578;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidBloodRaidersBattleCruiser.class;
    }

    public static LinkedHashMap<String, AsteroidBloodRaidersBattleCruiser> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidBloodRaidersBattleCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, AsteroidBloodRaidersBattleCruiser> items;
    }
}
