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
import fr.guiguilechat.jcelechat.model.sde.attributes.AffectedByIndustrialInvulnModule;
import fr.guiguilechat.jcelechat.model.sde.attributes.Agility;
import fr.guiguilechat.jcelechat.model.sde.attributes.AllowedInCapIndustrialMaintenanceBay;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorEmDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorExplosiveDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorHP;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorKineticDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorThermalDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ArmorUniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.BaseWarpSpeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.CapacitorCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.CargoScanResistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.Charge;
import fr.guiguilechat.jcelechat.model.sde.attributes.CpuLoad;
import fr.guiguilechat.jcelechat.model.sde.attributes.CpuOutput;
import fr.guiguilechat.jcelechat.model.sde.attributes.Damage;
import fr.guiguilechat.jcelechat.model.sde.attributes.DroneBandwidth;
import fr.guiguilechat.jcelechat.model.sde.attributes.DroneCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.EliteBonusBarge1;
import fr.guiguilechat.jcelechat.model.sde.attributes.EliteBonusBarge2;
import fr.guiguilechat.jcelechat.model.sde.attributes.EmDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.EnergyWarfareResistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ExhumersBonusGasHarvestingDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.ExhumersBonusGeneralMiningHoldCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.ExhumersBonusIceHarvestingDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.ExhumersBonusOreMiningDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.ExhumersBonusOreMiningYield;
import fr.guiguilechat.jcelechat.model.sde.attributes.ExhumersBonusShieldResistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ExplosiveDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.FwLpKill;
import fr.guiguilechat.jcelechat.model.sde.attributes.GeneralMiningHoldCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.GfxBoosterID;
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
import fr.guiguilechat.jcelechat.model.sde.attributes.IceHarvestCycleBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.IndustrialBonusDroneDamage;
import fr.guiguilechat.jcelechat.model.sde.attributes.IsIndustrialJumpConduitPassenger;
import fr.guiguilechat.jcelechat.model.sde.attributes.IsIndustrialJumpPortalPassenger;
import fr.guiguilechat.jcelechat.model.sde.attributes.KineticDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.LauncherSlotsLeft;
import fr.guiguilechat.jcelechat.model.sde.attributes.LowSlots;
import fr.guiguilechat.jcelechat.model.sde.attributes.MainColor;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxDirectionalVelocity;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxLockedTargets;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxPassengers;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxTargetRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxVelocity;
import fr.guiguilechat.jcelechat.model.sde.attributes.MedSlots;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaLevelOld;
import fr.guiguilechat.jcelechat.model.sde.attributes.MinTargetVelDmgMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.MiningAmountMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.MiningBargeBonusGasHarvestingDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.MiningBargeBonusGeneralMiningHoldCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.MiningBargeBonusIceHarvestingDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.MiningBargeBonusIceHarvestingRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.MiningBargeBonusOreMiningRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.MiningBargeBonusOreMiningYield;
import fr.guiguilechat.jcelechat.model.sde.attributes.MiningBargeBonusShieldCapacity;
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
import fr.guiguilechat.jcelechat.model.sde.attributes.RookieDroneBonus;
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
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusORE2;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusORE3;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipRoleBonusDroneDamage;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipRoleBonusDroneHitPoints;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipRoleBonusGasHarvesterDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipRoleBonusIceHarvesterActivationCost;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipRoleBonusIceHarvestingDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipRoleBonusOreMiningDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipRoleBonusStripMinerActivationCost;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipScanResistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.SignatureRadius;
import fr.guiguilechat.jcelechat.model.sde.attributes.StasisWebifierResistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.StructureUniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.TechLevel;
import fr.guiguilechat.jcelechat.model.sde.attributes.ThermalDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.TurretSlotsLeft;
import fr.guiguilechat.jcelechat.model.sde.attributes.TypeColorScheme;
import fr.guiguilechat.jcelechat.model.sde.attributes.Uniformity;
import fr.guiguilechat.jcelechat.model.sde.attributes.UpgradeCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.UpgradeSlotsLeft;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarpCapacitorNeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarpFactor;
import fr.guiguilechat.jcelechat.model.sde.attributes.WarpSpeedMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.WeaponDisruptionResistance;
import fr.guiguilechat.jcelechat.model.sde.types.Ship;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class Exhumer
    extends Ship
{
    /**
     * Tells if this type (ship) can be affected by the Rorqual Invulnerability Module
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int affectedbyindustrialinvulnmodule;
    /**
     * Tells if this type (ship) can be placed in the maintenance bay of a capital industrial ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int allowedincapindustrialmaintenancebay;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int elitebonusbarge1;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int elitebonusbarge2;
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int exhumersbonusgasharvestingduration;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double exhumersbonusgeneralminingholdcapacity;
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int exhumersbonusiceharvestingduration;
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int exhumersbonusoreminingduration;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int exhumersbonusoreminingyield;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int exhumersbonusshieldresistance;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fwlpkill;
    /**
     * Capacity of general mining hold
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int generalminingholdcapacity;
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
    @DefaultRealValue(0.0)
    public double iceharvestcyclebonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int industrialbonusdronedamage;
    /**
     * Set this attribute on a ship to declare that the ship is an eligible passenger to be carried through an Industrial Jump Conduit.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int isindustrialjumpconduitpassenger;
    /**
     * Set this attribute on a ship to declare that the ship is an eligible passenger to travel through an Industrial Jump Portal
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int isindustrialjumpportalpassenger;
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
     * Specifies the maximum numbers of passengers that the ship can have
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxpassengers;
    /**
     * Deprecated.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double mintargetveldmgmultiplier;
    /**
     * The factor by which the amount mined by a mining laser is scaled.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(1.0)
    public double miningamountmultiplier;
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int miningbargebonusgasharvestingduration;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int miningbargebonusgeneralminingholdcapacity;
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int miningbargebonusiceharvestingduration;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int miningbargebonusiceharvestingrange;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int miningbargebonusoreminingrange;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int miningbargebonusoreminingyield;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int miningbargebonusshieldcapacity;
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
     * Bonus to drone damage, HP and mining yield
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rookiedronebonus;
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
     * ORE Mining barge bonus 2
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonusore2;
    /**
     * ORE Mining Barge bonus 3
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonusore3;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shiprolebonusdronedamage;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shiprolebonusdronehitpoints;
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shiprolebonusgasharvesterduration;
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shiprolebonusiceharvesteractivationcost;
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shiprolebonusiceharvestingduration;
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shiprolebonusoreminingduration;
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shiprolebonusstripmineractivationcost;
    /**
     * Chance of being able to resist a ship scan.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipscanresistance;
    /**
     * How many rigs can by fitted to this ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int upgradeslotsleft;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {BaseWarpSpeed.INSTANCE, Damage.INSTANCE, ShipBonusORE2 .INSTANCE, ShieldCapacity.INSTANCE, ShieldCharge.INSTANCE, Hp.INSTANCE, ArmorHP.INSTANCE, RigSize.INSTANCE, PowerOutput.INSTANCE, ArmorEmDamageResonance.INSTANCE, IceHarvestCycleBonus.INSTANCE, LowSlots.INSTANCE, ArmorUniformity.INSTANCE, ArmorExplosiveDamageResonance.INSTANCE, MedSlots.INSTANCE, StructureUniformity.INSTANCE, ArmorKineticDamageResonance.INSTANCE, HiSlots.INSTANCE, ArmorThermalDamageResonance.INSTANCE, PowerLoad.INSTANCE, ShieldEmDamageResonance.INSTANCE, ShieldExplosiveDamageResonance.INSTANCE, ShieldKineticDamageResonance.INSTANCE, Charge.INSTANCE, ShieldThermalDamageResonance.INSTANCE, FwLpKill.INSTANCE, PowerToSpeed.INSTANCE, GeneralMiningHoldCapacity.INSTANCE, IndustrialBonusDroneDamage.INSTANCE, WarpFactor.INSTANCE, RequiredSkill1Level.INSTANCE, RequiredSkill2Level.INSTANCE, DroneCapacity.INSTANCE, MaxVelocity.INSTANCE, Capacity.INSTANCE, RookieDroneBonus.INSTANCE, SignatureRadius.INSTANCE, CpuOutput.INSTANCE, CpuLoad.INSTANCE, ScanResolution.INSTANCE, RechargeRate.INSTANCE, WeaponDisruptionResistance.INSTANCE, StasisWebifierResistance.INSTANCE, Agility.INSTANCE, MaxTargetRange.INSTANCE, ScanSpeed.INSTANCE, WarpSpeedMultiplier.INSTANCE, AllowedInCapIndustrialMaintenanceBay.INSTANCE, LauncherSlotsLeft.INSTANCE, TurretSlotsLeft.INSTANCE, ShipRoleBonusIceHarvestingDuration.INSTANCE, ShipRoleBonusDroneDamage.INSTANCE, ShipRoleBonusDroneHitPoints.INSTANCE, UpgradeCapacity.INSTANCE, MiningBargeBonusOreMiningYield.INSTANCE, KineticDamageResonance.INSTANCE, MiningBargeBonusIceHarvestingDuration.INSTANCE, ThermalDamageResonance.INSTANCE, MiningBargeBonusGasHarvestingDuration.INSTANCE, ExplosiveDamageResonance.INSTANCE, MiningBargeBonusOreMiningRange.INSTANCE, RigSlots.INSTANCE, MiningBargeBonusIceHarvestingRange.INSTANCE, EmDamageResonance.INSTANCE, MiningBargeBonusGeneralMiningHoldCapacity.INSTANCE, MiningBargeBonusShieldCapacity.INSTANCE, ExhumersBonusOreMiningDuration.INSTANCE, MetaLevelOld.INSTANCE, ExhumersBonusIceHarvestingDuration.INSTANCE, MainColor.INSTANCE, ExhumersBonusOreMiningYield.INSTANCE, ExhumersBonusGeneralMiningHoldCapacity.INSTANCE, ExhumersBonusShieldResistance.INSTANCE, MaxPassengers.INSTANCE, UpgradeSlotsLeft.INSTANCE, Uniformity.INSTANCE, MaxDirectionalVelocity.INSTANCE, MinTargetVelDmgMultiplier.INSTANCE, ShipRoleBonusGasHarvesterDuration.INSTANCE, WarpCapacitorNeed.INSTANCE, ExhumersBonusGasHarvestingDuration.INSTANCE, HeatCapacityHi.INSTANCE, HeatDissipationRateHi.INSTANCE, ShipRoleBonusStripMinerActivationCost.INSTANCE, EliteBonusBarge1 .INSTANCE, ShipRoleBonusIceHarvesterActivationCost.INSTANCE, EliteBonusBarge2 .INSTANCE, ShipRoleBonusOreMiningDuration.INSTANCE, ShipBonusORE3 .INSTANCE, AffectedByIndustrialInvulnModule.INSTANCE, Radius.INSTANCE, TechLevel.INSTANCE, HeatDissipationRateMed.INSTANCE, HeatDissipationRateLow.INSTANCE, HeatCapacityMed.INSTANCE, HeatCapacityLow.INSTANCE, RequiredSkill1 .INSTANCE, RequiredSkill2 .INSTANCE, CargoScanResistance.INSTANCE, MaxLockedTargets.INSTANCE, HeatGenerationMultiplier.INSTANCE, MiningAmountMultiplier.INSTANCE, ScanRadarStrength.INSTANCE, ScanLadarStrength.INSTANCE, ScanMagnetometricStrength.INSTANCE, ScanGravimetricStrength.INSTANCE, PropulsionGraphicID.INSTANCE, ShieldRechargeRate.INSTANCE, CapacitorCapacity.INSTANCE, ShieldUniformity.INSTANCE, TypeColorScheme.INSTANCE, HeatAttenuationHi.INSTANCE, HeatAttenuationMed.INSTANCE, HeatAttenuationLow.INSTANCE, GfxBoosterID.INSTANCE, DroneBandwidth.INSTANCE, IsIndustrialJumpConduitPassenger.INSTANCE, IsIndustrialJumpPortalPassenger.INSTANCE, EnergyWarfareResistance.INSTANCE, ShipScanResistance.INSTANCE })));
    public static final Exhumer.MetaGroup METAGROUP = new Exhumer.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  2464 :
            {
                return affectedbyindustrialinvulnmodule;
            }
            case  1891 :
            {
                return allowedincapindustrialmaintenancebay;
            }
            case  924 :
            {
                return elitebonusbarge1;
            }
            case  925 :
            {
                return elitebonusbarge2;
            }
            case  3226 :
            {
                return exhumersbonusgasharvestingduration;
            }
            case  3198 :
            {
                return exhumersbonusgeneralminingholdcapacity;
            }
            case  3194 :
            {
                return exhumersbonusiceharvestingduration;
            }
            case  3193 :
            {
                return exhumersbonusoreminingduration;
            }
            case  3197 :
            {
                return exhumersbonusoreminingyield;
            }
            case  3199 :
            {
                return exhumersbonusshieldresistance;
            }
            case  1555 :
            {
                return fwlpkill;
            }
            case  1556 :
            {
                return generalminingholdcapacity;
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
            case  780 :
            {
                return iceharvestcyclebonus;
            }
            case  2580 :
            {
                return industrialbonusdronedamage;
            }
            case  3324 :
            {
                return isindustrialjumpconduitpassenger;
            }
            case  3325 :
            {
                return isindustrialjumpportalpassenger;
            }
            case  124 :
            {
                return maincolor;
            }
            case  661 :
            {
                return maxdirectionalvelocity;
            }
            case  129 :
            {
                return maxpassengers;
            }
            case  662 :
            {
                return mintargetveldmgmultiplier;
            }
            case  207 :
            {
                return miningamountmultiplier;
            }
            case  3183 :
            {
                return miningbargebonusgasharvestingduration;
            }
            case  3187 :
            {
                return miningbargebonusgeneralminingholdcapacity;
            }
            case  3182 :
            {
                return miningbargebonusiceharvestingduration;
            }
            case  3185 :
            {
                return miningbargebonusiceharvestingrange;
            }
            case  3184 :
            {
                return miningbargebonusoreminingrange;
            }
            case  3181 :
            {
                return miningbargebonusoreminingyield;
            }
            case  3188 :
            {
                return miningbargebonusshieldcapacity;
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
            case  1831 :
            {
                return rookiedronebonus;
            }
            case  564 :
            {
                return scanresolution;
            }
            case  79 :
            {
                return scanspeed;
            }
            case  774 :
            {
                return shipbonusore2;
            }
            case  926 :
            {
                return shipbonusore3;
            }
            case  3179 :
            {
                return shiprolebonusdronedamage;
            }
            case  3180 :
            {
                return shiprolebonusdronehitpoints;
            }
            case  3225 :
            {
                return shiprolebonusgasharvesterduration;
            }
            case  3229 :
            {
                return shiprolebonusiceharvesteractivationcost;
            }
            case  3178 :
            {
                return shiprolebonusiceharvestingduration;
            }
            case  3230 :
            {
                return shiprolebonusoreminingduration;
            }
            case  3228 :
            {
                return shiprolebonusstripmineractivationcost;
            }
            case  511 :
            {
                return shipscanresistance;
            }
            case  1154 :
            {
                return upgradeslotsleft;
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
    public IMetaGroup<Exhumer> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Exhumer>
    {
        public static final String RESOURCE_PATH = "SDE/types/ship/Exhumer.yaml";
        private Map<String, Exhumer> cache = (null);

        @Override
        public IMetaCategory<? super Exhumer> category() {
            return Ship.METACAT;
        }

        @Override
        public int getGroupId() {
            return  543;
        }

        @Override
        public String getName() {
            return "Exhumer";
        }

        @Override
        public synchronized Map<String, Exhumer> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(Exhumer.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<String, Exhumer> types;
        }
    }
}
