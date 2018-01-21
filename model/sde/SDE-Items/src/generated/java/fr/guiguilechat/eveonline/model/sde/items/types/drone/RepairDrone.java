
package fr.guiguilechat.eveonline.model.sde.items.types.drone;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.Drone;
import org.yaml.snakeyaml.Yaml;

public class RepairDrone
    extends Drone
{

    /**
     * Damage multiplier.
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(1.0D)
    public double DamageMultiplier;
    /**
     * The maximum number of their targets that the character can attack at a given time.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double MaxAttackTargets;
    /**
     * Length of activation time.
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double Duration;
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
     * An amount to modify the structural damage by.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double StructureDamageAmount;
    /**
     * An amount to modify the armor damage by.
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double ArmorDamageAmount;
    /**
     * The range at which this thing does it thing.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double OrbitRange;
    /**
     * Amount of time taken to fully recharge the shield.
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ShieldRechargeRate;
    /**
     * Time in milliseconds between possible activations
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double Speed;
    /**
     * Explosive damage done.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ExplosiveDamage;
    /**
     * Kinetic damage done.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double KineticDamage;
    /**
     * Distance below which range does not affect the to-hit equation.
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double MaxRange;
    public final static String RESOURCE_PATH = "SDE/items/drone/RepairDrone.yaml";
    private static LinkedHashMap<String, RepairDrone> cache = (null);

    @Override
    public int getGroupId() {
        return  299;
    }

    @Override
    public Class<?> getGroup() {
        return RepairDrone.class;
    }

    public static LinkedHashMap<String, RepairDrone> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(RepairDrone.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, RepairDrone> items;

    }

}
