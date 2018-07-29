package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;

public class FWGallenteFederationFrigate
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/FWGallenteFederationFrigate.yaml";
    private static LinkedHashMap<String, FWGallenteFederationFrigate> cache = (null);

    @Override
    public int getGroupId() {
        return  1168;
    }

    @Override
    public Class<?> getGroup() {
        return FWGallenteFederationFrigate.class;
    }

    public static synchronized LinkedHashMap<String, FWGallenteFederationFrigate> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(FWGallenteFederationFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, FWGallenteFederationFrigate> items;
    }
}
