
package fr.guiguilechat.eveonline.model.sde.compiled.items.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Entity;
import org.yaml.snakeyaml.Yaml;

public class ConcordDrone
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/ConcordDrone.yaml";
    private static LinkedHashMap<String, ConcordDrone> cache = (null);

    @Override
    public int getGroupId() {
        return  301;
    }

    @Override
    public Class<?> getGroup() {
        return ConcordDrone.class;
    }

    public static LinkedHashMap<String, ConcordDrone> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(ConcordDrone.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, ConcordDrone> items;

    }

}
