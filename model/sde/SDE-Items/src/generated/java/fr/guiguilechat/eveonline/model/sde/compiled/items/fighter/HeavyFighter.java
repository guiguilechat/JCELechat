
package fr.guiguilechat.eveonline.model.sde.compiled.items.fighter;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.DefaultValue;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Fighter;
import org.yaml.snakeyaml.Yaml;

public class HeavyFighter
    extends Fighter
{

    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double FighterAbilityKamikazeResistanceID;
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
    public double FighterAbilityLaunchBombType;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double FighterAbilityKamikazeDamageEM;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double FighterAbilityKamikazeDamageTherm;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double FighterAbilityKamikazeDamageKin;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double FighterAbilityKamikazeDamageExp;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double FighterAbilityKamikazeSignatureRadius;
    /**
     * Range at which the fighters Explode from the target
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(500.0D)
    public double FighterAbilityKamikazeRange;
    /**
     * meta group of type
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double MetaGroupID;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double FighterSquadronIsHeavy;
    /**
     * 
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double FighterAbilityLaunchBombDuration;
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
    @Stackable(true)
    @DefaultValue(0.0D)
    public double FighterSquadronIsStandupHeavy;
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
     * Maximum Velocity Bonus
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(100.0D)
    public double FighterAbilityAfterburnerSpeedBonus;
    /**
     * Jump Range
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double FighterAbilityMicroJumpDriveDistance;
    /**
     * Duration
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double FighterAbilityMicroJumpDriveDuration;
    /**
     * Signature Radius Bonus
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double FighterAbilityMicroJumpDriveSignatureRadiusBonus;
    /**
     * Duration
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double FighterAbilityAfterburnerDuration;
    /**
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double FighterAbilityMissilesResistanceID;
    public final static String RESOURCE_PATH = "SDE/items/fighter/HeavyFighter.yaml";
    private static LinkedHashMap<String, HeavyFighter> cache = (null);

    @Override
    public int getGroupId() {
        return  1653;
    }

    @Override
    public Class<?> getGroup() {
        return HeavyFighter.class;
    }

    public static LinkedHashMap<String, HeavyFighter> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(HeavyFighter.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, HeavyFighter> items;

    }

}
