
package fr.guiguilechat.eveonline.model.sde.items.types.celestial;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.Celestial;
import org.yaml.snakeyaml.Yaml;

public class LargeCollidableObject
    extends Celestial
{

    /**
     * The difficulty in opening this object.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double AccessDifficulty;
    /**
     * Amount of maximum shield HP on the item.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShieldCapacity;
    /**
     * Whether a spawn container should refill itself when there are no guards assigned to it.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int SpawnWithoutGuardsToo;
    /**
     * Defines whether an entity can be hacked or not.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Hackable;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int EntityEquipmentMin;
    /**
     * The maximum hitpoints of an object.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Hp;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int EntityEquipmentMax;
    /**
     * The number of hit points on the entities armor.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ArmorHP;
    /**
     * DO NOT MESS WITH
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double ArmorUniformity;
    /**
     * DO NOT MESS WITH
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0D)
    public double StructureUniformity;
    /**
     * The skill required to reprocess this ore type.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ReprocessingSkillType;
    /**
     * If this module is in use and this attribute is 1, then assistance modules cannot be used on the ship.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DisallowAssistance;
    /**
     * The distance outside of which the entity activates their MWD equivalent.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(2500)
    public int EntityChaseMaxDistance;
    /**
     * distance from maximum range at which accuracy has fallen by half
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(1)
    public int Falloff;
    /**
     *  0: white (default)
     *  1: red (hostile NPC)
     *  2: blue (Neutral NPC)
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int EntityBracketColour;
    /**
     * Amount of time taken to fully recharge the shield.
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double ShieldRechargeRate;
    /**
     * The distance at which the entity orbits, follows.. and more.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(500.0D)
    public double EntityFlyRange;
    /**
     * Weapon accuracy
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0D)
    public double TrackingSpeed;
    /**
     * Reward for destroying this entity.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int EntityKillBounty;
    /**
     * Capacitor capacity
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double CapacitorCapacity;
    /**
     * Signature Radius is used for turret tracking and scanning.
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(100)
    public int SignatureRadius;
    /**
     * Distance below which range does not affect the to-hit equation.
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int MaxRange;
    /**
     * The type ID of the skill that is required.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1;
    /**
     * The distance from a target an entity starts using its weapons.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(15000)
    public int EntityAttackRange;
    /**
     * Deprecated. The minimum number of pieces of loot dropped by this entity.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int EntityLootCountMin;
    /**
     * The maximum number of pieces of loot dropped by this entity.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int EntityLootCountMax;
    /**
     * How much security status is modified by for killing this entity.  Depending on the entity, this may be a positive or negative amount.
     * Value is a % movement of the character's current security towards the upper/lower limit.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int EntitySecurityStatusKillBonus;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int TierDifficulty;
    public final static String RESOURCE_PATH = "SDE/items/celestial/LargeCollidableObject.yaml";
    private static LinkedHashMap<String, LargeCollidableObject> cache = (null);

    @Override
    public int getGroupId() {
        return  226;
    }

    @Override
    public Class<?> getGroup() {
        return LargeCollidableObject.class;
    }

    public static LinkedHashMap<String, LargeCollidableObject> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(LargeCollidableObject.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, LargeCollidableObject> items;

    }

}
