package fr.guiguilechat.jcelechat.model.sde.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.AdvancedCapitalConstructionComponentBlueprints;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.AdvancedCondenserPackBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.AdvancedExoticPlasmaChargeBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.AdvancedFrequencyCrystalBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.AdvancedHybridChargeBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.AdvancedProjectileAmmoBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.AntiWarpScramblerBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.ArmorCoatingBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.ArmorHardenerBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.ArmorPlateBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.ArmorRepairUnitBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.ArmorResistanceShiftHardenerBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.AutomatedTargetingSystemBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.AuxiliaryPowerCoreBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.BallisticControlSystemBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.BattlecruiserBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.BattleshipBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.BiochemicalReactionFormulas;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.BombBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.BoosterBlueprints;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.BurstJammerBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.BurstProjectorBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.CapacitorBatteryBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.CapacitorBoosterBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.CapacitorBoosterChargeBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.CapacitorRechargerBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.CapitalConstructionBlueprints;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.CapitalIndustrialShipBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.CargoScannerBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.CarrierBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.CloakingDeviceBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.CoProcessorBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.CombatDroneBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.CommandBurstBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.CommandBurstChargeBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.CommandDestroyerBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.CompositeReactionFormulas;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.CondenserPackBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.ConstructionComponentBlueprints;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.ContainerBlueprints;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.CruiserBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.CyberElectronicsImplantBlueprints;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.CyberScanningImplantBlueprints;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.DamageControlBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.DataMinerBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.DefenderMissileBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.DestroyerBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.DreadnoughtBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.DroneDamageModuleBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.DroneUpgradeBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.DrugBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.ECMBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.ECMStabilizerBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.ElectronicWarfareDroneBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.EliteIndustrialBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.EnergyNeutralizerBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.EnergyNeutralizerDroneBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.EnergyNosferatuBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.EnergyWeaponBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.EntosisLinkBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.EntropicRadiationSinkBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.ExoticPlasmaChargeBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.ForceAuxiliaryBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.FreighterBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.FrequencyCrystalBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.FrigateBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.FuelBlockBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.FueledArmorRepairerBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.FueledShieldBoosterBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.GangCoordinatorBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.GunneryUpgradeBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.HeatSinkBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.HeavyFighterBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.HullModsBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.HullRepairUnitBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.HybridChargeBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.HybridComponentBlueprints;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.HybridWeaponBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.ImplantBlueprints;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.IndustrialBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.IndustrialCommandShipBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.InfrastructureUpgradeBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.JumpFilamentBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.LightFighterBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.LogisticDroneBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.MassEntanglerBlueprints;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.MicroJumpDriveBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.MicroJumpFieldGeneratorBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.MiningBargeBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.MiningCrystalBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.MiningDroneBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.MiningLaserBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.MiningLaserUpgradeBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.MissileBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.MissileGuidanceComputerBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.MissileGuidanceEnhancerBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.MissileLauncherBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.MobileCynosuralBeaconBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.MobileCynosuralInhibitorBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.MobileDepotBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.MobileMicroJumpUnitBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.MobileScanInhibitorBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.MobileSiphonUnitBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.MobileTractorUnitBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.MobileWarpDisruptorBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.MolecularForgedReactionFormulas;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.MutadaptiveRemoteArmorRepairerBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.PassiveTargetingSystemBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.PolymerReactionFormulas;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.PowerManagerBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.PrecursorWeaponBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.ProjectileAmmoBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.ProjectileWeaponBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.PropulsionModuleBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.RemoteArmorRepairerBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.RemoteCapacitorTransmitterBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.RemoteHullRepairerBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.RemoteShieldBoosterBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.RemoteTrackingComputerBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.RigBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.SalvageDroneBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.SalvagerBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.ScanProbeBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.ScanProbeLauncherBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.ScanningUpgradeBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.ScanningUpgradeTimeBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.ScriptBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.SensorBoosterBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.ShieldBoostAmplifierBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.ShieldBoosterBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.ShieldExtenderBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.ShieldHardenerBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.ShieldRechargerBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.ShieldResistanceAmplifierBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.ShieldResistanceShiftHardenerBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.ShipScannerBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.ShuttleBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.SiegeModuleBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.SignalAmplifierBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.SignatureSuppressorBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.SmartBombBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.SovereigntyStructureBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.SpecialEditionCommodityBlueprints;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.StarbaseBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.StarbaseControlTowerBlueprints;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.StarbaseECMJammingArrayBlueprints;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.StarbaseEnergyNeutralizingBatteryBlueprints;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.StarbaseHybridBatteryBlueprints;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.StarbaseLaserBatteryBlueprints;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.StarbaseMissileBatteryBlueprints;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.StarbaseMobileLaboratoryBlueprints;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.StarbaseProjectileBatteryBlueprints;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.StarbaseSensorDampeningArrayBlueprints;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.StarbaseStasisWebificationBatteryBlueprints;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.StarbaseWarpScramblingBatteryBlueprints;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.StasisWebBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.StasisWebifyingDroneBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.StrategicCruiserBlueprints;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.StripMinerBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.StructureBlueprints;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.StructureModuleBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.StructureRigBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.SubsystemBlueprints;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.SuperWeaponBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.SupercarrierBlueprints;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.SupportFighterBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.SurveyProbeBlueprints;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.SurveyProbeLauncherBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.SurveyScannerBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.TacticalDestroyerBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.TargetPainterBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.TitanBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.ToolBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.TrackingComputerBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.TrackingEnhancerBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.TractorBeamBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.VortonProjectorBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.VortonProjectorUpgradeBlueprints;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.WarpScramblerBlueprint;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.WeaponDisruptorBlueprint;

