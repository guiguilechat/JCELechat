package fr.guiguilechat.jcelechat.model.sde.items.types.celestial;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.Celestial;
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
    public int AnchoringDelay;
    /**
     * The maximum hitpoints of an object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Hp;
    /**
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1;
    /**
     * Required skill level for skill 1
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1Level;
    /**
     * This is a display-only attribute for showinfo
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiresSovereigntyDisplayOnly;
    /**
     * Bonus for refining ore. Used for station improvements
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double StationOreRefiningBonus;
    /**
     * The type of station this platform can be used to build.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int StationTypeID;
    public final static StationImprovementPlatform.MetaGroup METAGROUP = new StationImprovementPlatform.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/celestial/StationImprovementPlatform.yaml";
    private static Map<String, StationImprovementPlatform> cache = (null);

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  556 :
            {
                return AnchoringDelay;
            }
            case  9 :
            {
                return Hp;
            }
            case  182 :
            {
                return RequiredSkill1;
            }
            case  277 :
            {
                return RequiredSkill1Level;
            }
            case  1806 :
            {
                return RequiresSovereigntyDisplayOnly;
            }
            case  1939 :
            {
                return StationOreRefiningBonus;
            }
            case  472 :
            {
                return StationTypeID;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public int getGroupId() {
        return  836;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<StationImprovementPlatform> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, StationImprovementPlatform> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(StationImprovementPlatform.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, StationImprovementPlatform> items;
    }

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<StationImprovementPlatform>
    {

        @Override
        public MetaCategory<? super StationImprovementPlatform> category() {
            return Celestial.METACAT;
        }

        @Override
        public String getName() {
            return "StationImprovementPlatform";
        }

        @Override
        public Collection<StationImprovementPlatform> items() {
            return (load().values());
        }
    }
}
