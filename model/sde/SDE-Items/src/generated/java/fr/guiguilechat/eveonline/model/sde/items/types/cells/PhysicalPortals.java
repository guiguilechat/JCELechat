
package fr.guiguilechat.eveonline.model.sde.items.types.cells;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Cells;
import org.yaml.snakeyaml.Yaml;

public class PhysicalPortals
    extends Cells
{

    public final static String RESOURCE_PATH = "SDE/items/cells/PhysicalPortals.yaml";
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
                cache = new Yaml().loadAs(new InputStreamReader(PhysicalPortals.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, PhysicalPortals> items;

    }

}
