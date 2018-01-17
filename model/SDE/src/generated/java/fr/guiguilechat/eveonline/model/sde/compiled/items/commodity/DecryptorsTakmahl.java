
package fr.guiguilechat.eveonline.model.sde.compiled.items.commodity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.DefaultValue;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.compiled.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Commodity;
import org.yaml.snakeyaml.Yaml;

public class DecryptorsTakmahl
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
    public final static String RESOURCE_PATH = "SDE/items/commodity/DecryptorsTakmahl.yaml";
    private static LinkedHashMap<String, DecryptorsTakmahl> cache = (null);

    @Override
    public int getGroupId() {
        return  734;
    }

    @Override
    public Class<?> getGroup() {
        return DecryptorsTakmahl.class;
    }

    public static LinkedHashMap<String, DecryptorsTakmahl> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(DecryptorsTakmahl.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, DecryptorsTakmahl> items;

    }

}
