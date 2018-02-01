package fr.guiguilechat.eveonline.model.sde.items.types;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import fr.guiguilechat.eveonline.model.sde.items.Item;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.OLDStructureResourceRigLIceReprocessing;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.OLDStructureResourceRigLOreReprocessing;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.OLDStructureResourceRigMHSOreReprocessing;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.OLDStructureResourceRigMIce1Reprocessing;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.OLDStructureResourceRigMIce2Reprocessing;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.OLDStructureResourceRigMLNSOreReprocessing;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.OLDStructureResourceRigXLReprocessing;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureASMLMissileLauncher;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureAXLMissileLauncher;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureAdministrationServiceModule;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureAdvertisementServiceModule;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureAreaDenialModule;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureAssemblyRigLSubsystem;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureBiochemicalReactorRigMME;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureBiochemicalReactorRigMTE;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureBurstProjector;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureCitadelRigLDroneHitpointsAndSpeed;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureCitadelRigMDroneHitpoints;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureCitadelRigMDroneSpeed;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureCitadelRigXLDroneAndPDB;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureCitadelServiceModule;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureCombatRigLAoELauncherApplicationAndProjection;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureCombatRigLEWProjectionAndCapReduction;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureCombatRigLEnergyNeutralizerProjectionAndCapReduction;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureCombatRigLMaxTargetsAndSensorResolution;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureCombatRigLMissileApplicationAndProjection;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureCombatRigLPointDefenseBatteryApplicationAndProjection;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureCombatRigMEWCapReduction;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureCombatRigMEWProjection;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureCombatRigMEnergyNeutralizerCapReduction;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureCombatRigMEnergyNeutralizerProjection;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureCombatRigMMaxTargets;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureCombatRigMMissileApplication;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureCombatRigMMissileProjection;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureCombatRigMSensorResolution;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureCombatRigXLDoomsdayAndTargeting;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureCombatRigXLEnergyNeutralizerAndEW;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureCombatRigXLMissileAndAoEMissile;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureCompositeReactorRigMME;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureCompositeReactorRigMTE;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureDisruptionBattery;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureDoomsdayWeapon;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureDrillingRigLProficiency;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureDrillingRigMEfficiency;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureDrillingRigMStability;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureECMBattery;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureEnergyNeutralizer;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureEngineeringRigLAdvancedComponentEfficiency;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureEngineeringRigLAdvancedLargeShipEfficiency;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureEngineeringRigLAdvancedMediumShipEfficiency;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureEngineeringRigLAdvancedSmallShipEfficiency;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureEngineeringRigLAmmunitionEfficiency;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureEngineeringRigLBasicCapitalComponentEfficiency;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureEngineeringRigLBasicLargeShipEfficiency;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureEngineeringRigLBasicMediumShipEfficiency;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureEngineeringRigLBasicSmallShipEfficiency;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureEngineeringRigLBlueprintCopyOptimization;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureEngineeringRigLCapitalShipEfficiency;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureEngineeringRigLDroneAndFighterEfficiency;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureEngineeringRigLEquipmentEfficiency;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureEngineeringRigLInventionOptimization;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureEngineeringRigLMEResearchOptimization;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureEngineeringRigLStructureEfficiency;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureEngineeringRigLTEResearchOptimization;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureEngineeringRigMAdvancedComponentME;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureEngineeringRigMAdvancedComponentTE;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureEngineeringRigMAdvancedLargeShipME;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureEngineeringRigMAdvancedLargeShipTE;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureEngineeringRigMAdvancedMediumShipME;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureEngineeringRigMAdvancedMediumShipTE;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureEngineeringRigMAdvancedSmallShipME;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureEngineeringRigMAdvancedSmallShipTE;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureEngineeringRigMAmmunitionME;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureEngineeringRigMAmmunitionTE;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureEngineeringRigMBasicCapitalComponentME;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureEngineeringRigMBasicCapitalComponentTE;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureEngineeringRigMBasicLargeShipME;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureEngineeringRigMBasicLargeShipTE;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureEngineeringRigMBasicMediumShipME;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureEngineeringRigMBasicMediumShipTE;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureEngineeringRigMBasicSmallShipME;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureEngineeringRigMBasicSmallShipTE;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureEngineeringRigMBlueprintCopyAccelerator;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureEngineeringRigMBlueprintCopyCostOptimization;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureEngineeringRigMDroneAndFighterME;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureEngineeringRigMDroneAndFighterTE;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureEngineeringRigMEquipmentME;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureEngineeringRigMEquipmentTE;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureEngineeringRigMInventionAccelerator;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureEngineeringRigMInventionCostOptimization;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureEngineeringRigMMEResearchAccelerator;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureEngineeringRigMMEResearchCostOptimization;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureEngineeringRigMStructureME;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureEngineeringRigMStructureTE;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureEngineeringRigMTEResearchAccelerator;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureEngineeringRigMTEResearchCostOptimization;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureEngineeringRigXLEquipmentAndConsumableEfficiency;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureEngineeringRigXLLaboratoryOptimization;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureEngineeringRigXLShipEfficiency;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureEngineeringRigXLStructureAndComponentEfficiency;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureEngineeringServiceModule;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureFittingModule;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureGuidedBombLauncher;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureHybridReactorRigMME;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureHybridReactorRigMTE;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureLaboratoryRigLCapitalComponent;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureLaboratoryRigLComponent;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureLaboratoryRigLModuleConsumableDrone;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureLaboratoryRigLShip;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureLaboratoryRigLStructureComponent;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureLaboratoryRigLSubsystem;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureLaboratoryRigMConsumable;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureLaboratoryRigMDrone;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureLaboratoryRigMLargeShip;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureLaboratoryRigMMediumShip;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureLaboratoryRigMModule;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureLaboratoryRigMSmallShip;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureLaboratoryRigXLAllComponents;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureLaboratoryRigXLEquipment;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureLaboratoryRigXLShip;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureMoonDrillingServiceModule;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureObservatoryServiceModule;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureReactorRigLEfficiency;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureResearchServiceModule;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureResourceProcessingServiceModule;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureResourceRigLReprocessing;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureResourceRigMAsteroidOreReprocessing;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureResourceRigMIceReprocessing;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureResourceRigMMoonOreReprocessing;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureResourceRigXLReprocessing;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureSignalAmplifier;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureStargateServiceModule;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureStasisWebifier;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureWarpScrambler;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.StructureWeaponUpgrade;
import fr.guiguilechat.eveonline.model.sde.items.types.structuremodule.UnpublishedStructureModules;

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
        return Stream.of(StructureCitadelServiceModule.load(), StructureStargateServiceModule.load(), StructureEngineeringRigMEquipmentME.load(), StructureEngineeringRigMBlueprintCopyAccelerator.load(), StructureCitadelRigXLDroneAndPDB.load(), StructureEngineeringRigMBasicMediumShipME.load(), StructureEngineeringRigLBasicMediumShipEfficiency.load(), StructureEngineeringRigLAdvancedMediumShipEfficiency.load(), StructureGuidedBombLauncher.load(), StructureCombatRigMMaxTargets.load(), StructureCitadelRigLDroneHitpointsAndSpeed.load(), StructureLaboratoryRigXLShip.load(), StructureBiochemicalReactorRigMME.load(), StructureEngineeringRigMBasicLargeShipME.load(), StructureReactorRigLEfficiency.load(), StructureLaboratoryRigLComponent.load(), StructureEngineeringRigMDroneAndFighterME.load(), StructureEngineeringRigMInventionAccelerator.load(), StructureCombatRigLMaxTargetsAndSensorResolution.load(), UnpublishedStructureModules.load(), StructureStasisWebifier.load(), StructureEngineeringRigMBasicCapitalComponentTE.load(), StructureLaboratoryRigMLargeShip.load(), StructureEngineeringRigLBasicSmallShipEfficiency.load(), StructureCombatRigXLMissileAndAoEMissile.load(), StructureEngineeringRigMMEResearchAccelerator.load(), StructureEnergyNeutralizer.load(), StructureResourceRigMAsteroidOreReprocessing.load(), StructureDisruptionBattery.load(), StructureEngineeringRigMBasicSmallShipTE.load(), OLDStructureResourceRigXLReprocessing.load(), OLDStructureResourceRigMHSOreReprocessing.load(), StructureEngineeringServiceModule.load(), StructureEngineeringRigMAdvancedMediumShipME.load(), StructureEngineeringRigXLStructureAndComponentEfficiency.load(), StructureEngineeringRigMInventionCostOptimization.load(), StructureWarpScrambler.load(), StructureEngineeringRigMAdvancedMediumShipTE.load(), StructureCombatRigMMissileProjection.load(), StructureDrillingRigMEfficiency.load(), StructureCompositeReactorRigMME.load(), StructureEngineeringRigMBasicLargeShipTE.load(), StructureAdministrationServiceModule.load(), StructureDoomsdayWeapon.load(), StructureEngineeringRigLBasicCapitalComponentEfficiency.load(), StructureResourceRigMMoonOreReprocessing.load(), OLDStructureResourceRigLIceReprocessing.load(), StructureEngineeringRigMStructureME.load(), StructureEngineeringRigXLShipEfficiency.load(), StructureEngineeringRigLEquipmentEfficiency.load(), StructureLaboratoryRigLStructureComponent.load(), StructureObservatoryServiceModule.load(), StructureCombatRigXLEnergyNeutralizerAndEW.load(), StructureEngineeringRigLAmmunitionEfficiency.load(), StructureBurstProjector.load(), StructureEngineeringRigMBasicCapitalComponentME.load(), OLDStructureResourceRigLOreReprocessing.load(), StructureResourceRigLReprocessing.load(), StructureCombatRigMEWProjection.load(), StructureEngineeringRigLBasicLargeShipEfficiency.load(), StructureEngineeringRigLDroneAndFighterEfficiency.load(), OLDStructureResourceRigMIce2Reprocessing.load(), StructureLaboratoryRigMConsumable.load(), StructureCombatRigLMissileApplicationAndProjection.load(), StructureEngineeringRigMAdvancedLargeShipME.load(), StructureEngineeringRigLInventionOptimization.load(), StructureCombatRigLEnergyNeutralizerProjectionAndCapReduction.load(), StructureLaboratoryRigMModule.load(), StructureLaboratoryRigLSubsystem.load(), StructureCombatRigMEnergyNeutralizerProjection.load(), StructureEngineeringRigMAdvancedComponentTE.load(), StructureSignalAmplifier.load(), StructureLaboratoryRigMMediumShip.load(), StructureEngineeringRigLCapitalShipEfficiency.load(), StructureHybridReactorRigMME.load(), StructureFittingModule.load(), OLDStructureResourceRigMLNSOreReprocessing.load(), StructureEngineeringRigMTEResearchAccelerator.load(), StructureLaboratoryRigXLAllComponents.load(), StructureEngineeringRigMBasicMediumShipTE.load(), StructureEngineeringRigMAdvancedSmallShipME.load(), StructureCombatRigXLDoomsdayAndTargeting.load(), StructureCombatRigLAoELauncherApplicationAndProjection.load(), StructureCombatRigMEnergyNeutralizerCapReduction.load(), StructureCombatRigMSensorResolution.load(), StructureDrillingRigLProficiency.load(), StructureCombatRigMMissileApplication.load(), StructureMoonDrillingServiceModule.load(), StructureEngineeringRigLStructureEfficiency.load(), StructureEngineeringRigLTEResearchOptimization.load(), StructureCombatRigLPointDefenseBatteryApplicationAndProjection.load(), StructureEngineeringRigLAdvancedLargeShipEfficiency.load(), StructureAssemblyRigLSubsystem.load(), StructureLaboratoryRigLModuleConsumableDrone.load(), StructureEngineeringRigMBlueprintCopyCostOptimization.load(), StructureEngineeringRigLAdvancedSmallShipEfficiency.load(), StructureCombatRigLEWProjectionAndCapReduction.load(), StructureAXLMissileLauncher.load(), StructureEngineeringRigLAdvancedComponentEfficiency.load(), StructureAdvertisementServiceModule.load(), StructureCitadelRigMDroneHitpoints.load(), StructureEngineeringRigMAmmunitionME.load(), StructureCompositeReactorRigMTE.load(), StructureResourceProcessingServiceModule.load(), StructureHybridReactorRigMTE.load(), StructureResourceRigMIceReprocessing.load(), StructureBiochemicalReactorRigMTE.load(), StructureDrillingRigMStability.load(), StructureEngineeringRigMBasicSmallShipME.load(), StructureASMLMissileLauncher.load(), StructureLaboratoryRigMDrone.load(), StructureResearchServiceModule.load(), StructureEngineeringRigXLLaboratoryOptimization.load(), StructureEngineeringRigLMEResearchOptimization.load(), StructureLaboratoryRigLCapitalComponent.load(), OLDStructureResourceRigMIce1Reprocessing.load(), StructureEngineeringRigMTEResearchCostOptimization.load(), StructureLaboratoryRigLShip.load(), StructureWeaponUpgrade.load(), StructureResourceRigXLReprocessing.load(), StructureCombatRigMEWCapReduction.load(), StructureEngineeringRigMAdvancedComponentME.load(), StructureEngineeringRigMMEResearchCostOptimization.load(), StructureEngineeringRigMEquipmentTE.load(), StructureEngineeringRigMAdvancedSmallShipTE.load(), StructureLaboratoryRigXLEquipment.load(), StructureECMBattery.load(), StructureEngineeringRigLBlueprintCopyOptimization.load(), StructureEngineeringRigMStructureTE.load(), StructureEngineeringRigMAmmunitionTE.load(), StructureEngineeringRigXLEquipmentAndConsumableEfficiency.load(), StructureEngineeringRigMDroneAndFighterTE.load(), StructureAreaDenialModule.load(), StructureCitadelRigMDroneSpeed.load(), StructureEngineeringRigMAdvancedLargeShipTE.load(), StructureLaboratoryRigMSmallShip.load()).flatMap((m -> m.entrySet().stream())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
