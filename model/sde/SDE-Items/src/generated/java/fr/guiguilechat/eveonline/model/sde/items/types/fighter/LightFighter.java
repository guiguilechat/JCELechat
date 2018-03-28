package fr.guiguilechat.eveonline.model.sde.items.types.fighter;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.Fighter;
import org.yaml.snakeyaml.Yaml;

public class LightFighter
    extends Fighter
{
    /**
     * EM Damage
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int FighterAbilityAttackMissileDamageEM;
    /**
     * Explosive Damage
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int FighterAbilityAttackMissileDamageExp;
    /**
     * Kinetic Damage
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int FighterAbilityAttackMissileDamageKin;
    /**
     * Damage Multiplier
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int FighterAbilityAttackMissileDamageMultiplier;
    /**
     * Thermal Damage
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int FighterAbilityAttackMissileDamageTherm;
    /**
     * Rate of fire
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int FighterAbilityAttackMissileDuration;
    /**
     * Explosion Radius
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int FighterAbilityAttackMissileExplosionRadius;
    /**
     * Explosion Velocity
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int FighterAbilityAttackMissileExplosionVelocity;
    /**
     * Accuracy Falloff
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int FighterAbilityAttackMissileRangeFalloff;
    /**
     * Optimal Range
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int FighterAbilityAttackMissileRangeOptimal;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double FighterAbilityAttackMissileReductionFactor;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double FighterAbilityAttackMissileReductionSensitivity;
    /**
     * Duration
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int FighterAbilityEvasiveManeuversDuration;
    /**
     * Shield EM Damage Resistance
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double FighterAbilityEvasiveManeuversEmResonance;
    /**
     * Shield Explosive Damage Resistance
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double FighterAbilityEvasiveManeuversExpResonance;
    /**
     * Shield Kinetic Damage Resistance
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double FighterAbilityEvasiveManeuversKinResonance;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int FighterAbilityEvasiveManeuversSignatureRadiusBonus;
    /**
     * Maximum Velocity Bonus
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int FighterAbilityEvasiveManeuversSpeedBonus;
    /**
     * Shield Thermal Damage Resistance
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double FighterAbilityEvasiveManeuversThermResonance;
    /**
     * EM Damage
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int FighterAbilityMissilesDamageEM;
    /**
     * Explosive Damage
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int FighterAbilityMissilesDamageExp;
    /**
     * Kinetic Damage
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int FighterAbilityMissilesDamageKin;
    /**
     * Damage Multiplier
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int FighterAbilityMissilesDamageMultiplier;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double FighterAbilityMissilesDamageReductionFactor;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double FighterAbilityMissilesDamageReductionSensitivity;
    /**
     * Thermal Damage
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int FighterAbilityMissilesDamageTherm;
    /**
     * Rate of fire
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int FighterAbilityMissilesDuration;
    /**
     * Explosion Radius
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int FighterAbilityMissilesExplosionRadius;
    /**
     * Explosion Velocity
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int FighterAbilityMissilesExplosionVelocity;
    /**
     * Optimal Range
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int FighterAbilityMissilesRange;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterAbilityMissilesResistanceID;
    /**
     * Duration
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int FighterAbilityTackleDuration;
    /**
     * Range
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int FighterAbilityTackleRange;
    /**
     * Warp Disruption Strength
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterAbilityTackleWarpDisruptionPointStrength;
    /**
     * Maximum Velocity Bonus
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int FighterAbilityTackleWebSpeedPenalty;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterAbilityTackleWebSpeedPenaltyInterim;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterSquadronIsLight;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterSquadronIsStandupLight;
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

    public static synchronized LinkedHashMap<String, LightFighter> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(LightFighter.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, LightFighter> items;
    }
}
