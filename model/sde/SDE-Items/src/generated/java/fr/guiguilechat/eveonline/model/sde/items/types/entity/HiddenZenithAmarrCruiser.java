
package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class HiddenZenithAmarrCruiser
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/HiddenZenithAmarrCruiser.yaml";
    private static LinkedHashMap<String, HiddenZenithAmarrCruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  1790;
    }

    @Override
    public Class<?> getGroup() {
        return HiddenZenithAmarrCruiser.class;
    }

    public static LinkedHashMap<String, HiddenZenithAmarrCruiser> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(HiddenZenithAmarrCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, HiddenZenithAmarrCruiser> items;

    }

}
