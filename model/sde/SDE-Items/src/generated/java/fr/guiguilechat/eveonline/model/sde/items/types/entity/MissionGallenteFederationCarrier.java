
package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionGallenteFederationCarrier
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/MissionGallenteFederationCarrier.yaml";
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
                cache = new Yaml().loadAs(new InputStreamReader(MissionGallenteFederationCarrier.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, MissionGallenteFederationCarrier> items;

    }

}
