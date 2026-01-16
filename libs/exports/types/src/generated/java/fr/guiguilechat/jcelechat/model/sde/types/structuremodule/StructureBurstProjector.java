package fr.guiguilechat.jcelechat.model.sde.types.structuremodule;

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
import fr.guiguilechat.jcelechat.model.sde.attributes.AoeCloudSizeBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.AoeVelocityBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipGroup01;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipGroup02;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipGroup03;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipType1;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipType10;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipType11;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipType2;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipType3;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipType4;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipType5;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipType6;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipType7;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipType8;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanFitShipType9;
import fr.guiguilechat.jcelechat.model.sde.attributes.CapacitorNeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.Cpu;
import fr.guiguilechat.jcelechat.model.sde.attributes.DisallowEarlyDeactivation;
import fr.guiguilechat.jcelechat.model.sde.attributes.DisallowInEmpireSpace;
import fr.guiguilechat.jcelechat.model.sde.attributes.DisallowInHighSec;
import fr.guiguilechat.jcelechat.model.sde.attributes.DisallowRepeatingActivation;
import fr.guiguilechat.jcelechat.model.sde.attributes.DoomsdayAOEDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.DoomsdayAOERange;
import fr.guiguilechat.jcelechat.model.sde.attributes.DoomsdayAOEShape;
import fr.guiguilechat.jcelechat.model.sde.attributes.DoomsdayAOESignatureRadius;
import fr.guiguilechat.jcelechat.model.sde.attributes.DoomsdayRangeIsFixed;
import fr.guiguilechat.jcelechat.model.sde.attributes.DoomsdayWarningDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.Duration;
import fr.guiguilechat.jcelechat.model.sde.attributes.DurationECMJammerBurstProjector;
import fr.guiguilechat.jcelechat.model.sde.attributes.DurationSensorDampeningBurstProjector;
import fr.guiguilechat.jcelechat.model.sde.attributes.DurationTargetIlluminationBurstProjector;
import fr.guiguilechat.jcelechat.model.sde.attributes.DurationTargetWarpableBeacon;
import fr.guiguilechat.jcelechat.model.sde.attributes.DurationWeaponDisruptionBurstProjector;
import fr.guiguilechat.jcelechat.model.sde.attributes.EnergyNeutralizerAmount;
import fr.guiguilechat.jcelechat.model.sde.attributes.ExplosionDelayBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.FalloffBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hp;
import fr.guiguilechat.jcelechat.model.sde.attributes.IsPointTargeted;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxRangeBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxTargetRangeBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxTypeFitted;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaLevelOld;
import fr.guiguilechat.jcelechat.model.sde.attributes.MissileVelocityBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.OnlineMaxSecurityClass;
import fr.guiguilechat.jcelechat.model.sde.attributes.Power;
import fr.guiguilechat.jcelechat.model.sde.attributes.RemoteResistanceID;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanGravimetricStrengthBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanLadarStrengthBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanMagnetometricStrengthBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanRadarStrengthBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanResolutionBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.SignatureRadiusBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.SpeedFactor;
import fr.guiguilechat.jcelechat.model.sde.attributes.StructureItemVisualFlag;
import fr.guiguilechat.jcelechat.model.sde.attributes.TechLevel;
import fr.guiguilechat.jcelechat.model.sde.attributes.TrackingSpeedBonus;
import fr.guiguilechat.jcelechat.model.sde.types.StructureModule;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class StructureBurstProjector
    extends StructureModule
{
    /**
     * Booster attribute to explosion radius of missiles vs. signature radius.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double aoecloudsizebonus;
    /**
     * Increases velocity of missile explosion
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double aoevelocitybonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshipgroup01;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshipgroup02;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshipgroup03;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshiptype1;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshiptype10;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshiptype11;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshiptype2;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshiptype3;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshiptype4;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshiptype5;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshiptype6;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshiptype7;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshiptype8;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int canfitshiptype9;
    /**
     * The amount of charge used from the capacitor for a module activation.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double capacitorneed;
    /**
     * CPU need of module
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double cpu;
    /**
     * Signifies that this module if activated, will prevent ejection from the ship it is fitted to and extend the log out ship removal timer.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int disallowearlydeactivation;
    /**
     * If set on a charge or module type, will prevent it from being activated in empire space.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int disallowinempirespace;
    /**
     * Security status restriction, preventing ships from entering high sec and modules from being activated.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int disallowinhighsec;
    /**
     * If set, this module cannot be activated and made to autorepeat.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int disallowrepeatingactivation;
    /**
     * Duration of the AOE Effect
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int doomsdayaoeduration;
    /**
     * Radius of the AOE Effect
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int doomsdayaoerange;
    /**
     *  1: Fixed Cylinder (Beam)
     *  2: Cylinder moving in an arc (Slash)
     *  3: Fixed Cone
     *  4: Projected Sphere
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int doomsdayaoeshape;
    /**
     * Signature Radius of the AOE Effect
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int doomsdayaoesignatureradius;
    /**
     * Determines whether the maxRange attribute is a fixed length or a maximum length of the effect
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int doomsdayrangeisfixed;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int doomsdaywarningduration;
    /**
     * Length of activation time.
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double duration;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int durationecmjammerburstprojector;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int durationsensordampeningburstprojector;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int durationtargetilluminationburstprojector;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int durationtargetwarpablebeacon;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int durationweapondisruptionburstprojector;
    /**
     * An amount to modify the power of the target by.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double energyneutralizeramount;
    /**
     * Autogenerated skill attribute, explosionDelayBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double explosiondelaybonus;
    /**
     * Autogenerated skill attribute, falloffBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double falloffbonus;
    /**
     * The maximum hitpoints of an object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double hp;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int ispointtargeted;
    /**
     * Distance below which range does not affect the to-hit equation.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double maxrange;
    /**
     * Autogenerated skill attribute, maxRangeBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double maxrangebonus;
    /**
     * Bonus to Max Targeting Range
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double maxtargetrangebonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxtypefitted;
    /**
     * Authoring has been moved to FSD
     * The ranking of the module within its tech level
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int metalevelold;
    /**
     * Autogenerated skill attribute, missileVelocityBonus 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double missilevelocitybonus;
    /**
     * Determines the maximum security class that a module can be onlined within. Used for structure modules.
     * 
     *  0=Nullsec
     *  1=Lowsec
     *  2=Highsec
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(2)
    public int onlinemaxsecurityclass;
    /**
     * current power need
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int power;
    /**
     * Attribute ID of the resistance type v's this Ewar module.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int remoteresistanceid;
    /**
     * +/- modifier to the gravimetric strength of an electronic system.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double scangravimetricstrengthbonus;
    /**
     * +/- modifier to the ladar strength of an electronic system.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double scanladarstrengthbonus;
    /**
     * +/- modifier to the magnetometric strength of an electronic system.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double scanmagnetometricstrengthbonus;
    /**
     * +/- modifier to the radar strength of an electronic system.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double scanradarstrengthbonus;
    /**
     * Bonus for scan resolution
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double scanresolutionbonus;
    /**
     * Autogenerated skill attribute, signatureRadiusBonus
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double signatureradiusbonus;
    /**
     * Factor by which topspeed increases.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double speedfactor;
    /**
     * Dogma attribute that specifies if the item should have the structure icon or not.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int structureitemvisualflag;
    /**
     * Authoring has been moved to FSD
     * Tech level of an item
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int techlevel;
    /**
     * Tracking Speed Bonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double trackingspeedbonus;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {CapacitorNeed.INSTANCE, Hp.INSTANCE, DisallowEarlyDeactivation.INSTANCE, CanFitShipGroup01 .INSTANCE, CanFitShipGroup02 .INSTANCE, CanFitShipGroup03 .INSTANCE, SpeedFactor.INSTANCE, OnlineMaxSecurityClass.INSTANCE, CanFitShipType1 .INSTANCE, CanFitShipType2 .INSTANCE, CanFitShipType3 .INSTANCE, CanFitShipType5 .INSTANCE, CanFitShipType4 .INSTANCE, Power.INSTANCE, StructureItemVisualFlag.INSTANCE, CanFitShipType7 .INSTANCE, MissileVelocityBonus.INSTANCE, TechLevel.INSTANCE, SignatureRadiusBonus.INSTANCE, Cpu.INSTANCE, DisallowInHighSec.INSTANCE, DisallowInEmpireSpace.INSTANCE, MaxTargetRangeBonus.INSTANCE, MaxRange.INSTANCE, CanFitShipType8 .INSTANCE, ScanResolutionBonus.INSTANCE, CanFitShipType6 .INSTANCE, CanFitShipType9 .INSTANCE, CanFitShipType10 .INSTANCE, DurationTargetWarpableBeacon.INSTANCE, CanFitShipType11 .INSTANCE, Duration.INSTANCE, AoeVelocityBonus.INSTANCE, AoeCloudSizeBonus.INSTANCE, ExplosionDelayBonus.INSTANCE, DoomsdayWarningDuration.INSTANCE, RemoteResistanceID.INSTANCE, IsPointTargeted.INSTANCE, FalloffBonus.INSTANCE, DurationWeaponDisruptionBurstProjector.INSTANCE, DurationECMJammerBurstProjector.INSTANCE, MaxRangeBonus.INSTANCE, DurationSensorDampeningBurstProjector.INSTANCE, DurationTargetIlluminationBurstProjector.INSTANCE, EnergyNeutralizerAmount.INSTANCE, DoomsdayAOERange.INSTANCE, DoomsdayAOEDuration.INSTANCE, DoomsdayAOESignatureRadius.INSTANCE, ScanGravimetricStrengthBonus.INSTANCE, ScanLadarStrengthBonus.INSTANCE, ScanMagnetometricStrengthBonus.INSTANCE, ScanRadarStrengthBonus.INSTANCE, DisallowRepeatingActivation.INSTANCE, MetaLevelOld.INSTANCE, DoomsdayAOEShape.INSTANCE, DoomsdayRangeIsFixed.INSTANCE, MaxTypeFitted.INSTANCE, TrackingSpeedBonus.INSTANCE })));
    public static final StructureBurstProjector.MetaGroup METAGROUP = new StructureBurstProjector.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  848 :
            {
                return aoecloudsizebonus;
            }
            case  847 :
            {
                return aoevelocitybonus;
            }
            case  1298 :
            {
                return canfitshipgroup01;
            }
            case  1299 :
            {
                return canfitshipgroup02;
            }
            case  1300 :
            {
                return canfitshipgroup03;
            }
            case  1302 :
            {
                return canfitshiptype1;
            }
            case  2488 :
            {
                return canfitshiptype10;
            }
            case  2758 :
            {
                return canfitshiptype11;
            }
            case  1303 :
            {
                return canfitshiptype2;
            }
            case  1304 :
            {
                return canfitshiptype3;
            }
            case  1305 :
            {
                return canfitshiptype4;
            }
            case  1944 :
            {
                return canfitshiptype5;
            }
            case  2103 :
            {
                return canfitshiptype6;
            }
            case  2463 :
            {
                return canfitshiptype7;
            }
            case  2486 :
            {
                return canfitshiptype8;
            }
            case  2487 :
            {
                return canfitshiptype9;
            }
            case  6 :
            {
                return capacitorneed;
            }
            case  50 :
            {
                return cpu;
            }
            case  906 :
            {
                return disallowearlydeactivation;
            }
            case  1074 :
            {
                return disallowinempirespace;
            }
            case  1970 :
            {
                return disallowinhighsec;
            }
            case  1014 :
            {
                return disallowrepeatingactivation;
            }
            case  2280 :
            {
                return doomsdayaoeduration;
            }
            case  2279 :
            {
                return doomsdayaoerange;
            }
            case  2429 :
            {
                return doomsdayaoeshape;
            }
            case  2281 :
            {
                return doomsdayaoesignatureradius;
            }
            case  2430 :
            {
                return doomsdayrangeisfixed;
            }
            case  2262 :
            {
                return doomsdaywarningduration;
            }
            case  73 :
            {
                return duration;
            }
            case  2398 :
            {
                return durationecmjammerburstprojector;
            }
            case  2399 :
            {
                return durationsensordampeningburstprojector;
            }
            case  2400 :
            {
                return durationtargetilluminationburstprojector;
            }
            case  2745 :
            {
                return durationtargetwarpablebeacon;
            }
            case  2397 :
            {
                return durationweapondisruptionburstprojector;
            }
            case  97 :
            {
                return energyneutralizeramount;
            }
            case  596 :
            {
                return explosiondelaybonus;
            }
            case  349 :
            {
                return falloffbonus;
            }
            case  9 :
            {
                return hp;
            }
            case  2269 :
            {
                return ispointtargeted;
            }
            case  54 :
            {
                return maxrange;
            }
            case  351 :
            {
                return maxrangebonus;
            }
            case  309 :
            {
                return maxtargetrangebonus;
            }
            case  2431 :
            {
                return maxtypefitted;
            }
            case  633 :
            {
                return metalevelold;
            }
            case  547 :
            {
                return missilevelocitybonus;
            }
            case  2581 :
            {
                return onlinemaxsecurityclass;
            }
            case  30 :
            {
                return power;
            }
            case  2138 :
            {
                return remoteresistanceid;
            }
            case  238 :
            {
                return scangravimetricstrengthbonus;
            }
            case  239 :
            {
                return scanladarstrengthbonus;
            }
            case  240 :
            {
                return scanmagnetometricstrengthbonus;
            }
            case  241 :
            {
                return scanradarstrengthbonus;
            }
            case  566 :
            {
                return scanresolutionbonus;
            }
            case  554 :
            {
                return signatureradiusbonus;
            }
            case  20 :
            {
                return speedfactor;
            }
            case  2334 :
            {
                return structureitemvisualflag;
            }
            case  422 :
            {
                return techlevel;
            }
            case  767 :
            {
                return trackingspeedbonus;
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
    public IMetaGroup<StructureBurstProjector> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<StructureBurstProjector>
    {
        public static final String RESOURCE_PATH = "SDE/types/structuremodule/StructureBurstProjector.yaml";
        private Map<Integer, StructureBurstProjector> cache = (null);

        @Override
        public IMetaCategory<? super StructureBurstProjector> category() {
            return StructureModule.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1331;
        }

        @Override
        public String getName() {
            return "StructureBurstProjector";
        }

        @Override
        public synchronized Map<Integer, StructureBurstProjector> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(StructureBurstProjector.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, StructureBurstProjector> types;
        }
    }
}
