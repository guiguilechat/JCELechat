
package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class HiddenZenithGallenteCruiser
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/HiddenZenithGallenteCruiser.yaml";
    private static LinkedHashMap<String, HiddenZenithGallenteCruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  1796;
    }

    @Override
    public Class<?> getGroup() {
        return HiddenZenithGallenteCruiser.class;
    }

    public static LinkedHashMap<String, HiddenZenithGallenteCruiser> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(HiddenZenithGallenteCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, HiddenZenithGallenteCruiser> items;

    }

}
