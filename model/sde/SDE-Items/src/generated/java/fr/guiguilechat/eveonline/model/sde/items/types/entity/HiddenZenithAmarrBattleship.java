
package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class HiddenZenithAmarrBattleship
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/HiddenZenithAmarrBattleship.yaml";
    private static LinkedHashMap<String, HiddenZenithAmarrBattleship> cache = (null);

    @Override
    public int getGroupId() {
        return  1789;
    }

    @Override
    public Class<?> getGroup() {
        return HiddenZenithAmarrBattleship.class;
    }

    public static LinkedHashMap<String, HiddenZenithAmarrBattleship> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(HiddenZenithAmarrBattleship.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, HiddenZenithAmarrBattleship> items;

    }

}
