
package fr.guiguilechat.eveonline.model.sde.items.types.structuremodule;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.StructureModule;
import org.yaml.snakeyaml.Yaml;

public class StructureBurstProjector
    extends StructureModule
{

    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double CanFitShipGroup01;
    public final static String RESOURCE_PATH = "SDE/items/structuremodule/StructureBurstProjector.yaml";
    private static LinkedHashMap<String, StructureBurstProjector> cache = (null);

    @Override
    public int getGroupId() {
        return  1331;
    }

    @Override
    public Class<?> getGroup() {
        return StructureBurstProjector.class;
    }

    public static LinkedHashMap<String, StructureBurstProjector> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(StructureBurstProjector.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, StructureBurstProjector> items;

    }

}
