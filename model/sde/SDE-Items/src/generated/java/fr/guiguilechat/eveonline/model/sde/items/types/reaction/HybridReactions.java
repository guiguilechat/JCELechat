
package fr.guiguilechat.eveonline.model.sde.items.types.reaction;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Reaction;
import org.yaml.snakeyaml.Yaml;

public class HybridReactions
    extends Reaction
{

    public final static String RESOURCE_PATH = "SDE/items/reaction/HybridReactions.yaml";
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
                cache = new Yaml().loadAs(new InputStreamReader(HybridReactions.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, HybridReactions> items;

    }

}
