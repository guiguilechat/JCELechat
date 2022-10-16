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
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityAfterburnerDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityAfterburnerSpeedBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityAntiFighterMissileResistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityECMDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityECMRangeFalloff;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityECMRangeOptimal;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityECMStrengthGravimetric;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityECMStrengthLadar;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityECMStrengthMagnetometric;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityECMStrengthRadar;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityECMTargetJam;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityECMTargetSuccess;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityEnergyNeutralizerAmount;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityEnergyNeutralizerDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityEnergyNeutralizerFalloffRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityEnergyNeutralizerOptimalRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityMicroWarpDriveDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityMicroWarpDriveSignatureRadiusBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityMicroWarpDriveSpeedBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityStasisWebifierDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityStasisWebifierFalloffRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityStasisWebifierOptimalRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityStasisWebifierResistanceID;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityStasisWebifierSpeedPenalty;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityStasisWebifierSpeedPenaltyInterim;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityWarpDisruptionDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityWarpDisruptionPointStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityWarpDisruptionPointStrengthInterim;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityWarpDisruptionRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterRefuelingTime;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterSquadronIsStandupSupport;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterSquadronIsSupport;
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
import org.yaml.snakeyaml.Yaml;

