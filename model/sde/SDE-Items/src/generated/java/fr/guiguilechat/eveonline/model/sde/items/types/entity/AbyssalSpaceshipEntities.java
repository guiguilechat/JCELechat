package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AbyssalSpaceshipEntities
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/AbyssalSpaceshipEntities.yaml";
    private static LinkedHashMap<String, AbyssalSpaceshipEntities> cache = (null);

    @Override
    public int getGroupId() {
        return  1982;
    }

    @Override
    public Class<?> getGroup() {
        return AbyssalSpaceshipEntities.class;
    }

    public static synchronized LinkedHashMap<String, AbyssalSpaceshipEntities> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AbyssalSpaceshipEntities.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, AbyssalSpaceshipEntities> items;
    }
}
