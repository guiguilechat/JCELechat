
package fr.guiguilechat.eveonline.model.sde.compiled.items.planetaryresources;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.PlanetaryResources;
import org.yaml.snakeyaml.Yaml;

public class PlanetLiquidGas
    extends PlanetaryResources
{

    public final static String RESOURCE_PATH = "SDE/planetaryresources/PlanetLiquidGas.yaml";
    private static LinkedHashMap<String, PlanetLiquidGas> cache = (null);

    @Override
    public int getGroupId() {
        return  1033;
    }

    @Override
    public Class<?> getGroup() {
        return PlanetLiquidGas.class;
    }

    public static LinkedHashMap<String, PlanetLiquidGas> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, PlanetLiquidGas> items;

    }

}
