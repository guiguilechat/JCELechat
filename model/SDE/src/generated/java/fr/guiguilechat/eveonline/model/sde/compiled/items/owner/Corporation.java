
package fr.guiguilechat.eveonline.model.sde.compiled.items.owner;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Owner;
import org.yaml.snakeyaml.Yaml;

public class Corporation
    extends Owner
{

    public final static String RESOURCE_PATH = "SDE/owner/Corporation.yaml";
    private static LinkedHashMap<String, Corporation> cache = (null);

    @Override
    public int getGroupId() {
        return  2;
    }

    @Override
    public Class<?> getGroup() {
        return Corporation.class;
    }

    public static LinkedHashMap<String, Corporation> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, Corporation> items;

    }

}
