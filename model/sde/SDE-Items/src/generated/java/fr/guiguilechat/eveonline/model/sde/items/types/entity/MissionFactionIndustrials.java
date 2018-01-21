
package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionFactionIndustrials
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/MissionFactionIndustrials.yaml";
    private static LinkedHashMap<String, MissionFactionIndustrials> cache = (null);

    @Override
    public int getGroupId() {
        return  927;
    }

    @Override
    public Class<?> getGroup() {
        return MissionFactionIndustrials.class;
    }

    public static LinkedHashMap<String, MissionFactionIndustrials> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(MissionFactionIndustrials.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, MissionFactionIndustrials> items;

    }

}
