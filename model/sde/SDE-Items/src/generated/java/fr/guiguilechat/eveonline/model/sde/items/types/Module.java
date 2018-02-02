package fr.guiguilechat.eveonline.model.sde.items.types;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import fr.guiguilechat.eveonline.model.sde.items.Item;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.module.AncillaryArmorRepairer;
import fr.guiguilechat.eveonline.model.sde.items.types.module.AncillaryRemoteArmorRepairer;
import fr.guiguilechat.eveonline.model.sde.items.types.module.AncillaryRemoteShieldBooster;
import fr.guiguilechat.eveonline.model.sde.items.types.module.AncillaryShieldBooster;
import fr.guiguilechat.eveonline.model.sde.items.types.module.ArmorCoating;
import fr.guiguilechat.eveonline.model.sde.items.types.module.ArmorHardener;
import fr.guiguilechat.eveonline.model.sde.items.types.module.ArmorPlatingEnergized;
import fr.guiguilechat.eveonline.model.sde.items.types.module.ArmorReinforcer;
import fr.guiguilechat.eveonline.model.sde.items.types.module.ArmorRepairUnit;
import fr.guiguilechat.eveonline.model.sde.items.types.module.ArmorResistanceShiftHardener;
import fr.guiguilechat.eveonline.model.sde.items.types.module.AutomatedTargetingSystem;
import fr.guiguilechat.eveonline.model.sde.items.types.module.AuxiliaryPowerCore;
import fr.guiguilechat.eveonline.model.sde.items.types.module.BallisticControlSystem;
import fr.guiguilechat.eveonline.model.sde.items.types.module.BurstJammer;
import fr.guiguilechat.eveonline.model.sde.items.types.module.BurstProjectors;
import fr.guiguilechat.eveonline.model.sde.items.types.module.CPUEnhancer;
import fr.guiguilechat.eveonline.model.sde.items.types.module.CapacitorBattery;
import fr.guiguilechat.eveonline.model.sde.items.types.module.CapacitorBooster;
import fr.guiguilechat.eveonline.model.sde.items.types.module.CapacitorFluxCoil;
import fr.guiguilechat.eveonline.model.sde.items.types.module.CapacitorPowerRelay;
import fr.guiguilechat.eveonline.model.sde.items.types.module.CapacitorRecharger;
import fr.guiguilechat.eveonline.model.sde.items.types.module.CapitalSensorArray;
import fr.guiguilechat.eveonline.model.sde.items.types.module.CargoScanner;
import fr.guiguilechat.eveonline.model.sde.items.types.module.CloakingDevice;
import fr.guiguilechat.eveonline.model.sde.items.types.module.CloneVatBay;
import fr.guiguilechat.eveonline.model.sde.items.types.module.CommandBurst;
import fr.guiguilechat.eveonline.model.sde.items.types.module.CountermeasureLauncher;
import fr.guiguilechat.eveonline.model.sde.items.types.module.CynosuralField;
import fr.guiguilechat.eveonline.model.sde.items.types.module.DamageControl;
import fr.guiguilechat.eveonline.model.sde.items.types.module.DataMiners;
import fr.guiguilechat.eveonline.model.sde.items.types.module.DroneControlRangeModule;
import fr.guiguilechat.eveonline.model.sde.items.types.module.DroneDamageModules;
import fr.guiguilechat.eveonline.model.sde.items.types.module.DroneNavigationComputer;
import fr.guiguilechat.eveonline.model.sde.items.types.module.DroneTrackingEnhancer;
import fr.guiguilechat.eveonline.model.sde.items.types.module.DroneTrackingModules;
import fr.guiguilechat.eveonline.model.sde.items.types.module.ECCM;
import fr.guiguilechat.eveonline.model.sde.items.types.module.ECM;
import fr.guiguilechat.eveonline.model.sde.items.types.module.ECMStabilizer;
import fr.guiguilechat.eveonline.model.sde.items.types.module.EnergyNeutralizer;
import fr.guiguilechat.eveonline.model.sde.items.types.module.EnergyNosferatu;
import fr.guiguilechat.eveonline.model.sde.items.types.module.EnergyWeapon;
import fr.guiguilechat.eveonline.model.sde.items.types.module.EntosisLink;
import fr.guiguilechat.eveonline.model.sde.items.types.module.ExpandedCargohold;
import fr.guiguilechat.eveonline.model.sde.items.types.module.FestivalLauncher;
import fr.guiguilechat.eveonline.model.sde.items.types.module.FighterSupportUnit;
import fr.guiguilechat.eveonline.model.sde.items.types.module.FlexArmorHardener;
import fr.guiguilechat.eveonline.model.sde.items.types.module.FlexShieldHardener;
import fr.guiguilechat.eveonline.model.sde.items.types.module.FrequencyMiningLaser;
import fr.guiguilechat.eveonline.model.sde.items.types.module.GangCoordinator;
import fr.guiguilechat.eveonline.model.sde.items.types.module.GasCloudHarvester;
import fr.guiguilechat.eveonline.model.sde.items.types.module.Gyrostabilizer;
import fr.guiguilechat.eveonline.model.sde.items.types.module.HeatSink;
import fr.guiguilechat.eveonline.model.sde.items.types.module.HullRepairUnit;
import fr.guiguilechat.eveonline.model.sde.items.types.module.HybridWeapon;
import fr.guiguilechat.eveonline.model.sde.items.types.module.InertialStabilizer;
import fr.guiguilechat.eveonline.model.sde.items.types.module.InterdictionSphereLauncher;
import fr.guiguilechat.eveonline.model.sde.items.types.module.JumpDriveEconomizer;
import fr.guiguilechat.eveonline.model.sde.items.types.module.JumpPortalGenerator;
import fr.guiguilechat.eveonline.model.sde.items.types.module.MagneticFieldStabilizer;
import fr.guiguilechat.eveonline.model.sde.items.types.module.MicroJumpDrive;
import fr.guiguilechat.eveonline.model.sde.items.types.module.MicroJumpFieldGenerators;
import fr.guiguilechat.eveonline.model.sde.items.types.module.MiningLaser;
import fr.guiguilechat.eveonline.model.sde.items.types.module.MiningUpgrade;
import fr.guiguilechat.eveonline.model.sde.items.types.module.MissileGuidanceComputer;
import fr.guiguilechat.eveonline.model.sde.items.types.module.MissileGuidanceEnhancer;
import fr.guiguilechat.eveonline.model.sde.items.types.module.MissileLauncherBomb;
import fr.guiguilechat.eveonline.model.sde.items.types.module.MissileLauncherCruise;
import fr.guiguilechat.eveonline.model.sde.items.types.module.MissileLauncherDefender;
import fr.guiguilechat.eveonline.model.sde.items.types.module.MissileLauncherHeavy;
import fr.guiguilechat.eveonline.model.sde.items.types.module.MissileLauncherHeavyAssault;
import fr.guiguilechat.eveonline.model.sde.items.types.module.MissileLauncherLight;
import fr.guiguilechat.eveonline.model.sde.items.types.module.MissileLauncherRapidHeavy;
import fr.guiguilechat.eveonline.model.sde.items.types.module.MissileLauncherRapidLight;
import fr.guiguilechat.eveonline.model.sde.items.types.module.MissileLauncherRapidTorpedo;
import fr.guiguilechat.eveonline.model.sde.items.types.module.MissileLauncherRocket;
import fr.guiguilechat.eveonline.model.sde.items.types.module.MissileLauncherTorpedo;
import fr.guiguilechat.eveonline.model.sde.items.types.module.MissileLauncherXLCruise;
import fr.guiguilechat.eveonline.model.sde.items.types.module.MissileLauncherXLTorpedo;
import fr.guiguilechat.eveonline.model.sde.items.types.module.NanofiberInternalStructure;
import fr.guiguilechat.eveonline.model.sde.items.types.module.NonRepeatingHardeners;
import fr.guiguilechat.eveonline.model.sde.items.types.module.OverdriveInjectorSystem;
import fr.guiguilechat.eveonline.model.sde.items.types.module.PassiveTargetingSystem;
import fr.guiguilechat.eveonline.model.sde.items.types.module.PowerDiagnosticSystem;
import fr.guiguilechat.eveonline.model.sde.items.types.module.ProjectedECCM;
import fr.guiguilechat.eveonline.model.sde.items.types.module.ProjectileWeapon;
import fr.guiguilechat.eveonline.model.sde.items.types.module.PropulsionModule;
import fr.guiguilechat.eveonline.model.sde.items.types.module.QAModule;
import fr.guiguilechat.eveonline.model.sde.items.types.module.ReactorControlUnit;
import fr.guiguilechat.eveonline.model.sde.items.types.module.ReinforcedBulkhead;
import fr.guiguilechat.eveonline.model.sde.items.types.module.RemoteArmorRepairer;
import fr.guiguilechat.eveonline.model.sde.items.types.module.RemoteCapacitorTransmitter;
import fr.guiguilechat.eveonline.model.sde.items.types.module.RemoteHullRepairer;
import fr.guiguilechat.eveonline.model.sde.items.types.module.RemoteSensorBooster;
import fr.guiguilechat.eveonline.model.sde.items.types.module.RemoteShieldBooster;
import fr.guiguilechat.eveonline.model.sde.items.types.module.RemoteTrackingComputer;
import fr.guiguilechat.eveonline.model.sde.items.types.module.RigAnchor;
import fr.guiguilechat.eveonline.model.sde.items.types.module.RigArmor;
import fr.guiguilechat.eveonline.model.sde.items.types.module.RigCore;
import fr.guiguilechat.eveonline.model.sde.items.types.module.RigDrones;
import fr.guiguilechat.eveonline.model.sde.items.types.module.RigElectronicSystems;
import fr.guiguilechat.eveonline.model.sde.items.types.module.RigEnergyWeapon;
import fr.guiguilechat.eveonline.model.sde.items.types.module.RigHybridWeapon;
import fr.guiguilechat.eveonline.model.sde.items.types.module.RigLauncher;
import fr.guiguilechat.eveonline.model.sde.items.types.module.RigMining;
import fr.guiguilechat.eveonline.model.sde.items.types.module.RigNavigation;
import fr.guiguilechat.eveonline.model.sde.items.types.module.RigProjectileWeapon;
import fr.guiguilechat.eveonline.model.sde.items.types.module.RigResourceProcessing;
import fr.guiguilechat.eveonline.model.sde.items.types.module.RigScanning;
import fr.guiguilechat.eveonline.model.sde.items.types.module.RigShield;
import fr.guiguilechat.eveonline.model.sde.items.types.module.RigTargeting;
import fr.guiguilechat.eveonline.model.sde.items.types.module.Salvager;
import fr.guiguilechat.eveonline.model.sde.items.types.module.ScanProbeLauncher;
import fr.guiguilechat.eveonline.model.sde.items.types.module.ScanningUpgrade;
import fr.guiguilechat.eveonline.model.sde.items.types.module.ScanningUpgradeTime;
import fr.guiguilechat.eveonline.model.sde.items.types.module.SensorBackupArray;
import fr.guiguilechat.eveonline.model.sde.items.types.module.SensorBooster;
import fr.guiguilechat.eveonline.model.sde.items.types.module.SensorDampener;
import fr.guiguilechat.eveonline.model.sde.items.types.module.ShieldBoostAmplifier;
import fr.guiguilechat.eveonline.model.sde.items.types.module.ShieldBooster;
import fr.guiguilechat.eveonline.model.sde.items.types.module.ShieldExtender;
import fr.guiguilechat.eveonline.model.sde.items.types.module.ShieldFluxCoil;
import fr.guiguilechat.eveonline.model.sde.items.types.module.ShieldHardener;
import fr.guiguilechat.eveonline.model.sde.items.types.module.ShieldPowerRelay;
import fr.guiguilechat.eveonline.model.sde.items.types.module.ShieldRecharger;
import fr.guiguilechat.eveonline.model.sde.items.types.module.ShieldResistanceAmplifier;
import fr.guiguilechat.eveonline.model.sde.items.types.module.ShipModifiers;
import fr.guiguilechat.eveonline.model.sde.items.types.module.ShipScanner;
import fr.guiguilechat.eveonline.model.sde.items.types.module.SiegeModule;
import fr.guiguilechat.eveonline.model.sde.items.types.module.SignalAmplifier;
import fr.guiguilechat.eveonline.model.sde.items.types.module.SignatureScrambling;
import fr.guiguilechat.eveonline.model.sde.items.types.module.SmartBomb;
import fr.guiguilechat.eveonline.model.sde.items.types.module.StasisGrappler;
import fr.guiguilechat.eveonline.model.sde.items.types.module.StasisWeb;
import fr.guiguilechat.eveonline.model.sde.items.types.module.StripMiner;
import fr.guiguilechat.eveonline.model.sde.items.types.module.SuperWeapon;
import fr.guiguilechat.eveonline.model.sde.items.types.module.SurveyProbeLauncher;
import fr.guiguilechat.eveonline.model.sde.items.types.module.SurveyScanner;
import fr.guiguilechat.eveonline.model.sde.items.types.module.SystemScanner;
import fr.guiguilechat.eveonline.model.sde.items.types.module.TargetBreaker;
import fr.guiguilechat.eveonline.model.sde.items.types.module.TargetPainter;
import fr.guiguilechat.eveonline.model.sde.items.types.module.TitanPhenomenaGenerator;
import fr.guiguilechat.eveonline.model.sde.items.types.module.TrackingComputer;
import fr.guiguilechat.eveonline.model.sde.items.types.module.TrackingEnhancer;
import fr.guiguilechat.eveonline.model.sde.items.types.module.TractorBeam;
import fr.guiguilechat.eveonline.model.sde.items.types.module.WarpAccelerator;
import fr.guiguilechat.eveonline.model.sde.items.types.module.WarpCoreStabilizer;
import fr.guiguilechat.eveonline.model.sde.items.types.module.WarpDisruptFieldGenerator;
import fr.guiguilechat.eveonline.model.sde.items.types.module.WarpScrambler;
import fr.guiguilechat.eveonline.model.sde.items.types.module.WeaponDisruptor;

