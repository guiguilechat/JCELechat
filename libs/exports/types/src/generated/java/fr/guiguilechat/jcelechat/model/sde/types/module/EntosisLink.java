package fr.guiguilechat.jcelechat.model.sde.types.module;

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
import fr.guiguilechat.jcelechat.model.sde.attributes.CanCloak;
import fr.guiguilechat.jcelechat.model.sde.attributes.CapacitorNeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.ConsumptionQuantity;
import fr.guiguilechat.jcelechat.model.sde.attributes.ConsumptionType;
import fr.guiguilechat.jcelechat.model.sde.attributes.Cpu;
import fr.guiguilechat.jcelechat.model.sde.attributes.Duration;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntosisAssistanceImpedanceMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntosisCPUAdd;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hp;
import fr.guiguilechat.jcelechat.model.sde.attributes.ImplantBonusVelocity;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxGroupFitted;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaLevelOld;
import fr.guiguilechat.jcelechat.model.sde.attributes.Power;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill2;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill2Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanGravimetricStrengthPercent;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanLadarStrengthPercent;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanMagnetometricStrengthPercent;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanRadarStrengthPercent;
import fr.guiguilechat.jcelechat.model.sde.attributes.SiegeModeWarpStatus;
import fr.guiguilechat.jcelechat.model.sde.attributes.Slots;
import fr.guiguilechat.jcelechat.model.sde.attributes.SpeedLimit;
import fr.guiguilechat.jcelechat.model.sde.attributes.TechLevel;
import fr.guiguilechat.jcelechat.model.sde.types.Module;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class EntosisLink
    extends Module
{
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int cancloak;
    /**
     * The amount of charge used from the capacitor for a module activation.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double capacitorneed;
    /**
     * The amount of the given resource type needed to be consumed for each activation cycle of this structure.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int consumptionquantity;
    /**
     * The type of resource needed to be consumed for each activation cycle of this structure.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int consumptiontype;
    /**
     * CPU need of module
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double cpu;
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
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double entosisassistanceimpedancemultiplier;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int entosiscpuadd;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double implantbonusvelocity;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxgroupfitted;
    /**
     * Distance below which range does not affect the to-hit equation.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double maxrange;
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
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill2;
    /**
     * Required skill level for skill 2
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill2level;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int scangravimetricstrengthpercent;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int scanladarstrengthpercent;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int scanmagnetometricstrengthpercent;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int scanradarstrengthpercent;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int siegemodewarpstatus;
    /**
     * The number of slots this module requires.  Only used for launchers, bays and turrets.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int slots;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1000000)
    public int speedlimit;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {EntosisAssistanceImpedanceMultiplier.INSTANCE, ScanGravimetricStrengthPercent.INSTANCE, ScanLadarStrengthPercent.INSTANCE, ScanMagnetometricStrengthPercent.INSTANCE, CapacitorNeed.INSTANCE, ScanRadarStrengthPercent.INSTANCE, MaxGroupFitted.INSTANCE, Duration.INSTANCE, ConsumptionType.INSTANCE, Hp.INSTANCE, ConsumptionQuantity.INSTANCE, CanCloak.INSTANCE, SiegeModeWarpStatus.INSTANCE, RequiredSkill1Level.INSTANCE, RequiredSkill2Level.INSTANCE, Power.INSTANCE, TechLevel.INSTANCE, Slots.INSTANCE, SpeedLimit.INSTANCE, Cpu.INSTANCE, ImplantBonusVelocity.INSTANCE, MaxRange.INSTANCE, RequiredSkill1 .INSTANCE, RequiredSkill2 .INSTANCE, EntosisCPUAdd.INSTANCE, MetaLevelOld.INSTANCE })));
    public static final EntosisLink.MetaGroup METAGROUP = new EntosisLink.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  1163 :
            {
                return cancloak;
            }
            case  6 :
            {
                return capacitorneed;
            }
            case  714 :
            {
                return consumptionquantity;
            }
            case  713 :
            {
                return consumptiontype;
            }
            case  50 :
            {
                return cpu;
            }
            case  73 :
            {
                return duration;
            }
            case  2754 :
            {
                return entosisassistanceimpedancemultiplier;
            }
            case  2041 :
            {
                return entosiscpuadd;
            }
            case  1076 :
            {
                return implantbonusvelocity;
            }
            case  1544 :
            {
                return maxgroupfitted;
            }
            case  54 :
            {
                return maxrange;
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
            case  183 :
            {
                return requiredskill2;
            }
            case  278 :
            {
                return requiredskill2level;
            }
            case  1027 :
            {
                return scangravimetricstrengthpercent;
            }
            case  1028 :
            {
                return scanladarstrengthpercent;
            }
            case  1029 :
            {
                return scanmagnetometricstrengthpercent;
            }
            case  1030 :
            {
                return scanradarstrengthpercent;
            }
            case  852 :
            {
                return siegemodewarpstatus;
            }
            case  47 :
            {
                return slots;
            }
            case  2033 :
            {
                return speedlimit;
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
    public IMetaGroup<EntosisLink> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<EntosisLink>
    {
        public static final String RESOURCE_PATH = "SDE/types/module/EntosisLink.yaml";
        private Map<Integer, EntosisLink> cache = (null);

        @Override
        public IMetaCategory<? super EntosisLink> category() {
            return Module.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1313;
        }

        @Override
        public String getName() {
            return "EntosisLink";
        }

        @Override
        public synchronized Map<Integer, EntosisLink> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(EntosisLink.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, EntosisLink> types;
        }
    }
}
