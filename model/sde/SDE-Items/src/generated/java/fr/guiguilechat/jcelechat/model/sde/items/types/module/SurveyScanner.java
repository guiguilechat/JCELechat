package fr.guiguilechat.jcelechat.model.sde.items.types.module;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.Module;
import org.yaml.snakeyaml.Yaml;

public class SurveyScanner
    extends Module
{
    /**
     * The amount of charge used from the capacitor for a module activation.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double CapacitorNeed;
    /**
     * CPU need of module
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Cpu;
    /**
     * If set, this module cannot be activated and made to autorepeat.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DisallowRepeatingActivation;
    /**
     * Length of activation time.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Duration;
    /**
     * current power need
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Power;
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
     * Distance from thing to survey.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int SurveyScanRange;
    /**
     * Restrict activation to this one module group.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int TargetGroup;
    public final static SurveyScanner.MetaGroup METAGROUP = new SurveyScanner.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  6 :
            {
                return CapacitorNeed;
            }
            case  50 :
            {
                return Cpu;
            }
            case  1014 :
            {
                return DisallowRepeatingActivation;
            }
            case  73 :
            {
                return Duration;
            }
            case  30 :
            {
                return Power;
            }
            case  182 :
            {
                return RequiredSkill1;
            }
            case  277 :
            {
                return RequiredSkill1Level;
            }
            case  197 :
            {
                return SurveyScanRange;
            }
            case  189 :
            {
                return TargetGroup;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public IMetaGroup<SurveyScanner> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<SurveyScanner>
    {
        public final static String RESOURCE_PATH = "SDE/items/module/SurveyScanner.yaml";
        private Map<String, SurveyScanner> cache = (null);

        @Override
        public IMetaCategory<? super SurveyScanner> category() {
            return Module.METACAT;
        }

        @Override
        public int getGroupId() {
            return  49;
        }

        @Override
        public String getName() {
            return "SurveyScanner";
        }

        @Override
        public synchronized Map<String, SurveyScanner> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(SurveyScanner.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, SurveyScanner> items;
        }
    }
}
