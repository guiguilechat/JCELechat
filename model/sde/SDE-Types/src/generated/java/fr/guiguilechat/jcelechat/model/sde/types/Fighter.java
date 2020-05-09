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
import fr.guiguilechat.jcelechat.model.sde.types.fighter.HeavyFighter;
import fr.guiguilechat.jcelechat.model.sde.types.fighter.LightFighter;
import fr.guiguilechat.jcelechat.model.sde.types.fighter.SupportFighter;

public abstract class Fighter
    extends EveType
{
    /**
     * The agility of the object.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double Agility;
    /**
     * The cargo space allowed
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Capacity;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterAbilityAntiFighterMissileResistance;
    /**
     * Duration
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterAbilityMicroWarpDriveDuration;
    /**
     * Signature Radius Bonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterAbilityMicroWarpDriveSignatureRadiusBonus;
    /**
     * Maximum Velocity Bonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterAbilityMicroWarpDriveSpeedBonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterRefuelingTime;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterSquadronMaxSize;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterSquadronOrbitRange;
    /**
     *  0=None
     *  1=Anti-Fighter
     *  2=General
     *  3=Ewar
     *  4=TorpedoBomber
     *  5=AOEBomber
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterSquadronRole;
    /**
     * The maximum hitpoints of an object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Hp;
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
     * Maximum velocity of ship
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double MaxVelocity;
    /**
     * Authoring has been moved to FSD
     * The ranking of the module within its tech level
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MetaLevel;
    /**
     * Radius of an object in meters
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Radius;
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
     * Dogma attribute that specifies if the item should have the structure icon or not.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int StructureItemVisualFlag;
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
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(3.0)
    public double WarpSpeedMultiplier;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {fr.guiguilechat.jcelechat.model.sde.attributes.Mass.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.ShieldCapacity.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.ShieldCharge.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.Hp.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.StructureUniformity.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityAntiFighterMissileResistance.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.ShieldEmDamageResonance.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.ShieldExplosiveDamageResonance.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.ShieldKineticDamageResonance.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.ShieldThermalDamageResonance.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill2Level.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.StructureItemVisualFlag.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.Radius.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.MaxVelocity.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.TechLevel.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.Capacity.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.FighterSquadronMaxSize.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.SignatureRadius.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.FighterSquadronOrbitRange.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.ScanResolution.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1 .INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill2 .INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.MaxLockedTargets.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.RemoteRepairImpedance.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.Agility.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.MaxTargetRange.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.ScanRadarStrength.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.ScanLadarStrength.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.ScanMagnetometricStrength.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.ScanGravimetricStrength.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.RemoteAssistanceImpedance.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.WarpSpeedMultiplier.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.FighterSquadronRole.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.ShieldRechargeRate.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.ShieldUniformity.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityMicroWarpDriveSpeedBonus.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityMicroWarpDriveSignatureRadiusBonus.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityMicroWarpDriveDuration.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.MetaLevel.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.FighterRefuelingTime.INSTANCE })));
    public static final Fighter.MetaCat METACAT = new Fighter.MetaCat();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  70 :
            {
                return Agility;
            }
            case  38 :
            {
                return Capacity;
            }
            case  2189 :
            {
                return FighterAbilityAntiFighterMissileResistance;
            }
            case  2157 :
            {
                return FighterAbilityMicroWarpDriveDuration;
            }
            case  2153 :
            {
                return FighterAbilityMicroWarpDriveSignatureRadiusBonus;
            }
            case  2152 :
            {
                return FighterAbilityMicroWarpDriveSpeedBonus;
            }
            case  2426 :
            {
                return FighterRefuelingTime;
            }
            case  2215 :
            {
                return FighterSquadronMaxSize;
            }
            case  2223 :
            {
                return FighterSquadronOrbitRange;
            }
            case  2270 :
            {
                return FighterSquadronRole;
            }
            case  9 :
            {
                return Hp;
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
            case  37 :
            {
                return MaxVelocity;
            }
            case  633 :
            {
                return MetaLevel;
            }
            case  162 :
            {
                return Radius;
            }
            case  2135 :
            {
                return RemoteAssistanceImpedance;
            }
            case  2116 :
            {
                return RemoteRepairImpedance;
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
            case  263 :
            {
                return ShieldCapacity;
            }
            case  264 :
            {
                return ShieldCharge;
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
            case  2334 :
            {
                return StructureItemVisualFlag;
            }
            case  525 :
            {
                return StructureUniformity;
            }
            case  422 :
            {
                return TechLevel;
            }
            case  600 :
            {
                return WarpSpeedMultiplier;
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
    public IMetaCategory<Fighter> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<Fighter>
    {

        @Override
        public int getCategoryId() {
            return  87;
        }

        @Override
        public String getName() {
            return "Fighter";
        }

        @Override
        public Collection<IMetaGroup<? extends Fighter>> groups() {
            return Arrays.asList(SupportFighter.METAGROUP, LightFighter.METAGROUP, HeavyFighter.METAGROUP);
        }
    }
}
