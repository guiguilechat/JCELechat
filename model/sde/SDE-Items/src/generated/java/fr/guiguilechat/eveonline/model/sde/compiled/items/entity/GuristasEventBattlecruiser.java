
package fr.guiguilechat.eveonline.model.sde.compiled.items.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Entity;
import org.yaml.snakeyaml.Yaml;

public class GuristasEventBattlecruiser
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/GuristasEventBattlecruiser.yaml";
    private static LinkedHashMap<String, GuristasEventBattlecruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  1666;
    }

    @Override
    public Class<?> getGroup() {
        return GuristasEventBattlecruiser.class;
    }

    public static LinkedHashMap<String, GuristasEventBattlecruiser> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(GuristasEventBattlecruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, GuristasEventBattlecruiser> items;

    }

}