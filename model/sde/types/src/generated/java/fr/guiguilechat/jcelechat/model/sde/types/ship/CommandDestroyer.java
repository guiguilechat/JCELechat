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
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.CargoScanResistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.Charge;
import fr.guiguilechat.jcelechat.model.sde.attributes.CpuLoad;
import fr.guiguilechat.jcelechat.model.sde.attributes.CpuOutput;
import fr.guiguilechat.jcelechat.model.sde.attributes.Damage;
import fr.guiguilechat.jcelechat.model.sde.attributes.DestroyerROFpenality;
import fr.guiguilechat.jcelechat.model.sde.attributes.DroneBandwidth;
import fr.guiguilechat.jcelechat.model.sde.attributes.DroneCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.EliteBonusCommandDestroyer1;
import fr.guiguilechat.jcelechat.model.sde.attributes.EliteBonusCommandDestroyer2;
import fr.guiguilechat.jcelechat.model.sde.attributes.EmDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.EnergyWarfareResistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ExplosiveDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.FwLpKill;
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
import fr.guiguilechat.jcelechat.model.sde.attributes.HullEmDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.HullExplosiveDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.HullKineticDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.HullThermalDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.KineticDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.LauncherSlotsLeft;
import fr.guiguilechat.jcelechat.model.sde.attributes.LowSlots;
import fr.guiguilechat.jcelechat.model.sde.attributes.MWDSignatureRadiusBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.MainColor;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxDirectionalVelocity;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxLockedTargets;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxPassengers;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxTargetRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxVelocity;
import fr.guiguilechat.jcelechat.model.sde.attributes.MedSlots;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaLevelOld;
import fr.guiguilechat.jcelechat.model.sde.attributes.MinTargetVelDmgMultiplier;
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
import fr.guiguilechat.jcelechat.model.sde.attributes.RoleBonusCD;
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
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusAD1;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusAD2;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusCD1;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusCD2;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusGD1;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusGD2;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusMD1;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusMD2;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusPD1;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusPD2;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusRole1;
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipBonusRole2;
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

