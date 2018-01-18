
package fr.guiguilechat.eveonline.model.sde.compiled.items.celestial;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Celestial;
import org.yaml.snakeyaml.Yaml;

public class Moon
    extends Celestial
{

    public final static String RESOURCE_PATH = "SDE/items/celestial/Moon.yaml";
    private static LinkedHashMap<String, Moon> cache = (null);

    @Override
    public int getGroupId() {
        return  8;
    }

    @Override
    public Class<?> getGroup() {
        return Moon.class;
    }

    public static LinkedHashMap<String, Moon> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Moon.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, Moon> items;

    }

}
