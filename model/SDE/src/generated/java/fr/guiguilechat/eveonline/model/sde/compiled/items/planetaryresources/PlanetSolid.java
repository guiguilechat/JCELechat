
package fr.guiguilechat.eveonline.model.sde.compiled.items.planetaryresources;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.PlanetaryResources;
import org.yaml.snakeyaml.Yaml;

public class PlanetSolid
    extends PlanetaryResources
{

    public final static String RESOURCE_PATH = "SDE/planetaryresources/PlanetSolid.yaml";
    private static LinkedHashMap<String, PlanetSolid> cache = (null);

    @Override
    public int getGroupId() {
        return  1032;
    }

    @Override
    public Class<?> getGroup() {
        return PlanetSolid.class;
    }

    public static LinkedHashMap<String, PlanetSolid> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, PlanetSolid> items;

    }

}
