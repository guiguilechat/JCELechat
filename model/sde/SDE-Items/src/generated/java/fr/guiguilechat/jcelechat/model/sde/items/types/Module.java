package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.AncillaryArmorRepairer;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.AncillaryRemoteArmorRepairer;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.AncillaryRemoteShieldBooster;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.AncillaryShieldBooster;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.ArmorCoating;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.ArmorHardener;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.ArmorPlatingEnergized;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.ArmorReinforcer;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.ArmorRepairUnit;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.ArmorResistanceShiftHardener;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.AutomatedTargetingSystem;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.AuxiliaryPowerCore;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.BallisticControlSystem;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.BurstJammer;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.BurstProjectors;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.CPUEnhancer;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.CapacitorBattery;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.CapacitorBooster;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.CapacitorFluxCoil;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.CapacitorPowerRelay;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.CapacitorRecharger;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.CapitalSensorArray;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.CargoScanner;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.CloakingDevice;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.CloneVatBay;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.CommandBurst;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.CynosuralField;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.DamageControl;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.DataMiners;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.DroneControlRangeModule;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.DroneDamageModules;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.DroneNavigationComputer;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.DroneTrackingEnhancer;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.DroneTrackingModules;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.ECM;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.ECMStabilizer;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.EnergyNeutralizer;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.EnergyNosferatu;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.EnergyWeapon;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.EntosisLink;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.EntropicRadiationSink;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.ExpandedCargohold;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.FestivalLauncher;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.FighterSupportUnit;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.FlexArmorHardener;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.FlexShieldHardener;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.FrequencyMiningLaser;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.GangCoordinator;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.GasCloudHarvester;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.Gyrostabilizer;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.HeatSink;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.HullRepairUnit;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.HybridWeapon;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.InertialStabilizer;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.InterdictionSphereLauncher;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.JumpDriveEconomizer;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.JumpPortalGenerator;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.MagneticFieldStabilizer;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.MicroJumpDrive;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.MicroJumpFieldGenerators;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.MiningLaser;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.MiningUpgrade;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.MissileGuidanceComputer;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.MissileGuidanceEnhancer;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.MissileLauncherBomb;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.MissileLauncherCruise;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.MissileLauncherDefender;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.MissileLauncherHeavy;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.MissileLauncherHeavyAssault;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.MissileLauncherLight;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.MissileLauncherRapidHeavy;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.MissileLauncherRapidLight;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.MissileLauncherRapidTorpedo;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.MissileLauncherRocket;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.MissileLauncherTorpedo;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.MissileLauncherXLCruise;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.MissileLauncherXLTorpedo;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.NanofiberInternalStructure;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.OverdriveInjectorSystem;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.PassiveTargetingSystem;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.PowerDiagnosticSystem;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.PrecursorWeapon;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.ProjectileWeapon;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.PropulsionModule;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.ReactorControlUnit;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.ReinforcedBulkhead;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.RemoteArmorRepairer;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.RemoteCapacitorTransmitter;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.RemoteHullRepairer;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.RemoteSensorBooster;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.RemoteShieldBooster;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.RemoteTrackingComputer;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.RigAnchor;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.RigArmor;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.RigCore;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.RigDrones;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.RigElectronicSystems;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.RigEnergyWeapon;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.RigHybridWeapon;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.RigLauncher;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.RigNavigation;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.RigProjectileWeapon;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.RigResourceProcessing;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.RigScanning;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.RigShield;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.RigTargeting;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.Salvager;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.ScanProbeLauncher;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.ScanningUpgrade;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.ScanningUpgradeTime;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.SensorBooster;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.SensorDampener;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.ShieldBoostAmplifier;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.ShieldBooster;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.ShieldExtender;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.ShieldFluxCoil;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.ShieldHardener;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.ShieldPowerRelay;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.ShieldRecharger;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.ShieldResistanceAmplifier;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.ShipScanner;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.SiegeModule;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.SignalAmplifier;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.SmartBomb;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.StasisGrappler;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.StasisWeb;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.StripMiner;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.SuperWeapon;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.SurveyProbeLauncher;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.SurveyScanner;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.TargetBreaker;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.TargetPainter;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.TitanPhenomenaGenerator;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.TrackingComputer;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.TrackingEnhancer;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.TractorBeam;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.WarpAccelerator;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.WarpCoreStabilizer;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.WarpDisruptFieldGenerator;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.WarpScrambler;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.WeaponDisruptor;

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
     * Authoring has been moved to FSD
     * The ranking of the module within its tech level
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MetaLevel;
    /**
     * Authoring has been moved to FSD
     * Tech level of an item
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int TechLevel;

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  9 :
            {
                return Hp;
            }
            case  633 :
            {
                return MetaLevel;
            }
            case  422 :
            {
                return TechLevel;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public int getCategoryId() {
        return  7;
    }

    @Override
    public Class<?> getCategory() {
        return Module.class;
    }

    public static Map<String, ? extends Module> loadCategory() {
        return Stream.of(AncillaryArmorRepairer.load(), AncillaryRemoteArmorRepairer.load(), AncillaryRemoteShieldBooster.load(), AncillaryShieldBooster.load(), ArmorCoating.load(), ArmorHardener.load(), ArmorPlatingEnergized.load(), ArmorReinforcer.load(), ArmorRepairUnit.load(), ArmorResistanceShiftHardener.load(), AutomatedTargetingSystem.load(), AuxiliaryPowerCore.load(), BallisticControlSystem.load(), BurstJammer.load(), BurstProjectors.load(), CPUEnhancer.load(), CapacitorBattery.load(), CapacitorBooster.load(), CapacitorFluxCoil.load(), CapacitorPowerRelay.load(), CapacitorRecharger.load(), CapitalSensorArray.load(), CargoScanner.load(), CloakingDevice.load(), CloneVatBay.load(), CommandBurst.load(), CynosuralField.load(), DamageControl.load(), DataMiners.load(), DroneControlRangeModule.load(), DroneDamageModules.load(), DroneNavigationComputer.load(), DroneTrackingEnhancer.load(), DroneTrackingModules.load(), ECM.load(), ECMStabilizer.load(), EnergyNeutralizer.load(), EnergyNosferatu.load(), EnergyWeapon.load(), EntosisLink.load(), EntropicRadiationSink.load(), ExpandedCargohold.load(), FestivalLauncher.load(), FighterSupportUnit.load(), FlexArmorHardener.load(), FlexShieldHardener.load(), FrequencyMiningLaser.load(), GangCoordinator.load(), GasCloudHarvester.load(), Gyrostabilizer.load(), HeatSink.load(), HullRepairUnit.load(), HybridWeapon.load(), InertialStabilizer.load(), InterdictionSphereLauncher.load(), JumpDriveEconomizer.load(), JumpPortalGenerator.load(), MagneticFieldStabilizer.load(), MicroJumpDrive.load(), MicroJumpFieldGenerators.load(), MiningLaser.load(), MiningUpgrade.load(), MissileGuidanceComputer.load(), MissileGuidanceEnhancer.load(), MissileLauncherBomb.load(), MissileLauncherCruise.load(), MissileLauncherDefender.load(), MissileLauncherHeavy.load(), MissileLauncherHeavyAssault.load(), MissileLauncherLight.load(), MissileLauncherRapidHeavy.load(), MissileLauncherRapidLight.load(), MissileLauncherRapidTorpedo.load(), MissileLauncherRocket.load(), MissileLauncherTorpedo.load(), MissileLauncherXLCruise.load(), MissileLauncherXLTorpedo.load(), NanofiberInternalStructure.load(), OverdriveInjectorSystem.load(), PassiveTargetingSystem.load(), PowerDiagnosticSystem.load(), PrecursorWeapon.load(), ProjectileWeapon.load(), PropulsionModule.load(), ReactorControlUnit.load(), ReinforcedBulkhead.load(), RemoteArmorRepairer.load(), RemoteCapacitorTransmitter.load(), RemoteHullRepairer.load(), RemoteSensorBooster.load(), RemoteShieldBooster.load(), RemoteTrackingComputer.load(), RigAnchor.load(), RigArmor.load(), RigCore.load(), RigDrones.load(), RigElectronicSystems.load(), RigEnergyWeapon.load(), RigHybridWeapon.load(), RigLauncher.load(), RigNavigation.load(), RigProjectileWeapon.load(), RigResourceProcessing.load(), RigScanning.load(), RigShield.load(), RigTargeting.load(), Salvager.load(), ScanProbeLauncher.load(), ScanningUpgrade.load(), ScanningUpgradeTime.load(), SensorBooster.load(), SensorDampener.load(), ShieldBoostAmplifier.load(), ShieldBooster.load(), ShieldExtender.load(), ShieldFluxCoil.load(), ShieldHardener.load(), ShieldPowerRelay.load(), ShieldRecharger.load(), ShieldResistanceAmplifier.load(), ShipScanner.load(), SiegeModule.load(), SignalAmplifier.load(), SmartBomb.load(), StasisGrappler.load(), StasisWeb.load(), StripMiner.load(), SuperWeapon.load(), SurveyProbeLauncher.load(), SurveyScanner.load(), TargetBreaker.load(), TargetPainter.load(), TitanPhenomenaGenerator.load(), TrackingComputer.load(), TrackingEnhancer.load(), TractorBeam.load(), WarpAccelerator.load(), WarpCoreStabilizer.load(), WarpDisruptFieldGenerator.load(), WarpScrambler.load(), WeaponDisruptor.load()).flatMap((m -> m.entrySet().stream())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
