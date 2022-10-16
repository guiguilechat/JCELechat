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
import fr.guiguilechat.jcelechat.model.sde.attributes.CapacitorNeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.ChargeGroup1;
import fr.guiguilechat.jcelechat.model.sde.attributes.ChargeRate;
import fr.guiguilechat.jcelechat.model.sde.attributes.ChargeSize;
import fr.guiguilechat.jcelechat.model.sde.attributes.Cpu;
import fr.guiguilechat.jcelechat.model.sde.attributes.Duration;
import fr.guiguilechat.jcelechat.model.sde.attributes.FalloffEffectiveness;
import fr.guiguilechat.jcelechat.model.sde.attributes.HeatAbsorbtionRateModifier;
import fr.guiguilechat.jcelechat.model.sde.attributes.HeatDamage;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hp;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxGroupFitted;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.MediumRemoteRepFittingMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaLevelOld;
import fr.guiguilechat.jcelechat.model.sde.attributes.OverloadSelfDurationBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.Power;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.ReloadTime;
import fr.guiguilechat.jcelechat.model.sde.attributes.RemoteResistanceID;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredThermoDynamicsSkill;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.TechLevel;
import fr.guiguilechat.jcelechat.model.sde.types.Module;
import org.yaml.snakeyaml.Yaml;

public class AncillaryRemoteShieldBooster
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
     * The size of the charges that can fit in the turret/whatever.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int chargesize;
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
    /**
     * Bonus to shield.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double shieldbonus;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {ChargeSize.INSTANCE, ReloadTime.INSTANCE, MediumRemoteRepFittingMultiplier.INSTANCE, ShieldBonus.INSTANCE, CapacitorNeed.INSTANCE, MaxGroupFitted.INSTANCE, Duration.INSTANCE, Hp.INSTANCE, RequiredSkill1Level.INSTANCE, RemoteResistanceID.INSTANCE, HeatAbsorbtionRateModifier.INSTANCE, ChargeGroup1 .INSTANCE, Power.INSTANCE, Radius.INSTANCE, TechLevel.INSTANCE, Capacity.INSTANCE, Cpu.INSTANCE, RequiredSkill1 .INSTANCE, MaxRange.INSTANCE, OverloadSelfDurationBonus.INSTANCE, ChargeRate.INSTANCE, MetaLevelOld.INSTANCE, HeatDamage.INSTANCE, RequiredThermoDynamicsSkill.INSTANCE, FalloffEffectiveness.INSTANCE })));
    public static final AncillaryRemoteShieldBooster.MetaGroup METAGROUP = new AncillaryRemoteShieldBooster.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
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
            case  128 :
            {
                return chargesize;
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
            case  68 :
            {
                return shieldbonus;
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
    public IMetaGroup<AncillaryRemoteShieldBooster> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AncillaryRemoteShieldBooster>
    {
        public static final String RESOURCE_PATH = "SDE/types/module/AncillaryRemoteShieldBooster.yaml";
        private Map<String, AncillaryRemoteShieldBooster> cache = (null);

        @Override
        public IMetaCategory<? super AncillaryRemoteShieldBooster> category() {
            return Module.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1697;
        }

        @Override
        public String getName() {
            return "AncillaryRemoteShieldBooster";
        }

        @Override
        public synchronized Map<String, AncillaryRemoteShieldBooster> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(AncillaryRemoteShieldBooster.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AncillaryRemoteShieldBooster> types;
        }
    }
}
