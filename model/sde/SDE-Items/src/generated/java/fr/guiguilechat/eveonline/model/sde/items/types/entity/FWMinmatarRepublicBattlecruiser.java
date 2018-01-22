package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class FWMinmatarRepublicBattlecruiser
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/FWMinmatarRepublicBattlecruiser.yaml";
    private static LinkedHashMap<String, FWMinmatarRepublicBattlecruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  1186;
    }

    @Override
    public Class<?> getGroup() {
        return FWMinmatarRepublicBattlecruiser.class;
    }

    public static LinkedHashMap<String, FWMinmatarRepublicBattlecruiser> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(FWMinmatarRepublicBattlecruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, FWMinmatarRepublicBattlecruiser> items;
    }
}
