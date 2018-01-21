
package fr.guiguilechat.eveonline.model.sde.items.types.abstrct;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Abstrct;
import org.yaml.snakeyaml.Yaml;

public class Miscellaneous
    extends Abstrct
{

    public final static String RESOURCE_PATH = "SDE/items/abstrct/Miscellaneous.yaml";
    private static LinkedHashMap<String, Miscellaneous> cache = (null);

    @Override
    public int getGroupId() {
        return  1319;
    }

    @Override
    public Class<?> getGroup() {
        return Miscellaneous.class;
    }

    public static LinkedHashMap<String, Miscellaneous> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Miscellaneous.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, Miscellaneous> items;

    }

}
