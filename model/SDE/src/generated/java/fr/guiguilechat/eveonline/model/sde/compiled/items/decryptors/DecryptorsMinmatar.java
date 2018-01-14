
package fr.guiguilechat.eveonline.model.sde.compiled.items.decryptors;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Decryptors;
import org.yaml.snakeyaml.Yaml;

public class DecryptorsMinmatar
    extends Decryptors
{

    public final static String RESOURCE_PATH = "SDE/decryptors/DecryptorsMinmatar.yaml";
    private static LinkedHashMap<String, DecryptorsMinmatar> cache = (null);

    @Override
    public int getGroupId() {
        return  729;
    }

    @Override
    public Class<?> getGroup() {
        return DecryptorsMinmatar.class;
    }

    public static LinkedHashMap<String, DecryptorsMinmatar> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, DecryptorsMinmatar> items;

    }

}
