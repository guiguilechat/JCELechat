
package fr.guiguilechat.eveonline.model.sde.items.types.blueprint;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.Blueprint;
import org.yaml.snakeyaml.Yaml;

public class EncounterSurveillanceSystemBlueprint
    extends Blueprint
{

    /**
     * This is a bookkeeping attribute for blueprints, which will hopefully be deprecated by the end of 2014
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double IndustryBlueprintRank;
    public final static String RESOURCE_PATH = "SDE/items/blueprint/EncounterSurveillanceSystemBlueprint.yaml";
    private static LinkedHashMap<String, EncounterSurveillanceSystemBlueprint> cache = (null);

    @Override
    public int getGroupId() {
        return  1277;
    }

    @Override
    public Class<?> getGroup() {
        return EncounterSurveillanceSystemBlueprint.class;
    }

    public static LinkedHashMap<String, EncounterSurveillanceSystemBlueprint> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(EncounterSurveillanceSystemBlueprint.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, EncounterSurveillanceSystemBlueprint> items;

    }

}
