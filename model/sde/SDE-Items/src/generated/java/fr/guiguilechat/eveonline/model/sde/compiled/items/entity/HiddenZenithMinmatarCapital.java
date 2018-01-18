
package fr.guiguilechat.eveonline.model.sde.compiled.items.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Entity;
import org.yaml.snakeyaml.Yaml;

public class HiddenZenithMinmatarCapital
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/HiddenZenithMinmatarCapital.yaml";
    private static LinkedHashMap<String, HiddenZenithMinmatarCapital> cache = (null);

    @Override
    public int getGroupId() {
        return  1807;
    }

    @Override
    public Class<?> getGroup() {
        return HiddenZenithMinmatarCapital.class;
    }

    public static LinkedHashMap<String, HiddenZenithMinmatarCapital> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(HiddenZenithMinmatarCapital.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, HiddenZenithMinmatarCapital> items;

    }

}