public abstract class Blueprint
    extends EveType
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final Blueprint.MetaCat METACAT = new Blueprint.MetaCat();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

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
            return Arrays.asList(MolecularForgedReactionFormulas.METAGROUP, FrigateBlueprint.METAGROUP, CruiserBlueprint.METAGROUP, BattleshipBlueprint.METAGROUP, IndustrialBlueprint.METAGROUP, TitanBlueprint.METAGROUP, ShuttleBlueprint.METAGROUP, ShieldExtenderBlueprint.METAGROUP, ShieldRechargerBlueprint.METAGROUP, ShieldBoosterBlueprint.METAGROUP, RemoteShieldBoosterBlueprint.METAGROUP, CapacitorRechargerBlueprint.METAGROUP, PropulsionModuleBlueprint.METAGROUP, CargoScannerBlueprint.METAGROUP, ShipScannerBlueprint.METAGROUP, SurveyScannerBlueprint.METAGROUP, ECMBlueprint.METAGROUP, WarpScramblerBlueprint.METAGROUP, EnergyWeaponBlueprint.METAGROUP, MiningLaserBlueprint.METAGROUP, ProjectileWeaponBlueprint.METAGROUP, MissileLauncherBlueprint.METAGROUP, PowerManagerBlueprint.METAGROUP, GunneryUpgradeBlueprint.METAGROUP, DamageControlBlueprint.METAGROUP, CapacitorBatteryBlueprint.METAGROUP, ArmorRepairUnitBlueprint.METAGROUP, HullRepairUnitBlueprint.METAGROUP, StasisWebBlueprint.METAGROUP, RemoteCapacitorTransmitterBlueprint.METAGROUP, EnergyNosferatuBlueprint.METAGROUP, EnergyNeutralizerBlueprint.METAGROUP, SmartBombBlueprint.METAGROUP, HybridWeaponBlueprint.METAGROUP, CapacitorBoosterBlueprint.METAGROUP, ShieldHardenerBlueprint.METAGROUP, HullModsBlueprint.METAGROUP, BurstJammerBlueprint.METAGROUP, PassiveTargetingSystemBlueprint.METAGROUP, AutomatedTargetingSystemBlueprint.METAGROUP, ArmorCoatingBlueprint.METAGROUP, ProjectileAmmoBlueprint.METAGROUP, MissileBlueprint.METAGROUP, HybridChargeBlueprint.METAGROUP, FrequencyCrystalBlueprint.METAGROUP, CapacitorBoosterChargeBlueprint.METAGROUP, DefenderMissileBlueprint.METAGROUP, BombBlueprint.METAGROUP, CombatDroneBlueprint.METAGROUP, MiningDroneBlueprint.METAGROUP, DrugBlueprint.METAGROUP, HeatSinkBlueprint.METAGROUP, SensorBoosterBlueprint.METAGROUP, TrackingComputerBlueprint.METAGROUP, ShieldResistanceAmplifierBlueprint.METAGROUP, AntiWarpScramblerBlueprint.METAGROUP, WeaponDisruptorBlueprint.METAGROUP, TrackingEnhancerBlueprint.METAGROUP, RemoteTrackingComputerBlueprint.METAGROUP, CoProcessorBlueprint.METAGROUP, SignalAmplifierBlueprint.METAGROUP, ArmorHardenerBlueprint.METAGROUP, ArmorPlateBlueprint.METAGROUP, RemoteArmorRepairerBlueprint.METAGROUP, AuxiliaryPowerCoreBlueprint.METAGROUP, ToolBlueprint.METAGROUP, ShieldBoostAmplifierBlueprint.METAGROUP, MobileWarpDisruptorBlueprint.METAGROUP, BallisticControlSystemBlueprint.METAGROUP, CloakingDeviceBlueprint.METAGROUP, DroneUpgradeBlueprint.METAGROUP, ConstructionComponentBlueprints.METAGROUP, MiningBargeBlueprint.METAGROUP, ScanProbeBlueprint.METAGROUP, DestroyerBlueprint.METAGROUP, BattlecruiserBlueprint.METAGROUP, StripMinerBlueprint.METAGROUP, EliteIndustrialBlueprint.METAGROUP, TargetPainterBlueprint.METAGROUP, SiegeModuleBlueprint.METAGROUP, FreighterBlueprint.METAGROUP, GangCoordinatorBlueprint.METAGROUP, DreadnoughtBlueprint.METAGROUP, CarrierBlueprint.METAGROUP, SuperWeaponBlueprint.METAGROUP, BoosterBlueprints.METAGROUP, AdvancedHybridChargeBlueprint.METAGROUP, TractorBeamBlueprint.METAGROUP, ImplantBlueprints.METAGROUP, AdvancedProjectileAmmoBlueprint.METAGROUP, AdvancedFrequencyCrystalBlueprint.METAGROUP, MiningCrystalBlueprint.METAGROUP, RigBlueprint.METAGROUP, StarbaseControlTowerBlueprints.METAGROUP, StarbaseLaserBatteryBlueprints.METAGROUP, StarbaseProjectileBatteryBlueprints.METAGROUP, StarbaseHybridBatteryBlueprints.METAGROUP, StarbaseECMJammingArrayBlueprints.METAGROUP, StarbaseWarpScramblingBatteryBlueprints.METAGROUP, StarbaseStasisWebificationBatteryBlueprints.METAGROUP, StarbaseSensorDampeningArrayBlueprints.METAGROUP, StarbaseEnergyNeutralizingBatteryBlueprints.METAGROUP, RemoteHullRepairerBlueprint.METAGROUP, StarbaseMissileBatteryBlueprints.METAGROUP, StarbaseMobileLaboratoryBlueprints.METAGROUP, ScriptBlueprint.METAGROUP, AdvancedCapitalConstructionComponentBlueprints.METAGROUP, CapitalConstructionBlueprints.METAGROUP, DataMinerBlueprint.METAGROUP, ScanProbeLauncherBlueprint.METAGROUP, CapitalIndustrialShipBlueprint.METAGROUP, IndustrialCommandShipBlueprint.METAGROUP, HybridComponentBlueprints.METAGROUP, SubsystemBlueprints.METAGROUP, StrategicCruiserBlueprints.METAGROUP, SupercarrierBlueprints.METAGROUP, SovereigntyStructureBlueprint.METAGROUP, StarbaseBlueprint.METAGROUP, SalvagerBlueprint.METAGROUP, FuelBlockBlueprint.METAGROUP, MiningLaserUpgradeBlueprint.METAGROUP, EnergyNeutralizerDroneBlueprint.METAGROUP, ElectronicWarfareDroneBlueprint.METAGROUP, LogisticDroneBlueprint.METAGROUP, HeavyFighterBlueprint.METAGROUP, LightFighterBlueprint.METAGROUP, StasisWebifyingDroneBlueprint.METAGROUP, ArmorResistanceShiftHardenerBlueprint.METAGROUP, DroneDamageModuleBlueprint.METAGROUP, SignatureSuppressorBlueprint.METAGROUP, FueledShieldBoosterBlueprint.METAGROUP, SurveyProbeBlueprints.METAGROUP, ContainerBlueprints.METAGROUP, SalvageDroneBlueprint.METAGROUP, MicroJumpDriveBlueprint.METAGROUP, SpecialEditionCommodityBlueprints.METAGROUP, FueledArmorRepairerBlueprint.METAGROUP, ECMStabilizerBlueprint.METAGROUP, ScanningUpgradeBlueprint.METAGROUP, SurveyProbeLauncherBlueprint.METAGROUP, ScanningUpgradeTimeBlueprint.METAGROUP, MobileSiphonUnitBlueprint.METAGROUP, MobileCynosuralInhibitorBlueprint.METAGROUP, MobileDepotBlueprint.METAGROUP, MobileTractorUnitBlueprint.METAGROUP, MobileScanInhibitorBlueprint.METAGROUP, MobileMicroJumpUnitBlueprint.METAGROUP, TacticalDestroyerBlueprint.METAGROUP, InfrastructureUpgradeBlueprint.METAGROUP, EntosisLinkBlueprint.METAGROUP, MissileGuidanceEnhancerBlueprint.METAGROUP, MissileGuidanceComputerBlueprint.METAGROUP, StructureBlueprints.METAGROUP, CommandDestroyerBlueprint.METAGROUP, MicroJumpFieldGeneratorBlueprint.METAGROUP, SupportFighterBlueprint.METAGROUP, BurstProjectorBlueprint.METAGROUP, StructureModuleBlueprint.METAGROUP, StructureRigBlueprint.METAGROUP, ForceAuxiliaryBlueprint.METAGROUP, ShieldResistanceShiftHardenerBlueprint.METAGROUP, CommandBurstChargeBlueprint.METAGROUP, CommandBurstBlueprint.METAGROUP, CompositeReactionFormulas.METAGROUP, PolymerReactionFormulas.METAGROUP, BiochemicalReactionFormulas.METAGROUP, CyberScanningImplantBlueprints.METAGROUP, PrecursorWeaponBlueprint.METAGROUP, EntropicRadiationSinkBlueprint.METAGROUP, ExoticPlasmaChargeBlueprint.METAGROUP, AdvancedExoticPlasmaChargeBlueprint.METAGROUP, JumpFilamentBlueprint.METAGROUP, MassEntanglerBlueprints.METAGROUP, VortonProjectorBlueprint.METAGROUP, CondenserPackBlueprint.METAGROUP, AdvancedCondenserPackBlueprint.METAGROUP, MutadaptiveRemoteArmorRepairerBlueprint.METAGROUP, VortonProjectorUpgradeBlueprints.METAGROUP, CyberElectronicsImplantBlueprints.METAGROUP, MobileCynosuralBeaconBlueprint.METAGROUP);
        }
    }
}
