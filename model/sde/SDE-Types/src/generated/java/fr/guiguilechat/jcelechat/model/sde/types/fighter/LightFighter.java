package fr.guiguilechat.jcelechat.model.sde.types.fighter;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultRealValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.Agility;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityAntiFighterMissileResistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityAttackMissileDamageEM;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityAttackMissileDamageExp;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityAttackMissileDamageKin;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityAttackMissileDamageMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityAttackMissileDamageTherm;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityAttackMissileDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityAttackMissileExplosionRadius;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityAttackMissileExplosionVelocity;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityAttackMissileRangeFalloff;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityAttackMissileRangeOptimal;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityAttackMissileReductionFactor;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityAttackMissileReductionSensitivity;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityEvasiveManeuversDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityEvasiveManeuversEmResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityEvasiveManeuversExpResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityEvasiveManeuversKinResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityEvasiveManeuversSignatureRadiusBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityEvasiveManeuversSpeedBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityEvasiveManeuversThermResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityMicroWarpDriveDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityMicroWarpDriveSignatureRadiusBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityMicroWarpDriveSpeedBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityMissilesDamageEM;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityMissilesDamageExp;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityMissilesDamageKin;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityMissilesDamageMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityMissilesDamageReductionFactor;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityMissilesDamageReductionSensitivity;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityMissilesDamageTherm;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityMissilesDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityMissilesExplosionRadius;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityMissilesExplosionVelocity;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityMissilesRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityMissilesResistanceID;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityTackleDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityTackleRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityTackleWarpDisruptionPointStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityTackleWebSpeedPenalty;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityTackleWebSpeedPenaltyInterim;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterRefuelingTime;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterSquadronIsLight;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterSquadronIsStandupLight;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterSquadronMaxSize;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterSquadronOrbitRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterSquadronRole;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hp;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxLockedTargets;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxTargetRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxVelocity;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaLevelOld;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.RemoteAssistanceImpedance;
import fr.guiguilechat.jcelechat.model.sde.attributes.RemoteRepairImpedance;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill2;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill2Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanGravimetricStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanLadarStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanMagnetometricStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanRadarStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanResolution;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldCharge;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldEmDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldExplosiveDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldKineticDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldRechargeRate;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldThermalDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldUniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.SignatureRadius;
import fr.guiguilechat.jcelechat.model.sde.attributes.StructureItemVisualFlag;
import fr.guiguilechat.jcelechat.model.sde.attributes.StructureUniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.TechLevel;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarpSpeedMultiplier;
import fr.guiguilechat.jcelechat.model.sde.types.Fighter;
import org.yaml.snakeyaml.LoaderOptions;
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
    public int fighterabilityattackmissiledamageem;
    /**
     * Explosive Damage
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int fighterabilityattackmissiledamageexp;
    /**
     * Kinetic Damage
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int fighterabilityattackmissiledamagekin;
    /**
     * Damage Multiplier
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int fighterabilityattackmissiledamagemultiplier;
    /**
     * Thermal Damage
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int fighterabilityattackmissiledamagetherm;
    /**
     * Rate of fire
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int fighterabilityattackmissileduration;
    /**
     * Explosion Radius
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int fighterabilityattackmissileexplosionradius;
    /**
     * Explosion Velocity
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int fighterabilityattackmissileexplosionvelocity;
    /**
     * Accuracy Falloff
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int fighterabilityattackmissilerangefalloff;
    /**
     * Optimal Range
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int fighterabilityattackmissilerangeoptimal;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double fighterabilityattackmissilereductionfactor;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double fighterabilityattackmissilereductionsensitivity;
    /**
     * Duration
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int fighterabilityevasivemaneuversduration;
    /**
     * Shield EM Damage Resistance
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double fighterabilityevasivemaneuversemresonance;
    /**
     * Shield Explosive Damage Resistance
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double fighterabilityevasivemaneuversexpresonance;
    /**
     * Shield Kinetic Damage Resistance
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double fighterabilityevasivemaneuverskinresonance;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int fighterabilityevasivemaneuverssignatureradiusbonus;
    /**
     * Maximum Velocity Bonus
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int fighterabilityevasivemaneuversspeedbonus;
    /**
     * Shield Thermal Damage Resistance
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double fighterabilityevasivemaneuversthermresonance;
    /**
     * EM Damage
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int fighterabilitymissilesdamageem;
    /**
     * Explosive Damage
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int fighterabilitymissilesdamageexp;
    /**
     * Kinetic Damage
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int fighterabilitymissilesdamagekin;
    /**
     * Damage Multiplier
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int fighterabilitymissilesdamagemultiplier;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double fighterabilitymissilesdamagereductionfactor;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double fighterabilitymissilesdamagereductionsensitivity;
    /**
     * Thermal Damage
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int fighterabilitymissilesdamagetherm;
    /**
     * Rate of fire
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int fighterabilitymissilesduration;
    /**
     * Explosion Radius
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int fighterabilitymissilesexplosionradius;
    /**
     * Explosion Velocity
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int fighterabilitymissilesexplosionvelocity;
    /**
     * Optimal Range
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int fighterabilitymissilesrange;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fighterabilitymissilesresistanceid;
    /**
     * Duration
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int fighterabilitytackleduration;
    /**
     * Range
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int fighterabilitytacklerange;
    /**
     * Warp Disruption Strength
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fighterabilitytacklewarpdisruptionpointstrength;
    /**
     * Maximum Velocity Bonus
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int fighterabilitytacklewebspeedpenalty;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fighterabilitytacklewebspeedpenaltyinterim;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fightersquadronislight;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fightersquadronisstanduplight;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {FighterAbilityMissilesDuration.INSTANCE, ShieldCapacity.INSTANCE, ShieldCharge.INSTANCE, Hp.INSTANCE, StructureUniformity.INSTANCE, FighterAbilityAntiFighterMissileResistance.INSTANCE, ShieldEmDamageResonance.INSTANCE, ShieldExplosiveDamageResonance.INSTANCE, ShieldKineticDamageResonance.INSTANCE, ShieldThermalDamageResonance.INSTANCE, RequiredSkill1Level.INSTANCE, RequiredSkill2Level.INSTANCE, StructureItemVisualFlag.INSTANCE, Radius.INSTANCE, FighterSquadronIsLight.INSTANCE, MaxVelocity.INSTANCE, TechLevel.INSTANCE, Capacity.INSTANCE, FighterSquadronMaxSize.INSTANCE, SignatureRadius.INSTANCE, FighterSquadronOrbitRange.INSTANCE, FighterAbilityEvasiveManeuversSpeedBonus.INSTANCE, FighterAbilityEvasiveManeuversSignatureRadiusBonus.INSTANCE, FighterAbilityAttackMissileDamageMultiplier.INSTANCE, FighterAbilityAttackMissileDamageEM.INSTANCE, FighterAbilityAttackMissileDamageTherm.INSTANCE, FighterSquadronIsStandupLight.INSTANCE, ScanResolution.INSTANCE, FighterAbilityAttackMissileDamageKin.INSTANCE, FighterAbilityAttackMissileDamageExp.INSTANCE, RequiredSkill1 .INSTANCE, FighterAbilityAttackMissileReductionFactor.INSTANCE, RequiredSkill2 .INSTANCE, FighterAbilityAttackMissileReductionSensitivity.INSTANCE, FighterAbilityAttackMissileDuration.INSTANCE, FighterAbilityAttackMissileExplosionRadius.INSTANCE, FighterAbilityAttackMissileExplosionVelocity.INSTANCE, FighterAbilityAttackMissileRangeOptimal.INSTANCE, FighterAbilityAttackMissileRangeFalloff.INSTANCE, FighterAbilityTackleDuration.INSTANCE, FighterAbilityTackleRange.INSTANCE, MaxLockedTargets.INSTANCE, FighterAbilityTackleWebSpeedPenalty.INSTANCE, FighterAbilityTackleWebSpeedPenaltyInterim.INSTANCE, RemoteRepairImpedance.INSTANCE, FighterAbilityEvasiveManeuversEmResonance.INSTANCE, Agility.INSTANCE, FighterAbilityEvasiveManeuversThermResonance.INSTANCE, FighterAbilityEvasiveManeuversKinResonance.INSTANCE, FighterAbilityEvasiveManeuversExpResonance.INSTANCE, FighterAbilityEvasiveManeuversDuration.INSTANCE, MaxTargetRange.INSTANCE, FighterAbilityMissilesExplosionRadius.INSTANCE, FighterAbilityMissilesExplosionVelocity.INSTANCE, FighterAbilityMissilesDamageReductionFactor.INSTANCE, FighterAbilityMissilesDamageReductionSensitivity.INSTANCE, ScanRadarStrength.INSTANCE, ScanLadarStrength.INSTANCE, FighterAbilityMissilesDamageMultiplier.INSTANCE, ScanMagnetometricStrength.INSTANCE, FighterAbilityMissilesDamageEM.INSTANCE, ScanGravimetricStrength.INSTANCE, FighterAbilityMissilesDamageTherm.INSTANCE, FighterAbilityMissilesDamageKin.INSTANCE, FighterAbilityMissilesDamageExp.INSTANCE, RemoteAssistanceImpedance.INSTANCE, WarpSpeedMultiplier.INSTANCE, FighterSquadronRole.INSTANCE, ShieldRechargeRate.INSTANCE, ShieldUniformity.INSTANCE, FighterAbilityMissilesRange.INSTANCE, FighterAbilityMicroWarpDriveSpeedBonus.INSTANCE, FighterAbilityMicroWarpDriveSignatureRadiusBonus.INSTANCE, FighterAbilityMicroWarpDriveDuration.INSTANCE, FighterAbilityTackleWarpDisruptionPointStrength.INSTANCE, MetaLevelOld.INSTANCE, FighterAbilityMissilesResistanceID.INSTANCE, FighterRefuelingTime.INSTANCE })));
    public static final LightFighter.MetaGroup METAGROUP = new LightFighter.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  2227 :
            {
                return fighterabilityattackmissiledamageem;
            }
            case  2230 :
            {
                return fighterabilityattackmissiledamageexp;
            }
            case  2229 :
            {
                return fighterabilityattackmissiledamagekin;
            }
            case  2226 :
            {
                return fighterabilityattackmissiledamagemultiplier;
            }
            case  2228 :
            {
                return fighterabilityattackmissiledamagetherm;
            }
            case  2233 :
            {
                return fighterabilityattackmissileduration;
            }
            case  2234 :
            {
                return fighterabilityattackmissileexplosionradius;
            }
            case  2235 :
            {
                return fighterabilityattackmissileexplosionvelocity;
            }
            case  2237 :
            {
                return fighterabilityattackmissilerangefalloff;
            }
            case  2236 :
            {
                return fighterabilityattackmissilerangeoptimal;
            }
            case  2231 :
            {
                return fighterabilityattackmissilereductionfactor;
            }
            case  2232 :
            {
                return fighterabilityattackmissilereductionsensitivity;
            }
            case  2123 :
            {
                return fighterabilityevasivemaneuversduration;
            }
            case  2118 :
            {
                return fighterabilityevasivemaneuversemresonance;
            }
            case  2121 :
            {
                return fighterabilityevasivemaneuversexpresonance;
            }
            case  2120 :
            {
                return fighterabilityevasivemaneuverskinresonance;
            }
            case  2225 :
            {
                return fighterabilityevasivemaneuverssignatureradiusbonus;
            }
            case  2224 :
            {
                return fighterabilityevasivemaneuversspeedbonus;
            }
            case  2119 :
            {
                return fighterabilityevasivemaneuversthermresonance;
            }
            case  2131 :
            {
                return fighterabilitymissilesdamageem;
            }
            case  2134 :
            {
                return fighterabilitymissilesdamageexp;
            }
            case  2133 :
            {
                return fighterabilitymissilesdamagekin;
            }
            case  2130 :
            {
                return fighterabilitymissilesdamagemultiplier;
            }
            case  2127 :
            {
                return fighterabilitymissilesdamagereductionfactor;
            }
            case  2128 :
            {
                return fighterabilitymissilesdamagereductionsensitivity;
            }
            case  2132 :
            {
                return fighterabilitymissilesdamagetherm;
            }
            case  2182 :
            {
                return fighterabilitymissilesduration;
            }
            case  2125 :
            {
                return fighterabilitymissilesexplosionradius;
            }
            case  2126 :
            {
                return fighterabilitymissilesexplosionvelocity;
            }
            case  2149 :
            {
                return fighterabilitymissilesrange;
            }
            case  2170 :
            {
                return fighterabilitymissilesresistanceid;
            }
            case  2238 :
            {
                return fighterabilitytackleduration;
            }
            case  2239 :
            {
                return fighterabilitytacklerange;
            }
            case  2425 :
            {
                return fighterabilitytacklewarpdisruptionpointstrength;
            }
            case  2242 :
            {
                return fighterabilitytacklewebspeedpenalty;
            }
            case  2243 :
            {
                return fighterabilitytacklewebspeedpenaltyinterim;
            }
            case  2212 :
            {
                return fightersquadronislight;
            }
            case  2740 :
            {
                return fightersquadronisstanduplight;
            }
            default:
            {
                return super.valueSet((attribute));
            }
        }
    }

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
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
                    LoaderOptions options = new LoaderOptions();
                    options.setCodePointLimit(Integer.MAX_VALUE);
                    cache = new Yaml(options).loadAs(reader, (Container.class)).types;
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
