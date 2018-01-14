
package fr.guiguilechat.eveonline.model.sde.compiled.items.reaction;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Reaction;
import org.yaml.snakeyaml.Yaml;

public class ComplexBiochemicalReactions
    extends Reaction
{

    public final static String RESOURCE_PATH = "SDE/reaction/ComplexBiochemicalReactions.yaml";
    private static LinkedHashMap<String, ComplexBiochemicalReactions> cache = (null);

    @Override
    public int getGroupId() {
        return  662;
    }

    @Override
    public Class<?> getGroup() {
        return ComplexBiochemicalReactions.class;
    }

    public static LinkedHashMap<String, ComplexBiochemicalReactions> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, ComplexBiochemicalReactions> items;

    }

}
