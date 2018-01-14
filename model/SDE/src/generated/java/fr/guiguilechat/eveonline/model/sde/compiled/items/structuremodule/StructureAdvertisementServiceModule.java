
package fr.guiguilechat.eveonline.model.sde.compiled.items.structuremodule;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.DefaultValue;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.compiled.items.StructureModule;
import org.yaml.snakeyaml.Yaml;

public class StructureAdvertisementServiceModule
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
    public final static String RESOURCE_PATH = "SDE/structuremodule/StructureAdvertisementServiceModule.yaml";
    private static LinkedHashMap<String, StructureAdvertisementServiceModule> cache = (null);

    @Override
    public int getGroupId() {
        return  1326;
    }

    @Override
    public Class<?> getGroup() {
        return StructureAdvertisementServiceModule.class;
    }

    public static LinkedHashMap<String, StructureAdvertisementServiceModule> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, StructureAdvertisementServiceModule> items;

    }

}
