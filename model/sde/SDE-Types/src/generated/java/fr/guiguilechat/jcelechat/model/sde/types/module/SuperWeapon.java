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
import fr.guiguilechat.jcelechat.model.sde.attributes.BuffDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanCloak;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipGroup01;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipType1;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipType2;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipType3;
import fr.guiguilechat.jcelechat.model.sde.attributes.CapacitorNeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.ConsumptionQuantity;
import fr.guiguilechat.jcelechat.model.sde.attributes.ConsumptionType;
import fr.guiguilechat.jcelechat.model.sde.attributes.Cpu;
import fr.guiguilechat.jcelechat.model.sde.attributes.DamageDelayDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.DisallowActivateOnWarp;
import fr.guiguilechat.jcelechat.model.sde.attributes.DisallowDocking;
import fr.guiguilechat.jcelechat.model.sde.attributes.DisallowEarlyDeactivation;
import fr.guiguilechat.jcelechat.model.sde.attributes.DisallowInEmpireSpace;
import fr.guiguilechat.jcelechat.model.sde.attributes.DisallowRepeatingActivation;
import fr.guiguilechat.jcelechat.model.sde.attributes.DisallowTethering;
import fr.guiguilechat.jcelechat.model.sde.attributes.DoomsdayAOERange;
import fr.guiguilechat.jcelechat.model.sde.attributes.DoomsdayAOEShape;
import fr.guiguilechat.jcelechat.model.sde.attributes.DoomsdayDamageCycleTime;
import fr.guiguilechat.jcelechat.model.sde.attributes.DoomsdayDamageDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.DoomsdayDamageRadius;
import fr.guiguilechat.jcelechat.model.sde.attributes.DoomsdayEnergyNeutAmount;
import fr.guiguilechat.jcelechat.model.sde.attributes.DoomsdayEnergyNeutRadius;
import fr.guiguilechat.jcelechat.model.sde.attributes.DoomsdayEnergyNeutResistanceID;
import fr.guiguilechat.jcelechat.model.sde.attributes.DoomsdayEnergyNeutSignatureRadius;
import fr.guiguilechat.jcelechat.model.sde.attributes.DoomsdayImmobilityDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.DoomsdayNoJumpOrCloakDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.DoomsdayRangeIsFixed;
import fr.guiguilechat.jcelechat.model.sde.attributes.DoomsdayWarningDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.Duration;
import fr.guiguilechat.jcelechat.model.sde.attributes.EffectDeactivationDelay;
import fr.guiguilechat.jcelechat.model.sde.attributes.EmDamage;
import fr.guiguilechat.jcelechat.model.sde.attributes.ExplosiveDamage;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hp;
import fr.guiguilechat.jcelechat.model.sde.attributes.IsPointTargeted;
import fr.guiguilechat.jcelechat.model.sde.attributes.JumpDelayDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.KineticDamage;
import fr.guiguilechat.jcelechat.model.sde.attributes.Mass;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxGroupActive;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxGroupFitted;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxTypeFitted;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaLevelOld;
import fr.guiguilechat.jcelechat.model.sde.attributes.ModuleReactivationDelay;
import fr.guiguilechat.jcelechat.model.sde.attributes.PanicDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.Power;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill2;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill2Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.SiegeModeWarpStatus;
import fr.guiguilechat.jcelechat.model.sde.attributes.SignatureRadius;
import fr.guiguilechat.jcelechat.model.sde.attributes.SpeedFactor;
import fr.guiguilechat.jcelechat.model.sde.attributes.TechLevel;
import fr.guiguilechat.jcelechat.model.sde.attributes.ThermalDamage;
import fr.guiguilechat.jcelechat.model.sde.types.Module;
import org.yaml.snakeyaml.Yaml;

