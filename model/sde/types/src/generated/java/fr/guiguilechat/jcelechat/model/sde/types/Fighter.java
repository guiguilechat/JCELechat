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
import fr.guiguilechat.jcelechat.model.sde.attributes.Agility;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityAntiFighterMissileResistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityMicroWarpDriveDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityMicroWarpDriveSignatureRadiusBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityMicroWarpDriveSpeedBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterRefuelingTime;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterSquadronMaxSize;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterSquadronOrbitRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterSquadronRole;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hp;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxLockedTargets;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxTargetRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxVelocity;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaGroupID;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaLevelOld;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.RemoteAssistanceImpedance;
import fr.guiguilechat.jcelechat.model.sde.attributes.RemoteRepairImpedance;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill2;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill2Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanGravimetricStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanLadarStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanMagnetometricStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanRadarStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanResolution;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldCharge;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldEmDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldExplosiveDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldKineticDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldRechargeRate;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldThermalDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldUniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.SignatureRadius;
import fr.guiguilechat.jcelechat.model.sde.attributes.StructureItemVisualFlag;
import fr.guiguilechat.jcelechat.model.sde.attributes.StructureUniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.TechLevel;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarpSpeedMultiplier;
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
    @DefaultRealValue(0.0)
    public double agility;
    /**
     * The cargo space allowed
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double capacity;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fighterabilityantifightermissileresistance;
    /**
     * Duration
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fighterabilitymicrowarpdriveduration;
    /**
     * Signature Radius Bonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fighterabilitymicrowarpdrivesignatureradiusbonus;
    /**
     * Maximum Velocity Bonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fighterabilitymicrowarpdrivespeedbonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fighterrefuelingtime;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fightersquadronmaxsize;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fightersquadronorbitrange;
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
    public int fightersquadronrole;
    /**
     * The maximum hitpoints of an object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double hp;
    /**
     * Maximum number of locked targets that the character or their ships electronics can handle at any given time.  Both have individual limits which apply separately.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxlockedtargets;
    /**
     * Maximum range at which the scanner can lock a target.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double maxtargetrange;
    /**
     * Maximum velocity of ship
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double maxvelocity;
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
     * Authoring has been moved to FSD
     * The ranking of the module within its tech level
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int metalevelold;
    /**
     * Radius of an object in meters
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double radius;
    /**
     * Impedance against Remote assistance (sensor boosters, tracking computers and ECCM).
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double remoteassistanceimpedance;
    /**
     * Impedance against Remote Repair (shield, armor, hull and energy).
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double remoterepairimpedance;
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
     * The resolution that the vessel can target other objects at.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double scanresolution;
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
     * Multiplies EM damage taken by shield
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double shieldemdamageresonance;
    /**
     * Multiplies EXPLOSIVE damage taken by Armor. 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double shieldexplosivedamageresonance;
    /**
     * Multiplies KINETIC damage taken by Armor. 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double shieldkineticdamageresonance;
    /**
     * Amount of time taken to fully recharge the shield.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shieldrechargerate;
    /**
     * Multiplies THERMAL damage taken by Shield. 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double shieldthermaldamageresonance;
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
     * Dogma attribute that specifies if the item should have the structure icon or not.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int structureitemvisualflag;
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
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(3.0)
    public double warpspeedmultiplier;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {ShieldCapacity.INSTANCE, ShieldCharge.INSTANCE, Hp.INSTANCE, StructureUniformity.INSTANCE, FighterAbilityAntiFighterMissileResistance.INSTANCE, ShieldEmDamageResonance.INSTANCE, ShieldExplosiveDamageResonance.INSTANCE, ShieldKineticDamageResonance.INSTANCE, ShieldThermalDamageResonance.INSTANCE, RequiredSkill1Level.INSTANCE, RequiredSkill2Level.INSTANCE, MetaGroupID.INSTANCE, StructureItemVisualFlag.INSTANCE, Radius.INSTANCE, MaxVelocity.INSTANCE, TechLevel.INSTANCE, Capacity.INSTANCE, FighterSquadronMaxSize.INSTANCE, SignatureRadius.INSTANCE, FighterSquadronOrbitRange.INSTANCE, ScanResolution.INSTANCE, RequiredSkill1 .INSTANCE, RequiredSkill2 .INSTANCE, MaxLockedTargets.INSTANCE, RemoteRepairImpedance.INSTANCE, Agility.INSTANCE, MaxTargetRange.INSTANCE, ScanRadarStrength.INSTANCE, ScanLadarStrength.INSTANCE, ScanMagnetometricStrength.INSTANCE, ScanGravimetricStrength.INSTANCE, RemoteAssistanceImpedance.INSTANCE, WarpSpeedMultiplier.INSTANCE, FighterSquadronRole.INSTANCE, ShieldRechargeRate.INSTANCE, ShieldUniformity.INSTANCE, FighterAbilityMicroWarpDriveSpeedBonus.INSTANCE, FighterAbilityMicroWarpDriveSignatureRadiusBonus.INSTANCE, FighterAbilityMicroWarpDriveDuration.INSTANCE, MetaLevelOld.INSTANCE, FighterRefuelingTime.INSTANCE })));
    public static final Fighter.MetaCat METACAT = new Fighter.MetaCat();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  70 :
            {
                return agility;
            }
            case  38 :
            {
                return capacity;
            }
            case  2189 :
            {
                return fighterabilityantifightermissileresistance;
            }
            case  2157 :
            {
                return fighterabilitymicrowarpdriveduration;
            }
            case  2153 :
            {
                return fighterabilitymicrowarpdrivesignatureradiusbonus;
            }
            case  2152 :
            {
                return fighterabilitymicrowarpdrivespeedbonus;
            }
            case  2426 :
            {
                return fighterrefuelingtime;
            }
            case  2215 :
            {
                return fightersquadronmaxsize;
            }
            case  2223 :
            {
                return fightersquadronorbitrange;
            }
            case  2270 :
            {
                return fightersquadronrole;
            }
            case  9 :
            {
                return hp;
            }
            case  192 :
            {
                return maxlockedtargets;
            }
            case  76 :
            {
                return maxtargetrange;
            }
            case  37 :
            {
                return maxvelocity;
            }
            case  1692 :
            {
                return metagroupid;
            }
            case  633 :
            {
                return metalevelold;
            }
            case  162 :
            {
                return radius;
            }
            case  2135 :
            {
                return remoteassistanceimpedance;
            }
            case  2116 :
            {
                return remoterepairimpedance;
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
            case  564 :
            {
                return scanresolution;
            }
            case  263 :
            {
                return shieldcapacity;
            }
            case  264 :
            {
                return shieldcharge;
            }
            case  271 :
            {
                return shieldemdamageresonance;
            }
            case  272 :
            {
                return shieldexplosivedamageresonance;
            }
            case  273 :
            {
                return shieldkineticdamageresonance;
            }
            case  479 :
            {
                return shieldrechargerate;
            }
            case  274 :
            {
                return shieldthermaldamageresonance;
            }
            case  484 :
            {
                return shielduniformity;
            }
            case  552 :
            {
                return signatureradius;
            }
            case  2334 :
            {
                return structureitemvisualflag;
            }
            case  525 :
            {
                return structureuniformity;
            }
            case  422 :
            {
                return techlevel;
            }
            case  600 :
            {
                return warpspeedmultiplier;
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
