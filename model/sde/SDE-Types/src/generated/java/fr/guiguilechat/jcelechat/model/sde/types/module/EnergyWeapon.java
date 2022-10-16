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
import fr.guiguilechat.jcelechat.model.sde.attributes.AIIgnoreDronesBelowSignatureRadius;
import fr.guiguilechat.jcelechat.model.sde.attributes.AITankingModifierDrone;
import fr.guiguilechat.jcelechat.model.sde.attributes.AmmoLoaded;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipGroup01;
import fr.guiguilechat.jcelechat.model.sde.attributes.CapacitorNeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.ChargeGroup1;
import fr.guiguilechat.jcelechat.model.sde.attributes.ChargeGroup2;
import fr.guiguilechat.jcelechat.model.sde.attributes.ChargeSize;
import fr.guiguilechat.jcelechat.model.sde.attributes.Cpu;
import fr.guiguilechat.jcelechat.model.sde.attributes.DamageMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.EmDamage;
import fr.guiguilechat.jcelechat.model.sde.attributes.ExplosiveDamage;
import fr.guiguilechat.jcelechat.model.sde.attributes.Falloff;
import fr.guiguilechat.jcelechat.model.sde.attributes.HeatAbsorbtionRateModifier;
import fr.guiguilechat.jcelechat.model.sde.attributes.HeatDamage;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hp;
import fr.guiguilechat.jcelechat.model.sde.attributes.KineticDamage;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaGroupID;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaLevelOld;
import fr.guiguilechat.jcelechat.model.sde.attributes.OptimalSigRadius;
import fr.guiguilechat.jcelechat.model.sde.attributes.OverloadDamageModifier;
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
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill4;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill4Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredThermoDynamicsSkill;
import fr.guiguilechat.jcelechat.model.sde.attributes.ResistanceKiller;
import fr.guiguilechat.jcelechat.model.sde.attributes.ResistanceKillerHull;
import fr.guiguilechat.jcelechat.model.sde.attributes.Slots;
import fr.guiguilechat.jcelechat.model.sde.attributes.Speed;
import fr.guiguilechat.jcelechat.model.sde.attributes.TargetModule;
import fr.guiguilechat.jcelechat.model.sde.attributes.TechLevel;
import fr.guiguilechat.jcelechat.model.sde.attributes.ThermalDamage;
import fr.guiguilechat.jcelechat.model.sde.attributes.TrackingSpeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.TypeColorScheme;
import fr.guiguilechat.jcelechat.model.sde.types.Module;
import org.yaml.snakeyaml.Yaml;

