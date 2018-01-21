
package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class SpawnContainer
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/SpawnContainer.yaml";
    private static LinkedHashMap<String, SpawnContainer> cache = (null);

    @Override
    public int getGroupId() {
        return  306;
    }

    @Override
    public Class<?> getGroup() {
        return SpawnContainer.class;
    }

    public static LinkedHashMap<String, SpawnContainer> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(SpawnContainer.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, SpawnContainer> items;

    }

}
