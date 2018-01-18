
package fr.guiguilechat.eveonline.model.sde.compiled.items.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionMinmatarRepublicCarrier
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/MissionMinmatarRepublicCarrier.yaml";
    private static LinkedHashMap<String, MissionMinmatarRepublicCarrier> cache = (null);

    @Override
    public int getGroupId() {
        return  868;
    }

    @Override
    public Class<?> getGroup() {
        return MissionMinmatarRepublicCarrier.class;
    }

    public static LinkedHashMap<String, MissionMinmatarRepublicCarrier> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(MissionMinmatarRepublicCarrier.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, MissionMinmatarRepublicCarrier> items;

    }

}
