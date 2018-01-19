
package fr.guiguilechat.eveonline.model.sde.compiled.items.blueprint;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Blueprint;
import org.yaml.snakeyaml.Yaml;

public class UnknownBlueprint
    extends Blueprint
{

    public final static String RESOURCE_PATH = "SDE/items/blueprint/UnknownBlueprint.yaml";
    private static LinkedHashMap<String, UnknownBlueprint> cache = (null);

    @Override
    public int getGroupId() {
        return  1461;
    }

    @Override
    public Class<?> getGroup() {
        return UnknownBlueprint.class;
    }

    public static LinkedHashMap<String, UnknownBlueprint> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(UnknownBlueprint.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, UnknownBlueprint> items;

    }

}