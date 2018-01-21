
package fr.guiguilechat.eveonline.model.sde.items.types.celestial;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Celestial;
import org.yaml.snakeyaml.Yaml;

public class Sun
    extends Celestial
{

    public final static String RESOURCE_PATH = "SDE/items/celestial/Sun.yaml";
    private static LinkedHashMap<String, Sun> cache = (null);

    @Override
    public int getGroupId() {
        return  6;
    }

    @Override
    public Class<?> getGroup() {
        return Sun.class;
    }

    public static LinkedHashMap<String, Sun> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Sun.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, Sun> items;

    }

}
