
package fr.guiguilechat.eveonline.model.sde.compiled.items.drone;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.DefaultValue;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Drone;
import org.yaml.snakeyaml.Yaml;

public class StasisWebifyingDrone
    extends Drone
{

    /**
     * Amount of maximum shield HP on the item.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ShieldCapacity;
    /**
     * The number of hit points on the entities armor.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ArmorHP;
    /**
     * Multiplies EM damage taken by Armor. 
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultValue(1.0D)
    public double ArmorEmDamageResonance;
    /**
     * Multiplies EXPLOSIVE damage taken by Armor. 
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultValue(1.0D)
    public double ArmorExplosiveDamageResonance;
    /**
     * DO NOT MESS WITH
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ArmorUniformity;
    /**
     * 
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultValue(0.2D)
    public double FighterAbilityAntiFighterMissileResistance;
    /**
     * Multiplies KINETIC damage taken by Armor. 
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultValue(1.0D)
    public double ArmorKineticDamageResonance;
    /**
     * Multiplies THERMAL damage taken by Armor. 
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultValue(1.0D)
    public double ArmorThermalDamageResonance;
    /**
     * Multiplies EM damage taken by shield
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultValue(1.0D)
    public double ShieldEmDamageResonance;
    /**
     * Multiplies EXPLOSIVE damage taken by Armor. 
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultValue(1.0D)
    public double ShieldExplosiveDamageResonance;
    /**
     * Multiplies KINETIC damage taken by Armor. 
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultValue(1.0D)
    public double ShieldKineticDamageResonance;
    /**
     * Multiplies THERMAL damage taken by Shield. 
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultValue(1.0D)
    public double ShieldThermalDamageResonance;
    /**
     * Factor by which topspeed increases.
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(1.0D)
    public double SpeedFactor;
    /**
     * Required skill level for skill 2
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double RequiredSkill2Level;
    /**
     * Required skill level for skill 3
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double RequiredSkill3Level;
    /**
     * The distance outside of which the entity activates their MWD equivalent.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(2500.0D)
    public double EntityChaseMaxDistance;
    /**
     * The distance at which the entity orbits, follows.. and more.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(500.0D)
    public double EntityFlyRange;
    /**
     * Tech level of an item
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(1.0D)
    public double TechLevel;
    /**
     * Signature Radius is used for turret tracking and scanning.
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultValue(100.0D)
    public double SignatureRadius;
    /**
     * Distance below which range does not affect the to-hit equation.
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double MaxRange;
    /**
     * The type ID of the skill that is required.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double RequiredSkill2;
    /**
     * The type ID of the skill that is required.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double RequiredSkill3;
    /**
     * The maximum number of their targets that the character can attack at a given time.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double MaxAttackTargets;
    /**
     * The maximum amount of time stalled before entity chase speed kicks in.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(5000.0D)
    public double EntityChaseMaxDelay;
    /**
     * Chance that the max delay is waited before chase is engaged.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(1.0D)
    public double EntityChaseMaxDelayChance;
    /**
     * The agility of the object.
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double Agility;
    /**
     * The maximum amount of time chase is ever engaged for.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(5000.0D)
    public double EntityChaseMaxDuration;
    /**
     * The chance of engaging chase for the maximum duration.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(1.0D)
    public double EntityChaseMaxDurationChance;
    /**
     * Length of activation time.
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double Duration;
    /**
     * scanning speed in milliseconds
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double ScanSpeed;
    /**
     * Attribute ID of the resistance type v's this Ewar module.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double RemoteResistanceID;
    /**
     * Amount of time taken to fully recharge the shield.
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ShieldRechargeRate;
    /**
     * DO NOT MESS WITH This number is deducted from the %chance of the seeping to armor, to slow seep of damage through shield.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ShieldUniformity;
    /**
     * Prefered target signature. The base signature radius at which the turret's tracking speed is rated. 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(1000.0D)
    public double OptimalSigRadius;
    /**
     * The distance from a target an entity starts using its weapons.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(15000.0D)
    public double EntityAttackRange;
    /**
     * The speed that entities fly at when not chasing a target.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double EntityCruiseSpeed;
    public final static String RESOURCE_PATH = "SDE/items/drone/StasisWebifyingDrone.yaml";
    private static LinkedHashMap<String, StasisWebifyingDrone> cache = (null);

    @Override
    public int getGroupId() {
        return  641;
    }

    @Override
    public Class<?> getGroup() {
        return StasisWebifyingDrone.class;
    }

    public static LinkedHashMap<String, StasisWebifyingDrone> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(StasisWebifyingDrone.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, StasisWebifyingDrone> items;

    }

}