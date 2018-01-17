
package fr.guiguilechat.eveonline.model.sde.compiled.items.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Entity;
import org.yaml.snakeyaml.Yaml;

public class PirateDrone
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/PirateDrone.yaml";
    private static LinkedHashMap<String, PirateDrone> cache = (null);

    @Override
    public int getGroupId() {
        return  185;
    }

    @Override
    public Class<?> getGroup() {
        return PirateDrone.class;
    }

    public static LinkedHashMap<String, PirateDrone> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(PirateDrone.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, PirateDrone> items;

    }

}
