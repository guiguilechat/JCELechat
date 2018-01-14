
package fr.guiguilechat.eveonline.model.sde.compiled.items.celestial;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Celestial;
import org.yaml.snakeyaml.Yaml;

public class HarvestableCloud
    extends Celestial
{

    public final static String RESOURCE_PATH = "SDE/celestial/HarvestableCloud.yaml";
    private static LinkedHashMap<String, HarvestableCloud> cache = (null);

    @Override
    public int getGroupId() {
        return  711;
    }

    @Override
    public Class<?> getGroup() {
        return HarvestableCloud.class;
    }

    public static LinkedHashMap<String, HarvestableCloud> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, HarvestableCloud> items;

    }

}
