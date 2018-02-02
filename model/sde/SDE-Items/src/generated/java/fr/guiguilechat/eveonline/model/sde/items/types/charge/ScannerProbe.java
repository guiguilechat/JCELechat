package fr.guiguilechat.eveonline.model.sde.items.types.charge;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.Charge;
import org.yaml.snakeyaml.Yaml;

public class ScannerProbe
    extends Charge
{
    /**
     * Maximum velocity of ship
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double MaxVelocity;
    /**
     * If this is 1 then the probe can scan for ships, otherwise it can't.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ProbeCanScanShips;
    /**
     * The agility of the object.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double Agility;
    /**
     * Tech level of an item
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int TechLevel;
    /**
     * The maximum hitpoints of an object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Hp;
    /**
     * One of the groups of launcher this charge can be loaded into.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int LauncherGroup;
    /**
     * Range in meters of explosion effect area.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ExplosionRange;
    /**
     * DO NOT MESS WITH
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double StructureUniformity;
    /**
     * Required skill level for skill 1
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1Level;
    /**
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(3.0)
    public double WarpSpeedMultiplier;
    /**
     * The amount of milliseconds before the object explodes.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int ExplosionDelay;
    /**
     * This is the lowest scan range value in AUs for probes under the revised probing system
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double BaseScanRange;
    /**
     * This is the highest sensor strength in points for probes under the revised probing system
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int BaseSensorStrength;
    /**
     * meta group of type
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int MetaGroupID;
    /**
     * This is the lowest maximum scan deviation in AU for probes under the revised probing system
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double BaseMaxScanDeviation;
    /**
     * This is the multiplier/divisor for probe range increases and associated values under the revised probing system
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RangeFactor;
    public final static String RESOURCE_PATH = "SDE/items/charge/ScannerProbe.yaml";
    private static LinkedHashMap<String, ScannerProbe> cache = (null);

    @Override
    public int getGroupId() {
        return  479;
    }

    @Override
    public Class<?> getGroup() {
        return ScannerProbe.class;
    }

    public static synchronized LinkedHashMap<String, ScannerProbe> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(ScannerProbe.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, ScannerProbe> items;
    }
}
