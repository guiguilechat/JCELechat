package fr.guiguilechat.jcelechat.model.sde.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.types.structure.Citadel;
import fr.guiguilechat.jcelechat.model.sde.types.structure.EngineeringComplex;
import fr.guiguilechat.jcelechat.model.sde.types.structure.Refinery;
import fr.guiguilechat.jcelechat.model.sde.types.structure.UpwellCynoBeacon;
import fr.guiguilechat.jcelechat.model.sde.types.structure.UpwellCynoJammer;
import fr.guiguilechat.jcelechat.model.sde.types.structure.UpwellJumpGate;

public abstract class Structure
    extends EveType
{
    /**
     * Resistance to ECM. 0 gives Immunity.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double ECMResistance;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ArmorDamageLimit;
    /**
     * Multiplies EM damage taken by Armor. 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double ArmorEmDamageResonance;
    /**
     * Multiplies EXPLOSIVE damage taken by Armor. 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double ArmorExplosiveDamageResonance;
    /**
     * The number of hit points on the entities armor.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double ArmorHP;
    /**
     * Multiplies KINETIC damage taken by Armor. 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double ArmorKineticDamageResonance;
    /**
     * Multiplies THERMAL damage taken by Armor. 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double ArmorThermalDamageResonance;
    /**
     * DO NOT MESS WITH
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double ArmorUniformity;
    /**
     * Capacitor capacity
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double CapacitorCapacity;
    /**
     * The cargo space allowed
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Capacity;
    /**
     * Distance which players can deposit cargo into a structure
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int CargoDeliveryRange;
    /**
     * charge of module
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Charge;
    /**
     * CPU output of ship
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int CpuOutput;
    /**
     * Electro magnetic damage multiplier for shield and armor. Represented as "% Resistance" in the UI.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double EmDamageResonance;
    /**
     * Resistance against Energy Neutralizing and Nosferatu
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double EnergyWarfareResistance;
    /**
     * damage multiplier vs. explosive damagers.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double ExplosiveDamageResonance;
    /**
     * Defines whether an entity can be hacked or not.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Hackable;
    /**
     * tbd
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int HiSlots;
    /**
     * Armor hitpoint attribute used by structures as a workaround for implementing Standup layered plating stacking penalties
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(1)
    public int HiddenArmorHPMultiplier;
    /**
     * The maximum hitpoints of an object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Hp;
    /**
     * damage multiplier vs. kinetic damagers.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double KineticDamageResonance;
    /**
     * The number of remaining unused launcher slots.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int LauncherSlotsLeft;
    /**
     * The number of low power slots on the ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int LowSlots;
    /**
     * Integer that describes the types mass
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double Mass;
    /**
     * Maximum number of locked targets that the character or their ships electronics can handle at any given time.  Both have individual limits which apply separately.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MaxLockedTargets;
    /**
     * Maximum range at which the scanner can lock a target.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double MaxTargetRange;
    /**
     * The maximum possible target range.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(300000)
    public int MaximumRangeCap;
    /**
     * tbd
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MedSlots;
    /**
     * power output of power core
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int PowerOutput;
    /**
     * Radius of an object in meters
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Radius;
    /**
     * Amount of time taken to fully recharge the capacitor.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double RechargeRate;
    /**
     * Impedance against Remote assistance (sensor boosters, tracking computers and ECCM).
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double RemoteAssistanceImpedance;
    /**
     * Impedance against Remote Repair (shield, armor, hull and energy).
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double RemoteRepairImpedance;
    /**
     * The number of rig slots on the ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RigSlots;
    /**
     * Gravimetric strength.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double ScanGravimetricStrength;
    /**
     * Ladar strength.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double ScanLadarStrength;
    /**
     * Magnetometric strength.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double ScanMagnetometricStrength;
    /**
     * Radar strength.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double ScanRadarStrength;
    /**
     * The resolution that the vessel can target other objects at.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double ScanResolution;
    /**
     * Resistance against Remote Sensor Dampeners.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double SensorDampenerResistance;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ServiceSlots;
    /**
     * Amount of maximum shield HP on the item.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double ShieldCapacity;
    /**
     * DO NOT MESS WITH. Helper attribute for entities, stands in for the shield charge.
     * The amount of starting shield capacity of the NPC.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double ShieldCharge;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShieldDamageLimit;
    /**
     * Multiplies EM damage taken by shield
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double ShieldEmDamageResonance;
    /**
     * Multiplies EXPLOSIVE damage taken by Armor. 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double ShieldExplosiveDamageResonance;
    /**
     * Multiplies KINETIC damage taken by Armor. 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double ShieldKineticDamageResonance;
    /**
     * Amount of time taken to fully recharge the shield.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double ShieldRechargeRate;
    /**
     * Multiplies THERMAL damage taken by Shield. 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double ShieldThermalDamageResonance;
    /**
     * DO NOT MESS WITH This number is deducted from the %chance of the seeping to armor, to slow seep of damage through shield.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double ShieldUniformity;
    /**
     * Signature Radius is used for turret tracking and scanning.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(100.0)
    public double SignatureRadius;
    /**
     * Resistance against Stasis Webifiers
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double StasisWebifierResistance;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int StructureDamageLimit;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(1)
    public int StructureFullPowerStateHitpointMultiplier;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int StructurePowerStateArmorPlatingMultiplier;
    /**
     * Resistance against Target Painters
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double TargetPainterResistance;
    /**
     * damage multiplier vs. thermal.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double ThermalDamageResonance;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int TierDifficulty;
    /**
     * This number is deducted from the %chance of the seeping to armor, to slow seep of damage through shield.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Uniformity;
    /**
     * Resistance against Remote Weapon Disruptors.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double WeaponDisruptionResistance;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {fr.guiguilechat.jcelechat.model.sde.attributes.Mass.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.ShieldCapacity.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.Hackable.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.Uniformity.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.ServiceSlots.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.ShieldCharge.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.Hp.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.ArmorHP.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.PowerOutput.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.ArmorEmDamageResonance.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.LowSlots.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.ArmorExplosiveDamageResonance.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.ArmorUniformity.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.MedSlots.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.ArmorKineticDamageResonance.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.HiSlots.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.ArmorThermalDamageResonance.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.ShieldEmDamageResonance.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.ShieldExplosiveDamageResonance.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.ShieldKineticDamageResonance.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.ShieldThermalDamageResonance.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.Charge.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.MaximumRangeCap.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.Radius.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.Capacity.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.SignatureRadius.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.CpuOutput.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.ScanResolution.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.RechargeRate.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.StructureFullPowerStateHitpointMultiplier.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.HiddenArmorHPMultiplier.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.SensorDampenerResistance.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.MaxLockedTargets.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.WeaponDisruptionResistance.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.TargetPainterResistance.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.StasisWebifierResistance.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.RemoteRepairImpedance.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.MaxTargetRange.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.ECMResistance.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.ScanRadarStrength.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.ScanLadarStrength.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.ScanMagnetometricStrength.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.ScanGravimetricStrength.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.RemoteAssistanceImpedance.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.ShieldRechargeRate.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.CapacitorCapacity.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.ShieldUniformity.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.LauncherSlotsLeft.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.CargoDeliveryRange.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.KineticDamageResonance.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.ThermalDamageResonance.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.ExplosiveDamageResonance.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.RigSlots.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.EmDamageResonance.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.ShieldDamageLimit.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.ArmorDamageLimit.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.StructureDamageLimit.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.StructurePowerStateArmorPlatingMultiplier.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.EnergyWarfareResistance.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.TierDifficulty.INSTANCE })));
    public static final Structure.MetaCat METACAT = new Structure.MetaCat();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  2253 :
            {
                return ECMResistance;
            }
            case  2035 :
            {
                return ArmorDamageLimit;
            }
            case  267 :
            {
                return ArmorEmDamageResonance;
            }
            case  268 :
            {
                return ArmorExplosiveDamageResonance;
            }
            case  265 :
            {
                return ArmorHP;
            }
            case  269 :
            {
                return ArmorKineticDamageResonance;
            }
            case  270 :
            {
                return ArmorThermalDamageResonance;
            }
            case  524 :
            {
                return ArmorUniformity;
            }
            case  482 :
            {
                return CapacitorCapacity;
            }
            case  38 :
            {
                return Capacity;
            }
            case  2790 :
            {
                return CargoDeliveryRange;
            }
            case  18 :
            {
                return Charge;
            }
            case  48 :
            {
                return CpuOutput;
            }
            case  113 :
            {
                return EmDamageResonance;
            }
            case  2045 :
            {
                return EnergyWarfareResistance;
            }
            case  111 :
            {
                return ExplosiveDamageResonance;
            }
            case  1927 :
            {
                return Hackable;
            }
            case  14 :
            {
                return HiSlots;
            }
            case  2751 :
            {
                return HiddenArmorHPMultiplier;
            }
            case  9 :
            {
                return Hp;
            }
            case  109 :
            {
                return KineticDamageResonance;
            }
            case  101 :
            {
                return LauncherSlotsLeft;
            }
            case  12 :
            {
                return LowSlots;
            }
            case  4 :
            {
                return Mass;
            }
            case  192 :
            {
                return MaxLockedTargets;
            }
            case  76 :
            {
                return MaxTargetRange;
            }
            case  797 :
            {
                return MaximumRangeCap;
            }
            case  13 :
            {
                return MedSlots;
            }
            case  11 :
            {
                return PowerOutput;
            }
            case  162 :
            {
                return Radius;
            }
            case  55 :
            {
                return RechargeRate;
            }
            case  2135 :
            {
                return RemoteAssistanceImpedance;
            }
            case  2116 :
            {
                return RemoteRepairImpedance;
            }
            case  1137 :
            {
                return RigSlots;
            }
            case  211 :
            {
                return ScanGravimetricStrength;
            }
            case  209 :
            {
                return ScanLadarStrength;
            }
            case  210 :
            {
                return ScanMagnetometricStrength;
            }
            case  208 :
            {
                return ScanRadarStrength;
            }
            case  564 :
            {
                return ScanResolution;
            }
            case  2112 :
            {
                return SensorDampenerResistance;
            }
            case  2056 :
            {
                return ServiceSlots;
            }
            case  263 :
            {
                return ShieldCapacity;
            }
            case  264 :
            {
                return ShieldCharge;
            }
            case  2034 :
            {
                return ShieldDamageLimit;
            }
            case  271 :
            {
                return ShieldEmDamageResonance;
            }
            case  272 :
            {
                return ShieldExplosiveDamageResonance;
            }
            case  273 :
            {
                return ShieldKineticDamageResonance;
            }
            case  479 :
            {
                return ShieldRechargeRate;
            }
            case  274 :
            {
                return ShieldThermalDamageResonance;
            }
            case  484 :
            {
                return ShieldUniformity;
            }
            case  552 :
            {
                return SignatureRadius;
            }
            case  2115 :
            {
                return StasisWebifierResistance;
            }
            case  2036 :
            {
                return StructureDamageLimit;
            }
            case  2743 :
            {
                return StructureFullPowerStateHitpointMultiplier;
            }
            case  2805 :
            {
                return StructurePowerStateArmorPlatingMultiplier;
            }
            case  2114 :
            {
                return TargetPainterResistance;
            }
            case  110 :
            {
                return ThermalDamageResonance;
            }
            case  1919 :
            {
                return TierDifficulty;
            }
            case  136 :
            {
                return Uniformity;
            }
            case  2113 :
            {
                return WeaponDisruptionResistance;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaCategory<Structure> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<Structure>
    {

        @Override
        public int getCategoryId() {
            return  65;
        }

        @Override
        public String getName() {
            return "Structure";
        }

        @Override
        public Collection<IMetaGroup<? extends Structure>> groups() {
            return Arrays.asList(EngineeringComplex.METAGROUP, Refinery.METAGROUP, UpwellJumpGate.METAGROUP, Citadel.METAGROUP, UpwellCynoJammer.METAGROUP, UpwellCynoBeacon.METAGROUP);
        }
    }
}
