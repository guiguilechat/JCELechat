
package fr.guiguilechat.eveonline.model.sde.compiled.items.commodity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.DefaultValue;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Commodity;
import org.yaml.snakeyaml.Yaml;

public class AdvancedCapitalConstructionComponents
    extends Commodity
{

    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(1.0D)
    public double MoonMiningAmount;
    public final static String RESOURCE_PATH = "SDE/items/commodity/AdvancedCapitalConstructionComponents.yaml";
    private static LinkedHashMap<String, AdvancedCapitalConstructionComponents> cache = (null);

    @Override
    public int getGroupId() {
        return  913;
    }

    @Override
    public Class<?> getGroup() {
        return AdvancedCapitalConstructionComponents.class;
    }

    public static LinkedHashMap<String, AdvancedCapitalConstructionComponents> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AdvancedCapitalConstructionComponents.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, AdvancedCapitalConstructionComponents> items;

    }

}