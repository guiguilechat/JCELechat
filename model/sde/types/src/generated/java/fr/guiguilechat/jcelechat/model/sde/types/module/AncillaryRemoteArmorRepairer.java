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

public class AncillaryRemoteArmorRepairer
    extends Module
{
    /**
     * An amount to modify the armor damage by.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double armordamageamount;
    /**
     * The amount of charge used from the capacitor for a module activation.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double capacitorneed;
    /**
     * One of the groups of charge this launcher can be loaded with.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int chargegroup1;
    /**
     * Number of charges consumed per activation
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int chargerate;
    /**
     * Multiplier applied to armorDamageAmount when consuming a collection of charges.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int chargedarmordamagemultiplier;
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
     * distance from maximum range at which effectiveness has fallen by half
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int falloffeffectiveness;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double heatabsorbtionratemodifier;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double heatdamage;
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
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int mediumremoterepfittingmultiplier;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int overloadarmordamageamount;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double overloadselfdurationbonus;
    /**
     * current power need
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int power;
    /**
     * reload time (ms)
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(10000.0)
    public double reloadtime;
    /**
     * Attribute ID of the resistance type v's this Ewar module.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int remoteresistanceid;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double repairmultiplierbonusmax;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double repairmultiplierbonuspercycle;
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
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredthermodynamicsskill;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {ReloadTime.INSTANCE, MediumRemoteRepFittingMultiplier.INSTANCE, CapacitorNeed.INSTANCE, MaxGroupFitted.INSTANCE, Duration.INSTANCE, Hp.INSTANCE, OverloadArmorDamageAmount.INSTANCE, ArmorDamageAmount.INSTANCE, RequiredSkill1Level.INSTANCE, RemoteResistanceID.INSTANCE, ChargeGroup1 .INSTANCE, HeatAbsorbtionRateModifier.INSTANCE, Power.INSTANCE, ChargedArmorDamageMultiplier.INSTANCE, TechLevel.INSTANCE, RepairMultiplierBonusPerCycle.INSTANCE, RepairMultiplierBonusMax.INSTANCE, Cpu.INSTANCE, MaxRange.INSTANCE, RequiredSkill1 .INSTANCE, OverloadSelfDurationBonus.INSTANCE, ChargeRate.INSTANCE, MetaLevelOld.INSTANCE, HeatDamage.INSTANCE, RequiredThermoDynamicsSkill.INSTANCE, FalloffEffectiveness.INSTANCE })));
    public static final AncillaryRemoteArmorRepairer.MetaGroup METAGROUP = new AncillaryRemoteArmorRepairer.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  84 :
            {
                return armordamageamount;
            }
            case  6 :
            {
                return capacitorneed;
            }
            case  604 :
            {
                return chargegroup1;
            }
            case  56 :
            {
                return chargerate;
            }
            case  1886 :
            {
                return chargedarmordamagemultiplier;
            }
            case  50 :
            {
                return cpu;
            }
            case  73 :
            {
                return duration;
            }
            case  2044 :
            {
                return falloffeffectiveness;
            }
            case  1180 :
            {
                return heatabsorbtionratemodifier;
            }
            case  1211 :
            {
                return heatdamage;
            }
            case  1544 :
            {
                return maxgroupfitted;
            }
            case  54 :
            {
                return maxrange;
            }
            case  2691 :
            {
                return mediumremoterepfittingmultiplier;
            }
            case  1230 :
            {
                return overloadarmordamageamount;
            }
            case  1206 :
            {
                return overloadselfdurationbonus;
            }
            case  30 :
            {
                return power;
            }
            case  1795 :
            {
                return reloadtime;
            }
            case  2138 :
            {
                return remoteresistanceid;
            }
            case  2797 :
            {
                return repairmultiplierbonusmax;
            }
            case  2796 :
            {
                return repairmultiplierbonuspercycle;
            }
            case  182 :
            {
                return requiredskill1;
            }
            case  277 :
            {
                return requiredskill1level;
            }
            case  1212 :
            {
                return requiredthermodynamicsskill;
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
    public IMetaGroup<AncillaryRemoteArmorRepairer> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AncillaryRemoteArmorRepairer>
    {
        public static final String RESOURCE_PATH = "SDE/types/module/AncillaryRemoteArmorRepairer.yaml";
        private Map<Integer, AncillaryRemoteArmorRepairer> cache = (null);

        @Override
        public IMetaCategory<? super AncillaryRemoteArmorRepairer> category() {
            return Module.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1698;
        }

        @Override
        public String getName() {
            return "AncillaryRemoteArmorRepairer";
        }

        @Override
        public synchronized Map<Integer, AncillaryRemoteArmorRepairer> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(AncillaryRemoteArmorRepairer.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, AncillaryRemoteArmorRepairer> types;
        }
    }
}
