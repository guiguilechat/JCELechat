package fr.guiguilechat.jcelechat.model.sde.types.fighter;

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
import fr.guiguilechat.jcelechat.model.sde.types.Fighter;
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
    public static final LightFighter.MetaGroup METAGROUP = new LightFighter.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  2227 :
            {
                return FighterAbilityAttackMissileDamageEM;
            }
            case  2230 :
            {
                return FighterAbilityAttackMissileDamageExp;
            }
            case  2229 :
            {
                return FighterAbilityAttackMissileDamageKin;
            }
            case  2226 :
            {
                return FighterAbilityAttackMissileDamageMultiplier;
            }
            case  2228 :
            {
                return FighterAbilityAttackMissileDamageTherm;
            }
            case  2233 :
            {
                return FighterAbilityAttackMissileDuration;
            }
            case  2234 :
            {
                return FighterAbilityAttackMissileExplosionRadius;
            }
            case  2235 :
            {
                return FighterAbilityAttackMissileExplosionVelocity;
            }
            case  2237 :
            {
                return FighterAbilityAttackMissileRangeFalloff;
            }
            case  2236 :
            {
                return FighterAbilityAttackMissileRangeOptimal;
            }
            case  2231 :
            {
                return FighterAbilityAttackMissileReductionFactor;
            }
            case  2232 :
            {
                return FighterAbilityAttackMissileReductionSensitivity;
            }
            case  2123 :
            {
                return FighterAbilityEvasiveManeuversDuration;
            }
            case  2118 :
            {
                return FighterAbilityEvasiveManeuversEmResonance;
            }
            case  2121 :
            {
                return FighterAbilityEvasiveManeuversExpResonance;
            }
            case  2120 :
            {
                return FighterAbilityEvasiveManeuversKinResonance;
            }
            case  2225 :
            {
                return FighterAbilityEvasiveManeuversSignatureRadiusBonus;
            }
            case  2224 :
            {
                return FighterAbilityEvasiveManeuversSpeedBonus;
            }
            case  2119 :
            {
                return FighterAbilityEvasiveManeuversThermResonance;
            }
            case  2131 :
            {
                return FighterAbilityMissilesDamageEM;
            }
            case  2134 :
            {
                return FighterAbilityMissilesDamageExp;
            }
            case  2133 :
            {
                return FighterAbilityMissilesDamageKin;
            }
            case  2130 :
            {
                return FighterAbilityMissilesDamageMultiplier;
            }
            case  2127 :
            {
                return FighterAbilityMissilesDamageReductionFactor;
            }
            case  2128 :
            {
                return FighterAbilityMissilesDamageReductionSensitivity;
            }
            case  2132 :
            {
                return FighterAbilityMissilesDamageTherm;
            }
            case  2182 :
            {
                return FighterAbilityMissilesDuration;
            }
            case  2125 :
            {
                return FighterAbilityMissilesExplosionRadius;
            }
            case  2126 :
            {
                return FighterAbilityMissilesExplosionVelocity;
            }
            case  2149 :
            {
                return FighterAbilityMissilesRange;
            }
            case  2170 :
            {
                return FighterAbilityMissilesResistanceID;
            }
            case  2238 :
            {
                return FighterAbilityTackleDuration;
            }
            case  2239 :
            {
                return FighterAbilityTackleRange;
            }
            case  2425 :
            {
                return FighterAbilityTackleWarpDisruptionPointStrength;
            }
            case  2242 :
            {
                return FighterAbilityTackleWebSpeedPenalty;
            }
            case  2243 :
            {
                return FighterAbilityTackleWebSpeedPenaltyInterim;
            }
            case  2212 :
            {
                return FighterSquadronIsLight;
            }
            case  2740 :
            {
                return FighterSquadronIsStandupLight;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public IMetaGroup<LightFighter> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<LightFighter>
    {
        public static final String RESOURCE_PATH = "SDE/types/fighter/LightFighter.yaml";
        private Map<String, LightFighter> cache = (null);

        @Override
        public IMetaCategory<? super LightFighter> category() {
            return Fighter.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1652;
        }

        @Override
        public String getName() {
            return "LightFighter";
        }

        @Override
        public synchronized Map<String, LightFighter> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(LightFighter.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, LightFighter> types;
        }
    }
}
