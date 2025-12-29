package fr.guiguilechat.jcelechat.model.sde.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.blueprint.*;

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
            return Arrays.asList(MolecularForgedReactionFormulas.METAGROUP, MobileObservatoryBlueprint.METAGROUP, InterdictionNullifierBlueprint.METAGROUP, GasHarvesterBlueprint.METAGROUP, MobileAnalysisBeaconBlueprint.METAGROUP, CompressorsBlueprints.METAGROUP, StructureAreaDenialAmmoBlueprint.METAGROUP, FrigateBlueprint.METAGROUP, CruiserBlueprint.METAGROUP, BattleshipBlueprint.METAGROUP, HaulerBlueprint.METAGROUP, TitanBlueprint.METAGROUP, ShuttleBlueprint.METAGROUP, ShieldExtenderBlueprint.METAGROUP, ShieldRechargerBlueprint.METAGROUP, ShieldBoosterBlueprint.METAGROUP, RemoteShieldBoosterBlueprint.METAGROUP, CapacitorRechargerBlueprint.METAGROUP, PropulsionModuleBlueprint.METAGROUP, CargoScannerBlueprint.METAGROUP, ShipScannerBlueprint.METAGROUP, MiningSurveyChipsetBlueprint.METAGROUP, ECMBlueprint.METAGROUP, WarpScramblerBlueprint.METAGROUP, EnergyWeaponBlueprint.METAGROUP, MiningLaserBlueprint.METAGROUP, ProjectileWeaponBlueprint.METAGROUP, MissileLauncherBlueprint.METAGROUP, PowerManagerBlueprint.METAGROUP, GunneryUpgradeBlueprint.METAGROUP, DamageControlBlueprint.METAGROUP, CapacitorBatteryBlueprint.METAGROUP, ArmorRepairUnitBlueprint.METAGROUP, HullRepairUnitBlueprint.METAGROUP, StasisWebBlueprint.METAGROUP, RemoteCapacitorTransmitterBlueprint.METAGROUP, EnergyNosferatuBlueprint.METAGROUP, EnergyNeutralizerBlueprint.METAGROUP, SmartBombBlueprint.METAGROUP, HybridWeaponBlueprint.METAGROUP, CapacitorBoosterBlueprint.METAGROUP, ShieldHardenerBlueprint.METAGROUP, HullModsBlueprint.METAGROUP, BurstJammerBlueprint.METAGROUP, PassiveTargetingSystemBlueprint.METAGROUP, AutomatedTargetingSystemBlueprint.METAGROUP, ArmorCoatingBlueprint.METAGROUP, ProjectileAmmoBlueprint.METAGROUP, MissileBlueprint.METAGROUP, HybridChargeBlueprint.METAGROUP, FrequencyCrystalBlueprint.METAGROUP, CapacitorBoosterChargeBlueprint.METAGROUP, DefenderMissileBlueprint.METAGROUP, BombBlueprint.METAGROUP, CombatDroneBlueprint.METAGROUP, MiningDroneBlueprint.METAGROUP, DrugBlueprint.METAGROUP, HeatSinkBlueprint.METAGROUP, SensorBoosterBlueprint.METAGROUP, TrackingComputerBlueprint.METAGROUP, ShieldResistanceAmplifierBlueprint.METAGROUP, AntiWarpScramblerBlueprint.METAGROUP, WeaponDisruptorBlueprint.METAGROUP, TrackingEnhancerBlueprint.METAGROUP, RemoteTrackingComputerBlueprint.METAGROUP, CoProcessorBlueprint.METAGROUP, SignalAmplifierBlueprint.METAGROUP, ArmorHardenerBlueprint.METAGROUP, ArmorPlateBlueprint.METAGROUP, RemoteArmorRepairerBlueprint.METAGROUP, AuxiliaryPowerCoreBlueprint.METAGROUP, ToolBlueprint.METAGROUP, ShieldBoostAmplifierBlueprint.METAGROUP, MobileWarpDisruptorBlueprint.METAGROUP, BallisticControlSystemBlueprint.METAGROUP, CloakingDeviceBlueprint.METAGROUP, DroneUpgradeBlueprint.METAGROUP, ConstructionComponentBlueprints.METAGROUP, MiningBargeBlueprint.METAGROUP, ScanProbeBlueprint.METAGROUP, DestroyerBlueprint.METAGROUP, BattlecruiserBlueprint.METAGROUP, StripMinerBlueprint.METAGROUP, EliteHaulerBlueprint.METAGROUP, TargetPainterBlueprint.METAGROUP, SiegeModuleBlueprint.METAGROUP, FreighterBlueprint.METAGROUP, GangCoordinatorBlueprint.METAGROUP, DreadnoughtBlueprint.METAGROUP, CarrierBlueprint.METAGROUP, SuperWeaponBlueprint.METAGROUP, MercenaryDenBlueprint.METAGROUP, BoosterBlueprints.METAGROUP, AdvancedHybridChargeBlueprint.METAGROUP, TractorBeamBlueprint.METAGROUP, ImplantBlueprints.METAGROUP, MutaplasmidBlueprint.METAGROUP, AdvancedProjectileAmmoBlueprint.METAGROUP, AdvancedFrequencyCrystalBlueprint.METAGROUP, MiningCrystalBlueprint.METAGROUP, RigBlueprint.METAGROUP, ExpeditionCommandShipBlueprint.METAGROUP, MobilePhaseAnchorBlueprint.METAGROUP, StarbaseControlTowerBlueprints.METAGROUP, StarbaseLaserBatteryBlueprints.METAGROUP, StarbaseProjectileBatteryBlueprints.METAGROUP, StarbaseHybridBatteryBlueprints.METAGROUP, StarbaseECMJammingArrayBlueprints.METAGROUP, StarbaseWarpScramblingBatteryBlueprints.METAGROUP, StarbaseStasisWebificationBatteryBlueprints.METAGROUP, StarbaseSensorDampeningArrayBlueprints.METAGROUP, StarbaseEnergyNeutralizingBatteryBlueprints.METAGROUP, RemoteHullRepairerBlueprint.METAGROUP, StarbaseMissileBatteryBlueprints.METAGROUP, StarbaseMobileLaboratoryBlueprints.METAGROUP, ScriptBlueprint.METAGROUP, AdvancedCapitalConstructionComponentBlueprints.METAGROUP, CapitalConstructionBlueprints.METAGROUP, DataMinerBlueprint.METAGROUP, ScanProbeLauncherBlueprint.METAGROUP, CapitalIndustrialShipBlueprint.METAGROUP, IndustrialCommandShipBlueprint.METAGROUP, HybridComponentBlueprints.METAGROUP, SubsystemBlueprints.METAGROUP, StrategicCruiserBlueprints.METAGROUP, SupercarrierBlueprints.METAGROUP, SovereigntyStructureBlueprint.METAGROUP, StarbaseBlueprint.METAGROUP, SalvagerBlueprint.METAGROUP, FuelBlockBlueprint.METAGROUP, MiningLaserUpgradeBlueprint.METAGROUP, EnergyNeutralizerDroneBlueprint.METAGROUP, ElectronicWarfareDroneBlueprint.METAGROUP, LogisticDroneBlueprint.METAGROUP, HeavyFighterBlueprint.METAGROUP, LightFighterBlueprint.METAGROUP, StasisWebifyingDroneBlueprint.METAGROUP, ArmorResistanceShiftHardenerBlueprint.METAGROUP, DroneDamageModuleBlueprint.METAGROUP, SignatureSuppressorBlueprint.METAGROUP, FueledShieldBoosterBlueprint.METAGROUP, SurveyProbeBlueprints.METAGROUP, ContainerBlueprints.METAGROUP, SalvageDroneBlueprint.METAGROUP, MicroJumpDriveBlueprint.METAGROUP, SpecialEditionCommodityBlueprints.METAGROUP, FueledArmorRepairerBlueprint.METAGROUP, ECMStabilizerBlueprint.METAGROUP, ScanningUpgradeBlueprint.METAGROUP, SurveyProbeLauncherBlueprint.METAGROUP, ScanningUpgradeTimeBlueprint.METAGROUP, MobileSiphonUnitBlueprint.METAGROUP, MobileCynosuralInhibitorBlueprint.METAGROUP, MobileDepotBlueprint.METAGROUP, MobileTractorUnitBlueprint.METAGROUP, MobileScanInhibitorBlueprint.METAGROUP, MobileMicroJumpUnitBlueprint.METAGROUP, TacticalDestroyerBlueprint.METAGROUP, InfrastructureUpgradeBlueprint.METAGROUP, EntosisLinkBlueprint.METAGROUP, MissileGuidanceEnhancerBlueprint.METAGROUP, MissileGuidanceComputerBlueprint.METAGROUP, StructureBlueprints.METAGROUP, CommandDestroyerBlueprint.METAGROUP, MicroJumpFieldGeneratorBlueprint.METAGROUP, SupportFighterBlueprint.METAGROUP, BurstProjectorBlueprint.METAGROUP, StructureModuleBlueprint.METAGROUP, StructureRigBlueprint.METAGROUP, ForceAuxiliaryBlueprint.METAGROUP, ShieldResistanceShiftHardenerBlueprint.METAGROUP, CommandBurstChargeBlueprint.METAGROUP, CommandBurstBlueprint.METAGROUP, CompositeReactionFormulas.METAGROUP, PolymerReactionFormulas.METAGROUP, BiochemicalReactionFormulas.METAGROUP, CyberScanningImplantBlueprints.METAGROUP, PrecursorWeaponBlueprint.METAGROUP, EntropicRadiationSinkBlueprint.METAGROUP, ExoticPlasmaChargeBlueprint.METAGROUP, AdvancedExoticPlasmaChargeBlueprint.METAGROUP, MassEntanglerBlueprints.METAGROUP, MutadaptiveRemoteArmorRepairerBlueprint.METAGROUP, CyberElectronicsImplantBlueprints.METAGROUP, JumpFilamentBlueprint.METAGROUP, VortonProjectorBlueprint.METAGROUP, CondenserPackBlueprint.METAGROUP, AdvancedCondenserPackBlueprint.METAGROUP, VortonProjectorUpgradeBlueprints.METAGROUP, MobileCynosuralBeaconBlueprint.METAGROUP);
        }
    }
}
