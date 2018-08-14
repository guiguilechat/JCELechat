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

public class SurveyProbeLauncher
    extends Module
{
    /**
     * One of the groups of charge this launcher can be loaded with.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ChargeGroup2;
    /**
     * Number of charges consumed per activation
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int ChargeRate;
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
     * Maximum modules of same group that can be activated at same time, 0 = no limit, 1 = 1
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MaxGroupActive;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MaxGroupFitted;
    /**
     * current power need
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Power;
    /**
     * reload time (ms)
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(10000.0)
    public double ReloadTime;
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
     * The number of slots this module requires.  Only used for launchers, bays and turrets.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int Slots;
    /**
     * Time in milliseconds between possible activations
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int Speed;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int SurveyProbeDurationBonus;
    public final static SurveyProbeLauncher.MetaGroup METAGROUP = new SurveyProbeLauncher.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  605 :
            {
                return ChargeGroup2;
            }
            case  56 :
            {
                return ChargeRate;
            }
            case  50 :
            {
                return Cpu;
            }
            case  1014 :
            {
                return DisallowRepeatingActivation;
            }
            case  763 :
            {
                return MaxGroupActive;
            }
            case  1544 :
            {
                return MaxGroupFitted;
            }
            case  30 :
            {
                return Power;
            }
            case  1795 :
            {
                return ReloadTime;
            }
            case  182 :
            {
                return RequiredSkill1;
            }
            case  277 :
            {
                return RequiredSkill1Level;
            }
            case  47 :
            {
                return Slots;
            }
            case  51 :
            {
                return Speed;
            }
            case  2701 :
            {
                return SurveyProbeDurationBonus;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public IMetaGroup<SurveyProbeLauncher> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<SurveyProbeLauncher>
    {
        public final static String RESOURCE_PATH = "SDE/items/module/SurveyProbeLauncher.yaml";
        private Map<String, SurveyProbeLauncher> cache = (null);

        @Override
        public IMetaCategory<? super SurveyProbeLauncher> category() {
            return Module.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1226;
        }

        @Override
        public String getName() {
            return "SurveyProbeLauncher";
        }

        @Override
        public synchronized Map<String, SurveyProbeLauncher> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(SurveyProbeLauncher.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, SurveyProbeLauncher> items;
        }
    }
}
