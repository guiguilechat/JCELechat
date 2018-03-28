package fr.guiguilechat.eveonline.model.sde.items.types.asteroid;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.Asteroid;
import org.yaml.snakeyaml.Yaml;

public class Scordite
    extends Asteroid
{
    /**
     * max visual size for asteroids to fit moon chunk
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(16255)
    public int AsteroidMaxRadius;
    /**
     *  0: Mission/NPE Ore
     *  1: Standard Ore/Ice
     *  2: +5% Ore
     *  3: +10% Ore
     *  4: High Quality Ice or Extracted Ore
     *  5: Jackpot Moon Ore
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int AsteroidMetaLevel;
    /**
     * Controls how quickly an asteroid radius increases as its quantity grows.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double AsteroidRadiusGrowthFactor;
    /**
     * Sets the radius of the asteroid ball when it has a quantity of 1 unit
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(90)
    public int AsteroidRadiusUnitSize;
    /**
     * Number of items needed to be able to compress it
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int CompressionQuantityNeeded;
    /**
     * What type this type can be compressed into
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int CompressionTypeID;
    /**
     * Reference for grouping ores in visual displays. All variants of one ore should have the same BasicType ID
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultIntValue(0)
    public int OreBasicType;
    /**
     * The skill required to reprocess this ore type.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int ReprocessingSkillType;
    /**
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1;
    /**
     * Resistance against Stasis Webifiers
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(1.0)
    public double StasisWebifierResistance;
    public final static String RESOURCE_PATH = "SDE/items/asteroid/Scordite.yaml";
    private static LinkedHashMap<String, Scordite> cache = (null);

    @Override
    public int getGroupId() {
        return  460;
    }

    @Override
    public Class<?> getGroup() {
        return Scordite.class;
    }

    public static synchronized LinkedHashMap<String, Scordite> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(Scordite.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, Scordite> items;
    }
}
