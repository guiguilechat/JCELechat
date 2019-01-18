package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.AdvancedCapitalConstructionComponentBlueprints;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.AdvancedExoticPlasmaChargeBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.AdvancedFrequencyCrystalBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.AdvancedHybridChargeBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.AdvancedProjectileAmmoBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.AntiWarpScramblerBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.ArmorCoatingBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.ArmorHardenerBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.ArmorReinforcerBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.ArmorRepairUnitBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.ArmorResistanceShiftHardenerBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.AutomatedTargetingSystemBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.AuxiliaryPowerCoreBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.BallisticControlSystemBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.BattlecruiserBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.BattleshipBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.BiochemicalReactionFormulas;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.BombBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.BoosterBlueprints;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.BurstJammerBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.BurstProjectorBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.CapacitorBatteryBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.CapacitorBoosterBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.CapacitorBoosterChargeBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.CapacitorRechargerBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.CapitalConstructionBlueprints;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.CapitalIndustrialShipBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.CargoScannerBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.CarrierBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.CloakingDeviceBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.CoProcessorBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.CombatDroneBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.CommandBurstBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.CommandBurstChargeBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.CommandDestroyerBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.CompositeReactionFormulas;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.ConstructionComponentBlueprints;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.ContainerBlueprints;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.CruiserBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.CyberScanningImplantBlueprints;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.DamageControlBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.DataMinerBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.DefenderMissileBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.DestroyerBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.DreadnoughtBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.DroneDamageModuleBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.DroneUpgradeBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.DrugBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.ECMBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.ECMStabilizerBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.ElectronicWarfareDroneBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.EliteIndustrialBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.EncounterSurveillanceSystemBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.EnergyNeutralizerBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.EnergyNeutralizerDroneBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.EnergyNosferatuBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.EnergyWeaponBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.EntosisLinkBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.EntropicRadiationSinkBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.ExoticPlasmaChargeBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.ForceAuxiliaryBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.FreighterBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.FrequencyCrystalBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.FrigateBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.FuelBlockBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.FueledArmorRepairerBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.FueledShieldBoosterBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.GangCoordinatorBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.GunneryUpgradeBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.HeatSinkBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.HeavyFighterBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.HullModsBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.HullRepairUnitBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.HybridChargeBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.HybridComponentBlueprints;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.HybridWeaponBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.ImplantBlueprints;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.IndustrialBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.IndustrialCommandShipBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.InfrastructureUpgradeBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.LightFighterBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.LogisticDroneBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.MassEntanglerBlueprints;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.MicroJumpDriveBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.MicroJumpFieldGeneratorBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.MiningBargeBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.MiningCrystalBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.MiningDroneBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.MiningLaserBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.MiningLaserUpgradeBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.MissileBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.MissileGuidanceComputerBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.MissileGuidanceEnhancerBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.MissileLauncherBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.MobileCynosuralInhibitorBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.MobileDecoyUnitBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.MobileDepotBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.MobileMicroJumpUnitBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.MobileScanInhibitorBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.MobileSiphonUnitBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.MobileTractorUnitBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.MobileWarpDisruptorBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.MutadaptiveRemoteArmorRepairerBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.PassiveTargetingSystemBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.PolymerReactionFormulas;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.PowerManagerBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.PrecursorWeaponBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.ProjectileAmmoBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.ProjectileWeaponBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.PropulsionModuleBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.RemoteArmorRepairerBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.RemoteCapacitorTransmitterBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.RemoteHullRepairerBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.RemoteShieldBoosterBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.RemoteTrackingComputerBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.RigBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.SalvageDroneBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.SalvagerBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.ScanProbeBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.ScanProbeLauncherBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.ScanningUpgradeBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.ScanningUpgradeTimeBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.ScriptBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.SensorBoosterBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.ShieldBoostAmplifierBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.ShieldBoosterBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.ShieldExtenderBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.ShieldHardenerBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.ShieldRechargerBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.ShieldResistanceAmplifierBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.ShieldResistanceShiftHardenerBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.ShipScannerBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.ShuttleBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.SiegeModuleBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.SignalAmplifierBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.SmartBombBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.SovereigntyStructureBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.SpecialEditionCommodityBlueprints;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.StarbaseBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.StarbaseControlTowerBlueprints;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.StarbaseECMJammingArrayBlueprints;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.StarbaseEnergyNeutralizingBatteryBlueprints;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.StarbaseHybridBatteryBlueprints;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.StarbaseLaserBatteryBlueprints;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.StarbaseMissileBatteryBlueprints;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.StarbaseMobileLaboratoryBlueprints;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.StarbaseProjectileBatteryBlueprints;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.StarbaseSensorDampeningArrayBlueprints;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.StarbaseStasisWebificationBatteryBlueprints;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.StarbaseWarpScramblingBatteryBlueprints;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.StasisWebBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.StasisWebifyingDroneBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.StrategicCruiserBlueprints;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.StripMinerBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.StructureBlueprints;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.StructureModuleBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.StructureRigBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.SubsystemBlueprints;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.SuperWeaponBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.SupercarrierBlueprints;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.SupportFighterBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.SurveyProbeBlueprints;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.SurveyProbeLauncherBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.SurveyScannerBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.TacticalDestroyerBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.TargetBreakerBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.TargetPainterBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.TitanBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.ToolBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.TrackingComputerBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.TrackingEnhancerBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.TractorBeamBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.WarpScramblerBlueprint;
import fr.guiguilechat.jcelechat.model.sde.items.types.blueprint.WeaponDisruptorBlueprint;