public class EnergyWeapon
    extends Module
{
    /**
     * NPC'S with this attribute wont shoot drones with signature radius less than this value.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int aiignoredronesbelowsignatureradius;
    /**
     * Tanking modifier applied to drones if their owner is tanking. 1.0 is no modifier
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.699999988079071)
    public double aitankingmodifierdrone;
    /**
     * A temporary attribute for projectile/hybrid weapons to indicate which charges they have loaded when created in newbie ships ala ammo.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ammoloaded;
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
     * EM damage done.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double emdamage;
    /**
     * Explosive damage done.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double explosivedamage;
    /**
     * distance from maximum range at which accuracy has fallen by half
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double falloff;
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
     * Kinetic damage done.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double kineticdamage;
    /**
     * Distance below which range does not affect the to-hit equation.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double maxrange;
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
     * Prefered target signature. The base signature radius at which the turret's tracking speed is rated. 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1000)
    public int optimalsigradius;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int overloaddamagemodifier;
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
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill4;
    /**
     * Required skill level for skill 4
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill4level;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredthermodynamicsskill;
    /**
     * Display name is misleading, this attribute is used only for Shield and Armor, but for convenience sake only this one is used.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int resistancekiller;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int resistancekillerhull;
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
     * Thermal damage done.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double thermaldamage;
    /**
     * Weapon accuracy
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double trackingspeed;
    /**
     * The value of this attribute is a graphicsID which controls the color scheme of this type. It is used to apply said color scheme to items of other types whose gfx representation is tied in with the attribute holder. Example: Turrets on ships.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int typecolorscheme;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {ChargeSize.INSTANCE, DamageMultiplier.INSTANCE, ReloadTime.INSTANCE, RequiredSkill4 .INSTANCE, CapacitorNeed.INSTANCE, RequiredSkill4Level.INSTANCE, Hp.INSTANCE, CanFitShipGroup01 .INSTANCE, RequiredSkill1Level.INSTANCE, RequiredSkill2Level.INSTANCE, RequiredSkill3Level.INSTANCE, HeatAbsorbtionRateModifier.INSTANCE, MetaGroupID.INSTANCE, ChargeGroup1 .INSTANCE, ChargeGroup2 .INSTANCE, Falloff.INSTANCE, Power.INSTANCE, TrackingSpeed.INSTANCE, Radius.INSTANCE, TechLevel.INSTANCE, Capacity.INSTANCE, TypeColorScheme.INSTANCE, OptimalSigRadius.INSTANCE, Slots.INSTANCE, Cpu.INSTANCE, EmDamage.INSTANCE, Speed.INSTANCE, ExplosiveDamage.INSTANCE, OverloadRofBonus.INSTANCE, KineticDamage.INSTANCE, RequiredSkill1 .INSTANCE, MaxRange.INSTANCE, ThermalDamage.INSTANCE, RequiredSkill2 .INSTANCE, RequiredSkill3 .INSTANCE, AITankingModifierDrone.INSTANCE, MetaLevelOld.INSTANCE, OverloadDamageModifier.INSTANCE, ResistanceKiller.INSTANCE, HeatDamage.INSTANCE, ResistanceKillerHull.INSTANCE, RequiredThermoDynamicsSkill.INSTANCE, TargetModule.INSTANCE, AmmoLoaded.INSTANCE, AIIgnoreDronesBelowSignatureRadius.INSTANCE })));
    public static final EnergyWeapon.MetaGroup METAGROUP = new EnergyWeapon.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  1855 :
            {
                return aiignoredronesbelowsignatureradius;
            }
            case  1656 :
            {
                return aitankingmodifierdrone;
            }
            case  127 :
            {
                return ammoloaded;
            }
            case  1298 :
            {
                return canfitshipgroup01;
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
            case  114 :
            {
                return emdamage;
            }
            case  116 :
            {
                return explosivedamage;
            }
            case  158 :
            {
                return falloff;
            }
            case  1180 :
            {
                return heatabsorbtionratemodifier;
            }
            case  1211 :
            {
                return heatdamage;
            }
            case  117 :
            {
                return kineticdamage;
            }
            case  54 :
            {
                return maxrange;
            }
            case  1692 :
            {
                return metagroupid;
            }
            case  620 :
            {
                return optimalsigradius;
            }
            case  1210 :
            {
                return overloaddamagemodifier;
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
            case  1285 :
            {
                return requiredskill4;
            }
            case  1286 :
            {
                return requiredskill4level;
            }
            case  1212 :
            {
                return requiredthermodynamicsskill;
            }
            case  1978 :
            {
                return resistancekiller;
            }
            case  1979 :
            {
                return resistancekillerhull;
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
            case  118 :
            {
                return thermaldamage;
            }
            case  160 :
            {
                return trackingspeed;
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
    public IMetaGroup<EnergyWeapon> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<EnergyWeapon>
    {
        public static final String RESOURCE_PATH = "SDE/types/module/EnergyWeapon.yaml";
        private Map<String, EnergyWeapon> cache = (null);

        @Override
        public IMetaCategory<? super EnergyWeapon> category() {
            return Module.METACAT;
        }

        @Override
        public int getGroupId() {
            return  53;
        }

        @Override
        public String getName() {
            return "EnergyWeapon";
        }

        @Override
        public synchronized Map<String, EnergyWeapon> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(EnergyWeapon.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, EnergyWeapon> types;
        }
    }
}
