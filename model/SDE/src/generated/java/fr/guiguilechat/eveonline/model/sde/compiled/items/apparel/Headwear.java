
package fr.guiguilechat.eveonline.model.sde.compiled.items.apparel;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Apparel;
import org.yaml.snakeyaml.Yaml;

public class Headwear
    extends Apparel
{

    public final static String RESOURCE_PATH = "SDE/items/apparel/Headwear.yaml";
    private static LinkedHashMap<String, Headwear> cache = (null);

    @Override
    public int getGroupId() {
        return  1092;
    }

    @Override
    public Class<?> getGroup() {
        return Headwear.class;
    }

    public static LinkedHashMap<String, Headwear> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Headwear.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, Headwear> items;

    }

}
