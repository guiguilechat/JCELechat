package fr.guiguilechat.jcelechat.model.sde.types;

import java.util.Arrays;
import java.util.Collection;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.types.module.AncillaryArmorRepairer;
import fr.guiguilechat.jcelechat.model.sde.types.module.AncillaryRemoteArmorRepairer;
import fr.guiguilechat.jcelechat.model.sde.types.module.AncillaryRemoteShieldBooster;
import fr.guiguilechat.jcelechat.model.sde.types.module.AncillaryShieldBooster;
import fr.guiguilechat.jcelechat.model.sde.types.module.ArmorCoating;
import fr.guiguilechat.jcelechat.model.sde.types.module.ArmorHardener;
import fr.guiguilechat.jcelechat.model.sde.types.module.ArmorPlatingEnergized;
import fr.guiguilechat.jcelechat.model.sde.types.module.ArmorReinforcer;
import fr.guiguilechat.jcelechat.model.sde.types.module.ArmorRepairUnit;
import fr.guiguilechat.jcelechat.model.sde.types.module.ArmorResistanceShiftHardener;
import fr.guiguilechat.jcelechat.model.sde.types.module.AutomatedTargetingSystem;
import fr.guiguilechat.jcelechat.model.sde.types.module.AuxiliaryPowerCore;
import fr.guiguilechat.jcelechat.model.sde.types.module.BallisticControlSystem;
import fr.guiguilechat.jcelechat.model.sde.types.module.BurstJammer;
import fr.guiguilechat.jcelechat.model.sde.types.module.BurstProjectors;
import fr.guiguilechat.jcelechat.model.sde.types.module.CPUEnhancer;
import fr.guiguilechat.jcelechat.model.sde.types.module.CapacitorBattery;
import fr.guiguilechat.jcelechat.model.sde.types.module.CapacitorBooster;
import fr.guiguilechat.jcelechat.model.sde.types.module.CapacitorFluxCoil;
import fr.guiguilechat.jcelechat.model.sde.types.module.CapacitorPowerRelay;
import fr.guiguilechat.jcelechat.model.sde.types.module.CapacitorRecharger;
import fr.guiguilechat.jcelechat.model.sde.types.module.CapitalSensorArray;
import fr.guiguilechat.jcelechat.model.sde.types.module.CargoScanner;
import fr.guiguilechat.jcelechat.model.sde.types.module.CloakingDevice;
import fr.guiguilechat.jcelechat.model.sde.types.module.CloneVatBay;
import fr.guiguilechat.jcelechat.model.sde.types.module.CommandBurst;
import fr.guiguilechat.jcelechat.model.sde.types.module.CynosuralFieldGenerator;
import fr.guiguilechat.jcelechat.model.sde.types.module.DamageControl;
import fr.guiguilechat.jcelechat.model.sde.types.module.DataMiners;
import fr.guiguilechat.jcelechat.model.sde.types.module.DroneControlRangeModule;
import fr.guiguilechat.jcelechat.model.sde.types.module.DroneDamageModules;
import fr.guiguilechat.jcelechat.model.sde.types.module.DroneNavigationComputer;
import fr.guiguilechat.jcelechat.model.sde.types.module.DroneTrackingEnhancer;
import fr.guiguilechat.jcelechat.model.sde.types.module.DroneTrackingModules;
import fr.guiguilechat.jcelechat.model.sde.types.module.ECM;
import fr.guiguilechat.jcelechat.model.sde.types.module.ECMStabilizer;
import fr.guiguilechat.jcelechat.model.sde.types.module.EnergyNeutralizer;
import fr.guiguilechat.jcelechat.model.sde.types.module.EnergyNosferatu;
import fr.guiguilechat.jcelechat.model.sde.types.module.EnergyWeapon;
import fr.guiguilechat.jcelechat.model.sde.types.module.EntosisLink;
import fr.guiguilechat.jcelechat.model.sde.types.module.EntropicRadiationSink;
import fr.guiguilechat.jcelechat.model.sde.types.module.ExpandedCargohold;
import fr.guiguilechat.jcelechat.model.sde.types.module.FestivalLauncher;
import fr.guiguilechat.jcelechat.model.sde.types.module.FighterSupportUnit;
import fr.guiguilechat.jcelechat.model.sde.types.module.FlexArmorHardener;
import fr.guiguilechat.jcelechat.model.sde.types.module.FlexShieldHardener;
import fr.guiguilechat.jcelechat.model.sde.types.module.FrequencyMiningLaser;
import fr.guiguilechat.jcelechat.model.sde.types.module.GasCloudHarvester;
import fr.guiguilechat.jcelechat.model.sde.types.module.Gyrostabilizer;
import fr.guiguilechat.jcelechat.model.sde.types.module.HeatSink;
import fr.guiguilechat.jcelechat.model.sde.types.module.HullRepairUnit;
import fr.guiguilechat.jcelechat.model.sde.types.module.HybridWeapon;
import fr.guiguilechat.jcelechat.model.sde.types.module.InertialStabilizer;
import fr.guiguilechat.jcelechat.model.sde.types.module.InterdictionSphereLauncher;
import fr.guiguilechat.jcelechat.model.sde.types.module.JumpDriveEconomizer;
import fr.guiguilechat.jcelechat.model.sde.types.module.JumpPortalGenerator;
import fr.guiguilechat.jcelechat.model.sde.types.module.MagneticFieldStabilizer;
import fr.guiguilechat.jcelechat.model.sde.types.module.MassEntanglers;
import fr.guiguilechat.jcelechat.model.sde.types.module.MicroJumpDrive;
import fr.guiguilechat.jcelechat.model.sde.types.module.MicroJumpFieldGenerators;
import fr.guiguilechat.jcelechat.model.sde.types.module.MiningLaser;
import fr.guiguilechat.jcelechat.model.sde.types.module.MiningUpgrade;
import fr.guiguilechat.jcelechat.model.sde.types.module.MissileGuidanceComputer;
import fr.guiguilechat.jcelechat.model.sde.types.module.MissileGuidanceEnhancer;
import fr.guiguilechat.jcelechat.model.sde.types.module.MissileLauncherBomb;
import fr.guiguilechat.jcelechat.model.sde.types.module.MissileLauncherCruise;
import fr.guiguilechat.jcelechat.model.sde.types.module.MissileLauncherDefender;
import fr.guiguilechat.jcelechat.model.sde.types.module.MissileLauncherHeavy;
import fr.guiguilechat.jcelechat.model.sde.types.module.MissileLauncherHeavyAssault;
import fr.guiguilechat.jcelechat.model.sde.types.module.MissileLauncherLight;
import fr.guiguilechat.jcelechat.model.sde.types.module.MissileLauncherRapidHeavy;
import fr.guiguilechat.jcelechat.model.sde.types.module.MissileLauncherRapidLight;
import fr.guiguilechat.jcelechat.model.sde.types.module.MissileLauncherRapidTorpedo;
import fr.guiguilechat.jcelechat.model.sde.types.module.MissileLauncherRocket;
import fr.guiguilechat.jcelechat.model.sde.types.module.MissileLauncherTorpedo;
import fr.guiguilechat.jcelechat.model.sde.types.module.MissileLauncherXLCruise;
import fr.guiguilechat.jcelechat.model.sde.types.module.MissileLauncherXLTorpedo;
import fr.guiguilechat.jcelechat.model.sde.types.module.MutadaptiveRemoteArmorRepairer;
import fr.guiguilechat.jcelechat.model.sde.types.module.NanofiberInternalStructure;
import fr.guiguilechat.jcelechat.model.sde.types.module.OverdriveInjectorSystem;
import fr.guiguilechat.jcelechat.model.sde.types.module.PassiveTargetingSystem;
import fr.guiguilechat.jcelechat.model.sde.types.module.PowerDiagnosticSystem;
import fr.guiguilechat.jcelechat.model.sde.types.module.PrecursorWeapon;
import fr.guiguilechat.jcelechat.model.sde.types.module.ProjectileWeapon;
import fr.guiguilechat.jcelechat.model.sde.types.module.PropulsionModule;
import fr.guiguilechat.jcelechat.model.sde.types.module.ReactorControlUnit;
import fr.guiguilechat.jcelechat.model.sde.types.module.ReinforcedBulkhead;
import fr.guiguilechat.jcelechat.model.sde.types.module.RemoteArmorRepairer;
import fr.guiguilechat.jcelechat.model.sde.types.module.RemoteCapacitorTransmitter;
import fr.guiguilechat.jcelechat.model.sde.types.module.RemoteHullRepairer;
import fr.guiguilechat.jcelechat.model.sde.types.module.RemoteSensorBooster;
import fr.guiguilechat.jcelechat.model.sde.types.module.RemoteShieldBooster;
import fr.guiguilechat.jcelechat.model.sde.types.module.RemoteTrackingComputer;
import fr.guiguilechat.jcelechat.model.sde.types.module.RigAnchor;
import fr.guiguilechat.jcelechat.model.sde.types.module.RigArmor;
import fr.guiguilechat.jcelechat.model.sde.types.module.RigCore;
import fr.guiguilechat.jcelechat.model.sde.types.module.RigDrones;
import fr.guiguilechat.jcelechat.model.sde.types.module.RigElectronicSystems;
import fr.guiguilechat.jcelechat.model.sde.types.module.RigEnergyWeapon;
import fr.guiguilechat.jcelechat.model.sde.types.module.RigHybridWeapon;
import fr.guiguilechat.jcelechat.model.sde.types.module.RigLauncher;
import fr.guiguilechat.jcelechat.model.sde.types.module.RigNavigation;
import fr.guiguilechat.jcelechat.model.sde.types.module.RigProjectileWeapon;
import fr.guiguilechat.jcelechat.model.sde.types.module.RigResourceProcessing;
import fr.guiguilechat.jcelechat.model.sde.types.module.RigScanning;
import fr.guiguilechat.jcelechat.model.sde.types.module.RigShield;
import fr.guiguilechat.jcelechat.model.sde.types.module.RigTargeting;
import fr.guiguilechat.jcelechat.model.sde.types.module.Salvager;
import fr.guiguilechat.jcelechat.model.sde.types.module.ScanProbeLauncher;
import fr.guiguilechat.jcelechat.model.sde.types.module.ScanningUpgrade;
import fr.guiguilechat.jcelechat.model.sde.types.module.ScanningUpgradeTime;
import fr.guiguilechat.jcelechat.model.sde.types.module.SensorBooster;
import fr.guiguilechat.jcelechat.model.sde.types.module.SensorDampener;
import fr.guiguilechat.jcelechat.model.sde.types.module.ShieldBoostAmplifier;
import fr.guiguilechat.jcelechat.model.sde.types.module.ShieldBooster;
import fr.guiguilechat.jcelechat.model.sde.types.module.ShieldExtender;
import fr.guiguilechat.jcelechat.model.sde.types.module.ShieldFluxCoil;
import fr.guiguilechat.jcelechat.model.sde.types.module.ShieldHardener;
import fr.guiguilechat.jcelechat.model.sde.types.module.ShieldPowerRelay;
import fr.guiguilechat.jcelechat.model.sde.types.module.ShieldRecharger;
import fr.guiguilechat.jcelechat.model.sde.types.module.ShieldResistanceAmplifier;
import fr.guiguilechat.jcelechat.model.sde.types.module.ShipScanner;
import fr.guiguilechat.jcelechat.model.sde.types.module.SiegeModule;
import fr.guiguilechat.jcelechat.model.sde.types.module.SignalAmplifier;
import fr.guiguilechat.jcelechat.model.sde.types.module.SmartBomb;
import fr.guiguilechat.jcelechat.model.sde.types.module.StasisGrappler;
import fr.guiguilechat.jcelechat.model.sde.types.module.StasisNullifiers;
import fr.guiguilechat.jcelechat.model.sde.types.module.StasisWeb;
import fr.guiguilechat.jcelechat.model.sde.types.module.StripMiner;
import fr.guiguilechat.jcelechat.model.sde.types.module.SuperWeapon;
import fr.guiguilechat.jcelechat.model.sde.types.module.SurveyProbeLauncher;
import fr.guiguilechat.jcelechat.model.sde.types.module.SurveyScanner;
import fr.guiguilechat.jcelechat.model.sde.types.module.TargetBreaker;
import fr.guiguilechat.jcelechat.model.sde.types.module.TargetPainter;
import fr.guiguilechat.jcelechat.model.sde.types.module.TitanPhenomenaGenerator;
import fr.guiguilechat.jcelechat.model.sde.types.module.TrackingComputer;
import fr.guiguilechat.jcelechat.model.sde.types.module.TrackingEnhancer;
import fr.guiguilechat.jcelechat.model.sde.types.module.TractorBeam;
import fr.guiguilechat.jcelechat.model.sde.types.module.WarpAccelerator;
import fr.guiguilechat.jcelechat.model.sde.types.module.WarpCoreStabilizer;
import fr.guiguilechat.jcelechat.model.sde.types.module.WarpDisruptFieldGenerator;
import fr.guiguilechat.jcelechat.model.sde.types.module.WarpScrambler;
import fr.guiguilechat.jcelechat.model.sde.types.module.WeaponDisruptor;

