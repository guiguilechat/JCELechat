
package fr.guiguilechat.eveonline.model.sde.items.types.celestial;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Celestial;
import org.yaml.snakeyaml.Yaml;

public class Stargate
    extends Celestial
{

    public final static String RESOURCE_PATH = "SDE/items/celestial/Stargate.yaml";
    private static LinkedHashMap<String, Stargate> cache = (null);

    @Override
    public int getGroupId() {
        return  10;
    }

    @Override
    public Class<?> getGroup() {
        return Stargate.class;
    }

    public static LinkedHashMap<String, Stargate> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Stargate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, Stargate> items;

    }

}
