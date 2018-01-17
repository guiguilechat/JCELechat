
package fr.guiguilechat.eveonline.model.sde.compiled.items.material;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Material;
import org.yaml.snakeyaml.Yaml;

public class HybridPolymers
    extends Material
{

    public final static String RESOURCE_PATH = "SDE/items/material/HybridPolymers.yaml";
    private static LinkedHashMap<String, HybridPolymers> cache = (null);

    @Override
    public int getGroupId() {
        return  974;
    }

    @Override
    public Class<?> getGroup() {
        return HybridPolymers.class;
    }

    public static LinkedHashMap<String, HybridPolymers> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(HybridPolymers.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, HybridPolymers> items;

    }

}
