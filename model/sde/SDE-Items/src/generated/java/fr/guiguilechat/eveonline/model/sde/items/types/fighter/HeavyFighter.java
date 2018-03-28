package fr.guiguilechat.eveonline.model.sde.items.types.fighter;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.Fighter;
import org.yaml.snakeyaml.Yaml;

public class HeavyFighter
    extends Fighter
{
    /**
     * Duration
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int FighterAbilityAfterburnerDuration;
    /**
     * Maximum Velocity Bonus
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(100)
    public int FighterAbilityAfterburnerSpeedBonus;
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
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int FighterAbilityKamikazeDamageEM;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int FighterAbilityKamikazeDamageExp;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int FighterAbilityKamikazeDamageKin;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int FighterAbilityKamikazeDamageTherm;
    /**
     * Range at which the fighters Explode from the target
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(500)
    public int FighterAbilityKamikazeRange;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterAbilityKamikazeResistanceID;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterAbilityKamikazeSignatureRadius;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterAbilityLaunchBombDuration;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterAbilityLaunchBombType;
    /**
     * Jump Range
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterAbilityMicroJumpDriveDistance;
    /**
     * Duration
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterAbilityMicroJumpDriveDuration;
    /**
     * Signature Radius Bonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterAbilityMicroJumpDriveSignatureRadiusBonus;
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
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterSquadronIsHeavy;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterSquadronIsStandupHeavy;
    /**
     * meta group of type
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MetaGroupID;
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

    public static synchronized LinkedHashMap<String, HeavyFighter> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(HeavyFighter.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, HeavyFighter> items;
    }
}
