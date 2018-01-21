
package fr.guiguilechat.eveonline.model.sde.items.types.bonus;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.Bonus;
import org.yaml.snakeyaml.Yaml;

public class PhysicalHandicap
    extends Bonus
{

    /**
     * Scales the capacitor need for fitted modules.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0D)
    public double CapacitorNeedMultiplier;
    /**
     * Scales the accuracy of some targeted weapon.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(1.0D)
    public double AccuracyMultiplier;
    public final static String RESOURCE_PATH = "SDE/items/bonus/PhysicalHandicap.yaml";
    private static LinkedHashMap<String, PhysicalHandicap> cache = (null);

    @Override
    public int getGroupId() {
        return  192;
    }

    @Override
    public Class<?> getGroup() {
        return PhysicalHandicap.class;
    }

    public static LinkedHashMap<String, PhysicalHandicap> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(PhysicalHandicap.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, PhysicalHandicap> items;

    }

}
