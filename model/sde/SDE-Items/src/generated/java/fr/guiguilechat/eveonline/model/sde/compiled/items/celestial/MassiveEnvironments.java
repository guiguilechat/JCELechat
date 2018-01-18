
package fr.guiguilechat.eveonline.model.sde.compiled.items.celestial;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Celestial;
import org.yaml.snakeyaml.Yaml;

public class MassiveEnvironments
    extends Celestial
{

    public final static String RESOURCE_PATH = "SDE/items/celestial/MassiveEnvironments.yaml";
    private static LinkedHashMap<String, MassiveEnvironments> cache = (null);

    @Override
    public int getGroupId() {
        return  1882;
    }

    @Override
    public Class<?> getGroup() {
        return MassiveEnvironments.class;
    }

    public static LinkedHashMap<String, MassiveEnvironments> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(MassiveEnvironments.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, MassiveEnvironments> items;

    }

}
