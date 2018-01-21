
package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class DestructibleSentryGun
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/DestructibleSentryGun.yaml";
    private static LinkedHashMap<String, DestructibleSentryGun> cache = (null);

    @Override
    public int getGroupId() {
        return  383;
    }

    @Override
    public Class<?> getGroup() {
        return DestructibleSentryGun.class;
    }

    public static LinkedHashMap<String, DestructibleSentryGun> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(DestructibleSentryGun.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, DestructibleSentryGun> items;

    }

}