public abstract class Module
    extends Item
{
    /**
     * The maximum hitpoints of an object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Hp;
    /**
     * The ranking of the module within its tech level
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MetaLevel;
    /**
     * Tech level of an item
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int TechLevel;

    @Override
    public int getCategoryId() {
        return  7;
    }

    @Override
    public Class<?> getCategory() {
        return Module.class;
    }

    public static Map<String, ? extends Module> loadCategory() {
        return Stream.of(CountermeasureLauncher.load(), ArmorResistanceShiftHardener.load(), RemoteTrackingComputer.load(), ArmorReinforcer.load(), RigScanning.load(), GasCloudHarvester.load(), MicroJumpDrive.load(), StasisWeb.load(), TrackingComputer.load(), BallisticControlSystem.load(), RemoteSensorBooster.load(), StripMiner.load(), RigResourceProcessing.load(), SignalAmplifier.load(), BurstProjectors.load(), RemoteShieldBooster.load(), DataMiners.load(), ShipScanner.load(), RigArmor.load(), CloakingDevice.load(), RemoteArmorRepairer.load(), ECCM.load(), RigElectronicSystems.load(), FighterSupportUnit.load(), ECMStabilizer.load(), JumpDriveEconomizer.load(), MagneticFieldStabilizer.load(), SystemScanner.load(), RigShield.load(), ScanningUpgrade.load(), CapacitorRecharger.load(), CloneVatBay.load(), FrequencyMiningLaser.load(), WarpDisruptFieldGenerator.load(), PropulsionModule.load(), ShieldRecharger.load(), DroneControlRangeModule.load(), ShieldBooster.load(), ArmorPlatingEnergized.load(), FlexArmorHardener.load(), SensorBackupArray.load(), ProjectedECCM.load(), DroneTrackingModules.load(), ShieldExtender.load(), ReinforcedBulkhead.load(), MissileLauncherXLTorpedo.load(), NanofiberInternalStructure.load(), MissileLauncherTorpedo.load(), CPUEnhancer.load(), MissileLauncherRapidLight.load(), DroneTrackingEnhancer.load(), EnergyNeutralizer.load(), HybridWeapon.load(), MissileLauncherLight.load(), ShieldHardener.load(), HullRepairUnit.load(), TargetPainter.load(), AncillaryRemoteArmorRepairer.load(), RigLauncher.load(), SensorBooster.load(), ExpandedCargohold.load(), InertialStabilizer.load(), TrackingEnhancer.load(), CynosuralField.load(), CapacitorPowerRelay.load(), Gyrostabilizer.load(), ShieldPowerRelay.load(), MissileGuidanceComputer.load(), TractorBeam.load(), MicroJumpFieldGenerators.load(), BurstJammer.load(), WeaponDisruptor.load(), ShieldBoostAmplifier.load(), SurveyScanner.load(), DroneDamageModules.load(), AutomatedTargetingSystem.load(), CapacitorBooster.load(), SiegeModule.load(), SmartBomb.load(), EnergyWeapon.load(), SuperWeapon.load(), PassiveTargetingSystem.load(), RemoteCapacitorTransmitter.load(), ArmorHardener.load(), SensorDampener.load(), CapacitorFluxCoil.load(), NonRepeatingHardeners.load(), ProjectileWeapon.load(), RigProjectileWeapon.load(), RigCore.load(), TitanPhenomenaGenerator.load(), RigHybridWeapon.load(), MissileLauncherBomb.load(), MissileLauncherHeavy.load(), TargetBreaker.load(), MiningLaser.load(), ScanningUpgradeTime.load(), FestivalLauncher.load(), RigEnergyWeapon.load(), StasisGrappler.load(), AuxiliaryPowerCore.load(), FlexShieldHardener.load(), RigDrones.load(), ArmorRepairUnit.load(), HeatSink.load(), ShieldResistanceAmplifier.load(), MissileLauncherXLCruise.load(), RigMining.load(), MissileGuidanceEnhancer.load(), RigTargeting.load(), RemoteHullRepairer.load(), InterdictionSphereLauncher.load(), RigNavigation.load(), WarpAccelerator.load(), ShieldFluxCoil.load(), DamageControl.load(), CapacitorBattery.load(), QAModule.load(), AncillaryShieldBooster.load(), PowerDiagnosticSystem.load(), AncillaryArmorRepairer.load(), MissileLauncherRapidHeavy.load(), RigAnchor.load(), ScanProbeLauncher.load(), MissileLauncherDefender.load(), CommandBurst.load(), DroneNavigationComputer.load(), SurveyProbeLauncher.load(), SignatureScrambling.load(), JumpPortalGenerator.load(), MissileLauncherHeavyAssault.load(), CargoScanner.load(), ArmorCoating.load(), MissileLauncherRapidTorpedo.load(), MissileLauncherRocket.load(), AncillaryRemoteShieldBooster.load(), GangCoordinator.load(), EnergyNosferatu.load(), EntosisLink.load(), WarpCoreStabilizer.load(), MissileLauncherCruise.load(), CapitalSensorArray.load(), MiningUpgrade.load(), ShipModifiers.load(), ECM.load(), ReactorControlUnit.load(), Salvager.load(), WarpScrambler.load(), OverdriveInjectorSystem.load()).flatMap((m -> m.entrySet().stream())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
