package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.module.*;

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
        return Stream.of(AncillaryArmorRepairer.load(), AncillaryRemoteArmorRepairer.load(), AncillaryRemoteShieldBooster.load(), AncillaryShieldBooster.load(), ArmorCoating.load(), ArmorHardener.load(), ArmorPlatingEnergized.load(), ArmorReinforcer.load(), ArmorRepairUnit.load(), ArmorResistanceShiftHardener.load(), AutomatedTargetingSystem.load(), AuxiliaryPowerCore.load(), BallisticControlSystem.load(), BurstJammer.load(), BurstProjectors.load(), CPUEnhancer.load(), CapacitorBattery.load(), CapacitorBooster.load(), CapacitorFluxCoil.load(), CapacitorPowerRelay.load(), CapacitorRecharger.load(), CapitalSensorArray.load(), CargoScanner.load(), CloakingDevice.load(), CloneVatBay.load(), CommandBurst.load(), CountermeasureLauncher.load(), CynosuralField.load(), DamageControl.load(), DataMiners.load(), DroneControlRangeModule.load(), DroneDamageModules.load(), DroneNavigationComputer.load(), DroneTrackingEnhancer.load(), DroneTrackingModules.load(), ECCM.load(), ECM.load(), ECMStabilizer.load(), EnergyNeutralizer.load(), EnergyNosferatu.load(), EnergyWeapon.load(), EntosisLink.load(), EntropicRadiationSink.load(), ExpandedCargohold.load(), FestivalLauncher.load(), FighterSupportUnit.load(), FlexArmorHardener.load(), FlexShieldHardener.load(), FrequencyMiningLaser.load(), GangCoordinator.load(), GasCloudHarvester.load(), Gyrostabilizer.load(), HeatSink.load(), HullRepairUnit.load(), HybridWeapon.load(), InertialStabilizer.load(), InterdictionSphereLauncher.load(), JumpDriveEconomizer.load(), JumpPortalGenerator.load(), MagneticFieldStabilizer.load(), MicroJumpDrive.load(), MicroJumpFieldGenerators.load(), MiningLaser.load(), MiningUpgrade.load(), MissileGuidanceComputer.load(), MissileGuidanceEnhancer.load(), MissileLauncherBomb.load(), MissileLauncherCruise.load(), MissileLauncherDefender.load(), MissileLauncherHeavy.load(), MissileLauncherHeavyAssault.load(), MissileLauncherLight.load(), MissileLauncherRapidHeavy.load(), MissileLauncherRapidLight.load(), MissileLauncherRapidTorpedo.load(), MissileLauncherRocket.load(), MissileLauncherTorpedo.load(), MissileLauncherXLCruise.load(), MissileLauncherXLTorpedo.load(), NanofiberInternalStructure.load(), NonRepeatingHardeners.load(), OverdriveInjectorSystem.load(), PassiveTargetingSystem.load(), PowerDiagnosticSystem.load(), PrecursorWeapon.load(), ProjectedECCM.load(), ProjectileWeapon.load(), PropulsionModule.load(), QAModule.load(), ReactorControlUnit.load(), ReinforcedBulkhead.load(), RemoteArmorRepairer.load(), RemoteCapacitorTransmitter.load(), RemoteHullRepairer.load(), RemoteSensorBooster.load(), RemoteShieldBooster.load(), RemoteTrackingComputer.load(), RigAnchor.load(), RigArmor.load(), RigCore.load(), RigDrones.load(), RigElectronicSystems.load(), RigEnergyWeapon.load(), RigHybridWeapon.load(), RigLauncher.load(), RigMining.load(), RigNavigation.load(), RigProjectileWeapon.load(), RigResourceProcessing.load(), RigScanning.load(), RigShield.load(), RigTargeting.load(), Salvager.load(), ScanProbeLauncher.load(), ScanningUpgrade.load(), ScanningUpgradeTime.load(), SensorBackupArray.load(), SensorBooster.load(), SensorDampener.load(), ShieldBoostAmplifier.load(), ShieldBooster.load(), ShieldExtender.load(), ShieldFluxCoil.load(), ShieldHardener.load(), ShieldPowerRelay.load(), ShieldRecharger.load(), ShieldResistanceAmplifier.load(), ShipModifiers.load(), ShipScanner.load(), SiegeModule.load(), SignalAmplifier.load(), SignatureScrambling.load(), SmartBomb.load(), StasisGrappler.load(), StasisWeb.load(), StripMiner.load(), SuperWeapon.load(), SurveyProbeLauncher.load(), SurveyScanner.load(), SystemScanner.load(), TargetBreaker.load(), TargetPainter.load(), TitanPhenomenaGenerator.load(), TrackingComputer.load(), TrackingEnhancer.load(), TractorBeam.load(), WarpAccelerator.load(), WarpCoreStabilizer.load(), WarpDisruptFieldGenerator.load(), WarpScrambler.load(), WeaponDisruptor.load()).flatMap((m -> m.entrySet().stream())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
