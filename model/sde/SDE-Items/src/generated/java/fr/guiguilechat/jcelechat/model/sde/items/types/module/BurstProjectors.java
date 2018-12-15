package fr.guiguilechat.jcelechat.model.sde.items.types.module;

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
import fr.guiguilechat.jcelechat.model.sde.items.types.Module;
import org.yaml.snakeyaml.Yaml;

public class BurstProjectors
    extends Module
{
    /**
     * Booster attribute to explosion radius of missiles vs. signature radius.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double AoeCloudSizeBonus;
    /**
     * Increases velocity of missile explosion
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double AoeVelocityBonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int CanCloak;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int CanFitShipGroup01;
    /**
     * The amount of charge used from the capacitor for a module activation.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double CapacitorNeed;
    /**
     * The amount of the given resource type needed to be consumed for each activation cycle of this structure.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ConsumptionQuantity;
    /**
     * The type of resource needed to be consumed for each activation cycle of this structure.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ConsumptionType;
    /**
     * CPU need of module
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Cpu;
    /**
     * If this ship attribute is NOT 0 then they will be prevented from docking in stations or structures.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DisallowDocking;
    /**
     * Signifies that this module if activated, will prevent ejection from the ship it is fitted to and extend the log out ship removal timer.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DisallowEarlyDeactivation;
    /**
     * If set on a charge or module type, will prevent it from being activated in empire space.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DisallowInEmpireSpace;
    /**
     * If set, this module cannot be activated and made to autorepeat.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DisallowRepeatingActivation;
    /**
     * Duration of the AOE Effect
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DoomsdayAOEDuration;
    /**
     * Radius of the AOE Effect
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DoomsdayAOERange;
    /**
     *  1: Fixed Cylinder (Beam)
     *  2: Cylinder moving in an arc (Slash)
     *  3: Fixed Cone
     *  4: Projected Sphere
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DoomsdayAOEShape;
    /**
     * Signature Radius of the AOE Effect
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DoomsdayAOESignatureRadius;
    /**
     * Determines whether the maxRange attribute is a fixed length or a maximum length of the effect
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DoomsdayRangeIsFixed;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DoomsdayWarningDuration;
    /**
     * Length of activation time.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Duration;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DurationECMJammerBurstProjector;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DurationSensorDampeningBurstProjector;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DurationTargetIlluminationBurstProjector;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DurationWeaponDisruptionBurstProjector;
    /**
     * An amount to modify the power of the target by.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int EnergyNeutralizerAmount;
    /**
     * Autogenerated skill attribute, explosionDelayBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double ExplosionDelayBonus;
    /**
     * Autogenerated skill attribute, falloffBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double FalloffBonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int IsPointTargeted;
    /**
     * Maximum modules of same group that can be activated at same time, 0 = no limit, 1 = 1
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MaxGroupActive;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MaxGroupFitted;
    /**
     * Distance below which range does not affect the to-hit equation.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int MaxRange;
    /**
     * Autogenerated skill attribute, maxRangeBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double MaxRangeBonus;
    /**
     * Bonus to Max Targeting Range
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double MaxTargetRangeBonus;
    /**
     * Authoring has been moved to FSD
     * The ranking of the module within its tech level
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MetaLevel;
    /**
     * Autogenerated skill attribute, missileVelocityBonus 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double MissileVelocityBonus;
    /**
     * current power need
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Power;
    /**
     * Attribute ID of the resistance type v's this Ewar module.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RemoteResistanceID;
    /**
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1;
    /**
     * Required skill level for skill 1
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1Level;
    /**
     * +/- modifier to the gravimetric strength of an electronic system.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double ScanGravimetricStrengthBonus;
    /**
     * +/- modifier to the ladar strength of an electronic system.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double ScanLadarStrengthBonus;
    /**
     * +/- modifier to the magnetometric strength of an electronic system.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double ScanMagnetometricStrengthBonus;
    /**
     * +/- modifier to the radar strength of an electronic system.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double ScanRadarStrengthBonus;
    /**
     * Bonus for scan resolution
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double ScanResolutionBonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int SiegeModeWarpStatus;
    /**
     * Autogenerated skill attribute, signatureRadiusBonus
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double SignatureRadiusBonus;
    /**
     * Factor by which topspeed increases.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(1.0)
    public double SpeedFactor;
    /**
     * Authoring has been moved to FSD
     * Tech level of an item
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int TechLevel;
    /**
     * Tracking Speed Bonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double TrackingSpeedBonus;
    public final static BurstProjectors.MetaGroup METAGROUP = new BurstProjectors.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  848 :
            {
                return AoeCloudSizeBonus;
            }
            case  847 :
            {
                return AoeVelocityBonus;
            }
            case  1163 :
            {
                return CanCloak;
            }
            case  1298 :
            {
                return CanFitShipGroup01;
            }
            case  6 :
            {
                return CapacitorNeed;
            }
            case  714 :
            {
                return ConsumptionQuantity;
            }
            case  713 :
            {
                return ConsumptionType;
            }
            case  50 :
            {
                return Cpu;
            }
            case  2354 :
            {
                return DisallowDocking;
            }
            case  906 :
            {
                return DisallowEarlyDeactivation;
            }
            case  1074 :
            {
                return DisallowInEmpireSpace;
            }
            case  1014 :
            {
                return DisallowRepeatingActivation;
            }
            case  2280 :
            {
                return DoomsdayAOEDuration;
            }
            case  2279 :
            {
                return DoomsdayAOERange;
            }
            case  2429 :
            {
                return DoomsdayAOEShape;
            }
            case  2281 :
            {
                return DoomsdayAOESignatureRadius;
            }
            case  2430 :
            {
                return DoomsdayRangeIsFixed;
            }
            case  2262 :
            {
                return DoomsdayWarningDuration;
            }
            case  73 :
            {
                return Duration;
            }
            case  2398 :
            {
                return DurationECMJammerBurstProjector;
            }
            case  2399 :
            {
                return DurationSensorDampeningBurstProjector;
            }
            case  2400 :
            {
                return DurationTargetIlluminationBurstProjector;
            }
            case  2397 :
            {
                return DurationWeaponDisruptionBurstProjector;
            }
            case  97 :
            {
                return EnergyNeutralizerAmount;
            }
            case  596 :
            {
                return ExplosionDelayBonus;
            }
            case  349 :
            {
                return FalloffBonus;
            }
            case  2269 :
            {
                return IsPointTargeted;
            }
            case  763 :
            {
                return MaxGroupActive;
            }
            case  1544 :
            {
                return MaxGroupFitted;
            }
            case  54 :
            {
                return MaxRange;
            }
            case  351 :
            {
                return MaxRangeBonus;
            }
            case  309 :
            {
                return MaxTargetRangeBonus;
            }
            case  633 :
            {
                return MetaLevel;
            }
            case  547 :
            {
                return MissileVelocityBonus;
            }
            case  30 :
            {
                return Power;
            }
            case  2138 :
            {
                return RemoteResistanceID;
            }
            case  182 :
            {
                return RequiredSkill1;
            }
            case  277 :
            {
                return RequiredSkill1Level;
            }
            case  238 :
            {
                return ScanGravimetricStrengthBonus;
            }
            case  239 :
            {
                return ScanLadarStrengthBonus;
            }
            case  240 :
            {
                return ScanMagnetometricStrengthBonus;
            }
            case  241 :
            {
                return ScanRadarStrengthBonus;
            }
            case  566 :
            {
                return ScanResolutionBonus;
            }
            case  852 :
            {
                return SiegeModeWarpStatus;
            }
            case  554 :
            {
                return SignatureRadiusBonus;
            }
            case  20 :
            {
                return SpeedFactor;
            }
            case  422 :
            {
                return TechLevel;
            }
            case  767 :
            {
                return TrackingSpeedBonus;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public IMetaGroup<BurstProjectors> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<BurstProjectors>
    {
        public final static String RESOURCE_PATH = "SDE/items/module/BurstProjectors.yaml";
        private Map<String, BurstProjectors> cache = (null);

        @Override
        public IMetaCategory<? super BurstProjectors> category() {
            return Module.METACAT;
        }

        @Override
        public int getGroupId() {
            return  842;
        }

        @Override
        public String getName() {
            return "BurstProjectors";
        }

        @Override
        public synchronized Map<String, BurstProjectors> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(BurstProjectors.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, BurstProjectors> items;
        }
    }
}
