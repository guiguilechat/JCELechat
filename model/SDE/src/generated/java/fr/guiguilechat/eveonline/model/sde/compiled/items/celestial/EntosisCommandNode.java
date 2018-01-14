
package fr.guiguilechat.eveonline.model.sde.compiled.items.celestial;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Celestial;
import org.yaml.snakeyaml.Yaml;

public class EntosisCommandNode
    extends Celestial
{

    public final static String RESOURCE_PATH = "SDE/celestial/EntosisCommandNode.yaml";
    private static LinkedHashMap<String, EntosisCommandNode> cache = (null);

    @Override
    public int getGroupId() {
        return  1316;
    }

    @Override
    public Class<?> getGroup() {
        return EntosisCommandNode.class;
    }

    public static LinkedHashMap<String, EntosisCommandNode> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, EntosisCommandNode> items;

    }

}
