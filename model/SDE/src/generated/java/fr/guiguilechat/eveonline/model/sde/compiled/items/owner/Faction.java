
package fr.guiguilechat.eveonline.model.sde.compiled.items.owner;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Owner;
import org.yaml.snakeyaml.Yaml;

public class Faction
    extends Owner
{

    public final static String RESOURCE_PATH = "SDE/owner/Faction.yaml";
    private static LinkedHashMap<String, Faction> cache = (null);

    @Override
    public int getGroupId() {
        return  19;
    }

    @Override
    public Class<?> getGroup() {
        return Faction.class;
    }

    public static LinkedHashMap<String, Faction> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, Faction> items;

    }

}
