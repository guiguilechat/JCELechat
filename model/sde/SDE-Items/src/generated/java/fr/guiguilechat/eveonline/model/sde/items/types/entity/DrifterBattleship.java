package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DrifterBattleship
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/DrifterBattleship.yaml";
    private static LinkedHashMap<String, DrifterBattleship> cache = (null);

    @Override
    public int getGroupId() {
        return  1310;
    }

    @Override
    public Class<?> getGroup() {
        return DrifterBattleship.class;
    }

    public static LinkedHashMap<String, DrifterBattleship> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(DrifterBattleship.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, DrifterBattleship> items;
    }
}
