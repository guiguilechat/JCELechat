
package fr.guiguilechat.eveonline.model.sde.compiled.items.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionFactionTransports
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/items/entity/MissionFactionTransports.yaml";
    private static LinkedHashMap<String, MissionFactionTransports> cache = (null);

    @Override
    public int getGroupId() {
        return  875;
    }

    @Override
    public Class<?> getGroup() {
        return MissionFactionTransports.class;
    }

    public static LinkedHashMap<String, MissionFactionTransports> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(MissionFactionTransports.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, MissionFactionTransports> items;

    }

}
