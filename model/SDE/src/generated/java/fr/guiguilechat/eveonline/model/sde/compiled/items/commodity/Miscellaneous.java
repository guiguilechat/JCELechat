
package fr.guiguilechat.eveonline.model.sde.compiled.items.commodity;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.DefaultValue;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Commodity;
import org.yaml.snakeyaml.Yaml;

public class Miscellaneous
    extends Commodity
{

    /**
     * Cost multiplier per m3 volume of this commodity when importing to a planet
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultValue(1.0D)
    public double ImportTaxMultiplier;
    /**
     * Export tax multiplier when exporting this commodity off a planet.
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultValue(1.0D)
    public double ExportTaxMultiplier;
    /**
     * The maximum hitpoints of an object.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double Hp;
    public final static String RESOURCE_PATH = "SDE/commodity/Miscellaneous.yaml";
    private static LinkedHashMap<String, Miscellaneous> cache = (null);

    @Override
    public int getGroupId() {
        return  314;
    }

    @Override
    public Class<?> getGroup() {
        return Miscellaneous.class;
    }

    public static LinkedHashMap<String, Miscellaneous> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, Miscellaneous> items;

    }

}
