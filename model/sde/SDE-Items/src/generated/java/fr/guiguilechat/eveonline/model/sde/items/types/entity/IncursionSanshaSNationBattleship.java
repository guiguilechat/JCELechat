package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class IncursionSanshaSNationBattleship
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/IncursionSanshaSNationBattleship.yaml";
    private static LinkedHashMap<String, IncursionSanshaSNationBattleship> cache = (null);

    @Override
    public int getGroupId() {
        return  1056;
    }

    @Override
    public Class<?> getGroup() {
        return IncursionSanshaSNationBattleship.class;
    }

    public static synchronized LinkedHashMap<String, IncursionSanshaSNationBattleship> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(IncursionSanshaSNationBattleship.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, IncursionSanshaSNationBattleship> items;
    }
}
