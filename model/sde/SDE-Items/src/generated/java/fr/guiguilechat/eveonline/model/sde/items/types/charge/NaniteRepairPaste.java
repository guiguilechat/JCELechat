
package fr.guiguilechat.eveonline.model.sde.items.types.charge;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.annotations.DefaultValue;
import fr.guiguilechat.eveonline.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.eveonline.model.sde.items.annotations.Stackable;
import fr.guiguilechat.eveonline.model.sde.items.types.Charge;
import org.yaml.snakeyaml.Yaml;

public class NaniteRepairPaste
    extends Charge
{

    /**
     * The maximum hitpoints of an object.
     * 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultValue(0.0D)
    public double Hp;
    public final static String RESOURCE_PATH = "SDE/items/charge/NaniteRepairPaste.yaml";
    private static LinkedHashMap<String, NaniteRepairPaste> cache = (null);

    @Override
    public int getGroupId() {
        return  916;
    }

    @Override
    public Class<?> getGroup() {
        return NaniteRepairPaste.class;
    }

    public static LinkedHashMap<String, NaniteRepairPaste> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(NaniteRepairPaste.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, NaniteRepairPaste> items;

    }

}
