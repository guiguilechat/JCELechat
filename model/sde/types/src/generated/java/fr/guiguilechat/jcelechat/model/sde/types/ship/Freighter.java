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
import fr.guiguilechat.jcelechat.model.sde.attributes.AdvancedAgility;
import fr.guiguilechat.jcelechat.model.sde.attributes.Agility;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorEmDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorExplosiveDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorHP;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorKineticDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorThermalDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorUniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.BaseWarpSpeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.CanUseCargoInSpace;
import fr.guiguilechat.jcelechat.model.sde.attributes.CapacitorCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.CargoScanResistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.Charge;
import fr.guiguilechat.jcelechat.model.sde.attributes.CpuLoad;
import fr.guiguilechat.jcelechat.model.sde.attributes.CpuNeedBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.CpuOutput;
import fr.guiguilechat.jcelechat.model.sde.attributes.Damage;
import fr.guiguilechat.jcelechat.model.sde.attributes.DroneBandwidth;
import fr.guiguilechat.jcelechat.model.sde.attributes.DroneCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.EmDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.EnergyWarfareResistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ExplosiveDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.FighterAbilityAntiCapitalMissileResistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.FreighterBonusA1;
import fr.guiguilechat.jcelechat.model.sde.attributes.FreighterBonusA2;
import fr.guiguilechat.jcelechat.model.sde.attributes.FreighterBonusC1;
import fr.guiguilechat.jcelechat.model.sde.attributes.FreighterBonusC2;
import fr.guiguilechat.jcelechat.model.sde.attributes.FreighterBonusG1;
import fr.guiguilechat.jcelechat.model.sde.attributes.FreighterBonusG2;
import fr.guiguilechat.jcelechat.model.sde.attributes.FreighterBonusM1;
import fr.guiguilechat.jcelechat.model.sde.attributes.FreighterBonusM2;
import fr.guiguilechat.jcelechat.model.sde.attributes.FreighterBonusO1;
import fr.guiguilechat.jcelechat.model.sde.attributes.FreighterBonusO2;
import fr.guiguilechat.jcelechat.model.sde.attributes.FwLpKill;
import fr.guiguilechat.jcelechat.model.sde.attributes.GfxBoosterID;
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
import fr.guiguilechat.jcelechat.model.sde.attributes.IsCapitalSize;
import fr.guiguilechat.jcelechat.model.sde.attributes.IsCarrierJumpConduitPassenger;
import fr.guiguilechat.jcelechat.model.sde.attributes.JumpFatigueMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.KineticDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.LauncherSlotsLeft;
import fr.guiguilechat.jcelechat.model.sde.attributes.LowSlots;
import fr.guiguilechat.jcelechat.model.sde.attributes.MainColor;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxLockedTargets;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxOperationalDistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxPassengers;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxTargetRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxVelocity;
import fr.guiguilechat.jcelechat.model.sde.attributes.MedSlots;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaLevelOld;
import fr.guiguilechat.jcelechat.model.sde.attributes.PowerLoad;
import fr.guiguilechat.jcelechat.model.sde.attributes.PowerOutput;
import fr.guiguilechat.jcelechat.model.sde.attributes.PowerToSpeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.PropulsionGraphicID;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.RechargeRate;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill2;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill2Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.RigSize;
import fr.guiguilechat.jcelechat.model.sde.attributes.RigSlots;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanGravimetricStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanLadarStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanMagnetometricStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanRadarStrength;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanResolution;
import fr.guiguilechat.jcelechat.model.sde.attributes.ScanSpeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldCharge;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldEmDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldExplosiveDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldKineticDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldRechargeRate;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldThermalDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShieldUniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusUFreighter1;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusUFreighter2;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusUFreighter3;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipMaintenanceBayCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.SignatureRadius;
import fr.guiguilechat.jcelechat.model.sde.attributes.SpecialColonyResourcesHoldCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.StasisWebifierResistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.StructureUniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.TechLevel;
import fr.guiguilechat.jcelechat.model.sde.attributes.ThermalDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.TurretSlotsLeft;
import fr.guiguilechat.jcelechat.model.sde.attributes.TypeColorScheme;
import fr.guiguilechat.jcelechat.model.sde.attributes.Uniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.UpgradeCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.UpgradeSlotsLeft;
import fr.guiguilechat.jcelechat.model.sde.attributes.UpwellFreighterCapitalFlexHardenerFittingCapBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.UpwellFreightercloakCPUPenalty;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarpCapacitorNeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarpFactor;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarpSpeedMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.WeaponDisruptionResistance;
import fr.guiguilechat.jcelechat.model.sde.types.Ship;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class Freighter
    extends Ship
{
    /**
     * Attribute on ship to make advanced command affect only ships that we want.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int advancedagility;
    /**
     * Can use cargo in space or not, 0 = no, 1 = yes
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int canusecargoinspace;
    /**
     * Autogenerated skill attribute, cpuNeedBonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int cpuneedbonus;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fighterabilityanticapitalmissileresistance;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int freighterbonusa1;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int freighterbonusa2;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int freighterbonusc1;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int freighterbonusc2;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int freighterbonusg1;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int freighterbonusg2;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int freighterbonusm1;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int freighterbonusm2;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int freighterbonuso1;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int freighterbonuso2;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fwlpkill;
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
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int iscapitalsize;
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(1)
    public int iscarrierjumpconduitpassenger;
    /**
     * Multiplier for jump fatigue distance
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double jumpfatiguemultiplier;
    /**
     * The main color of a ship type.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maincolor;
    /**
     * The maximum distance at which the object can be used.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxoperationaldistance;
    /**
     * Specifies the maximum numbers of passengers that the ship can have
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxpassengers;
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
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonusufreighter1;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonusufreighter2;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonusufreighter3;
    /**
     * The capacity of the hangar in a ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipmaintenancebaycapacity;
    /**
     * Infrastructure Hold Capacity
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int specialcolonyresourcesholdcapacity;
    /**
     * How many rigs can by fitted to this ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int upgradeslotsleft;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double upwellfreightercapitalflexhardenerfittingcapbonus;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int upwellfreightercloakcpupenalty;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {BaseWarpSpeed.INSTANCE, Damage.INSTANCE, ShieldCapacity.INSTANCE, ShieldCharge.INSTANCE, Hp.INSTANCE, ArmorHP.INSTANCE, RigSize.INSTANCE, PowerOutput.INSTANCE, ArmorEmDamageResonance.INSTANCE, LowSlots.INSTANCE, ArmorUniformity.INSTANCE, ArmorExplosiveDamageResonance.INSTANCE, MedSlots.INSTANCE, StructureUniformity.INSTANCE, ArmorKineticDamageResonance.INSTANCE, SpecialColonyResourcesHoldCapacity.INSTANCE, HiSlots.INSTANCE, ArmorThermalDamageResonance.INSTANCE, PowerLoad.INSTANCE, ShieldEmDamageResonance.INSTANCE, ShieldExplosiveDamageResonance.INSTANCE, ShipBonusUFreighter1 .INSTANCE, ShieldKineticDamageResonance.INSTANCE, ShipBonusUFreighter2 .INSTANCE, Charge.INSTANCE, ShieldThermalDamageResonance.INSTANCE, FwLpKill.INSTANCE, UpwellFreightercloakCPUPenalty.INSTANCE, PowerToSpeed.INSTANCE, WarpFactor.INSTANCE, RequiredSkill1Level.INSTANCE, ShipBonusUFreighter3 .INSTANCE, RequiredSkill2Level.INSTANCE, UpwellFreighterCapitalFlexHardenerFittingCapBonus.INSTANCE, DroneCapacity.INSTANCE, MaxVelocity.INSTANCE, Capacity.INSTANCE, SignatureRadius.INSTANCE, CpuOutput.INSTANCE, CpuLoad.INSTANCE, IsCarrierJumpConduitPassenger.INSTANCE, ScanResolution.INSTANCE, CpuNeedBonus.INSTANCE, RechargeRate.INSTANCE, WeaponDisruptionResistance.INSTANCE, StasisWebifierResistance.INSTANCE, Agility.INSTANCE, MaxTargetRange.INSTANCE, ScanSpeed.INSTANCE, CanUseCargoInSpace.INSTANCE, AdvancedAgility.INSTANCE, WarpSpeedMultiplier.INSTANCE, LauncherSlotsLeft.INSTANCE, TurretSlotsLeft.INSTANCE, UpgradeCapacity.INSTANCE, KineticDamageResonance.INSTANCE, ThermalDamageResonance.INSTANCE, ExplosiveDamageResonance.INSTANCE, RigSlots.INSTANCE, EmDamageResonance.INSTANCE, FreighterBonusA1 .INSTANCE, FreighterBonusA2 .INSTANCE, FreighterBonusC1 .INSTANCE, FreighterBonusC2 .INSTANCE, MetaLevelOld.INSTANCE, FreighterBonusG2 .INSTANCE, FreighterBonusG1 .INSTANCE, MainColor.INSTANCE, FreighterBonusM1 .INSTANCE, FreighterBonusM2 .INSTANCE, MaxPassengers.INSTANCE, UpgradeSlotsLeft.INSTANCE, Uniformity.INSTANCE, HasShipMaintenanceBay.INSTANCE, ShipMaintenanceBayCapacity.INSTANCE, WarpCapacitorNeed.INSTANCE, HeatCapacityHi.INSTANCE, HeatDissipationRateHi.INSTANCE, Radius.INSTANCE, TechLevel.INSTANCE, HeatDissipationRateMed.INSTANCE, HeatDissipationRateLow.INSTANCE, HeatCapacityMed.INSTANCE, HeatCapacityLow.INSTANCE, JumpFatigueMultiplier.INSTANCE, RequiredSkill1 .INSTANCE, RequiredSkill2 .INSTANCE, CargoScanResistance.INSTANCE, FreighterBonusO1 .INSTANCE, FreighterBonusO2 .INSTANCE, MaxLockedTargets.INSTANCE, FighterAbilityAntiCapitalMissileResistance.INSTANCE, HeatGenerationMultiplier.INSTANCE, MaxOperationalDistance.INSTANCE, ScanRadarStrength.INSTANCE, ScanLadarStrength.INSTANCE, ScanMagnetometricStrength.INSTANCE, ScanGravimetricStrength.INSTANCE, PropulsionGraphicID.INSTANCE, ShieldRechargeRate.INSTANCE, CapacitorCapacity.INSTANCE, ShieldUniformity.INSTANCE, TypeColorScheme.INSTANCE, HeatAttenuationHi.INSTANCE, HeatAttenuationMed.INSTANCE, HeatAttenuationLow.INSTANCE, GfxBoosterID.INSTANCE, DroneBandwidth.INSTANCE, IsCapitalSize.INSTANCE, EnergyWarfareResistance.INSTANCE })));
    public static final Freighter.MetaGroup METAGROUP = new Freighter.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  853 :
            {
                return advancedagility;
            }
            case  849 :
            {
                return canusecargoinspace;
            }
            case  310 :
            {
                return cpuneedbonus;
            }
            case  2244 :
            {
                return fighterabilityanticapitalmissileresistance;
            }
            case  886 :
            {
                return freighterbonusa1;
            }
            case  887 :
            {
                return freighterbonusa2;
            }
            case  888 :
            {
                return freighterbonusc1;
            }
            case  889 :
            {
                return freighterbonusc2;
            }
            case  891 :
            {
                return freighterbonusg1;
            }
            case  890 :
            {
                return freighterbonusg2;
            }
            case  892 :
            {
                return freighterbonusm1;
            }
            case  893 :
            {
                return freighterbonusm2;
            }
            case  1983 :
            {
                return freighterbonuso1;
            }
            case  1984 :
            {
                return freighterbonuso2;
            }
            case  1555 :
            {
                return fwlpkill;
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
            case  1785 :
            {
                return iscapitalsize;
            }
            case  5682 :
            {
                return iscarrierjumpconduitpassenger;
            }
            case  1971 :
            {
                return jumpfatiguemultiplier;
            }
            case  124 :
            {
                return maincolor;
            }
            case  715 :
            {
                return maxoperationaldistance;
            }
            case  129 :
            {
                return maxpassengers;
            }
            case  183 :
            {
                return requiredskill2;
            }
            case  278 :
            {
                return requiredskill2level;
            }
            case  1547 :
            {
                return rigsize;
            }
            case  1137 :
            {
                return rigslots;
            }
            case  564 :
            {
                return scanresolution;
            }
            case  79 :
            {
                return scanspeed;
            }
            case  5649 :
            {
                return shipbonusufreighter1;
            }
            case  5650 :
            {
                return shipbonusufreighter2;
            }
            case  5654 :
            {
                return shipbonusufreighter3;
            }
            case  908 :
            {
                return shipmaintenancebaycapacity;
            }
            case  5646 :
            {
                return specialcolonyresourcesholdcapacity;
            }
            case  1154 :
            {
                return upgradeslotsleft;
            }
            case  5655 :
            {
                return upwellfreightercapitalflexhardenerfittingcapbonus;
            }
            case  5651 :
            {
                return upwellfreightercloakcpupenalty;
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
    public IMetaGroup<Freighter> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Freighter>
    {
        public static final String RESOURCE_PATH = "SDE/types/ship/Freighter.yaml";
        private Map<Integer, Freighter> cache = (null);

        @Override
        public IMetaCategory<? super Freighter> category() {
            return Ship.METACAT;
        }

        @Override
        public int getGroupId() {
            return  513;
        }

        @Override
        public String getName() {
            return "Freighter";
        }

        @Override
        public synchronized Map<Integer, Freighter> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(Freighter.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, Freighter> types;
        }
    }
}
