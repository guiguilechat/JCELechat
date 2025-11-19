package fr.guiguilechat.jcelechat.model.sde.types.ship;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultRealValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.Agility;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorEmDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorExplosiveDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorHP;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorKineticDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorThermalDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorUniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.BaseWarpSpeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.CapacitorCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.CargoScanResistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.CovertOpsAndReconOpsCloakModuleDelay;
import fr.guiguilechat.jcelechat.model.sde.attributes.CpuLoad;
import fr.guiguilechat.jcelechat.model.sde.attributes.CpuOutput;
import fr.guiguilechat.jcelechat.model.sde.attributes.Damage;
import fr.guiguilechat.jcelechat.model.sde.attributes.DroneBandwidth;
import fr.guiguilechat.jcelechat.model.sde.attributes.DroneCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.EmDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.EnergyWarfareResistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ExplosiveDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.FleetHangarCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.FwLpKill;
import fr.guiguilechat.jcelechat.model.sde.attributes.GfxBoosterID;
import fr.guiguilechat.jcelechat.model.sde.attributes.HasFleetHangars;
import fr.guiguilechat.jcelechat.model.sde.attributes.HasShipMaintenanceBay;
import fr.guiguilechat.jcelechat.model.sde.attributes.HeatAttenuationHi;
import fr.guiguilechat.jcelechat.model.sde.attributes.HeatAttenuationLow;
import fr.guiguilechat.jcelechat.model.sde.attributes.HeatAttenuationMed;
import fr.guiguilechat.jcelechat.model.sde.attributes.HeatCapacityHi;
import fr.guiguilechat.jcelechat.model.sde.attributes.HeatCapacityLow;
import fr.guiguilechat.jcelechat.model.sde.attributes.HeatCapacityMed;
import fr.guiguilechat.jcelechat.model.sde.attributes.HeatDissipationRateHi;
import fr.guiguilechat.jcelechat.model.sde.attributes.HeatDissipationRateLow;
import fr.guiguilechat.jcelechat.model.sde.attributes.HeatDissipationRateMed;
import fr.guiguilechat.jcelechat.model.sde.attributes.HeatGenerationMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.HiSlots;
import fr.guiguilechat.jcelechat.model.sde.attributes.Hp;
import fr.guiguilechat.jcelechat.model.sde.attributes.IsBlackOpsJumpConduitPassenger;
import fr.guiguilechat.jcelechat.model.sde.attributes.IsBlackOpsJumpPortalPassenger;
import fr.guiguilechat.jcelechat.model.sde.attributes.KineticDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.LauncherSlotsLeft;
import fr.guiguilechat.jcelechat.model.sde.attributes.LowSlots;
import fr.guiguilechat.jcelechat.model.sde.attributes.MainColor;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxDirectionalScanRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxDirectionalVelocity;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxGangModules;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxLockedTargets;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxOperationalDistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxOperationalUsers;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxPassengers;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxTargetRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxVelocity;
import fr.guiguilechat.jcelechat.model.sde.attributes.MedSlots;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaGroupID;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaLevelOld;
import fr.guiguilechat.jcelechat.model.sde.attributes.MinTargetVelDmgMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.PowerLoad;
import fr.guiguilechat.jcelechat.model.sde.attributes.PowerOutput;
import fr.guiguilechat.jcelechat.model.sde.attributes.PowerToSpeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.PropulsionGraphicID;
import fr.guiguilechat.jcelechat.model.sde.attributes.RechargeRate;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill2;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill2Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill3;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill3Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.RigSize;
import fr.guiguilechat.jcelechat.model.sde.attributes.RigSlots;
import fr.guiguilechat.jcelechat.model.sde.attributes.RoleBonusCommandBurstAoERange;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanGravimetricStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanLadarStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanMagnetometricStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanRadarStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanResolution;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanSpeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.SensorDampenerResistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldEmDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldExplosiveDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldKineticDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldRechargeRate;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldThermalDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldUniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusABC1;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusGBC1;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusGasCloudScoopCPUreductionSOEECSrole;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusRole6;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusRole7;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusRole8;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusSoEECS1;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusSoEECS2;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusSoEECS3;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusSoEECS4;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipMaintenanceBayCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.SignatureRadius;
import fr.guiguilechat.jcelechat.model.sde.attributes.SpecialExpeditionHoldCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.StasisWebifierResistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.StructureUniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.TechLevel;
import fr.guiguilechat.jcelechat.model.sde.attributes.ThermalDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.TurretSlotsLeft;
import fr.guiguilechat.jcelechat.model.sde.attributes.TypeColorScheme;
import fr.guiguilechat.jcelechat.model.sde.attributes.Uniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.UpgradeCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.UpgradeSlotsLeft;
import fr.guiguilechat.jcelechat.model.sde.attributes.VirusStrengthBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarpCapacitorNeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarpFactor;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarpSpeedMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.WeaponDisruptionResistance;
import fr.guiguilechat.jcelechat.model.sde.types.Ship;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class ExpeditionCommandShip
    extends Ship
{
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(30000)
    public int covertopsandreconopscloakmoduledelay;
    /**
     * The capacity of the fleet hangar.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fleethangarcapacity;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fwlpkill;
    /**
     * Whether this ship has fleet hangars.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int hasfleethangars;
    /**
     * Indicates whether a ship type has a ship maintenance bay.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int hasshipmaintenancebay;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double heatattenuationhi;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double heatattenuationlow;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double heatattenuationmed;
    /**
     * tbd
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int hislots;
    /**
     * Set this attribute on a ship to declare that the ship is an eligible passenger to be carried through a BlackOps Jump Conduit
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int isblackopsjumpconduitpassenger;
    /**
     * Set this attribute on a ship to declare that the ship is an eligible passenger to travel through a BlackOps Jump Portal
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int isblackopsjumpportalpassenger;
    /**
     * The number of low power slots on the ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int lowslots;
    /**
     * The main color of a ship type.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maincolor;
    /**
     * Deprecated.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int maxdirectionalvelocity;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(3)
    public int maxgangmodules;
    /**
     * The maximum distance at which the object can be used.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxoperationaldistance;
    /**
     * The maximum number of users that can be present within the operational range of the structure for it to be capable of operation.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxoperationalusers;
    /**
     * Specifies the maximum numbers of passengers that the ship can have
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxpassengers;
    /**
     * tbd
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int medslots;
    /**
     * Authoring has been moved to FSD.
     * meta group of type
     * 
     *  3: Story-line (Cosmos)
     *  4: Faction
     *  5: Officer (rare asteroid NPCs)
     *  6: Deadspace
     * 
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int metagroupid;
    /**
     * Deprecated.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double mintargetveldmgmultiplier;
    /**
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill1;
    /**
     * Required skill level for skill 1
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill1level;
    /**
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill2;
    /**
     * Required skill level for skill 2
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill2level;
    /**
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill3;
    /**
     * Required skill level for skill 3
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill3level;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rigsize;
    /**
     * The number of rig slots on the ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rigslots;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rolebonuscommandburstaoerange;
    /**
     * The resolution that the vessel can target other objects at.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double scanresolution;
    /**
     * scanning speed in milliseconds
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int scanspeed;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shipbonusabc1;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shipbonusgbc1;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonusgascloudscoopcpureductionsoeecsrole;
    /**
     * Ship Role Bonus. Not multiplied by skills.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shipbonusrole6;
    /**
     * Fixed Role Bonus on a ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shipbonusrole7;
    /**
     * Second Stock Bonus on Pirate Faction Ships.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shipbonusrole8;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(5)
    public int shipbonussoeecs1;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(5)
    public int shipbonussoeecs2;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(5)
    public int shipbonussoeecs3;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(5)
    public int shipbonussoeecs4;
    /**
     * The capacity of the hangar in a ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipmaintenancebaycapacity;
    /**
     * Expedition Hold Capacity
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int specialexpeditionholdcapacity;
    /**
     * The value of this attribute is a graphicsID which controls the color scheme of this type. It is used to apply said color scheme to items of other types whose gfx representation is tied in with the attribute holder. Example: Turrets on ships.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int typecolorscheme;
    /**
     * Attribute on ships used for ship upgrades
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int upgradecapacity;
    /**
     * How many rigs can by fitted to this ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int upgradeslotsleft;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int virusstrengthbonus;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {BaseWarpSpeed.INSTANCE, Damage.INSTANCE, ShieldCapacity.INSTANCE, Hp.INSTANCE, ArmorHP.INSTANCE, CovertOpsAndReconOpsCloakModuleDelay.INSTANCE, RigSize.INSTANCE, PowerOutput.INSTANCE, ArmorEmDamageResonance.INSTANCE, LowSlots.INSTANCE, ArmorExplosiveDamageResonance.INSTANCE, ArmorUniformity.INSTANCE, MedSlots.INSTANCE, ArmorKineticDamageResonance.INSTANCE, StructureUniformity.INSTANCE, HiSlots.INSTANCE, RoleBonusCommandBurstAoERange.INSTANCE, ArmorThermalDamageResonance.INSTANCE, PowerLoad.INSTANCE, ShieldEmDamageResonance.INSTANCE, ShieldExplosiveDamageResonance.INSTANCE, ShieldKineticDamageResonance.INSTANCE, ShieldThermalDamageResonance.INSTANCE, FwLpKill.INSTANCE, PowerToSpeed.INSTANCE, RequiredSkill1Level.INSTANCE, WarpFactor.INSTANCE, RequiredSkill2Level.INSTANCE, RequiredSkill3Level.INSTANCE, ShipBonusRole7 .INSTANCE, ShipBonusABC1 .INSTANCE, DroneCapacity.INSTANCE, MaxVelocity.INSTANCE, SignatureRadius.INSTANCE, CpuOutput.INSTANCE, CpuLoad.INSTANCE, ShipBonusSoEECS1 .INSTANCE, ScanResolution.INSTANCE, ShipBonusSoEECS2 .INSTANCE, ShipBonusSoEECS3 .INSTANCE, ShipBonusSoEECS4 .INSTANCE, RechargeRate.INSTANCE, SpecialExpeditionHoldCapacity.INSTANCE, ShipBonusGasCloudScoopCPUreductionSOEECSrole.INSTANCE, SensorDampenerResistance.INSTANCE, WeaponDisruptionResistance.INSTANCE, StasisWebifierResistance.INSTANCE, Agility.INSTANCE, MaxTargetRange.INSTANCE, ScanSpeed.INSTANCE, WarpSpeedMultiplier.INSTANCE, LauncherSlotsLeft.INSTANCE, TurretSlotsLeft.INSTANCE, UpgradeCapacity.INSTANCE, KineticDamageResonance.INSTANCE, ThermalDamageResonance.INSTANCE, ExplosiveDamageResonance.INSTANCE, RigSlots.INSTANCE, EmDamageResonance.INSTANCE, MetaLevelOld.INSTANCE, MainColor.INSTANCE, VirusStrengthBonus.INSTANCE, MaxPassengers.INSTANCE, UpgradeSlotsLeft.INSTANCE, Uniformity.INSTANCE, HasShipMaintenanceBay.INSTANCE, ShipMaintenanceBayCapacity.INSTANCE, HasFleetHangars.INSTANCE, FleetHangarCapacity.INSTANCE, MaxDirectionalVelocity.INSTANCE, MinTargetVelDmgMultiplier.INSTANCE, ShipBonusRole8 .INSTANCE, WarpCapacitorNeed.INSTANCE, HeatCapacityHi.INSTANCE, HeatDissipationRateHi.INSTANCE, MetaGroupID.INSTANCE, MaxDirectionalScanRange.INSTANCE, TechLevel.INSTANCE, HeatDissipationRateMed.INSTANCE, HeatDissipationRateLow.INSTANCE, HeatCapacityMed.INSTANCE, HeatCapacityLow.INSTANCE, MaxGangModules.INSTANCE, RequiredSkill1 .INSTANCE, RequiredSkill2 .INSTANCE, RequiredSkill3 .INSTANCE, CargoScanResistance.INSTANCE, MaxLockedTargets.INSTANCE, HeatGenerationMultiplier.INSTANCE, MaxOperationalDistance.INSTANCE, MaxOperationalUsers.INSTANCE, ScanRadarStrength.INSTANCE, ScanLadarStrength.INSTANCE, ScanMagnetometricStrength.INSTANCE, ScanGravimetricStrength.INSTANCE, PropulsionGraphicID.INSTANCE, ShieldRechargeRate.INSTANCE, CapacitorCapacity.INSTANCE, ShieldUniformity.INSTANCE, TypeColorScheme.INSTANCE, ShipBonusGBC1 .INSTANCE, HeatAttenuationHi.INSTANCE, HeatAttenuationMed.INSTANCE, HeatAttenuationLow.INSTANCE, GfxBoosterID.INSTANCE, DroneBandwidth.INSTANCE, IsBlackOpsJumpPortalPassenger.INSTANCE, IsBlackOpsJumpConduitPassenger.INSTANCE, EnergyWarfareResistance.INSTANCE, ShipBonusRole6 .INSTANCE })));
    public static final ExpeditionCommandShip.MetaGroup METAGROUP = new ExpeditionCommandShip.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  1034 :
            {
                return covertopsandreconopscloakmoduledelay;
            }
            case  912 :
            {
                return fleethangarcapacity;
            }
            case  1555 :
            {
                return fwlpkill;
            }
            case  911 :
            {
                return hasfleethangars;
            }
            case  907 :
            {
                return hasshipmaintenancebay;
            }
            case  1259 :
            {
                return heatattenuationhi;
            }
            case  1262 :
            {
                return heatattenuationlow;
            }
            case  1261 :
            {
                return heatattenuationmed;
            }
            case  14 :
            {
                return hislots;
            }
            case  3322 :
            {
                return isblackopsjumpconduitpassenger;
            }
            case  3320 :
            {
                return isblackopsjumpportalpassenger;
            }
            case  12 :
            {
                return lowslots;
            }
            case  124 :
            {
                return maincolor;
            }
            case  661 :
            {
                return maxdirectionalvelocity;
            }
            case  435 :
            {
                return maxgangmodules;
            }
            case  715 :
            {
                return maxoperationaldistance;
            }
            case  716 :
            {
                return maxoperationalusers;
            }
            case  129 :
            {
                return maxpassengers;
            }
            case  13 :
            {
                return medslots;
            }
            case  1692 :
            {
                return metagroupid;
            }
            case  662 :
            {
                return mintargetveldmgmultiplier;
            }
            case  182 :
            {
                return requiredskill1;
            }
            case  277 :
            {
                return requiredskill1level;
            }
            case  183 :
            {
                return requiredskill2;
            }
            case  278 :
            {
                return requiredskill2level;
            }
            case  184 :
            {
                return requiredskill3;
            }
            case  279 :
            {
                return requiredskill3level;
            }
            case  1547 :
            {
                return rigsize;
            }
            case  1137 :
            {
                return rigslots;
            }
            case  2574 :
            {
                return rolebonuscommandburstaoerange;
            }
            case  564 :
            {
                return scanresolution;
            }
            case  79 :
            {
                return scanspeed;
            }
            case  795 :
            {
                return shipbonusabc1;
            }
            case  747 :
            {
                return shipbonusgbc1;
            }
            case  5945 :
            {
                return shipbonusgascloudscoopcpureductionsoeecsrole;
            }
            case  2303 :
            {
                return shipbonusrole6;
            }
            case  793 :
            {
                return shipbonusrole7;
            }
            case  1688 :
            {
                return shipbonusrole8;
            }
            case  5939 :
            {
                return shipbonussoeecs1;
            }
            case  5940 :
            {
                return shipbonussoeecs2;
            }
            case  5941 :
            {
                return shipbonussoeecs3;
            }
            case  5942 :
            {
                return shipbonussoeecs4;
            }
            case  908 :
            {
                return shipmaintenancebaycapacity;
            }
            case  5944 :
            {
                return specialexpeditionholdcapacity;
            }
            case  1768 :
            {
                return typecolorscheme;
            }
            case  1132 :
            {
                return upgradecapacity;
            }
            case  1154 :
            {
                return upgradeslotsleft;
            }
            case  1918 :
            {
                return virusstrengthbonus;
            }
            default:
            {
                return super.valueSet((attribute));
            }
        }
    }

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<ExpeditionCommandShip> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<ExpeditionCommandShip>
    {
        public static final String RESOURCE_PATH = "SDE/types/ship/ExpeditionCommandShip.yaml";
        private Map<Integer, ExpeditionCommandShip> cache = (null);

        @Override
        public IMetaCategory<? super ExpeditionCommandShip> category() {
            return Ship.METACAT;
        }

        @Override
        public int getGroupId() {
            return  4902;
        }

        @Override
        public String getName() {
            return "ExpeditionCommandShip";
        }

        @Override
        public synchronized Map<Integer, ExpeditionCommandShip> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(ExpeditionCommandShip.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    LoaderOptions options = new LoaderOptions();
                    options.setCodePointLimit(Integer.MAX_VALUE);
                    cache = new Yaml(options).loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<Integer, ExpeditionCommandShip> types;
        }
    }
}
