package fr.guiguilechat.jcelechat.model.sde.types.entity;

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
import fr.guiguilechat.jcelechat.model.sde.attributes.AIChanceToNotTargetSwitch;
import fr.guiguilechat.jcelechat.model.sde.attributes.AIIgnoreDronesBelowSignatureRadius;
import fr.guiguilechat.jcelechat.model.sde.attributes.AIShouldUseEffectMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.AIShouldUseSignatureRadius;
import fr.guiguilechat.jcelechat.model.sde.attributes.AIShouldUseTargetSwitching;
import fr.guiguilechat.jcelechat.model.sde.attributes.AITankingModifierDrone;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorEmDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorExplosiveDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorHP;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorKineticDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorThermalDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorUniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.CapacitorCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.Charge;
import fr.guiguilechat.jcelechat.model.sde.attributes.DisallowAssistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityAttackRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityBracketColour;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityChaseMaxDistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityCruiseSpeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityEquipmentMax;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityEquipmentMin;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityFactionLoss;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityFlyRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityGroupRespawnChance;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityKillBounty;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityLootCountMin;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityOverviewShipGroupId;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntitySecurityMaxGain;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntitySecurityStatusKillBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.Falloff;
import fr.guiguilechat.jcelechat.model.sde.attributes.GfxBoosterID;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hp;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxVelocity;
import fr.guiguilechat.jcelechat.model.sde.attributes.PropulsionGraphicID;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.RechargeRate;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldCharge;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldEmDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldExplosiveDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldKineticDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldRechargeRate;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldThermalDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldUniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.SignatureRadius;
import fr.guiguilechat.jcelechat.model.sde.attributes.StructureUniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.TrackingSpeed;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class AsteroidSanshaSNationHauler
    extends Entity
{
    /**
     * A percentage chance to not change targets 0.0 - 1.0. 1.0 they will never change targets 0.0 they will always change targets
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double aichancetonottargetswitch;
    /**
     * NPC'S with this attribute wont shoot drones with signature radius less than this value.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int aiignoredronesbelowsignatureradius;
    /**
     * Should the entity watch for effects when choosing targets
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int aishoulduseeffectmultiplier;
    /**
     * Should this type use signature radius
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int aishouldusesignatureradius;
    /**
     * This controls how L1 AI target switches
     * When disabled AI_ChanceToNotTargetSwitch, AI_ShouldUseEffectMultiplier, and AI_ShouldUseSignatureRadius are disabled also.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int aishouldusetargetswitching;
    /**
     * Tanking modifier applied to drones if their owner is tanking. 1.0 is no modifier
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.699999988079071)
    public double aitankingmodifierdrone;
    /**
     * Multiplies EM damage taken by Armor. 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double armoremdamageresonance;
    /**
     * Multiplies EXPLOSIVE damage taken by Armor. 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double armorexplosivedamageresonance;
    /**
     * The number of hit points on the entities armor.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double armorhp;
    /**
     * Multiplies KINETIC damage taken by Armor. 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double armorkineticdamageresonance;
    /**
     * Multiplies THERMAL damage taken by Armor. 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double armorthermaldamageresonance;
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
     * If this module is in use and this attribute is 1, then assistance modules cannot be used on the ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int disallowassistance;
    /**
     * The distance from a target an entity starts using its weapons.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(15000)
    public int entityattackrange;
    /**
     *  0: white (default)
     *  1: red (hostile NPC)
     *  2: blue (Neutral NPC)
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int entitybracketcolour;
    /**
     * The distance outside of which the entity activates their MWD equivalent.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(2500)
    public int entitychasemaxdistance;
    /**
     * The speed that entities fly at when not chasing a target.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double entitycruisespeed;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int entityequipmentmax;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int entityequipmentmin;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double entityfactionloss;
    /**
     * The distance at which the entity orbits, follows.. and more.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(500.0)
    public double entityflyrange;
    /**
     * The chance an entity will respawn into his group if destroyed.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int entitygrouprespawnchance;
    /**
     * Reward for destroying this entity.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int entitykillbounty;
    /**
     * Deprecated. The minimum number of pieces of loot dropped by this entity.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int entitylootcountmin;
    /**
     * This attribute is used on entities to link them to a player ship group. This is then used to determine which overview icon they should get, among other things
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int entityoverviewshipgroupid;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double entitysecuritymaxgain;
    /**
     * How much security status is modified by for killing this entity.  Depending on the entity, this may be a positive or negative amount.
     * Value is a % movement of the character's current security towards the upper/lower limit.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double entitysecuritystatuskillbonus;
    /**
     * distance from maximum range at which accuracy has fallen by half
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double falloff;
    /**
     * Graphic ID of the boosters for drone type ships.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int gfxboosterid;
    /**
     * The maximum hitpoints of an object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double hp;
    /**
     * Distance below which range does not affect the to-hit equation.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double maxrange;
    /**
     * Maximum velocity of ship
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double maxvelocity;
    /**
     * The graphicID of the propulsion system.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int propulsiongraphicid;
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
     * DO NOT MESS WITH
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double structureuniformity;
    /**
     * Weapon accuracy
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double trackingspeed;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {EntityGroupRespawnChance.INSTANCE, ShieldCapacity.INSTANCE, ShieldCharge.INSTANCE, Hp.INSTANCE, ArmorHP.INSTANCE, ArmorEmDamageResonance.INSTANCE, ArmorExplosiveDamageResonance.INSTANCE, ArmorUniformity.INSTANCE, ArmorKineticDamageResonance.INSTANCE, StructureUniformity.INSTANCE, ArmorThermalDamageResonance.INSTANCE, ShieldEmDamageResonance.INSTANCE, ShieldExplosiveDamageResonance.INSTANCE, ShieldKineticDamageResonance.INSTANCE, ShieldThermalDamageResonance.INSTANCE, Charge.INSTANCE, EntityChaseMaxDistance.INSTANCE, Falloff.INSTANCE, EntityBracketColour.INSTANCE, TrackingSpeed.INSTANCE, EntityFlyRange.INSTANCE, Radius.INSTANCE, MaxVelocity.INSTANCE, Capacity.INSTANCE, SignatureRadius.INSTANCE, EntityFactionLoss.INSTANCE, EntitySecurityMaxGain.INSTANCE, MaxRange.INSTANCE, RechargeRate.INSTANCE, AIIgnoreDronesBelowSignatureRadius.INSTANCE, EntityEquipmentMin.INSTANCE, EntityEquipmentMax.INSTANCE, DisallowAssistance.INSTANCE, PropulsionGraphicID.INSTANCE, ShieldRechargeRate.INSTANCE, EntityKillBounty.INSTANCE, CapacitorCapacity.INSTANCE, ShieldUniformity.INSTANCE, EntityOverviewShipGroupId.INSTANCE, AIShouldUseTargetSwitching.INSTANCE, AIShouldUseSignatureRadius.INSTANCE, AIChanceToNotTargetSwitch.INSTANCE, AIShouldUseEffectMultiplier.INSTANCE, GfxBoosterID.INSTANCE, EntityAttackRange.INSTANCE, AITankingModifierDrone.INSTANCE, EntityLootCountMin.INSTANCE, EntityCruiseSpeed.INSTANCE, EntitySecurityStatusKillBonus.INSTANCE })));
    public static final AsteroidSanshaSNationHauler.MetaGroup METAGROUP = new AsteroidSanshaSNationHauler.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  1651 :
            {
                return aichancetonottargetswitch;
            }
            case  1855 :
            {
                return aiignoredronesbelowsignatureradius;
            }
            case  1652 :
            {
                return aishoulduseeffectmultiplier;
            }
            case  1650 :
            {
                return aishouldusesignatureradius;
            }
            case  1648 :
            {
                return aishouldusetargetswitching;
            }
            case  1656 :
            {
                return aitankingmodifierdrone;
            }
            case  267 :
            {
                return armoremdamageresonance;
            }
            case  268 :
            {
                return armorexplosivedamageresonance;
            }
            case  265 :
            {
                return armorhp;
            }
            case  269 :
            {
                return armorkineticdamageresonance;
            }
            case  270 :
            {
                return armorthermaldamageresonance;
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
            case  854 :
            {
                return disallowassistance;
            }
            case  247 :
            {
                return entityattackrange;
            }
            case  798 :
            {
                return entitybracketcolour;
            }
            case  665 :
            {
                return entitychasemaxdistance;
            }
            case  508 :
            {
                return entitycruisespeed;
            }
            case  457 :
            {
                return entityequipmentmax;
            }
            case  456 :
            {
                return entityequipmentmin;
            }
            case  562 :
            {
                return entityfactionloss;
            }
            case  416 :
            {
                return entityflyrange;
            }
            case  640 :
            {
                return entitygrouprespawnchance;
            }
            case  481 :
            {
                return entitykillbounty;
            }
            case  250 :
            {
                return entitylootcountmin;
            }
            case  1766 :
            {
                return entityoverviewshipgroupid;
            }
            case  563 :
            {
                return entitysecuritymaxgain;
            }
            case  252 :
            {
                return entitysecuritystatuskillbonus;
            }
            case  158 :
            {
                return falloff;
            }
            case  246 :
            {
                return gfxboosterid;
            }
            case  9 :
            {
                return hp;
            }
            case  54 :
            {
                return maxrange;
            }
            case  37 :
            {
                return maxvelocity;
            }
            case  217 :
            {
                return propulsiongraphicid;
            }
            case  162 :
            {
                return radius;
            }
            case  55 :
            {
                return rechargerate;
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
            case  525 :
            {
                return structureuniformity;
            }
            case  160 :
            {
                return trackingspeed;
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
    public IMetaGroup<AsteroidSanshaSNationHauler> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AsteroidSanshaSNationHauler>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/AsteroidSanshaSNationHauler.yaml";
        private Map<Integer, AsteroidSanshaSNationHauler> cache = (null);

        @Override
        public IMetaCategory<? super AsteroidSanshaSNationHauler> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  568;
        }

        @Override
        public String getName() {
            return "AsteroidSanshaSNationHauler";
        }

        @Override
        public synchronized Map<Integer, AsteroidSanshaSNationHauler> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(AsteroidSanshaSNationHauler.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    LoaderOptions options = new LoaderOptions();
                    options.setCodePointLimit(Integer.MAX_VALUE);
                    cache = new Yaml(options).loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<Integer, AsteroidSanshaSNationHauler> types;
        }
    }
}
