
package fr.guiguilechat.eveonline.model.sde.compiled.items.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Entity;
import org.yaml.snakeyaml.Yaml;

public class HiddenZenithAmarrCapital
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/HiddenZenithAmarrCapital.yaml";
    private static LinkedHashMap<String, HiddenZenithAmarrCapital> cache = (null);

    @Override
    public int getGroupId() {
        return  1804;
    }

    @Override
    public Class<?> getGroup() {
        return HiddenZenithAmarrCapital.class;
    }

    public static LinkedHashMap<String, HiddenZenithAmarrCapital> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(HiddenZenithAmarrCapital.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, HiddenZenithAmarrCapital> items;

    }

}
