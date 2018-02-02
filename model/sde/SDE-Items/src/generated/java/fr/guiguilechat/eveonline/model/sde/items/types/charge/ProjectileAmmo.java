package fr.guiguilechat.eveonline.model.sde.items.types.charge;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.Charge;
import org.yaml.snakeyaml.Yaml;

public class ProjectileAmmo
    extends Charge
{
    /**
     * The size of the charges that can fit in the turret/whatever.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ChargeSize;
    /**
     * Just for the UI to display base damage on shield.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int BaseShieldDamage;
    /**
     * Just for the UI to display base damage on armor.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int BaseArmorDamage;
    /**
     * Tech level of an item
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int TechLevel;
    /**
     * One of the groups of launcher this charge can be loaded into.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int LauncherGroup;
    /**
     * For charges, hidden attribute used by sentry guns to modify target pick range.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double EntityFlyRangeMultiplier;
    /**
     * EM damage done.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double EmDamage;
    /**
     * Scale the tracking speed of a weapon.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double TrackingSpeedMultiplier;
    /**
     * Explosive damage done.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double ExplosiveDamage;
    /**
     * Kinetic damage done.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double KineticDamage;
    /**
     * Thermal damage done.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double ThermalDamage;
    /**
     * Multiplier of range the relevant weapon.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double WeaponRangeMultiplier;
    /**
     * The main color of a ship type.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MainColor;
    /**
     * meta group of type
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MetaGroupID;
    public final static String RESOURCE_PATH = "SDE/items/charge/ProjectileAmmo.yaml";
    private static LinkedHashMap<String, ProjectileAmmo> cache = (null);

    @Override
    public int getGroupId() {
        return  83;
    }

    @Override
    public Class<?> getGroup() {
        return ProjectileAmmo.class;
    }

    public static synchronized LinkedHashMap<String, ProjectileAmmo> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(ProjectileAmmo.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, ProjectileAmmo> items;
    }
}
