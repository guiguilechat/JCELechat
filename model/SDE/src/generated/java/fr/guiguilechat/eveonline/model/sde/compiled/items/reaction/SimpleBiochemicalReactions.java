
package fr.guiguilechat.eveonline.model.sde.compiled.items.reaction;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Reaction;
import org.yaml.snakeyaml.Yaml;

public class SimpleBiochemicalReactions
    extends Reaction
{

    public final static String RESOURCE_PATH = "SDE/reaction/SimpleBiochemicalReactions.yaml";
    private static LinkedHashMap<String, SimpleBiochemicalReactions> cache = (null);

    @Override
    public int getGroupId() {
        return  661;
    }

    @Override
    public Class<?> getGroup() {
        return SimpleBiochemicalReactions.class;
    }

    public static LinkedHashMap<String, SimpleBiochemicalReactions> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, SimpleBiochemicalReactions> items;

    }

}