public class SuperWeapon
    extends Module
{
    /**
     * Applied modifier duration
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int buffduration;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int cancloak;
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
    public int canfitshiptype1;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshiptype2;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshiptype3;
    /**
     * The amount of charge used from the capacitor for a module activation.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
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
    @DefaultDoubleValue(0.0)
    public double cpu;
    /**
     * The delay in ms until the damage is done to the target. (Allows some FX to be played)
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(10000)
    public int damagedelayduration;
    /**
     * Stops the module from being activated if the ship is aligning to warp.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int disallowactivateonwarp;
    /**
     * If this ship attribute is NOT 0 then they will be prevented from docking in stations or structures.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int disallowdocking;
    /**
     * Signifies that this module if activated, will prevent ejection from the ship it is fitted to and extend the log out ship removal timer.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int disallowearlydeactivation;
    /**
     * If set on a charge or module type, will prevent it from being activated in empire space.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int disallowinempirespace;
    /**
     * If set, this module cannot be activated and made to autorepeat.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int disallowrepeatingactivation;
    /**
     * If this ship attribute is NOT 0 then they will be prevented from activating the structure tethering.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int disallowtethering;
    /**
     * Radius of the AOE Effect
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int doomsdayaoerange;
    /**
     *  1: Fixed Cylinder (Beam)
     *  2: Cylinder moving in an arc (Slash)
     *  3: Fixed Cone
     *  4: Projected Sphere
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int doomsdayaoeshape;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int doomsdaydamagecycletime;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int doomsdaydamageduration;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int doomsdaydamageradius;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int doomsdayenergyneutamount;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int doomsdayenergyneutradius;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int doomsdayenergyneutresistanceid;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int doomsdayenergyneutsignatureradius;
    /**
     * Length of Immobility time.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int doomsdayimmobilityduration;
    /**
     * Length of No Jump Or Cloak time.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int doomsdaynojumporcloakduration;
    /**
     * Determines whether the maxRange attribute is a fixed length or a maximum length of the effect
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int doomsdayrangeisfixed;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int doomsdaywarningduration;
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
    @DefaultIntValue(0)
    public int effectdeactivationdelay;
    /**
     * EM damage done.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double emdamage;
    /**
     * Explosive damage done.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double explosivedamage;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int ispointtargeted;
    /**
     * Delay in seconds; until you can jump again.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int jumpdelayduration;
    /**
     * Kinetic damage done.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double kineticdamage;
    /**
     * Maximum modules of same group that can be activated at same time, 0 = no limit, 1 = 1
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxgroupactive;
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
    @DefaultDoubleValue(0.0)
    public double maxrange;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxtypefitted;
    /**
     * Authoring has been moved to FSD
     * The ranking of the module within its tech level
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int metalevelold;
    /**
     * Amount of time that has to be waited after the deactivation of this module until it can be reactivated.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int modulereactivationdelay;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int panicduration;
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
    public int siegemodewarpstatus;
    /**
     * Signature Radius is used for turret tracking and scanning.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(100.0)
    public double signatureradius;
    /**
     * Factor by which topspeed increases.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double speedfactor;
    /**
     * Authoring has been moved to FSD
     * Tech level of an item
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int techlevel;
    /**
     * Thermal damage done.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double thermaldamage;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {Mass.INSTANCE, CapacitorNeed.INSTANCE, MaxGroupFitted.INSTANCE, Hp.INSTANCE, DisallowEarlyDeactivation.INSTANCE, CanCloak.INSTANCE, CanFitShipGroup01 .INSTANCE, SpeedFactor.INSTANCE, RequiredSkill1Level.INSTANCE, CanFitShipType1 .INSTANCE, RequiredSkill2Level.INSTANCE, CanFitShipType2 .INSTANCE, CanFitShipType3 .INSTANCE, ModuleReactivationDelay.INSTANCE, Power.INSTANCE, Radius.INSTANCE, TechLevel.INSTANCE, Capacity.INSTANCE, DisallowTethering.INSTANCE, SignatureRadius.INSTANCE, EffectDeactivationDelay.INSTANCE, DamageDelayDuration.INSTANCE, DoomsdayEnergyNeutResistanceID.INSTANCE, DisallowDocking.INSTANCE, DisallowInEmpireSpace.INSTANCE, Cpu.INSTANCE, MaxRange.INSTANCE, RequiredSkill1 .INSTANCE, RequiredSkill2 .INSTANCE, JumpDelayDuration.INSTANCE, ConsumptionType.INSTANCE, Duration.INSTANCE, ConsumptionQuantity.INSTANCE, DoomsdayEnergyNeutRadius.INSTANCE, DoomsdayEnergyNeutAmount.INSTANCE, SiegeModeWarpStatus.INSTANCE, DoomsdayEnergyNeutSignatureRadius.INSTANCE, DoomsdayWarningDuration.INSTANCE, DoomsdayDamageRadius.INSTANCE, DoomsdayDamageDuration.INSTANCE, DoomsdayDamageCycleTime.INSTANCE, IsPointTargeted.INSTANCE, DisallowActivateOnWarp.INSTANCE, PanicDuration.INSTANCE, DoomsdayAOERange.INSTANCE, BuffDuration.INSTANCE, EmDamage.INSTANCE, ExplosiveDamage.INSTANCE, KineticDamage.INSTANCE, DisallowRepeatingActivation.INSTANCE, ThermalDamage.INSTANCE, MetaLevelOld.INSTANCE, DoomsdayNoJumpOrCloakDuration.INSTANCE, MaxGroupActive.INSTANCE, DoomsdayImmobilityDuration.INSTANCE, DoomsdayAOEShape.INSTANCE, DoomsdayRangeIsFixed.INSTANCE, MaxTypeFitted.INSTANCE })));
    public static final SuperWeapon.MetaGroup METAGROUP = new SuperWeapon.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  2535 :
            {
                return buffduration;
            }
            case  1163 :
            {
                return cancloak;
            }
            case  1298 :
            {
                return canfitshipgroup01;
            }
            case  1302 :
            {
                return canfitshiptype1;
            }
            case  1303 :
            {
                return canfitshiptype2;
            }
            case  1304 :
            {
                return canfitshiptype3;
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
            case  1839 :
            {
                return damagedelayduration;
            }
            case  1245 :
            {
                return disallowactivateonwarp;
            }
            case  2354 :
            {
                return disallowdocking;
            }
            case  906 :
            {
                return disallowearlydeactivation;
            }
            case  1074 :
            {
                return disallowinempirespace;
            }
            case  1014 :
            {
                return disallowrepeatingactivation;
            }
            case  2343 :
            {
                return disallowtethering;
            }
            case  2279 :
            {
                return doomsdayaoerange;
            }
            case  2429 :
            {
                return doomsdayaoeshape;
            }
            case  2265 :
            {
                return doomsdaydamagecycletime;
            }
            case  2264 :
            {
                return doomsdaydamageduration;
            }
            case  2263 :
            {
                return doomsdaydamageradius;
            }
            case  2260 :
            {
                return doomsdayenergyneutamount;
            }
            case  2259 :
            {
                return doomsdayenergyneutradius;
            }
            case  2609 :
            {
                return doomsdayenergyneutresistanceid;
            }
            case  2261 :
            {
                return doomsdayenergyneutsignatureradius;
            }
            case  2428 :
            {
                return doomsdayimmobilityduration;
            }
            case  2427 :
            {
                return doomsdaynojumporcloakduration;
            }
            case  2430 :
            {
                return doomsdayrangeisfixed;
            }
            case  2262 :
            {
                return doomsdaywarningduration;
            }
            case  73 :
            {
                return duration;
            }
            case  1579 :
            {
                return effectdeactivationdelay;
            }
            case  114 :
            {
                return emdamage;
            }
            case  116 :
            {
                return explosivedamage;
            }
            case  2269 :
            {
                return ispointtargeted;
            }
            case  1221 :
            {
                return jumpdelayduration;
            }
            case  117 :
            {
                return kineticdamage;
            }
            case  763 :
            {
                return maxgroupactive;
            }
            case  1544 :
            {
                return maxgroupfitted;
            }
            case  54 :
            {
                return maxrange;
            }
            case  2431 :
            {
                return maxtypefitted;
            }
            case  633 :
            {
                return metalevelold;
            }
            case  669 :
            {
                return modulereactivationdelay;
            }
            case  2788 :
            {
                return panicduration;
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
            case  852 :
            {
                return siegemodewarpstatus;
            }
            case  552 :
            {
                return signatureradius;
            }
            case  20 :
            {
                return speedfactor;
            }
            case  422 :
            {
                return techlevel;
            }
            case  118 :
            {
                return thermaldamage;
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
    public IMetaGroup<SuperWeapon> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<SuperWeapon>
    {
        public static final String RESOURCE_PATH = "SDE/types/module/SuperWeapon.yaml";
        private Map<String, SuperWeapon> cache = (null);

        @Override
        public IMetaCategory<? super SuperWeapon> category() {
            return Module.METACAT;
        }

        @Override
        public int getGroupId() {
            return  588;
        }

        @Override
        public String getName() {
            return "SuperWeapon";
        }

        @Override
        public synchronized Map<String, SuperWeapon> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(SuperWeapon.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, SuperWeapon> types;
        }
    }
}
