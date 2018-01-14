
package fr.guiguilechat.eveonline.model.sde.compiled.items.material;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Material;
import org.yaml.snakeyaml.Yaml;

public class IceProduct
    extends Material
{

    public final static String RESOURCE_PATH = "SDE/material/IceProduct.yaml";
    private static LinkedHashMap<String, IceProduct> cache = (null);

    @Override
    public int getGroupId() {
        return  423;
    }

    @Override
    public Class<?> getGroup() {
        return IceProduct.class;
    }

    public static LinkedHashMap<String, IceProduct> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, IceProduct> items;

    }

}
