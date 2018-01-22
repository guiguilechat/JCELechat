package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidGuristasOfficer
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidGuristasOfficer.yaml";
    private static LinkedHashMap<String, AsteroidGuristasOfficer> cache = (null);

    @Override
    public int getGroupId() {
        return  564;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidGuristasOfficer.class;
    }

    public static LinkedHashMap<String, AsteroidGuristasOfficer> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidGuristasOfficer.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, AsteroidGuristasOfficer> items;
    }
}
