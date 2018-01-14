
package fr.guiguilechat.eveonline.model.sde.compiled.items.entity;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionGallenteFederationFrigate
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/entity/MissionGallenteFederationFrigate.yaml";
    private static LinkedHashMap<String, MissionGallenteFederationFrigate> cache = (null);

    @Override
    public int getGroupId() {
        return  677;
    }

    @Override
    public Class<?> getGroup() {
        return MissionGallenteFederationFrigate.class;
    }

    public static LinkedHashMap<String, MissionGallenteFederationFrigate> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, MissionGallenteFederationFrigate> items;

    }

}
