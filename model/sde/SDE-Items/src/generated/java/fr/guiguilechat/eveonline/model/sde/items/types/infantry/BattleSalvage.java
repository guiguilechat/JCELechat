
package fr.guiguilechat.eveonline.model.sde.items.types.infantry;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Infantry;
import org.yaml.snakeyaml.Yaml;

public class BattleSalvage
    extends Infantry
{

    public final static String RESOURCE_PATH = "SDE/items/infantry/BattleSalvage.yaml";
    private static LinkedHashMap<String, BattleSalvage> cache = (null);

    @Override
    public int getGroupId() {
        return  368656;
    }

    @Override
    public Class<?> getGroup() {
        return BattleSalvage.class;
    }

    public static LinkedHashMap<String, BattleSalvage> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(BattleSalvage.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, BattleSalvage> items;

    }

}
