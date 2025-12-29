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

public class FlexShieldHardener
    extends Module
{
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshipgroup01;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshipgroup02;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshipgroup03;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshipgroup04;
    /**
     * Can be fitted to
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshipgroup05;
    /**
     * Can be fitted to
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshipgroup06;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshipgroup07;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshiptype1;
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
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double emdamageresistancebonus;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double explosivedamageresistancebonus;
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
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double kineticdamageresistancebonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxgroupfitted;
    /**
     * Authoring has been moved to FSD.
     * meta group of type
     * 
     *  3: Story-line (Cosmos)
     *  4: Faction
     *  5: Officer (rare asteroid NPCs)
     *  6: Deadspace
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int metagroupid;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int overloadhardeningbonus;
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
    public int requiredthermodynamicsskill;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double thermaldamageresistancebonus;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {ChargeSize.INSTANCE, ReloadTime.INSTANCE, CapacitorNeed.INSTANCE, MaxGroupFitted.INSTANCE, Duration.INSTANCE, Hp.INSTANCE, CanFitShipGroup05 .INSTANCE, CanFitShipGroup01 .INSTANCE, CanFitShipGroup02 .INSTANCE, CanFitShipGroup03 .INSTANCE, RequiredSkill1Level.INSTANCE, CanFitShipGroup04 .INSTANCE, RequiredSkill2Level.INSTANCE, CanFitShipType1 .INSTANCE, CanFitShipGroup06 .INSTANCE, EmDamageResistanceBonus.INSTANCE, CanFitShipGroup07 .INSTANCE, ExplosiveDamageResistanceBonus.INSTANCE, KineticDamageResistanceBonus.INSTANCE, ThermalDamageResistanceBonus.INSTANCE, ChargeGroup1 .INSTANCE, HeatAbsorbtionRateModifier.INSTANCE, MetaGroupID.INSTANCE, Power.INSTANCE, TechLevel.INSTANCE, Cpu.INSTANCE, RequiredSkill1 .INSTANCE, RequiredSkill2 .INSTANCE, OverloadHardeningBonus.INSTANCE, MetaLevelOld.INSTANCE, HeatDamage.INSTANCE, RequiredThermoDynamicsSkill.INSTANCE })));
    public static final FlexShieldHardener.MetaGroup METAGROUP = new FlexShieldHardener.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  1298 :
            {
                return canfitshipgroup01;
            }
            case  1299 :
            {
                return canfitshipgroup02;
            }
            case  1300 :
            {
                return canfitshipgroup03;
            }
            case  1301 :
            {
                return canfitshipgroup04;
            }
            case  1872 :
            {
                return canfitshipgroup05;
            }
            case  1879 :
            {
                return canfitshipgroup06;
            }
            case  1880 :
            {
                return canfitshipgroup07;
            }
            case  1302 :
            {
                return canfitshiptype1;
            }
            case  6 :
            {
                return capacitorneed;
            }
            case  604 :
            {
                return chargegroup1;
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
            case  984 :
            {
                return emdamageresistancebonus;
            }
            case  985 :
            {
                return explosivedamageresistancebonus;
            }
            case  1180 :
            {
                return heatabsorbtionratemodifier;
            }
            case  1211 :
            {
                return heatdamage;
            }
            case  986 :
            {
                return kineticdamageresistancebonus;
            }
            case  1544 :
            {
                return maxgroupfitted;
            }
            case  1692 :
            {
                return metagroupid;
            }
            case  1208 :
            {
                return overloadhardeningbonus;
            }
            case  30 :
            {
                return power;
            }
            case  1795 :
            {
                return reloadtime;
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
            case  1212 :
            {
                return requiredthermodynamicsskill;
            }
            case  987 :
            {
                return thermaldamageresistancebonus;
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
    public IMetaGroup<FlexShieldHardener> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<FlexShieldHardener>
    {
        public static final String RESOURCE_PATH = "SDE/types/module/FlexShieldHardener.yaml";
        private Map<Integer, FlexShieldHardener> cache = (null);

        @Override
        public IMetaCategory<? super FlexShieldHardener> category() {
            return Module.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1700;
        }

        @Override
        public String getName() {
            return "FlexShieldHardener";
        }

        @Override
        public synchronized Map<Integer, FlexShieldHardener> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(FlexShieldHardener.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, FlexShieldHardener> types;
        }
    }
}
