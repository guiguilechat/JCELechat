
package fr.guiguilechat.eveonline.model.sde.items.types;

import fr.guiguilechat.eveonline.model.sde.items.Item;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;

public abstract class Structure
    extends Item
{

    /**
     * Amount of maximum shield HP on the item.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShieldCapacity;
    /**
     * This number is deducted from the %chance of the seeping to armor, to slow seep of damage through shield.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double Uniformity;
    /**
     * The maximum hitpoints of an object.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Hp;
    /**
     * The number of hit points on the entities armor.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ArmorHP;
    /**
     * power output of power core
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int PowerOutput;
    /**
     * Multiplies EM damage taken by Armor. 
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0D)
    public double ArmorEmDamageResonance;
    /**
     * Multiplies EXPLOSIVE damage taken by Armor. 
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0D)
    public double ArmorExplosiveDamageResonance;
    /**
     * DO NOT MESS WITH
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double ArmorUniformity;
    /**
     * The number of low power slots on the ship.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int LowSlots;
    /**
     * Multiplies KINETIC damage taken by Armor. 
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0D)
    public double ArmorKineticDamageResonance;
    /**
     * tbd
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MedSlots;
    /**
     * Multiplies THERMAL damage taken by Armor. 
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0D)
    public double ArmorThermalDamageResonance;
    /**
     * tbd
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int HiSlots;
    /**
     * Multiplies EM damage taken by shield
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0D)
    public double ShieldEmDamageResonance;
    /**
     * Multiplies EXPLOSIVE damage taken by Armor. 
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0D)
    public double ShieldExplosiveDamageResonance;
    /**
     * Multiplies KINETIC damage taken by Armor. 
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0D)
    public double ShieldKineticDamageResonance;
    /**
     * Multiplies THERMAL damage taken by Shield. 
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0D)
    public double ShieldThermalDamageResonance;
    /**
     * Signature Radius is used for turret tracking and scanning.
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(100)
    public int SignatureRadius;
    /**
     * CPU output of ship
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int CpuOutput;
    /**
     * The resolution that the vessel can target other objects at.
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int ScanResolution;
    /**
     * Amount of time taken to fully recharge the capacitor.
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double RechargeRate;
    /**
     * Maximum number of locked targets that the character or their ships electronics can handle at any given time.  Both have individual limits which apply separately.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MaxLockedTargets;
    /**
     * Resistance against Remote Sensor Dampeners.
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0D)
    public double SensorDampenerResistance;
    /**
     * Resistance against Remote Weapon Disruptors.
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0D)
    public double WeaponDisruptionResistance;
    /**
     * Resistance against Target Painters
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0D)
    public double TargetPainterResistance;
    /**
     * Resistance against Stasis Webifiers
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0D)
    public double StasisWebifierResistance;
    /**
     * 
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterAbilityAntiCapitalMissileResistance;
    /**
     * Impedance against Remote Repair (shield, armor, hull and energy).
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0D)
    public double RemoteRepairImpedance;
    /**
     * Maximum range at which the scanner can lock a target.
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int MaxTargetRange;
    /**
     * Resistance to ECM. 0 gives Immunity.
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0D)
    public double ECMResistance;
    /**
     * Radar strength.
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0D)
    public double ScanRadarStrength;
    /**
     * Ladar strength.
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0D)
    public double ScanLadarStrength;
    /**
     * Magnetometric strength.
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0D)
    public double ScanMagnetometricStrength;
    /**
     * Gravimetric strength.
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0D)
    public double ScanGravimetricStrength;
    /**
     * Impedance against Remote assistance (sensor boosters, tracking computers and ECCM).
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0D)
    public double RemoteAssistanceImpedance;
    /**
     * Amount of time taken to fully recharge the shield.
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double ShieldRechargeRate;
    /**
     * Capacitor capacity
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double CapacitorCapacity;
    /**
     * DO NOT MESS WITH This number is deducted from the %chance of the seeping to armor, to slow seep of damage through shield.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double ShieldUniformity;
    /**
     * damage multiplier vs. kinetic damagers.
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0D)
    public double KineticDamageResonance;
    /**
     * damage multiplier vs. thermal.
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0D)
    public double ThermalDamageResonance;
    /**
     * damage multiplier vs. explosive damagers.
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0D)
    public double ExplosiveDamageResonance;
    /**
     * Electro magnetic damage multiplier for shield and armor. Represented as "% Resistance" in the UI.
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0D)
    public double EmDamageResonance;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShieldDamageLimit;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ArmorDamageLimit;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int StructureDamageLimit;
    /**
     * Resistance against Energy Neutralizing and Nosferatu
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0D)
    public double EnergyWarfareResistance;

    @Override
    public int getCategoryId() {
        return  65;
    }

    @Override
    public Class<?> getCategory() {
        return Structure.class;
    }

}
