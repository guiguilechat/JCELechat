
package fr.guiguilechat.eveonline.model.sde.compiled.items.charge;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Charge;
import org.yaml.snakeyaml.Yaml;

public class HeavyDefenderMissile
    extends Charge
{

    public final static String RESOURCE_PATH = "SDE/items/charge/HeavyDefenderMissile.yaml";
    private static LinkedHashMap<String, HeavyDefenderMissile> cache = (null);

    @Override
    public int getGroupId() {
        return  1158;
    }

    @Override
    public Class<?> getGroup() {
        return HeavyDefenderMissile.class;
    }

    public static LinkedHashMap<String, HeavyDefenderMissile> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(HeavyDefenderMissile.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, HeavyDefenderMissile> items;

    }

}