public class SupportFighter
    extends Fighter
{
    /**
     * Duration
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int fighterabilityafterburnerduration;
    /**
     * Maximum Velocity Bonus
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(100)
    public int fighterabilityafterburnerspeedbonus;
    /**
     * Duration
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int fighterabilityecmduration;
    /**
     * Effectiveness Falloff
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int fighterabilityecmrangefalloff;
    /**
     * Optimal Range
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int fighterabilityecmrangeoptimal;
    /**
     * Gravimetric ECM Jammer Strength
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double fighterabilityecmstrengthgravimetric;
    /**
     * Ladar ECM Jammer Strength
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double fighterabilityecmstrengthladar;
    /**
     * Magnetometric ECM Jammer Strength
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double fighterabilityecmstrengthmagnetometric;
    /**
     * Radar ECM Jammer Strength
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double fighterabilityecmstrengthradar;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fighterabilityecmtargetjam;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fighterabilityecmtargetsuccess;
    /**
     * Energy Amount Neutralized
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int fighterabilityenergyneutralizeramount;
    /**
     * Duration
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int fighterabilityenergyneutralizerduration;
    /**
     * Effectiveness Falloff
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int fighterabilityenergyneutralizerfalloffrange;
    /**
     * Optimal Range
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int fighterabilityenergyneutralizeroptimalrange;
    /**
     * Duration
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int fighterabilitystasiswebifierduration;
    /**
     * Effectiveness Falloff
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int fighterabilitystasiswebifierfalloffrange;
    /**
     * Optimal Range
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int fighterabilitystasiswebifieroptimalrange;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fighterabilitystasiswebifierresistanceid;
    /**
     * Maximum Velocity Bonus
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int fighterabilitystasiswebifierspeedpenalty;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fighterabilitystasiswebifierspeedpenaltyinterim;
    /**
     * Duration
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int fighterabilitywarpdisruptionduration;
    /**
     * Warp Disruption Strength
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int fighterabilitywarpdisruptionpointstrength;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fighterabilitywarpdisruptionpointstrengthinterim;
    /**
     * Optimal Range
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int fighterabilitywarpdisruptionrange;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fightersquadronisstandupsupport;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fightersquadronissupport;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {FighterAbilityStasisWebifierDuration.INSTANCE, ShieldCapacity.INSTANCE, FighterAbilityStasisWebifierSpeedPenalty.INSTANCE, ShieldCharge.INSTANCE, FighterAbilityStasisWebifierSpeedPenaltyInterim.INSTANCE, Hp.INSTANCE, FighterAbilityStasisWebifierOptimalRange.INSTANCE, FighterAbilityStasisWebifierFalloffRange.INSTANCE, FighterAbilityStasisWebifierResistanceID.INSTANCE, StructureUniformity.INSTANCE, FighterAbilityAntiFighterMissileResistance.INSTANCE, ShieldEmDamageResonance.INSTANCE, ShieldExplosiveDamageResonance.INSTANCE, ShieldKineticDamageResonance.INSTANCE, ShieldThermalDamageResonance.INSTANCE, RequiredSkill1Level.INSTANCE, RequiredSkill2Level.INSTANCE, FighterAbilityWarpDisruptionDuration.INSTANCE, FighterAbilityWarpDisruptionRange.INSTANCE, FighterAbilityWarpDisruptionPointStrength.INSTANCE, FighterAbilityWarpDisruptionPointStrengthInterim.INSTANCE, StructureItemVisualFlag.INSTANCE, FighterAbilityEnergyNeutralizerDuration.INSTANCE, FighterAbilityEnergyNeutralizerOptimalRange.INSTANCE, FighterAbilityEnergyNeutralizerFalloffRange.INSTANCE, Radius.INSTANCE, FighterAbilityEnergyNeutralizerAmount.INSTANCE, FighterSquadronIsSupport.INSTANCE, MaxVelocity.INSTANCE, TechLevel.INSTANCE, Capacity.INSTANCE, FighterSquadronMaxSize.INSTANCE, SignatureRadius.INSTANCE, FighterAbilityECMDuration.INSTANCE, FighterAbilityECMRangeOptimal.INSTANCE, FighterAbilityECMRangeFalloff.INSTANCE, FighterSquadronOrbitRange.INSTANCE, ScanResolution.INSTANCE, FighterSquadronIsStandupSupport.INSTANCE, RequiredSkill1 .INSTANCE, RequiredSkill2 .INSTANCE, MaxLockedTargets.INSTANCE, RemoteRepairImpedance.INSTANCE, FighterAbilityECMStrengthGravimetric.INSTANCE, Agility.INSTANCE, FighterAbilityECMStrengthLadar.INSTANCE, FighterAbilityECMStrengthMagnetometric.INSTANCE, FighterAbilityECMStrengthRadar.INSTANCE, FighterAbilityECMTargetSuccess.INSTANCE, FighterAbilityECMTargetJam.INSTANCE, MaxTargetRange.INSTANCE, ScanRadarStrength.INSTANCE, ScanLadarStrength.INSTANCE, ScanMagnetometricStrength.INSTANCE, ScanGravimetricStrength.INSTANCE, RemoteAssistanceImpedance.INSTANCE, WarpSpeedMultiplier.INSTANCE, FighterSquadronRole.INSTANCE, ShieldRechargeRate.INSTANCE, ShieldUniformity.INSTANCE, FighterAbilityAfterburnerSpeedBonus.INSTANCE, FighterAbilityMicroWarpDriveSpeedBonus.INSTANCE, FighterAbilityMicroWarpDriveSignatureRadiusBonus.INSTANCE, FighterAbilityMicroWarpDriveDuration.INSTANCE, FighterAbilityAfterburnerDuration.INSTANCE, MetaLevelOld.INSTANCE, FighterRefuelingTime.INSTANCE })));
    public static final SupportFighter.MetaGroup METAGROUP = new SupportFighter.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  2158 :
            {
                return fighterabilityafterburnerduration;
            }
            case  2151 :
            {
                return fighterabilityafterburnerspeedbonus;
            }
            case  2220 :
            {
                return fighterabilityecmduration;
            }
            case  2222 :
            {
                return fighterabilityecmrangefalloff;
            }
            case  2221 :
            {
                return fighterabilityecmrangeoptimal;
            }
            case  2246 :
            {
                return fighterabilityecmstrengthgravimetric;
            }
            case  2247 :
            {
                return fighterabilityecmstrengthladar;
            }
            case  2248 :
            {
                return fighterabilityecmstrengthmagnetometric;
            }
            case  2249 :
            {
                return fighterabilityecmstrengthradar;
            }
            case  2251 :
            {
                return fighterabilityecmtargetjam;
            }
            case  2250 :
            {
                return fighterabilityecmtargetsuccess;
            }
            case  2211 :
            {
                return fighterabilityenergyneutralizeramount;
            }
            case  2208 :
            {
                return fighterabilityenergyneutralizerduration;
            }
            case  2210 :
            {
                return fighterabilityenergyneutralizerfalloffrange;
            }
            case  2209 :
            {
                return fighterabilityenergyneutralizeroptimalrange;
            }
            case  2183 :
            {
                return fighterabilitystasiswebifierduration;
            }
            case  2187 :
            {
                return fighterabilitystasiswebifierfalloffrange;
            }
            case  2186 :
            {
                return fighterabilitystasiswebifieroptimalrange;
            }
            case  2188 :
            {
                return fighterabilitystasiswebifierresistanceid;
            }
            case  2184 :
            {
                return fighterabilitystasiswebifierspeedpenalty;
            }
            case  2185 :
            {
                return fighterabilitystasiswebifierspeedpenaltyinterim;
            }
            case  2203 :
            {
                return fighterabilitywarpdisruptionduration;
            }
            case  2205 :
            {
                return fighterabilitywarpdisruptionpointstrength;
            }
            case  2206 :
            {
                return fighterabilitywarpdisruptionpointstrengthinterim;
            }
            case  2204 :
            {
                return fighterabilitywarpdisruptionrange;
            }
            case  2741 :
            {
                return fightersquadronisstandupsupport;
            }
            case  2213 :
            {
                return fightersquadronissupport;
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
    public IMetaGroup<SupportFighter> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<SupportFighter>
    {
        public static final String RESOURCE_PATH = "SDE/types/fighter/SupportFighter.yaml";
        private Map<String, SupportFighter> cache = (null);

        @Override
        public IMetaCategory<? super SupportFighter> category() {
            return Fighter.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1537;
        }

        @Override
        public String getName() {
            return "SupportFighter";
        }

        @Override
        public synchronized Map<String, SupportFighter> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(SupportFighter.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, SupportFighter> types;
        }
    }
}
