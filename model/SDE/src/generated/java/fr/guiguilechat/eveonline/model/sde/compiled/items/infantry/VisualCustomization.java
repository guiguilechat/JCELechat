
package fr.guiguilechat.eveonline.model.sde.compiled.items.infantry;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Infantry;
import org.yaml.snakeyaml.Yaml;

public class VisualCustomization
    extends Infantry
{

    public final static String RESOURCE_PATH = "SDE/items/infantry/VisualCustomization.yaml";
    private static LinkedHashMap<String, VisualCustomization> cache = (null);

    @Override
    public int getGroupId() {
        return  367594;
    }

    @Override
    public Class<?> getGroup() {
        return VisualCustomization.class;
    }

    public static LinkedHashMap<String, VisualCustomization> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(VisualCustomization.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, VisualCustomization> items;

    }

}
