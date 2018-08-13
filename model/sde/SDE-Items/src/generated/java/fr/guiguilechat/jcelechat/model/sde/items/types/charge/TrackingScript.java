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

public class TrackingScript
    extends Charge
{
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1399)
    public int AoeCloudSizeBonusBonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1399)
    public int AoeVelocityBonusBonus;
    /**
     * The size of the charges that can fit in the turret/whatever.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ChargeSize;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1399)
    public int FalloffBonusBonus;
    /**
     * One of the groups of launcher this charge can be loaded into.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int LauncherGroup;
    /**
     * One of the groups of launcher this charge can be loaded into.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int LauncherGroup2;
    /**
     * One of the groups of launcher this charge can be loaded into.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int LauncherGroup3;
    /**
     * The main color of a ship type.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MainColor;
    /**
     * Bonus to maxRangeBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MaxRangeBonusBonus;
    /**
     * Authoring has been moved to FSD
     * Tech level of an item
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int TechLevel;
    /**
     * Bonus to trackingSpeedBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int TrackingSpeedBonusBonus;
    public final static TrackingScript.MetaGroup METAGROUP = new TrackingScript.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/charge/TrackingScript.yaml";
    private static Map<String, TrackingScript> cache = (null);

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  2023 :
            {
                return AoeCloudSizeBonusBonus;
            }
            case  2024 :
            {
                return AoeVelocityBonusBonus;
            }
            case  128 :
            {
                return ChargeSize;
            }
            case  1332 :
            {
                return FalloffBonusBonus;
            }
            case  137 :
            {
                return LauncherGroup;
            }
            case  602 :
            {
                return LauncherGroup2;
            }
            case  603 :
            {
                return LauncherGroup3;
            }
            case  124 :
            {
                return MainColor;
            }
            case  1315 :
            {
                return MaxRangeBonusBonus;
            }
            case  422 :
            {
                return TechLevel;
            }
            case  1316 :
            {
                return TrackingSpeedBonusBonus;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public int getGroupId() {
        return  907;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<TrackingScript> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, TrackingScript> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(TrackingScript.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, TrackingScript> items;
    }

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<TrackingScript>
    {

        @Override
        public MetaCategory<? super TrackingScript> category() {
            return Charge.METACAT;
        }

        @Override
        public String getName() {
            return "TrackingScript";
        }

        @Override
        public Collection<TrackingScript> items() {
            return (load().values());
        }
    }
}
