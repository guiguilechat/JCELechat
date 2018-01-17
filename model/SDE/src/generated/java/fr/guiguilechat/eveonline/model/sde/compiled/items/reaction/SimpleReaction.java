
package fr.guiguilechat.eveonline.model.sde.compiled.items.reaction;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Reaction;
import org.yaml.snakeyaml.Yaml;

public class SimpleReaction
    extends Reaction
{

    public final static String RESOURCE_PATH = "SDE/items/reaction/SimpleReaction.yaml";
    private static LinkedHashMap<String, SimpleReaction> cache = (null);

    @Override
    public int getGroupId() {
        return  436;
    }

    @Override
    public Class<?> getGroup() {
        return SimpleReaction.class;
    }

    public static LinkedHashMap<String, SimpleReaction> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(SimpleReaction.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, SimpleReaction> items;

    }

}
