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
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.AccessDifficulty;
import fr.guiguilechat.jcelechat.model.sde.attributes.AllowRefills;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorHP;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorUniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.CapacitorCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.DisallowAssistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityAttackRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityChaseMaxDistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityEquipmentGroupMax;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityEquipmentMax;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityEquipmentMin;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityFlyRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityLootCountMax;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntityLootCountMin;
import fr.guiguilechat.jcelechat.model.sde.attributes.EntitySecurityStatusKillBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.Falloff;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hp;
import fr.guiguilechat.jcelechat.model.sde.attributes.IsArcheology;
import fr.guiguilechat.jcelechat.model.sde.attributes.IsHacking;
import fr.guiguilechat.jcelechat.model.sde.attributes.LootRespawnTime;
import fr.guiguilechat.jcelechat.model.sde.attributes.Mass;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldCharge;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldRechargeRate;
import fr.guiguilechat.jcelechat.model.sde.attributes.SignatureRadius;
import fr.guiguilechat.jcelechat.model.sde.attributes.SpawnWithoutGuardsToo;
import fr.guiguilechat.jcelechat.model.sde.attributes.SpecialTutorialLootRespawnTime;
import fr.guiguilechat.jcelechat.model.sde.attributes.StructureUniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.TierDifficulty;
import fr.guiguilechat.jcelechat.model.sde.attributes.TrackingSpeed;
import fr.guiguilechat.jcelechat.model.sde.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class SpawnContainer
    extends Entity
{
    /**
     * The difficulty in opening this object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double accessdifficulty;
    /**
     * When set to 1 this attribute allows Spawn Containers to refill and relock. 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int allowrefills;
    /**
     * The number of hit points on the entities armor.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double armorhp;
    /**
     * DO NOT MESS WITH
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double armoruniformity;
    /**
     * Capacitor capacity
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double capacitorcapacity;
    /**
     * The cargo space allowed
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double capacity;
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
     * The distance outside of which the entity activates their MWD equivalent.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(2500)
    public int entitychasemaxdistance;
    /**
     * The maximum drops of same group (example: entity can only drop 1 of group: energy laser)
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int entityequipmentgroupmax;
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
     * The distance at which the entity orbits, follows.. and more.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(500.0)
    public double entityflyrange;
    /**
     * The maximum number of pieces of loot dropped by this entity.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int entitylootcountmax;
    /**
     * Deprecated. The minimum number of pieces of loot dropped by this entity.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int entitylootcountmin;
    /**
     * How much security status is modified by for killing this entity.  Depending on the entity, this may be a positive or negative amount.
     * Value is a % movement of the character's current security towards the upper/lower limit.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double entitysecuritystatuskillbonus;
    /**
     * distance from maximum range at which accuracy has fallen by half
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double falloff;
    /**
     * Defines whether an entity can be hacked or not.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int hackable;
    /**
     * The maximum hitpoints of an object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double hp;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int isarcheology;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ishacking;
    /**
     * The number of milliseconds before the container replenishes the loot inside itself.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(600000.0)
    public double lootrespawntime;
    /**
     * Integer that describes the types mass
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double mass;
    /**
     * Distance below which range does not affect the to-hit equation.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double maxrange;
    /**
     * Radius of an object in meters
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double radius;
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
     * Amount of maximum shield HP on the item.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double shieldcapacity;
    /**
     * DO NOT MESS WITH. Helper attribute for entities, stands in for the shield charge.
     * The amount of starting shield capacity of the NPC.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double shieldcharge;
    /**
     * Amount of time taken to fully recharge the shield.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double shieldrechargerate;
    /**
     * Signature Radius is used for turret tracking and scanning.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(100.0)
    public double signatureradius;
    /**
     * Whether a spawn container should refill itself when there are no guards assigned to it.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int spawnwithoutguardstoo;
    /**
     * The number of milliseconds before the container replenishes the loot inside itself. This special tutorial attribute will allow re-spawning of items in distribution dungeons bypassing restrictions present. 10 second minimum (10000 ms).
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(30000)
    public int specialtutoriallootrespawntime;
    /**
     * DO NOT MESS WITH
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double structureuniformity;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int tierdifficulty;
    /**
     * Weapon accuracy
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double trackingspeed;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {Mass.INSTANCE, AccessDifficulty.INSTANCE, SpawnWithoutGuardsToo.INSTANCE, Hackable.INSTANCE, ShieldCapacity.INSTANCE, EntityEquipmentMin.INSTANCE, ShieldCharge.INSTANCE, EntityEquipmentMax.INSTANCE, Hp.INSTANCE, ArmorHP.INSTANCE, ArmorUniformity.INSTANCE, StructureUniformity.INSTANCE, EntityEquipmentGroupMax.INSTANCE, RequiredSkill1Level.INSTANCE, LootRespawnTime.INSTANCE, DisallowAssistance.INSTANCE, EntityChaseMaxDistance.INSTANCE, Falloff.INSTANCE, ShieldRechargeRate.INSTANCE, TrackingSpeed.INSTANCE, EntityFlyRange.INSTANCE, CapacitorCapacity.INSTANCE, Radius.INSTANCE, AllowRefills.INSTANCE, Capacity.INSTANCE, SignatureRadius.INSTANCE, SpecialTutorialLootRespawnTime.INSTANCE, IsHacking.INSTANCE, IsArcheology.INSTANCE, MaxRange.INSTANCE, RequiredSkill1 .INSTANCE, EntityAttackRange.INSTANCE, EntityLootCountMin.INSTANCE, EntityLootCountMax.INSTANCE, EntitySecurityStatusKillBonus.INSTANCE, TierDifficulty.INSTANCE })));
    public static final SpawnContainer.MetaGroup METAGROUP = new SpawnContainer.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  901 :
            {
                return accessdifficulty;
            }
            case  2019 :
            {
                return allowrefills;
            }
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
            case  854 :
            {
                return disallowassistance;
            }
            case  247 :
            {
                return entityattackrange;
            }
            case  665 :
            {
                return entitychasemaxdistance;
            }
            case  465 :
            {
                return entityequipmentgroupmax;
            }
            case  457 :
            {
                return entityequipmentmax;
            }
            case  456 :
            {
                return entityequipmentmin;
            }
            case  416 :
            {
                return entityflyrange;
            }
            case  251 :
            {
                return entitylootcountmax;
            }
            case  250 :
            {
                return entitylootcountmin;
            }
            case  252 :
            {
                return entitysecuritystatuskillbonus;
            }
            case  158 :
            {
                return falloff;
            }
            case  1927 :
            {
                return hackable;
            }
            case  9 :
            {
                return hp;
            }
            case  1331 :
            {
                return isarcheology;
            }
            case  1330 :
            {
                return ishacking;
            }
            case  470 :
            {
                return lootrespawntime;
            }
            case  4 :
            {
                return mass;
            }
            case  54 :
            {
                return maxrange;
            }
            case  162 :
            {
                return radius;
            }
            case  182 :
            {
                return requiredskill1;
            }
            case  277 :
            {
                return requiredskill1level;
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
            case  552 :
            {
                return signatureradius;
            }
            case  903 :
            {
                return spawnwithoutguardstoo;
            }
            case  1582 :
            {
                return specialtutoriallootrespawntime;
            }
            case  525 :
            {
                return structureuniformity;
            }
            case  1919 :
            {
                return tierdifficulty;
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
    public IMetaGroup<SpawnContainer> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<SpawnContainer>
    {
        public static final String RESOURCE_PATH = "SDE/types/entity/SpawnContainer.yaml";
        private Map<String, SpawnContainer> cache = (null);

        @Override
        public IMetaCategory<? super SpawnContainer> category() {
            return Entity.METACAT;
        }

        @Override
        public int getGroupId() {
            return  306;
        }

        @Override
        public String getName() {
            return "SpawnContainer";
        }

        @Override
        public synchronized Map<String, SpawnContainer> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(SpawnContainer.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, SpawnContainer> types;
        }
    }
}
