
package fr.guiguilechat.eveonline.model.sde.compiled.items.reaction;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Reaction;
import org.yaml.snakeyaml.Yaml;

public class ComplexReactions
    extends Reaction
{

    public final static String RESOURCE_PATH = "SDE/reaction/ComplexReactions.yaml";
    private static LinkedHashMap<String, ComplexReactions> cache = (null);

    @Override
    public int getGroupId() {
        return  484;
    }

    @Override
    public Class<?> getGroup() {
        return ComplexReactions.class;
    }

    public static LinkedHashMap<String, ComplexReactions> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, ComplexReactions> items;

    }

}
