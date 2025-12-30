package fr.guiguilechat.jcelechat.model.sde.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.structuremodule.*;

public abstract class StructureModule
    extends EveType
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final StructureModule.MetaCat METACAT = new StructureModule.MetaCat();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
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
            return Arrays.asList(StructureCitadelServiceModule.METAGROUP, StructureResourceProcessingServiceModule.METAGROUP, StructureXLMissileLauncher.METAGROUP, StructureGuidedBombLauncher.METAGROUP, StructureEnergyNeutralizer.METAGROUP, StructureAreaDenialModule.METAGROUP, StructureBurstProjector.METAGROUP, StructureECMBattery.METAGROUP, StructureDoomsdayWeapon.METAGROUP, StructureEngineeringServiceModule.METAGROUP, StructureWeaponUpgrade.METAGROUP, StructureFittingModule.METAGROUP, StructureStasisWebifier.METAGROUP, StructureWarpScrambler.METAGROUP, StructureSignalAmplifier.METAGROUP, StructureMultiroleMissileLauncher.METAGROUP, StructureCombatRigMMissileApplication.METAGROUP, StructureCombatRigMMissileProjection.METAGROUP, StructureCombatRigMEnergyNeutralizerProjection.METAGROUP, StructureCombatRigMEnergyNeutralizerCapReduction.METAGROUP, StructureCombatRigMEWProjection.METAGROUP, StructureCombatRigMEWCapReduction.METAGROUP, StructureCombatRigMMaxTargets.METAGROUP, StructureCombatRigMBoostedSensors.METAGROUP, StructureCombatRigLMissileApplicationAndProjection.METAGROUP, StructureCombatRigLEnergyNeutralizerProjectionAndCapReduction.METAGROUP, StructureCombatRigLEWProjectionAndCapReduction.METAGROUP, StructureCombatRigLAoELauncherApplicationAndProjection.METAGROUP, StructureCombatRigLPointDefenseBatteryApplicationAndProjection.METAGROUP, StructureCombatRigLMaxTargetsAndSensorBoosting.METAGROUP, StructureCombatRigXLMissileAndAoEMissile.METAGROUP, StructureCombatRigXLEnergyNeutralizerAndEW.METAGROUP, StructureCombatRigXLDoomsdayAndTargeting.METAGROUP, StructureDisruptionBattery.METAGROUP, StructureEngineeringRigMEquipmentME.METAGROUP, StructureEngineeringRigMEquipmentTE.METAGROUP, StructureEngineeringRigMAmmunitionME.METAGROUP, StructureEngineeringRigMAmmunitionTE.METAGROUP, StructureEngineeringRigMDroneAndFighterME.METAGROUP, StructureEngineeringRigMDroneAndFighterTE.METAGROUP, StructureEngineeringRigMBasicSmallShipME.METAGROUP, StructureEngineeringRigMBasicSmallShipTE.METAGROUP, StructureEngineeringRigMBasicMediumShipME.METAGROUP, StructureEngineeringRigMBasicMediumShipTE.METAGROUP, StructureEngineeringRigMBasicLargeShipME.METAGROUP, StructureEngineeringRigMBasicLargeShipTE.METAGROUP, StructureEngineeringRigMAdvancedSmallShipME.METAGROUP, StructureEngineeringRigMAdvancedSmallShipTE.METAGROUP, StructureEngineeringRigMAdvancedMediumShipME.METAGROUP, StructureEngineeringRigMAdvancedMediumShipTE.METAGROUP, StructureEngineeringRigMAdvancedLargeShipME.METAGROUP, StructureEngineeringRigMAdvancedLargeShipTE.METAGROUP, StructureEngineeringRigMAdvancedComponentME.METAGROUP, StructureEngineeringRigMAdvancedComponentTE.METAGROUP, StructureEngineeringRigMBasicCapitalComponentTE.METAGROUP, StructureEngineeringRigMBasicCapitalComponentME.METAGROUP, StructureEngineeringRigMStructureME.METAGROUP, StructureEngineeringRigMStructureTE.METAGROUP, StructureEngineeringRigMInventionCostOptimization.METAGROUP, StructureEngineeringRigMInventionAccelerator.METAGROUP, StructureEngineeringRigMMEResearchCostOptimization.METAGROUP, StructureEngineeringRigMMEResearchAccelerator.METAGROUP, StructureEngineeringRigMTEResearchCostOptimization.METAGROUP, StructureEngineeringRigMTEResearchAccelerator.METAGROUP, StructureEngineeringRigMBlueprintCopyCostOptimization.METAGROUP, StructureEngineeringRigMBlueprintCopyAccelerator.METAGROUP, StructureEngineeringRigLEquipmentEfficiency.METAGROUP, StructureEngineeringRigLAmmunitionEfficiency.METAGROUP, StructureEngineeringRigLDroneAndFighterEfficiency.METAGROUP, StructureEngineeringRigLBasicSmallShipEfficiency.METAGROUP, StructureEngineeringRigLBasicMediumShipEfficiency.METAGROUP, StructureEngineeringRigLBasicLargeShipEfficiency.METAGROUP, StructureEngineeringRigLAdvancedSmallShipEfficiency.METAGROUP, StructureEngineeringRigLAdvancedMediumShipEfficiency.METAGROUP, StructureEngineeringRigLAdvancedLargeShipEfficiency.METAGROUP, StructureEngineeringRigLCapitalShipEfficiency.METAGROUP, StructureEngineeringRigLAdvancedComponentEfficiency.METAGROUP, StructureEngineeringRigLBasicCapitalComponentEfficiency.METAGROUP, StructureEngineeringRigLStructureEfficiency.METAGROUP, StructureEngineeringRigLInventionOptimization.METAGROUP, StructureEngineeringRigLMEResearchOptimization.METAGROUP, StructureEngineeringRigLTEResearchOptimization.METAGROUP, StructureEngineeringRigLBlueprintCopyOptimization.METAGROUP, StructureEngineeringRigXLEquipmentAndConsumableEfficiency.METAGROUP, StructureEngineeringRigXLShipEfficiency.METAGROUP, StructureEngineeringRigXLStructureAndComponentEfficiency.METAGROUP, StructureEngineeringRigXLLaboratoryOptimization.METAGROUP, StructureMoonDrillingServiceModule.METAGROUP, StructureDrillingRigMEfficiency.METAGROUP, StructureDrillingRigMStability.METAGROUP, StructureDrillingRigLProficiency.METAGROUP, StructureCompositeReactorRigMTE.METAGROUP, StructureCompositeReactorRigMME.METAGROUP, StructureHybridReactorRigMTE.METAGROUP, StructureHybridReactorRigMME.METAGROUP, StructureBiochemicalReactorRigMTE.METAGROUP, StructureBiochemicalReactorRigMME.METAGROUP, StructureReactorRigLEfficiency.METAGROUP, StructureResourceRigMAsteroidOreReprocessing.METAGROUP, StructureResourceRigMIceReprocessing.METAGROUP, StructureResourceRigMMoonOreReprocessing.METAGROUP, StructureResourceRigLReprocessing.METAGROUP, StructureResourceRigXLReprocessing.METAGROUP, StructureCapacitorBattery.METAGROUP, StructureCapacitorPowerRelay.METAGROUP, StructureArmorReinforcer.METAGROUP, StructureFestivalLauncher.METAGROUP, OutpostConversionRigs.METAGROUP, QuantumCores.METAGROUP);
        }
    }
}
