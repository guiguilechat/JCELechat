
package fr.guiguilechat.eveonline.model.sde.compiled.items.entity;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionGallenteFederationCarrier
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/entity/MissionGallenteFederationCarrier.yaml";
    private static LinkedHashMap<String, MissionGallenteFederationCarrier> cache = (null);

    @Override
    public int getGroupId() {
        return  867;
    }

    @Override
    public Class<?> getGroup() {
        return MissionGallenteFederationCarrier.class;
    }

    public static LinkedHashMap<String, MissionGallenteFederationCarrier> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, MissionGallenteFederationCarrier> items;

    }

}
