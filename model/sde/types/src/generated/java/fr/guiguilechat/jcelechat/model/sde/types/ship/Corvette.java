package fr.guiguilechat.jcelechat.model.sde.types.ship;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultRealValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.*;
import fr.guiguilechat.jcelechat.model.sde.types.Ship;

public class Corvette
    extends Ship
{
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
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int integratedminingscanner;
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
    @DefaultRealValue(0.0)
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
    @DefaultRealValue(0.0)
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
    @DefaultRealValue(0.0)
    public double rookiesptfalloff;
    /**
     * Increase in Small Projectile Turret optimal range
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double rookiesptoptimal;
    /**
     * Increase in Small Projectile Turret tracking
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double rookiespttracking;
    /**
     * Bonus to shield booster repair amount
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
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
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {BaseWarpSpeed.INSTANCE, Damage.INSTANCE, ShieldCapacity.INSTANCE, Hp.INSTANCE, ArmorHP.INSTANCE, RigSize.INSTANCE, PowerOutput.INSTANCE, ArmorEmDamageResonance.INSTANCE, LowSlots.INSTANCE, ArmorExplosiveDamageResonance.INSTANCE, ArmorUniformity.INSTANCE, MedSlots.INSTANCE, ArmorKineticDamageResonance.INSTANCE, StructureUniformity.INSTANCE, HiSlots.INSTANCE, ArmorThermalDamageResonance.INSTANCE, PowerLoad.INSTANCE, ShieldEmDamageResonance.INSTANCE, ShieldExplosiveDamageResonance.INSTANCE, ShieldKineticDamageResonance.INSTANCE, ShieldThermalDamageResonance.INSTANCE, PowerToSpeed.INSTANCE, RequiredSkill1Level.INSTANCE, WarpFactor.INSTANCE, DroneCapacity.INSTANCE, RookieSETCapBonus.INSTANCE, RookieSETDamageBonus.INSTANCE, RookieWeaponDisruptionBonus.INSTANCE, RookieArmorResistanceBonus.INSTANCE, RookieSHTOptimalBonus.INSTANCE, RookieMissileKinDamageBonus.INSTANCE, RookieECMStrengthBonus.INSTANCE, RookieShieldResistBonus.INSTANCE, MaxVelocity.INSTANCE, RookieSHTDamageBonus.INSTANCE, RookieDroneBonus.INSTANCE, RookieDampStrengthBonus.INSTANCE, SignatureRadius.INSTANCE, RookieArmorRepBonus.INSTANCE, RookieTargetPainterStrengthBonus.INSTANCE, RookieShipVelocityBonus.INSTANCE, RookieSPTDamageBonus.INSTANCE, RookieShieldBoostBonus.INSTANCE, CpuOutput.INSTANCE, CpuLoad.INSTANCE, ScanResolution.INSTANCE, RechargeRate.INSTANCE, SensorDampenerResistance.INSTANCE, RookieSETTracking.INSTANCE, WeaponDisruptionResistance.INSTANCE, RookieSETOptimal.INSTANCE, RookieNosDrain.INSTANCE, StasisWebifierResistance.INSTANCE, RookieNeutDrain.INSTANCE, RookieWebAmount.INSTANCE, RookieLightMissileVelocity.INSTANCE, Agility.INSTANCE, RookieRocketVelocity.INSTANCE, RookieDroneMWDspeed.INSTANCE, RookieSHTTracking.INSTANCE, RookieSHTFalloff.INSTANCE, RookieSPTTracking.INSTANCE, RookieSPTFalloff.INSTANCE, MaxTargetRange.INSTANCE, RookieSPTOptimal.INSTANCE, ScanSpeed.INSTANCE, WarpSpeedMultiplier.INSTANCE, IntegratedMiningScanner.INSTANCE, LauncherSlotsLeft.INSTANCE, TurretSlotsLeft.INSTANCE, UpgradeCapacity.INSTANCE, KineticDamageResonance.INSTANCE, ThermalDamageResonance.INSTANCE, ExplosiveDamageResonance.INSTANCE, RigSlots.INSTANCE, EmDamageResonance.INSTANCE, MetaLevelOld.INSTANCE, MainColor.INSTANCE, MaxPassengers.INSTANCE, UpgradeSlotsLeft.INSTANCE, Uniformity.INSTANCE, MaxDirectionalVelocity.INSTANCE, MinTargetVelDmgMultiplier.INSTANCE, WarpCapacitorNeed.INSTANCE, HeatCapacityHi.INSTANCE, HeatDissipationRateHi.INSTANCE, MetaGroupID.INSTANCE, MaxDirectionalScanRange.INSTANCE, TechLevel.INSTANCE, HeatDissipationRateMed.INSTANCE, HeatDissipationRateLow.INSTANCE, HeatCapacityMed.INSTANCE, HeatCapacityLow.INSTANCE, RequiredSkill1 .INSTANCE, CargoScanResistance.INSTANCE, MaxLockedTargets.INSTANCE, HeatGenerationMultiplier.INSTANCE, ScanRadarStrength.INSTANCE, ScanLadarStrength.INSTANCE, ScanMagnetometricStrength.INSTANCE, ScanGravimetricStrength.INSTANCE, PropulsionGraphicID.INSTANCE, ShieldRechargeRate.INSTANCE, CapacitorCapacity.INSTANCE, ShieldUniformity.INSTANCE, TypeColorScheme.INSTANCE, HeatAttenuationHi.INSTANCE, HeatAttenuationMed.INSTANCE, HeatAttenuationLow.INSTANCE, GfxBoosterID.INSTANCE, DroneBandwidth.INSTANCE, EnergyWarfareResistance.INSTANCE, ShipScanResistance.INSTANCE })));
    public static final Corvette.MetaGroup METAGROUP = new Corvette.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
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
            case  14 :
            {
                return hislots;
            }
            case  5978 :
            {
                return integratedminingscanner;
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
    public IMetaGroup<Corvette> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Corvette>
    {
        public static final String RESOURCE_PATH = "SDE/types/ship/Corvette.yaml";
        private Map<Integer, Corvette> cache = (null);

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
        public synchronized Map<Integer, Corvette> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(Corvette.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, Corvette> types;
        }
    }
}
