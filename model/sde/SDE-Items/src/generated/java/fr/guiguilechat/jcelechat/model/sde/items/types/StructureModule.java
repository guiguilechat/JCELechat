package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.OLDStructureResourceRigLIceReprocessing;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.OLDStructureResourceRigLOreReprocessing;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.OLDStructureResourceRigMHSOreReprocessing;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.OLDStructureResourceRigMIce1Reprocessing;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.OLDStructureResourceRigMIce2Reprocessing;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.OLDStructureResourceRigMLNSOreReprocessing;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.OLDStructureResourceRigXLReprocessing;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.OutpostConversionRigs;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureAdministrationServiceModule;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureAdvertisementServiceModule;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureAreaDenialModule;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureArmorReinforcer;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureAssemblyRigLSubsystem;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureBiochemicalReactorRigMME;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureBiochemicalReactorRigMTE;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureBurstProjector;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureCapacitorBattery;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureCapacitorPowerRelay;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureCitadelRigLDroneHitpointsAndSpeed;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureCitadelRigMDroneHitpoints;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureCitadelRigMDroneSpeed;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureCitadelRigXLDroneAndPDB;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureCitadelServiceModule;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureCombatRigLAoELauncherApplicationAndProjection;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureCombatRigLEWProjectionAndCapReduction;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureCombatRigLEnergyNeutralizerProjectionAndCapReduction;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureCombatRigLMaxTargetsAndSensorBoosting;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureCombatRigLMissileApplicationAndProjection;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureCombatRigLPointDefenseBatteryApplicationAndProjection;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureCombatRigMBoostedSensors;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureCombatRigMEWCapReduction;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureCombatRigMEWProjection;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureCombatRigMEnergyNeutralizerCapReduction;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureCombatRigMEnergyNeutralizerProjection;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureCombatRigMMaxTargets;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureCombatRigMMissileApplication;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureCombatRigMMissileProjection;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureCombatRigXLDoomsdayAndTargeting;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureCombatRigXLEnergyNeutralizerAndEW;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureCombatRigXLMissileAndAoEMissile;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureCompositeReactorRigMME;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureCompositeReactorRigMTE;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureDisruptionBattery;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureDoomsdayWeapon;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureDrillingRigLProficiency;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureDrillingRigMEfficiency;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureDrillingRigMStability;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureECMBattery;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureEnergyNeutralizer;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureEngineeringRigLAdvancedComponentEfficiency;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureEngineeringRigLAdvancedLargeShipEfficiency;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureEngineeringRigLAdvancedMediumShipEfficiency;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureEngineeringRigLAdvancedSmallShipEfficiency;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureEngineeringRigLAmmunitionEfficiency;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureEngineeringRigLBasicCapitalComponentEfficiency;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureEngineeringRigLBasicLargeShipEfficiency;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureEngineeringRigLBasicMediumShipEfficiency;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureEngineeringRigLBasicSmallShipEfficiency;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureEngineeringRigLBlueprintCopyOptimization;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureEngineeringRigLCapitalShipEfficiency;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureEngineeringRigLDroneAndFighterEfficiency;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureEngineeringRigLEquipmentEfficiency;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureEngineeringRigLInventionOptimization;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureEngineeringRigLMEResearchOptimization;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureEngineeringRigLStructureEfficiency;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureEngineeringRigLTEResearchOptimization;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureEngineeringRigMAdvancedComponentME;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureEngineeringRigMAdvancedComponentTE;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureEngineeringRigMAdvancedLargeShipME;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureEngineeringRigMAdvancedLargeShipTE;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureEngineeringRigMAdvancedMediumShipME;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureEngineeringRigMAdvancedMediumShipTE;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureEngineeringRigMAdvancedSmallShipME;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureEngineeringRigMAdvancedSmallShipTE;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureEngineeringRigMAmmunitionME;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureEngineeringRigMAmmunitionTE;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureEngineeringRigMBasicCapitalComponentME;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureEngineeringRigMBasicCapitalComponentTE;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureEngineeringRigMBasicLargeShipME;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureEngineeringRigMBasicLargeShipTE;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureEngineeringRigMBasicMediumShipME;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureEngineeringRigMBasicMediumShipTE;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureEngineeringRigMBasicSmallShipME;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureEngineeringRigMBasicSmallShipTE;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureEngineeringRigMBlueprintCopyAccelerator;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureEngineeringRigMBlueprintCopyCostOptimization;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureEngineeringRigMDroneAndFighterME;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureEngineeringRigMDroneAndFighterTE;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureEngineeringRigMEquipmentME;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureEngineeringRigMEquipmentTE;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureEngineeringRigMInventionAccelerator;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureEngineeringRigMInventionCostOptimization;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureEngineeringRigMMEResearchAccelerator;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureEngineeringRigMMEResearchCostOptimization;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureEngineeringRigMStructureME;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureEngineeringRigMStructureTE;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureEngineeringRigMTEResearchAccelerator;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureEngineeringRigMTEResearchCostOptimization;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureEngineeringRigXLEquipmentAndConsumableEfficiency;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureEngineeringRigXLLaboratoryOptimization;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureEngineeringRigXLShipEfficiency;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureEngineeringRigXLStructureAndComponentEfficiency;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureEngineeringServiceModule;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureFestivalLauncher;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureFittingModule;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureGuidedBombLauncher;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureHybridReactorRigMME;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureHybridReactorRigMTE;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureLaboratoryRigLCapitalComponent;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureLaboratoryRigLComponent;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureLaboratoryRigLModuleConsumableDrone;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureLaboratoryRigLShip;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureLaboratoryRigLStructureComponent;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureLaboratoryRigLSubsystem;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureLaboratoryRigMConsumable;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureLaboratoryRigMDrone;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureLaboratoryRigMLargeShip;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureLaboratoryRigMMediumShip;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureLaboratoryRigMModule;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureLaboratoryRigMSmallShip;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureLaboratoryRigXLAllComponents;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureLaboratoryRigXLEquipment;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureLaboratoryRigXLShip;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureMoonDrillingServiceModule;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureMultiroleMissileLauncher;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureObservatoryServiceModule;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureQAModules;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureReactorRigLEfficiency;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureResearchServiceModule;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureResourceProcessingServiceModule;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureResourceRigLReprocessing;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureResourceRigMAsteroidOreReprocessing;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureResourceRigMIceReprocessing;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureResourceRigMMoonOreReprocessing;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureResourceRigXLReprocessing;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureSignalAmplifier;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureStargateServiceModule;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureStasisWebifier;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureWarpScrambler;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureWeaponUpgrade;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureXLMissileLauncher;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.UnpublishedStructureModules;

