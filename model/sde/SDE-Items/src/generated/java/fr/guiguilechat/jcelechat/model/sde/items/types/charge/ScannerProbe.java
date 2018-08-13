package fr.guiguilechat.jcelechat.model.sde.items.types.charge;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.Charge;
import org.yaml.snakeyaml.Yaml;

public class ScannerProbe
    extends Charge
{
    /**
     * The agility of the object.
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double Agility;
    /**
     * This is the lowest maximum scan deviation in AU for probes under the revised probing system
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double BaseMaxScanDeviation;
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
     * The amount of milliseconds before the object explodes.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultIntValue(0)
    public int ExplosionDelay;
    /**
     * Range in meters of explosion effect area.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ExplosionRange;
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
     * Maximum velocity of ship
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double MaxVelocity;
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
     * If this is 1 then the probe can scan for ships, otherwise it can't.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ProbeCanScanShips;
    /**
     * This is the multiplier/divisor for probe range increases and associated values under the revised probing system
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RangeFactor;
    /**
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1;
    /**
     * Required skill level for skill 1
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1Level;
    /**
     * DO NOT MESS WITH
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double StructureUniformity;
    /**
     * Authoring has been moved to FSD
     * Tech level of an item
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(1)
    public int TechLevel;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(3.0)
    public double WarpSpeedMultiplier;
    public final static ScannerProbe.MetaGroup METAGROUP = new ScannerProbe.MetaGroup();
    public final static String RESOURCE_PATH = "SDE/items/charge/ScannerProbe.yaml";
    private static Map<String, ScannerProbe> cache = (null);

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  70 :
            {
                return Agility;
            }
            case  1372 :
            {
                return BaseMaxScanDeviation;
            }
            case  1370 :
            {
                return BaseScanRange;
            }
            case  1371 :
            {
                return BaseSensorStrength;
            }
            case  281 :
            {
                return ExplosionDelay;
            }
            case  107 :
            {
                return ExplosionRange;
            }
            case  9 :
            {
                return Hp;
            }
            case  137 :
            {
                return LauncherGroup;
            }
            case  37 :
            {
                return MaxVelocity;
            }
            case  1692 :
            {
                return MetaGroupID;
            }
            case  1413 :
            {
                return ProbeCanScanShips;
            }
            case  1373 :
            {
                return RangeFactor;
            }
            case  182 :
            {
                return RequiredSkill1;
            }
            case  277 :
            {
                return RequiredSkill1Level;
            }
            case  525 :
            {
                return StructureUniformity;
            }
            case  422 :
            {
                return TechLevel;
            }
            case  600 :
            {
                return WarpSpeedMultiplier;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public int getGroupId() {
        return  479;
    }

    @Override
    public fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<ScannerProbe> getGroup() {
        return METAGROUP;
    }

    public static synchronized Map<String, ScannerProbe> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(ScannerProbe.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return Collections.unmodifiableMap(cache);
    }

    private static class Container {
        public LinkedHashMap<String, ScannerProbe> items;
    }

    public static class MetaGroup
        implements fr.guiguilechat.jcelechat.model.sde.items.MetaGroup<ScannerProbe>
    {

        @Override
        public MetaCategory<? super ScannerProbe> category() {
            return Charge.METACAT;
        }

        @Override
        public String getName() {
            return "ScannerProbe";
        }

        @Override
        public Collection<ScannerProbe> items() {
            return (load().values());
        }
    }
}
