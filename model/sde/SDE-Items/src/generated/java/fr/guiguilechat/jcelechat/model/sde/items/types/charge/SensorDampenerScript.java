package fr.guiguilechat.jcelechat.model.sde.items.types.charge;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.Charge;
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
     * Authoring has been moved to FSD
     * Tech level of an item
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int TechLevel;
    public final static SensorDampenerScript.MetaGroup METAGROUP = new SensorDampenerScript.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/charge/SensorDampenerScript.yaml";
    private static Map<String, SensorDampenerScript> cache = (null);

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  128 :
            {
                return ChargeSize;
            }
            case  137 :
            {
                return LauncherGroup;
            }
            case  124 :
            {
                return MainColor;
            }
            case  1313 :
            {
                return MaxTargetRangeBonusBonus;
            }
            case  1314 :
            {
                return ScanResolutionBonusBonus;
            }
            case  422 :
            {
                return TechLevel;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public int getGroupId() {
        return  911;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<SensorDampenerScript> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, SensorDampenerScript> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(SensorDampenerScript.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, SensorDampenerScript> items;
    }

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<SensorDampenerScript>
    {

        @Override
        public MetaCategory<? super SensorDampenerScript> category() {
            return Charge.METACAT;
        }

        @Override
        public String getName() {
            return "SensorDampenerScript";
        }

        @Override
        public Collection<SensorDampenerScript> items() {
            return (load().values());
        }
    }
}
