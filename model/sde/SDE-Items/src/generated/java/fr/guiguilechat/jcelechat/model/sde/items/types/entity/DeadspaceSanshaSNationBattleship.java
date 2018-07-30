package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DeadspaceSanshaSNationBattleship
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/DeadspaceSanshaSNationBattleship.yaml";
    private static LinkedHashMap<String, DeadspaceSanshaSNationBattleship> cache = (null);

    @Override
    public int getGroupId() {
        return  621;
    }

    @Override
    public Class<?> getGroup() {
        return DeadspaceSanshaSNationBattleship.class;
    }

    public static synchronized LinkedHashMap<String, DeadspaceSanshaSNationBattleship> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(DeadspaceSanshaSNationBattleship.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, DeadspaceSanshaSNationBattleship> items;
    }
}
