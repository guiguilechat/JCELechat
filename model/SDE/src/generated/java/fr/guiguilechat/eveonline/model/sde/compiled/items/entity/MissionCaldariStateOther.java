
package fr.guiguilechat.eveonline.model.sde.compiled.items.entity;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionCaldariStateOther
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/entity/MissionCaldariStateOther.yaml";
    private static LinkedHashMap<String, MissionCaldariStateOther> cache = (null);

    @Override
    public int getGroupId() {
        return  675;
    }

    @Override
    public Class<?> getGroup() {
        return MissionCaldariStateOther.class;
    }

    public static LinkedHashMap<String, MissionCaldariStateOther> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, MissionCaldariStateOther> items;

    }

}
