
package fr.guiguilechat.eveonline.model.sde.compiled.items.planetaryresources;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.PlanetaryResources;
import org.yaml.snakeyaml.Yaml;

public class PlanetOrganic
    extends PlanetaryResources
{

    public final static String RESOURCE_PATH = "SDE/items/planetaryresources/PlanetOrganic.yaml";
    private static LinkedHashMap<String, PlanetOrganic> cache = (null);

    @Override
    public int getGroupId() {
        return  1035;
    }

    @Override
    public Class<?> getGroup() {
        return PlanetOrganic.class;
    }

    public static LinkedHashMap<String, PlanetOrganic> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(PlanetOrganic.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, PlanetOrganic> items;

    }

}
