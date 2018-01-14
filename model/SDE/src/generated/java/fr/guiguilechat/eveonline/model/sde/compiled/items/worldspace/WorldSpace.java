
package fr.guiguilechat.eveonline.model.sde.compiled.items.worldspace;

import java.io.FileReader;
import java.util.LinkedHashMap;
import org.yaml.snakeyaml.Yaml;

public class WorldSpace
    extends fr.guiguilechat.eveonline.model.sde.compiled.items.WorldSpace
{

    public final static String RESOURCE_PATH = "SDE/worldspace/WorldSpace.yaml";
    private static LinkedHashMap<String, WorldSpace> cache = (null);

    @Override
    public int getGroupId() {
        return  935;
    }

    @Override
    public Class<?> getGroup() {
        return WorldSpace.class;
    }

    public static LinkedHashMap<String, WorldSpace> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, WorldSpace> items;

    }

}
