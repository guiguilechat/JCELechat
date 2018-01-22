package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AmarrEventBattlecruiser
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/AmarrEventBattlecruiser.yaml";
    private static LinkedHashMap<String, AmarrEventBattlecruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  1762;
    }

    @Override
    public Class<?> getGroup() {
        return AmarrEventBattlecruiser.class;
    }

    public static LinkedHashMap<String, AmarrEventBattlecruiser> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AmarrEventBattlecruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, AmarrEventBattlecruiser> items;
    }
}
