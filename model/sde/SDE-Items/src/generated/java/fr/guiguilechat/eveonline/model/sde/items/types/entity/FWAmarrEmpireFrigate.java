package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class FWAmarrEmpireFrigate
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/FWAmarrEmpireFrigate.yaml";
    private static LinkedHashMap<String, FWAmarrEmpireFrigate> cache = (null);

    @Override
    public int getGroupId() {
        return  1169;
    }

    @Override
    public Class<?> getGroup() {
        return FWAmarrEmpireFrigate.class;
    }

    public static synchronized LinkedHashMap<String, FWAmarrEmpireFrigate> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(FWAmarrEmpireFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, FWAmarrEmpireFrigate> items;
    }
}
