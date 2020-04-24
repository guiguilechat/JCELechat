package fr.guiguilechat.jcelechat.model.sde.types.celestial;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
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
    public static final StationImprovementPlatform.MetaGroup METAGROUP = new StationImprovementPlatform.MetaGroup();

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
