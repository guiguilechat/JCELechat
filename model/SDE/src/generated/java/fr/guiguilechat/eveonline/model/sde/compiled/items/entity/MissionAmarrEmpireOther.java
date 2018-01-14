
package fr.guiguilechat.eveonline.model.sde.compiled.items.entity;

import java.io.FileReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.compiled.items.Entity;
import org.yaml.snakeyaml.Yaml;

public class MissionAmarrEmpireOther
    extends Entity
{

    public final static String RESOURCE_PATH = "SDE/entity/MissionAmarrEmpireOther.yaml";
    private static LinkedHashMap<String, MissionAmarrEmpireOther> cache = (null);

    @Override
    public int getGroupId() {
        return  670;
    }

    @Override
    public Class<?> getGroup() {
        return MissionAmarrEmpireOther.class;
    }

    public static LinkedHashMap<String, MissionAmarrEmpireOther> load() {
        if ((cache==null)) {
            try {
                cache = new Yaml().loadAs(new FileReader((RESOURCE_PATH)), (Container.class)).items;
            } catch (Exception _x) {
            }
        }
        return (cache);
    }

    private static class Container {

        public LinkedHashMap<String, MissionAmarrEmpireOther> items;

    }

}
