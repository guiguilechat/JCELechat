package fr.guiguilechat.jcelechat.model.sde.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultRealValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hp;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaLevelOld;
import fr.guiguilechat.jcelechat.model.sde.attributes.TechLevel;
import fr.guiguilechat.jcelechat.model.sde.types.module.*;

public abstract class Module
    extends EveType
{
    /**
     * The maximum hitpoints of an object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double hp;
    /**
     * Authoring has been moved to FSD
     * The ranking of the module within its tech level
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int metalevelold;
    /**
     * Authoring has been moved to FSD
     * Tech level of an item
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int techlevel;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {Hp.INSTANCE, TechLevel.INSTANCE, MetaLevelOld.INSTANCE })));
    public static final Module.MetaCat METACAT = new Module.MetaCat();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  9 :
            {
                return hp;
            }
            case  633 :
            {
                return metalevelold;
            }
            case  422 :
            {
                return techlevel;
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
    public IMetaCategory<Module> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<Module>
    {

        @Override
        public int getCategoryId() {
            return  7;
        }

        @Override
        public String getName() {
            return "Module";
        }

        @Override
        public Collection<IMetaGroup<? extends Module>> groups() {
            return Arrays.asList(InterdictionNullifier.METAGROUP, CovertJumpPortalGenerator.METAGROUP, ShieldExtender.METAGROUP, ShieldRecharger.METAGROUP, ShieldBooster.METAGROUP, RemoteShieldBooster.METAGROUP, GasCloudHarvesters.METAGROUP, CapacitorRecharger.METAGROUP, PropulsionModule.METAGROUP, CargoScanner.METAGROUP, ShipScanner.METAGROUP, MiningSurveyChipset.METAGROUP, WarpScrambler.METAGROUP, EnergyWeapon.METAGROUP, MiningLaser.METAGROUP, ProjectileWeapon.METAGROUP, ShieldPowerRelay.METAGROUP, Gyrostabilizer.METAGROUP, DamageControl.METAGROUP, CapacitorBattery.METAGROUP, ArmorRepairUnit.METAGROUP, HullRepairUnit.METAGROUP, StasisWeb.METAGROUP, RemoteCapacitorTransmitter.METAGROUP, EnergyNosferatu.METAGROUP, EnergyNeutralizer.METAGROUP, SmartBomb.METAGROUP, HybridWeapon.METAGROUP, CapacitorBooster.METAGROUP, ShieldHardener.METAGROUP, ReinforcedBulkhead.METAGROUP, Compressors.METAGROUP, BurstJammer.METAGROUP, PassiveTargetingSystem.METAGROUP, IndustrialJumpPortalGenerator.METAGROUP, AutomatedTargetingSystem.METAGROUP, ArmorCoating.METAGROUP, ECM.METAGROUP, HeatSink.METAGROUP, SensorDampener.METAGROUP, RemoteTrackingComputer.METAGROUP, SignalAmplifier.METAGROUP, TrackingEnhancer.METAGROUP, SensorBooster.METAGROUP, TrackingComputer.METAGROUP, CPUEnhancer.METAGROUP, RemoteSensorBooster.METAGROUP, WeaponDisruptor.METAGROUP, ShieldResistanceAmplifier.METAGROUP, MagneticFieldStabilizer.METAGROUP, WarpCoreStabilizer.METAGROUP, RemoteArmorRepairer.METAGROUP, EnergizedArmorMembrane.METAGROUP, ArmorHardener.METAGROUP, ArmorPlate.METAGROUP, CloakingDevice.METAGROUP, ShieldBoostAmplifier.METAGROUP, AuxiliaryPowerCore.METAGROUP, BallisticControlSystem.METAGROUP, TargetPainter.METAGROUP, FighterSupportUnit.METAGROUP, StripMiner.METAGROUP, ScanProbeLauncher.METAGROUP, FrequencyMiningLaser.METAGROUP, FestivalLauncher.METAGROUP, MissileLauncherCruise.METAGROUP, MissileLauncherRocket.METAGROUP, MissileLauncherTorpedo.METAGROUP, MissileLauncherLight.METAGROUP, MissileLauncherHeavy.METAGROUP, MissileLauncherRapidLight.METAGROUP, MissileLauncherDefender.METAGROUP, ECMStabilizer.METAGROUP, SiegeModule.METAGROUP, MissileLauncherXLTorpedo.METAGROUP, DataMiners.METAGROUP, MiningUpgrade.METAGROUP, RemoteHullRepairer.METAGROUP, SuperWeapon.METAGROUP, InterdictionSphereLauncher.METAGROUP, JumpPortalGenerator.METAGROUP, DroneNavigationComputer.METAGROUP, DroneDamageModules.METAGROUP, DroneTrackingModules.METAGROUP, DroneControlRangeModule.METAGROUP, TractorBeam.METAGROUP, CynosuralFieldGenerator.METAGROUP, CapitalMobilityModules.METAGROUP, BreacherPodLaunchers.METAGROUP, GasCloudScoops.METAGROUP, InertialStabilizer.METAGROUP, NanofiberInternalStructure.METAGROUP, OverdriveInjectorSystem.METAGROUP, ExpandedCargohold.METAGROUP, PowerDiagnosticSystem.METAGROUP, CapacitorPowerRelay.METAGROUP, CapacitorFluxCoil.METAGROUP, ReactorControlUnit.METAGROUP, ShieldFluxCoil.METAGROUP, MissileLauncherHeavyAssault.METAGROUP, RigArmor.METAGROUP, RigShield.METAGROUP, RigEnergyWeapon.METAGROUP, RigHybridWeapon.METAGROUP, RigProjectileWeapon.METAGROUP, RigDrones.METAGROUP, RigLauncher.METAGROUP, RigCore.METAGROUP, RigNavigation.METAGROUP, RigElectronicSystems.METAGROUP, CloneVatBay.METAGROUP, BurstProjectors.METAGROUP, MissileLauncherBomb.METAGROUP, WarpDisruptFieldGenerator.METAGROUP, Salvager.METAGROUP, ArmorResistanceShiftHardener.METAGROUP, SignatureSuppressor.METAGROUP, AncillaryShieldBooster.METAGROUP, MicroJumpDrive.METAGROUP, AncillaryArmorRepairer.METAGROUP, ScanningUpgrade.METAGROUP, SurveyProbeLauncher.METAGROUP, RigResourceProcessing.METAGROUP, RigScanning.METAGROUP, RigTargeting.METAGROUP, ScanningUpgradeTime.METAGROUP, MissileLauncherRapidHeavy.METAGROUP, WarpAccelerator.METAGROUP, DroneTrackingEnhancer.METAGROUP, JumpDriveEconomizer.METAGROUP, RigAnchor.METAGROUP, EntosisLink.METAGROUP, MissileGuidanceEnhancer.METAGROUP, MissileGuidanceComputer.METAGROUP, MicroJumpFieldGenerators.METAGROUP, StasisGrappler.METAGROUP, MissileLauncherRapidTorpedo.METAGROUP, MissileLauncherXLCruise.METAGROUP, AncillaryRemoteShieldBooster.METAGROUP, AncillaryRemoteArmorRepairer.METAGROUP, FlexArmorHardener.METAGROUP, FlexShieldHardener.METAGROUP, CapitalSensorArray.METAGROUP, CommandBurst.METAGROUP, TitanPhenomenaGenerator.METAGROUP, PrecursorWeapon.METAGROUP, EntropicRadiationSink.METAGROUP, MassEntanglers.METAGROUP, MutadaptiveRemoteArmorRepairer.METAGROUP, VortonProjector.METAGROUP, VortonProjectorUpgrade.METAGROUP);
        }
    }
}
