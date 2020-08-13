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
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.ActivationBlockedStrenght;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipGroup01;
import fr.guiguilechat.jcelechat.model.sde.attributes.CapacitorNeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.Cpu;
import fr.guiguilechat.jcelechat.model.sde.attributes.DisallowInEmpireSpace;
import fr.guiguilechat.jcelechat.model.sde.attributes.Duration;
import fr.guiguilechat.jcelechat.model.sde.attributes.HeatAbsorbtionRateModifier;
import fr.guiguilechat.jcelechat.model.sde.attributes.HeatDamage;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hp;
import fr.guiguilechat.jcelechat.model.sde.attributes.Mass;
import fr.guiguilechat.jcelechat.model.sde.attributes.MassBonusPercentage;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxVelocityBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxVelocityMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaLevelOld;
import fr.guiguilechat.jcelechat.model.sde.attributes.Power;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill2;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill2Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.SpeedBoostFactorBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.SpeedFactorBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.TechLevel;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarpScrambleStrength;
import fr.guiguilechat.jcelechat.model.sde.types.Module;
import org.yaml.snakeyaml.Yaml;

public class MassEntanglers
    extends Module
{
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int activationblockedstrenght;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshipgroup01;
    /**
     * The amount of charge used from the capacitor for a module activation.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double capacitorneed;
    /**
     * CPU need of module
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double cpu;
    /**
     * If set on a charge or module type, will prevent it from being activated in empire space.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int disallowinempirespace;
    /**
     * Length of activation time.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double duration;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double heatabsorbtionratemodifier;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double heatdamage;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int massbonuspercentage;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxvelocitybonus;
    /**
     * Maximum velocity multiplier
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double maxvelocitymultiplier;
    /**
     * Authoring has been moved to FSD
     * The ranking of the module within its tech level
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int metalevelold;
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
    public int requiredskill2;
    /**
     * Required skill level for skill 2
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill2level;
    /**
     * Afterburner and Microwarpdrive Thrust Bonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int speedboostfactorbonus;
    /**
     * Afterburner and Microwarpdrive Max Velocity Bonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int speedfactorbonus;
    /**
     * Authoring has been moved to FSD
     * Tech level of an item
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int techlevel;
    /**
     * Amount to modify ships warp scramble status by.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int warpscramblestrength;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {Radius.INSTANCE, Mass.INSTANCE, CapacitorNeed.INSTANCE, TechLevel.INSTANCE, ActivationBlockedStrenght.INSTANCE, Capacity.INSTANCE, Duration.INSTANCE, WarpScrambleStrength.INSTANCE, Hp.INSTANCE, MaxVelocityBonus.INSTANCE, MassBonusPercentage.INSTANCE, SpeedFactorBonus.INSTANCE, CanFitShipGroup01 .INSTANCE, Cpu.INSTANCE, DisallowInEmpireSpace.INSTANCE, RequiredSkill2Level.INSTANCE, SpeedBoostFactorBonus.INSTANCE, RequiredSkill2 .INSTANCE, MetaLevelOld.INSTANCE, HeatDamage.INSTANCE, HeatAbsorbtionRateModifier.INSTANCE, Power.INSTANCE, MaxVelocityMultiplier.INSTANCE })));
    public static final MassEntanglers.MetaGroup METAGROUP = new MassEntanglers.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  1350 :
            {
                return activationblockedstrenght;
            }
            case  1298 :
            {
                return canfitshipgroup01;
            }
            case  6 :
            {
                return capacitorneed;
            }
            case  50 :
            {
                return cpu;
            }
            case  1074 :
            {
                return disallowinempirespace;
            }
            case  73 :
            {
                return duration;
            }
            case  1180 :
            {
                return heatabsorbtionratemodifier;
            }
            case  1211 :
            {
                return heatdamage;
            }
            case  1131 :
            {
                return massbonuspercentage;
            }
            case  2603 :
            {
                return maxvelocitybonus;
            }
            case  1470 :
            {
                return maxvelocitymultiplier;
            }
            case  633 :
            {
                return metalevelold;
            }
            case  30 :
            {
                return power;
            }
            case  183 :
            {
                return requiredskill2;
            }
            case  278 :
            {
                return requiredskill2level;
            }
            case  1270 :
            {
                return speedboostfactorbonus;
            }
            case  1164 :
            {
                return speedfactorbonus;
            }
            case  422 :
            {
                return techlevel;
            }
            case  105 :
            {
                return warpscramblestrength;
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
    public IMetaGroup<MassEntanglers> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MassEntanglers>
    {
        public static final String RESOURCE_PATH = "SDE/types/module/MassEntanglers.yaml";
        private Map<String, MassEntanglers> cache = (null);

        @Override
        public IMetaCategory<? super MassEntanglers> category() {
            return Module.METACAT;
        }

        @Override
        public int getGroupId() {
            return  2008;
        }

        @Override
        public String getName() {
            return "MassEntanglers";
        }

        @Override
        public synchronized Map<String, MassEntanglers> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(MassEntanglers.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, MassEntanglers> types;
        }
    }
}
