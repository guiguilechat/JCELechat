
package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class GuristasEventBattleship
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/GuristasEventBattleship.yaml";
    private static LinkedHashMap<String, GuristasEventBattleship> cache = (null);

    @Override
    public int getGroupId() {
        return  1667;
    }

    @Override
    public Class<?> getGroup() {
        return GuristasEventBattleship.class;
    }

    public static LinkedHashMap<String, GuristasEventBattleship> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(GuristasEventBattleship.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, GuristasEventBattleship> items;

    }

}
