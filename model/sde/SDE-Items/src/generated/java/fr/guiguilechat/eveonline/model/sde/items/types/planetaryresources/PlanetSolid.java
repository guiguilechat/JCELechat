
package fr.guiguilechat.eveonline.model.sde.items.types.planetaryresources;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.PlanetaryResources;
import org.yaml.snakeyaml.Yaml;

public class PlanetSolid
    extends PlanetaryResources
{

    public final static String RESOURCE_PATH = "SDE/items/planetaryresources/PlanetSolid.yaml";
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
                cache = new Yaml().loadAs(new InputStreamReader(PlanetSolid.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, PlanetSolid> items;

    }

}
