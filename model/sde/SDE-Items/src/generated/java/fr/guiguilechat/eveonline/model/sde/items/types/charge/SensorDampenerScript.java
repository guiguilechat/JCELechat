package fr.guiguilechat.eveonline.model.sde.items.types.charge;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.Charge;
import org.yaml.snakeyaml.Yaml;

public class SensorDampenerScript
    extends Charge
{
    /**
     * The size of the charges that can fit in the turret/whatever.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ChargeSize;
    /**
     * One of the groups of launcher this charge can be loaded into.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int LauncherGroup;
    /**
     * The main color of a ship type.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MainColor;
    /**
     * Bonus to maxTargetRangeBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MaxTargetRangeBonusBonus;
    /**
     * Bonus to scanResolutionBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ScanResolutionBonusBonus;
    /**
     * Tech level of an item
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int TechLevel;
    public final static String RESOURCE_PATH = "SDE/items/charge/SensorDampenerScript.yaml";
    private static LinkedHashMap<String, SensorDampenerScript> cache = (null);

    @Override
    public int getGroupId() {
        return  911;
    }

    @Override
    public Class<?> getGroup() {
        return SensorDampenerScript.class;
    }

    public static synchronized LinkedHashMap<String, SensorDampenerScript> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(SensorDampenerScript.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, SensorDampenerScript> items;
    }
}
