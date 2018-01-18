
package fr.guiguilechat.eveonline.model.sde.compiled.items.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionGallenteFederationFrigate
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/MissionGallenteFederationFrigate.yaml";
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
                cache = new Yaml().loadAs(new InputStreamReader(MissionGallenteFederationFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, MissionGallenteFederationFrigate> items;

    }

}