public abstract class Blueprint
    extends Item
{
    public static final Blueprint.MetaCat METACAT = new Blueprint.MetaCat();

    @Override
    public IMetaCategory<Blueprint> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<Blueprint>
    {

        @Override
        public int getCategoryId() {
            return  9;
        }

        @Override
        public String getName() {
            return "Blueprint";
        }

        @Override
        public Collection<IMetaGroup<? extends Blueprint>> groups() {
            return Arrays.asList(FrigateBlueprint.METAGROUP, CruiserBlueprint.METAGROUP, BattleshipBlueprint.METAGROUP, IndustrialBlueprint.METAGROUP, TitanBlueprint.METAGROUP, ShuttleBlueprint.METAGROUP, ShieldExtenderBlueprint.METAGROUP, ShieldRechargerBlueprint.METAGROUP, ShieldBoosterBlueprint.METAGROUP, RemoteShieldBoosterBlueprint.METAGROUP, CapacitorRechargerBlueprint.METAGROUP, PropulsionModuleBlueprint.METAGROUP, CargoScannerBlueprint.METAGROUP, ShipScannerBlueprint.METAGROUP, SurveyScannerBlueprint.METAGROUP, ECMBlueprint.METAGROUP, WarpScramblerBlueprint.METAGROUP, EnergyWeaponBlueprint.METAGROUP, MiningLaserBlueprint.METAGROUP, ProjectileWeaponBlueprint.METAGROUP, MissileLauncherBlueprint.METAGROUP, PowerManagerBlueprint.METAGROUP, GunneryUpgradeBlueprint.METAGROUP, DamageControlBlueprint.METAGROUP, CapacitorBatteryBlueprint.METAGROUP, ArmorRepairUnitBlueprint.METAGROUP, HullRepairUnitBlueprint.METAGROUP, StasisWebBlueprint.METAGROUP, RemoteCapacitorTransmitterBlueprint.METAGROUP, EnergyNosferatuBlueprint.METAGROUP, EnergyNeutralizerBlueprint.METAGROUP, SmartBombBlueprint.METAGROUP, HybridWeaponBlueprint.METAGROUP, CapacitorBoosterBlueprint.METAGROUP, ShieldHardenerBlueprint.METAGROUP, HullModsBlueprint.METAGROUP, BurstJammerBlueprint.METAGROUP, PassiveTargetingSystemBlueprint.METAGROUP, AutomatedTargetingSystemBlueprint.METAGROUP, ArmorCoatingBlueprint.METAGROUP, ProjectileAmmoBlueprint.METAGROUP, MissileBlueprint.METAGROUP, HybridChargeBlueprint.METAGROUP, FrequencyCrystalBlueprint.METAGROUP, CapacitorBoosterChargeBlueprint.METAGROUP, DefenderMissileBlueprint.METAGROUP, BombBlueprint.METAGROUP, CombatDroneBlueprint.METAGROUP, MiningDroneBlueprint.METAGROUP, DrugBlueprint.METAGROUP, HeatSinkBlueprint.METAGROUP, SensorBoosterBlueprint.METAGROUP, TrackingComputerBlueprint.METAGROUP, ShieldResistanceAmplifierBlueprint.METAGROUP, AntiWarpScramblerBlueprint.METAGROUP, WeaponDisruptorBlueprint.METAGROUP, TrackingEnhancerBlueprint.METAGROUP, RemoteTrackingComputerBlueprint.METAGROUP, CoProcessorBlueprint.METAGROUP, SignalAmplifierBlueprint.METAGROUP, ArmorHardenerBlueprint.METAGROUP, ArmorReinforcerBlueprint.METAGROUP, RemoteArmorRepairerBlueprint.METAGROUP, AuxiliaryPowerCoreBlueprint.METAGROUP, ToolBlueprint.METAGROUP, ShieldBoostAmplifierBlueprint.METAGROUP, MobileWarpDisruptorBlueprint.METAGROUP, BallisticControlSystemBlueprint.METAGROUP, CloakingDeviceBlueprint.METAGROUP, DroneUpgradeBlueprint.METAGROUP, ConstructionComponentBlueprints.METAGROUP, MiningBargeBlueprint.METAGROUP, ScanProbeBlueprint.METAGROUP, DestroyerBlueprint.METAGROUP, BattlecruiserBlueprint.METAGROUP, StripMinerBlueprint.METAGROUP, EliteIndustrialBlueprint.METAGROUP, TargetPainterBlueprint.METAGROUP, SiegeModuleBlueprint.METAGROUP, FreighterBlueprint.METAGROUP, GangCoordinatorBlueprint.METAGROUP, DreadnoughtBlueprint.METAGROUP, CarrierBlueprint.METAGROUP, SuperWeaponBlueprint.METAGROUP, BoosterBlueprints.METAGROUP, AdvancedHybridChargeBlueprint.METAGROUP, TractorBeamBlueprint.METAGROUP, ImplantBlueprints.METAGROUP, AdvancedProjectileAmmoBlueprint.METAGROUP, AdvancedFrequencyCrystalBlueprint.METAGROUP, MiningCrystalBlueprint.METAGROUP, RigBlueprint.METAGROUP, StarbaseControlTowerBlueprints.METAGROUP, StarbaseLaserBatteryBlueprints.METAGROUP, StarbaseProjectileBatteryBlueprints.METAGROUP, StarbaseHybridBatteryBlueprints.METAGROUP, StarbaseECMJammingArrayBlueprints.METAGROUP, StarbaseWarpScramblingBatteryBlueprints.METAGROUP, StarbaseStasisWebificationBatteryBlueprints.METAGROUP, StarbaseSensorDampeningArrayBlueprints.METAGROUP, StarbaseEnergyNeutralizingBatteryBlueprints.METAGROUP, RemoteHullRepairerBlueprint.METAGROUP, StarbaseMissileBatteryBlueprints.METAGROUP, StarbaseMobileLaboratoryBlueprints.METAGROUP, ScriptBlueprint.METAGROUP, AdvancedCapitalConstructionComponentBlueprints.METAGROUP, CapitalConstructionBlueprints.METAGROUP, DataMinerBlueprint.METAGROUP, ScanProbeLauncherBlueprint.METAGROUP, CapitalIndustrialShipBlueprint.METAGROUP, IndustrialCommandShipBlueprint.METAGROUP, HybridComponentBlueprints.METAGROUP, SubsystemBlueprints.METAGROUP, StrategicCruiserBlueprints.METAGROUP, SupercarrierBlueprints.METAGROUP, SovereigntyStructureBlueprint.METAGROUP, StarbaseBlueprint.METAGROUP, SalvagerBlueprint.METAGROUP, FuelBlockBlueprint.METAGROUP, MiningLaserUpgradeBlueprint.METAGROUP, EnergyNeutralizerDroneBlueprint.METAGROUP, ElectronicWarfareDroneBlueprint.METAGROUP, LogisticDroneBlueprint.METAGROUP, HeavyFighterBlueprint.METAGROUP, LightFighterBlueprint.METAGROUP, StasisWebifyingDroneBlueprint.METAGROUP, ArmorResistanceShiftHardenerBlueprint.METAGROUP, DroneDamageModuleBlueprint.METAGROUP, TargetBreakerBlueprint.METAGROUP, FueledShieldBoosterBlueprint.METAGROUP, SurveyProbeBlueprints.METAGROUP, ContainerBlueprints.METAGROUP, SalvageDroneBlueprint.METAGROUP, MicroJumpDriveBlueprint.METAGROUP, SpecialEditionCommodityBlueprints.METAGROUP, FueledArmorRepairerBlueprint.METAGROUP, ECMStabilizerBlueprint.METAGROUP, ScanningUpgradeBlueprint.METAGROUP, SurveyProbeLauncherBlueprint.METAGROUP, ScanningUpgradeTimeBlueprint.METAGROUP, MobileSiphonUnitBlueprint.METAGROUP, MobileCynosuralInhibitorBlueprint.METAGROUP, MobileDepotBlueprint.METAGROUP, MobileTractorUnitBlueprint.METAGROUP, EncounterSurveillanceSystemBlueprint.METAGROUP, MobileScanInhibitorBlueprint.METAGROUP, MobileMicroJumpUnitBlueprint.METAGROUP, MobileDecoyUnitBlueprint.METAGROUP, TacticalDestroyerBlueprint.METAGROUP, InfrastructureUpgradeBlueprint.METAGROUP, EntosisLinkBlueprint.METAGROUP, MissileGuidanceEnhancerBlueprint.METAGROUP, MissileGuidanceComputerBlueprint.METAGROUP, StructureBlueprints.METAGROUP, CommandDestroyerBlueprint.METAGROUP, MicroJumpFieldGeneratorBlueprint.METAGROUP, SupportFighterBlueprint.METAGROUP, BurstProjectorBlueprint.METAGROUP, StructureModuleBlueprint.METAGROUP, StructureRigBlueprint.METAGROUP, ForceAuxiliaryBlueprint.METAGROUP, ShieldResistanceShiftHardenerBlueprint.METAGROUP, CommandBurstChargeBlueprint.METAGROUP, CommandBurstBlueprint.METAGROUP, CompositeReactionFormulas.METAGROUP, PolymerReactionFormulas.METAGROUP, BiochemicalReactionFormulas.METAGROUP, CyberScanningImplantBlueprints.METAGROUP, PrecursorWeaponBlueprint.METAGROUP, EntropicRadiationSinkBlueprint.METAGROUP, ExoticPlasmaChargeBlueprint.METAGROUP, AdvancedExoticPlasmaChargeBlueprint.METAGROUP, MassEntanglerBlueprints.METAGROUP, MutadaptiveRemoteArmorRepairerBlueprint.METAGROUP);
        }
    }
}
