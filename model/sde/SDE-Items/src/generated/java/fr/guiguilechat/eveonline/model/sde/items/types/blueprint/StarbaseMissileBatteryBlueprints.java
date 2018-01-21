
package fr.guiguilechat.eveonline.model.sde.items.types.blueprint;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.Blueprint;
import org.yaml.snakeyaml.Yaml;

public class StarbaseMissileBatteryBlueprints
    extends Blueprint
{

    /**
     * This is a bookkeeping attribute for blueprints, which will hopefully be deprecated by the end of 2014
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double IndustryBlueprintRank;
    public final static String RESOURCE_PATH = "SDE/items/blueprint/StarbaseMissileBatteryBlueprints.yaml";
    private static LinkedHashMap<String, StarbaseMissileBatteryBlueprints> cache = (null);

    @Override
    public int getGroupId() {
        return  871;
    }

    @Override
    public Class<?> getGroup() {
        return StarbaseMissileBatteryBlueprints.class;
    }

    public static LinkedHashMap<String, StarbaseMissileBatteryBlueprints> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(StarbaseMissileBatteryBlueprints.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, StarbaseMissileBatteryBlueprints> items;

    }

}
