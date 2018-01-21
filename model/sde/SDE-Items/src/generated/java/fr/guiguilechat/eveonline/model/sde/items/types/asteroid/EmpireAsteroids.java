
package fr.guiguilechat.eveonline.model.sde.items.types.asteroid;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.Asteroid;
import org.yaml.snakeyaml.Yaml;

public class EmpireAsteroids
    extends Asteroid
{

    /**
     * Resistance against Stasis Webifiers
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultValue(1.0D)
    public double StasisWebifierResistance;
    /**
     * Reference for grouping ores in visual displays. All variants of one ore should have the same BasicType ID
     * 
     */
    @HighIsGood(false)
    @Stackable(false)
    @DefaultValue(0.0D)
    public double OreBasicType;
    /**
     *  0: Mission/NPE Ore
     *  1: Standard Ore/Ice
     *  2: +5% Ore
     *  3: +10% Ore
     *  4: High Quality Ice or Extracted Ore
     *  5: Jackpot Moon Ore
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double AsteroidMetaLevel;
    /**
     * Sets the radius of the asteroid ball when it has a quantity of 1 unit
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(90.0D)
    public double AsteroidRadiusUnitSize;
    public final static String RESOURCE_PATH = "SDE/items/asteroid/EmpireAsteroids.yaml";
    private static LinkedHashMap<String, EmpireAsteroids> cache = (null);

    @Override
    public int getGroupId() {
        return  1911;
    }

    @Override
    public Class<?> getGroup() {
        return EmpireAsteroids.class;
    }

    public static LinkedHashMap<String, EmpireAsteroids> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(EmpireAsteroids.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, EmpireAsteroids> items;

    }

}
