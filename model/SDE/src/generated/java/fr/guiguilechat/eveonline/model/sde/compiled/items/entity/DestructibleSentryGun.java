
package fr.guiguilechat.eveonline.model.sde.compiled.items.entity;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Entity;
import org.yaml.snakeyaml.Yaml;

public class DestructibleSentryGun
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/entity/DestructibleSentryGun.yaml";
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
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, DestructibleSentryGun> items;

    }

}
