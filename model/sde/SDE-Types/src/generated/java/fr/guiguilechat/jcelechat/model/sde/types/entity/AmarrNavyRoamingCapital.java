package fr.guiguilechat.jcelechat.model.sde.types.entity;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AmarrNavyRoamingCapital
    extends Entity
{
    /**
     * A percentage chance to not change targets 0.0 - 1.0. 1.0 they will never change targets 0.0 they will always change targets
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double AIChanceToNotTargetSwitch;
    /**
     * Should the entity watch for effects when choosing targets
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int AIShouldUseEffectMultiplier;
    /**
     * Should use secondary effect on other targets?
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int AIShouldUseSecondaryTarget;
    /**
     * Should this type use signature radius
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int AIShouldUseSignatureRadius;
    /**
     * This controls how L1 AI target switches
     * When disabled AI_ChanceToNotTargetSwitch, AI_ShouldUseEffectMultiplier, and AI_ShouldUseSignatureRadius are disabled also.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int AIShouldUseTargetSwitching;
    /**
     * The agility of the object.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double Agility;
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
     * Just for the UI to display the ship warp speed.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int BaseWarpSpeed;
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
     * Amount of armor repaired per cycle for entities.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double EntityArmorRepairAmount;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double EntityArmorRepairDelayChanceLarge;
    /**
     * Duration between armor repair actions for entities.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int EntityArmorRepairDuration;
    /**
     *  0: white (default)
     *  1: red (hostile NPC)
     *  2: blue (Neutral NPC)
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int EntityBracketColour;
    /**
     * The maximum amount of time stalled before entity chase speed kicks in.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(5000)
    public int EntityChaseMaxDelay;
    /**
     * Chance that the max delay is waited before chase is engaged.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double EntityChaseMaxDelayChance;
    /**
     * The distance outside of which the entity activates their MWD equivalent.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(2500)
    public int EntityChaseMaxDistance;
    /**
     * The maximum amount of time chase is ever engaged for.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(5000)
    public int EntityChaseMaxDuration;
    /**
     * The chance of engaging chase for the maximum duration.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double EntityChaseMaxDurationChance;
    /**
     * The speed that entities fly at when not chasing a target.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double EntityCruiseSpeed;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double EntityFactionLoss;
    /**
     * The distance at which the entity orbits, follows.. and more.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(500.0)
    public double EntityFlyRange;
    /**
     * The maximum hitpoints of an object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Hp;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int IsCapitalSize;
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
     * Maximum distance to a friendly NPC so that remote repairs may be performed on it.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(5000)
    public int NpcAssistanceRange;
    /**
     * the amount of armor that is repaired per cycle to each target
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int NpcRemoteArmorRepairAmount;
    /**
     * the chance of the NPC remote reapiring it's comrads.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double NpcRemoteArmorRepairChance;
    /**
     * How long NPC take to remote repair ther comerad in MS.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(10000)
    public int NpcRemoteArmorRepairDuration;
    /**
     * The maximum number of targets that can be repaired at once.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int NpcRemoteArmorRepairMaxTargets;
    /**
     * How damaged does a teammate's armor need to be before it will be repaired.
     *  0.1 means: Must be below 90% armor to get repairs
     *  0.9 means: Must be below 10% armor to get repairs
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.25)
    public double NpcRemoteArmorRepairThreshold;
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
     * DO NOT MESS WITH
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double StructureUniformity;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(3.0)
    public double WarpSpeedMultiplier;
    public static final AmarrNavyRoamingCapital.MetaGroup METAGROUP = new AmarrNavyRoamingCapital.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  1651 :
            {
                return AIChanceToNotTargetSwitch;
            }
            case  1652 :
            {
                return AIShouldUseEffectMultiplier;
            }
            case  1649 :
            {
                return AIShouldUseSecondaryTarget;
            }
            case  1650 :
            {
                return AIShouldUseSignatureRadius;
            }
            case  1648 :
            {
                return AIShouldUseTargetSwitching;
            }
            case  70 :
            {
                return Agility;
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
            case  1281 :
            {
                return BaseWarpSpeed;
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
            case  631 :
            {
                return EntityArmorRepairAmount;
            }
            case  1011 :
            {
                return EntityArmorRepairDelayChanceLarge;
            }
            case  630 :
            {
                return EntityArmorRepairDuration;
            }
            case  798 :
            {
                return EntityBracketColour;
            }
            case  580 :
            {
                return EntityChaseMaxDelay;
            }
            case  581 :
            {
                return EntityChaseMaxDelayChance;
            }
            case  665 :
            {
                return EntityChaseMaxDistance;
            }
            case  582 :
            {
                return EntityChaseMaxDuration;
            }
            case  583 :
            {
                return EntityChaseMaxDurationChance;
            }
            case  508 :
            {
                return EntityCruiseSpeed;
            }
            case  562 :
            {
                return EntityFactionLoss;
            }
            case  416 :
            {
                return EntityFlyRange;
            }
            case  9 :
            {
                return Hp;
            }
            case  1785 :
            {
                return IsCapitalSize;
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
            case  1464 :
            {
                return NpcAssistanceRange;
            }
            case  1455 :
            {
                return NpcRemoteArmorRepairAmount;
            }
            case  1453 :
            {
                return NpcRemoteArmorRepairChance;
            }
            case  1454 :
            {
                return NpcRemoteArmorRepairDuration;
            }
            case  1501 :
            {
                return NpcRemoteArmorRepairMaxTargets;
            }
            case  1456 :
            {
                return NpcRemoteArmorRepairThreshold;
            }
            case  162 :
            {
                return Radius;
            }
            case  55 :
            {
                return RechargeRate;
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
            case  525 :
            {
                return StructureUniformity;
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
    public IMetaGroup<AmarrNavyRoamingCapital> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<AmarrNavyRoamingCapital>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/AmarrNavyRoamingCapital.yaml";
        private Map<String, AmarrNavyRoamingCapital> cache = (null);

        @Override
        public IMetaCategory<? super AmarrNavyRoamingCapital> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1412;
        }

        @Override
        public String getName() {
            return "AmarrNavyRoamingCapital";
        }

        @Override
        public synchronized Map<String, AmarrNavyRoamingCapital> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(AmarrNavyRoamingCapital.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, AmarrNavyRoamingCapital> types;
        }
    }
}
