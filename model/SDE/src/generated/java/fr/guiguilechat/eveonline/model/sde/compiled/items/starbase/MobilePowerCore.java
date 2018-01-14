
package fr.guiguilechat.eveonline.model.sde.compiled.items.starbase;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.DefaultValue;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Starbase;
import org.yaml.snakeyaml.Yaml;

public class MobilePowerCore
    extends Starbase
{

    /**
     * CPU need of module
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double Cpu;
    /**
     * The hull damage proportion at which an entity becomes incapacitated.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double IncapacitationRatio;
    /**
     * current power need
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double Power;
    public final static String RESOURCE_PATH = "SDE/starbase/MobilePowerCore.yaml";
    private static LinkedHashMap<String, MobilePowerCore> cache = (null);

    @Override
    public int getGroupId() {
        return  414;
    }

    @Override
    public Class<?> getGroup() {
        return MobilePowerCore.class;
    }

    public static LinkedHashMap<String, MobilePowerCore> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, MobilePowerCore> items;

    }

}
