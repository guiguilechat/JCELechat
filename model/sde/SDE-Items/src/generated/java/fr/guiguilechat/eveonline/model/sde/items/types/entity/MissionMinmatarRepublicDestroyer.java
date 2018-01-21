
package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionMinmatarRepublicDestroyer
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/MissionMinmatarRepublicDestroyer.yaml";
    private static LinkedHashMap<String, MissionMinmatarRepublicDestroyer> cache = (null);

    @Override
    public int getGroupId() {
        return  684;
    }

    @Override
    public Class<?> getGroup() {
        return MissionMinmatarRepublicDestroyer.class;
    }

    public static LinkedHashMap<String, MissionMinmatarRepublicDestroyer> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(MissionMinmatarRepublicDestroyer.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, MissionMinmatarRepublicDestroyer> items;

    }

}
