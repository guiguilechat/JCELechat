package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.OutpostConversionRigs;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureAreaDenialModule;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureArmorReinforcer;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureBiochemicalReactorRigMME;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureBiochemicalReactorRigMTE;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureBurstProjector;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureCapacitorBattery;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureCapacitorPowerRelay;
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
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureMoonDrillingServiceModule;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureMultiroleMissileLauncher;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureReactorRigLEfficiency;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureResourceProcessingServiceModule;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureResourceRigLReprocessing;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureResourceRigMAsteroidOreReprocessing;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureResourceRigMIceReprocessing;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureResourceRigMMoonOreReprocessing;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureResourceRigXLReprocessing;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureSignalAmplifier;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureStasisWebifier;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureWarpScrambler;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureWeaponUpgrade;
import fr.guiguilechat.jcelechat.model.sde.items.types.structuremodule.StructureXLMissileLauncher;

public abstract class StructureModule
    extends Item
{
    /**
     * Dogma attribute that specifies if the item should have the structure icon or not.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int StructureItemVisualFlag;
    public static final StructureModule.MetaCat METACAT = new StructureModule.MetaCat();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  2334 :
            {
                return StructureItemVisualFlag;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public IMetaCategory<StructureModule> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<StructureModule>
    {

        @Override
        public int getCategoryId() {
            return  66;
        }

        @Override
        public String getName() {
            return "StructureModule";
        }

        @Override
        public Collection<IMetaGroup<? extends StructureModule>> groups() {
            return Arrays.asList(StructureCitadelServiceModule.METAGROUP, StructureResourceProcessingServiceModule.METAGROUP, StructureXLMissileLauncher.METAGROUP, StructureGuidedBombLauncher.METAGROUP, StructureEnergyNeutralizer.METAGROUP, StructureAreaDenialModule.METAGROUP, StructureBurstProjector.METAGROUP, StructureECMBattery.METAGROUP, StructureDoomsdayWeapon.METAGROUP, StructureEngineeringServiceModule.METAGROUP, StructureWeaponUpgrade.METAGROUP, StructureFittingModule.METAGROUP, StructureStasisWebifier.METAGROUP, StructureWarpScrambler.METAGROUP, StructureSignalAmplifier.METAGROUP, StructureMultiroleMissileLauncher.METAGROUP, StructureCombatRigMMissileApplication.METAGROUP, StructureCombatRigMMissileProjection.METAGROUP, StructureCombatRigMEnergyNeutralizerProjection.METAGROUP, StructureCombatRigMEnergyNeutralizerCapReduction.METAGROUP, StructureCombatRigMEWProjection.METAGROUP, StructureCombatRigMEWCapReduction.METAGROUP, StructureCombatRigMMaxTargets.METAGROUP, StructureCombatRigMBoostedSensors.METAGROUP, StructureCombatRigLMissileApplicationAndProjection.METAGROUP, StructureCombatRigLEnergyNeutralizerProjectionAndCapReduction.METAGROUP, StructureCombatRigLEWProjectionAndCapReduction.METAGROUP, StructureCombatRigLAoELauncherApplicationAndProjection.METAGROUP, StructureCombatRigLPointDefenseBatteryApplicationAndProjection.METAGROUP, StructureCombatRigLMaxTargetsAndSensorBoosting.METAGROUP, StructureCombatRigXLMissileAndAoEMissile.METAGROUP, StructureCombatRigXLEnergyNeutralizerAndEW.METAGROUP, StructureCombatRigXLDoomsdayAndTargeting.METAGROUP, StructureDisruptionBattery.METAGROUP, StructureEngineeringRigMEquipmentME.METAGROUP, StructureEngineeringRigMEquipmentTE.METAGROUP, StructureEngineeringRigMAmmunitionME.METAGROUP, StructureEngineeringRigMAmmunitionTE.METAGROUP, StructureEngineeringRigMDroneAndFighterME.METAGROUP, StructureEngineeringRigMDroneAndFighterTE.METAGROUP, StructureEngineeringRigMBasicSmallShipME.METAGROUP, StructureEngineeringRigMBasicSmallShipTE.METAGROUP, StructureEngineeringRigMBasicMediumShipME.METAGROUP, StructureEngineeringRigMBasicMediumShipTE.METAGROUP, StructureEngineeringRigMBasicLargeShipME.METAGROUP, StructureEngineeringRigMBasicLargeShipTE.METAGROUP, StructureEngineeringRigMAdvancedSmallShipME.METAGROUP, StructureEngineeringRigMAdvancedSmallShipTE.METAGROUP, StructureEngineeringRigMAdvancedMediumShipME.METAGROUP, StructureEngineeringRigMAdvancedMediumShipTE.METAGROUP, StructureEngineeringRigMAdvancedLargeShipME.METAGROUP, StructureEngineeringRigMAdvancedLargeShipTE.METAGROUP, StructureEngineeringRigMAdvancedComponentME.METAGROUP, StructureEngineeringRigMAdvancedComponentTE.METAGROUP, StructureEngineeringRigMBasicCapitalComponentTE.METAGROUP, StructureEngineeringRigMBasicCapitalComponentME.METAGROUP, StructureEngineeringRigMStructureME.METAGROUP, StructureEngineeringRigMStructureTE.METAGROUP, StructureEngineeringRigMInventionCostOptimization.METAGROUP, StructureEngineeringRigMInventionAccelerator.METAGROUP, StructureEngineeringRigMMEResearchCostOptimization.METAGROUP, StructureEngineeringRigMMEResearchAccelerator.METAGROUP, StructureEngineeringRigMTEResearchCostOptimization.METAGROUP, StructureEngineeringRigMTEResearchAccelerator.METAGROUP, StructureEngineeringRigMBlueprintCopyCostOptimization.METAGROUP, StructureEngineeringRigMBlueprintCopyAccelerator.METAGROUP, StructureEngineeringRigLEquipmentEfficiency.METAGROUP, StructureEngineeringRigLAmmunitionEfficiency.METAGROUP, StructureEngineeringRigLDroneAndFighterEfficiency.METAGROUP, StructureEngineeringRigLBasicSmallShipEfficiency.METAGROUP, StructureEngineeringRigLBasicMediumShipEfficiency.METAGROUP, StructureEngineeringRigLBasicLargeShipEfficiency.METAGROUP, StructureEngineeringRigLAdvancedSmallShipEfficiency.METAGROUP, StructureEngineeringRigLAdvancedMediumShipEfficiency.METAGROUP, StructureEngineeringRigLAdvancedLargeShipEfficiency.METAGROUP, StructureEngineeringRigLCapitalShipEfficiency.METAGROUP, StructureEngineeringRigLAdvancedComponentEfficiency.METAGROUP, StructureEngineeringRigLBasicCapitalComponentEfficiency.METAGROUP, StructureEngineeringRigLStructureEfficiency.METAGROUP, StructureEngineeringRigLInventionOptimization.METAGROUP, StructureEngineeringRigLMEResearchOptimization.METAGROUP, StructureEngineeringRigLTEResearchOptimization.METAGROUP, StructureEngineeringRigLBlueprintCopyOptimization.METAGROUP, StructureEngineeringRigXLEquipmentAndConsumableEfficiency.METAGROUP, StructureEngineeringRigXLShipEfficiency.METAGROUP, StructureEngineeringRigXLStructureAndComponentEfficiency.METAGROUP, StructureEngineeringRigXLLaboratoryOptimization.METAGROUP, StructureMoonDrillingServiceModule.METAGROUP, StructureDrillingRigMEfficiency.METAGROUP, StructureDrillingRigMStability.METAGROUP, StructureDrillingRigLProficiency.METAGROUP, StructureCompositeReactorRigMTE.METAGROUP, StructureCompositeReactorRigMME.METAGROUP, StructureHybridReactorRigMTE.METAGROUP, StructureHybridReactorRigMME.METAGROUP, StructureBiochemicalReactorRigMTE.METAGROUP, StructureBiochemicalReactorRigMME.METAGROUP, StructureReactorRigLEfficiency.METAGROUP, StructureResourceRigMAsteroidOreReprocessing.METAGROUP, StructureResourceRigMIceReprocessing.METAGROUP, StructureResourceRigMMoonOreReprocessing.METAGROUP, StructureResourceRigLReprocessing.METAGROUP, StructureResourceRigXLReprocessing.METAGROUP, StructureCapacitorBattery.METAGROUP, StructureCapacitorPowerRelay.METAGROUP, StructureArmorReinforcer.METAGROUP, StructureFestivalLauncher.METAGROUP, OutpostConversionRigs.METAGROUP);
        }
    }
}
