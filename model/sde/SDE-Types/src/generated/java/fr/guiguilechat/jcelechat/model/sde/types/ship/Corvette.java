package fr.guiguilechat.jcelechat.model.sde.types.ship;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
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
    public double HeatAttenuationHi;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double HeatAttenuationLow;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double HeatAttenuationMed;
    /**
     * The main color of a ship type.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MainColor;
    /**
     * Deprecated.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int MaxDirectionalVelocity;
    /**
     * Specifies the maximum numbers of passengers that the ship can have
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MaxPassengers;
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
    public int MetaGroupID;
    /**
     * Deprecated.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double MinTargetVelDmgMultiplier;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RigSize;
    /**
     * The number of rig slots on the ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RigSlots;
    /**
     * Bonus to armor repair amount
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RookieArmorRepBonus;
    /**
     * Bonus to armor resistances
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RookieArmorResistanceBonus;
    /**
     * Bonus to sensor damper effectiveness
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RookieDampStrengthBonus;
    /**
     * Bonus to drone damage, HP and mining yield
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RookieDroneBonus;
    /**
     * Increase in Drone MWD speed
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RookieDroneMWDspeed;
    /**
     * ECM Strength Bonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RookieECMStrengthBonus;
    /**
     * Increase in Light Missile velocity
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RookieLightMissileVelocity;
    /**
     * Bonus to kinetic missile damage
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RookieMissileKinDamageBonus;
    /**
     * Increase in Energy Neutralizer drain amount
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RookieNeutDrain;
    /**
     * Increase in Nosferatu drain amount
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RookieNosDrain;
    /**
     * Increase in Rocket velocity
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RookieRocketVelocity;
    /**
     * Reduction in energy turret capacitor use
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RookieSETCapBonus;
    /**
     * Energy turret damage bonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RookieSETDamageBonus;
    /**
     * Increase in Small Energy Turret optimal Range
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RookieSETOptimal;
    /**
     * Increase in small energy turret tracking
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double RookieSETTracking;
    /**
     * Bonus to Small Hybrid Turret damage
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RookieSHTDamageBonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RookieSHTFalloff;
    /**
     * Small Hybrid Turret optimal range bonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RookieSHTOptimalBonus;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double RookieSHTTracking;
    /**
     * Bonus to Small Projectile Turret damage
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RookieSPTDamageBonus;
    /**
     * Increase in Small Projectile Turret falloff
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double RookieSPTFalloff;
    /**
     * Increase in Small Projectile Turret optimal range
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double RookieSPTOptimal;
    /**
     * Increase in Small Projectile Turret tracking
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double RookieSPTTracking;
    /**
     * Bonus to shield booster repair amount
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double RookieShieldBoostBonus;
    /**
     * Shield resistance bonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RookieShieldResistBonus;
    /**
     * Bonus to ship velocity
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RookieShipVelocityBonus;
    /**
     * Bonus to target painter effectiveness
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RookieTargetPainterStrengthBonus;
    /**
     * Bonus to tracking disruptor effectiveness
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RookieWeaponDisruptionBonus;
    /**
     * Increase in Statis Webifier speed reduction
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RookieWebAmount;
    /**
     * The resolution that the vessel can target other objects at.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int ScanResolution;
    /**
     * scanning speed in milliseconds
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int ScanSpeed;
    /**
     * Chance of being able to resist a ship scan.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShipScanResistance;
    /**
     * How many rigs can by fitted to this ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int UpgradeSlotsLeft;
    public static final Corvette.MetaGroup METAGROUP = new Corvette.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  1259 :
            {
                return HeatAttenuationHi;
            }
            case  1262 :
            {
                return HeatAttenuationLow;
            }
            case  1261 :
            {
                return HeatAttenuationMed;
            }
            case  124 :
            {
                return MainColor;
            }
            case  661 :
            {
                return MaxDirectionalVelocity;
            }
            case  129 :
            {
                return MaxPassengers;
            }
            case  1692 :
            {
                return MetaGroupID;
            }
            case  662 :
            {
                return MinTargetVelDmgMultiplier;
            }
            case  1547 :
            {
                return RigSize;
            }
            case  1137 :
            {
                return RigSlots;
            }
            case  1833 :
            {
                return RookieArmorRepBonus;
            }
            case  1825 :
            {
                return RookieArmorResistanceBonus;
            }
            case  1832 :
            {
                return RookieDampStrengthBonus;
            }
            case  1831 :
            {
                return RookieDroneBonus;
            }
            case  1864 :
            {
                return RookieDroneMWDspeed;
            }
            case  1828 :
            {
                return RookieECMStrengthBonus;
            }
            case  1862 :
            {
                return RookieLightMissileVelocity;
            }
            case  1827 :
            {
                return RookieMissileKinDamageBonus;
            }
            case  1860 :
            {
                return RookieNeutDrain;
            }
            case  1859 :
            {
                return RookieNosDrain;
            }
            case  1863 :
            {
                return RookieRocketVelocity;
            }
            case  1822 :
            {
                return RookieSETCapBonus;
            }
            case  1823 :
            {
                return RookieSETDamageBonus;
            }
            case  1858 :
            {
                return RookieSETOptimal;
            }
            case  1857 :
            {
                return RookieSETTracking;
            }
            case  1830 :
            {
                return RookieSHTDamageBonus;
            }
            case  1866 :
            {
                return RookieSHTFalloff;
            }
            case  1826 :
            {
                return RookieSHTOptimalBonus;
            }
            case  1865 :
            {
                return RookieSHTTracking;
            }
            case  1836 :
            {
                return RookieSPTDamageBonus;
            }
            case  1868 :
            {
                return RookieSPTFalloff;
            }
            case  1869 :
            {
                return RookieSPTOptimal;
            }
            case  1867 :
            {
                return RookieSPTTracking;
            }
            case  1837 :
            {
                return RookieShieldBoostBonus;
            }
            case  1829 :
            {
                return RookieShieldResistBonus;
            }
            case  1835 :
            {
                return RookieShipVelocityBonus;
            }
            case  1834 :
            {
                return RookieTargetPainterStrengthBonus;
            }
            case  1824 :
            {
                return RookieWeaponDisruptionBonus;
            }
            case  1861 :
            {
                return RookieWebAmount;
            }
            case  564 :
            {
                return ScanResolution;
            }
            case  79 :
            {
                return ScanSpeed;
            }
            case  511 :
            {
                return ShipScanResistance;
            }
            case  1154 :
            {
                return UpgradeSlotsLeft;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
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
