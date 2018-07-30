package fr.guiguilechat.jcelechat.model.sde.items.types.structure;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.Structure;
import org.yaml.snakeyaml.Yaml;

public class Laboratory
    extends Structure
{
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DroneBandwidth;
    /**
     * This defines the total capacity of drones allowed in the drone bay of the ship
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int DroneCapacity;
    /**
     * This defines the total capacity of fighters allowed in the fighter bay of the ship
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterCapacity;
    /**
     * Number of Heavy Fighters the ship can launch.Heavy 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterHeavySlots;
    /**
     * Number of Light Fighters the ship can launch.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterLightSlots;
    /**
     * Number of Support Fighters the ship can launch.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterSupportSlots;
    /**
     * This defines the total number of fighter launch tubes on the ship.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterTubes;
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
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int HeatCapacityHi;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int HeatCapacityLow;
    /**
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int HeatCapacityMed;
    /**
     * The number of remaining unused launcher slots.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int LauncherSlotsLeft;
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
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ServiceSlots;
    /**
     * Attribute on ships used for ship upgrades
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int UpgradeCapacity;
    public final static String RESOURCE_PATH = "SDE/items/structure/Laboratory.yaml";
    private static LinkedHashMap<String, Laboratory> cache = (null);

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  1271 :
            {
                return DroneBandwidth;
            }
            case  283 :
            {
                return DroneCapacity;
            }
            case  2055 :
            {
                return FighterCapacity;
            }
            case  2219 :
            {
                return FighterHeavySlots;
            }
            case  2217 :
            {
                return FighterLightSlots;
            }
            case  2218 :
            {
                return FighterSupportSlots;
            }
            case  2216 :
            {
                return FighterTubes;
            }
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
            case  1178 :
            {
                return HeatCapacityHi;
            }
            case  1200 :
            {
                return HeatCapacityLow;
            }
            case  1199 :
            {
                return HeatCapacityMed;
            }
            case  101 :
            {
                return LauncherSlotsLeft;
            }
            case  1547 :
            {
                return RigSize;
            }
            case  1137 :
            {
                return RigSlots;
            }
            case  2056 :
            {
                return ServiceSlots;
            }
            case  1132 :
            {
                return UpgradeCapacity;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public int getGroupId() {
        return  1405;
    }

    @Override
    public Class<?> getGroup() {
        return Laboratory.class;
    }

    public static synchronized LinkedHashMap<String, Laboratory> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Laboratory.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, Laboratory> items;
    }
}
