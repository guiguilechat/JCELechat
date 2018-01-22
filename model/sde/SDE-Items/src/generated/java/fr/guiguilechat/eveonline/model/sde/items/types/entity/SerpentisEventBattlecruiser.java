package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class SerpentisEventBattlecruiser
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/SerpentisEventBattlecruiser.yaml";
    private static LinkedHashMap<String, SerpentisEventBattlecruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  1568;
    }

    @Override
    public Class<?> getGroup() {
        return SerpentisEventBattlecruiser.class;
    }

    public static LinkedHashMap<String, SerpentisEventBattlecruiser> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(SerpentisEventBattlecruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, SerpentisEventBattlecruiser> items;
    }
}