public abstract class Module
    extends EveType
{
    /**
     * The cargo space allowed
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Capacity;
    /**
     * The maximum hitpoints of an object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Hp;
    /**
     * Integer that describes the types mass
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double Mass;
    /**
     * Radius of an object in meters
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Radius;
    public static final Module.MetaCat METACAT = new Module.MetaCat();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  38 :
            {
                return Capacity;
            }
            case  9 :
            {
                return Hp;
            }
            case  4 :
            {
                return Mass;
            }
            case  162 :
            {
                return Radius;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
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
            return Arrays.asList(ShieldExtender.METAGROUP, ShieldRecharger.METAGROUP, ShieldBooster.METAGROUP, RemoteShieldBooster.METAGROUP, CapacitorRecharger.METAGROUP, PropulsionModule.METAGROUP, CargoScanner.METAGROUP, ShipScanner.METAGROUP, SurveyScanner.METAGROUP, WarpScrambler.METAGROUP, EnergyWeapon.METAGROUP, MiningLaser.METAGROUP, ProjectileWeapon.METAGROUP, ShieldPowerRelay.METAGROUP, Gyrostabilizer.METAGROUP, DamageControl.METAGROUP, CapacitorBattery.METAGROUP, ArmorRepairUnit.METAGROUP, HullRepairUnit.METAGROUP, StasisWeb.METAGROUP, RemoteCapacitorTransmitter.METAGROUP, EnergyNosferatu.METAGROUP, EnergyNeutralizer.METAGROUP, SmartBomb.METAGROUP, HybridWeapon.METAGROUP, CapacitorBooster.METAGROUP, ShieldHardener.METAGROUP, ReinforcedBulkhead.METAGROUP, BurstJammer.METAGROUP, PassiveTargetingSystem.METAGROUP, AutomatedTargetingSystem.METAGROUP, ArmorCoating.METAGROUP, ECM.METAGROUP, HeatSink.METAGROUP, SensorDampener.METAGROUP, RemoteTrackingComputer.METAGROUP, SignalAmplifier.METAGROUP, TrackingEnhancer.METAGROUP, SensorBooster.METAGROUP, TrackingComputer.METAGROUP, CPUEnhancer.METAGROUP, RemoteSensorBooster.METAGROUP, WeaponDisruptor.METAGROUP, ShieldResistanceAmplifier.METAGROUP, MagneticFieldStabilizer.METAGROUP, WarpCoreStabilizer.METAGROUP, RemoteArmorRepairer.METAGROUP, ArmorPlatingEnergized.METAGROUP, ArmorHardener.METAGROUP, ArmorReinforcer.METAGROUP, CloakingDevice.METAGROUP, ShieldBoostAmplifier.METAGROUP, AuxiliaryPowerCore.METAGROUP, BallisticControlSystem.METAGROUP, TargetPainter.METAGROUP, FighterSupportUnit.METAGROUP, StripMiner.METAGROUP, ScanProbeLauncher.METAGROUP, FrequencyMiningLaser.METAGROUP, FestivalLauncher.METAGROUP, MissileLauncherCruise.METAGROUP, MissileLauncherRocket.METAGROUP, MissileLauncherTorpedo.METAGROUP, MissileLauncherLight.METAGROUP, MissileLauncherHeavy.METAGROUP, MissileLauncherRapidLight.METAGROUP, MissileLauncherDefender.METAGROUP, ECMStabilizer.METAGROUP, SiegeModule.METAGROUP, MissileLauncherXLTorpedo.METAGROUP, DataMiners.METAGROUP, MiningUpgrade.METAGROUP, RemoteHullRepairer.METAGROUP, SuperWeapon.METAGROUP, InterdictionSphereLauncher.METAGROUP, JumpPortalGenerator.METAGROUP, DroneNavigationComputer.METAGROUP, DroneDamageModules.METAGROUP, DroneTrackingModules.METAGROUP, DroneControlRangeModule.METAGROUP, TractorBeam.METAGROUP, CynosuralFieldGenerator.METAGROUP, GasCloudHarvester.METAGROUP, InertialStabilizer.METAGROUP, NanofiberInternalStructure.METAGROUP, OverdriveInjectorSystem.METAGROUP, ExpandedCargohold.METAGROUP, PowerDiagnosticSystem.METAGROUP, CapacitorPowerRelay.METAGROUP, CapacitorFluxCoil.METAGROUP, ReactorControlUnit.METAGROUP, ShieldFluxCoil.METAGROUP, MissileLauncherHeavyAssault.METAGROUP, RigArmor.METAGROUP, RigShield.METAGROUP, RigEnergyWeapon.METAGROUP, RigHybridWeapon.METAGROUP, RigProjectileWeapon.METAGROUP, RigDrones.METAGROUP, RigLauncher.METAGROUP, RigCore.METAGROUP, RigNavigation.METAGROUP, RigElectronicSystems.METAGROUP, CloneVatBay.METAGROUP, BurstProjectors.METAGROUP, MissileLauncherBomb.METAGROUP, WarpDisruptFieldGenerator.METAGROUP, Salvager.METAGROUP, ArmorResistanceShiftHardener.METAGROUP, TargetBreaker.METAGROUP, AncillaryShieldBooster.METAGROUP, MicroJumpDrive.METAGROUP, AncillaryArmorRepairer.METAGROUP, ScanningUpgrade.METAGROUP, SurveyProbeLauncher.METAGROUP, RigResourceProcessing.METAGROUP, RigScanning.METAGROUP, RigTargeting.METAGROUP, ScanningUpgradeTime.METAGROUP, MissileLauncherRapidHeavy.METAGROUP, WarpAccelerator.METAGROUP, DroneTrackingEnhancer.METAGROUP, JumpDriveEconomizer.METAGROUP, RigAnchor.METAGROUP, EntosisLink.METAGROUP, MissileGuidanceEnhancer.METAGROUP, MissileGuidanceComputer.METAGROUP, MicroJumpFieldGenerators.METAGROUP, StasisGrappler.METAGROUP, MissileLauncherRapidTorpedo.METAGROUP, MissileLauncherXLCruise.METAGROUP, AncillaryRemoteShieldBooster.METAGROUP, AncillaryRemoteArmorRepairer.METAGROUP, FlexArmorHardener.METAGROUP, FlexShieldHardener.METAGROUP, CapitalSensorArray.METAGROUP, CommandBurst.METAGROUP, TitanPhenomenaGenerator.METAGROUP, PrecursorWeapon.METAGROUP, EntropicRadiationSink.METAGROUP, MassEntanglers.METAGROUP, StasisNullifiers.METAGROUP, MutadaptiveRemoteArmorRepairer.METAGROUP);
        }
    }
}
