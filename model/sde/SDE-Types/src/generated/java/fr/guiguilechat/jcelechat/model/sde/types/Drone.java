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
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultRealValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorHP;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorUniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.CapacitorCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.Charge;
import fr.guiguilechat.jcelechat.model.sde.attributes.DroneBandwidthUsed;
import fr.guiguilechat.jcelechat.model.sde.attributes.EmDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ExplosiveDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityAntiFighterMissileResistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hp;
import fr.guiguilechat.jcelechat.model.sde.attributes.KineticDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxLockedTargets;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxVelocity;
import fr.guiguilechat.jcelechat.model.sde.attributes.ProximityRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.RechargeRate;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill2;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill2Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill3;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill3Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanGravimetricStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanLadarStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanMagnetometricStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanRadarStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldCharge;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldRechargeRate;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldUniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.SignatureRadius;
import fr.guiguilechat.jcelechat.model.sde.attributes.StructureUniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.TechLevel;
import fr.guiguilechat.jcelechat.model.sde.attributes.ThermalDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.Uniformity;
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
    @DefaultRealValue(0.0)
    public double armorhp;
    /**
     * DO NOT MESS WITH
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double armoruniformity;
    /**
     * Capacitor capacity
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double capacitorcapacity;
    /**
     * The cargo space allowed
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double capacity;
    /**
     * charge of module
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int charge;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int dronebandwidthused;
    /**
     * Electro magnetic damage multiplier for shield and armor. Represented as "% Resistance" in the UI.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double emdamageresonance;
    /**
     * damage multiplier vs. explosive damagers.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double explosivedamageresonance;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fighterabilityantifightermissileresistance;
    /**
     * The maximum hitpoints of an object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double hp;
    /**
     * damage multiplier vs. kinetic damagers.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double kineticdamageresonance;
    /**
     * Maximum number of locked targets that the character or their ships electronics can handle at any given time.  Both have individual limits which apply separately.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxlockedtargets;
    /**
     * Maximum velocity of ship
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double maxvelocity;
    /**
     * The distance at which to react when relevant objects come within range.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int proximityrange;
    /**
     * Radius of an object in meters
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double radius;
    /**
     * Amount of time taken to fully recharge the capacitor.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double rechargerate;
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
     * Gravimetric strength.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double scangravimetricstrength;
    /**
     * Ladar strength.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double scanladarstrength;
    /**
     * Magnetometric strength.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double scanmagnetometricstrength;
    /**
     * Radar strength.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double scanradarstrength;
    /**
     * Amount of maximum shield HP on the item.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shieldcapacity;
    /**
     * DO NOT MESS WITH. Helper attribute for entities, stands in for the shield charge.
     * The amount of starting shield capacity of the NPC.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shieldcharge;
    /**
     * Amount of time taken to fully recharge the shield.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shieldrechargerate;
    /**
     * DO NOT MESS WITH This number is deducted from the %chance of the seeping to armor, to slow seep of damage through shield.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shielduniformity;
    /**
     * Signature Radius is used for turret tracking and scanning.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(100.0)
    public double signatureradius;
    /**
     * DO NOT MESS WITH
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double structureuniformity;
    /**
     * Authoring has been moved to FSD
     * Tech level of an item
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int techlevel;
    /**
     * damage multiplier vs. thermal.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double thermaldamageresonance;
    /**
     * This number is deducted from the %chance of the seeping to armor, to slow seep of damage through shield.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double uniformity;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {ShieldCapacity.INSTANCE, Uniformity.INSTANCE, ShieldCharge.INSTANCE, Hp.INSTANCE, ArmorHP.INSTANCE, ArmorUniformity.INSTANCE, FighterAbilityAntiFighterMissileResistance.INSTANCE, StructureUniformity.INSTANCE, Charge.INSTANCE, RequiredSkill1Level.INSTANCE, RequiredSkill2Level.INSTANCE, RequiredSkill3Level.INSTANCE, ProximityRange.INSTANCE, Radius.INSTANCE, MaxVelocity.INSTANCE, TechLevel.INSTANCE, Capacity.INSTANCE, SignatureRadius.INSTANCE, RequiredSkill1 .INSTANCE, RequiredSkill2 .INSTANCE, RechargeRate.INSTANCE, RequiredSkill3 .INSTANCE, MaxLockedTargets.INSTANCE, ScanRadarStrength.INSTANCE, ScanLadarStrength.INSTANCE, ScanMagnetometricStrength.INSTANCE, ScanGravimetricStrength.INSTANCE, ShieldRechargeRate.INSTANCE, CapacitorCapacity.INSTANCE, ShieldUniformity.INSTANCE, KineticDamageResonance.INSTANCE, ThermalDamageResonance.INSTANCE, ExplosiveDamageResonance.INSTANCE, EmDamageResonance.INSTANCE, DroneBandwidthUsed.INSTANCE })));
    public static final Drone.MetaCat METACAT = new Drone.MetaCat();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  265 :
            {
                return armorhp;
            }
            case  524 :
            {
                return armoruniformity;
            }
            case  482 :
            {
                return capacitorcapacity;
            }
            case  38 :
            {
                return capacity;
            }
            case  18 :
            {
                return charge;
            }
            case  1272 :
            {
                return dronebandwidthused;
            }
            case  113 :
            {
                return emdamageresonance;
            }
            case  111 :
            {
                return explosivedamageresonance;
            }
            case  2189 :
            {
                return fighterabilityantifightermissileresistance;
            }
            case  9 :
            {
                return hp;
            }
            case  109 :
            {
                return kineticdamageresonance;
            }
            case  192 :
            {
                return maxlockedtargets;
            }
            case  37 :
            {
                return maxvelocity;
            }
            case  154 :
            {
                return proximityrange;
            }
            case  162 :
            {
                return radius;
            }
            case  55 :
            {
                return rechargerate;
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
            case  211 :
            {
                return scangravimetricstrength;
            }
            case  209 :
            {
                return scanladarstrength;
            }
            case  210 :
            {
                return scanmagnetometricstrength;
            }
            case  208 :
            {
                return scanradarstrength;
            }
            case  263 :
            {
                return shieldcapacity;
            }
            case  264 :
            {
                return shieldcharge;
            }
            case  479 :
            {
                return shieldrechargerate;
            }
            case  484 :
            {
                return shielduniformity;
            }
            case  552 :
            {
                return signatureradius;
            }
            case  525 :
            {
                return structureuniformity;
            }
            case  422 :
            {
                return techlevel;
            }
            case  110 :
            {
                return thermaldamageresonance;
            }
            case  136 :
            {
                return uniformity;
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
