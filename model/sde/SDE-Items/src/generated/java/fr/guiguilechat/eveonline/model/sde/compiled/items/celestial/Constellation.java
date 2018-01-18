
package fr.guiguilechat.eveonline.model.sde.compiled.items.celestial;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Celestial;
import org.yaml.snakeyaml.Yaml;

public class Constellation
    extends Celestial
{

    public final static String RESOURCE_PATH = "SDE/items/celestial/Constellation.yaml";
    private static LinkedHashMap<String, Constellation> cache = (null);

    @Override
    public int getGroupId() {
        return  4;
    }

    @Override
    public Class<?> getGroup() {
        return Constellation.class;
    }

    public static LinkedHashMap<String, Constellation> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Constellation.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, Constellation> items;

    }

}
