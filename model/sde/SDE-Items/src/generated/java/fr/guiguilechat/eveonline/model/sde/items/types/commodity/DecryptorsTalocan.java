
package fr.guiguilechat.eveonline.model.sde.items.types.commodity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.Commodity;
import org.yaml.snakeyaml.Yaml;

public class DecryptorsTalocan
    extends Commodity
{

    /**
     * Modifies the max runs in a blueprint created through invention
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double InventionMaxRunModifier;
    /**
     * Modifies base chance of successful invention
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double InventionPropabilityMultiplier;
    /**
     * Modifies the mineral efficiency of invented BPCs
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double InventionMEModifier;
    /**
     * Modifies the time efficiency of invented BPCs
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double InventionTEModifier;
    public final static String RESOURCE_PATH = "SDE/items/commodity/DecryptorsTalocan.yaml";
    private static LinkedHashMap<String, DecryptorsTalocan> cache = (null);

    @Override
    public int getGroupId() {
        return  735;
    }

    @Override
    public Class<?> getGroup() {
        return DecryptorsTalocan.class;
    }

    public static LinkedHashMap<String, DecryptorsTalocan> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(DecryptorsTalocan.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, DecryptorsTalocan> items;

    }

}
