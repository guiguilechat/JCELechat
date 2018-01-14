
package fr.guiguilechat.eveonline.model.sde.compiled.items.entity;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Entity;
import org.yaml.snakeyaml.Yaml;

public class HiddenZenithGallenteCapital
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/entity/HiddenZenithGallenteCapital.yaml";
    private static LinkedHashMap<String, HiddenZenithGallenteCapital> cache = (null);

    @Override
    public int getGroupId() {
        return  1806;
    }

    @Override
    public Class<?> getGroup() {
        return HiddenZenithGallenteCapital.class;
    }

    public static LinkedHashMap<String, HiddenZenithGallenteCapital> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, HiddenZenithGallenteCapital> items;

    }

}
