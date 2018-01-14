
package fr.guiguilechat.eveonline.model.sde.compiled.items.decryptors;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Decryptors;
import org.yaml.snakeyaml.Yaml;

public class GenericDecryptor
    extends Decryptors
{

    public final static String RESOURCE_PATH = "SDE/decryptors/GenericDecryptor.yaml";
    private static LinkedHashMap<String, GenericDecryptor> cache = (null);

    @Override
    public int getGroupId() {
        return  1304;
    }

    @Override
    public Class<?> getGroup() {
        return GenericDecryptor.class;
    }

    public static LinkedHashMap<String, GenericDecryptor> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, GenericDecryptor> items;

    }

}
