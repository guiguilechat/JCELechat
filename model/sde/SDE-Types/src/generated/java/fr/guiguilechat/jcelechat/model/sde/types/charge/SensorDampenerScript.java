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
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.ChargeSize;
import fr.guiguilechat.jcelechat.model.sde.attributes.LauncherGroup;
import fr.guiguilechat.jcelechat.model.sde.attributes.MainColor;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxTargetRangeBonusBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanResolutionBonusBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.TechLevel;
import fr.guiguilechat.jcelechat.model.sde.types.Charge;
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
    public int chargesize;
    /**
     * One of the groups of launcher this charge can be loaded into.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int launchergroup;
    /**
     * The main color of a ship type.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maincolor;
    /**
     * Bonus to maxTargetRangeBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxtargetrangebonusbonus;
    /**
     * Bonus to scanResolutionBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int scanresolutionbonusbonus;
    /**
     * Authoring has been moved to FSD
     * Tech level of an item
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int techlevel;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {ChargeSize.INSTANCE, MaxTargetRangeBonusBonus.INSTANCE, ScanResolutionBonusBonus.INSTANCE, Radius.INSTANCE, TechLevel.INSTANCE, Capacity.INSTANCE, LauncherGroup.INSTANCE, MainColor.INSTANCE })));
    public static final SensorDampenerScript.MetaGroup METAGROUP = new SensorDampenerScript.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  128 :
            {
                return chargesize;
            }
            case  137 :
            {
                return launchergroup;
            }
            case  124 :
            {
                return maincolor;
            }
            case  1313 :
            {
                return maxtargetrangebonusbonus;
            }
            case  1314 :
            {
                return scanresolutionbonusbonus;
            }
            case  422 :
            {
                return techlevel;
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
    public IMetaGroup<SensorDampenerScript> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<SensorDampenerScript>
    {
        public static final String RESOURCE_PATH = "SDE/types/charge/SensorDampenerScript.yaml";
        private Map<String, SensorDampenerScript> cache = (null);

        @Override
        public IMetaCategory<? super SensorDampenerScript> category() {
            return Charge.METACAT;
        }

        @Override
        public int getGroupId() {
            return  911;
        }

        @Override
        public String getName() {
            return "SensorDampenerScript";
        }

        @Override
        public synchronized Map<String, SensorDampenerScript> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(SensorDampenerScript.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, SensorDampenerScript> types;
        }
    }
}
