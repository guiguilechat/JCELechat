package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class RoamingBloodRaiderFrigate
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/RoamingBloodRaiderFrigate.yaml";
    private static LinkedHashMap<String, RoamingBloodRaiderFrigate> cache = (null);

    @Override
    public int getGroupId() {
        return  1734;
    }

    @Override
    public Class<?> getGroup() {
        return RoamingBloodRaiderFrigate.class;
    }

    public static synchronized LinkedHashMap<String, RoamingBloodRaiderFrigate> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(RoamingBloodRaiderFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, RoamingBloodRaiderFrigate> items;
    }
}
