
package fr.guiguilechat.eveonline.model.sde.items.types.infantry;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Infantry;
import org.yaml.snakeyaml.Yaml;

public class Services
    extends Infantry
{

    public final static String RESOURCE_PATH = "SDE/items/infantry/Services.yaml";
    private static LinkedHashMap<String, Services> cache = (null);

    @Override
    public int getGroupId() {
        return  367487;
    }

    @Override
    public Class<?> getGroup() {
        return Services.class;
    }

    public static LinkedHashMap<String, Services> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Services.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, Services> items;

    }

}
