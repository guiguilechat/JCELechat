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
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
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
import fr.guiguilechat.jcelechat.model.sde.attributes.Charge;
import fr.guiguilechat.jcelechat.model.sde.attributes.CpuLoad;
import fr.guiguilechat.jcelechat.model.sde.attributes.CpuOutput;
import fr.guiguilechat.jcelechat.model.sde.attributes.Damage;
import fr.guiguilechat.jcelechat.model.sde.attributes.DroneBandwidth;
import fr.guiguilechat.jcelechat.model.sde.attributes.DroneCapacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.EmDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.ExplosiveDamageResonance;
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
import fr.guiguilechat.jcelechat.model.sde.attributes.KineticDamageResonance;
import fr.guiguilechat.jcelechat.model.sde.attributes.LauncherSlotsLeft;
import fr.guiguilechat.jcelechat.model.sde.attributes.LowSlots;
import fr.guiguilechat.jcelechat.model.sde.attributes.MainColor;
import fr.guiguilechat.jcelechat.model.sde.attributes.Mass;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxDirectionalVelocity;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxLockedTargets;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxPassengers;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxTargetRange;
import fr.guiguilechat.jcelechat.model.sde.attributes.MaxVelocity;
import fr.guiguilechat.jcelechat.model.sde.attributes.MedSlots;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaGroupID;
import fr.guiguilechat.jcelechat.model.sde.attributes.MetaLevel;
import fr.guiguilechat.jcelechat.model.sde.attributes.MinTargetVelDmgMultiplier;
import fr.guiguilechat.jcelechat.model.sde.attributes.PowerLoad;
import fr.guiguilechat.jcelechat.model.sde.attributes.PowerOutput;
import fr.guiguilechat.jcelechat.model.sde.attributes.PowerToSpeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.PropulsionGraphicID;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.attributes.RechargeRate;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1;
import fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level;
import fr.guiguilechat.jcelechat.model.sde.attributes.RigSize;
import fr.guiguilechat.jcelechat.model.sde.attributes.RigSlots;
import fr.guiguilechat.jcelechat.model.sde.attributes.RookieArmorRepBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.RookieArmorResistanceBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.RookieDampStrengthBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.RookieDroneBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.RookieDroneMWDspeed;
import fr.guiguilechat.jcelechat.model.sde.attributes.RookieECMStrengthBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.RookieLightMissileVelocity;
import fr.guiguilechat.jcelechat.model.sde.attributes.RookieMissileKinDamageBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.RookieNeutDrain;
import fr.guiguilechat.jcelechat.model.sde.attributes.RookieNosDrain;
import fr.guiguilechat.jcelechat.model.sde.attributes.RookieRocketVelocity;
import fr.guiguilechat.jcelechat.model.sde.attributes.RookieSETCapBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.RookieSETDamageBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.RookieSETOptimal;
import fr.guiguilechat.jcelechat.model.sde.attributes.RookieSETTracking;
import fr.guiguilechat.jcelechat.model.sde.attributes.RookieSHTDamageBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.RookieSHTFalloff;
import fr.guiguilechat.jcelechat.model.sde.attributes.RookieSHTOptimalBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.RookieSHTTracking;
import fr.guiguilechat.jcelechat.model.sde.attributes.RookieSPTDamageBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.RookieSPTFalloff;
import fr.guiguilechat.jcelechat.model.sde.attributes.RookieSPTOptimal;
import fr.guiguilechat.jcelechat.model.sde.attributes.RookieSPTTracking;
import fr.guiguilechat.jcelechat.model.sde.attributes.RookieShieldBoostBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.RookieShieldResistBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.RookieShipVelocityBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.RookieTargetPainterStrengthBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.RookieWeaponDisruptionBonus;
import fr.guiguilechat.jcelechat.model.sde.attributes.RookieWebAmount;
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
import fr.guiguilechat.jcelechat.model.sde.attributes.ShipScanResistance;
import fr.guiguilechat.jcelechat.model.sde.attributes.SignatureRadius;
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
import fr.guiguilechat.jcelechat.model.sde.types.Ship;
import org.yaml.snakeyaml.Yaml;