public class CommandDestroyer
    extends Ship
{
    /**
     * reduction in MicroWarp Drive signature
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int mwdsignatureradiusbonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double destroyerrofpenality;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int elitebonuscommanddestroyer1;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int elitebonuscommanddestroyer2;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fwlpkill;
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
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double hullemdamageresonance;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double hullexplosivedamageresonance;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double hullkineticdamageresonance;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double hullthermaldamageresonance;
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
     * role bonus for command destroyers
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rolebonuscd;
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
    @DefaultIntValue(0)
    public int shipbonusad1;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonusad2;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonuscd1;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonuscd2;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shipbonusgd1;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonusgd2;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonusmd1;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonusmd2;
    /**
     * Precursor Destroyer Skill Attribute
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonuspd1;
    /**
     * Precursor Destroyer Skill Attribute
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonuspd2;
    /**
     * Ship Role Bonus. Not multiplied by skills.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shipbonusrole1;
    /**
     * Ship Role Bonus. Not multiplied by skills.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipbonusrole2;
    /**
     * Chance of being able to resist a ship scan.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int shipscanresistance;
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
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {BaseWarpSpeed.INSTANCE, Damage.INSTANCE, ShieldCapacity.INSTANCE, ShieldCharge.INSTANCE, Hp.INSTANCE, ArmorHP.INSTANCE, RigSize.INSTANCE, MWDSignatureRadiusBonus.INSTANCE, EliteBonusCommandDestroyer1 .INSTANCE, PowerOutput.INSTANCE, ArmorEmDamageResonance.INSTANCE, LowSlots.INSTANCE, EliteBonusCommandDestroyer2 .INSTANCE, ArmorUniformity.INSTANCE, ArmorExplosiveDamageResonance.INSTANCE, MedSlots.INSTANCE, StructureUniformity.INSTANCE, ArmorKineticDamageResonance.INSTANCE, HiSlots.INSTANCE, ArmorThermalDamageResonance.INSTANCE, PowerLoad.INSTANCE, ShieldEmDamageResonance.INSTANCE, RoleBonusCD.INSTANCE, ShieldExplosiveDamageResonance.INSTANCE, ShieldKineticDamageResonance.INSTANCE, Charge.INSTANCE, ShieldThermalDamageResonance.INSTANCE, FwLpKill.INSTANCE, PowerToSpeed.INSTANCE, RequiredSkill1Level.INSTANCE, WarpFactor.INSTANCE, RequiredSkill2Level.INSTANCE, DroneCapacity.INSTANCE, MaxVelocity.INSTANCE, Capacity.INSTANCE, SignatureRadius.INSTANCE, CpuOutput.INSTANCE, CpuLoad.INSTANCE, ScanResolution.INSTANCE, RechargeRate.INSTANCE, WeaponDisruptionResistance.INSTANCE, StasisWebifierResistance.INSTANCE, Agility.INSTANCE, MaxTargetRange.INSTANCE, ScanSpeed.INSTANCE, WarpSpeedMultiplier.INSTANCE, ShipBonusAD1 .INSTANCE, ShipBonusAD2 .INSTANCE, LauncherSlotsLeft.INSTANCE, TurretSlotsLeft.INSTANCE, UpgradeCapacity.INSTANCE, KineticDamageResonance.INSTANCE, ThermalDamageResonance.INSTANCE, ExplosiveDamageResonance.INSTANCE, RigSlots.INSTANCE, EmDamageResonance.INSTANCE, MetaLevelOld.INSTANCE, MainColor.INSTANCE, MaxPassengers.INSTANCE, UpgradeSlotsLeft.INSTANCE, Uniformity.INSTANCE, MaxDirectionalVelocity.INSTANCE, MinTargetVelDmgMultiplier.INSTANCE, WarpCapacitorNeed.INSTANCE, HeatCapacityHi.INSTANCE, HeatDissipationRateHi.INSTANCE, Radius.INSTANCE, TechLevel.INSTANCE, HeatDissipationRateMed.INSTANCE, HeatDissipationRateLow.INSTANCE, HeatCapacityMed.INSTANCE, HeatCapacityLow.INSTANCE, RequiredSkill1 .INSTANCE, RequiredSkill2 .INSTANCE, CargoScanResistance.INSTANCE, MaxLockedTargets.INSTANCE, HeatGenerationMultiplier.INSTANCE, HullEmDamageResonance.INSTANCE, HullExplosiveDamageResonance.INSTANCE, HullKineticDamageResonance.INSTANCE, ScanRadarStrength.INSTANCE, HullThermalDamageResonance.INSTANCE, ScanLadarStrength.INSTANCE, ScanMagnetometricStrength.INSTANCE, ScanGravimetricStrength.INSTANCE, DestroyerROFpenality.INSTANCE, ShipBonusMD1 .INSTANCE, PropulsionGraphicID.INSTANCE, ShipBonusCD1 .INSTANCE, ShipBonusCD2 .INSTANCE, ShieldRechargeRate.INSTANCE, ShipBonusGD1 .INSTANCE, CapacitorCapacity.INSTANCE, ShipBonusGD2 .INSTANCE, ShipBonusMD2 .INSTANCE, ShieldUniformity.INSTANCE, TypeColorScheme.INSTANCE, HeatAttenuationHi.INSTANCE, HeatAttenuationMed.INSTANCE, HeatAttenuationLow.INSTANCE, ShipBonusPD1 .INSTANCE, ShipBonusPD2 .INSTANCE, GfxBoosterID.INSTANCE, DroneBandwidth.INSTANCE, ShipBonusRole1 .INSTANCE, ShipBonusRole2 .INSTANCE, EnergyWarfareResistance.INSTANCE, ShipScanResistance.INSTANCE })));
    public static final CommandDestroyer.MetaGroup METAGROUP = new CommandDestroyer.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  1803 :
            {
                return mwdsignatureradiusbonus;
            }
            case  727 :
            {
                return destroyerrofpenality;
            }
            case  2059 :
            {
                return elitebonuscommanddestroyer1;
            }
            case  2060 :
            {
                return elitebonuscommanddestroyer2;
            }
            case  1555 :
            {
                return fwlpkill;
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
            case  974 :
            {
                return hullemdamageresonance;
            }
            case  975 :
            {
                return hullexplosivedamageresonance;
            }
            case  976 :
            {
                return hullkineticdamageresonance;
            }
            case  977 :
            {
                return hullthermaldamageresonance;
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
            case  2064 :
            {
                return rolebonuscd;
            }
            case  564 :
            {
                return scanresolution;
            }
            case  79 :
            {
                return scanspeed;
            }
            case  1887 :
            {
                return shipbonusad1;
            }
            case  1888 :
            {
                return shipbonusad2;
            }
            case  734 :
            {
                return shipbonuscd1;
            }
            case  735 :
            {
                return shipbonuscd2;
            }
            case  738 :
            {
                return shipbonusgd1;
            }
            case  739 :
            {
                return shipbonusgd2;
            }
            case  729 :
            {
                return shipbonusmd1;
            }
            case  740 :
            {
                return shipbonusmd2;
            }
            case  2799 :
            {
                return shipbonuspd1;
            }
            case  2800 :
            {
                return shipbonuspd2;
            }
            case  2298 :
            {
                return shipbonusrole1;
            }
            case  2299 :
            {
                return shipbonusrole2;
            }
            case  511 :
            {
                return shipscanresistance;
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
    public IMetaGroup<CommandDestroyer> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<CommandDestroyer>
    {
        public static final String RESOURCE_PATH = "SDE/types/ship/CommandDestroyer.yaml";
        private Map<Integer, CommandDestroyer> cache = (null);

        @Override
        public IMetaCategory<? super CommandDestroyer> category() {
            return Ship.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1534;
        }

        @Override
        public String getName() {
            return "CommandDestroyer";
        }

        @Override
        public synchronized Map<Integer, CommandDestroyer> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(CommandDestroyer.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, CommandDestroyer> types;
        }
    }
}
