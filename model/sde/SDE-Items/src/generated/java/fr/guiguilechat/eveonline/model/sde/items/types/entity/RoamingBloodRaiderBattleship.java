package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class RoamingBloodRaiderBattleship
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/RoamingBloodRaiderBattleship.yaml";
    private static LinkedHashMap<String, RoamingBloodRaiderBattleship> cache = (null);

    @Override
    public int getGroupId() {
        return  1737;
    }

    @Override
    public Class<?> getGroup() {
        return RoamingBloodRaiderBattleship.class;
    }

    public static LinkedHashMap<String, RoamingBloodRaiderBattleship> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(RoamingBloodRaiderBattleship.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, RoamingBloodRaiderBattleship> items;
    }
}
