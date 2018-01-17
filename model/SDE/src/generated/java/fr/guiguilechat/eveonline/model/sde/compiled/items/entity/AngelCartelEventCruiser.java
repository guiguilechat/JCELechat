
package fr.guiguilechat.eveonline.model.sde.compiled.items.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Entity;
import org.yaml.snakeyaml.Yaml;

public class AngelCartelEventCruiser
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/AngelCartelEventCruiser.yaml";
    private static LinkedHashMap<String, AngelCartelEventCruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  1725;
    }

    @Override
    public Class<?> getGroup() {
        return AngelCartelEventCruiser.class;
    }

    public static LinkedHashMap<String, AngelCartelEventCruiser> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AngelCartelEventCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, AngelCartelEventCruiser> items;

    }

}
