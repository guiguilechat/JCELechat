
package fr.guiguilechat.eveonline.model.sde.compiled.items.commodity;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Commodity;
import org.yaml.snakeyaml.Yaml;

public class ArtifactsAndPrototypes
    extends Commodity
{

    public final static String RESOURCE_PATH = "SDE/commodity/ArtifactsAndPrototypes.yaml";
    private static LinkedHashMap<String, ArtifactsAndPrototypes> cache = (null);

    @Override
    public int getGroupId() {
        return  528;
    }

    @Override
    public Class<?> getGroup() {
        return ArtifactsAndPrototypes.class;
    }

    public static LinkedHashMap<String, ArtifactsAndPrototypes> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, ArtifactsAndPrototypes> items;

    }

}
