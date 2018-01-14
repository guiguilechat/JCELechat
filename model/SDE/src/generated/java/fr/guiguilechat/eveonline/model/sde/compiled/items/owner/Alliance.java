
package fr.guiguilechat.eveonline.model.sde.compiled.items.owner;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Owner;
import org.yaml.snakeyaml.Yaml;

public class Alliance
    extends Owner
{

    public final static String RESOURCE_PATH = "SDE/owner/Alliance.yaml";
    private static LinkedHashMap<String, Alliance> cache = (null);

    @Override
    public int getGroupId() {
        return  32;
    }

    @Override
    public Class<?> getGroup() {
        return Alliance.class;
    }

    public static LinkedHashMap<String, Alliance> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, Alliance> items;

    }

}
