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
        return Stream.of(CynosuralField.load(), MagneticFieldStabilizer.load(), RigArmor.load(), TrackingEnhancer.load(), PowerDiagnosticSystem.load(), RigScanning.load(), CapacitorPowerRelay.load(), FlexArmorHardener.load(), OverdriveInjectorSystem.load(), RigHybridWeapon.load(), MissileLauncherRapidHeavy.load(), CloneVatBay.load(), SurveyProbeLauncher.load(), PropulsionModule.load(), DamageControl.load(), DroneTrackingModules.load(), NanofiberInternalStructure.load(), WarpAccelerator.load(), RigDrones.load(), HullRepairUnit.load(), ShieldResistanceAmplifier.load(), MissileGuidanceEnhancer.load(), GangCoordinator.load(), MissileLauncherLight.load(), InterdictionSphereLauncher.load(), GasCloudHarvester.load(), MissileLauncherXLCruise.load(), SmartBomb.load(), HybridWeapon.load(), BurstProjectors.load(), BallisticControlSystem.load(), ShieldBooster.load(), RemoteArmorRepairer.load(), SystemScanner.load(), MissileLauncherHeavy.load(), AncillaryArmorRepairer.load(), Salvager.load(), FighterSupportUnit.load(), CountermeasureLauncher.load(), BurstJammer.load(), DataMiners.load(), JumpPortalGenerator.load(), FlexShieldHardener.load(), WarpScrambler.load(), RigElectronicSystems.load(), MissileGuidanceComputer.load(), SurveyScanner.load(), RemoteSensorBooster.load(), InertialStabilizer.load(), CapacitorBooster.load(), StripMiner.load(), FrequencyMiningLaser.load(), RemoteShieldBooster.load(), MissileLauncherHeavyAssault.load(), DroneControlRangeModule.load(), ProjectileWeapon.load(), ArmorResistanceShiftHardener.load(), MissileLauncherRapidTorpedo.load(), ArmorReinforcer.load(), RemoteCapacitorTransmitter.load(), PassiveTargetingSystem.load(), ArmorHardener.load(), QAModule.load(), RigLauncher.load(), MissileLauncherDefender.load(), SuperWeapon.load(), DroneNavigationComputer.load(), CloakingDevice.load(), AutomatedTargetingSystem.load(), WarpCoreStabilizer.load(), RemoteHullRepairer.load(), WarpDisruptFieldGenerator.load(), TitanPhenomenaGenerator.load(), ExpandedCargohold.load(), SensorDampener.load(), CPUEnhancer.load(), MissileLauncherRocket.load(), ShipModifiers.load(), RemoteTrackingComputer.load(), RigProjectileWeapon.load(), ShieldFluxCoil.load(), RigTargeting.load(), TractorBeam.load(), EntosisLink.load(), ECM.load(), SiegeModule.load(), SignalAmplifier.load(), CommandBurst.load(), ShieldPowerRelay.load(), MissileLauncherTorpedo.load(), DroneDamageModules.load(), EnergyWeapon.load(), ShieldExtender.load(), ArmorPlatingEnergized.load(), AncillaryRemoteShieldBooster.load(), MiningLaser.load(), JumpDriveEconomizer.load(), HeatSink.load(), ShieldRecharger.load(), AncillaryShieldBooster.load(), EnergyNosferatu.load(), WeaponDisruptor.load(), ECMStabilizer.load(), CapitalSensorArray.load(), NonRepeatingHardeners.load(), CargoScanner.load(), Gyrostabilizer.load(), MicroJumpDrive.load(), TargetBreaker.load(), SensorBooster.load(), ScanProbeLauncher.load(), RigAnchor.load(), ShieldHardener.load(), MissileLauncherXLTorpedo.load(), MissileLauncherRapidLight.load(), AuxiliaryPowerCore.load(), ReinforcedBulkhead.load(), StasisGrappler.load(), RigResourceProcessing.load(), ShipScanner.load(), RigNavigation.load(), DroneTrackingEnhancer.load(), ArmorRepairUnit.load(), EnergyNeutralizer.load(), FestivalLauncher.load(), MissileLauncherCruise.load(), RigShield.load(), MiningUpgrade.load(), ReactorControlUnit.load(), AncillaryRemoteArmorRepairer.load(), RigEnergyWeapon.load(), ScanningUpgradeTime.load(), SensorBackupArray.load(), TrackingComputer.load(), ShieldBoostAmplifier.load(), TargetPainter.load(), ArmorCoating.load(), ECCM.load(), ScanningUpgrade.load(), CapacitorBattery.load(), SignatureScrambling.load(), RigMining.load(), CapacitorRecharger.load(), StasisWeb.load(), ProjectedECCM.load(), CapacitorFluxCoil.load(), RigCore.load(), MissileLauncherBomb.load(), MicroJumpFieldGenerators.load()).flatMap((m -> m.entrySet().stream())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
