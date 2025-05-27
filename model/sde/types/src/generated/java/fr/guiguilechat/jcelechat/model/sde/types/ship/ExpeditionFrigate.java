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
import fr.guiguilechat.jcelechat.model.sde.attributes.CovertOpsAndReconOpsCloakModuleDelay;
import fr.guiguilechat.jcelechat.model.sde.attributes.CovertOpsStealthBomberTargettingDelay;
import fr.guiguilechat.jcelechat.model.sde.attributes.CpuLoad;
import fr.guiguilechat.jcelechat.model.sde.attributes.CpuOutput;
import fr.guiguilechat.jcelechat.model.sde.attributes.Damage;
import fr.guiguilechat.jcelechat.model.sde.attributes.DroneBandwidth;
import fr.guiguilechat.jcelechat.model.sde.attributes.DroneCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.EmDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.EnergyWarfareResistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ExpeditionFrigateBonusIceHarvestingDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.ExpeditionFrigateBonusOreMiningYield;
import fr.guiguilechat.jcelechat.model.sde.attributes.ExpeditionFrigateBonusShieldResistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ExpeditionFrigateBonusSignatureRadius;
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
import fr.guiguilechat.jcelechat.model.sde.attributes.IgnoreCloakVelocityPenalty;
import fr.guiguilechat.jcelechat.model.sde.attributes.IsBlackOpsJumpConduitPassenger;
import fr.guiguilechat.jcelechat.model.sde.attributes.IsBlackOpsJumpPortalPassenger;
import fr.guiguilechat.jcelechat.model.sde.attributes.IsIndustrialJumpConduitPassenger;
import fr.guiguilechat.jcelechat.model.sde.attributes.IsIndustrialJumpPortalPassenger;
import fr.guiguilechat.jcelechat.model.sde.attributes.KineticDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.LauncherSlotsLeft;
import fr.guiguilechat.jcelechat.model.sde.attributes.LowSlots;
import fr.guiguilechat.jcelechat.model.sde.attributes.MainColor;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxDirectionalScanRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxDirectionalVelocity;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxLockedTargets;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxPassengers;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxTargetRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxVelocity;
import fr.guiguilechat.jcelechat.model.sde.attributes.MedSlots;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaLevelOld;
import fr.guiguilechat.jcelechat.model.sde.attributes.MinTargetVelDmgMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.MiningAmountMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.MiningFrigateBonusGasCloudHarvestingDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.MiningFrigateBonusIceHarvestingDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.MiningFrigatesBonusOreMiningYield;
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
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusRole7;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipRoleBonusGasHarvestingYield;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipRoleBonusIceHarvestingDuration;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipRoleBonusOreMiningYield;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipScanResistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.SignatureRadius;
import fr.guiguilechat.jcelechat.model.sde.attributes.SkillLevel;
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

