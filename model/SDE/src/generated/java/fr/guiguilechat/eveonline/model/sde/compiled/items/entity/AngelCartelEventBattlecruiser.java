
package fr.guiguilechat.eveonline.model.sde.compiled.items.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Entity;
import org.yaml.snakeyaml.Yaml;

public class AngelCartelEventBattlecruiser
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/AngelCartelEventBattlecruiser.yaml";
    private static LinkedHashMap<String, AngelCartelEventBattlecruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  1726;
    }

    @Override
    public Class<?> getGroup() {
        return AngelCartelEventBattlecruiser.class;
    }

    public static LinkedHashMap<String, AngelCartelEventBattlecruiser> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AngelCartelEventBattlecruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, AngelCartelEventBattlecruiser> items;

    }

}
