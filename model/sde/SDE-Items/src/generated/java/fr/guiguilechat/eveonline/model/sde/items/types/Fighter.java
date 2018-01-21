
package fr.guiguilechat.eveonline.model.sde.items.types;

import fr.guiguilechat.eveonline.model.sde.items.Item;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;

public abstract class Fighter
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
     * The maximum hitpoints of an object.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Hp;
    /**
     * DO NOT MESS WITH
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0D)
    public double StructureUniformity;
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
     * Required skill level for skill 1
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1Level;
    /**
     * Required skill level for skill 2
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill2Level;
    /**
     * Maximum velocity of ship
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0D)
    public double MaxVelocity;
    /**
     * Signature Radius is used for turret tracking and scanning.
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(100)
    public int SignatureRadius;
    /**
     * The resolution that the vessel can target other objects at.
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int ScanResolution;
    /**
     * Impedance against Remote Repair (shield, armor, hull and energy).
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0D)
    public double RemoteRepairImpedance;
    /**
     * The agility of the object.
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(0.0D)
    public double Agility;
    /**
     * Maximum range at which the scanner can lock a target.
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int MaxTargetRange;
    /**
     * Impedance against Remote assistance (sensor boosters, tracking computers and ECCM).
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0D)
    public double RemoteAssistanceImpedance;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(3.0D)
    public double WarpSpeedMultiplier;
    /**
     * Maximum Velocity Bonus
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterAbilityMicroWarpDriveSpeedBonus;
    /**
     * Signature Radius Bonus
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterAbilityMicroWarpDriveSignatureRadiusBonus;
    /**
     * Duration
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterAbilityMicroWarpDriveDuration;
    /**
     * The ranking of the module within its tech level
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MetaLevel;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterRefuelingTime;
    /**
     * 
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterAbilityAntiFighterMissileResistance;
    /**
     * Tech level of an item
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int TechLevel;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterSquadronMaxSize;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterSquadronOrbitRange;
    /**
     * The type ID of the skill that is required.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1;
    /**
     * The type ID of the skill that is required.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill2;
    /**
     * Maximum number of locked targets that the character or their ships electronics can handle at any given time.  Both have individual limits which apply separately.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MaxLockedTargets;
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
     *  0=None
     *  1=Anti-Fighter
     *  2=General
     *  3=Ewar
     *  4=TorpedoBomber
     *  5=AOEBomber
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterSquadronRole;
    /**
     * Amount of time taken to fully recharge the shield.
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double ShieldRechargeRate;
    /**
     * DO NOT MESS WITH This number is deducted from the %chance of the seeping to armor, to slow seep of damage through shield.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double ShieldUniformity;

    @Override
    public int getCategoryId() {
        return  87;
    }

    @Override
    public Class<?> getCategory() {
        return Fighter.class;
    }

}
