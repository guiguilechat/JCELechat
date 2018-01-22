package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class RoamingBloodRaiderBattlecruiser
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/RoamingBloodRaiderBattlecruiser.yaml";
    private static LinkedHashMap<String, RoamingBloodRaiderBattlecruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  1736;
    }

    @Override
    public Class<?> getGroup() {
        return RoamingBloodRaiderBattlecruiser.class;
    }

    public static LinkedHashMap<String, RoamingBloodRaiderBattlecruiser> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(RoamingBloodRaiderBattlecruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, RoamingBloodRaiderBattlecruiser> items;
    }
}
