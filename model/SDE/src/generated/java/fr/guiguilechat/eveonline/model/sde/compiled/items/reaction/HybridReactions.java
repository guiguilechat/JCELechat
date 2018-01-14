
package fr.guiguilechat.eveonline.model.sde.compiled.items.reaction;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Reaction;
import org.yaml.snakeyaml.Yaml;

public class HybridReactions
    extends Reaction
{

    public final static String RESOURCE_PATH = "SDE/reaction/HybridReactions.yaml";
    private static LinkedHashMap<String, HybridReactions> cache = (null);

    @Override
    public int getGroupId() {
        return  977;
    }

    @Override
    public Class<?> getGroup() {
        return HybridReactions.class;
    }

    public static LinkedHashMap<String, HybridReactions> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, HybridReactions> items;

    }

}
