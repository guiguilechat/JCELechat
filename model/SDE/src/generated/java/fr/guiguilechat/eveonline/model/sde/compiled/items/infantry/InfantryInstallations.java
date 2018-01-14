
package fr.guiguilechat.eveonline.model.sde.compiled.items.infantry;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Infantry;
import org.yaml.snakeyaml.Yaml;

public class InfantryInstallations
    extends Infantry
{

    public final static String RESOURCE_PATH = "SDE/infantry/InfantryInstallations.yaml";
    private static LinkedHashMap<String, InfantryInstallations> cache = (null);

    @Override
    public int getGroupId() {
        return  354753;
    }

    @Override
    public Class<?> getGroup() {
        return InfantryInstallations.class;
    }

    public static LinkedHashMap<String, InfantryInstallations> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, InfantryInstallations> items;

    }

}