public class ExpeditionFrigate
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
    @Stackable(false)
    @DefaultIntValue(30000)
    public int covertopsandreconopscloakmoduledelay;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(20000)
    public int covertopsstealthbombertargettingdelay;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int expeditionfrigatebonusiceharvestingduration;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int expeditionfrigatebonusoreminingyield;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int expeditionfrigatebonusshieldresistance;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int expeditionfrigatebonussignatureradius;
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
     * tbd
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int hislots;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ignorecloakvelocitypenalty;
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
    public int miningfrigatebonusgascloudharvestingduration;
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int miningfrigatebonusiceharvestingduration;
    /**
     * ORE Mining frigate bonus 1
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int miningfrigatesbonusoreminingyield;
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
    /**
     * Fixed Role Bonus on a ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shipbonusrole7;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shiprolebonusgasharvestingyield;
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shiprolebonusiceharvestingduration;
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shiprolebonusoreminingyield;
    /**
     * Chance of being able to resist a ship scan.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipscanresistance;
    /**
     * Level of skill
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int skilllevel;
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
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {BaseWarpSpeed.INSTANCE, Damage.INSTANCE, ShieldCapacity.INSTANCE, ShieldCharge.INSTANCE, Hp.INSTANCE, ArmorHP.INSTANCE, CovertOpsAndReconOpsCloakModuleDelay.INSTANCE, RigSize.INSTANCE, CovertOpsStealthBomberTargettingDelay.INSTANCE, PowerOutput.INSTANCE, ArmorEmDamageResonance.INSTANCE, LowSlots.INSTANCE, ArmorUniformity.INSTANCE, ArmorExplosiveDamageResonance.INSTANCE, MedSlots.INSTANCE, StructureUniformity.INSTANCE, ArmorKineticDamageResonance.INSTANCE, HiSlots.INSTANCE, ArmorThermalDamageResonance.INSTANCE, PowerLoad.INSTANCE, ShieldEmDamageResonance.INSTANCE, ShieldExplosiveDamageResonance.INSTANCE, ShieldKineticDamageResonance.INSTANCE, Charge.INSTANCE, ShieldThermalDamageResonance.INSTANCE, FwLpKill.INSTANCE, PowerToSpeed.INSTANCE, GeneralMiningHoldCapacity.INSTANCE, RequiredSkill1Level.INSTANCE, WarpFactor.INSTANCE, RequiredSkill2Level.INSTANCE, SkillLevel.INSTANCE, ShipBonusRole7 .INSTANCE, DroneCapacity.INSTANCE, MaxVelocity.INSTANCE, Capacity.INSTANCE, SignatureRadius.INSTANCE, CpuOutput.INSTANCE, CpuLoad.INSTANCE, MiningFrigatesBonusOreMiningYield.INSTANCE, ScanResolution.INSTANCE, IgnoreCloakVelocityPenalty.INSTANCE, RechargeRate.INSTANCE, WeaponDisruptionResistance.INSTANCE, StasisWebifierResistance.INSTANCE, Agility.INSTANCE, MaxTargetRange.INSTANCE, ScanSpeed.INSTANCE, WarpSpeedMultiplier.INSTANCE, ExpeditionFrigateBonusIceHarvestingDuration.INSTANCE, AllowedInCapIndustrialMaintenanceBay.INSTANCE, LauncherSlotsLeft.INSTANCE, TurretSlotsLeft.INSTANCE, ShipRoleBonusOreMiningYield.INSTANCE, ShipRoleBonusIceHarvestingDuration.INSTANCE, UpgradeCapacity.INSTANCE, KineticDamageResonance.INSTANCE, ThermalDamageResonance.INSTANCE, ExplosiveDamageResonance.INSTANCE, RigSlots.INSTANCE, EmDamageResonance.INSTANCE, ExpeditionFrigateBonusSignatureRadius.INSTANCE, ExpeditionFrigateBonusOreMiningYield.INSTANCE, ExpeditionFrigateBonusShieldResistance.INSTANCE, MetaLevelOld.INSTANCE, MainColor.INSTANCE, MaxPassengers.INSTANCE, UpgradeSlotsLeft.INSTANCE, Uniformity.INSTANCE, MaxDirectionalVelocity.INSTANCE, MinTargetVelDmgMultiplier.INSTANCE, WarpCapacitorNeed.INSTANCE, HeatCapacityHi.INSTANCE, HeatDissipationRateHi.INSTANCE, AffectedByIndustrialInvulnModule.INSTANCE, Radius.INSTANCE, MaxDirectionalScanRange.INSTANCE, MiningFrigateBonusGasCloudHarvestingDuration.INSTANCE, TechLevel.INSTANCE, ShipRoleBonusGasHarvestingYield.INSTANCE, MiningFrigateBonusIceHarvestingDuration.INSTANCE, HeatDissipationRateMed.INSTANCE, HeatDissipationRateLow.INSTANCE, HeatCapacityMed.INSTANCE, HeatCapacityLow.INSTANCE, RequiredSkill1 .INSTANCE, RequiredSkill2 .INSTANCE, CargoScanResistance.INSTANCE, MaxLockedTargets.INSTANCE, HeatGenerationMultiplier.INSTANCE, MiningAmountMultiplier.INSTANCE, ScanRadarStrength.INSTANCE, ScanLadarStrength.INSTANCE, ScanMagnetometricStrength.INSTANCE, ScanGravimetricStrength.INSTANCE, PropulsionGraphicID.INSTANCE, ShieldRechargeRate.INSTANCE, CapacitorCapacity.INSTANCE, ShieldUniformity.INSTANCE, TypeColorScheme.INSTANCE, HeatAttenuationHi.INSTANCE, HeatAttenuationMed.INSTANCE, HeatAttenuationLow.INSTANCE, GfxBoosterID.INSTANCE, DroneBandwidth.INSTANCE, IsBlackOpsJumpPortalPassenger.INSTANCE, IsBlackOpsJumpConduitPassenger.INSTANCE, IsIndustrialJumpConduitPassenger.INSTANCE, IsIndustrialJumpPortalPassenger.INSTANCE, EnergyWarfareResistance.INSTANCE, ShipScanResistance.INSTANCE })));
    public static final ExpeditionFrigate.MetaGroup METAGROUP = new ExpeditionFrigate.MetaGroup();

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
            case  1034 :
            {
                return covertopsandreconopscloakmoduledelay;
            }
            case  1035 :
            {
                return covertopsstealthbombertargettingdelay;
            }
            case  3167 :
            {
                return expeditionfrigatebonusiceharvestingduration;
            }
            case  3191 :
            {
                return expeditionfrigatebonusoreminingyield;
            }
            case  3192 :
            {
                return expeditionfrigatebonusshieldresistance;
            }
            case  3190 :
            {
                return expeditionfrigatebonussignatureradius;
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
            case  14 :
            {
                return hislots;
            }
            case  2102 :
            {
                return ignorecloakvelocitypenalty;
            }
            case  3322 :
            {
                return isblackopsjumpconduitpassenger;
            }
            case  3320 :
            {
                return isblackopsjumpportalpassenger;
            }
            case  3324 :
            {
                return isindustrialjumpconduitpassenger;
            }
            case  3325 :
            {
                return isindustrialjumpportalpassenger;
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
            case  129 :
            {
                return maxpassengers;
            }
            case  13 :
            {
                return medslots;
            }
            case  662 :
            {
                return mintargetveldmgmultiplier;
            }
            case  207 :
            {
                return miningamountmultiplier;
            }
            case  3237 :
            {
                return miningfrigatebonusgascloudharvestingduration;
            }
            case  3240 :
            {
                return miningfrigatebonusiceharvestingduration;
            }
            case  1842 :
            {
                return miningfrigatesbonusoreminingyield;
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
            case  793 :
            {
                return shipbonusrole7;
            }
            case  3239 :
            {
                return shiprolebonusgasharvestingyield;
            }
            case  3178 :
            {
                return shiprolebonusiceharvestingduration;
            }
            case  3177 :
            {
                return shiprolebonusoreminingyield;
            }
            case  511 :
            {
                return shipscanresistance;
            }
            case  280 :
            {
                return skilllevel;
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
    public IMetaGroup<ExpeditionFrigate> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<ExpeditionFrigate>
    {
        public static final String RESOURCE_PATH = "SDE/types/ship/ExpeditionFrigate.yaml";
        private Map<Integer, ExpeditionFrigate> cache = (null);

        @Override
        public IMetaCategory<? super ExpeditionFrigate> category() {
            return Ship.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1283;
        }

        @Override
        public String getName() {
            return "ExpeditionFrigate";
        }

        @Override
        public synchronized Map<Integer, ExpeditionFrigate> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(ExpeditionFrigate.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, ExpeditionFrigate> types;
        }
    }
}
