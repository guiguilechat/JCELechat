
package fr.guiguilechat.eveonline.model.sde.compiled.items.fighter;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.DefaultValue;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Fighter;
import org.yaml.snakeyaml.Yaml;

public class LightFighter
    extends Fighter
{

    /**
     * Rate of fire
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double FighterAbilityMissilesDuration;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double FighterSquadronIsLight;
    /**
     * Maximum Velocity Bonus
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double FighterAbilityEvasiveManeuversSpeedBonus;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double FighterAbilityEvasiveManeuversSignatureRadiusBonus;
    /**
     * Damage Multiplier
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double FighterAbilityAttackMissileDamageMultiplier;
    /**
     * EM Damage
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double FighterAbilityAttackMissileDamageEM;
    /**
     * Thermal Damage
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double FighterAbilityAttackMissileDamageTherm;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double FighterSquadronIsStandupLight;
    /**
     * Kinetic Damage
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double FighterAbilityAttackMissileDamageKin;
    /**
     * Explosive Damage
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double FighterAbilityAttackMissileDamageExp;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double FighterAbilityAttackMissileReductionFactor;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double FighterAbilityAttackMissileReductionSensitivity;
    /**
     * Rate of fire
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double FighterAbilityAttackMissileDuration;
    /**
     * Explosion Radius
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double FighterAbilityAttackMissileExplosionRadius;
    /**
     * Explosion Velocity
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double FighterAbilityAttackMissileExplosionVelocity;
    /**
     * Optimal Range
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double FighterAbilityAttackMissileRangeOptimal;
    /**
     * Accuracy Falloff
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double FighterAbilityAttackMissileRangeFalloff;
    /**
     * Duration
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double FighterAbilityTackleDuration;
    /**
     * Range
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double FighterAbilityTackleRange;
    /**
     * Maximum Velocity Bonus
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double FighterAbilityTackleWebSpeedPenalty;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double FighterAbilityTackleWebSpeedPenaltyInterim;
    /**
     * Shield EM Damage Resistance
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultValue(1.0D)
    public double FighterAbilityEvasiveManeuversEmResonance;
    /**
     * Shield Thermal Damage Resistance
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultValue(1.0D)
    public double FighterAbilityEvasiveManeuversThermResonance;
    /**
     * Shield Kinetic Damage Resistance
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultValue(1.0D)
    public double FighterAbilityEvasiveManeuversKinResonance;
    /**
     * Shield Explosive Damage Resistance
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultValue(1.0D)
    public double FighterAbilityEvasiveManeuversExpResonance;
    /**
     * Duration
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double FighterAbilityEvasiveManeuversDuration;
    /**
     * Explosion Radius
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double FighterAbilityMissilesExplosionRadius;
    /**
     * Explosion Velocity
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double FighterAbilityMissilesExplosionVelocity;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double FighterAbilityMissilesDamageReductionFactor;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double FighterAbilityMissilesDamageReductionSensitivity;
    /**
     * Damage Multiplier
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double FighterAbilityMissilesDamageMultiplier;
    /**
     * EM Damage
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double FighterAbilityMissilesDamageEM;
    /**
     * Thermal Damage
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double FighterAbilityMissilesDamageTherm;
    /**
     * Kinetic Damage
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double FighterAbilityMissilesDamageKin;
    /**
     * Explosive Damage
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double FighterAbilityMissilesDamageExp;
    /**
     * Optimal Range
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double FighterAbilityMissilesRange;
    /**
     * Warp Disruption Strength
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double FighterAbilityTackleWarpDisruptionPointStrength;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double FighterAbilityMissilesResistanceID;
    public final static String RESOURCE_PATH = "SDE/items/fighter/LightFighter.yaml";
    private static LinkedHashMap<String, LightFighter> cache = (null);

    @Override
    public int getGroupId() {
        return  1652;
    }

    @Override
    public Class<?> getGroup() {
        return LightFighter.class;
    }

    public static LinkedHashMap<String, LightFighter> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(LightFighter.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, LightFighter> items;

    }

}
