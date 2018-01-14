
package fr.guiguilechat.eveonline.model.sde.compiled.items.commodity;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Commodity;
import org.yaml.snakeyaml.Yaml;

public class ResearchData
    extends Commodity
{

    public final static String RESOURCE_PATH = "SDE/commodity/ResearchData.yaml";
    private static LinkedHashMap<String, ResearchData> cache = (null);

    @Override
    public int getGroupId() {
        return  1141;
    }

    @Override
    public Class<?> getGroup() {
        return ResearchData.class;
    }

    public static LinkedHashMap<String, ResearchData> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, ResearchData> items;

    }

}
