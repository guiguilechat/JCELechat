
package fr.guiguilechat.eveonline.model.sde.compiled.items.blueprint;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.DefaultValue;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Blueprint;
import org.yaml.snakeyaml.Yaml;

public class BattleshipBlueprint
    extends Blueprint
{

    /**
     * Multiplies the job cost for this blueprint type by the specified value
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(1.0D)
    public double IndustryJobCostMultiplier;
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
    public final static String RESOURCE_PATH = "SDE/items/blueprint/BattleshipBlueprint.yaml";
    private static LinkedHashMap<String, BattleshipBlueprint> cache = (null);

    @Override
    public int getGroupId() {
        return  107;
    }

    @Override
    public Class<?> getGroup() {
        return BattleshipBlueprint.class;
    }

    public static LinkedHashMap<String, BattleshipBlueprint> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(BattleshipBlueprint.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, BattleshipBlueprint> items;

    }

}