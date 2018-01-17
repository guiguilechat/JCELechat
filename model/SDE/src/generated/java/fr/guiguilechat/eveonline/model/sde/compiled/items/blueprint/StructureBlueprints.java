
package fr.guiguilechat.eveonline.model.sde.compiled.items.blueprint;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Blueprint;
import org.yaml.snakeyaml.Yaml;

public class StructureBlueprints
    extends Blueprint
{

    public final static String RESOURCE_PATH = "SDE/items/blueprint/StructureBlueprints.yaml";
    private static LinkedHashMap<String, StructureBlueprints> cache = (null);

    @Override
    public int getGroupId() {
        return  1462;
    }

    @Override
    public Class<?> getGroup() {
        return StructureBlueprints.class;
    }

    public static LinkedHashMap<String, StructureBlueprints> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(StructureBlueprints.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, StructureBlueprints> items;

    }

}
