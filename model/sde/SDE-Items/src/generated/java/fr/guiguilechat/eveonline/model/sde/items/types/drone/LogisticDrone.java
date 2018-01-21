
package fr.guiguilechat.eveonline.model.sde.items.types.drone;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.Drone;
import org.yaml.snakeyaml.Yaml;

public class LogisticDrone
    extends Drone
{

    /**
     * The amount of charge used from the capacitor for a module activation.
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double CapacitorNeed;
    /**
     * Amount of maximum shield HP on the item.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShieldCapacity;
    /**
     * The number of hit points on the entities armor.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ArmorHP;
    /**
     * Multiplies EM damage taken by Armor. 
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0D)
    public double ArmorEmDamageResonance;
    /**
     * DO NOT MESS WITH
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double ArmorUniformity;
    /**
     * Multiplies EXPLOSIVE damage taken by Armor. 
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0D)
    public double ArmorExplosiveDamageResonance;
    /**
     * 
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterAbilityAntiFighterMissileResistance;
    /**
     * Multiplies KINETIC damage taken by Armor. 
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0D)
    public double ArmorKineticDamageResonance;
    /**
     * Multiplies THERMAL damage taken by Armor. 
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0D)
    public double ArmorThermalDamageResonance;
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
     * Required skill level for skill 2
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill2Level;
    /**
     * Required skill level for skill 3
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill3Level;
    /**
     * The distance outside of which the entity activates their MWD equivalent.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(2500)
    public int EntityChaseMaxDistance;
    /**
     * The distance at which the entity orbits, follows.. and more.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(500.0D)
    public double EntityFlyRange;
    /**
     * Tech level of an item
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int TechLevel;
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
    public int RequiredSkill2;
    /**
     * The type ID of the skill that is required.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill3;
    /**
     * The maximum number of their targets that the character can attack at a given time.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MaxAttackTargets;
    /**
     * The maximum amount of time stalled before entity chase speed kicks in.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(5000)
    public int EntityChaseMaxDelay;
    /**
     * Bonus to shield.
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0D)
    public double ShieldBonus;
    /**
     * Chance that the max delay is waited before chase is engaged.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int EntityChaseMaxDelayChance;
    /**
     * The maximum amount of time chase is ever engaged for.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(5000)
    public int EntityChaseMaxDuration;
    /**
     * The agility of the object.
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(0.0D)
    public double Agility;
    /**
     * The chance of engaging chase for the maximum duration.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int EntityChaseMaxDurationChance;
    /**
     * Length of activation time.
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double Duration;
    /**
     * scanning speed in milliseconds
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int ScanSpeed;
    /**
     * An amount to modify the structural damage by.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int StructureDamageAmount;
    /**
     * An amount to modify the armor damage by.
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int ArmorDamageAmount;
    /**
     * Attribute ID of the resistance type v's this Ewar module.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RemoteResistanceID;
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
    /**
     * The ranking of the module within its tech level
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MetaLevel;
    /**
     * The speed that entities fly at when not chasing a target.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int EntityCruiseSpeed;
    public final static String RESOURCE_PATH = "SDE/items/drone/LogisticDrone.yaml";
    private static LinkedHashMap<String, LogisticDrone> cache = (null);

    @Override
    public int getGroupId() {
        return  640;
    }

    @Override
    public Class<?> getGroup() {
        return LogisticDrone.class;
    }

    public static LinkedHashMap<String, LogisticDrone> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(LogisticDrone.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, LogisticDrone> items;

    }

}
