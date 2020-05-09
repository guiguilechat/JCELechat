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
import fr.guiguilechat.jcelechat.model.sde.types.drone.CombatDrone;
import fr.guiguilechat.jcelechat.model.sde.types.drone.ElectronicWarfareDrone;
import fr.guiguilechat.jcelechat.model.sde.types.drone.EnergyNeutralizerDrone;
import fr.guiguilechat.jcelechat.model.sde.types.drone.LogisticDrone;
import fr.guiguilechat.jcelechat.model.sde.types.drone.MiningDrone;
import fr.guiguilechat.jcelechat.model.sde.types.drone.SalvageDrone;
import fr.guiguilechat.jcelechat.model.sde.types.drone.StasisWebifyingDrone;

public abstract class Drone
    extends EveType
{
    /**
     * The number of hit points on the entities armor.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double ArmorHP;
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
     * charge of module
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Charge;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DroneBandwidthUsed;
    /**
     * Electro magnetic damage multiplier for shield and armor. Represented as "% Resistance" in the UI.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double EmDamageResonance;
    /**
     * damage multiplier vs. explosive damagers.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double ExplosiveDamageResonance;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterAbilityAntiFighterMissileResistance;
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
     * Maximum velocity of ship
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double MaxVelocity;
    /**
     * The distance at which to react when relevant objects come within range.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ProximityRange;
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
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1;
    /**
     * Required skill level for skill 1
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1Level;
    /**
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill2;
    /**
     * Required skill level for skill 2
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill2Level;
    /**
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill3;
    /**
     * Required skill level for skill 3
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill3Level;
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
     * Amount of time taken to fully recharge the shield.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double ShieldRechargeRate;
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
     * DO NOT MESS WITH
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double StructureUniformity;
    /**
     * Authoring has been moved to FSD
     * Tech level of an item
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int TechLevel;
    /**
     * damage multiplier vs. thermal.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double ThermalDamageResonance;
    /**
     * This number is deducted from the %chance of the seeping to armor, to slow seep of damage through shield.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Uniformity;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {fr.guiguilechat.jcelechat.model.sde.attributes.Mass.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.ShieldCapacity.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.Uniformity.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.ShieldCharge.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.Hp.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.ArmorHP.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.ArmorUniformity.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityAntiFighterMissileResistance.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.StructureUniformity.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.Charge.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill2Level.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill3Level.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.ProximityRange.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.Radius.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.MaxVelocity.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.TechLevel.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.Capacity.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.SignatureRadius.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1 .INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill2 .INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.RechargeRate.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill3 .INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.MaxLockedTargets.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.ScanRadarStrength.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.ScanLadarStrength.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.ScanMagnetometricStrength.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.ScanGravimetricStrength.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.ShieldRechargeRate.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.CapacitorCapacity.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.ShieldUniformity.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.KineticDamageResonance.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.ThermalDamageResonance.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.ExplosiveDamageResonance.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.EmDamageResonance.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.DroneBandwidthUsed.INSTANCE })));
    public static final Drone.MetaCat METACAT = new Drone.MetaCat();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  265 :
            {
                return ArmorHP;
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
            case  18 :
            {
                return Charge;
            }
            case  1272 :
            {
                return DroneBandwidthUsed;
            }
            case  113 :
            {
                return EmDamageResonance;
            }
            case  111 :
            {
                return ExplosiveDamageResonance;
            }
            case  2189 :
            {
                return FighterAbilityAntiFighterMissileResistance;
            }
            case  9 :
            {
                return Hp;
            }
            case  109 :
            {
                return KineticDamageResonance;
            }
            case  4 :
            {
                return Mass;
            }
            case  192 :
            {
                return MaxLockedTargets;
            }
            case  37 :
            {
                return MaxVelocity;
            }
            case  154 :
            {
                return ProximityRange;
            }
            case  162 :
            {
                return Radius;
            }
            case  55 :
            {
                return RechargeRate;
            }
            case  182 :
            {
                return RequiredSkill1;
            }
            case  277 :
            {
                return RequiredSkill1Level;
            }
            case  183 :
            {
                return RequiredSkill2;
            }
            case  278 :
            {
                return RequiredSkill2Level;
            }
            case  184 :
            {
                return RequiredSkill3;
            }
            case  279 :
            {
                return RequiredSkill3Level;
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
            case  263 :
            {
                return ShieldCapacity;
            }
            case  264 :
            {
                return ShieldCharge;
            }
            case  479 :
            {
                return ShieldRechargeRate;
            }
            case  484 :
            {
                return ShieldUniformity;
            }
            case  552 :
            {
                return SignatureRadius;
            }
            case  525 :
            {
                return StructureUniformity;
            }
            case  422 :
            {
                return TechLevel;
            }
            case  110 :
            {
                return ThermalDamageResonance;
            }
            case  136 :
            {
                return Uniformity;
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
    public IMetaCategory<Drone> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<Drone>
    {

        @Override
        public int getCategoryId() {
            return  18;
        }

        @Override
        public String getName() {
            return "Drone";
        }

        @Override
        public Collection<IMetaGroup<? extends Drone>> groups() {
            return Arrays.asList(CombatDrone.METAGROUP, MiningDrone.METAGROUP, EnergyNeutralizerDrone.METAGROUP, ElectronicWarfareDrone.METAGROUP, LogisticDrone.METAGROUP, StasisWebifyingDrone.METAGROUP, SalvageDrone.METAGROUP);
        }
    }
}
