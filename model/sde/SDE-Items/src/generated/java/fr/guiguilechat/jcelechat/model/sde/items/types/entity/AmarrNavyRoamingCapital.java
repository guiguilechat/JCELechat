package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class AmarrNavyRoamingCapital
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/AmarrNavyRoamingCapital.yaml";
    private static LinkedHashMap<String, AmarrNavyRoamingCapital> cache = (null);

    @Override
    public int getGroupId() {
        return  1412;
    }

    @Override
    public Class<?> getGroup() {
        return AmarrNavyRoamingCapital.class;
    }

    public static synchronized LinkedHashMap<String, AmarrNavyRoamingCapital> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AmarrNavyRoamingCapital.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, AmarrNavyRoamingCapital> items;
    }
}
