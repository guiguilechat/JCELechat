
package fr.guiguilechat.eveonline.model.sde.compiled.items.entity;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Entity;
import org.yaml.snakeyaml.Yaml;

public class NPCMiningBarge
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/entity/NPCMiningBarge.yaml";
    private static LinkedHashMap<String, NPCMiningBarge> cache = (null);

    @Override
    public int getGroupId() {
        return  1765;
    }

    @Override
    public Class<?> getGroup() {
        return NPCMiningBarge.class;
    }

    public static LinkedHashMap<String, NPCMiningBarge> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, NPCMiningBarge> items;

    }

}
