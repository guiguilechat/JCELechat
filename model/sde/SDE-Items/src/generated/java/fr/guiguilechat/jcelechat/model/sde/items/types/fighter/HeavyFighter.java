package fr.guiguilechat.jcelechat.model.sde.items.types.fighter;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.Fighter;
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
     * Authoring has been moved to FSD.
     * meta group of type
     * 
     *  3: Story-line (Cosmos)
     *  4: Faction
     *  5: Officer (rare asteroid NPCs)
     *  6: Deadspace
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MetaGroupID;
    public final static HeavyFighter.MetaGroup METAGROUP = new HeavyFighter.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  2158 :
            {
                return FighterAbilityAfterburnerDuration;
            }
            case  2151 :
            {
                return FighterAbilityAfterburnerSpeedBonus;
            }
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
            case  2325 :
            {
                return FighterAbilityKamikazeDamageEM;
            }
            case  2328 :
            {
                return FighterAbilityKamikazeDamageExp;
            }
            case  2327 :
            {
                return FighterAbilityKamikazeDamageKin;
            }
            case  2326 :
            {
                return FighterAbilityKamikazeDamageTherm;
            }
            case  2330 :
            {
                return FighterAbilityKamikazeRange;
            }
            case  2432 :
            {
                return FighterAbilityKamikazeResistanceID;
            }
            case  2329 :
            {
                return FighterAbilityKamikazeSignatureRadius;
            }
            case  2349 :
            {
                return FighterAbilityLaunchBombDuration;
            }
            case  2324 :
            {
                return FighterAbilityLaunchBombType;
            }
            case  2154 :
            {
                return FighterAbilityMicroJumpDriveDistance;
            }
            case  2155 :
            {
                return FighterAbilityMicroJumpDriveDuration;
            }
            case  2156 :
            {
                return FighterAbilityMicroJumpDriveSignatureRadiusBonus;
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
            case  2214 :
            {
                return FighterSquadronIsHeavy;
            }
            case  2742 :
            {
                return FighterSquadronIsStandupHeavy;
            }
            case  1692 :
            {
                return MetaGroupID;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public IMetaGroup<HeavyFighter> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<HeavyFighter>
    {
        public final static String RESOURCE_PATH = "SDE/items/fighter/HeavyFighter.yaml";
        private Map<String, HeavyFighter> cache = (null);

        @Override
        public IMetaCategory<? super HeavyFighter> category() {
            return Fighter.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1653;
        }

        @Override
        public String getName() {
            return "HeavyFighter";
        }

        @Override
        public synchronized Map<String, HeavyFighter> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(HeavyFighter.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, HeavyFighter> items;
        }
    }
}
