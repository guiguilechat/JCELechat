
package fr.guiguilechat.eveonline.model.sde.compiled.items.structuremodule;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.DefaultValue;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.compiled.items.StructureModule;
import org.yaml.snakeyaml.Yaml;

public class StructureObservatoryServiceModule
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
    /**
     * CPU need of module
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double Cpu;
    /**
     * current power need
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double Power;
    public final static String RESOURCE_PATH = "SDE/structuremodule/StructureObservatoryServiceModule.yaml";
    private static LinkedHashMap<String, StructureObservatoryServiceModule> cache = (null);

    @Override
    public int getGroupId() {
        return  1323;
    }

    @Override
    public Class<?> getGroup() {
        return StructureObservatoryServiceModule.class;
    }

    public static LinkedHashMap<String, StructureObservatoryServiceModule> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, StructureObservatoryServiceModule> items;

    }

}
