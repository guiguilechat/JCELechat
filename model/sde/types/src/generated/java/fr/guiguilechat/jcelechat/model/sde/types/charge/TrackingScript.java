package fr.guiguilechat.jcelechat.model.sde.types.charge;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.AoeCloudSizeBonusBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.AoeVelocityBonusBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.ChargeSize;
import fr.guiguilechat.jcelechat.model.sde.attributes.FalloffBonusBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.LauncherGroup;
import fr.guiguilechat.jcelechat.model.sde.attributes.LauncherGroup2;
import fr.guiguilechat.jcelechat.model.sde.attributes.LauncherGroup3;
import fr.guiguilechat.jcelechat.model.sde.attributes.MainColor;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxRangeBonusBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.TechLevel;
import fr.guiguilechat.jcelechat.model.sde.attributes.TrackingSpeedBonusBonus;
import fr.guiguilechat.jcelechat.model.sde.types.Charge;
import org.yaml.snakeyaml.LoaderOptions;
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
    public int aoecloudsizebonusbonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1399)
    public int aoevelocitybonusbonus;
    /**
     * The size of the charges that can fit in the turret/whatever.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int chargesize;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1399)
    public int falloffbonusbonus;
    /**
     * One of the groups of launcher this charge can be loaded into.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int launchergroup;
    /**
     * One of the groups of launcher this charge can be loaded into.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int launchergroup2;
    /**
     * One of the groups of launcher this charge can be loaded into.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int launchergroup3;
    /**
     * The main color of a ship type.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maincolor;
    /**
     * Bonus to maxRangeBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxrangebonusbonus;
    /**
     * Authoring has been moved to FSD
     * Tech level of an item
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int techlevel;
    /**
     * Bonus to trackingSpeedBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int trackingspeedbonusbonus;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {ChargeSize.INSTANCE, MaxRangeBonusBonus.INSTANCE, TrackingSpeedBonusBonus.INSTANCE, FalloffBonusBonus.INSTANCE, TechLevel.INSTANCE, AoeCloudSizeBonusBonus.INSTANCE, AoeVelocityBonusBonus.INSTANCE, LauncherGroup.INSTANCE, LauncherGroup2 .INSTANCE, LauncherGroup3 .INSTANCE, MainColor.INSTANCE })));
    public static final TrackingScript.MetaGroup METAGROUP = new TrackingScript.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  2023 :
            {
                return aoecloudsizebonusbonus;
            }
            case  2024 :
            {
                return aoevelocitybonusbonus;
            }
            case  128 :
            {
                return chargesize;
            }
            case  1332 :
            {
                return falloffbonusbonus;
            }
            case  137 :
            {
                return launchergroup;
            }
            case  602 :
            {
                return launchergroup2;
            }
            case  603 :
            {
                return launchergroup3;
            }
            case  124 :
            {
                return maincolor;
            }
            case  1315 :
            {
                return maxrangebonusbonus;
            }
            case  422 :
            {
                return techlevel;
            }
            case  1316 :
            {
                return trackingspeedbonusbonus;
            }
            default:
            {
                return super.valueSet((attribute));
            }
        }
    }

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<TrackingScript> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<TrackingScript>
    {
        public static final String RESOURCE_PATH = "SDE/types/charge/TrackingScript.yaml";
        private Map<Integer, TrackingScript> cache = (null);

        @Override
        public IMetaCategory<? super TrackingScript> category() {
            return Charge.METACAT;
        }

        @Override
        public int getGroupId() {
            return  907;
        }

        @Override
        public String getName() {
            return "TrackingScript";
        }

        @Override
        public synchronized Map<Integer, TrackingScript> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(TrackingScript.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    LoaderOptions options = new LoaderOptions();
                    options.setCodePointLimit(Integer.MAX_VALUE);
                    cache = new Yaml(options).loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<Integer, TrackingScript> types;
        }
    }
}
