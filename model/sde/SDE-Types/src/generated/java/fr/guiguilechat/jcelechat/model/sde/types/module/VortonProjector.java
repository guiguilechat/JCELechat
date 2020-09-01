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
import fr.guiguilechat.jcelechat.model.sde.attributes.AccuracyBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.AoeCloudSize;
import fr.guiguilechat.jcelechat.model.sde.attributes.AoeDamageReductionFactor;
import fr.guiguilechat.jcelechat.model.sde.attributes.AoeVelocity;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipType1;
import fr.guiguilechat.jcelechat.model.sde.attributes.CapacitorNeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.ChargeGroup1;
import fr.guiguilechat.jcelechat.model.sde.attributes.ChargeGroup2;
import fr.guiguilechat.jcelechat.model.sde.attributes.ChargeRate;
import fr.guiguilechat.jcelechat.model.sde.attributes.ChargeSize;
import fr.guiguilechat.jcelechat.model.sde.attributes.Cpu;
import fr.guiguilechat.jcelechat.model.sde.attributes.DamageMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.HeatAbsorbtionRateModifier;
import fr.guiguilechat.jcelechat.model.sde.attributes.HeatDamage;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hp;
import fr.guiguilechat.jcelechat.model.sde.attributes.MainColor;
import fr.guiguilechat.jcelechat.model.sde.attributes.Mass;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaLevelOld;
import fr.guiguilechat.jcelechat.model.sde.attributes.OverloadRofBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.Power;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.ReloadTime;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill2;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill2Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill3;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill3Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredThermoDynamicsSkill;
import fr.guiguilechat.jcelechat.model.sde.attributes.Slots;
import fr.guiguilechat.jcelechat.model.sde.attributes.Speed;
import fr.guiguilechat.jcelechat.model.sde.attributes.TargetModule;
import fr.guiguilechat.jcelechat.model.sde.attributes.TechLevel;
import fr.guiguilechat.jcelechat.model.sde.attributes.TypeColorScheme;
import fr.guiguilechat.jcelechat.model.sde.attributes.VortonArcRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.VortonArcTargets;
import fr.guiguilechat.jcelechat.model.sde.types.Module;
import org.yaml.snakeyaml.Yaml;

public class VortonProjector
    extends Module
{
    /**
     * The maximum distance from the original target from which additional targets can receive arc damage
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(1)
    public int vortonarcrange;
    /**
     * Maximum number of arc targets hit.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(1)
    public int vortonarctargets;
    /**
     * Something to do with accuracy.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int accuracybonus;
    /**
     * Size of the damage cloud caused by impact.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int aoecloudsize;
    /**
     * Missile Damage Modifier. Smaller is better (Don't use less than 0.5)
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double aoedamagereductionfactor;
    /**
     * Velocity of the damage cloud created on impact.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double aoevelocity;
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
     * One of the groups of charge this launcher can be loaded with.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int chargegroup2;
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
     * Damage multiplier.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double damagemultiplier;
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
     * The main color of a ship type.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maincolor;
    /**
     * Distance below which range does not affect the to-hit equation.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double maxrange;
    /**
     * Authoring has been moved to FSD
     * The ranking of the module within its tech level
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int metalevelold;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int overloadrofbonus;
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
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill3;
    /**
     * Required skill level for skill 3
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill3level;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredthermodynamicsskill;
    /**
     * The number of slots this module requires.  Only used for launchers, bays and turrets.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int slots;
    /**
     * Time in milliseconds between possible activations
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double speed;
    /**
     * GroupID of module targeted by this weapon
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int targetmodule;
    /**
     * Authoring has been moved to FSD
     * Tech level of an item
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int techlevel;
    /**
     * The value of this attribute is a graphicsID which controls the color scheme of this type. It is used to apply said color scheme to items of other types whose gfx representation is tied in with the attribute holder. Example: Turrets on ships.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int typecolorscheme;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {ChargeSize.INSTANCE, DamageMultiplier.INSTANCE, ReloadTime.INSTANCE, Mass.INSTANCE, CapacitorNeed.INSTANCE, AoeDamageReductionFactor.INSTANCE, Hp.INSTANCE, AoeVelocity.INSTANCE, AoeCloudSize.INSTANCE, RequiredSkill1Level.INSTANCE, CanFitShipType1 .INSTANCE, RequiredSkill2Level.INSTANCE, RequiredSkill3Level.INSTANCE, HeatAbsorbtionRateModifier.INSTANCE, VortonArcRange.INSTANCE, ChargeGroup1 .INSTANCE, VortonArcTargets.INSTANCE, ChargeGroup2 .INSTANCE, Power.INSTANCE, Radius.INSTANCE, TechLevel.INSTANCE, Capacity.INSTANCE, TypeColorScheme.INSTANCE, Slots.INSTANCE, Cpu.INSTANCE, Speed.INSTANCE, OverloadRofBonus.INSTANCE, RequiredSkill1 .INSTANCE, MaxRange.INSTANCE, RequiredSkill2 .INSTANCE, ChargeRate.INSTANCE, RequiredSkill3 .INSTANCE, MetaLevelOld.INSTANCE, HeatDamage.INSTANCE, RequiredThermoDynamicsSkill.INSTANCE, MainColor.INSTANCE, TargetModule.INSTANCE, AccuracyBonus.INSTANCE })));
    public static final VortonProjector.MetaGroup METAGROUP = new VortonProjector.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  3036 :
            {
                return vortonarcrange;
            }
            case  3037 :
            {
                return vortonarctargets;
            }
            case  63 :
            {
                return accuracybonus;
            }
            case  654 :
            {
                return aoecloudsize;
            }
            case  1353 :
            {
                return aoedamagereductionfactor;
            }
            case  653 :
            {
                return aoevelocity;
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
            case  605 :
            {
                return chargegroup2;
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
            case  64 :
            {
                return damagemultiplier;
            }
            case  1180 :
            {
                return heatabsorbtionratemodifier;
            }
            case  1211 :
            {
                return heatdamage;
            }
            case  124 :
            {
                return maincolor;
            }
            case  54 :
            {
                return maxrange;
            }
            case  633 :
            {
                return metalevelold;
            }
            case  1205 :
            {
                return overloadrofbonus;
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
            case  184 :
            {
                return requiredskill3;
            }
            case  279 :
            {
                return requiredskill3level;
            }
            case  1212 :
            {
                return requiredthermodynamicsskill;
            }
            case  47 :
            {
                return slots;
            }
            case  51 :
            {
                return speed;
            }
            case  61 :
            {
                return targetmodule;
            }
            case  422 :
            {
                return techlevel;
            }
            case  1768 :
            {
                return typecolorscheme;
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
    public IMetaGroup<VortonProjector> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<VortonProjector>
    {
        public static final String RESOURCE_PATH = "SDE/types/module/VortonProjector.yaml";
        private Map<String, VortonProjector> cache = (null);

        @Override
        public IMetaCategory<? super VortonProjector> category() {
            return Module.METACAT;
        }

        @Override
        public int getGroupId() {
            return  4060;
        }

        @Override
        public String getName() {
            return "VortonProjector";
        }

        @Override
        public synchronized Map<String, VortonProjector> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(VortonProjector.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, VortonProjector> types;
        }
    }
}
