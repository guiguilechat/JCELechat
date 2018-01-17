
package fr.guiguilechat.eveonline.model.sde.compiled.items.celestial;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.DefaultValue;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Celestial;
import org.yaml.snakeyaml.Yaml;

public class WarpGate
    extends Celestial
{

    /**
     * The maximum hitpoints of an object.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double Hp;
    /**
     * The number of hit points on the entities armor.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ArmorHP;
    /**
     * The distance at which to react when relevant objects come within range.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double ProximityRange;
    /**
     * Determines the maximum weight of a ship that, ships that are to heavy get denied of service by this attribute
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double MassLimit;
    public final static String RESOURCE_PATH = "SDE/items/celestial/WarpGate.yaml";
    private static LinkedHashMap<String, WarpGate> cache = (null);

    @Override
    public int getGroupId() {
        return  366;
    }

    @Override
    public Class<?> getGroup() {
        return WarpGate.class;
    }

    public static LinkedHashMap<String, WarpGate> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(WarpGate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, WarpGate> items;

    }

}
