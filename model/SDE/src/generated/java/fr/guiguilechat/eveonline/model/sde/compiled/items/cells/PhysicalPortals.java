
package fr.guiguilechat.eveonline.model.sde.compiled.items.cells;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Cells;
import org.yaml.snakeyaml.Yaml;

public class PhysicalPortals
    extends Cells
{

    public final static String RESOURCE_PATH = "SDE/cells/PhysicalPortals.yaml";
    private static LinkedHashMap<String, PhysicalPortals> cache = (null);

    @Override
    public int getGroupId() {
        return  1126;
    }

    @Override
    public Class<?> getGroup() {
        return PhysicalPortals.class;
    }

    public static LinkedHashMap<String, PhysicalPortals> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, PhysicalPortals> items;

    }

}
