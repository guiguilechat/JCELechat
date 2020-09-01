package fr.guiguilechat.jcelechat.model.sde.types.celestial;

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
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultRealValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.AnchoringDelay;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hp;
import fr.guiguilechat.jcelechat.model.sde.attributes.Mass;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiresSovereigntyDisplayOnly;
import fr.guiguilechat.jcelechat.model.sde.attributes.StationOreRefiningBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.StationTypeID;
import fr.guiguilechat.jcelechat.model.sde.types.Celestial;
import org.yaml.snakeyaml.Yaml;

public class StationImprovementPlatform
    extends Celestial
{
    /**
     * How long it takes to anchor or unanchor this object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(60000)
    public int anchoringdelay;
    /**
     * The cargo space allowed
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double capacity;
    /**
     * The maximum hitpoints of an object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double hp;
    /**
     * Integer that describes the types mass
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double mass;
    /**
     * Radius of an object in meters
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double radius;
    /**
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill1;
    /**
     * Required skill level for skill 1
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill1level;
    /**
     * This is a display-only attribute for showinfo
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiressovereigntydisplayonly;
    /**
     * Bonus for refining ore. Used for station improvements
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double stationorerefiningbonus;
    /**
     * The type of station this platform can be used to build.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int stationtypeid;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {Radius.INSTANCE, StationOreRefiningBonus.INSTANCE, Mass.INSTANCE, RequiredSkill1Level.INSTANCE, Capacity.INSTANCE, RequiredSkill1 .INSTANCE, StationTypeID.INSTANCE, Hp.INSTANCE, AnchoringDelay.INSTANCE, RequiresSovereigntyDisplayOnly.INSTANCE })));
    public static final StationImprovementPlatform.MetaGroup METAGROUP = new StationImprovementPlatform.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  556 :
            {
                return anchoringdelay;
            }
            case  38 :
            {
                return capacity;
            }
            case  9 :
            {
                return hp;
            }
            case  4 :
            {
                return mass;
            }
            case  162 :
            {
                return radius;
            }
            case  182 :
            {
                return requiredskill1;
            }
            case  277 :
            {
                return requiredskill1level;
            }
            case  1806 :
            {
                return requiressovereigntydisplayonly;
            }
            case  1939 :
            {
                return stationorerefiningbonus;
            }
            case  472 :
            {
                return stationtypeid;
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
    public IMetaGroup<StationImprovementPlatform> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<StationImprovementPlatform>
    {
        public static final String RESOURCE_PATH = "SDE/types/celestial/StationImprovementPlatform.yaml";
        private Map<String, StationImprovementPlatform> cache = (null);

        @Override
        public IMetaCategory<? super StationImprovementPlatform> category() {
            return Celestial.METACAT;
        }

        @Override
        public int getGroupId() {
            return  836;
        }

        @Override
        public String getName() {
            return "StationImprovementPlatform";
        }

        @Override
        public synchronized Map<String, StationImprovementPlatform> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(StationImprovementPlatform.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, StationImprovementPlatform> types;
        }
    }
}
