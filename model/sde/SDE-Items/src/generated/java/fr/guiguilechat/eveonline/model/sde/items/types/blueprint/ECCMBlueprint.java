
package fr.guiguilechat.eveonline.model.sde.items.types.blueprint;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.Blueprint;
import org.yaml.snakeyaml.Yaml;

public class ECCMBlueprint
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
    /**
     * Tech level of an item
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(1.0D)
    public double TechLevel;
    public final static String RESOURCE_PATH = "SDE/items/blueprint/ECCMBlueprint.yaml";
    private static LinkedHashMap<String, ECCMBlueprint> cache = (null);

    @Override
    public int getGroupId() {
        return  131;
    }

    @Override
    public Class<?> getGroup() {
        return ECCMBlueprint.class;
    }

    public static LinkedHashMap<String, ECCMBlueprint> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(ECCMBlueprint.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, ECCMBlueprint> items;

    }

}
