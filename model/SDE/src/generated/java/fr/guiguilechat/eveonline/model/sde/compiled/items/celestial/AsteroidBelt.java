
package fr.guiguilechat.eveonline.model.sde.compiled.items.celestial;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Celestial;
import org.yaml.snakeyaml.Yaml;

public class AsteroidBelt
    extends Celestial
{

    public final static String RESOURCE_PATH = "SDE/items/celestial/AsteroidBelt.yaml";
    private static LinkedHashMap<String, AsteroidBelt> cache = (null);

    @Override
    public int getGroupId() {
        return  9;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidBelt.class;
    }

    public static LinkedHashMap<String, AsteroidBelt> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidBelt.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, AsteroidBelt> items;

    }

}
