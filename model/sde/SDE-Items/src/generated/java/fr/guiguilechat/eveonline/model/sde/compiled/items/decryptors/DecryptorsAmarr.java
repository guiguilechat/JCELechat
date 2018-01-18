
package fr.guiguilechat.eveonline.model.sde.compiled.items.decryptors;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Decryptors;
import org.yaml.snakeyaml.Yaml;

public class DecryptorsAmarr
    extends Decryptors
{

    public final static String RESOURCE_PATH = "SDE/items/decryptors/DecryptorsAmarr.yaml";
    private static LinkedHashMap<String, DecryptorsAmarr> cache = (null);

    @Override
    public int getGroupId() {
        return  728;
    }

    @Override
    public Class<?> getGroup() {
        return DecryptorsAmarr.class;
    }

    public static LinkedHashMap<String, DecryptorsAmarr> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(DecryptorsAmarr.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, DecryptorsAmarr> items;

    }

}
