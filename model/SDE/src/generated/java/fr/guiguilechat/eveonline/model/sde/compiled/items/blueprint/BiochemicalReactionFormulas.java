
package fr.guiguilechat.eveonline.model.sde.compiled.items.blueprint;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Blueprint;
import org.yaml.snakeyaml.Yaml;

public class BiochemicalReactionFormulas
    extends Blueprint
{

    public final static String RESOURCE_PATH = "SDE/blueprint/BiochemicalReactionFormulas.yaml";
    private static LinkedHashMap<String, BiochemicalReactionFormulas> cache = (null);

    @Override
    public int getGroupId() {
        return  1890;
    }

    @Override
    public Class<?> getGroup() {
        return BiochemicalReactionFormulas.class;
    }

    public static LinkedHashMap<String, BiochemicalReactionFormulas> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, BiochemicalReactionFormulas> items;

    }

}
