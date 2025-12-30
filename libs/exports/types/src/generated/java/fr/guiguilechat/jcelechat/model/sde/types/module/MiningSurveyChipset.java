package fr.guiguilechat.jcelechat.model.sde.types.module;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultRealValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.*;
import fr.guiguilechat.jcelechat.model.sde.types.Module;

public class MiningSurveyChipset
    extends Module
{
    /**
     * The amount of charge used from the capacitor for a module activation.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double capacitorneed;
    /**
     * CPU need of module
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double cpu;
    /**
     * If set, this module cannot be activated and made to autorepeat.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int disallowrepeatingactivation;
    /**
     * Length of activation time.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double duration;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxgroupfitted;
    /**
     * Maximum modules of same group that can be onlined at same time, 0 = no limit, 1 = 1
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxgrouponline;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int miningcritbonusyieldbonus;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int miningcritchancebonus;
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int miningscannerupgrade;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int miningwasteprobabilitybonus;
    /**
     * current power need
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int power;
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
     * Distance from thing to survey.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int surveyscanrange;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {MiningCritChanceBonus.INSTANCE, MiningCritBonusYieldBonus.INSTANCE, SurveyScanRange.INSTANCE, MiningWasteProbabilityBonus.INSTANCE, CapacitorNeed.INSTANCE, TechLevel.INSTANCE, MaxGroupFitted.INSTANCE, Duration.INSTANCE, Hp.INSTANCE, Cpu.INSTANCE, MaxGroupOnline.INSTANCE, RequiredSkill1Level.INSTANCE, RequiredSkill1 .INSTANCE, DisallowRepeatingActivation.INSTANCE, MetaLevelOld.INSTANCE, MiningScannerUpgrade.INSTANCE, Power.INSTANCE })));
    public static final MiningSurveyChipset.MetaGroup METAGROUP = new MiningSurveyChipset.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  6 :
            {
                return capacitorneed;
            }
            case  50 :
            {
                return cpu;
            }
            case  1014 :
            {
                return disallowrepeatingactivation;
            }
            case  73 :
            {
                return duration;
            }
            case  1544 :
            {
                return maxgroupfitted;
            }
            case  978 :
            {
                return maxgrouponline;
            }
            case  6050 :
            {
                return miningcritbonusyieldbonus;
            }
            case  6049 :
            {
                return miningcritchancebonus;
            }
            case  5979 :
            {
                return miningscannerupgrade;
            }
            case  6053 :
            {
                return miningwasteprobabilitybonus;
            }
            case  30 :
            {
                return power;
            }
            case  182 :
            {
                return requiredskill1;
            }
            case  277 :
            {
                return requiredskill1level;
            }
            case  197 :
            {
                return surveyscanrange;
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
    public IMetaGroup<MiningSurveyChipset> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MiningSurveyChipset>
    {
        public static final String RESOURCE_PATH = "SDE/types/module/MiningSurveyChipset.yaml";
        private Map<Integer, MiningSurveyChipset> cache = (null);

        @Override
        public IMetaCategory<? super MiningSurveyChipset> category() {
            return Module.METACAT;
        }

        @Override
        public int getGroupId() {
            return  49;
        }

        @Override
        public String getName() {
            return "MiningSurveyChipset";
        }

        @Override
        public synchronized Map<Integer, MiningSurveyChipset> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(MiningSurveyChipset.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, MiningSurveyChipset> types;
        }
    }
}
