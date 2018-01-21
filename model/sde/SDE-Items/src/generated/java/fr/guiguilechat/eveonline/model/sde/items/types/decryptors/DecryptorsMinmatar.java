
package fr.guiguilechat.eveonline.model.sde.items.types.decryptors;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Decryptors;
import org.yaml.snakeyaml.Yaml;

public class DecryptorsMinmatar
    extends Decryptors
{

    public final static String RESOURCE_PATH = "SDE/items/decryptors/DecryptorsMinmatar.yaml";
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
                cache = new Yaml().loadAs(new InputStreamReader(DecryptorsMinmatar.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, DecryptorsMinmatar> items;

    }

}
