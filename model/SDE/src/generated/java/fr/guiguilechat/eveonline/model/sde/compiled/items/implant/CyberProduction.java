
package fr.guiguilechat.eveonline.model.sde.compiled.items.implant;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.DefaultValue;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Implant;
import org.yaml.snakeyaml.Yaml;

public class CyberProduction
    extends Implant
{

    /**
     * Tech level of an item
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(1.0D)
    public double TechLevel;
    /**
     * Autogenerated skill attribute, manufacturingTimeBonus
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ManufacturingTimeBonus;
    /**
     * Whether an item is an implant or not
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double Implantness;
    public final static String RESOURCE_PATH = "SDE/implant/CyberProduction.yaml";
    private static LinkedHashMap<String, CyberProduction> cache = (null);

    @Override
    public int getGroupId() {
        return  743;
    }

    @Override
    public Class<?> getGroup() {
        return CyberProduction.class;
    }

    public static LinkedHashMap<String, CyberProduction> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, CyberProduction> items;

    }

}