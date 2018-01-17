
package fr.guiguilechat.eveonline.model.sde.compiled.items.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Entity;
import org.yaml.snakeyaml.Yaml;

public class AmarrEventCruiser
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/AmarrEventCruiser.yaml";
    private static LinkedHashMap<String, AmarrEventCruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  1761;
    }

    @Override
    public Class<?> getGroup() {
        return AmarrEventCruiser.class;
    }

    public static LinkedHashMap<String, AmarrEventCruiser> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AmarrEventCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, AmarrEventCruiser> items;

    }

}
