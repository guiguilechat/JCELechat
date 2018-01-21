
package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class ScatterContainer
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/ScatterContainer.yaml";
    private static LinkedHashMap<String, ScatterContainer> cache = (null);

    @Override
    public int getGroupId() {
        return  1207;
    }

    @Override
    public Class<?> getGroup() {
        return ScatterContainer.class;
    }

    public static LinkedHashMap<String, ScatterContainer> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(ScatterContainer.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, ScatterContainer> items;

    }

}
