
package fr.guiguilechat.eveonline.model.sde.items.types.blueprint;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Blueprint;
import org.yaml.snakeyaml.Yaml;

public class MicroJumpFieldGeneratorBlueprint
    extends Blueprint
{

    public final static String RESOURCE_PATH = "SDE/items/blueprint/MicroJumpFieldGeneratorBlueprint.yaml";
    private static LinkedHashMap<String, MicroJumpFieldGeneratorBlueprint> cache = (null);

    @Override
    public int getGroupId() {
        return  1543;
    }

    @Override
    public Class<?> getGroup() {
        return MicroJumpFieldGeneratorBlueprint.class;
    }

    public static LinkedHashMap<String, MicroJumpFieldGeneratorBlueprint> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(MicroJumpFieldGeneratorBlueprint.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, MicroJumpFieldGeneratorBlueprint> items;

    }

}
