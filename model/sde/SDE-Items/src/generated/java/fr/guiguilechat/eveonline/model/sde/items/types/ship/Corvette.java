package fr.guiguilechat.eveonline.model.sde.items.types.ship;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.Ship;
import org.yaml.snakeyaml.Yaml;

public class Corvette
    extends Ship
{
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RigSize;
    /**
     * The number of low power slots on the ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int LowSlots;
    /**
     * tbd
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MedSlots;
    /**
     * tbd
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int HiSlots;
    /**
     * Required skill level for skill 1
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1Level;
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
     * Bonus to tracking disruptor effectiveness
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RookieWeaponDisruptionBonus;
    /**
     * Bonus to armor resistances
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RookieArmorResistanceBonus;
    /**
     * Small Hybrid Turret optimal range bonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RookieSHTOptimalBonus;
    /**
     * Bonus to kinetic missile damage
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RookieMissileKinDamageBonus;
    /**
     * ECM Strength Bonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RookieECMStrengthBonus;
    /**
     * Shield resistance bonus
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RookieShieldResistBonus;
    /**
     * Bonus to Small Hybrid Turret damage
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RookieSHTDamageBonus;
    /**
     * Bonus to drone damage, HP and mining yield
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RookieDroneBonus;
    /**
     * Bonus to sensor damper effectiveness
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RookieDampStrengthBonus;
    /**
     * Bonus to armor repair amount
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RookieArmorRepBonus;
    /**
     * Bonus to target painter effectiveness
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RookieTargetPainterStrengthBonus;
    /**
     * Bonus to ship velocity
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RookieShipVelocityBonus;
    /**
     * Bonus to Small Projectile Turret damage
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RookieSPTDamageBonus;
    /**
     * Bonus to shield booster repair amount
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double RookieShieldBoostBonus;
    /**
     * The resolution that the vessel can target other objects at.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int ScanResolution;
    /**
     * Increase in small energy turret tracking
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double RookieSETTracking;
    /**
     * Increase in Small Energy Turret optimal Range
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RookieSETOptimal;
    /**
     * Increase in Nosferatu drain amount
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RookieNosDrain;
    /**
     * Increase in Energy Neutralizer drain amount
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RookieNeutDrain;
    /**
     * Increase in Statis Webifier speed reduction
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RookieWebAmount;
    /**
     * Increase in Light Missile velocity
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RookieLightMissileVelocity;
    /**
     * Increase in Rocket velocity
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RookieRocketVelocity;
    /**
     * Increase in Drone MWD speed
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RookieDroneMWDspeed;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double RookieSHTTracking;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RookieSHTFalloff;
    /**
     * Increase in Small Projectile Turret tracking
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double RookieSPTTracking;
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
     * scanning speed in milliseconds
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int ScanSpeed;
    /**
     * Attribute on ships used for ship upgrades
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int UpgradeCapacity;
    /**
     * The number of rig slots on the ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RigSlots;
    /**
     * The main color of a ship type.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MainColor;
    /**
     * How many upgrades can by fitted to this ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int UpgradeSlotsLeft;
    /**
     * Deprecated.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int MaxDirectionalVelocity;
    /**
     * Deprecated.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double MinTargetVelDmgMultiplier;
    /**
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1;
    /**
     * The value of this attribute is a graphicsID which controls the color scheme of this type. It is used to apply said color scheme to items of other types whose gfx representation is tied in with the attribute holder. Example: Turrets on ships.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int TypeColorScheme;
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
    public double HeatAttenuationMed;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double HeatAttenuationLow;
    /**
     * Chance of being able to resist a ship scan.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ShipScanResistance;
    public final static String RESOURCE_PATH = "SDE/items/ship/Corvette.yaml";
    private static LinkedHashMap<String, Corvette> cache = (null);

    @Override
    public int getGroupId() {
        return  237;
    }

    @Override
    public Class<?> getGroup() {
        return Corvette.class;
    }

    public static synchronized LinkedHashMap<String, Corvette> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Corvette.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, Corvette> items;
    }
}
