
package fr.guiguilechat.eveonline.model.sde.compiled.items.commodity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Commodity;
import org.yaml.snakeyaml.Yaml;

public class ObsoleteBooks
    extends Commodity
{

    public final static String RESOURCE_PATH = "SDE/items/commodity/ObsoleteBooks.yaml";
    private static LinkedHashMap<String, ObsoleteBooks> cache = (null);

    @Override
    public int getGroupId() {
        return  267;
    }

    @Override
    public Class<?> getGroup() {
        return ObsoleteBooks.class;
    }

    public static LinkedHashMap<String, ObsoleteBooks> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(ObsoleteBooks.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, ObsoleteBooks> items;

    }

}