public abstract class StructureModule
    extends Item
{

    @Override
    public int getCategoryId() {
        return  66;
    }

    @Override
    public Class<?> getCategory() {
        return StructureModule.class;
    }

    public static Map<String, ? extends StructureModule> loadCategory() {
        return Stream.of(OLDStructureResourceRigLIceReprocessing.load(), OLDStructureResourceRigLOreReprocessing.load(), OLDStructureResourceRigMHSOreReprocessing.load(), OLDStructureResourceRigMIce1Reprocessing.load(), OLDStructureResourceRigMIce2Reprocessing.load(), OLDStructureResourceRigMLNSOreReprocessing.load(), OLDStructureResourceRigXLReprocessing.load(), OutpostConversionRigs.load(), StructureAdministrationServiceModule.load(), StructureAdvertisementServiceModule.load(), StructureAreaDenialModule.load(), StructureArmorReinforcer.load(), StructureAssemblyRigLSubsystem.load(), StructureBiochemicalReactorRigMME.load(), StructureBiochemicalReactorRigMTE.load(), StructureBurstProjector.load(), StructureCapacitorBattery.load(), StructureCapacitorPowerRelay.load(), StructureCitadelRigLDroneHitpointsAndSpeed.load(), StructureCitadelRigMDroneHitpoints.load(), StructureCitadelRigMDroneSpeed.load(), StructureCitadelRigXLDroneAndPDB.load(), StructureCitadelServiceModule.load(), StructureCombatRigLAoELauncherApplicationAndProjection.load(), StructureCombatRigLEWProjectionAndCapReduction.load(), StructureCombatRigLEnergyNeutralizerProjectionAndCapReduction.load(), StructureCombatRigLMaxTargetsAndSensorBoosting.load(), StructureCombatRigLMissileApplicationAndProjection.load(), StructureCombatRigLPointDefenseBatteryApplicationAndProjection.load(), StructureCombatRigMBoostedSensors.load(), StructureCombatRigMEWCapReduction.load(), StructureCombatRigMEWProjection.load(), StructureCombatRigMEnergyNeutralizerCapReduction.load(), StructureCombatRigMEnergyNeutralizerProjection.load(), StructureCombatRigMMaxTargets.load(), StructureCombatRigMMissileApplication.load(), StructureCombatRigMMissileProjection.load(), StructureCombatRigXLDoomsdayAndTargeting.load(), StructureCombatRigXLEnergyNeutralizerAndEW.load(), StructureCombatRigXLMissileAndAoEMissile.load(), StructureCompositeReactorRigMME.load(), StructureCompositeReactorRigMTE.load(), StructureDisruptionBattery.load(), StructureDoomsdayWeapon.load(), StructureDrillingRigLProficiency.load(), StructureDrillingRigMEfficiency.load(), StructureDrillingRigMStability.load(), StructureECMBattery.load(), StructureEnergyNeutralizer.load(), StructureEngineeringRigLAdvancedComponentEfficiency.load(), StructureEngineeringRigLAdvancedLargeShipEfficiency.load(), StructureEngineeringRigLAdvancedMediumShipEfficiency.load(), StructureEngineeringRigLAdvancedSmallShipEfficiency.load(), StructureEngineeringRigLAmmunitionEfficiency.load(), StructureEngineeringRigLBasicCapitalComponentEfficiency.load(), StructureEngineeringRigLBasicLargeShipEfficiency.load(), StructureEngineeringRigLBasicMediumShipEfficiency.load(), StructureEngineeringRigLBasicSmallShipEfficiency.load(), StructureEngineeringRigLBlueprintCopyOptimization.load(), StructureEngineeringRigLCapitalShipEfficiency.load(), StructureEngineeringRigLDroneAndFighterEfficiency.load(), StructureEngineeringRigLEquipmentEfficiency.load(), StructureEngineeringRigLInventionOptimization.load(), StructureEngineeringRigLMEResearchOptimization.load(), StructureEngineeringRigLStructureEfficiency.load(), StructureEngineeringRigLTEResearchOptimization.load(), StructureEngineeringRigMAdvancedComponentME.load(), StructureEngineeringRigMAdvancedComponentTE.load(), StructureEngineeringRigMAdvancedLargeShipME.load(), StructureEngineeringRigMAdvancedLargeShipTE.load(), StructureEngineeringRigMAdvancedMediumShipME.load(), StructureEngineeringRigMAdvancedMediumShipTE.load(), StructureEngineeringRigMAdvancedSmallShipME.load(), StructureEngineeringRigMAdvancedSmallShipTE.load(), StructureEngineeringRigMAmmunitionME.load(), StructureEngineeringRigMAmmunitionTE.load(), StructureEngineeringRigMBasicCapitalComponentME.load(), StructureEngineeringRigMBasicCapitalComponentTE.load(), StructureEngineeringRigMBasicLargeShipME.load(), StructureEngineeringRigMBasicLargeShipTE.load(), StructureEngineeringRigMBasicMediumShipME.load(), StructureEngineeringRigMBasicMediumShipTE.load(), StructureEngineeringRigMBasicSmallShipME.load(), StructureEngineeringRigMBasicSmallShipTE.load(), StructureEngineeringRigMBlueprintCopyAccelerator.load(), StructureEngineeringRigMBlueprintCopyCostOptimization.load(), StructureEngineeringRigMDroneAndFighterME.load(), StructureEngineeringRigMDroneAndFighterTE.load(), StructureEngineeringRigMEquipmentME.load(), StructureEngineeringRigMEquipmentTE.load(), StructureEngineeringRigMInventionAccelerator.load(), StructureEngineeringRigMInventionCostOptimization.load(), StructureEngineeringRigMMEResearchAccelerator.load(), StructureEngineeringRigMMEResearchCostOptimization.load(), StructureEngineeringRigMStructureME.load(), StructureEngineeringRigMStructureTE.load(), StructureEngineeringRigMTEResearchAccelerator.load(), StructureEngineeringRigMTEResearchCostOptimization.load(), StructureEngineeringRigXLEquipmentAndConsumableEfficiency.load(), StructureEngineeringRigXLLaboratoryOptimization.load(), StructureEngineeringRigXLShipEfficiency.load(), StructureEngineeringRigXLStructureAndComponentEfficiency.load(), StructureEngineeringServiceModule.load(), StructureFestivalLauncher.load(), StructureFittingModule.load(), StructureGuidedBombLauncher.load(), StructureHybridReactorRigMME.load(), StructureHybridReactorRigMTE.load(), StructureLaboratoryRigLCapitalComponent.load(), StructureLaboratoryRigLComponent.load(), StructureLaboratoryRigLModuleConsumableDrone.load(), StructureLaboratoryRigLShip.load(), StructureLaboratoryRigLStructureComponent.load(), StructureLaboratoryRigLSubsystem.load(), StructureLaboratoryRigMConsumable.load(), StructureLaboratoryRigMDrone.load(), StructureLaboratoryRigMLargeShip.load(), StructureLaboratoryRigMMediumShip.load(), StructureLaboratoryRigMModule.load(), StructureLaboratoryRigMSmallShip.load(), StructureLaboratoryRigXLAllComponents.load(), StructureLaboratoryRigXLEquipment.load(), StructureLaboratoryRigXLShip.load(), StructureMoonDrillingServiceModule.load(), StructureMultiroleMissileLauncher.load(), StructureObservatoryServiceModule.load(), StructureQAModules.load(), StructureReactorRigLEfficiency.load(), StructureResearchServiceModule.load(), StructureResourceProcessingServiceModule.load(), StructureResourceRigLReprocessing.load(), StructureResourceRigMAsteroidOreReprocessing.load(), StructureResourceRigMIceReprocessing.load(), StructureResourceRigMMoonOreReprocessing.load(), StructureResourceRigXLReprocessing.load(), StructureSignalAmplifier.load(), StructureStargateServiceModule.load(), StructureStasisWebifier.load(), StructureWarpScrambler.load(), StructureWeaponUpgrade.load(), StructureXLMissileLauncher.load(), UnpublishedStructureModules.load()).flatMap((m -> m.entrySet().stream())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
