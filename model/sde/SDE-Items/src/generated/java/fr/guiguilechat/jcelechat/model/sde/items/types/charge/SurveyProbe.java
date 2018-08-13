package fr.guiguilechat.jcelechat.model.sde.items.types.charge;

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
import fr.guiguilechat.jcelechat.model.sde.items.types.Charge;
import org.yaml.snakeyaml.Yaml;

public class SurveyProbe
    extends Charge
{
    /**
     * The agility of the object.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double Agility;
    /**
     * The amount of milliseconds before the object explodes.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int ExplosionDelay;
    /**
     * Range in meters of explosion effect area.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ExplosionRange;
    /**
     * The maximum hitpoints of an object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Hp;
    /**
     * One of the groups of launcher this charge can be loaded into.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int LauncherGroup;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MaxScanRange;
    /**
     * Maximum velocity of ship
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double MaxVelocity;
    /**
     * Number of probes to analyze
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(3)
    public int ProbesInGroup;
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
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill2;
    /**
     * Required skill level for skill 2
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill2Level;
    /**
     * Effective range of scanner in multiples of AUs
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(10)
    public int ScanRange;
    /**
     * DO NOT MESS WITH
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double StructureUniformity;
    /**
     * Authoring has been moved to FSD
     * Tech level of an item
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int TechLevel;
    public final static SurveyProbe.MetaGroup METAGROUP = new SurveyProbe.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/charge/SurveyProbe.yaml";
    private static Map<String, SurveyProbe> cache = (null);

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  70 :
            {
                return Agility;
            }
            case  281 :
            {
                return ExplosionDelay;
            }
            case  107 :
            {
                return ExplosionRange;
            }
            case  9 :
            {
                return Hp;
            }
            case  137 :
            {
                return LauncherGroup;
            }
            case  2697 :
            {
                return MaxScanRange;
            }
            case  37 :
            {
                return MaxVelocity;
            }
            case  794 :
            {
                return ProbesInGroup;
            }
            case  182 :
            {
                return RequiredSkill1;
            }
            case  277 :
            {
                return RequiredSkill1Level;
            }
            case  183 :
            {
                return RequiredSkill2;
            }
            case  278 :
            {
                return RequiredSkill2Level;
            }
            case  765 :
            {
                return ScanRange;
            }
            case  525 :
            {
                return StructureUniformity;
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
        return  492;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<SurveyProbe> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, SurveyProbe> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(SurveyProbe.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, SurveyProbe> items;
    }

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<SurveyProbe>
    {

        @Override
        public MetaCategory<? super SurveyProbe> category() {
            return Charge.METACAT;
        }

        @Override
        public String getName() {
            return "SurveyProbe";
        }

        @Override
        public Collection<SurveyProbe> items() {
            return (load().values());
        }
    }
}