public class Corvette
    extends Ship
{
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double heatattenuationhi;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double heatattenuationlow;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double heatattenuationmed;
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
    @DefaultDoubleValue(1.0)
    public double mintargetveldmgmultiplier;
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
     * Bonus to armor repair amount
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rookiearmorrepbonus;
    /**
     * Bonus to armor resistances
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rookiearmorresistancebonus;
    /**
     * Bonus to sensor damper effectiveness
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rookiedampstrengthbonus;
    /**
     * Bonus to drone damage, HP and mining yield
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rookiedronebonus;
    /**
     * Increase in Drone MWD speed
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rookiedronemwdspeed;
    /**
     * ECM Strength Bonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rookieecmstrengthbonus;
    /**
     * Increase in Light Missile velocity
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rookielightmissilevelocity;
    /**
     * Bonus to kinetic missile damage
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rookiemissilekindamagebonus;
    /**
     * Increase in Energy Neutralizer drain amount
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rookieneutdrain;
    /**
     * Increase in Nosferatu drain amount
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rookienosdrain;
    /**
     * Increase in Rocket velocity
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rookierocketvelocity;
    /**
     * Reduction in energy turret capacitor use
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rookiesetcapbonus;
    /**
     * Energy turret damage bonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rookiesetdamagebonus;
    /**
     * Increase in Small Energy Turret optimal Range
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rookiesetoptimal;
    /**
     * Increase in small energy turret tracking
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double rookiesettracking;
    /**
     * Bonus to Small Hybrid Turret damage
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rookieshtdamagebonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rookieshtfalloff;
    /**
     * Small Hybrid Turret optimal range bonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rookieshtoptimalbonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double rookieshttracking;
    /**
     * Bonus to Small Projectile Turret damage
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rookiesptdamagebonus;
    /**
     * Increase in Small Projectile Turret falloff
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double rookiesptfalloff;
    /**
     * Increase in Small Projectile Turret optimal range
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double rookiesptoptimal;
    /**
     * Increase in Small Projectile Turret tracking
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double rookiespttracking;
    /**
     * Bonus to shield booster repair amount
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double rookieshieldboostbonus;
    /**
     * Shield resistance bonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rookieshieldresistbonus;
    /**
     * Bonus to ship velocity
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rookieshipvelocitybonus;
    /**
     * Bonus to target painter effectiveness
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rookietargetpainterstrengthbonus;
    /**
     * Bonus to tracking disruptor effectiveness
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rookieweapondisruptionbonus;
    /**
     * Increase in Statis Webifier speed reduction
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int rookiewebamount;
    /**
     * The resolution that the vessel can target other objects at.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double scanresolution;
    /**
     * scanning speed in milliseconds
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int scanspeed;
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
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {BaseWarpSpeed.INSTANCE, Damage.INSTANCE, Mass.INSTANCE, ShieldCapacity.INSTANCE, ShieldCharge.INSTANCE, Hp.INSTANCE, ArmorHP.INSTANCE, RigSize.INSTANCE, PowerOutput.INSTANCE, ArmorEmDamageResonance.INSTANCE, LowSlots.INSTANCE, ArmorUniformity.INSTANCE, ArmorExplosiveDamageResonance.INSTANCE, MedSlots.INSTANCE, StructureUniformity.INSTANCE, ArmorKineticDamageResonance.INSTANCE, HiSlots.INSTANCE, ArmorThermalDamageResonance.INSTANCE, PowerLoad.INSTANCE, ShieldEmDamageResonance.INSTANCE, ShieldExplosiveDamageResonance.INSTANCE, ShieldKineticDamageResonance.INSTANCE, Charge.INSTANCE, ShieldThermalDamageResonance.INSTANCE, PowerToSpeed.INSTANCE, WarpFactor.INSTANCE, RequiredSkill1Level.INSTANCE, DroneCapacity.INSTANCE, RookieSETCapBonus.INSTANCE, RookieSETDamageBonus.INSTANCE, RookieWeaponDisruptionBonus.INSTANCE, RookieArmorResistanceBonus.INSTANCE, RookieSHTOptimalBonus.INSTANCE, RookieMissileKinDamageBonus.INSTANCE, RookieECMStrengthBonus.INSTANCE, RookieShieldResistBonus.INSTANCE, MaxVelocity.INSTANCE, RookieSHTDamageBonus.INSTANCE, Capacity.INSTANCE, RookieDroneBonus.INSTANCE, RookieDampStrengthBonus.INSTANCE, SignatureRadius.INSTANCE, RookieArmorRepBonus.INSTANCE, RookieTargetPainterStrengthBonus.INSTANCE, RookieShipVelocityBonus.INSTANCE, RookieSPTDamageBonus.INSTANCE, RookieShieldBoostBonus.INSTANCE, CpuOutput.INSTANCE, CpuLoad.INSTANCE, ScanResolution.INSTANCE, RechargeRate.INSTANCE, RookieSETTracking.INSTANCE, RookieSETOptimal.INSTANCE, RookieNosDrain.INSTANCE, RookieNeutDrain.INSTANCE, RookieWebAmount.INSTANCE, RookieLightMissileVelocity.INSTANCE, Agility.INSTANCE, RookieRocketVelocity.INSTANCE, RookieDroneMWDspeed.INSTANCE, RookieSHTTracking.INSTANCE, RookieSHTFalloff.INSTANCE, RookieSPTTracking.INSTANCE, RookieSPTFalloff.INSTANCE, MaxTargetRange.INSTANCE, RookieSPTOptimal.INSTANCE, ScanSpeed.INSTANCE, WarpSpeedMultiplier.INSTANCE, LauncherSlotsLeft.INSTANCE, TurretSlotsLeft.INSTANCE, UpgradeCapacity.INSTANCE, KineticDamageResonance.INSTANCE, ThermalDamageResonance.INSTANCE, ExplosiveDamageResonance.INSTANCE, RigSlots.INSTANCE, EmDamageResonance.INSTANCE, MetaLevel.INSTANCE, MainColor.INSTANCE, MaxPassengers.INSTANCE, UpgradeSlotsLeft.INSTANCE, Uniformity.INSTANCE, MaxDirectionalVelocity.INSTANCE, MinTargetVelDmgMultiplier.INSTANCE, WarpCapacitorNeed.INSTANCE, HeatCapacityHi.INSTANCE, HeatDissipationRateHi.INSTANCE, MetaGroupID.INSTANCE, Radius.INSTANCE, TechLevel.INSTANCE, HeatDissipationRateMed.INSTANCE, HeatDissipationRateLow.INSTANCE, HeatCapacityMed.INSTANCE, HeatCapacityLow.INSTANCE, RequiredSkill1 .INSTANCE, MaxLockedTargets.INSTANCE, HeatGenerationMultiplier.INSTANCE, ScanRadarStrength.INSTANCE, ScanLadarStrength.INSTANCE, ScanMagnetometricStrength.INSTANCE, ScanGravimetricStrength.INSTANCE, PropulsionGraphicID.INSTANCE, ShieldRechargeRate.INSTANCE, CapacitorCapacity.INSTANCE, ShieldUniformity.INSTANCE, TypeColorScheme.INSTANCE, HeatAttenuationHi.INSTANCE, HeatAttenuationMed.INSTANCE, HeatAttenuationLow.INSTANCE, GfxBoosterID.INSTANCE, DroneBandwidth.INSTANCE, ShipScanResistance.INSTANCE })));
    public static final Corvette.MetaGroup METAGROUP = new Corvette.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
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
            case  1692 :
            {
                return metagroupid;
            }
            case  662 :
            {
                return mintargetveldmgmultiplier;
            }
            case  1547 :
            {
                return rigsize;
            }
            case  1137 :
            {
                return rigslots;
            }
            case  1833 :
            {
                return rookiearmorrepbonus;
            }
            case  1825 :
            {
                return rookiearmorresistancebonus;
            }
            case  1832 :
            {
                return rookiedampstrengthbonus;
            }
            case  1831 :
            {
                return rookiedronebonus;
            }
            case  1864 :
            {
                return rookiedronemwdspeed;
            }
            case  1828 :
            {
                return rookieecmstrengthbonus;
            }
            case  1862 :
            {
                return rookielightmissilevelocity;
            }
            case  1827 :
            {
                return rookiemissilekindamagebonus;
            }
            case  1860 :
            {
                return rookieneutdrain;
            }
            case  1859 :
            {
                return rookienosdrain;
            }
            case  1863 :
            {
                return rookierocketvelocity;
            }
            case  1822 :
            {
                return rookiesetcapbonus;
            }
            case  1823 :
            {
                return rookiesetdamagebonus;
            }
            case  1858 :
            {
                return rookiesetoptimal;
            }
            case  1857 :
            {
                return rookiesettracking;
            }
            case  1830 :
            {
                return rookieshtdamagebonus;
            }
            case  1866 :
            {
                return rookieshtfalloff;
            }
            case  1826 :
            {
                return rookieshtoptimalbonus;
            }
            case  1865 :
            {
                return rookieshttracking;
            }
            case  1836 :
            {
                return rookiesptdamagebonus;
            }
            case  1868 :
            {
                return rookiesptfalloff;
            }
            case  1869 :
            {
                return rookiesptoptimal;
            }
            case  1867 :
            {
                return rookiespttracking;
            }
            case  1837 :
            {
                return rookieshieldboostbonus;
            }
            case  1829 :
            {
                return rookieshieldresistbonus;
            }
            case  1835 :
            {
                return rookieshipvelocitybonus;
            }
            case  1834 :
            {
                return rookietargetpainterstrengthbonus;
            }
            case  1824 :
            {
                return rookieweapondisruptionbonus;
            }
            case  1861 :
            {
                return rookiewebamount;
            }
            case  564 :
            {
                return scanresolution;
            }
            case  79 :
            {
                return scanspeed;
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
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaGroup<Corvette> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Corvette>
    {
        public static final String RESOURCE_PATH = "SDE/types/ship/Corvette.yaml";
        private Map<String, Corvette> cache = (null);

        @Override
        public IMetaCategory<? super Corvette> category() {
            return Ship.METACAT;
        }

        @Override
        public int getGroupId() {
            return  237;
        }

        @Override
        public String getName() {
            return "Corvette";
        }

        @Override
        public synchronized Map<String, Corvette> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(Corvette.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    cache = new Yaml().loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, Corvette> types;
        }
    }
}
